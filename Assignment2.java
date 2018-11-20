/**
 * Header
 * <Raiyan Chowdhury>
 * <rac4444>
 * <16495> (Your section id)
 * Please fill inside < >  and do not remove < >.
 */

/**
 * Class to implement Assignment2 solution
 * findProgram method should be implemented.
 * Please do not include any main methods.
 */

import java.util.*;


public class Assignment2 {

	// Implement this function
	public static ArrayList<Airdrop> findProgram(int[][] map, int row, int column) {
		//keep track of visited regions
		boolean visited[][] = new boolean[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				visited[i][j] = false;
			}
		}
		
		ArrayList<Airdrop> sortedAirdrops = new ArrayList<Airdrop>();
		
		//the map must be traversed in row+col order, i.e. diagonally;
		//this can be done by considering that each region in a particular
		//diagonal strip has the same row+col value
		//so, we use the highest row+col value possible (sum) as a bound
		int sum = row + column - 2;
		//we iterate through the possible row+col values and use them
		//to find i,j values to access the map with.
		for (int k = 0; k <= sum; k++) {
			//however, there are some i+j values that will add up to "sum"
			//but are out of the bounds of the map, so they must be excluded
			//note: i represents row#, j represents col#
			
			//i cannot be less than or equal to k - column; 
			//if it were, then j would end up larger than "column"
			int min_row;
			if ((k - column + 1) > 0) { 
				min_row = (k - column + 1);
			}
			else { 
				min_row = 0;
			}
			//moreover, i cannot be larger than k; if it were, then
			//j would have to be less than 0 in order for i+j to == k 
			int max_row;
			if (k < (row - 1)) {
				max_row = k;
			} else {
				max_row = row - 1;
			}
			//with these bounds in place, we can now safely traverse the 2d array
			//diagonally using the maximum row#+col# as a bound, without checking
			//out-of-bounds locations
			for (int i = min_row; i <= max_row; i++) {
				int j = k - i;
				if (map[i][j] > 0 && visited[i][j] == false){
					Airdrop a = new Airdrop(i, j, 0);
					int[] size = {0};
					getRegion(map, i, j, visited, size);
					a.setSize(size[0]);
					sortedAirdrops.add(a);
				}
					
			}
			
		}
		
		return sortedAirdrops;
	}
	
	public static void getRegion(int map[][], int row, int column, 
			boolean visited[][], int[] size) {
		
		visited[row][column] = true;
		size[0] += map[row][column];
		
		//connected regions are connected vertically/horizontally, not diagonally
		//i.e. only up to one row# OR col# off, never both
		for (int drow = -1; drow <= 1; drow += 2) {
			//check down one and up one
			if (inRegion(map, row+drow, column, visited)) {
				getRegion(map, row+drow, column, visited, size);
			}
		}
		for (int dcol = -1; dcol <= 1; dcol += 2) {
			//then check left one and right one
			if (inRegion(map, row, column+dcol, visited)) {
				getRegion(map, row, column+dcol, visited, size);
			}
		}
	}
	
	public static boolean inRegion(int map[][], int row, int column, 
			boolean visited[][]) {
		
		return (row >= 0) && (row < map.length) && (column >= 0)
			&& (column < map[0].length) && (map[row][column] > 0)
			&& (visited[row][column] == false);
	}
}
