package com.xt;


import com.xt.leetcode.Solution0;
import com.xt.util.IOHelper;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Other {

    public Other() {
        System.out.println("Other");
    }

    public static void main(String[] args) {
        String str = "[123][456][789]";
        Pattern compile = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }

//        String str = "SEAction.testToast(\"AA\",\"{\\\"hhh\\\":\\\"aaa\\\"}\",)";
//
//        Pattern compile = Pattern.compile("\"(.*?)\",");
//        Matcher matcher = compile.matcher(str);
//        boolean matches = matcher.matches();
//
//        while (matcher.find()) {
//            System.out.println(matcher.group(1));
//        }


    }

    private void test(Other other) {
        test3(other::test2, "hello");
    }


    public void test3(Consumer<String> consumer, String show) {
        consumer.accept(show);
    }

    public void test2(String str) {
        System.out.println(str);
    }

    private void stringtest() {
        String s = "hello";
        String s1 = "hello";
        String s2 = new String("hello");
        String s3 = new String("hello");
        String s4 = "hell" + "o";
        String s6 = new String("hell") + new String("o");
        String s7 = "hell";
        String s8 = "o";

        char[] c = getChar(s);
        char[] c2 = getChar(s2);
        char[] c6 = getChar(s6);
        char[] c7 = getChar(s7 + s8);
        System.out.println(s == s1);//1
        System.out.println(s == s2);//2
        System.out.println(s2 == s3);//3
        System.out.println(s == s4);//4
        System.out.println(s2 == s4);//5
        System.out.println(s == s6);//6
        System.out.println(c == c2);//7
        System.out.println(c == c6);//8
        System.out.println((s7 + s8) == s);
        System.out.println(c == c7);
    }

    /**
     * 获取string中的value
     */
    private char[] getChar(String s) {
        Field value = null;
        try {
            value = s.getClass().getDeclaredField("value");
            value.setAccessible(true);
            Object o = value.get(s);
            return (char[]) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
