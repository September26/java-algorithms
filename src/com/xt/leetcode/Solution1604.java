package com.xt.leetcode;

import java.util.*;

/**
 * 1604. 警告一小时内使用相同员工卡大于等于三次的人
 * 每日一题：2023.02.07
 * 完成日期：2023.02.07
 * 链接：https://leetcode.cn/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
 * 描述：
 * 力扣公司的员工都使用员工卡来开办公室的门。每当一个员工使用一次他的员工卡，安保系统会记录下员工的名字和使用时间。如果一个员工在一小时时间内使用员工卡的次数大于等于三次，这个系统会自动发布一个 警告 。
 * <p>
 * 给你字符串数组 keyName 和 keyTime ，其中 [keyName[i], keyTime[i]] 对应一个人的名字和他在 某一天 内使用员工卡的时间。
 * <p>
 * 使用时间的格式是 24小时制 ，形如 "HH:MM" ，比方说 "23:51" 和 "09:49" 。
 * <p>
 * 请你返回去重后的收到系统警告的员工名字，将它们按 字典序升序 排序后返回。
 * <p>
 * 请注意 "10:00" - "11:00" 视为一个小时时间范围内，而 "23:51" - "00:10" 不被视为一小时内，因为系统记录的是某一天内的使用情况。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
 * 输出：["daniel"]
 * 解释："daniel" 在一小时内使用了 3 次员工卡（"10:00"，"10:40"，"11:00"）。
 * 示例 2：
 * <p>
 * 输入：keyName = ["alice","alice","alice","bob","bob","bob","bob"], keyTime = ["12:01","12:00","18:00","21:00","21:20","21:30","23:00"]
 * 输出：["bob"]
 * 解释："bob" 在一小时内使用了 3 次员工卡（"21:00"，"21:20"，"21:30"）。
 * 示例 3：
 * <p>
 * 输入：keyName = ["john","john","john"], keyTime = ["23:58","23:59","00:01"]
 * 输出：[]
 * 示例 4：
 * <p>
 * 输入：keyName = ["leslie","leslie","leslie","clare","clare","clare","clare"], keyTime = ["13:00","13:20","14:00","18:00","18:51","19:30","19:49"]
 * 输出：["clare","leslie"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= keyName.length, keyTime.length <= 105
 * keyName.length == keyTime.length
 * keyTime 格式为 "HH:MM" 。
 * 保证 [keyName[i], keyTime[i]] 形成的二元对 互不相同 。
 * 1 <= keyName[i].length <= 10
 * keyName[i] 只包含小写英文字母。
 * <p>
 * 解题思路：
 * 首先，根据keyName整理成Map对象，key为keyName中的人，value为List<String>，代表时间。
 * 然后，针对List进行处理，把字符串类型的时间转换为int类型，按照大小排序，然后统计60范围内是否存在3个,存在则返回true。
 * 最后，把所有为true的人放进集合，进行排序。
 * <p>
 * state:done
 */
public class Solution1604 {

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            String name = keyName[i];
            List<String> list = map.get(name);
            if (list == null) {
                list = new ArrayList<>();
                map.put(name, list);
            }
            list.add(keyTime[i]);
        }
        List<String> result = new ArrayList<>();
        for (String name : map.keySet()) {
            if (ifWarn(map.get(name))) {
                result.add(name);
            }
        }
        result.sort((o1, o2) -> (o1 + o2).compareTo((o2 + o1)));
        return result;
    }

    private boolean ifWarn(List<String> stringList) {
        if (stringList.size() < 3) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        for (String timeString : stringList) {
            String[] split = timeString.split(":");
            int time = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            list.add(time);
        }
        list.sort(Comparator.comparingInt(o -> o));
        for (int i = 2; i < list.size(); i++) {
            if (list.get(i) - list.get(i - 2) <= 60) {
                return true;
            }
        }
        return false;
    }

}