package es.caib.seycon.ng.comu;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    private static final String BUNDLE_NAME = "es.caib.seycon.ng.comu.messages"; //$NON-NLS-1$

    private Messages() {
    }

    public static String getString(String key) {
        try {
            return es.caib.seycon.ng.comu.lang.MessageFactory.getString(BUNDLE_NAME, key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}