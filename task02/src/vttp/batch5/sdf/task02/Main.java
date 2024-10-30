package vttp.batch5.sdf.task02;

import java.io.File;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {

		if (args.length == 0) {
			System.out.println("Error no filename provided");
			System.exit(1);
		}

		String filename = args[0];
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("File does not exist");
			System.exit(1);
		}

		Reader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String[][] board = new String[3][3];
		List<String> empty = new ArrayList<>();
		String input;
		int count = 0;
		while ((input = br.readLine()) != null) {
			String[] holder = input.split("");
			board[count][0] = holder[0].trim().toUpperCase();
			if (holder[0].equals(".")) {
				empty.add(count + "," + 0);
			}
			board[count][1] = holder[1].trim().toUpperCase();
			if (holder[1].equals(".")) {
				empty.add(count + "," + 1);
			}
			board[count][2] = holder[2].trim().toUpperCase();
			if (holder[2].equals(".")) {
				empty.add(count + "," + 2);
			}
			count++;
		}
		br.close();
		System.out.println("Processing: "+filename+"\n");
		System.out.println("Board:\n");
		System.out.println(Utilities.printformat(board));
		System.out.println("-----------------------------");
		for (String emp : empty) {
            String[] values = emp.split(",");
            int row = Integer.parseInt(values[0]);
            int col = Integer.parseInt(values[1]);
            int utility = 0;
			board[row][col] = "X";
			if(Utilities.hasWon(board,"X")){
				utility = 1;
			}
			else if(Utilities.nextMove(board,"O")){
				utility = -1;
			}
			board[row][col] = ".";

			System.out.printf("y=%d, x=%d, utility=%d\n",row,col,utility);
		}
	}
}
