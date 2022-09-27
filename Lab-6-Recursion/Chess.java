//Faiyaz Islam
//CIS 2168, Prof Rosen
//10/11/21
//This program will use recursion to solve the eight queens chess problem

public class Chess {

    public static void main(String[] args) {

        //creates the 8x8 board
        int[][] chessBoard = new int[8][8];

        //calls the recursive func, sends board and the first position on it
        chessSolver(chessBoard, 0);

    }

    public static boolean chessSolver(int[][] chessBoard, int position){

        //ends if the last column is reached
        if(position >= 8){

            //prints out chess board
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    System.out.print("[" + chessBoard[i][j] + "]");
                }
                System.out.println();
            }

            return true;
        }


        //goes through each of the rows
        for(int i = 0; i < chessBoard[position].length; i++){

            //checks if the position is valid place for queen
            if(valid(chessBoard, position, i)){

                //sets queen (1) at position
                chessBoard[position][i] = 1;

                //checks that next position is also valid
                if(chessSolver(chessBoard, position + 1)){
                    return true;
                }

                //sets value to no queen (0) otherwise
                else{
                    chessBoard[position][i] = 0;
                }
            }
        }

        return false;
    }

    public static boolean valid(int[][] chessBoard, int row, int column){

        //checks the rows
        if(!validRow(chessBoard, row, column)){
            return false;
        }

        //checks the columns
        if(!validColumn(chessBoard, column)){
            return false;
        }

        //checks the diagonals
        if(!validDiagonals(chessBoard, row, column)){
            return false;
        }

        return true;
    }

    public static boolean validRow(int[][] chessBoard, int row, int column){

        //checks the positions before in the row
        for(int i = 0; column - i >= 0; i++){
            if(chessBoard[row][column - i] == 1){
                return false;
            }
        }

        return true;
    }

    public static boolean validColumn(int[][] chessBoard, int column){

        //checks the values in the column
        for(int i = 0; i < chessBoard[0].length; i++){
            if(chessBoard[i][column] == 1){
                return false;
            }
        }

        return true;
    }

    public static boolean validDiagonals(int[][] chessBoard, int row, int column){

            //checks left and up
            for(int i = 0; (column - i >= 0) && (row - i >= 0); i++) {
                if (chessBoard[row - i][column - i] == 1) {
                    return false;
                }
            }

            //checks left and down
            for(int i = 0; (column - i >= 0) && (row + i < 8); i++){
                if(chessBoard[row + i][column - i] == 1)
                    return false;
            }

            //checks right and up
            for(int i = 0; (column + i < 8) && (row - i >= 0); i++) {
                if (chessBoard[row - i][column + i] == 1) {
                    return false;
                }
            }

            //checks right and down
            for(int i = 0; (column + i < 8) && (row + i < 8); i++){
                if(chessBoard[row + i][column + i] == 1)
                    return false;
            }

        return true;
    }

}
