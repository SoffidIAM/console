package com.soffid.iam.web.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.hsqldb.types.Charset;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Textbox;

import com.ibm.icu.text.SimpleDateFormat;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;


public class LogHandler extends FrameHandler {

	public LogHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		
		String file = getFileName();

		LinkedList<String> data = new LinkedList<>();
		try {
			BufferedReader reader = new BufferedReader( new FileReader(file));
			int size = 0;
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				if (size >= 1000)
					data.removeFirst();
				else
					size ++;
				data.addLast(line);
			}
		} catch (IOException e) {
			throw new UiException(e);
		}
		
		StringBuffer sb = new StringBuffer();
		for (String line: data) {
			sb.append(line).append("\n");
		}
		Textbox tb = (Textbox) getFellow("txt");
		tb.setValue(sb.toString());
		tb.setFocus(true);
		tb.setSelectionRange(sb.length(), sb.length());
	}

	public String getFileName() {
		String file = System.getProperty("catalina.home") + "/logs/soffid.";
		
		try {
			if (! Security.getCurrentTenantName().equals(Security.getMasterTenantName()))
				file = file + Security.getCurrentTenantName()+".";
		} catch (InternalErrorException e) {
		}
		
		file = file + new Timestamp(System.currentTimeMillis()).toString().substring(0, 10)+".log";
		return file;
	}
	
	public void download(Event ev) throws FileNotFoundException {
		Filedownload.save(new File(getFileName()), "text/plain");
	}
}
