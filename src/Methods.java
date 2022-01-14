import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Methods {
    static ArrayList<ArrayList<FullWord>> aList = null;


    public static ArrayList<Word> readJson(String JsonFile) {
        ArrayList<Word> continut = null;
        Gson gson = new Gson();
        try {
            Type foundListType = new TypeToken<ArrayList<Word>>() {
            }.getType();
            continut = gson.fromJson(new FileReader(JsonFile), foundListType);

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return continut;
    }

    public static ArrayList<ArrayList<FullWord>> list() {
        ArrayList<ArrayList<FullWord>> aList = new ArrayList<ArrayList<FullWord>>();
        String folderPath = "C:\\Users\\AleX_MasTer\\OneDrive\\Desktop\\tema2_poo\\Tema_2\\src\\JSON_files";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            ArrayList<Word> jsonFile = readJson("src/JSON_files/" + file.getName());                    //citesc fisierele json si le stochez intr-un Arraylist
            ArrayList<FullWord> fullWords = new ArrayList<FullWord>();
            for(Word el : jsonFile){
                String limba = file.getName().substring(0, 2);                         //stochez primele 2 litere din numele fisierului, care reprezinta limba
                FullWord element = new FullWord(limba, el);                             //obiectele Word le transform in Fullword
                fullWords.add(element);

            }
            aList.add(fullWords);
        }
        Methods.aList = aList;
        return Methods.aList;
    }

    public static ArrayList<FullWord> foundList(String language){

        ArrayList<FullWord> foundList = new ArrayList<FullWord>();
        for(ArrayList<FullWord> list : Methods.aList) {
            if (list.get(0).getLanguage().equals(language)) {                           //extrag dictionarul corespunztorii limbii primite ca parametru
                foundList = list;
                break;
            }
        }
        return foundList;
    }

    public boolean addWord(Word word, String language) {

        ArrayList<FullWord> foundList = foundList(language);                //stochez dictionarul unde trebuie sa adaug cuvantul
        boolean existWord = false;
        Word wordF = null;

        for (FullWord word1 : foundList) {
            Word wordDict = word1.getCuvant();

            if (wordDict.getWord().equals(word.getWord())) {            //caut cuvantul primit ca parametru in dictionar
                wordF = wordDict;                                       //daca il gasesc il stochez intr-o variabila ca sa pot verifica celelalte campuri
                break;
            }

        }
        if(wordF == null){
            FullWord newWord = new FullWord(language, word);
            foundList.add(newWord);                                     //daca nu exista in dictionar il adaug
            existWord = true;

        } else {
            boolean new1 = true;
            for (String singular1 : word.getSingular()) {              //parcurg formele de singular a cuv primit ca parametru
                new1 = true;
                for (String singular2 : wordF.getSingular()) {          //parcurg singularele cuv care exista in dictionar
                    if ((singular1.equals(singular2))) {
                        new1 = false;
                        break;
                    }

                }
                if (new1) {                                              //daca am gasit singulare in cuv word care nu exista in wordF
                    wordF.getSingular().add(singular1);                 //le adaug
                    existWord = true;
                }
            }

            for (String plural1 : word.getPlural()) {
                new1 = true;
                for (String plural2 : wordF.getPlural()) {
                    if ((plural1.equals(plural2))) {
                        new1 = false;
                        break;
                    }

                }
                if (new1) {
                    wordF.getPlural().add(plural1);                    //adaug formele noi de plural
                    existWord = true;
                }
            }

            boolean new2 = true;
            for (Definition definition : word.getDefinitions()) {               //parcurg definitiile lui word
                new2 = true;
                Definition def = null;
                for (Definition definition1 : wordF.getDefinitions()) {         //parcurg definitiile lui wordF
                    if (!(definition.getDict().equals(definition1.getDict()))) {
                        new2 = false;
                    }
                    else {
                        def = definition1;                                      //daca gasesc dict egal in ambele word-uri, stochez pentru a verifica textul
                        break;
                    }
                }
                if (!new2) {
                    wordF.getDefinitions().add(definition);                     //adaug definitia daca nu am gasit dict in definitia lui wordF
                    existWord = true;
                } else {
                    boolean new3 = true;
                    for (String text : definition.getText()) {
                        new3 = true;
                        for (String text1 : def.getText()) {
                            if ((text.equals(text1)))
                                new3 = false;
                        }
                        if (new3) {
                            def.getText().add(text);                    //adaug text nou in Arraylist<text>
                            existWord = true;
                        }
                    }
                }
            }
        }
        return existWord;
    }


    public static boolean removeWord(String word, String language){

        ArrayList<FullWord> foundList = foundList(language);
        for(FullWord word1 : foundList) {                            //parcurg dictionarul
            Word wordDict = word1.getCuvant();

            if(wordDict.getWord().equals(word)) {                    //daca gasesc word
                foundList.remove(word1);                                //il sterg
                return true;
                }
            }
        return false;
    }

    public static boolean addDefinitionForWord(String word, String language, Definition definition){
        ArrayList<FullWord> foundList = foundList(language);
        boolean succes = true;
        for(FullWord word1 : foundList) {
            Word wordDict = word1.getCuvant();

            if (wordDict.getWord().equals(word)) {                           //gasesc pe word in dictionar
                for(Definition definition1 : wordDict.getDefinitions()){     //parcurg definitiile word-ului
                    if(definition1.getDict().equals(definition.getDict())){  // si verific daca definitia primita ca parametru exista deja
                        succes = false;
                    }
                }
                if(succes) {
                    wordDict.getDefinitions().add(definition);               //daca nu, o adaug
                    succes = true;
                }
            }
        }
        return succes;
    }

    public static boolean removeDefinition(String word, String language, String dictionary){
        ArrayList<FullWord> foundList = foundList(language);
        boolean succes = false;
        for(FullWord word1 : foundList) {
            Word wordDict = word1.getCuvant();

            if (wordDict.getWord().equals(word)) {
                for(Definition definition1 : wordDict.getDefinitions()){
                    if(definition1.getDict().equals(dictionary)){                //daca exista deja definitia unui dict primit ca parametru
                        wordDict.getDefinitions().remove(definition1);           //o sterg din arraylist de definitii
                        succes = true;
                        break;
                    }
                }
            }
            if(succes)
                break;
        }
        return succes;                                                              //daca nu exista, returnez false
    }



    public static String translateWord(String word, String fromLanguage, String toLanguage){
        ArrayList<FullWord> foundList = new ArrayList<FullWord>();
        ArrayList<FullWord> translateList = new ArrayList<FullWord>();
        foundList = foundList(fromLanguage);
        translateList = foundList(toLanguage);
        String fromLangWord = new String();
        String wordTranslate = new String();                 //obtin dictionarul ambelor limbi

        for(FullWord word1 : foundList) {
            Word wordDict = word1.getCuvant();
            if(wordDict.getWord().equals(word)){            //cand gasesc cuvantul in dictionar stochez traducerea lui in engleza
                fromLangWord = wordDict.getWord_en();
                break;
            }
        }
        for(FullWord fullWord : translateList){
            Word translateWord = fullWord.getCuvant();
            if(translateWord.getWord_en().equals(fromLangWord)){        //daca gasesc in dictionar cuvatul fromLangWord
                wordTranslate = translateWord.getWord();                //stochez cuvantul in limba cautata
                break;
            }
            else{
                wordTranslate = word;
            }
        }

        return wordTranslate;                                           //il returnez
    }


    public static  String translateSentence(String sentence, String fromLanguage, String toLanguage){
        String translateToken = new String();
        String translateText = new String();
        ArrayList<String> translateSent = new ArrayList<String>();

        String[] splitStentence = sentence.split(" ");                  //despart cuvintele din propozitie si le stochez in array de string-uri
        for(String token : splitStentence){
            translateToken = translateWord(token, fromLanguage, toLanguage);  //traduc fiecare cuvant cu ajutorul metodei translateWord
            translateSent.add(translateToken);                                  //adaug cuvintele intr-un arraylist
        }
        translateText = translateText.join(" ", translateSent);             //convertesc arraylist-ul intr-un string

        return translateText;
    }


    public static ArrayList<String> translateSentences(String sentence, String fromLanguage, String toLanguage){
        ArrayList<FullWord> List = new ArrayList<FullWord>();
        ArrayList<FullWord> translateList = new ArrayList<FullWord>();
        List = foundList(fromLanguage);
        translateList = foundList(toLanguage);
        if(translateList.size() == 0)
            return null;
        String firstMode =  translateSentence(sentence, fromLanguage, toLanguage);
        ArrayList<String> restOfWords = new ArrayList<String>();
        String thirdMode = null;
        String secondMode = null;
        String translateToken = new String();

        boolean goodWord = false;
        String[] splitStentence = sentence.split(" ");
        for(String token : splitStentence){
            goodWord = false;
            translateToken = translateWord(token, fromLanguage, toLanguage);            //cuvantul tradus
            for(FullWord fullWord : translateList){
                Word word = fullWord.getCuvant();
                if(translateToken.equals(word.getWord())){                              //il gasesc in lista
                    for(Definition definition : word.getDefinitions()){
                        if(definition.getDictType().equals("synonyms")){                //caut prin definitiile dictionarului de sinonime
                            for(int i = 0; i < 2; i++){
                                if(definition.getText().size() > i){
                                    restOfWords.add(definition.getText().get(i));       //iau 2 sinonime si le pun in arraylist
                                    goodWord = true;
                                }
                                else {
                                    restOfWords.add(word.getWord());                    //daca nu sunt 2 sinonime, pun cuvantul propriu-zis
                                    goodWord = true;
                                }
                            }
                        }
                    }
                }
            }
            if (!goodWord) {
                restOfWords.add(translateToken);
                restOfWords.add(translateToken);
            }
        }
        for(int j = 0; j < restOfWords.size(); j += 2){                     //parcurg array-ul si pun intr-un string sinonimele pentru a 2 mod de traducere
            if (secondMode == null)
                secondMode = restOfWords.get(j) + " ";
            else
                secondMode += restOfWords.get(j) + " ";
        }
        for(int k = 1; k < restOfWords.size(); k += 2){                 //sar din 2 in 2 incepand de pe indexul 1 si pun sinonimele pentru al 3lea mod
            if (thirdMode == null)
                thirdMode = restOfWords.get(k) + " ";
            else
                thirdMode += restOfWords.get(k) + " ";
        }
        ArrayList<String> result = new ArrayList<>();
        result.add(firstMode);                                          //afisez traducerile
        result.add(secondMode);
        result.add(thirdMode);
        return result;
    }

    public static ArrayList<Definition> getDefinitionsForWord(String word, String language){
        ArrayList<FullWord> list = foundList(language);
        ArrayList<Definition> sortedDefinitions = new ArrayList<Definition>();
        for(FullWord word1 : list) {
            Word wordDict = word1.getCuvant();
            if (wordDict.getWord().equals(word)) {
                sortedDefinitions = wordDict.getDefinitions();              //accesez definitiile word-ului
            }
        }
        Collections.sort(sortedDefinitions, new SortbyYear());              //le sortez crescator dupa an cu ajutorul unei clase aparte
        return sortedDefinitions;
    }

    public static ArrayList<Word> sortDictionary(String language){
        ArrayList<FullWord> list = foundList(language);
        ArrayList<Word> wordList = new ArrayList<Word>();
        for(FullWord fullWord : list){
            Word word = fullWord.getCuvant();
            wordList.add(word);
        }
        Collections.sort(wordList, new SortedByName());                     //sortez cuvintele intr-un dictionar alfabetic
        return wordList;
    }

    public static void exportDictionary(String language) throws IOException {
        ArrayList<FullWord> dictionary = foundList(language);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = Files.newBufferedWriter(Paths.get("output.json"));  //creez un file
        ArrayList<Word> wordList = new ArrayList<Word>();
        for(FullWord fullWord : dictionary){
            Word word = fullWord.getCuvant();                                   //stochez intr-un arraylist cuvintele(fara campul "language")
            wordList.add(word);
        }
        ArrayList<Definition> sortDefinition = new ArrayList<Definition>();
        wordList = sortDictionary(language);                                    //sortez cuvintele in ordine alfabetica
        for(Word word : wordList){
            sortDefinition = getDefinitionsForWord(word.getWord(), language);   //pentru fiecare cuvant sortez definitiile dupa "year"
            word.setDefinitions(sortDefinition);
        }

        gson.toJson(wordList, writer);

        writer.close();

    }

}
