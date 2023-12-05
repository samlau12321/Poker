package com.neusoft.service;

import com.neusoft.entity.TestTemplate.Template;
import java.util.List;

public interface TemplateService extends BaseService{
    public void deleteQuestionInTemplate(Template question);

    public void changeQuestionInTemplate(Template question);

    public void viewAnswer(List<Template> templateList);

    public boolean MultipleDelete();

}
