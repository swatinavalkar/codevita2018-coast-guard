import java.util.*;
public class CoastGuard2 {
	int M;	//no. of rows
	int N;	//no. of columns
	int k;	//no. of boats
	int uncontSq;	//no. of uncontrolled square grids
	int[][] positions;	//array of boat positions
	int[][] boats;	//array of all boat step information
	int grids;	//no. of grids (M*N)
	
	public void calUncontSq() {
		boats = new int[k][grids];
		for(int i = 0, j = 0; i < k; i++){	//make k arrays of boat individual boat steps in boats' array
			for(int a = 0; a < N; a++) {
				for(int b = 0; b < M; b++, j++) {
					boats[i][j] = Math.abs(positions[i][0] - a) + Math.abs(positions[i][1] - b);	//steps required by the boat to reach every grid
				}
			}
			j = 0;
		}
		uncontSq = 0;
		for(int i = 0; i < (grids); i++){	//compare step values of all boats in same grids
			for(int b = 0; b < k-1; b++){
				if(boats[b][i] == boats[b+1][i]){
					uncontSq++;
					break;
				}
			}
		}
		System.out.println("Number of uncontrolled grids are " + uncontSq);
	}
	
	public void getInput() {
		String[] lineVector = null;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter comma separated values of rows, columns, no. of boats: ");
		lineVector = in.nextLine().split(",");
		try {
			M = Integer.parseInt(lineVector[0]);
			N = Integer.parseInt(lineVector[1]);
			k = Integer.parseInt(lineVector[2]);
			grids = M*N;
			checkInput();
			positions = new int[k][2];
			System.out.println("Enter comma separated values of position of boats line by line: ");
			for(int i = 0; i < k; i++) {
				lineVector = in.nextLine().split(",");
				int left = Integer.parseInt(lineVector[0]);
				int right = Integer.parseInt(lineVector[1]);
				int[] num = {left, right};
				checkInput(num, i);
				positions[i] = num;
			}
		} catch(Exception e) {
			exitProgram();
		}
		in.close();
	}
	
	public void checkInput() {
		if (M >=50 || M <=0 || N >=50 || N <=0 || k >= 10 || k <=1 || k > grids) {	//no. of boats(k) cannot be greater than total no. of grids
			exitProgram();
		}
	}
	
	public void checkInput(int[] num, int count) {
		if (num[0] < 0 || num[0] > N-1 || num[1] < 0 || num[1] > M-1) {
			exitProgram();
		}
		for (int i = 0; i < count; i++) {
			if(Arrays.equals(num, positions[i])) {	//check whether the position entered has already been entered before 
				exitProgram();
			}
		}
	}
	
	public void exitProgram() {
		System.out.println("Invalid input");
		System.exit(1);
	}
	
	public static void main(String[] args) {
		CoastGuard2 c = new CoastGuard2();
		c.getInput();
		c.calUncontSq();
	}
}