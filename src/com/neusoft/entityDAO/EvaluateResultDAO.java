package com.neusoft.entityDAO;

import com.neusoft.entity.TestTemplate.EvaluateResult;
import com.neusoft.entity.people.Patient;

import java.io.IOException;
import java.util.List;

public interface EvaluateResultDAO {
    public void addEvaluateResult(EvaluateResult evaluateResult) throws IOException;

    public void deleteEvaluateResult(EvaluateResult evaluateResult) throws IOException;

    public List<EvaluateResult> findAllEvaluateResult() throws IOException;

    public EvaluateResult findEvaluateResultByName(Patient patient) throws IOException;
}
