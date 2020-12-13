package com.soffid.iam.juli;

import java.io.UnsupportedEncodingException;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.regex.Pattern;

public class AsyncFileHandler extends java.util.logging.Handler {
	private String directory;
	private String prefix;
	private String suffix;
	private static PrivilegedAction<String> selector = null;
	Integer maxDays;
	Map<String, org.apache.juli.AsyncFileHandler> handlers = new HashMap<String, org.apache.juli.AsyncFileHandler>();
	
	public AsyncFileHandler () {
        String className = this.getClass().getName(); //allow classes to override
        // Retrieve configuration of logging file name
        directory = getProperty(className + ".directory", "logs");

        prefix = getProperty(className + ".prefix", "soffid.");

        suffix = getProperty(className + ".suffix", ".log");

        String sMaxDays = getProperty(className + ".maxDays", "30");
		try {
            maxDays = Integer.valueOf(sMaxDays);
        } catch (NumberFormatException ignore) {
            maxDays = 30;
        }
        // Get logging level for the handler
        setLevel(Level.parse(getProperty(className + ".level", "" + Level.ALL)));
	}
	
	
	public static void registerSelector(PrivilegedAction<String> selector) {
		String caller = new Exception().getStackTrace()[1].getClassName();
		if (caller.equals("com.soffid.iam.upload.UploadService"))
			AsyncFileHandler.selector = selector;
	}
	
	public org.apache.juli.AsyncFileHandler getHandler() {
		String tenant = null;
		if (selector != null)
			tenant = selector.run();
		if (tenant == null)
			tenant = "master";
		
		org.apache.juli.AsyncFileHandler afh = handlers.get(tenant);
		if (afh == null) {
			if ("master".equals(tenant))
				afh = new org.apache.juli.AsyncFileHandler(directory, prefix, suffix, maxDays );
			else
				afh = new org.apache.juli.AsyncFileHandler(directory, prefix+tenant+".", suffix, maxDays );
		}
		return afh;
	}
	@Override
	public void publish(LogRecord record) {
		getHandler().publish(record);
	}

	@Override
	public void flush() {
		getHandler().flush();
	}

	@Override
	public void close() throws SecurityException {
		getHandler().close();
	}

    private String getProperty(String name, String defaultValue) {
        String value = LogManager.getLogManager().getProperty(name);
        if (value == null) {
            value = defaultValue;
        } else {
            value = value.trim();
        }
        return value;
    }
}
