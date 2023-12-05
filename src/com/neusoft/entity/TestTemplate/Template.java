package com.neusoft.entity.TestTemplate;

import java.util.List;

public class Template {
    private List<Questions> questionList;
    private String TemplateID;
    private String TempType;
    public boolean Select;

    public Template(List<Questions> questionList, String templateID, String tempType, boolean select) {
        this.questionList = questionList;
        TemplateID = templateID;
        TempType = tempType;
        Select = select;
    }

    public Template(List<Questions> questionList, String templateID, String tempType) {
        this.questionList = questionList;
        TemplateID = templateID;
        TempType = tempType;
        this.Select = false;
    }

    public Template(String templateID, String tempType, boolean select) {
        TemplateID = templateID;
        TempType = tempType;
        Select = select;
    }

    public Template(String templateID, String tempType) {
        TemplateID = templateID;
        TempType = tempType;
        this.Select = false;
    }

    public Template(){

    }

    public List<Questions> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Questions> questionList) {
        this.questionList = questionList;
    }

    public String getTemplateID() {
        return TemplateID;
    }

    public void setTemplateID(String templateID) {
        TemplateID = templateID;
    }

    public String getTempType() {
        return TempType;
    }

    public void setTempType(String tempType) {
        TempType = tempType;
    }

    public boolean getSelect() {
        return Select;
    }

    public void setSelect(boolean select) {
        this.Select = select;
    }

    @Override
    public String toString() {
        return "Template{" +
                "questionList=" + questionList +
                ", TemplateID=" + TemplateID +
                ", TempType='" + TempType + '\'' +
                '}';
    }
}
