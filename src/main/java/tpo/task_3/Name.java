package tpo.task_3;

public class Name {
    private String name;
    private Language language;
    private int complexity;  // from 0 to 10

    public Name(String name, Language language, int complexity) throws Exception {
        this.name = name;
        this.language = language;
        this.complexity = correctComplexityOfName(complexity, language);
    }

    public Name(String name, Language language) {
        this.name = name;
        this.language = language;
        this.complexity = 5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) throws Exception {
        this.complexity = correctComplexityOfName(complexity, this.language);
    }

    private int correctComplexityOfName(int complexity, Language language) throws Exception {
        if (complexity > 10 || complexity < 0) {
            throw new Exception("Сложность имени должна быть в пределах от 0 до 10!");
        }
        if (language.getComplexity() > 5) {
            return Math.min((complexity + 3), 10);
        }
        return complexity;
    }
}
