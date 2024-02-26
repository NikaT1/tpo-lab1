package tpo.task_3;

import java.util.ArrayList;
public class TableOfNicknames {

    private NicknameFromPlaces place;
    private final ArrayList<Nickname> nicknameArrayList;

    public TableOfNicknames(NicknameFromPlaces place) {
        nicknameArrayList = new ArrayList<>();
        this.place = place;
    }

    public NicknameFromPlaces getPlace() {
        return place;
    }

    public void setPlace(NicknameFromPlaces place) {
        this.place = place;
    }

    public ArrayList<Nickname> getNicknameArrayList() {
        return nicknameArrayList;
    }

    public void addNicknameToArrayList(Nickname nickname) {
        this.nicknameArrayList.add(nickname);
    }

    public String[] getAllNicknamesOfEntity(Entity entity) {
        return nicknameArrayList.stream().filter(x -> x.getName().equals(entity.getName().getName())).map(Nickname::getNickname).toArray(String[]::new);
    }
}
