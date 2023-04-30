package com.soffid.iam.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;

import com.soffid.iam.api.DataType;
import com.soffid.iam.config.Config;
import com.soffid.iam.model.LuceneIndexEntity;
import com.soffid.iam.model.LuceneIndexEntityDao;
import com.soffid.iam.model.LuceneIndexPartEntity;
import com.soffid.iam.model.LuceneIndexPartEntityDao;
import com.soffid.iam.utils.Security;

public class LuceneIndexStatus {
	long timestamp;
	Directory directory;
	private String name;
	private LuceneIndexEntityDao luceneIndexEntityDao;
	private LuceneIndexPartEntityDao luceneIndexPartEntityDao;
	private boolean dirty;
	
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
			directory = new NIOFSDirectory(getIndexDir());
		}
		return directory;
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
					if (out != null) out.close();
					out = new FileOutputStream(new File(dir, partName));
				}
				out.write(part.getData());
			}
			out.close();
			timestamp = current.getTimestamp().getTime();
		}
	}
	public File getIndexDir() throws FileNotFoundException, IOException {
		File root = getBaseDir();
		File dir = new File(root, name);
		return dir;
	}

	public synchronized void fetchIfNeeded() throws FileNotFoundException, IOException {
		File dir = getIndexDir();

		dir.mkdirs();
		
		LuceneIndexEntity current = luceneIndexEntityDao.findByName(name);
		if (current != null && current.getTimestamp().getTime() > timestamp) {
			fetchFromDatabase();
		}
	}

	public synchronized void save() throws FileNotFoundException, IOException {
		File dir = getIndexDir();

		dir.mkdirs();
		
		LuceneIndexEntity current = luceneIndexEntityDao.findByName(name);
		if (current != null) {
			luceneIndexPartEntityDao.remove(current.getParts());
		}
		else {
			current = luceneIndexEntityDao.newLuceneIndexEntity();
			current.setName(name);
			current.setTimestamp(new Date());
			luceneIndexEntityDao.create(current);
		}

		dirty = false;
		for (File f: dir.listFiles()) {
			FileInputStream in = new FileInputStream(f);
			int order = 0;
			byte [] b = new byte[64000];
			for (int read = in.read(b); read >= 0; read = in.read(b)) {
				LuceneIndexPartEntity part = luceneIndexPartEntityDao.newLuceneIndexPartEntity();
				part.setName(f.getName());
				part.setIndex(current);
				part.setOrder(order ++);
				if (read == b.length)
					part.setData(b);
				else
					part.setData(Arrays.copyOf(b, read));
				luceneIndexPartEntityDao.create(part);
			}
			in.close();
		}
		
		current.setTimestamp(new Date());
		luceneIndexEntityDao.update(current);
	}
	
	public void setDirty() {
		dirty = true;
	}
	
	public void saveIfNeeded() throws FileNotFoundException, IOException {
		if (dirty)
			save();
	}
	public synchronized void reset() throws FileNotFoundException, IOException {
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
		dirty = false;
	}
}
