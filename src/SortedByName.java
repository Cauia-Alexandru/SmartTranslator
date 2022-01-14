import java.util.Comparator;

public class SortedByName implements Comparator<Word> {
    public int compare(Word w1, Word w2){
        return w1.getWord().compareToIgnoreCase(w2.getWord());
    }
}
