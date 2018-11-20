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
		//ArrayList<Integer> allSizes = new ArrayList<Integer>();
		
		//ArrayList<ArrayList<Airdrop>> connectedRegions = new ArrayList<ArrayList<Airdrop>>();
		
		//solution for unsorted
		
		/*ArrayList<Airdrop> unsortedAirdrops = new ArrayList<Airdrop>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (map[i][j] > 0 && visited[i][j] == false) {
					ArrayList<Airdrop> region = new ArrayList<Airdrop>();
					int[] size = {0};//wrapped in an array in order to pass by reference
					getRegion(map, i, j, visited, region, size);
					//allSizes.add(size[0]);
					Airdrop a = getAirdrop(region, size);
					unsortedAirdrops.add(a);
					//connectedRegions.add(region);
				}
			}
		}	

		return unsortedAirdrops;*/
		
		//solution for sorted
		
		ArrayList<Airdrop> sortedAirdrops = new ArrayList<Airdrop>();
		
		int sum = row + column - 2;
		for (int k = 0; k <= sum; k++) {
			int min_row;
			if ((k - column + 1) > 0) { 
				min_row = (k - column + 1);
			}
			else { 
				min_row = 0;
			}
			int max_row;
			if (k < (column - 1)) {
				max_row = k;
			} else {
				max_row = column - 1;
			}
			
			for (int i = min_row; i <= max_row; i++) {
				int j = k - i;
				if (map[i][j] > 0 && visited[i][j] == false){
					//ArrayList<Airdrop> region = new ArrayList<Airdrop>();
					Airdrop a = new Airdrop(i, j, 0);
					int[] size = {0};
					getRegion(map, i, j, visited, size);
					a.setSize(size[0]);
					//region.get(0).setSize(size[0]);
					//sortedAirdrops.add(region.get(0));
					sortedAirdrops.add(a);
				}
					
			}
			
		}
		
		return sortedAirdrops;
		
		
		
		//we now have ArrayLists of connected regions with airdrops, now have 
		//to pick the correct spot to drop each airdrop
		//ArrayList<Airdrop> unsortedAirdrops = new ArrayList<Airdrop>();
		/*for (int j = 0; j < connectedRegions.size(); j++) {
			ArrayList<Airdrop> list = connectedRegions.get(j);
			//start off with first airdrop being minimum
			int min = list.get(0).getColumn() + list.get(0).getRow();
			Airdrop min_airdrop = list.get(0);
			int min_row = list.get(0).getRow();
			
			for (int i = 1; i < list.size(); i++) {
				int row_col = list.get(i).getColumn() + list.get(i).getRow();
				//if row+col value is less than current min, update min
				//if equal, take lower row#
				if (row_col < min || 
						(row_col == min && list.get(i).getRow() < min_row)) {
					min = row_col;
					min_airdrop = list.get(i);
					min_row = list.get(i).getRow();
				}
			}
			min_airdrop.setSize(allSizes.get(j));
			unsortedAirdrops.add(min_airdrop);
		}*/
		
		//now the airdrops have to be sorted
	}
	
	public static Airdrop getAirdrop(ArrayList<Airdrop> region, int[] size) {
		int min = region.get(0).getColumn() + region.get(0).getRow();
		Airdrop min_airdrop = region.get(0);
		int min_row = region.get(0).getRow();
		for(int i = 1; i < region.size(); i++) {
			int row_col = region.get(i).getColumn() + region.get(i).getRow();
			int new_row = region.get(i).getRow();
			if (row_col < min || (row_col == min && new_row < min_row)) {
				min = row_col;
				min_airdrop = region.get(i);
				min_row = new_row;
			}
		}
		min_airdrop.setSize(size[0]);
		return min_airdrop;
	}
	
	public static void getRegion(int map[][], int row, int column, 
			boolean visited[][], int[] size) {
		
		visited[row][column] = true;
		size[0] += map[row][column];
		//Airdrop a = new Airdrop(row, column, 0);
		//region.add(a);
		
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
