package com.vidya.amazon;

import java.util.HashSet;
import java.util.Set;

/**
 * River Crossing
 *A frog is attempting to cross a river to reach the other side. Within the river, there are stones located at different positions given by a stones array (this array is in sorted order). Starting on the first stone (i.e. stones[0]), the frog makes a jump of size one potentially landing on the next stone. If the frog’s last jump was of size x, the frog’s next jump may be of size x - 1, x, or x + 1. Given these following conditions return whether or not the frog can reach the other side.
 * Note: The frog may only jump in the forward direction.
 *
 * Ex: Given the following stones…
 *
 * stones = [0, 1, 10], return false.
 * This question is asked by Amazon. The frog can jump from stone 0 to stone 1, but then the gap is too far to jump to the last stone (i.e. the stone at position 10)
 * Ex: Given the following stones…
 *
 * stones = [0, 1, 2, 4], return true.
 * The frog can jump from stone 0, to stone 1, to stone 2, to stone 4.
 */
public class FrogJump {

    public static void main(String[] args) {

        int[] stones = {0, 1, 5};

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

        int last = stones[stones.length - 1];
        Set<Integer> stoneSet = new HashSet<>();

        for (int i = 0; i < n; i++) {

            if (i > 3 && stones[i] > stones[i - 1] * 2) {
                return false;
            }
            stoneSet.add(stones[i]);
        }

        return canReach(stoneSet, last, 1, 1);
    }

    static boolean canReach(Set<Integer> stoneSet, int last, int pos, int jump) {

        if (pos + jump - 1 == last || pos + jump == last || pos + jump + 1 == last) {
            return true;
        }

        if(stoneSet.contains(pos + jump + 1)){
            if(canReach(stoneSet, last, pos + jump + 1, jump + 1)){
                return true;
            }
        }

        if(stoneSet.contains(pos + jump)){
            if(canReach(stoneSet,last, pos + jump, jump)){
                return true;
            }
        }

        if(jump > 1 && stoneSet.contains(pos + jump - 1)){
            if(canReach(stoneSet,last,pos + jump - 1, jump - 1)){
                return true;
            }
        }

        return false;
    }
}
