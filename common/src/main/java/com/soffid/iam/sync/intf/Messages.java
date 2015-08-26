package com.soffid.iam.sync.intf;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    private static final String BUNDLE_NAME = "es.caib.seycon.ng.sync.intf.messages"; //$NON-NLS-1$

    private Messages() {
    }

    public static String getString(String key) {
        try {
            return com.soffid.iam.lang.MessageFactory.getString(BUNDLE_NAME, key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
