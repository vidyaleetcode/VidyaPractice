package com.vidya.dailybyte;

import java.util.HashSet;
import java.util.Set;

/**
 * 403. Frog Jump
 *
 * A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
 *
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.
 *
 * If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [0,1,3,5,6,8,12,17]
 * Output: true
 * Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
 * Example 2:
 *
 * Input: stones = [0,1,2,3,4,8,9,11]
 * Output: false
 * Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.
 *
 *
 * Constraints:
 *
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 231 - 1
 * stones[0] == 0
 *
 * O(stones[n - 1]*n)
 */
public class FrogJump {

    public static void main(String[] args) {

        int[] stones = {0, 1, 3, 5, 6, 8, 12, 17};

        System.out.println(canCross(stones));
    }

    static boolean canCross(int[] stones) {

        if (stones == null || stones.length == 0) {
            return false;
        }

        int n = stones.length;
        if (n == 1) return true;
        if (stones[1] != 1) return false;
        if (n == 2) return true;
        int last = stones[n - 1];

        Set<Integer> stoneSet = new HashSet<>();
        for (int i = 0; i < n; i++) {

            //The 2 stones are far away
            if (i > 3 && stones[i] > stones[i - 1] * 2) {
                return false;
            }

            stoneSet.add(stones[i]);
        }
        return canReach(stoneSet, last, 1, 1);
    }

    static boolean canReach(Set<Integer> stoneSet, int last, int pos, int jump) {
        //3 Conditions.
        if (pos + jump - 1 == last || pos + jump == last || pos + jump + 1 == last) {
            return true;
        }

        if (stoneSet.contains(pos + jump + 1)) {
            if (canReach(stoneSet, last, pos + jump + 1, jump + 1)) {
                return true;
            }
        }

        if (stoneSet.contains(pos + jump)) {
            if (canReach(stoneSet, last, pos + jump, jump)) {
                return true;
            }
        }

        if (jump > 1 && stoneSet.contains(pos + jump - 1)) {
            if (canReach(stoneSet, last, pos + jump - 1, jump - 1)) {
                return true;
            }
        }
        return false;
    }
}
