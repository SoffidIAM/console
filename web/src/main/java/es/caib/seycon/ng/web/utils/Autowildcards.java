/**
 * 
 */
package es.caib.seycon.ng.web.utils;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Textbox;

/**
 * @author Soffid
 * 
 */
public class Autowildcards
{

	@SuppressWarnings("unchecked")
	public static void replaceAsteriskChar (Component c)
	{
		if (c instanceof Textbox)
		{
			Textbox tb = (Textbox) c;
			tb.setValue(replaceAsteriskChar(tb.getValue()));
		}
		else
		{
			for (Component c2: (List<Component>) c.getChildren())
			{
				replaceAsteriskChar(c2);
			}
		}
	}
	/**
	 * Method to replace asterisk character in string by percent sign.
	 * 
	 * @param toReplace
	 *            Strings to search asterisk character.
	 * @return Original parameters with asterisk replaced.
	 */
	public static String replaceAsteriskChar (String toReplace)
	{
		String process = null; // Processed string

		if ((toReplace == null) || toReplace.trim().length() == 0)
		{
			process = "%";
		}

		else
		{
			process = toReplace.replace("*", "%");
		}

		return process;
	}

	@SuppressWarnings("unchecked")
	public static void addPercentChar (Component c)
	{
		if (c instanceof Textbox)
		{
			Textbox tb = (Textbox) c;
			tb.setValue(addPercentChar(tb.getValue()));
		}
		else
		{
			for (Component c2: (List<Component>) c.getChildren())
			{
				addPercentChar(c2);
			}
		}
	}
	/**
	 * Method to add percent value at the start and end of string.
	 * 
	 * @param stringToProcess
	 *            String to process.
	 * @return String with percent chars added.
	 */
	public static String addPercentChar (String stringToProcess)
	{
		String processed = null; // Processed string

		// Check percent char in string
		if ((stringToProcess != null) && !stringToProcess.contains("%")
						&& !stringToProcess.isEmpty() && !stringToProcess.contains("/")
						&& !stringToProcess.contains("*"))
		{
			processed = "%" + stringToProcess.concat("%");
		}

		else
		{
			if ((stringToProcess == null) || stringToProcess.isEmpty()
							|| stringToProcess.equals("*"))
			{
				processed = "%";
			}

			else
			{
				processed = stringToProcess;
			}
		}

		return processed;
	}

	/**
	 * Method to replace asterisk character in all values of array.
	 * 
	 * @param toReplace
	 *            Array to process.
	 * @return Original array with asterisk character replaced.
	 */
	public static Map<String, Object> replaceAsteriskChar (Map<String, Object> toReplace)
	{
		Map<String, Object> dictionary = new HashMap<String, Object>(); // Processed
																		// dictionary

		// Check wildcards enabled
		if (toReplace != null)
		{
			for (String index : toReplace.keySet())
			{
				Object value = toReplace.get(index);
				if (value instanceof String)
					dictionary.put(index, replaceAsteriskChar((String) value));
			}

			return dictionary;
		}

		else
		{
			return toReplace;
		}
	}

	/**
	 * Method to add percent character in all values of array that not contains it.
	 * 
	 * @param toReplace
	 *            Array to process.
	 * @return Original array with asterisk character replaced.
	 */
	public static Map<String, Object> addPercentChar (Map<String, Object> toReplace)
	{
		Map<String, Object> dictionary = new HashMap<String, Object>(); // Processed
																		// dictionary

		// Check wildcards enabled
		if (toReplace != null)
		{
			for (String index : toReplace.keySet())
			{
				Object value = toReplace.get(index);
				if (value instanceof String)
					dictionary.put(index, addPercentChar((String) value));
			}

			return dictionary;
		}

		else
		{
			return toReplace;
		}
	}
}
