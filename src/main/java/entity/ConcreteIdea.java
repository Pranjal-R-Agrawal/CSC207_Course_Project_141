package entity;
public class ConcreteIdea implements Idea{

    private String idea;
    private String businessModel = "";

    public ConcreteIdea(String idea) {
        this.idea= idea;
    }
    @Override
    public Idea setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
        return this;
    }
    @Override
    public String getIdea() {
        return idea;
    }
    @Override
    public String getBusinessModel(){
        return businessModel;
    }
}
