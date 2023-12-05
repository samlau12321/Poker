package com.neusoft.entity.TestTemplate;

public class EvaluateResult {
    private String patientName;
    private String sex;
    private int score;
    private String doctorName;
    private String judgement;
    private String date;
    private String templateName;
    private String templateType;

    public EvaluateResult(String patientName, String sex, int score, String doctorName, String judgement, String date, String templateName, String templateType) {
        this.patientName = patientName;
        this.sex = sex;
        this.score = score;
        this.doctorName = doctorName;
        this.judgement = judgement;
        this.date = date;
        this.templateName = templateName;
        this.templateType = templateType;
    }

    public EvaluateResult() {

    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    @Override
    public String toString() {
        return "EvaluateResult{" +
                "patientName='" + patientName + '\'' +
                ", sex='" + sex + '\'' +
                ", score=" + score +
                ", doctorName='" + doctorName + '\'' +
                ", judgement='" + judgement + '\'' +
                ", date='" + date + '\'' +
                ", templateName='" + templateName + '\'' +
                ", templateType='" + templateType + '\'' +
                '}';
    }
}
