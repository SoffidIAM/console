package com.soffid.iam.web.laf;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soffid.iam.utils.ConfigurationCache;

@WebServlet(name="custom.svg", urlPatterns="*.svg")
public class SvgFilter extends HttpServlet {
	StringBuffer getText (String resource) throws IOException {
		InputStream in = getServletContext().getResourceAsStream( resource );
		if (in == null)
			return null;
		StringBuffer sb = new StringBuffer();
		Reader r = new InputStreamReader(in);
		for (int i = r.read(); i >= 0; i = r.read())
		{
			sb.append((char) i);
		}
		in.close();
		return sb;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean original = false;
		String green = ConfigurationCache.getProperty("soffid.ui.color1");
		if (green == null) green = StandardColors.originalGreen;
		
		String blue = ConfigurationCache.getProperty("soffid.ui.color2");
		if (blue == null) blue = StandardColors.originalBlue;

		
		String sky = ConfigurationCache.getProperty("soffid.ui.color3");
		if (sky == null) sky = StandardColors.originalSky;

		String originalWhite = "#ffffff";
		String white = "#ffffff";
		String uri = req.getServletPath();
//		uri = req.getPathInfo();
		
		String originalBlue = StandardColors.originalBlue;
		if (uri.endsWith("-orig.svg")) {
			original = true;
			uri = uri.substring(0, uri.length()-9)+".svg";
		} else if (uri.endsWith("-r.svg")) {
			uri = uri.substring(0, uri.length()-6)+".svg";
			originalBlue = "#ffffff";
			blue = sky;
			sky = ConfigurationCache.getProperty("soffid.ui.text3");
			if (sky == null) sky = StandardColors.originalSky;
		}
		else if (uri.endsWith("-black.svg")) {
			uri = uri.substring(0, uri.length()-10)+".svg";
			sky = "#101010";
			blue = "#101010";
			green = "#101010";
		}
		else if (uri.endsWith("-white.svg")) {
			white = sky ;
			uri = uri.substring(0, uri.length()-10)+".svg";
			sky = "#ffffff";
			blue = "#ffffff";
			green = "#ffffff";
		}
		else if (uri.endsWith("-green.svg")) {
			uri = uri.substring(0, uri.length()-10)+".svg";
			sky = green;
		}
		StringBuffer t = getText( uri );
		
		if (t == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			if ( ! original ) {
				Color originalGreenRgb = toColor ( StandardColors.originalGreen);
				Color originalBlueRgb = toColor ( originalBlue);
				Color originalSkyRgb = toColor ( StandardColors.originalSky);
				Color newGreenRgb = toColor ( green);
				Color newBlueRgb = toColor ( blue);
				Color newSkyRgb = toColor ( sky);
				Color black = toColor ( "#000000" );
				Color newBlack = toColor ( "#606060" );
				
				HSLColor originalGreenHsl = new HSLColor(originalGreenRgb);
				HSLColor originalBlueHsl = new HSLColor(originalBlueRgb);
				HSLColor originalSkyHsl = new HSLColor(originalSkyRgb);
				HSLColor newGreenHsl = new HSLColor(newGreenRgb);
				HSLColor newBlueHsl = new HSLColor(newBlueRgb);
				HSLColor newSkyHsl = new HSLColor(newSkyRgb);
				
				HSLColor blackHsl = new HSLColor(black);
				HSLColor newBlackHsl = new HSLColor(newBlack);
				
				for (int pos = t.indexOf("#"); pos >= 0; pos = t.indexOf("#", pos+1))
				{
					if ( pos < t.length() - 6)
					{
						String color = t.substring(pos+1, pos+7);
						if (isHex(color))
						{
							Color c = toColor("#"+color);
							HSLColor hslColor = new HSLColor(c);
							if ( sameColor (originalGreenHsl, hslColor ))
								replaceColor (t, pos, originalGreenHsl, newGreenHsl, hslColor);
							else if ( sameColor (originalBlueHsl, hslColor ))
								replaceColor (t, pos, originalBlueHsl, newBlueHsl, hslColor);
							else if ( sameColor (originalSkyHsl, hslColor ))
								replaceColor (t, pos, originalSkyHsl, newSkyHsl, hslColor);
							else if ( color.equals(originalWhite.substring(1)))
								t.replace(pos, pos+7, white);
						}
					}
				}
				
			}
			byte[] b = t.toString().getBytes("UTF-8");
			
			resp.setHeader("Cache-Control", "public, max-age=120");
			resp.setDateHeader("Expires", System.currentTimeMillis()+120000);
			resp.setDateHeader("Last-Modified", System.currentTimeMillis()-1200000);
			resp.setStatus(200);
			resp.setContentLength(b.length);
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("image/svg+xml");
			ServletOutputStream outputStream = resp.getOutputStream();
			outputStream.write(b);
			outputStream.close();
		}
	}

	private void replaceColor(StringBuffer t, int pos, HSLColor original, HSLColor newColor, HSLColor current) {
		HSLColor c;
		try {
			if (original.getLuminance() == current.getLuminance()) 
				c = newColor;
			else if (original.getLuminance() == 0.0)
				return; // Do not divide by 0
			else {
				float luminance = newColor.getLuminance() / original.getLuminance() * current.getLuminance();
				c = new HSLColor( newColor.getHue(), newColor.getSaturation(),
						luminance > 100.0f ? 100.0f: luminance);
			}
			
		} catch (IllegalArgumentException e) {
			c = newColor;
		}
		Color rgb = c.getRGB();
		String hex = String.format("%02x%02x%02x", rgb.getRed(), rgb.getGreen(), rgb.getBlue());
		
		
		t.replace(pos+1, pos+7, hex);
	}

	private boolean sameColor(HSLColor original, HSLColor color) {
		if ( Math.abs( original.getHue() - color.getHue() ) < 4.0 &&
				Math.abs( original.getSaturation() - color.getSaturation() ) < 4.0 )
			return true;
		else
			return false;
	}

	private boolean isHex(String color) {
		for ( char ch: color.toCharArray())
		{
			if (ch >= '0' && ch <= '9') continue;
			if (ch >= 'A' && ch <= 'F') continue;
			if (ch >= 'a' && ch <= 'f') continue;
			return false;
		}
		return true;
	}

	private Color toColor(String color) {
		int[] r = new int[3];
		
		r[1] = Integer.parseInt(color.substring(3, 5), 16);
		r[0] = Integer.parseInt(color.substring(1, 3), 16);
		r[2] = Integer.parseInt(color.substring(5, 7), 16);
		return new Color(r[0], r[1], r[2]);
	}

	
}
