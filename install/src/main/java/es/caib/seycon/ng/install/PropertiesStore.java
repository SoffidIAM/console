package es.caib.seycon.ng.install;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Properties;

import com.install4j.api.context.Context;

public class PropertiesStore {

	static Properties p = new Properties();
	
    public static final String DB_STATUS = "dbStatus"; //$NON-NLS-1$

    private static String keys[] = { "dbDriver", "dbDriverString", "dbDriverUrl", "dbDriverClass", "dbValidationQuery", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    		"dbSanitySelect", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            "dbPort", "dbTableTablespaceSize", "dbIndexTablespaceSize", "hostName", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            "dbHost", "dbSid", "dbUser", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
            "dbPassword", "dbPasswordCipher", "dbVersion", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
            "dbTableTablespace", "dbIndexTablespace", DB_STATUS //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

    };

    Context ctx; 

    public PropertiesStore(com.install4j.api.context.Context ctx) {
    	this.ctx = ctx;
    	load ();
    }

    public void load() {
        String s = ctx.getInstallationDirectory().getPath();
        s += "/conf/system.properties"; //$NON-NLS-1$
        File configFile = new File(s);
        try {
            if (configFile.isFile() && configFile.canRead()) {
                p.load(new FileInputStream(configFile));
                Enumeration e = p.keys();
                while (e.hasMoreElements()) {
                    String key = (String) e.nextElement();
                    String v = p.getProperty(key);
                    if (key.startsWith("i.")) //$NON-NLS-1$
                    {
                    	if (ctx.getVariable(key.substring(2)) == null)
                    		ctx.setVariable(key.substring(2), Integer.decode(v));
                    }
                    else if (key.startsWith("l.")) //$NON-NLS-1$
                    {
                    	if (ctx.getVariable(key.substring(2)) == null)
                    		ctx.setVariable(key.substring(2), Long.decode(v));
                    }
                    else
                    {
                   		ctx.setVariable(key, v);
                    }
                }
                
	        } else {
	            p.load(new FileInputStream(configFile+".template"));
	            Enumeration e = p.keys();
	            while (e.hasMoreElements()) {
	                String key = (String) e.nextElement();
	                String v = p.getProperty(key);
	                if (key.startsWith("i.")) //$NON-NLS-1$
	                    ctx.setVariable(key.substring(2), Integer.decode(v));
	                else if (key.startsWith("l.")) //$NON-NLS-1$
	                    ctx.setVariable(key.substring(2), Long.decode(v));
	                else
	                    ctx.setVariable(key, v);
	            }
	        }
        } catch (Exception e1) {
          e1.printStackTrace();
      }
    }

    public void save() throws IOException {
        String s = ctx.getInstallationDirectory().getPath();
        s += "/conf/system.properties"; //$NON-NLS-1$
        File configFile = new File(s);
        try {
            for (int i = 0; i < keys.length; i++) {
                Object value = ctx.getVariable(keys[i]);
                if (value != null && ! "".equals(value)) {
                    if (value instanceof Long)
                        p.setProperty("l." + keys[i], value.toString()); //$NON-NLS-1$
                    else if (value instanceof Integer)
                        p.setProperty("i." + keys[i], value.toString()); //$NON-NLS-1$
                    else
                        p.setProperty(keys[i], value.toString());
                }
            }
            if ( ctx.getVariable("dbPasswordClearText") != null )
            {
            	p.setProperty("dbPassword", PasswordCipher.encrypt((String) ctx.getVariable("dbPasswordClearText")));
            	p.setProperty("dbPasswordCipher", "com.soffid.iam.tomcat.SoffidPasswordCipher");
            }
            p.store(new FileOutputStream(configFile), "Autogenerated by Soffid installer");  //$NON-NLS-1$
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
		String driverClass = (String) ctx.getVariable("dbDriverClass");
        File f = new File (ctx.getInstallationDirectory().getPath()+"/conf/tomee.xml");
        if (f.canRead() && driverClass != null && ! "".equals(driverClass))
        {
        	InputStreamReader reader = new InputStreamReader( new FileInputStream(f));
        	StringBuffer sb = new StringBuffer();
        	int ch;
			while ((ch = reader.read()) != -1)
				sb.append((char)ch);
			reader.close();
			final String pattern = "jdbcDriver";
			int search = sb.indexOf(pattern);
			String result;
			if (search >= 0)
			{
				int end = sb.indexOf("\n", search);
				result = sb.substring(0, search) + 
					"jdbcDriver = "+driverClass +
					sb.substring(end);
			}
			else
				result = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f));
			writer.write(result);
			writer.close();
        }
    }
}
