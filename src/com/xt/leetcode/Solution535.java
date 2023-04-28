package com.xt.leetcode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * 535. TinyURL 的加密与解密
 * 每日一题：2022.06.29
 * 完成日期：
 * 链接：
 * 描述：
 * TinyURL 是一种 URL 简化服务， 比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，它将返回一个简化的URL http://tinyurl.com/4e9iAk 。请你设计一个类来加密与解密 TinyURL 。
 * <p>
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
 * <p>
 * 实现 Solution 类：
 * <p>
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：url = "https://leetcode.com/problems/design-tinyurl"
 * 输出："https://leetcode.com/problems/design-tinyurl"
 * <p>
 * 解释：
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // 返回加密后得到的 TinyURL 。
 * string ans = obj.decode(tiny); // 返回解密后得到的原本的 URL 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= url.length <= 10^4
 * 题目数据保证 url 是一个有效的 URL
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/encode-and-decode-tinyurl
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 解题思路：
 * 缩短路径，那只能形成一个映射关系，这个映射关系是长链接和短链接的映射关系。
 * 这种映射关系有很多种，我这里用最简单的一种，取长链接的MD5进行映射。
 * <p>
 * <p>
 * state:done
 */
public class Solution535 {

    public static class Codec {
        Map<String, String> cache = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String key = getMD5String(longUrl);
            cache.put(key, longUrl);
            return key;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return cache.get(shortUrl);
        }

        public String getMD5String(String str) {
            try {
                // 生成一个MD5加密计算摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 计算md5函数
                md.update(str.getBytes());
                // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
                // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
                //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
                return new BigInteger(1, md.digest()).toString(16);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}