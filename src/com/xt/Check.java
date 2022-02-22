package com.xt;

import com.xt.util.IOHelper;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 检查少了
 */
public class Check {
    public static void main(String[] args) {
        Set<String> articleSet = getArticleSet();
        Set<String> javaSet = getJavaSet();
        List<Integer> list = new ArrayList<>();
        for (String str : javaSet) {
            if (articleSet.contains(str)) {
                continue;
            }
            list.add(Integer.parseInt(str));
        }
        Collections.sort(list);
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    private static Set<String> getJavaSet() {
        Set<String> set = new HashSet<>();
        File leetcode = new File("src/com/xt/leetcode");
        for (File javaFile : leetcode.listFiles()) {
            String name = javaFile.getName();
            String id = name.substring(8, name.length() - 5);
            set.add(id);
        }
        return set;
    }

    //​力扣解法汇总
//力扣解法汇总
    private static Set<String> getArticleSet() {
        Set<String> set = new HashSet<>();
        try {
            List<String> list = IOHelper.readListStrByCode(IOHelper.fromFileToIputStream(new File("checklist.txt")), "utf-8");
            for (String str : list) {
                if (str.contains("力扣解法汇总")) {
                    String id = str.substring(str.indexOf("力扣解法汇总") + 6, str.indexOf("-"));
                    set.add(id);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

}
