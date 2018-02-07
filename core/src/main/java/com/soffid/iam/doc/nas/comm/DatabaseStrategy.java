package com.soffid.iam.doc.nas.comm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.doc.exception.NASException;
import com.soffid.iam.doc.model.DocumentBlockEntity;
import com.soffid.iam.doc.model.DocumentBlockEntityDao;
import com.soffid.iam.doc.nas.CommunicationStrategy;

public class DatabaseStrategy implements CommunicationStrategy {
	
	private DocumentBlockEntityDao documentBlockEntityDao;

	public DatabaseStrategy ()
	{
		this. documentBlockEntityDao = (DocumentBlockEntityDao) ServiceLocator.instance().getService("documentBlockEntityDao");
	}

	@Override
	public void setProperties() throws NASException {
	}

	@Override
	public void uploadFile(File archivo, String path) throws NASException {
		try {
			if (! documentBlockEntityDao.findByPath(path).isEmpty())
				throw new NASException(String.format("File %s already exist", path));
			long seq = 1;
			FileInputStream in = new FileInputStream(archivo);
			byte[] b = new byte[13684];
			int read;
			while ( (read = in.read(b)) >= 0)
			{
				DocumentBlockEntity documentBlock = documentBlockEntityDao.newDocumentBlockEntity();
				documentBlock.setPath(path);
				documentBlock.setSequenceNumber(seq++);
				byte b2[] = Arrays.copyOf(b, read);
				documentBlock.setContent(b2);
				documentBlockEntityDao.create(documentBlock);
			}
		} catch (IOException e) {
			throw new NASException("Error storing document", e);
		}
	}

	@Override
	public File retreiveFile(String path) throws NASException {
		try {
			File f = null;
			FileOutputStream out = null;
			for (DocumentBlockEntity db: documentBlockEntityDao.findByPath(path))
			{
				if (db.getContent() != null)
				{
					if (f == null)
					{
						f = File.createTempFile("doc", "tmp");
						out = new FileOutputStream (f);
					}
					out.write(db.getContent());
				}
			}
			if (out != null)
				out.close();
			return f;
		} catch (IOException e) {
			throw new NASException("Error reading document", e);
		}
	}

	@Override
	public void cleanTemporaryResources() throws NASException {
	}

	@Override
	public void deleteFile(String path) throws NASException {
		documentBlockEntityDao.remove(documentBlockEntityDao.findByPath(path));
	}

}
