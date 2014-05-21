package es.caib.seycon.ng.upload;

import java.io.PrintStream;
import java.util.Locale;

import org.apache.commons.logging.Log;

public class LoggingStream extends PrintStream {

	@Override
	public void flush() {
		printBuffer();
		super.flush();
	}

	@Override
	public PrintStream printf(String format, Object... args) {
		buffer.append (String.format(format, args));
		if (buffer.indexOf("\n") >= 0)
			printBuffer();
		return this;
	}

	@Override
	public PrintStream printf(Locale l, String format, Object... args) {
		buffer.append (String.format(l, format, args));
		if (buffer.indexOf("\n") >= 0)
			printBuffer();
		return this;
	}

	/**
	 * Create a new PrintWriter, without automatic line flushing.
	 * 
	 * @param prefix
	 *            A tag to prefix lines with for debugging purposes
	 * @param out
	 *            A character-output stream
	 */
	public LoggingStream(Log log) {
		super(System.out);
		this.log = log;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#print(boolean)
	 */
	@Override
	public void print(boolean x) {
		buffer.append(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#print(char)
	 */
	@Override
	public void print(char x) {
		buffer.append(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#print(int)
	 */
	@Override
	public void print(int x) {
		buffer.append(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#print(long)
	 */
	@Override
	public void print(long x) {
		buffer.append(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#print(float)
	 */
	@Override
	public void print(float x) {
		buffer.append(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#print(double)
	 */
	@Override
	public void print(double x) {
		buffer.append(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#print(char[])
	 */
	@Override
	public void print(char[] x) {
		buffer.append(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#print(java.lang.String)
	 */
	@Override
	public void print(String x) {
		buffer.append(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#print(java.lang.Object)
	 */
	@Override
	public void print(Object x) {
		buffer.append(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println()
	 */
	@Override
	public void println() {
		printBuffer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println(boolean)
	 */
	@Override
	public void println(boolean x) {
		print(x);
		printBuffer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println(char)
	 */
	@Override
	public void println(char x) {
		print(x);
		printBuffer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println(int)
	 */
	@Override
	public void println(int x) {
		print(x);
		printBuffer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println(long)
	 */
	@Override
	public void println(long x) {
		print(x);
		printBuffer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println(float)
	 */
	@Override
	public void println(float x) {
		print(x);
		printBuffer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println(double)
	 */
	@Override
	public void println(double x) {
		print(x);
		printBuffer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println(char[])
	 */
	@Override
	public void println(char[] x) {
		print(x);
		printBuffer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println(java.lang.String)
	 */
	@Override
	public void println(String x) {
		print(x);
		printBuffer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.PrintWriter#println(java.lang.Object)
	 */
	@Override
	public void println(Object x) {
		print(x);
		printBuffer();
	}

	/**
	 * Write the characters in the print buffer out to the stream
	 */
	private void printBuffer() {
		if (buffer.length() > 0) {
			log.info(buffer.toString());
			buffer.setLength(0);
		}
	}

	/**
	 * A buffer where we store stuff before a newline
	 */
	protected final StringBuffer buffer = new StringBuffer();

	private Log log;
}
