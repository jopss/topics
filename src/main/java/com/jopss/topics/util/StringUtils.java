package com.jopss.topics.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String[] REPLACES = {"a", "e", "i", "o", "u", "c", "A", "E", "I", "O", "U", "C", " ", ""};
    public static Pattern[] PATTERNS = null;

    private static void compilePatterns() {
        PATTERNS = new Pattern[REPLACES.length];
        PATTERNS[0] = Pattern.compile("[âãáàä@4]");
        PATTERNS[1] = Pattern.compile("[éèêë3]");
        PATTERNS[2] = Pattern.compile("[íìîï]");
        PATTERNS[3] = Pattern.compile("[óòôõö0]");
        PATTERNS[4] = Pattern.compile("[úùûü]");
        PATTERNS[5] = Pattern.compile("[ç]");
        PATTERNS[6] = Pattern.compile("[ÂÃÁÀÄ]");
        PATTERNS[7] = Pattern.compile("[ÉÈÊË]");
        PATTERNS[8] = Pattern.compile("[ÍÌÎÏ]");
        PATTERNS[9] = Pattern.compile("[ÓÒÔÕÖ]");
        PATTERNS[10] = Pattern.compile("[ÚÙÛÜ]");
        PATTERNS[11] = Pattern.compile("[Ç]");
        PATTERNS[12] = Pattern.compile("[\\n\\r]");
        PATTERNS[13] = Pattern.compile("[ªº\\´\\`]");
    }

    /**
     * Substitui os caracteres especiais por correspondentes.
     */
    public static String replaceSpecial(String text) {
        if (PATTERNS == null) {
            compilePatterns();
        }
        
        if (text != null) {
            for (int i = 0; i < PATTERNS.length; i++) {
                Matcher matcher = PATTERNS[i].matcher(text);
                text = matcher.replaceAll(REPLACES[i]);
            }
        }

        return text;
    }

}
