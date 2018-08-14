import java.util.*;
public class CoastGuard {
	int rows;
	int columns;
	int noOfBoats;
	int[][] boats;	//array of boat positins
	int grids;	//no. of grids (rows*columns)
	
	public void calUncontSq() {
		int uncontSq = 0;	//no. of uncontrolled square grids
		for(int a = 0; a < columns; a++) {	//a is the left component of the grid number
			for(int b = 0; b < rows; b++) {	//b is the right component of the grid number
			HashSet<Integer> stepsSet = new HashSet<Integer>();	//stores the list of steps required by each boat to reach (a,b) grid without duplicates
				for(int i = 0; i < noOfBoats; i++){
					if(!(stepsSet.add(Math.abs(boats[i][0] - a) + Math.abs(boats[i][1] - b)))){	//if duplicate entries, add() would return false
						uncontSq++;
					}
				}
			}
		}
		System.out.println("Number of uncontrolled grids are " + uncontSq);
	}
	
	public void getInput() {
		String[] lineVector = null;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter comma separated values of rows, columns, no. of steps: ");
		lineVector = in.nextLine().split(",");
		try {
			rows = Integer.parseInt(lineVector[0]);
			columns = Integer.parseInt(lineVector[1]);
			noOfBoats = Integer.parseInt(lineVector[2]);
			grids = rows*columns;
			checkInput();
			boats = new int[noOfBoats][2];
			System.out.println("Enter comma separated values of position of steps line by line: ");
			for(int i = 0; i < noOfBoats; i++) {
				lineVector = in.nextLine().split(",");
				int left = Integer.parseInt(lineVector[0]);
				int right = Integer.parseInt(lineVector[1]);
				int[] num = {left, right};
				checkInput(num, i);
				boats[i] = num;
			}
		} catch(Exception e) {
			exitProgram();
		}
		in.close();
	}
	
	public void checkInput() {
		if (rows >=50 || rows <=0 || columns >=50 || columns <=0 || noOfBoats >= 10 || noOfBoats <=1 || noOfBoats > grids) {	//no. of steps(noOfBoats) cannot be greater than total no. of grids
			exitProgram();
		}
	}
	
	public void checkInput(int[] num, int count) {
		if (num[0] < 0 || num[0] > columns-1 || num[1] < 0 || num[1] > rows-1) {
			exitProgram();
		}
		for (int i = 0; i < count; i++) {
			if(Arrays.equals(num, boats[i])) {	//check whether the position entered has already been entered before as two boats cannot be on a single grid
				exitProgram();
			}
		}
	}
	
	public void exitProgram() {
		System.out.println("Invalid input");
		System.exit(1);
	}
	
	public static void main(String[] args) {
		CoastGuard c = new CoastGuard();
		c.getInput();
		c.calUncontSq();
	}
}