package tpo.task_3;

import tpo.task_3.disease.Disease;

public class ProcessOfDisease {
    private Disease disease;
    private Entity entity;

    private DiseaseResult result;

    public ProcessOfDisease(Disease disease, Entity entity) {
        this.disease = disease;
        this.entity = entity;
        startInfection(entity);
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Entity getHuman() {
        return entity;
    }

    public void setHuman(Entity entity) {
        this.entity = entity;
    }

    public DiseaseResult getResult() {
        return result;
    }

    private void startInfection(Entity entity){
        entity.infect();
    }

    public void startDisease() throws Exception {
        entity.sick();
    }

    public void finishDisease(DiseaseResult result) throws Exception {
        if (disease.getType() == TypeOfDisease.DEADLY && result != DiseaseResult.DEATH) {
            throw new Exception("Чуда не бывает, нельзя выжить после смертельной болезни!");
        }
        if (result == DiseaseResult.DEATH && disease.getType() == TypeOfDisease.PASSING_WITHOUT_TREATMENT) {
            throw new Exception("Болезнь не вызывает смерть");
        }
        if (result == DiseaseResult.DEATH) {
            entity.die();
        } else if (result == DiseaseResult.WITHOUT_TRACE) {
            entity.recover();
        } else {
            entity.injure();
        }
        this.result = result;
    }
}
