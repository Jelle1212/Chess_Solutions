import java.util.*;


public class board {

static int N = 5;
static int M = 5;
static int Queens = 1;
static int Bishops = 3;
static int count = 0;
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
        
       count += squares_under_attack(board);
       //print2D(board);
       //System.out.println("");
   

        return 1;
    }
    //We havent placed all the pieces yet. Time to do some recursion.
    //Get the two dimensional x and y coordinates for the one dimensional coordinate i.
  
    int x = i % N;
    int y = i / N;

    //Place a queen in the current spot.
    if(queens < max_queens && i <= n ) {
        board[x][y] = -1;
        recurse(board, i+1, n, max_queens, max_bishops, queens + 1, bishops);
    }
    //Place a bishop in the current spot.
    if(bishops < max_bishops && i <= n ) {
        board[x][y] = 1;
        recurse(board, i+1, n, max_queens, max_bishops, queens, bishops + 1);
    }
    if ( i <= n ){//Place nothing in the current spot.
    board[x][y] = 0;
    recurse(board, i+1, n, max_queens, max_bishops, queens, bishops);
    }
    return 0;
}



public static int squares_under_attack(int board[][]){  //analyze the board and check if all squares are under attack
    for (int i = 0; i < M; i++){
        for(int j = 0; j <  N; j++){
            if(!isAttacked(j, i, board)){
                return 0;                           // if one square is not under attack then the board combination
                                                    // is not the solution an go to the next one
            }      
        }
    }
    print2D(board);
    System.out.println("");
    return 1;
}
public static boolean isAttacked(int row , int col, int board[][]){
    int i, j; 
    boolean QueenFound = false;
    boolean BishopFound = false;
    // Check the 'col'th column for any queen and if there is no bishop in the way on the square we stand on
    
    for (i = 0; i < N; i++){ 
        if(board[i][col] == -1){
            QueenFound = true;
            BishopFound = false;
        }
            
        else if (board[i][col] == 1){
            QueenFound = false;
            BishopFound = true;
        }

        if(i > row && BishopFound){
            QueenFound = false;
            BishopFound = false;
            break;
        }
            
        else if(i >= row && QueenFound)
            return true;
        
        if(i == row){
            QueenFound = false;
            BishopFound = false;
        }
            
    }
    
    // Check the 'row'th row for any queen and if there is no bishop in the way on the square we stand on
 
    for (j = 0; j < M; j++){ 
        if (board[row][j] == -1){ 
            QueenFound = true;
            BishopFound = false;
        }

        else if (board[row][j] == 1){ 
            QueenFound = false;
            BishopFound = true;
        }

        if(j >= col && BishopFound){
            QueenFound = false;
            BishopFound = false;
            break;
        }

        else if(j >= col && QueenFound)
            return true;

        if(j == col){
            QueenFound = false;
            BishopFound = false;
        }
    }
    

    // Check the diagonals for any queen or bishop. 
    for (i = 0; i < Math.min(N, M); i++) 
        if (row - i >= 0 && col - i >= 0 && board[row - i][col - i] ==  -1 ||
            row - i >= 0 && col - i >= 0 && board[row - i][col - i] ==  1) 
            return true; 
        else if (row - i >= 0 && col + i < M && board[row - i][col + i] == -1 ||
            row - i >= 0 && col + i < M && board[row - i][col + i] == 1) 
            return true; 
        else if (row + i < N && col - i >= 0 && board[row + i][col - i] == -1 ||
            row + i < N && col - i >= 0 && board[row + i][col - i] == 1) 
            return true; 
        else if (row + i < N && col + i < M && board[row + i][col + i] == -1 ||
            row + i < N && col + i < M && board[row + i][col + i] == 1) 
            return true; 
    
    // This square is unattacked. Hence return 'false'. 
    return false; 
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
    count_solutions(Queens, Bishops, N, M);
    System.out.println(count);
    System.out.println("");    
    }   
}
