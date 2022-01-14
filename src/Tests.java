import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Tests {
    void addWordTest() {
        Methods method = new Methods();
        Methods.list();
        ArrayList<FullWord> list = Methods.foundList("ro");
        System.out.println("Cuvintele inainte de adugare:");
        for (FullWord f : list) {
            Word w = f.getCuvant();
            System.out.println(w.getWord());
        }


        ArrayList<String> singular = new ArrayList<String>();
        ArrayList<String> plural = new ArrayList<String>();
        plural.add("mese");
        singular.add("masa");

        ArrayList<Definition> definitions = new ArrayList<Definition>();
        ArrayList<String> texts = new ArrayList<String>();
        String text = "stativ";
        String text1 = "obiect cu 4 picioare";
        String text2 = "serveste pentru a pastra lucruri pe ea";
        texts.add(text);
        texts.add(text1);
        texts.add(text2);
        Definition definition = new Definition("Dicționar exp", "exp", 1996, texts);
        definitions.add(definition);

        Word word = null;
        try {
            word = new Word("masa", "table", "noun", singular, plural, definitions);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean res = method.addWord(word, "ro");                                 //primul test
        System.out.println("Cuvintele dupa adaugare:");
        for (FullWord f : list) {
            Word w = f.getCuvant();
            System.out.println(w.getWord());
        }

        res = method.addWord(word, "ro");                                                 //al doilea test
        System.out.println("Cuvintele dupa adaugare:");
        for (FullWord f : list) {
            Word w = f.getCuvant();
            System.out.println(w.getWord());                                                    //am verificat daca nu aduga de 2 ori acelasi cuvant
        }

    }

    void removeWordTest() {
        Methods.list();

        ArrayList<FullWord> list = Methods.foundList("ro");
        System.out.println("Cuvintele inainte de stergere:");
        for (FullWord f : list) {
            Word w = f.getCuvant();
            System.out.println(w.getWord());
        }
        boolean res = Methods.removeWord("pisică", "ro");
        System.out.println(res);


        // ArrayList<FullWord> list2 = Methods.foundList("ro");
        System.out.println("Cuvintele dupa stergere:");                             //test1
        for (FullWord f : list) {
            Word w = f.getCuvant();
            System.out.println(w.getWord());
        }
        boolean res1 = Methods.removeWord("pisică", "ro");
        System.out.println(res1);
        System.out.println("incerc sa sterg cuvant care nu exista");            //test2
        for (FullWord f : list) {
            Word w = f.getCuvant();
            System.out.println(w.getWord());
        }
    }

    void addDefinitionTest() {
        Methods.list();

        ArrayList<Definition> definitions = new ArrayList<Definition>();
        ArrayList<String> texts = new ArrayList<String>();
        String text = "stativ";
        String text1 = "obiect cu 4 picioare";
        String text2 = "serveste pentru a pastra lucruri pe ea";
        texts.add(text);
        texts.add(text1);
        texts.add(text2);
        Definition definition = new Definition("Dicționar exp", "exp", 1996, texts);
        ArrayList<FullWord> list = Methods.foundList("ro");
        System.out.println("Definitiile inainte de adaugare:");

        for (FullWord fullWord : list) {
            Word word = fullWord.getCuvant();
            if (word.getWord().equals("joc"))
            {
                for (Definition definition1 : word.getDefinitions()) {
                    System.out.println(definition1.getDict());
                }
                break;
            }
        }

        Methods.addDefinitionForWord("joc", "ro", definition);
        System.out.println("Dupa adaugare definitiei:");                         //1 test
        for (FullWord fullWord : list) {
            Word word = fullWord.getCuvant();
            if (word.getWord().equals("joc"))
            {
                for (Definition definition1 : word.getDefinitions()) {
                    System.out.println(definition1.getDict());
                }
                break;
            }
        }

        Methods.addDefinitionForWord("computer", "ro", definition);
        for (FullWord fullWord : list) {
            Word word = fullWord.getCuvant();
            if (word.getWord().equals("joc"))
            {
                for (Definition definition1 : word.getDefinitions()) {
                    System.out.println(definition1.getDict());                  //al doilea test(verific exceptia cand adaug definitia unui cuv neexistent)
                }
                break;
            }
        }

    }

    void removeDefinitionTest(){
        Methods.list();

        ArrayList<FullWord> list = Methods.foundList("ro");
        System.out.println("Definitiile inainte de stergere:");

        for (FullWord fullWord : list) {
            Word word = fullWord.getCuvant();
            if (word.getWord().equals("merge"))
            {
                for (Definition definition1 : word.getDefinitions()) {
                    System.out.println(definition1.getDict());
                }
                break;
            }
        }
        Methods.removeDefinition("merge", "ro", "Micul dicționar academic, ediția a II-a");

        System.out.println("Definitiile dupa stergere:");               //1 test

        for (FullWord fullWord : list) {
            Word word = fullWord.getCuvant();
            if (word.getWord().equals("merge"))
            {
                for (Definition definition1 : word.getDefinitions()) {
                    System.out.println(definition1.getDict());
                }
                break;
            }
        }
        Methods.removeDefinition("merge", "ro", "Micul dicționar");
        System.out.println("Definitiile dupa stergere:");

        for (FullWord fullWord : list) {
            Word word = fullWord.getCuvant();
            if (word.getWord().equals("merge"))
            {
                for (Definition definition1 : word.getDefinitions()) {
                    System.out.println(definition1.getDict());              //2 test, daca sterg o definitia care nu exista
                }
                break;
            }
        }

    }

    void translateWordTest(){
        Methods.list();
        String res = Methods.translateWord("pisică", "ro", "fr");
        String res1 = Methods.translateWord("pisică", "ro", "rus");
        System.out.println(res1);
        System.out.println(res);
    }

    void translateSentenceTest(){
        Methods.list();
        String res = Methods.translateSentence("pisică joc", "ro", "fr");
        String res1 = Methods.translateSentence("pisică joc merge", "ro", "fr");
        System.out.println(res1);
        System.out.println(res);
    }

    void translateSentencesTest(){
        Methods.list();
        ArrayList<String> sentences = Methods.translateSentences("pisică joc", "ro", "fr");
        ArrayList<String> sentences1 = Methods.translateSentences("pisică joc", "ro", "ffffr");

        for(String s : sentences){
            System.out.println(s);
        }
        if (sentences1 != null) {
            for(String s : sentences1){
                System.out.println(s);
            }
        } else System.out.println("Nu s-a gasit limba");

    }

    void getDefinitionsForWordTest(){
        Methods.list();
        ArrayList<Definition> listDefintion = Methods.getDefinitionsForWord("câine", "ro");
        for(Definition definition : listDefintion){
            System.out.println(definition.getYear());
        }

        ArrayList<Definition> listDefintion1 = Methods.getDefinitionsForWord("mouse", "ro");
        if(listDefintion1 != null) {
            for (Definition definition : listDefintion1) {
                System.out.println(definition.getYear());
            }
        }
        if(listDefintion1.size() == 0)
            System.out.println("Acest cuvant nu are definitii");

    }



    public static void main(String[] args) {
        Tests test = new Tests();
        //test.addWordTest();
        //test.removeWordTest();
        //test.addDefinitionTest();
        //test.removeDefinitionTest();
        //test.translateWordTest();
        //test.translateSentenceTest();
        //test.translateSentencesTest();
        test.getDefinitionsForWordTest();
    }
}
