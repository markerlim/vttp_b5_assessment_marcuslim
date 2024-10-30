package vttp.batch5.sdf.task02;

public class Utilities {

    public static String printformat(String[][] board) {
        String line = "";
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                line += board[row][col];
            }
            line += "\n";
        }
        return line.trim();
    }

    public static boolean hasWon(String[][] board, String player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    public static boolean nextMove(String[][] board,String player){
        for(int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if(board[row][col].equals(".")){
                    board[row][col] = "O";
                    if(hasWon(board, player)){
                        return true;
                    }
                    board[row][col]=".";
                }
            }
        }
        return false;
    }
}
