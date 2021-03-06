package com.bootx;

import com.bootx.app.chengyu.entity.Idiom;
import com.bootx.app.chengyu.entity.Word;
import com.bootx.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Demo {

    public static List<Idiom> idiom() {
        String path = "C:\\Users\\black\\Downloads\\chinese-xinhua-master\\data\\idiom.json";
        File file = new File(path);
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString.replace(" ",""));
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        List<Idiom> idioms = JsonUtils.toObject(sb.toString(), new TypeReference<List<Idiom>>() {
        });
        return idioms;
    }

    public static List<Word> word() {
        String path = "C:\\Users\\black\\Downloads\\chinese-xinhua-master\\data\\word.json";
        File file = new File(path);
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString.replace(" ",""));
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        List<Word> idioms = JsonUtils.toObject(sb.toString(), new TypeReference<List<Word>>() {
        });
        return idioms;
    }



    public static void main(String[] args) {
        String word = "毫无疑义";
        char[] chars = word.toCharArray();
        System.out.println(chars.length);
    }
}
