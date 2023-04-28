package com.xt.leetcode;

import java.util.*;
import java.util.function.Predicate;

/**
 * 1797. 设计一个验证系统
 * 每日一题：2023.02.09
 * 完成日期：2023.02.09
 * 链接：https://leetcode.cn/problems/design-authentication-manager/description/
 * 描述：
 * 你需要设计一个包含验证码的验证系统。每一次验证中，用户会收到一个新的验证码，这个验证码在 currentTime 时刻之后 timeToLive 秒过期。如果验证码被更新了，那么它会在 currentTime （可能与之前的 currentTime 不同）时刻延长 timeToLive 秒。
 *
 * 请你实现 AuthenticationManager 类：
 *
 * AuthenticationManager(int timeToLive) 构造 AuthenticationManager 并设置 timeToLive 参数。
 * generate(string tokenId, int currentTime) 给定 tokenId ，在当前时间 currentTime 生成一个新的验证码。
 * renew(string tokenId, int currentTime) 将给定 tokenId 且 未过期 的验证码在 currentTime 时刻更新。如果给定 tokenId 对应的验证码不存在或已过期，请你忽略该操作，不会有任何更新操作发生。
 * countUnexpiredTokens(int currentTime) 请返回在给定 currentTime 时刻，未过期 的验证码数目。
 * 如果一个验证码在时刻 t 过期，且另一个操作恰好在时刻 t 发生（renew 或者 countUnexpiredTokens 操作），过期事件 优先于 其他操作。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：
 * ["AuthenticationManager", "renew", "generate", "countUnexpiredTokens", "generate", "renew", "renew", "countUnexpiredTokens"]
 * [[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
 * 输出：
 * [null, null, null, 1, null, null, null, 0]
 *
 * 解释：
 * AuthenticationManager authenticationManager = new AuthenticationManager(5); // 构造 AuthenticationManager ，设置 timeToLive = 5 秒。
 * authenticationManager.renew("aaa", 1); // 时刻 1 时，没有验证码的 tokenId 为 "aaa" ，没有验证码被更新。
 * authenticationManager.generate("aaa", 2); // 时刻 2 时，生成一个 tokenId 为 "aaa" 的新验证码。
 * authenticationManager.countUnexpiredTokens(6); // 时刻 6 时，只有 tokenId 为 "aaa" 的验证码未过期，所以返回 1 。
 * authenticationManager.generate("bbb", 7); // 时刻 7 时，生成一个 tokenId 为 "bbb" 的新验证码。
 * authenticationManager.renew("aaa", 8); // tokenId 为 "aaa" 的验证码在时刻 7 过期，且 8 >= 7 ，所以时刻 8 的renew 操作被忽略，没有验证码被更新。
 * authenticationManager.renew("bbb", 10); // tokenId 为 "bbb" 的验证码在时刻 10 没有过期，所以 renew 操作会执行，该 token 将在时刻 15 过期。
 * authenticationManager.countUnexpiredTokens(15); // tokenId 为 "bbb" 的验证码在时刻 15 过期，tokenId 为 "aaa" 的验证码在时刻 7 过期，所有验证码均已过期，所以返回 0 。
 *
 *
 * 提示：
 *
 * 1 <= timeToLive <= 108
 * 1 <= currentTime <= 108
 * 1 <= tokenId.length <= 5
 * tokenId 只包含小写英文字母。
 * 所有 generate 函数的调用都会包含独一无二的 tokenId 值。
 * 所有函数调用中，currentTime 的值 严格递增 。
 * 所有函数的调用次数总共不超过 2000 次。
 * 解题思路：
 * 采用计数法的策略。
 * 使用list来记录到期的tokenId记录，使用map来记录未到期的。
 * generate操作，则往map中记录+1，并且往list中添加一条记录，只不过到期时间为currentTime+mTimeToLive；
 * renew操作，则从map记录中查询，如果不存在则不需要。存在则更新map记录中Model1797的time值+1，并且再次增加一条到期记录。
 * countUnexpiredTokens返回map长度即可。
 * 另外每一次操作，都做一次removeUnexpired检查，清除掉所有到期的记录。
 * <p>
 * <p>
 * state:done
 */
public class Solution1797 {

    public static class AuthenticationManager {
        int mTimeToLive = 0;
        List<Model1797> list = new ArrayList<>();
        Map<String, Model1797> map = new HashMap<>();

        public AuthenticationManager(int timeToLive) {
            this.mTimeToLive = timeToLive;
        }

        public void generate(String tokenId, int currentTime) {
            list.add(new Model1797(tokenId, currentTime + mTimeToLive));
            map.put(tokenId, new Model1797(tokenId, currentTime));
            removeUnexpired(currentTime);
        }

        public void renew(String tokenId, int currentTime) {
            removeUnexpired(currentTime);
            Model1797 model1797 = map.get(tokenId);
            if (model1797 == null) {
                return;
            }
            model1797.time++;
            list.add(new Model1797(tokenId, currentTime + mTimeToLive));

        }

        public int countUnexpiredTokens(int currentTime) {
            removeUnexpired(currentTime);
            return map.size();
        }

        private void removeUnexpired(int currentTime) {
            Iterator<Model1797> iterator = list.iterator();
            while (iterator.hasNext()) {
                Model1797 next = iterator.next();
                if (currentTime >= next.currentTime) {
                    iterator.remove();
                    Model1797 model1797 = map.get(next.tokenId);
                    if (model1797.time == 1) {
                        map.remove(model1797.tokenId);
                    } else {
                        model1797.time--;
                    }
                    continue;
                }
                break;
            }
        }

        static class Model1797 {
            String tokenId;
            int currentTime;
            /**
             * 被记录了几次，
             * generate和renew操作都会导致time+1，如果记录了3次，则removeUnexpired操作中就要消除3次才可以移除队列
             */
            int time = 1;

            Model1797(String tokenId, int currentTime) {
                this.tokenId = tokenId;
                this.currentTime = currentTime;
            }
        }
    }
}