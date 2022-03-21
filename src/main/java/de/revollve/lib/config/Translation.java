package de.revollve.lib.config;

import org.ini4j.Wini;

/**
 * <h1>Translation.java</h1>
 *
 * To use create a new object of this and use the <code>#get()</code> method to retrieve the actual string that should be represented.
 * You have to create a <code>lang.ini</code> file in your resource folder or you specify a path for the translations in the second constructor.
 * All constructors take a string which is used to find the translation. It is the address for the translation.
 * <br><br>
 * Use it like
 * <br>
 * <code>identifier=actual translation</code>
 * <br><br>
 * Open a new section with <code>[language]</code> to write your identifier in.
 * <br><br>
 * @apiNote  Wont work without the org.ini4j api.
 * @see org.ini4j.Wini
 * @version 1.1
 * @author ReVollve
 */
public class Translation {

    private String identifier;
    private Wini privateIni = new Wini();
    private boolean isUsingCustomLangFile = false;
    private String customPath;
    private static Wini ini = new Wini();
    private boolean missing_file = false;
    private boolean missing_trans = false;

    /**
     * Set true to enable exception logging
     */
    public static boolean log_err = false;
    /**
     * This can be anything, it refers to the section name, where your translation stands.
     */
    public static String language = "english";
    /**
     * Message to display when a lang file is missing.
     */
    public static String msg_missing_file = "[missing lang file]";
    /**
     * Message to display when a translation is missing.
     */
    public static String msg_missing_translation = "[missing translation]";

    /**
     * Constructor which uses the standard path to the language file
     * @param identifier Name to find the translation
     * @since 1.0
     */
    public Translation(String identifier) {
        this.identifier = identifier;
        try {
            ini.load(Translation.class.getResourceAsStream("/lang.ini"));
        } catch (Exception e) {
            if(log_err) e.printStackTrace();
            missing_file = true;
        }
        update();
    }

    /**
     * Constructor which uses a custom path to the language file
     * @param identifier Name to find the translation
     * @param path Path for the file
     * @since 1.0
     */
    public Translation(String identifier, String path) {
        try {
            privateIni.load(Translation.class.getResourceAsStream(path));
        } catch (Exception e) {
            if(log_err) e.printStackTrace();
            missing_file = true;
            return;
        }

        isUsingCustomLangFile = true;
        this.customPath = path;
        this.identifier = identifier;
        update();
    }

    /**
     * Updates the config files
     * @since 1.1
     */
    public void update() {
        if(!isUsingCustomLangFile) {
            try {
                ini.load(Translation.class.getResourceAsStream("/lang.ini"));
                missing_file = false;
            } catch (Exception e) {
                if(log_err) e.printStackTrace();
                missing_file = true;
            }
        } else {
            try {
                privateIni.load(Translation.class.getResourceAsStream(customPath));
                missing_file = false;
            } catch (Exception e) {
                if(log_err) e.printStackTrace();
                missing_file = true;
            }
        }
        String result = null;
        try {
            if(!isUsingCustomLangFile) result = ini.get(language, identifier);
            else result = privateIni.get(language, identifier);
        } catch (Exception e) {
            if(log_err) e.printStackTrace();
        }

        missing_trans = result == null;
    }

    /**
     * Gets the actual result of the whole operation.
     * @return Translation
     * @since 1.0
     */
    public String get() {
        if(missing_file) return msg_missing_file;
        if(missing_trans) return msg_missing_translation;
        if(isUsingCustomLangFile) return privateIni.get(language, this.identifier);
        return ini.get(language, this.identifier);
    }

    /**
     * Changes language
     * @since 1.1
     */
    public static void setlanguage(String lang) {
        language = lang;
    }
}
