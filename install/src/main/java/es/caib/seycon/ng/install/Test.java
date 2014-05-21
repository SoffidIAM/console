package es.caib.seycon.ng.install;

import java.util.Random;

public class Test {
    public static void main (String args[]) {
        StringBuffer pass = new StringBuffer();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 8; i++) {
            int n = r.nextInt(i == 0 ? 52: 62);
            System.out.println ("n = "+n); //$NON-NLS-1$
            if (n < 26)
                pass.append((char) ((int) 'A' + n));
            else if (n < 52)
                pass.append ((char) ((int) 'a' + (n - 26)));
            else
                pass.append ((char) ((int) '0' + (n - 52)));
            System.out.println ("pass="+pass.toString()); //$NON-NLS-1$
        }
        System.out.println ("pass="+pass.toString()); //$NON-NLS-1$
    }
    

}
