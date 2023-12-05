package com.neusoft.entityDAO.impl;

import com.neusoft.entity.TestTemplate.Questions;
import com.neusoft.entity.TestTemplate.Template;
import com.neusoft.entity.people.Administrator;
import com.neusoft.entityDAO.TemplateDAO;
import com.neusoft.exception.IDRepeatException;
import com.neusoft.utils.FileUtil;
import com.neusoft.utils.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TemplateDAOImpl implements TemplateDAO {
    public static TemplateDAOImpl templateDAO = null;
    private TemplateDAOImpl(){

    }

    public static TemplateDAOImpl getTemplateDAO(){
        if (templateDAO == null){
            templateDAO = new TemplateDAOImpl();
        }
        return templateDAO;
    }

    String filename = "Templates.json";

    @Override
    public void addTemplate(Template template) throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, Template.class);
        if (list != null){
            for(int i = 0 ;i < list.size(); i++){
                Template template1 =(Template) list.get(i);
                String id = template1.getTemplateID();
                if (id.equals(template.getTemplateID())){
                    throw new IDRepeatException("ID重复，无法重复添加！");
                }
            }
        }

        String json_string = GsonUtil.toJson(template);
        if (json_string.length() > 0){
            FileUtil.writeData(filename, json_string, true);
        }
    }

    @Override
    public void updateTemplate(Template template) throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, Template.class);//传入json数据代表的那个list
        List<Template> templates = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                templates.add((Template) o);
            }
        }

        for (Template template1 : templates){
            if (template1.getTemplateID().equals(template.getTemplateID())){
                templates.set(templates.indexOf(template1), template);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Template template_temp : templates){
            if (templates.indexOf(template_temp) == templates.size() - 1){
                data.append(GsonUtil.toJson(template_temp));
            }
            else
                data.append(GsonUtil.toJson(template_temp)).append("\n");
        }
        FileUtil.writeData(filename, data.toString(), false);
    }

    @Override
    public void deleteTemplate(Template template) throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, Template.class);//传入json数据代表的那个list
        List<Template> templateArrayList = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                templateArrayList.add((Template) o);
            }
        }

        for (Template template1 : templateArrayList){
            if (template1.getTemplateID().equals(template.getTemplateID())){
                templateArrayList.remove(template1);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (Template template_temp : templateArrayList){
            if (templateArrayList.indexOf(template_temp) == templateArrayList.size() - 1){
                data.append(GsonUtil.toJson(template_temp));
            }
            else
                data.append(GsonUtil.toJson(template_temp)).append("\n");
        }
        FileUtil.writeData(filename, data.toString(), false);
    }

    @Override
    public List<Template> findAllTemplate() throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, Template.class);
        List<Template> list1 = new ArrayList<>();
        for (Object o : list){
            list1.add((Template) o);
        }
        return list1;//返回一个列表，里面有所有的Template对象
    }

    @Override
    public Template findTemplateByID(String id) throws IOException {
        Template template_found = null;
        List<Object> list = FileUtil.readDataToList(filename, Template.class);
        List<Template> list1 = new ArrayList<>();

        for (Object o : list){
            list1.add((Template) o);
        }

        for (Template template : list1){
            if (template.getTemplateID().equals(id)){
                template_found = template;
            }
        }
        return template_found;//返回寻找到的template对象
    }

    public boolean addQuestions(List<Questions> questionsList, String id) throws IOException{
        boolean flag = false;

        List<Template> templates = findAllTemplate();
        for (Template template : templates){
            if (id.equals(template.getTemplateID())){
                template.setQuestionList(questionsList);
                updateTemplate(template);
                flag = true;
                break;
            }
        }

        return flag;
    }
}
