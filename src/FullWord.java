import java.util.ArrayList;

public class FullWord {
    private String language;
    private Word cuvant;

    public FullWord(String language, Word cuvant) {
        this.language = language;
        this.cuvant = cuvant;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Word getCuvant() {
        return cuvant;
    }

    public void setCuvant(Word cuvant) {
        this.cuvant = cuvant;
    }
}
