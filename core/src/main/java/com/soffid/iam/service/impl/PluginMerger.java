package com.soffid.iam.service.impl;

import com.soffid.iam.model.BlobConfigurationEntity;
import com.soffid.iam.model.BlobConfigurationEntityDao;
import com.soffid.iam.model.ServerPluginEntityDao;
import com.soffid.iam.service.ConfigurationService;
import es.caib.seycon.ng.exception.InternalErrorException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class PluginMerger {
    ServerPluginEntityDao pluginDao;
    ConfigurationService configuracio;
    BlobConfigurationEntityDao blobConfigDao;

    public BlobConfigurationEntityDao getBlobConfigDao() {
        return blobConfigDao;
    }

    public void setBlobConfigDao(BlobConfigurationEntityDao blobConfigDao) {
        this.blobConfigDao = blobConfigDao;
    }

    public ServerPluginEntityDao getPluginDao() {
        return pluginDao;
    }

    public void setPluginDao(ServerPluginEntityDao pluginDao) {
        this.pluginDao = pluginDao;
    }

    public ConfigurationService getConfiguracio() {
        return configuracio;
    }

    public void setConfiguracio(ConfigurationService configuracio) {
        this.configuracio = configuracio;
    }

    public void merge() throws IOException, InternalErrorException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(stream);
        Collection c = blobConfigDao.loadAll();
        for (Iterator it = c.iterator(); it.hasNext();) {
            BlobConfigurationEntity blob = (BlobConfigurationEntity) it.next();
            if (blob.getName().startsWith("component."))        //$NON-NLS-1$
                dump(out, blob);
        }
        out.close();
        configuracio.updateBlob("server-base", stream.toByteArray()); //$NON-NLS-1$
        stream.close();
    }

    private void dump(ZipOutputStream out, BlobConfigurationEntity blob)
            throws IOException {
        ZipInputStream in = new ZipInputStream(new ByteArrayInputStream(
                blob.getValue()));
        ZipEntry entry = in.getNextEntry();
        while (entry != null) {
            try {
                String name = entry.getName();
                if (name.startsWith("META-INF") && //$NON-NLS-1$
                        (name.endsWith(".SF") || //$NON-NLS-1$
                         name.endsWith(".RSA") || //$NON-NLS-1$
                         name.endsWith(".DSA") ) ) { //$NON-NLS-1$
                    // Nothing to do
                } else {
                    out.putNextEntry(entry);
                    byte buffer[] = new byte[2048];
                    int read = in.read(buffer);
                    while (read > 0) {
                        out.write(buffer, 0, read);
                        read = in.read(buffer);
                    }
                    out.closeEntry();
                }
            } catch (ZipException e) {
                // Ignore duplicate Entry
            }
            in.closeEntry();
            entry = in.getNextEntry();
        }
    }
}
