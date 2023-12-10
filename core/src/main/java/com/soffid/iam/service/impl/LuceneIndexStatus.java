package com.soffid.iam.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.FieldInfo;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.hibernate.Hibernate;
import org.hibernate.UnresolvableObjectException;

import com.soffid.iam.config.Config;
import com.soffid.iam.model.LuceneIndexEntity;
import com.soffid.iam.model.LuceneIndexEntityDao;
import com.soffid.iam.model.LuceneIndexPartEntity;
import com.soffid.iam.model.LuceneIndexPartEntityDao;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class LuceneIndexStatus {
	long timestamp;
	Directory directory;
	private String name;
	private LuceneIndexEntityDao luceneIndexEntityDao;
	private LuceneIndexPartEntityDao luceneIndexPartEntityDao;
	private boolean dirty;
	private boolean indexOwner;
	boolean useFullContentPositions;
	
	public LuceneIndexStatus(LuceneIndexEntityDao luceneIndexEntityDao,
			LuceneIndexPartEntityDao luceneIndexPartEntityDao,
			String name) {
		this.name = name;
		this.luceneIndexEntityDao = luceneIndexEntityDao;
		this.luceneIndexPartEntityDao = luceneIndexPartEntityDao;
		this.timestamp = 0;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Directory getDirectory() throws IOException {
		if (directory == null) {
			fetchFromDatabase();
			directory = new NIOFSDirectory(getIndexDir().toPath());
			searchContentsField();
		}
		return directory;
	}
	
	private void searchContentsField() {
		useFullContentPositions = true;
		IndexReader reader;
		try {
			reader = DirectoryReader.open(directory);
			IndexSearcher indexer = new IndexSearcher(reader);
			for (LeafReaderContext leaf: indexer.getLeafContexts()) {
				for (FieldInfo fi: leaf.reader().getFieldInfos()) {
					if (fi.getName().equals("$contents")) {
						useFullContentPositions = fi.getIndexOptions() == IndexOptions.DOCS_AND_FREQS_AND_POSITIONS;
						break;
					}
				}
			}
		} catch (IOException e) {
		}
	}
	
	private File getBaseDir() throws FileNotFoundException, IOException {
		File f;
		if (Security.isSyncServer()) {
			f = Config.getConfig().getHomeDir();
		} else {
			f = new File (System.getProperty("catalina.base"));
		}
		File f2 = new File(f, "index");
		f2.mkdirs();
		return f2;
	}
	
	public synchronized void fetchFromDatabase() throws FileNotFoundException, IOException {
		File dir = getIndexDir();
		Long lastTimeStamp = null;
		File lastFile = null;
		dir.mkdirs();
		
		LuceneIndexEntity current = luceneIndexEntityDao.findByName(name);
		if (current != null) {
			for (File f: dir.listFiles()) {
				f.delete();
			}
			FileOutputStream out = null;
			String partName = null;
			for (LuceneIndexPartEntity part: luceneIndexPartEntityDao.findByIndex(current.getId())) {
				if (! part.getName().equals(partName)) {
					partName = part.getName();
					if (out != null) {
						out.close();
						if (lastTimeStamp != null)
								lastFile.setLastModified(lastTimeStamp);
					}
					lastFile = new File(dir, partName);
					out = new FileOutputStream(lastFile);
					lastTimeStamp = part.getTimestamp();
				}
				out.write(part.getData());
			}
			if (out != null) {
				out.close();
				if (lastTimeStamp != null)
					lastFile.setLastModified(lastTimeStamp);
				timestamp = current.getTimestamp();
			}
		}
	}
	
	public File getIndexDir() throws FileNotFoundException, IOException {
		File root = getBaseDir();
		File dir = new File(root, name);
		return dir;
	}

	public synchronized void fetchIfNeeded() throws FileNotFoundException, IOException {
		LuceneIndexEntity current = luceneIndexEntityDao.findByName(name);
		if (current != null && current.getTimestamp() > timestamp) {
			fetchFromDatabase();
		}
	}

	public void save() throws FileNotFoundException, IOException, InternalErrorException {
		File dir = getIndexDir();
		
		dir.mkdirs();
		
		LuceneIndexEntity current = luceneIndexEntityDao.findByName(name);
		if (current == null) {
			current = luceneIndexEntityDao.newLuceneIndexEntity();
			current.setName(name);
			current.setTimestamp(System.currentTimeMillis());
			luceneIndexEntityDao.create(current);
		} else {
			luceneIndexEntityDao.lock(current);
		}
		synchronizedSave(dir, current);
	}
	
	private synchronized void synchronizedSave(File dir, LuceneIndexEntity current) throws FileNotFoundException, IOException {
		// Keep files not modified. Remove others from database
		List<File> files = new LinkedList<>( Arrays.asList( dir.listFiles() ) );
		String partName = null;
		boolean remove = false;
		for (LuceneIndexPartEntity part: luceneIndexPartEntityDao.findByIndex(current.getId())) {
			if (! part.getName().equals(partName)) {
				remove = false;
				partName = part.getName();
				boolean found = false;
				for (File f: files) {
					if (f.getName().equals(part.getName())) {
						found = true;
						if (part.getTimestamp() == null || 
							part.getTimestamp().longValue() < f.lastModified()) {
							luceneIndexPartEntityDao.remove(part);
							remove = true;
						}
						else 
						{
							files.remove(f);
						}
						break;
					}
				}
				if (!found) {
					luceneIndexPartEntityDao.remove(part);
					remove = true;
				}
			} else if (remove) {
				luceneIndexPartEntityDao.remove(part);
			}
		}
		
		
		// Add new or modified files
		for (File f: files) {
			FileInputStream in = new FileInputStream(f);
			int order = 0;
			byte [] b = new byte[64000];
			for (int read = in.read(b); read >= 0; read = in.read(b)) {
				LuceneIndexPartEntity part = luceneIndexPartEntityDao.newLuceneIndexPartEntity();
				part.setName(f.getName());
				part.setIndex(current);
				part.setOrder(order ++);
				part.setTimestamp(f.lastModified());
				if (read == b.length)
					part.setData(b);
				else
					part.setData(Arrays.copyOf(b, read));
				luceneIndexPartEntityDao.create(part);
			}
			in.close();
		}
		
		dirty = false;		
		timestamp = System.currentTimeMillis();
		current.setTimestamp(timestamp);
		luceneIndexEntityDao.update(current);
		indexOwner = true;
	}
	
	public void setDirty() {
		dirty = true;
	}
	
	public void saveIfNeeded() throws FileNotFoundException, IOException, InternalErrorException {
		if (dirty) {
			try {
				save();
			} catch (InternalErrorException e) {
				LogFactory.getLog(getClass()).warn("Error saving text index "+name+": "+e.toString());
			}
		}
	}
	public synchronized void reset() throws FileNotFoundException, IOException, InternalErrorException {
		dirty = false;
		File dir = getIndexDir();
			
		LuceneIndexEntity current = luceneIndexEntityDao.findByName(name);
		if (current != null) {
			luceneIndexPartEntityDao.remove(current.getParts());
			luceneIndexEntityDao.remove(current);
		}
		
		if (dir.exists()) {
			for (File f: dir.listFiles()) {
				f.delete();
			}
		}		
		dirty = true;
		indexOwner = true;
		directory.close();
		directory = new NIOFSDirectory(getIndexDir().toPath());
		save();
		useFullContentPositions = true;
	}
	
	public void fetchforWriting() throws InterruptedException, FileNotFoundException, IOException, InternalErrorException {
		if (dirty )
			return;
		do {
			LuceneIndexEntity current = luceneIndexEntityDao.findByName(name);
			if (current == null) {
				File dir = getIndexDir();
				if (dir.exists()) {
					for (File f: dir.listFiles()) {
						f.delete();
					}
				}		
				dir.mkdirs();
				save();
				return;
			} else {
				luceneIndexEntityDao.refresh(current);
			}
			Long l = 75000 - (System.currentTimeMillis() - current.getTimestamp() );
			if (current.getTimestamp() == timestamp) {
				return;
			}
			indexOwner = false;
			if (l < 0 ) { 
				fetchIfNeeded();
				indexOwner = true;
				return;
			}
			else
				Thread.sleep(l);
		} while (true);
	}
	public boolean isUseFullContentPositions() {
		return useFullContentPositions;
	}
}
