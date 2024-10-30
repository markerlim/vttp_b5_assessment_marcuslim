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

    public int minimax(String[][] board, String player) {
        if (hasWon(board, "O")) {
            return -1;
        }
        if (hasWon(board, "X")) {
            return 1;
        }
        if(!hasWon(board, "X") && !hasWon(board, "O")){
            return 0;
        }

        if (player.equals("X")) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; i < 3; j++) {
                    if (board[i][j].equals(".")) {
                        board[i][j] = "X";
                        best = Math.max(best, minimax(board, "O"));
                        board[i][j] = ".";
                    }
                }
            }
            return best;

        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals(".")) {
                        board[i][j] = "O";
                        best = Math.min(best, minimax(board, "X"));
                        board[i][j] = ".";
                    }
                }
            }
            return best;
        }

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
