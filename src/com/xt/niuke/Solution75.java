package com.xt.niuke;


/**
 * 描述
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符 "go" 时，第一个只出现一次的字符是 "g" 。当从该字符流中读出前六个字符 “google" 时，第一个只出现一次的字符是"l"。
 * <p>
 * 数据范围：字符串长度满足  ，字符串中出现的字符一定在 ASCII 码内。
 * 进阶：空间复杂度  ，时间复杂度
 * <p>
 * 后台会用以下方式调用 Insert 和 FirstAppearingOnce 函数
 * string caseout = "";
 * 1.读入测试用例字符串casein
 * 2.如果对应语言有Init()函数的话，执行Init() 函数
 * 3.循环遍历字符串里的每一个字符ch {
 * Insert(ch);
 * caseout += FirstAppearingOnce()
 * }
 * 2. 输出caseout，进行比较。
 * 返回值描述：
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 * 11/2
 */
public class Solution75 {
    //Insert one char from stringstream
    int[] arrays = new int[1000];
    char[] chars = new char[1001];
    int head = 0;
    int num = 0;

    public void Insert(char ch) {
        arrays[ch] = arrays[ch] + 1;
        chars[num++] = ch;
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        char result = '#';

        while (true) {
            if (arrays[chars[head]] == 0) {
                System.out.println("error");
                break;
            }
            if (arrays[chars[head]] == 1) {
                return chars[head];
            }
            head++;
        }
        return result;
    }

    public String test(String casein) {
        String caseout = "";
        for (char ch : casein.toCharArray()) {
            Insert(ch);
            char c = FirstAppearingOnce();
            caseout += c;
        }

        return caseout;
    }


}
