package org.example.utils;

import java.util.prefs.Preferences;

public class AppPreferences {
    private final Preferences preferences;
    private final String secretKey;

    public AppPreferences() {
        secretKey = "Very_secret_key";
        preferences = Preferences.userRoot().node("org.phonebook");
    }

    public void saveWord(String word, String login) {
        preferences.put("word", CryptographyUtil.encrypt(word, secretKey, login));
    }

    public String getWord(String login) {
        if (preferences.get("word", "").isEmpty())
            return "";
        else
            return CryptographyUtil.decrypt(preferences.get("word", ""), secretKey, login);
    }

    public void removeWord() {
        preferences.remove("word");
    }

}
