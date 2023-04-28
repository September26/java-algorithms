package com.xt.leetcode;

import java.util.*;

/**
 * 1233. 删除子文件夹
 * 每日一题：2023.02.08
 * 完成日期：2023.02.08
 * 链接：https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/
 * 描述：
 * 你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。
 * <p>
 * 如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的 子文件夹 。
 * <p>
 * 文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：'/' 后跟一个或者多个小写英文字母。
 * <p>
 * 例如，"/leetcode" 和 "/leetcode/problems" 都是有效的路径，而空字符串和 "/" 不是。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * 输出：["/a","/c/d","/c/f"]
 * 解释："/a/b/" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
 * 示例 2：
 * <p>
 * 输入：folder = ["/a","/a/b/c","/a/b/d"]
 * 输出：["/a"]
 * 解释：文件夹 "/a/b/c" 和 "/a/b/d/" 都会被删除，因为它们都是 "/a" 的子文件夹。
 * 示例 3：
 * <p>
 * 输入: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * 输出: ["/a/b/c","/a/b/ca","/a/b/d"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= folder.length <= 4 * 104
 * 2 <= folder[i].length <= 100
 * folder[i] 只包含小写字母和 '/'
 * folder[i] 总是以字符 '/' 起始
 * 每个文件夹名都是 唯一 的
 * <p>
 * 解题思路：
 * 首先按照文件夹的层级排序，
 * 然后遍历文件夹列表，获取每个文件夹对象，逐级遍历查看map中是否存在parent文件夹。
 * <p>
 * <p>
 * state:done
 */
public class Solution1233 {


    public List<String> removeSubfolders(String[] folder) {
        Model1233[] array = new Model1233[folder.length];
        for (int i = 0; i < folder.length; i++) {
            String str = folder[i];
            array[i] = new Model1233(str);
        }
        Arrays.sort(array, Comparator.comparingInt(o -> o.mLength));
        Map<String, Model1233> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            Model1233 model = array[i];
            StringBuilder builder = new StringBuilder();
            for (int j = 1; j < model.mLength; j++) {
                String key = model.getKey(builder, j);
                if (map.containsKey(key)) {
                    break;
                }
                if (j == model.mLength - 1) {
                    map.put(model.mStr, model);
                    list.add(model.mStr);
                }
            }
        }
        return list;
    }


    static class Model1233 {
        String mStr;
        String[] mSplit;
        int mLength;

        Model1233(String str) {
            this.mStr = str;
            this.mSplit = str.split("/");
            this.mLength = mSplit.length;
        }


        public String getKey(StringBuilder builder, int index) {
            return builder.append("/").append(mSplit[index]).toString();
        }
    }

}