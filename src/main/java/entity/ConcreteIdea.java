package entity;
public class ConcreteIdea implements Idea{

    private String idea;
    private String businessModel = "";

    public ConcreteIdea(String idea) {
        this.idea= idea;
    }

    public Idea setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
        return this;
    }

    public String getIdea() {
        return idea;
    }

    public String getBusinessModel(){
        return businessModel;
    }
}
