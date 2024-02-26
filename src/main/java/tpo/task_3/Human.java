package tpo.task_3;

import java.util.Date;

public class Human extends Entity {

    public Human(Name name, Date birthDate) {
        super(name, birthDate);
    }

    public String sayName() throws Exception {
        if (super.getName().getComplexity() > 8) {
            throw new Exception("Имя не может быть произнесено, оно слишком сложное!");
        }
        return super.getName().getName();
    }
}
