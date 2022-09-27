//Faiyaz Islam
//CIS 2168, Prof Rosen
//10/11/21
//This program will use recursion to solve sudoku problems

public class Sudoku {

    public static void main(String[] args) {

        //sudoku problem input
        int[][] sudokuBoard = new int[][]{
                {0, 2, 0, 0, 0, 4, 3, 0, 0},
                {9, 0, 0, 0, 2, 0, 0, 0, 8},
                {0, 0, 0, 6, 0, 9, 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 7, 2, 5, 0, 3, 6, 8, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 0, 2, 0, 5, 0, 0, 0},
                {1, 0, 0, 0, 9, 0, 0, 0, 3},
                {0, 0, 9, 8, 0, 0, 0, 6, 0},
        };

        //calls recursive function, first position is 0
        sudokuSolver(sudokuBoard, 0);
    }

    public static boolean sudokuSolver(int[][] sudokuBoard, int position){

        //last position is 81, therefore function will end once that position is reached
        if(position >= 81){

            //prints out the board
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    System.out.print("[" + sudokuBoard[i][j] + "]");
                }
                System.out.println();
            }

            return true;
        }

        //if the position is not empty, then it will continue to next position
        if (sudokuBoard[position/9][position%9] != 0) {
            return sudokuSolver(sudokuBoard, position + 1);
        }

        else{

            //goes through each of the 9 possible values
            for(int i = 1; i <= 9; i++){

                //checks if valid value
                if(valid(sudokuBoard, i, position / 9, position % 9)){

                    sudokuBoard[position/9][position%9] = i;

                    //continues on to next position if value is satisfying
                    if(sudokuSolver(sudokuBoard, position + 1)){
                        return true;
                    }
                    else{
                        //if it doesn't work, then the position is made empty again
                        sudokuBoard[position/9][position%9] = 0;
                    }
                }
            }
        }

        return false;
    }

    //function for checking the validity of the values
    public static boolean valid(int[][] board, int value, int row, int column){

        //horizontal check
        if(!validRow(board, value, row)){
            return false;
        }

        //vertical check
        if(!validColumn(board, value, column)){
            return false;
        }

        //square check
        if(!validSquare(board, value, row, column)){
            return false;
        }

        return true;
    }

    public static boolean validRow(int[][] board, int value, int row){

        //goes through the row to see if the value is repeated
        for(int i = 0; i < 9; i++){
            if (board[row][i] == value) {
                return false;
            }
        }

        return true;
    }

    public static boolean validColumn(int[][] board, int value, int column){

        //goes through the column to see if the value is repeated
        for(int i = 0; i < 9; i++){
            if (board[i][column] == value) {
                return false;
            }
        }

        return true;
    }

    public static boolean validSquare(int[][] board, int value, int row, int column){

        //math here sets the boundaries for each of the 9 boxes
        int boxR = row/3;
        int boxC = column/3;

        //this double loop checks each of the positions in the 3x3 square for a repeat of the value
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i+(boxR*3)][j+(boxC*3)] == value){
                    return false;
                }
            }
        }

        return true;
    }

}
