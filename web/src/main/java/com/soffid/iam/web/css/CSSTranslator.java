package com.soffid.iam.web.css;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.web.laf.StandardColors;

public abstract class CSSTranslator extends HttpServlet {
	String getText () throws IOException {
		InputStream in = getServletContext().getResourceAsStream(getResourceName());
		StringBuffer sb = new StringBuffer();
		Reader r = new InputStreamReader(in);
		for (int i = r.read(); i >= 0; i = r.read())
		{
			sb.append((char) i);
		}
		in.close();
		return sb.toString();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentLocale = org.zkoss.util.Locales.getCurrent().getLanguage();
		boolean rtl = "ar".equals(currentLocale) ;
		
		String green = ConfigurationCache.getProperty("soffid.ui.color1");
		if (green == null) green = StandardColors.originalGreen;
		String greenLight = generateString( Color.decode(green).brighter() );
		String greenDark = generateString( Color.decode(green).darker() );

		String blue = ConfigurationCache.getProperty("soffid.ui.color2");
		if (blue == null) blue = StandardColors.originalBlue;
		String blueLight = generateString( Color.decode(blue).brighter() );
		String blueDark = generateString( Color.decode(blue).darker() );

		
		String sky = ConfigurationCache.getProperty("soffid.ui.color3");
		if (sky == null) sky = StandardColors.originalSky;
		String skyLight = generateString( Color.decode(sky).brighter() );
		String skyDark = generateString( Color.decode(sky).darker() );


		String greenText = ConfigurationCache.getProperty("soffid.ui.text1");
		if (greenText == null) greenText = "black";

		String blueText = ConfigurationCache.getProperty("soffid.ui.text2");
		if (blueText == null) blueText = "white";

		String skyText = ConfigurationCache.getProperty("soffid.ui.text3");
		if (skyText == null) skyText = "white";

		String t = getText();
		t = t.replaceAll("\\{GREENLIGHT\\}", greenLight)
			.replaceAll("\\{GREENDARK\\}", greenDark)
			.replaceAll("\\{GREENTEXT\\}", greenText)
			.replaceAll("\\{GREEN\\}", green)
			.replaceAll("\\{BLUE\\}", blue)
			.replaceAll("\\{BLUELIGHT\\}", blueLight)
			.replaceAll("\\{BLUEDARK\\}", blueDark)
			.replaceAll("\\{BLUETEXT\\}", blueText)
			.replaceAll("\\{SKYDARK\\}", skyDark)
			.replaceAll("\\{SKYLIGHT\\}", skyLight)
			.replaceAll("\\{SKYTEXT\\}", skyText)
			.replaceAll("\\{SKY\\}", sky)
			.replaceAll("\\{URL_GREENLIGHT\\}", greenLight.replaceAll("#", "%23"))
			.replaceAll("\\{URL_GREENDARK\\}", greenDark.replaceAll("#", "%23"))
			.replaceAll("\\{URL_GREENTEXT\\}", greenText.replaceAll("#", "%23"))
			.replaceAll("\\{URL_GREEN\\}", green.replaceAll("#", "%23"))
			.replaceAll("\\{URL_BLUE\\}", blue.replaceAll("#", "%23"))
			.replaceAll("\\{URL_BLUELIGHT\\}", blueLight.replaceAll("#", "%23"))
			.replaceAll("\\{URL_BLUEDARK\\}", blueDark.replaceAll("#", "%23"))
			.replaceAll("\\{URL_BLUETEXT\\}", blueText.replaceAll("#", "%23"))
			.replaceAll("\\{URL_SKYDARK\\}", skyDark.replaceAll("#", "%23"))
			.replaceAll("\\{URL_SKYLIGHT\\}", skyLight.replaceAll("#", "%23"))
			.replaceAll("\\{URL_SKYTEXT\\}", skyText.replaceAll("#", "%23"))
			.replaceAll("\\{URL_SKY\\}", sky.replaceAll("#", "%23"))
			.replaceAll("\\{DIRECTION\\}", rtl? "rtl" : "ltr")
			.replaceAll("\\{COLSORTER_POSITION\\}", rtl? "1%" : "99%")
			.replaceAll("\\{TEXT_ALIGN_LEFT\\}", rtl? "right": "left")
			.replaceAll("\\{TEXT_ALIGN_RIGHT\\}", rtl? "left": "right")
			.replaceAll("\\{COLLAPSE_IMG\\}", rtl? "collapse-rtl": "collapse")
			
			;
		
		byte[] b = t.getBytes("UTF-8");
		resp.setStatus(200);
		resp.setContentLength(b.length);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/css");
		ServletOutputStream outputStream = resp.getOutputStream();
		outputStream.write(b);
		outputStream.close();
	}

	private String generateString(Color color) {
		String s = Long.toString( ((long)color.getRed()) << 16 |
				((long)color.getGreen()) << 8 |
				((long)color.getBlue()), 16);
		while (s.length() < 6) s = "0"+s;
		return "#" + s;
	}

	private String enlight(String green) {
		int pair[];
		try {
			pair = parse (green);
			return format ( enlight(pair[0] ), enlight(pair[1] ), enlight(pair[2] ) );
		} catch (Exception e) {
			return green;
		}
	}

	private int enlight(int i) {
		double f = 0.95 - ((double) i) / 256.0;
		f = 256 * (1.0 - ( f * f));
		if (f > 255.0) f = 255.0;
		return (int) f;
	}

	private String dark(String green) {
		int pair[];
		try {
			pair = parse (green);
			return format ( dark(pair[0] ), dark(pair[1] ), dark(pair[2] ) );
		} catch (Exception e) {
			return green;
		}
	}

	private int dark(int i) {
		double f = ((double) i) / 256.0 - 0.05;
		f = 256.0 * ( f * f);
		if (f < 0.0) f = 0.0; 
		return (int) f;
	}
	
	private String format(int i, int j, int k) {
		return "#"+ toHex(i) + toHex(j) + toHex ( k );
	}

	private String toHex(int i) {
		String s = Integer.toHexString(i);
		if (s.length() == 1) s = "0"+s;
		return s;
	}

	private int[] parse(String green) throws IOException {
		if (! green.startsWith("#"))
			throw new IOException("Parse error");
		
		green = green.substring(1);
		int r[] = new int[3];
		if (green.length() >= 6)
		{
			r[0] = Integer.parseInt( green.substring(0, 2) , 16);
			r[1] = Integer.parseInt( green.substring(2, 4) , 16);
			r[2] = Integer.parseInt( green.substring(4, 6) , 16);
		}
		else
		{
			r[0] = Integer.parseInt( green.substring(0, 1) , 16);
			r[1] = Integer.parseInt( green.substring(1, 2) , 16);
			r[2] = Integer.parseInt( green.substring(2, 3) , 16);
		}
		return r;
	}

	abstract protected String getResourceName();
	
}
