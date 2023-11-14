package org.example.utils;

import java.util.prefs.Preferences;

public class AppPreferences {
    private Preferences preferences;

    public AppPreferences() {
        preferences = Preferences.userRoot().node("org.phonebook");
    }

    public void saveWord(String word) {
        preferences.put("word", word);
    }

    public String getWord() {
        return preferences.get("word", "");
    }

    public void removeWord() {
        preferences.remove("word");
    }

}
