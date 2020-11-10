package Beans;

public class WordBean {
    private String language;
    private String word;
    private String description;

    public WordBean() {
    }

    public WordBean(String language, String word, String description) {
        this.language = language;
        this.word = word;
        this.description = description;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription() {
        this.description = description;
    }
}
