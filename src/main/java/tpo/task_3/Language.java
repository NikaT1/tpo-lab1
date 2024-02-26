package tpo.task_3;

public class Language {
    private String name;
    private int complexity; // from 0 to 10

    public Language(String name, int complexity) throws Exception {
        if (complexity > 10 || complexity < 0) {
            throw new Exception("Сложность языка должна быть в пределах от 0 до 10!");
        }
        this.name = name;
        this.complexity = complexity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) throws Exception {
        if (complexity > 10 || complexity < 0) {
            throw new Exception("Сложность языка должна быть в пределах от 0 до 10!");
        }
        this.complexity = complexity;
    }
}
