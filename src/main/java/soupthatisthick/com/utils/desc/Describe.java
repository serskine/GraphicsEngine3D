package soupthatisthick.com.utils.desc;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class Describe {

    public static <T> String array(final T... array) {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean isFirst = true;
        for(final T item : array) {
            if (!isFirst) {
                sb.append(", ");
            }
            isFirst = false;
            sb.append(Objects.toString(item));
        }
        sb.append("}");
        return sb.toString();
    }
    public static final String container(final Collection<?> collection) {
        if (collection==null) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean isFirst = true;
        for(final Object item : collection) {
            if (!isFirst) {
                sb.append(", ");
            }
            isFirst = false;

            sb.append(Objects.toString(item));
        }
        sb.append("}");
        return sb.toString();
    }

    public static final String map(final Map<?,?> map) {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean isFirst = true;
        for(final Map.Entry item : map.entrySet()) {
            if (!isFirst) {
                sb.append(", ");
            }
            isFirst = false;
            sb.append(item.toString());
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * This method is used to pretty print strings that represent json or describe containers
     * @param input the previous text
     * @return a neatly formatted text
     */
    public static String prettyPrint(final String input) {
        final StringBuilder sb = new StringBuilder();
        int tabCount = 0;
        Character quote = null;
        boolean ignoringWhitespace = false;

        for(int i=0; i<input.length(); i++) {
            final char c = input.charAt(i);

            if (quote==null) {
                if (isQuote(c)) {
                    quote = Character.valueOf(c);
                }
            } else if (c == quote.charValue()) {
                quote = null;
            }

            if (quote==null) {

                if (isOpenBrace(c)) {
                    sb.append(c);
                    sb.append('\n');
                    tabCount++;
                    sb.append(tabString(tabCount));
                } else if (isCloseBrace(c)) {
                    sb.append('\n');
                    tabCount--;
                    sb.append(tabString(tabCount));
                    sb.append(c);
                } else if (isDelim(c)) {
                    sb.append(c);
                    sb.append('\n');
                    sb.append(tabString(tabCount));
                    ignoringWhitespace = true;
                } else if (isWhitespace(c) && ignoringWhitespace) {
                    // Do nothing. Ignore the character and move on.
                } else {
                    sb.append(c);
                    ignoringWhitespace = false;
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static boolean isWhitespace(char c) {
        return Character.isWhitespace(c);
    }

    public static boolean isQuote(char c) {
        return  (c == '\"');
    }
    public static boolean isDelim(char c) {
        return (c==',');
    }

    public static String tabString(int numTabs) {
        final String tab = "  ";
        final StringBuilder sb = new StringBuilder();
        for(int i=0; i<numTabs; i++) {
            sb.append(tab);
        }
        return sb.toString();
    }

    static boolean isOpenBrace(char c) {
        return  (c == '{');
    }

    static boolean isCloseBrace(char c) {
        return  (c == '}');
    }
}
