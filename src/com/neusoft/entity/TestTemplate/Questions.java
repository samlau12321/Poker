package com.neusoft.entity.TestTemplate;

import java.util.Map;

public class Questions {
    private String description;
    private String ID;
    private boolean Select;
    private String type;
    private String ChoiceA;
    private String ChoiceB;
    private String ChoiceC;

    public String getChoiceA() {
        return ChoiceA;
    }

    public void setChoiceA(String choiceA) {
        ChoiceA = choiceA;
    }

    public String getChoiceB() {
        return ChoiceB;
    }

    public void setChoiceB(String choiceB) {
        ChoiceB = choiceB;
    }

    public String getChoiceC() {
        return ChoiceC;
    }

    public void setChoiceC(String choiceC) {
        ChoiceC = choiceC;
    }

    public Questions(String description, String ID, boolean select, String type, String choiceA, String choiceB, String choiceC) {
        this.description = description;
        this.ID = ID;
        Select = select;
        this.type = type;
        ChoiceA = choiceA;
        ChoiceB = choiceB;
        ChoiceC = choiceC;
    }

    public Questions(String description, String ID, String type) {
        this.description = description;
        this.ID = ID;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Questions(String description, String ID, boolean select) {
        this.description = description;
        this.ID = ID;
        Select = select;
    }

    public Questions(String description, String ID) {
        this.description = description;
        this.ID = ID;
    }

    public Questions(){
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean getSelect() {
        return Select;
    }

    public void setSelect(boolean select) {
        Select = select;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "description='" + description + '\'' +
                ", ID='" + ID + '\'' +
                ", Select=" + Select +
                ", type='" + type + '\'' +
                ", ChoiceA='" + ChoiceA + '\'' +
                ", ChoiceB='" + ChoiceB + '\'' +
                ", ChoiceC='" + ChoiceC + '\'' +
                '}';
    }
}
