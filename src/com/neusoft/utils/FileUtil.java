package com.neusoft.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    /**
     * 一个向文件中写入内容的方法。
     * 新用户注册采用追加，而删除、修改用覆盖
     * @param filename 要写入数据的文件名
     * @param data 要写入的数据
     * @param mode 写入文件的方式。true代表追加，false代表覆盖。
     * @throws IOException
     */

    public static void writeData(String filename, String data, boolean mode) throws IOException {
        String filename2 = "resources/data/" + filename;
        try {
            FileWriter fw = new FileWriter(filename2, mode);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.newLine();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 一个将文件中的内容读取出来成为对象列表的方法
     * @param fileName 读取的文件名
     * @param C 操作类型，C：类类型
     * @return List<Object></>
     */

    public static List<Object> readDataToList(String fileName, Class<?> C) throws IOException {
        String filepath = "resources/data/" + fileName;
        List<Object> list = new ArrayList<Object>();
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String str = null;
        while ((str = br.readLine()) != null){
            str = "[" + str + "]";//json字符串，需要加上中括号，否则解析有问题！
            Object obj = GsonUtil.toObj(str,C);//每一行读出一个字符串，转成obj放到list里。
            list.add(obj);
        }
        br.close();//关闭流
        return list;//返回读取的对象
    }

    /**
     * 一个用来读取文件数据返回字符串的方法
     * @param filename 读取的文件名
     * @return 文件内容
     * @throws IOException
     */

    public static String readData(String filename) throws IOException {
        String filepath = "resources/data/" + filename;
        StringBuilder output = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String str;
            while ((str = br.readLine()) != null){
                output.append(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件打开失败！");
        }
        return output.toString();
    }
}
