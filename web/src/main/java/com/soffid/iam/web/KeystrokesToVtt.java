package com.soffid.iam.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;


public class KeystrokesToVtt extends OutputStream {
	private OutputStream out;
	ByteBuffer buffer = ByteBuffer.allocate(99999);
	private long start;
	final static int MIN_GAP = 3;
	final static int MAX_GAP = 6;
	long previous = 0;
	StringBuffer sbPrevious = new StringBuffer();
	StringBuffer sbNext = new StringBuffer();
	
	public KeystrokesToVtt( OutputStream out, long start) throws UnsupportedEncodingException, IOException {
		this.out = out;
		this.start = start;
		out.write("WEBVTT\n\n".getBytes("UTF-8"));
	}

	@Override
	public void write(int b) throws IOException {
		if (b == '\n')
			flush();
		else
			buffer.put((byte)b);
	}

	@Override
	public void close() throws IOException {
		flush();
		flushBuffer ( MAX_GAP );
		out.close();
	}

	@Override
	public void flush() throws IOException {
		String s = new String (buffer.array(), 0, buffer.position(), "UTF-8");
		int i = s.indexOf(' ');
		if ( i > 0)
		{
			Long second = Long.decode(s.substring(0, i));
			String keys = s.substring(i+1);
			if ( second > previous + MAX_GAP)
			{
				flushBuffer ( MAX_GAP );
				sbPrevious.setLength(0);
				sbNext.setLength(0);
				sbNext.append(keys);
				previous = second;
			}
			else if (second > previous + MIN_GAP)
			{
				flushBuffer ( second - previous );
				sbPrevious = sbNext;
				sbNext = new StringBuffer();
				sbNext.append(keys);
				previous = second;
			}
			else
			{
				sbNext.append(keys);					
			}
		}
		buffer.clear();
	}

	private void flushBuffer(long gap) throws UnsupportedEncodingException, IOException {
		if ( sbNext.length() > 0 && previous > start)
		{
			StringBuffer result = new StringBuffer();
			result.append ( printTime ( previous - start - 1))
				.append ( " --> ")
				.append ( printTime ( previous + gap - start - 1))
				.append ("\n")
				.append (sbPrevious.toString())
				.append (sbNext.toString())
				.append ("\n\n");
			out.write(result.toString().getBytes("UTF-8"));
		}		
				
	}

	private String printTime(long time) {
		return String.format("%02d:%02d.000", (time / 60), time % 60);
	}

}
