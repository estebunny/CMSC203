/**
 * This is a utility class that encrypts and decrypts a phrase using three
 * different approaches. 
 * 
 * The first approach is called the Vigenere Cipher.Vigenere encryption 
 * is a method of encrypting alphabetic text based on the letters of a keyword.
 * 
 * The second approach is Playfair Cipher. It encrypts two letters (a digraph) 
 * at a time instead of just one.
 * 
 * The third approach is Caesar Cipher. It is a simple replacement cypher. 
 * 
 * @author Huseyin Aygun
 * @version 8/3/2025
 */


package BobsCircus;
public class CryptoManager {

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

    private static final String ALPHABET64 =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&'()*+,-./:;<=>?@[\\]^_";

    private static final String ERROR =
            "The selected string is not in bounds, Try again.";

    // Check whether all characters belong to the 64-character alphabet.
    public static boolean isStringInBounds(String plainText) {
        if (plainText == null) {
            return false;
        }

        for (int i = 0; i < plainText.length(); i++) {
            if (ALPHABET64.indexOf(plainText.charAt(i)) == -1) {
                return false;
            }
        }

        return true;
    }

    // Vigenere Encryption
    public static String vigenereEncryption(String plainText, String key) {
        if (!isStringInBounds(plainText)
                || !isStringInBounds(key)
                || key.isEmpty()) {
            return ERROR;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            int plainIndex = ALPHABET64.indexOf(plainText.charAt(i));
            int keyIndex = ALPHABET64.indexOf(
                    key.charAt(i % key.length()));

            int encryptedIndex = (plainIndex + keyIndex) % 64;
            result.append(ALPHABET64.charAt(encryptedIndex));
        }

        return result.toString();
    }

    // Vigenere Decryption
    public static String vigenereDecryption(String encryptedText, String key) {
        if (!isStringInBounds(encryptedText)
                || !isStringInBounds(key)
                || key.isEmpty()) {
            return ERROR;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            int encryptedIndex =
                    ALPHABET64.indexOf(encryptedText.charAt(i));
            int keyIndex =
                    ALPHABET64.indexOf(key.charAt(i % key.length()));

            int plainIndex = (encryptedIndex - keyIndex + 64) % 64;
            result.append(ALPHABET64.charAt(plainIndex));
        }

        return result.toString();
    }

    // Build the 8 x 8 Playfair matrix.
    private static char[][] buildPlayfairMatrix(String key) {
        char[][] matrix = new char[8][8];
        StringBuilder characters = new StringBuilder();

        String combined = key + ALPHABET64;

        for (int i = 0; i < combined.length(); i++) {
            char current = combined.charAt(i);

            if (characters.indexOf(String.valueOf(current)) == -1) {
                characters.append(current);
            }
        }

        for (int i = 0; i < 64; i++) {
            matrix[i / 8][i % 8] = characters.charAt(i);
        }

        return matrix;
    }

    // Find the row and column of a character.
    private static int[] findPosition(char[][] matrix, char character) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (matrix[row][col] == character) {
                    return new int[] {row, col};
                }
            }
        }

        return new int[] {-1, -1};
    }

    // Process a pair using the Playfair rules.
    private static String processPlayfairPair(
            char first, char second, char[][] matrix, boolean encrypt) {

        int[] firstPosition = findPosition(matrix, first);
        int[] secondPosition = findPosition(matrix, second);

        int row1 = firstPosition[0];
        int col1 = firstPosition[1];
        int row2 = secondPosition[0];
        int col2 = secondPosition[1];

        int shift = encrypt ? 1 : 7;

        if (row1 == row2) {
            col1 = (col1 + shift) % 8;
            col2 = (col2 + shift) % 8;
        } else if (col1 == col2) {
            row1 = (row1 + shift) % 8;
            row2 = (row2 + shift) % 8;
        } else {
            int temp = col1;
            col1 = col2;
            col2 = temp;
        }

        return "" + matrix[row1][col1] + matrix[row2][col2];
    }

    // Playfair Encryption
    public static String playfairEncryption(String plainText, String key) {
        if (!isStringInBounds(plainText)
                || !isStringInBounds(key)) {
            return ERROR;
        }

        char[][] matrix = buildPlayfairMatrix(key);
        StringBuilder result = new StringBuilder();

        if (plainText.length() % 2 != 0) {
            plainText += "X";
        }

        for (int i = 0; i < plainText.length(); i += 2) {
            result.append(processPlayfairPair(
                    plainText.charAt(i),
                    plainText.charAt(i + 1),
                    matrix,
                    true));
        }

        return result.toString();
    }

    // Playfair Decryption
    public static String playfairDecryption(String encryptedText, String key) {
        if (!isStringInBounds(encryptedText)
                || !isStringInBounds(key)
                || encryptedText.length() % 2 != 0) {
            return ERROR;
        }

        char[][] matrix = buildPlayfairMatrix(key);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i += 2) {
            result.append(processPlayfairPair(
                    encryptedText.charAt(i),
                    encryptedText.charAt(i + 1),
                    matrix,
                    false));
        }

        return result.toString();
    }

    // Caesar Encryption
    public static String caesarEncryption(String plainText, int key) {
        if (!isStringInBounds(plainText)) {
            return ERROR;
        }

        StringBuilder result = new StringBuilder();
        int shift = Math.floorMod(key, 64);

        for (int i = 0; i < plainText.length(); i++) {
            int index = ALPHABET64.indexOf(plainText.charAt(i));
            int encryptedIndex = (index + shift) % 64;

            result.append(ALPHABET64.charAt(encryptedIndex));
        }

        return result.toString();
    }

    // Caesar Decryption
    public static String caesarDecryption(String encryptedText, int key) {
        if (!isStringInBounds(encryptedText)) {
            return ERROR;
        }

        StringBuilder result = new StringBuilder();
        int shift = Math.floorMod(key, 64);

        for (int i = 0; i < encryptedText.length(); i++) {
            int index = ALPHABET64.indexOf(encryptedText.charAt(i));
            int plainIndex = (index - shift + 64) % 64;

            result.append(ALPHABET64.charAt(plainIndex));
        }

        return result.toString();
    }
}
