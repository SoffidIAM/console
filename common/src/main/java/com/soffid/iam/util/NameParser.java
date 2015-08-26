package com.soffid.iam.util;

import java.util.Vector;

public class NameParser {

    int maxMembers;
    
    public NameParser() {

    }

    public String[] parse(String name, int size) {

        maxMembers = size;
        String name2 = normalizeName(name);

        Vector v = new Vector();
        String names[] = name2.split(" +"); //$NON-NLS-1$
        for (int i = 0; i < names.length; i++)
            v.add(names[i]);

        while (v.size() > size && joinDummyWords(v))
            ;
        while (v.size() > size && compressDummyWords(v))
            ;
        while (v.size() > size && compressName(v))
            ;
        while (v.size() < size)
            v.add(""); //$NON-NLS-1$
        String s[] = (String[]) v.toArray(new String[size]);
        return s;
    }

    public String normalizeName(String name) {
	StringBuffer b = new StringBuffer();
	String chars = name.toUpperCase();
	for (int i = 0; i < chars.length(); i++)
	{
	    char ch = chars.charAt(i);
	    switch (ch) {
	    case 'Á':
	    case 'À':
	    case 'Â':
	        ch = 'A';
	        break;
	    case 'É':
	    case 'È':
	    case 'Ê':
	        ch = 'E';
	        break;
            case 'Í':
            case 'Ì':
            case 'Ï':
            case 'Î':
                ch = 'I';
                break;
            case 'Ò':
            case 'Ó':
            case 'Ö':
                ch = 'O';
                break;
            case 'Ú':
            case 'Ù':
            case 'Ü':
                ch = 'U';
                break;
            case '·':
                ch = '.';
                
	    }
	    b.append(ch);
	}
	return b.toString();
    }

    private final String dummyWords[] = { "DE ", "LA ", "EL ", "DEL ", "DOS ", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            "D'", "VON ", "VAN ", "DER ", "Y ", "I " }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$

    private boolean joinDummyWords(Vector v) {
        for (int i = v.size() - 2; i >= 0; i--) {
            String thisWord = (String) v.get(i) + " "; //$NON-NLS-1$
            if (startsWithDummy(thisWord)) {
                String nextWord = (String) v.get(i + 1);
                v.set(i, thisWord + nextWord);
                v.remove(i + 1);
            }
        }
        return false;
    }

    private boolean startsWithDummy(String thisWord) {
        for (int i = 0; i < dummyWords.length; i++)
            if (thisWord.startsWith(dummyWords[i]))
                return true;
        return false;
    }

    private boolean compressDummyWords(Vector v) {
        for (int i = v.size() - 1; i > 0; i--) {
            String thisWord = (String) v.get(i);
            if (startsWithDummy(thisWord+" ")) { //$NON-NLS-1$
                String prevWord = (String) v.get(i - 1);
                v.set(i - 1, prevWord + " " + thisWord); //$NON-NLS-1$
                v.remove(i);
            }
        }
        return false;
    }

    private boolean compressName(Vector v) {
        if (v.size() > maxMembers) {
            String thisWord = (String) v.get(0) + " "; //$NON-NLS-1$
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
        NameParser np = new NameParser();
        String s[] = np.parse(args[0], 3);
        System.out.println("GN=" + s[0]); //$NON-NLS-1$
        System.out.println("SN=" + s[1]); //$NON-NLS-1$
        System.out.println("SN2=" + s[2]); //$NON-NLS-1$
    }

}
