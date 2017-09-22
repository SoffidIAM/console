package com.soffid.iam.web.component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

import org.zkoss.zul.Div;

public class FileDump extends Div {
	String src;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
		invalidate();
	}

	@Override
	public void redraw(Writer out) throws IOException {
		if (src != null)
		{
			File file = new File(src);
			if (file.exists())
			{
				FileReader reader = new FileReader(file);
				int read;
				do {
					read = reader.read();
					if (read < 0)
						break;
					out.write(read);
				} while (true);
				file.delete();
			}
		}
	}
	
	

}
