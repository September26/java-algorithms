package com.xt;


import java.util.Vector;

public class Other2 {

    public Other2() {
        System.out.println("Other2");
    }

    public Other2(String a) {
        System.out.println("Other2"+a);
    }

    static {
        System.out.println("static code");
    }

    {
        System.out.println("un static code");
    }

    public static void main(String[] args) {


        Vector<String> strings = new Vector<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        int i = strings.indexOf("3");
        System.out.println(i);


    }


}
