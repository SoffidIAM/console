package es.caib.seycon.ng.servei.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ServerBaseParser {
    File f;
    String version;
    boolean webPatch = false;

    public String getVersion() {
        return version;
    }

    public boolean isWebPatch() {
        return webPatch;
    }

    public ServerBaseParser() throws IOException {
        f = File.createTempFile("base", "zip"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    protected void finalize() throws Throwable {
        // Invoke the finalizer of our superclass
        // We haven't discussed superclasses or this syntax yet
        super.finalize();

        // Delete a temporary file we were using
        // If the file doesn't exist or tempfile is null, this can throw
        // an exception, but that exception is ignored.
        f.delete();
    }

    public void parse(byte b[]) throws IOException, SAXException,
            ParserConfigurationException, XPathExpressionException {
        copyContent(b);
        ZipFile zf = new ZipFile(f);
        @SuppressWarnings("unchecked")
        Enumeration<ZipEntry> entries = ((Enumeration<ZipEntry>) zf.entries());
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            String name = entry.getName();
            name = name.replace("\\", "/"); //$NON-NLS-1$ //$NON-NLS-2$
            if (!entry.isDirectory()
                    && (name.equals("META-INF/maven/es.caib.seycon.ng/seycon-base/pom.properties") || //$NON-NLS-1$
                        name.equals("META-INF/maven/com.soffid.iam.sync/syncserver/pom.properties"))) { //$NON-NLS-1$
                parseDescriptorEntry(zf.getInputStream(entry));
            }
            if (!entry.isDirectory()
                    && name.equals("WEB-INF/console-plugin.properties")) { //$NON-NLS-1$
                webPatch = true;
                parseDescriptorEntry(zf.getInputStream(entry));
            }
        }
    }

    private void parseDescriptorEntry(InputStream in) throws SAXException,
            IOException, ParserConfigurationException, XPathExpressionException {
        Properties p = new Properties();
        p.load(in);
        version = p.getProperty("version"); //$NON-NLS-1$
    }

    public void copyContent(byte b[]) throws FileNotFoundException, IOException {
        OutputStream out = new FileOutputStream(f);
        out.write(b);
        out.close();
    }

}
