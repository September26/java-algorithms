package com.xt.leetcode;


import java.util.*;

/**
 * 完成日期：
 * 链接：
 * 描述
 * 加载第 n 页的数据
 * 给定一个整数数组 nums, 数组长度 len, 书写一个函数 load_page_data(nums, len, n, page_size),
 * 返回按照升序排列后的第 n 页的数据，page_size 为每页数据长度。
 * <p>
 * 解题思路：
 * <p>
 * <p>
 * state:
 */
public class Bianchengti {


    /**
     * dest:
     * 1. coordinate arithmetic
     * 1. Two sides of the rectangular region are parallel to the equator, the other two sides of the rectangle are parallel to the longitude line. So strictly speaking, this is not a real rectangle.
     * 2. Longitude is between -180 and 180, latitude is between -90 to 90.
     * 3. The rectangular region is defined as follows
     * 经纬度，A位于西北，B位于东南
     *
     * @param a
     * @param b
     * @param p
     * @return
     */
    public boolean isInside(Coordinate a, Coordinate b, Coordinate p) {
        //纬度是否在范围内
        if (p.latitude > a.latitude || p.latitude < b.latitude) {
            return false;
        }
        double longA = a.longitude + 180;
        double longB = b.longitude + 180;
        double longP = p.longitude + 180;
        if (longA <= longB) {
            return longP >= longA && longP <= longB;
        }
        return longP >= longA || longP <= longB;
    }

    /**
     * dest:加载第 n 页的数据
     * 给定一个整数数组 nums, 数组长度 len, 书写一个函数 load_page_data(nums, len, n, page_size), 返回按照升序排列后的第 n 页的数据，page_size 为每页数据长度。
     *
     * @param nums
     * @param len
     * @param n         从0开始
     * @param page_size
     * @return
     */
    public int[] load_page_data(int[] nums, int len, int n, int page_size) {
        int[] indexPage = new int[page_size];
        if (n >= len) {
            return indexPage;
        }
        System.arraycopy(nums, page_size * n, indexPage, 0, page_size);
        return indexPage;
    }

    /**
     * 2. 聊天数据合并
     * 定义聊天消息类型 message，包含三个关键属性(id, timestamp, content)，id 为字符 串类型的唯一键, timestamp 为整数类型的 unix 时间戳表示消息的发送时间, content 为 字符串类型表示消息内容。
     * 给定本地聊天数据数组 local_messages，数组长度 local_len, 远端聊天数据数组 remote_messages，数组长度 remote_len, 书写一个函数
     * merge_messages(local_messages, local_len, remote_messages, remote_len) 返回新的聊天数据数组，数组按照 timestamp 递减顺序排列，数组中不存在相同 id 的
     * 消息数据。
     *
     * @param local_messages
     * @param local_len
     * @param remote_messages
     * @param remote_len
     * @return
     */
    public Message[] merge_messages(Message[] local_messages, int local_len, Message[] remote_messages, int remote_len) {
        Set<Integer> set = new HashSet<>();
        List<Message> list = new ArrayList<>();
        addMessage(local_messages, set, list);
        addMessage(remote_messages, set, list);
        list.sort((o1, o2) -> (int) (o2.timestamp - o1.timestamp));
        return list.toArray(new Message[0]);
    }

    private void addMessage(Message[] messages, Set<Integer> set, List<Message> list) {
        for (Message message : messages) {
            if (set.contains(message.id)) {
                continue;
            }
            set.add(message.id);
            list.add(message);
        }
    }

    static class Message {
        int id;
        long timestamp;
        String content;
    }

    public static class Coordinate {
        public double longitude;
        public double latitude;
    }

}