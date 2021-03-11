package com.example.spring.learn.elasticsearch.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName Report
 * @Description: TODO
 * @Author clark
 * @Date 2021/3/3 16:17
 **/
public class Report {
    public static void main(String[] args) {
        List<Outer> list = new LinkedList<>();

    }

    class Outer {
        String value;
        String name;
        List<Outer> list;
    }



}
