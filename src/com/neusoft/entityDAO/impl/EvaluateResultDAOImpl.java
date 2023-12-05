package com.neusoft.entityDAO.impl;

import com.neusoft.entity.TestTemplate.EvaluateResult;
import com.neusoft.entity.people.Patient;
import com.neusoft.entityDAO.EvaluateResultDAO;
import com.neusoft.utils.FileUtil;
import com.neusoft.utils.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluateResultDAOImpl implements EvaluateResultDAO {
    private static EvaluateResultDAOImpl evaluateResultDAO = null;
    private EvaluateResultDAOImpl(){

    }

    public static EvaluateResultDAOImpl getEvaluateResultDAO(){
        if (evaluateResultDAO == null){
            evaluateResultDAO = new EvaluateResultDAOImpl();
        }

        return evaluateResultDAO;
    }

    String filename = "EvaluateResults.json";

    @Override
    public void addEvaluateResult(EvaluateResult evaluateResult) throws IOException {
//        List<Object> list = FileUtil.readDataToList(filename, EvaluateResult.class);
//        if (list != null){
//            for(int i = 0 ;i < list.size(); i++){
//                EvaluateResult evaluateResult1 =(EvaluateResult) list.get(i);
//                int id = evaluateResult1.getID();
//                if (id == evaluateResult1.getID()){
//                    throw new IDRepeatException("ID重复，无法重复添加！");
//                }
//            }
//        }

        String json_string = GsonUtil.toJson(evaluateResult);
        if (json_string.length() > 0){
            FileUtil.writeData(filename, json_string, true);
        }
    }

    @Override
    public void deleteEvaluateResult(EvaluateResult evaluateResult) throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, EvaluateResult.class);//传入json数据代表的那个list
        List<EvaluateResult> evaluateResultArrayList = new ArrayList<>();
        if (list != null){
            for (Object o : list){
                evaluateResultArrayList.add((EvaluateResult) o);
            }
        }

        for (EvaluateResult evaluateResult1 : evaluateResultArrayList){
            if (evaluateResult1.getJudgement().equals(evaluateResult.getJudgement()) && evaluateResult1.getScore() ==
                    evaluateResult.getScore()){
                evaluateResultArrayList.remove(evaluateResult1);
                break;
            }
        }

        StringBuilder data = new StringBuilder();
        for (EvaluateResult evaluateResult_temp : evaluateResultArrayList){
            if (evaluateResultArrayList.indexOf(evaluateResult_temp) == evaluateResultArrayList.size() - 1){
                data.append(GsonUtil.toJson(evaluateResult_temp));
            }
            else
                data.append(GsonUtil.toJson(evaluateResult_temp)).append("\n");
        }
        FileUtil.writeData(filename, data.toString(), false);
    }

    @Override
    public List<EvaluateResult> findAllEvaluateResult() throws IOException {
        List<Object> list = FileUtil.readDataToList(filename, EvaluateResult.class);
        List<EvaluateResult> evaluateResultArrayList = new ArrayList<>();
        for (Object o : list){
            evaluateResultArrayList.add((EvaluateResult) o);
        }
        return evaluateResultArrayList;
    }

    @Override
    public EvaluateResult findEvaluateResultByName(Patient patient) throws IOException {
        EvaluateResult evaluateResult = null;
        List<Object> list = FileUtil.readDataToList(filename, EvaluateResult.class);
        List<EvaluateResult> evaluateResultArrayList = new ArrayList<>();
        for (Object o : list){
            evaluateResultArrayList.add((EvaluateResult) o);
        }

        for (EvaluateResult evaluateResult1 : evaluateResultArrayList){
            if (evaluateResult1.getPatientName().equals(patient.getName())){
                evaluateResult = evaluateResult1;
            }
        }

        return evaluateResult;
    }

    public EvaluateResult findEvaluateResultByDate(Date date) throws IOException {
        EvaluateResult evaluateResult = null;
        List<Object> list = FileUtil.readDataToList(filename, EvaluateResult.class);
        List<EvaluateResult> evaluateResultArrayList = new ArrayList<>();
        for (Object o : list){
            evaluateResultArrayList.add((EvaluateResult) o);
        }

        for (EvaluateResult evaluateResult1 : evaluateResultArrayList){
            if (evaluateResult1.getDate().equals(date.toString())){
                evaluateResult = evaluateResult1;
            }
        }
        return evaluateResult;
    }
}
