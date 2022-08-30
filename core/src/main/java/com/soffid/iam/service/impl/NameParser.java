package com.soffid.iam.service.impl;

import java.util.Vector;

public class NameParser {
	
	public NameParser()
	{
		
	}
	
	public String [] parse (String name, int size) {
		Vector v = new Vector ();
		String names [] = name.split("[ ,]+"); //$NON-NLS-1$
		for (int i = 0; i < names.length; i++)
			v.add(names[i]);
		
		while (v.size() > size && joinDummyWords (v)) ;
		while (v.size() > size && compressDummyWords (v)) ;
		while (v.size() > size && compressName (v)) ;
		String s [] = (String[]) v.toArray(new String [size]);
		return s;
	}

	private final String dummyWords [] = {
			"de ", "la ", "el ", "del ", "dos ", "d'", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
			"von ", //$NON-NLS-1$
			"van ", "der ", "y", "i" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	};
	
	private boolean joinDummyWords(Vector v) {
		for (int i = v.size () - 2; i >= 0; i--)
		{
			String thisWord = ( String ) v.get(i) + " "; //$NON-NLS-1$
			if (startsWithDummy (thisWord))
			{
				String nextWord = (String) v.get(i+1);
				v.set(i, thisWord + nextWord);
				v.remove(i+1);
			}
		}
		return false;
	}

	private boolean startsWithDummy(String thisWord) {
		for (int i = 0 ; i < dummyWords.length; i++)
			if (thisWord.startsWith(dummyWords[i]))
				return true;
		return false;
	}

	private boolean compressDummyWords(Vector v) {
		for (int i = v.size () - 1; i > 0; i--)
		{
			String thisWord = ( String ) v.get(i);
			if (startsWithDummy (thisWord))
			{
				String prevWord = (String) v.get(i-1);
				v.set(i-1, prevWord + " " + thisWord ); //$NON-NLS-1$
				v.remove(i);
			}
		}
		return false;
	}

	private boolean compressName(Vector v) {
		if (v.size () > 3)
		{
			String thisWord = ( String ) v.get(0) + " "; //$NON-NLS-1$
			String nextWord = (String) v.get(1);
			v.set(0, thisWord + nextWord);
			v.remove(1);
			return true;
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NameParser np = new NameParser ();
		String s [] = np.parse(args[0], 3);
		System.out.println ("GN="+s[0]); //$NON-NLS-1$
		System.out.println ("SN="+s[1]); //$NON-NLS-1$
		System.out.println ("SN2="+s[2]); //$NON-NLS-1$
	}

}
