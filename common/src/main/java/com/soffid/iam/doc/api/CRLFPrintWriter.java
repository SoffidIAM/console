package com.soffid.iam.doc.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class CRLFPrintWriter extends PrintWriter {

	public CRLFPrintWriter(Writer out) {
		super(out);
	}

	public CRLFPrintWriter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
		super(file, csn);
	}

	public CRLFPrintWriter(File file) throws FileNotFoundException {
		super(file);
	}

	public CRLFPrintWriter(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}

	public CRLFPrintWriter(OutputStream out) {
		super(out);
	}

	public CRLFPrintWriter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, csn);
	}

	public CRLFPrintWriter(String fileName) throws FileNotFoundException {
		super(fileName);
	}

	public CRLFPrintWriter(Writer out, boolean autoFlush) {
		super(out, autoFlush);
	}

	@Override
	public void println() {
		write ("\r\n");
	}


}
