import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Word {
    private String word;
    private String word_en;
    private String type;
    private ArrayList<String> singular;
    private ArrayList<String> plural;
    private ArrayList<Definition> definitions;

    public Word(String word, String word_en, String type, ArrayList<String> singular, ArrayList<String> plural, ArrayList<Definition> definitions) throws FileNotFoundException {
        this.word = word;
        this.word_en = word_en;
        this.type = type;
        this.singular = singular;
        this.plural = plural;
        this.definitions = definitions;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord_en() {
        return word_en;
    }

    public void setWord_en(String word_en) {
        this.word_en = word_en;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getSingular() {
        return singular;
    }

    public void setSingular(ArrayList<String> singular) {
        this.singular = singular;
    }

    public ArrayList<String> getPlural() {
        return plural;
    }

    public void setPlural(ArrayList<String> plural) {
        this.plural = plural;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<Definition> definitions) {
        this.definitions = definitions;
    }


}
