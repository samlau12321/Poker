package com.neusoft.entityDAO;

import com.neusoft.entity.TestTemplate.Questions;
import com.neusoft.entity.TestTemplate.Template;
import com.neusoft.entity.people.Patient;

import java.io.IOException;
import java.util.List;

public interface TemplateDAO {
    public void  addTemplate(Template template) throws IOException;

    public void updateTemplate(Template template) throws IOException;

    public void deleteTemplate(Template template) throws IOException;

    public List<Template> findAllTemplate() throws IOException;

    public Template findTemplateByID(String id) throws IOException;

    public boolean addQuestions(List<Questions> questionsList, String id) throws IOException;
}
