package com.vidya.fb;

public class TicTacGame {

    int[] rows, cols;
    int diagonal, antiDiagonal, target;

    public static void main(String[] args) {
        TicTacGame ticTacGame = new TicTacGame(3);
        System.out.println(ticTacGame.move(0,0,1));
        System.out.println(ticTacGame.move(0,2,2));
        System.out.println(ticTacGame.move(2,2,1));
        System.out.println(ticTacGame.move(1,1,2));
        System.out.println(ticTacGame.move(2,0,1));
        System.out.println(ticTacGame.move(1,0,2));
        System.out.println(ticTacGame.move(2,1,1));
    }

    public TicTacGame(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
        target = n;
    }

    public int move(int row, int col, int player) {
        int sign = player == 1 ? 1 : -1;
        int result = sign * target;

        rows[row] += sign;
        cols[col] += sign;

        if(row == col) {
            diagonal += sign;
        }
        if(row == target - 1 - col){
            antiDiagonal += sign;
        }

        if(rows[row] ==result || cols[col]== result ||
                diagonal == result || antiDiagonal == result){
            return player;
        }else{
            return 0;
        }
    }
}
