package tpo.task_3;

import java.util.ArrayList;
import java.util.Date;

public class Entity {

    private HealthStatus healthStatus;
    private Name name;
    private final ArrayList<Entity> parents;
    private final ArrayList<Entity> children;
    private Date birthDate;
    private Date deathDate;

    public Entity(Name name, Date birthDate) {
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = null;
        healthStatus = HealthStatus.HEALTHFUL;
    }

    public Name getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public ArrayList<Entity> getParents() {
        return parents;
    }

    public ArrayList<Entity> getChildren() {
        return children;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void addParent(Entity entity) {
        if (!parents.contains(entity)) {
            parents.add(entity);
            entity.addChildren(this);
        }
    }

    public void addChildren(Entity entity) {
        if (!children.contains(entity)) {
            children.add(entity);
            entity.addParent(this);
        }
    }
    public boolean checkParent(Entity entity) {
        return parents.contains(entity);
    }

    public boolean checkChildren(Entity entity) {
        return children.contains(entity);
    }

    public void die() {
        this.deathDate = new Date();
        this.healthStatus = HealthStatus.DEAD;
    }

    public void infect() {
        healthStatus = HealthStatus.INFECTED;
    }

    public void sick() throws Exception {
        if (healthStatus != HealthStatus.INFECTED) {
            throw new Exception("Существо должно быть сначала заражено!");
        }
        healthStatus = HealthStatus.SICK;
    }

    public void recover() {
        healthStatus = HealthStatus.HEALTHFUL;
    }

    public void injure() {
        healthStatus = HealthStatus.INJURY;
    }
}
