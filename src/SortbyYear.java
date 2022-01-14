import java.util.Comparator;

public class SortbyYear implements Comparator<Definition> {
    public int compare(Definition a, Definition b){
        return a.getYear() - b.getYear();
    }
}
