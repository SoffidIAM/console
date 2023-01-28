package com.soffid.iam.api;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Calendar;

import es.caib.seycon.util.Base64;

public class BinaryData implements Comparable<Object>, Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	byte data[];
	transient File temporaryFile;

	public BinaryData (String name, byte[] data) throws IOException {
		this.name = name;
		if (data != null && data.length > 64000) {
			temporaryFile = File.createTempFile("data", "raw");
			FileOutputStream out = new FileOutputStream(temporaryFile);
			out.write(data);
			out.close();
			temporaryFile.deleteOnExit();
		} else {
			this.data = data;
		}
	}
	
	public BinaryData(String name, InputStream in) throws IOException {
		this.name = name;
		temporaryFile = File.createTempFile("data", "raw");
		FileOutputStream out = new FileOutputStream(temporaryFile);
		byte buffer[] = new byte[64000];
		for (int read = in.read(buffer); read > 0; read = in.read(buffer))
			out.write(buffer, 0, read);
		out.close();
		in.close();
		temporaryFile.deleteOnExit();
	}

	public BinaryData(String name, Reader in) throws IOException {
		this.name = name;
		temporaryFile = File.createTempFile("data", "raw");
		PrintStream out = new PrintStream(temporaryFile);
		char[] buffer = new char[64000];
		for (int read = in.read(buffer); read > 0; read = in.read(buffer)) {
			if (read == buffer.length)
				out.print(buffer);
			else
				for (int i = 0; i < read; i++)
					out.print(buffer[i]);
		}
		out.write(data);
		out.close();
		in.close();
		temporaryFile.deleteOnExit();
	}

	public String getName() {
		return name;
	}
	
	public InputStream getInputStream() throws FileNotFoundException {
		if (data != null)
			return new ByteArrayInputStream(data);
		else if (temporaryFile != null)
			return new FileInputStream(temporaryFile);
		else
			return new ByteArrayInputStream(new byte[0]);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof BinaryData) {
			BinaryData d2 = (BinaryData) obj;
			if (d2.getName() == null ? name == null : d2.getName().equals(name)) {
				byte[] b1 = new byte[64000];
				byte[] b2 = new byte[64000];
				try {
					InputStream in1 = getInputStream();
					InputStream in2 = d2.getInputStream();
					do {
						int read1 = in1.read(b1);
						int read2 = in2.read(b2);
						if (read1 != read2)
							return false;
						if (read1 < 0)
							return true;
						if (Arrays.compare(b1, 0, read1, b2, 0, read2) != 0)
							return false;
					} while(true);
				} catch (IOException e) {
					throw new RuntimeException("Error reading blob", e);
				}
			}
			else
				return false;
		}
		else
			return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		if (temporaryFile != null)
			temporaryFile.delete();
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof BinaryData)
			return getName() == null ? 
					( ((BinaryData) o).getName() == null ? 0: 1) :
					getName().compareTo(((BinaryData) o).getName());
		else
			return 1;
	}
	
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		name = (String) in.readObject();
		temporaryFile = null;
		byte buffer[] = new byte[64000];
		FileOutputStream out = null;
		boolean first = true;
		for (int read = in.readInt(); read >= 0; read = in.readInt()) {
			in.read(buffer, 0, read);
			if (first) { 
				this.data = Arrays.copyOf(buffer, read);
				first = false;
			}
			else 
			{
				if (out == null) {
					temporaryFile = File.createTempFile("data", "raw");
					out = new FileOutputStream(temporaryFile);
					out.write(data);
					data = null;
					temporaryFile.deleteOnExit();				
				}
				out.write(buffer, 0, read);
			}
		}
		if (out != null)
			out.close();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(name);
		byte buffer[] = new byte[64000];
		InputStream in = getInputStream();
		for (int read = in.read(buffer); read > 0; read = in.read(buffer)) {
			out.writeInt(read);
			out.write(buffer, 0, read);
		}
		out.writeInt(-1);
	}
	
	public static BinaryData fromString(String s) throws IOException {
		int i = s.indexOf(":");
		if (i >= 0) {
			String name = null;
			byte[] data = Base64.decode(s.substring(i+1).trim());
			try {
				name = URLDecoder.decode(s.substring(0, i), "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
			return new BinaryData(name, data);
		} else {
			return new BinaryData(null, Base64.decode(s));
		}
	}

	public String toString() {
		try {
			StringBuffer sb = new StringBuffer();
			if (name != null) 
				sb.append(URLEncoder.encode(name, "UTF-8")).append(":");
			
			InputStream in = getInputStream();
			byte buffer[] = new byte[64000];
			for (int read = in.read(buffer); read > 0; read = in.read(buffer))
				sb.append(Base64.encodeBytes(buffer, 0, read, Base64.DONT_BREAK_LINES));
			return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
