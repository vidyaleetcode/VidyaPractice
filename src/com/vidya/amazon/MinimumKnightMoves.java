package com.vidya.amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumKnightMoves {

    static int[][] DIR = new int[][]{{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

    public static void main(String[] args) {
        System.out.println(minKnightMoves(2,1));

        System.out.println(minKnightMoves(5,5));
    }

    static int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);

        //Initial knight Move
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        Set<String> visited = new HashSet<>();
        visited.add("0-0");
        int result = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.remove();
                int curX = current[0];
                int curY = current[1];

                if (curX == x && curY == y) {
                    return result;
                }

                for (int[] dir : DIR) {
                    int newX = curX + dir[0];
                    int newY = curY + dir[1];

                    if (!visited.contains(newX + "-" + newY) && newX >= -1 && newY >= -1) {
                        queue.add(new int[]{newX, newY});
                        visited.add(newX + "-" + newY);
                    }
                }

            }
            result++;
        }

        return -1;
    }
}
