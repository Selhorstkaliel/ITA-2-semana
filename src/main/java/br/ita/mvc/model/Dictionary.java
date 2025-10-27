package br.ita.mvc.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Model class responsible for managing the translation dictionary.
 * Loads and provides access to word translations from a properties file.
 */
public class Dictionary {
    private Properties translations;
    
    public Dictionary() {
        translations = new Properties();
        loadDictionary();
    }
    
    /**
     * Loads the translation dictionary from the resources folder.
     */
    private void loadDictionary() {
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("dictionary.properties");
             java.io.InputStreamReader reader = new java.io.InputStreamReader(input, "UTF-8")) {
            if (input == null) {
                System.err.println("Unable to find dictionary.properties");
                return;
            }
            translations.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Translates a word from English to Portuguese.
     * If the word is not found, returns the original word.
     * 
     * @param word The English word to translate
     * @return The Portuguese translation or the original word if not found
     */
    public String translate(String word) {
        if (word == null || word.trim().isEmpty()) {
            return word;
        }
        
        // Convert to lowercase for case-insensitive lookup
        String normalizedWord = word.trim().toLowerCase();
        String translation = translations.getProperty(normalizedWord);
        
        // If translation not found, return the original word
        return translation != null ? translation : word;
    }
}
