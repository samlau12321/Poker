package com.neusoft.service;

import java.io.IOException;
import java.util.List;

/**
 * 业务类基类（设立原因：避免各个接口的方法冗余）
 */

public interface BaseService {
    /**
     * 保存操作。
     * @param obj 需要保存的对象
     * @throws IOException
     */

    public void save(Object obj, String filename, boolean mode) throws IOException;

    /**
     * 查询全部对象的操作
     * @return 一个对象列表
     */
    public List<Object> queryAll(String filename) throws IOException;

    public boolean validate(String loginName, String password, String filename) throws IOException;
}
