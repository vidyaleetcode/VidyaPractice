package com.vidya.amazon;

/**
 * 348. Design Tic-Tac-Toe
 * <p>
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 * <p>
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 * <p>
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
 * Output
 * [null, 0, 0, 0, 0, 0, 0, 1]
 * <p>
 * Explanation
 * TicTacToe ticTacToe = new TicTacToe(3);
 * Assume that player 1 is "X" and player 2 is "O" in the board.
 * ticTacToe.move(0, 0, 1); // return 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 * <p>
 * ticTacToe.move(0, 2, 2); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 * <p>
 * ticTacToe.move(2, 2, 1); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 * <p>
 * ticTacToe.move(1, 1, 2); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 * <p>
 * ticTacToe.move(2, 0, 1); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 * <p>
 * ticTacToe.move(1, 0, 2); // return 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 * <p>
 * ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 100
 * player is 1 or 2.
 * 1 <= row, col <= n
 * (row, col) are unique for each different call to move.
 * At most n2 calls will be made to move.
 *
 * Time Complexity : O(1)
 *
 */
public class TicTacToeGame {

    int[] rows, cols;
    int diagonal, antiDiagonal, target;

    public static void main(String[] args) {

        TicTacToeGame ticTacGame = new TicTacToeGame(3);
        System.out.println(ticTacGame.move(0,0,1));
        System.out.println(ticTacGame.move(0,2,2));
        System.out.println(ticTacGame.move(2,2,1));
        System.out.println(ticTacGame.move(1,1,2));
        System.out.println(ticTacGame.move(2,0,1));
        System.out.println(ticTacGame.move(1,0,2));
        System.out.println(ticTacGame.move(2,1,1));
    }

    public TicTacToeGame(int n) {
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

        if(row == col){
            diagonal += sign;
        }

        if(row == target - 1 - col){
            antiDiagonal += sign;
        }

        if(rows[row] == result || cols[col] ==result
        || diagonal == result || antiDiagonal == result){
            return player;
        }else{
            return 0;
        }

    }

}
