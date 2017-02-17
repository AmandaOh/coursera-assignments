package algorithms;
import java.util.Arrays;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	public final int N;
	private WeightedQuickUnionUF uf;
	private static final boolean open = true;
	private static final boolean blocked = false;
	private boolean[] grid;
	private int gridSize;
	private int gridIndex;
	private int topSite;
	private int bottomSite;
	private int openSitesCount;

	public Percolation(int n) {
		N = n;
		gridSize = n * n;
		uf = new WeightedQuickUnionUF(n*n + 2);
		grid= new boolean[gridSize + 2];
		Arrays.fill(grid, blocked);
		topSite = gridSize;
		bottomSite = gridSize + 1;
	}
	
	private boolean withinBounds(int row, int col) {
		int givenRow = row;
		int givenCol = col;
		if (( 1 <= givenRow && givenRow <= N ) && ( 1 <= givenCol && givenCol <= N)) {
			return  true;
		} else {
			throw new IndexOutOfBoundsException("Row and column indices must be between 1 and " + N);
		}
	}
	
	private int findIndex(int row, int col) {
		int gridRow = row - 1;
		int gridCol = col - 1;
		return (gridRow * N) + (gridCol);
	}
	
	private void formUnion(int row, int col, int side){
		gridIndex = findIndex(row, col);
		switch (side) {
			//union with top neighboring cell
			case 0: 
				if(row >= 2){
					if (isOpen(row - 1, col)) {
						uf.union(gridIndex, findIndex(row - 1, col));
						System.out.println("union with top cell");
					}
				}
				break;
				//union with right neighboring cell
			case 1:
				if(col <= (N - 1)){
					if (isOpen(row, col + 1)){
						uf.union(gridIndex, findIndex(row, col + 1));
						System.out.println("union with right cell");
					}
				}
				break;
			//union with bottom neighboring cell;
			case 2:
				if(row <= (N - 1)){
					if (isOpen(row + 1, col)) {
						uf.union(gridIndex, findIndex(row + 1, col));
						System.out.println("union with bottom cell");
					}
				}
				break;
			//union with left neighboring cell;
			case 3:
				if(col >= 2){
					if (isOpen(row, col - 1)){
						uf.union(gridIndex, findIndex(row, col - 1));
						System.out.println("union with left cell");
					}
				}
				break;
		}
	}
	
	public void open(int row, int col) {
		int givenRow = row;
		int givenCol = col;
		withinBounds(givenRow, givenCol);
		// row and column indices are integers between 1 and n
		gridIndex = findIndex(givenRow, givenCol);
		if (grid[gridIndex] == open) {
			return;
		} else {
			grid[gridIndex] = open;
			openSitesCount += 1;
			//union with virtual top and bottom node for top and bottom rows
			if (givenRow == 1) {
				uf.union(gridIndex, topSite);
			} else if (givenRow == N) {
				uf.union(gridIndex, bottomSite);
			}
			//union with neighboring up, right, bottom, left opened cells
			for (int i = 0; i < 4; i++){
				formUnion(givenRow, givenCol, i);
			}
		}
	}
	
	public boolean isOpen(int row, int col) {
		int givenRow = row;
		int givenCol = col;
		withinBounds(givenRow, givenCol);
		return grid[findIndex(givenRow, givenCol)];
	}
	
	public boolean isFull(int row, int col) {
		withinBounds(row, col);
		int gridIndex = findIndex(row, col);
		return uf.connected(gridIndex, topSite);
	}
	
	public int numberOfOpenSites() {
		return openSitesCount;
	}
	
	public boolean percolates() {
		return uf.connected(bottomSite, topSite);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int gridSize = 5;
		
		Percolation testGrid = new Percolation(gridSize);
		testGrid.open(1, 2);
		testGrid.open(2, 3);
		testGrid.open(3, 2);
		testGrid.open(2, 1);
		testGrid.open (2, 2);
		testGrid.open(4, 3);
		testGrid.open(5, 3);
		testGrid.open(3, 3);
		System.out.println(testGrid.isFull(2, 2));
		System.out.println(testGrid.numberOfOpenSites());
		System.out.println(testGrid.percolates());
	}

}
