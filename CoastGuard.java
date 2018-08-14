import java.util.*;
public class CoastGuard {
	int M;	//no. of rows
	int N;	//no. of columns
	int k;	//no. of boats
	int uncontSq;	//no. of uncontrolled square grids
	int[][] positions;	//array of boat positions
	int[][] boats;	//array of all boat step information
	int grids;	//no. of grids (M*N)
	
	public void getInput() {
		String inputLine = null;
		String[] lineVector = null;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter comma separated values of rows, columns, no. of boats: ");
		inputLine = in.nextLine();
		lineVector = inputLine.split(",");
		try {
			M = Integer.parseInt(lineVector[0]);
			N = Integer.parseInt(lineVector[1]);
			k = Integer.parseInt(lineVector[2]);
			grids = M*N;
			checkInput();
			positions = new int[k][2];
			System.out.println("Enter comma separated values of position of boats line by line: ");
			for(int i = 0; i < k; i++) {
				inputLine = in.nextLine();
				lineVector = inputLine.split(",");
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
	
	public void calUncontSq() {
		boats = new int[k][grids];
		
		for(int i = 0; i < k; i++){	//make k arrays of boat individual boat steps in boats' array
			int j = 0;	//variable to denote grids
			boats[i][j] = positions[i][0] + positions[i][1];	//no. of steps in first grid
			int starting = boats[i][0];
			int left = positions[i][0];
			for(int a = 0; a < N; a++) {
				starting = boats[i][j];
				for(int n = 0; n < (M - 1); n++) {
					int right = positions[i][1];
					if(right > 0) {
						j++;
						boats[i][j] = boats[i][j-1] - 1;
						right--;
					}
					else {
						j++;
						boats[i][j] = boats[i][j-1] + 1;
					}
				}
				j++;
				if(j < grids){
					if(left > 0){
						boats[i][j] = starting - 1;
						left--;
					}
					else {
						boats[i][j] = starting + 1;
					}
				}
			}
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
	
	public static void main(String[] args) {
		CoastGuard c = new CoastGuard();
		c.getInput();
		c.calUncontSq();
	}
}