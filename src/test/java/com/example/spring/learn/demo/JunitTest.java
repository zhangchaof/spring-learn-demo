package com.example.spring.learn.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName JunitTest
 * @Description: TODO
 * @Author clark
 * @Date 2021/3/13 10:55
 **/
public class JunitTest {
    @Test
    public void testPad() {
        System.out.println("true = " + StringUtils.leftPad("1000", 4, "0"));
    }
    @Test
    public void testMap() {
        System.out.println("true = " + "210506010002".substring(6,10));
        List<Map<String,Object>> list = Lists.newArrayList();
        Map<String,Object> retMap = Maps.newHashMap();
        retMap.put("test",11);
        list.add(retMap);
        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list));
        retMap.put("test",123);
        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list));
    }

    @Test
    public void matchDate() {
        System.out.println("1" + Lists.newArrayList().addAll(null));
        String reg = "^\\d{4}-?(?:0[1-9]|1[0-2])$";
        boolean flag = Pattern.matches(reg, "202-12-01");
        System.out.println("flag = " + flag);
        System.out.println("(LocalDate.now().toString().substring(0,7)) = " + (LocalDate.now().toString().substring(0,7)));
    }

    @Test
    public void testRatio() {

        NumberFormat numberFormat = NumberFormat.getInstance();

        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((+0.584));//所占百分比
        System.out.println("result = " + result);
        System.out.println("Double.valueOf(\"+0.5\") = " + Double.valueOf("+0.5"));
    }

    @Test
    public void test() {
//        String json = "{'k':}";
//        V v = JSON.parseObject(json, V.class);
//        System.out.println("v = " + v.getK());
        LocalDate date = LocalDate.now();
        LocalDate firstday = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstday = " + firstday);
        BigDecimal bigDecimal = new BigDecimal("0");
        System.out.println("bigDecimal = " + bigDecimal.toString());
        double k = 0;
        System.out.println("(bigDecimal) = " + (0 == k));
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("i = " + i);
        System.out.println("1.0/3 = " + 6.0 / 3);

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        String dealRatio = numberFormat.format(0.6 / 3 * 100);
        System.out.println("dealRatio = " + dealRatio);
    }

    @Test
    public void getDayOfWeek() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        System.out.println("monday = " + firstDayOfWeek);
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth);
    }

    @Test
    public void test01() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        long sum = list.stream().filter(temp -> temp != 1).count();
        System.out.println("sum = " + System.currentTimeMillis());
        System.out.println("list = " + list.size());

    }

    static class V {
        String k;

        public String getK() {
            return k;
        }

        public void setK(String k) {
            this.k = k;
        }

        @Override
        public String toString() {
            return "V{" +
                    "k='" + k + '\'' +
                    '}';
        }
    }
    
    @Test
    public void testD() {
        LocalDate localDate = LocalDate.parse("2021-01");
        LocalDate localDate1 = localDate.minusMonths(1L);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        System.out.println("dateTimeFormatter = " + dateTimeFormatter.format(localDate1));
    }
}
