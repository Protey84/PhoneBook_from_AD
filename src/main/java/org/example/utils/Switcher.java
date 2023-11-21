package org.example.utils;

import java.util.Arrays;
import java.util.HashSet;

public class Switcher{
    static String ruLetters="ёйцукенгшщзхъфывапролджэячсмитьбю";
    static String enLetters="`qwertyuiop[]asdfghjkl;'zxcvbnm,.";

    static String[] ru = (ruLetters+ruLetters.toUpperCase()).split("");
    static String[] en = (enLetters+enLetters.toUpperCase()).split("");
    static HashSet<String> ruSet=new HashSet<>();
    static HashSet<String> enSet=new HashSet<>();
    static {
        ruSet.addAll(Arrays.asList(ru));
        enSet.addAll(Arrays.asList(en));
    }

    private Switcher() {
    }

    public static String switchToDifferentLayout(String message) {
        if (message==null)
            return "";
        String[] symbolsMessage=message.split("");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (ruSet.contains(symbolsMessage[i])){
                builder.append(switchChar(symbolsMessage[i], ru, en));
            } else if (enSet.contains(symbolsMessage[i])&&Character.isLetter(symbolsMessage[i].charAt(0))) {
                builder.append(switchChar(symbolsMessage[i], en, ru));
            }
            else
             builder.append(symbolsMessage[i]);
        }
        return builder.toString();
    }
    static String switchChar(String  ch, String[] from, String[] to){
        for (int i = 0; i < from.length; i++) {
            if (ch.equals(from[i])){
                return to[i];
            }
        }
        return ch;
    }
}