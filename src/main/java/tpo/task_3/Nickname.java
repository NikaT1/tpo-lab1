package tpo.task_3;

public class Nickname extends Name {

    private String meaning;
    private final String nickname;
    public Nickname(String name, Language language, String nickname, String meaning) {
        super(name, language);
        this.nickname = nickname;
        this.meaning = meaning;
    }

    public Nickname(String name, Language language, String nickname) {
        super(name, language);
        this.nickname = nickname;
        this.meaning = null;
    }
    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getNickname() {
        return nickname;
    }
}
