package com.soffid.iam.web.popup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.zkoss.zk.ui.UiException;

import au.com.bytecode.opencsv.CSVReader;

public class CsvParser {
	private File file;
	private String separator;
	private String charset;
	private boolean containsHeaders = true;
	private String escapeChar = "\\";
	private String quote =  "\"";
	private Vector<String> columnMappings; 
	
	public CsvParser(File file) {
		this.file = file;
	}

	public void finalize () {
		if (file != null)
			file.delete();
	}

	public void initialParse() throws IOException 
	{
		int commas = 0;
		int tabs = 0;
		int semicolons = 0;
		InputStream in = new FileInputStream(file);
		int read;
		while (( read = in.read() ) >= 0 )
		{
			if (read == ',') commas ++;
			else if (read == '\t') tabs ++;
			else if (read == ';') semicolons ++;
		}
		in.close();
		separator = commas >= tabs ?
								  ( commas >= semicolons ? "," : ";")
								: ( tabs >= semicolons ? "\t": ";");

		int charsUtf8 = contarChars ("UTF-8");
		int charsUtf16LE = contarChars ("UTF-16LE");
		int charsUtf16BE = contarChars ("UTF-16BE");
		int charsIso = contarChars ("ISO-8859-1");
		int charsWin = contarChars ("CP1252");

		charset = charsUtf16LE >= charsUtf16BE &&
					charsUtf16LE >= charsUtf8 &&
					charsUtf16LE >= charsIso &&
					charsUtf16LE >= charsWin ? "UTF-16LE":
				charsUtf16BE >= charsUtf8 &&
					charsUtf16BE >= charsIso &&
					charsUtf16BE >= charsWin ? "UTF-16BE":
				charsUtf8 >= charsIso &&
					charsUtf8 >= charsWin ? "UTF-8":
				charsIso >= charsWin ? "ISO-8859-1":
					"CP1252";
		in.close();
						
	}

	private int contarChars(String charsetName)  {
		int chars = 0;
		int weirds = 0; 
		try {
			Reader r = new InputStreamReader ( new FileInputStream(file), Charset.forName(charsetName));
			int read;
			while (( read = r.read() ) >= 0 )
			{
				if (! Character.isDefined((char)read))
				{
					chars = 0;
					break;
				}
				if ( Character.isAlphabetic((char)read))
					chars ++;
				else if ( read < 32 || read > 65)
					weirds ++;
			}
			r.close();
		} catch (UnsupportedEncodingException e) {
			return 0;
		} catch (IOException e) {
			return 0;
		}
		return chars - weirds * 4;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public boolean isContainsHeaders() {
		return containsHeaders;
	}

	public void setContainsHeaders(boolean containsHeaders) {
		this.containsHeaders = containsHeaders;
	}
	
	public List<String[]> readFirstLines () throws IOException {
		LinkedList<String[]> r = new LinkedList<String[]>(); 
		
		Reader reader = new InputStreamReader( 
				new FileInputStream(file), 
				charset);
		CSVReader csv = new CSVReader( reader, 
				separator.isEmpty() ? ',' : separator.charAt(0), 
				quote.isEmpty() ? '\0' : quote.charAt(0), 
				escapeChar.isEmpty() ? '\0' : escapeChar.charAt(0) );
		int maxcols = 0;
		for ( int i = ( containsHeaders ? 0: 1); i < 10;  i++)
		{
			String[] line = csv.readNext();
			if (line == null)
				break;
			if ( line.length > maxcols)
				maxcols = line.length;
			r.add(line);
		}
		
		String[] header;
		if ( ! containsHeaders)
		{
			header = new String [maxcols];
			for (int i = 1; i <= maxcols; i++)
			{
				header[i-1] = "#"+i;
			}
			r.addFirst(header);
		}
		csv.close();
		return r;
	}

	public String getEscapeChar() {
		return escapeChar;
	}

	public void setEscapeChar(String escapeChar) {
		this.escapeChar = escapeChar;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public void setMappings(Vector<String> columnMappings) {
		this.columnMappings = columnMappings;
	}

	public Vector<String> getColumnMappings() {
		return columnMappings;
	}

	public void setColumnMappings(Vector<String> columnMappings) {
		this.columnMappings = columnMappings;
	}
	
	public Iterator<Map<String,String>> iterator() throws IOException {
		Reader reader = new InputStreamReader( 
				new FileInputStream(file), 
				charset);
		CSVReader csv = new CSVReader( reader, 
				separator.isEmpty() ? ',' : separator.charAt(0), 
				quote.isEmpty() ? '\0' : quote.charAt(0), 
				escapeChar.isEmpty() ? '\0' : escapeChar.charAt(0) );
		if (containsHeaders)
			csv.readNext();
		return new CsvIterator(csv);
	}
	
	class CsvIterator implements Iterator<Map<String,String>> {
		private CSVReader csv;
		private String[] next;

		CsvIterator (CSVReader csv) throws IOException {
			this.csv = csv;
			next = csv.readNext();
		}
		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public Map<String, String> next() {
			if (next == null)
				return null;
			Map<String,String> m = new HashMap<String, String>();
			for ( int i = 0; i < columnMappings.size(); i++)
			{
				String mapping = columnMappings.get(i);
				if ( mapping != null && ! mapping.isEmpty() && i < next.length)
				{
					m.put(mapping, next[i] == null || next[i].isEmpty()? null: next[i]);
				}
			}

			try {
				next = csv.readNext();
			} catch (IOException e) {
				next = null;
				throw new UiException(e);
			}
			
			return m;
		}
		
	}
}
