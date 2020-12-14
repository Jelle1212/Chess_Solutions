
package chessboard;

import java.util.*;


public class board {

// Just to start it off.
static int count_solutions(int max_queens, int max_bishops, int a, int b) {
    int[][] board= new int[a][b];
    return recurse(board, 0, (a * b)-1, max_queens, max_bishops, 0, 0);
}

// This is where the actual work is done.
// board is the board so far, represented by a two dimensional array where
// -1 = Queen
// 0 = Empty
// 1 = Bishop
// i is the square we are currently on, and n is the total number of board.
// max_queens and max_bishops are the maximum allowed to place.
// queens and bishops are the number placed so far.
static int recurse(int[][] board, int i, int n, int max_queens, int max_bishops, int queens, int bishops) {
    if((max_queens == queens && max_bishops == bishops)) {
        //If we have placed all the pieces, it is time to check if it is a solution.
        //Return one if it is, otherwise zero.
        //return (int) check_solution(board);
        print2D(board);
        
        System.out.println("");
        return 1;
    }
    //We havent placed all the pieces yet. Time to do some recursion.
    //Get the two dimensional x and y coordinates for the one dimensional coordinate i.
    int x = i / board.length;
    int y = i % board.length;
    //Number of solutions = the sum of number of solutions for the alternatives.
    int solutions = 0;
    //Place a queen in the current spot.
    if(queens < max_queens && i <= n ) {
        board[x][y] = -1;
        solutions += recurse(board, i+1, n, max_queens, max_bishops, queens + 1, bishops);
    }
    //Place a bishop in the current spot.
    if(bishops < max_bishops && i <= n ) {
        board[x][y] = 1;
        solutions += recurse(board, i+1, n, max_queens, max_bishops, queens, bishops + 1);
    }
    if ( i <= n ){//Place nothing in the current spot.
    board[x][y] = 0;
    solutions += recurse(board, i+1, n, max_queens, max_bishops, queens, bishops);
    return solutions;
    }
    return 1;
}

public static boolean check_solution(int board[][], int max_queens, int max_bishops){
    int count_queen=0;
    int count_bishop=0;
    int[] i_coordinates_queens;
    int[] j_coordinates_queens;
    for (int i = 0; i <board.length; i++){
        for(int j = 0; j < board[i].length; j++){
            if(board[i][j] == -1 && count_queen < max_queens){
                count_queen++;
                //i_coordinates_queens[i] = i;
            }
            else if(board[i][j] == 1 && count_bishop < max_bishops){
                count_bishop++;
            }
            else
        }
    }
}

 

public static void print2D(int mat[][]) 
    { 
        // Loop through all rows 
        for (int[] row : mat) 
            
            // converting each row as string 
            // and then printing in a separate line 
            System.out.println(Arrays.toString(row));
            
    } 
   
public static void main(String[] args) {
    count_solutions(1, 3, 4, 4);
    
    }   
}
