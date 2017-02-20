package algorithms;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int N;
	private int trials;
	private StdRandom random;
	private double [] resultsArray;
	
	public PercolationStats(int n, int trials) {
		this.N = n;
		this.trials = trials;
		
		resultsArray = new double [trials];
		
		if((N <= 0)||(trials <= 0)) {
			throw new java.lang.IllegalArgumentException("arguments must be greater than 0.");
		}
		
		for(int i = 0; i < trials; i++) {
			Percolation perc = new Percolation(N);
			//open cell until grid percolates
			while(!perc.percolates()){
				int row = StdRandom.uniform(1, N + 1);
				int col = StdRandom.uniform(1, N + 1);
					if(!perc.isOpen(row, col)){
						perc.open(row, col);
					}
			}
			System.out.println(perc.numberOfOpenSites());
			double threshold = (double) perc.numberOfOpenSites() / (N * N);
			resultsArray[i] = threshold;
			System.out.println(threshold);
		}
	}
	
	public double mean()  {
		return StdStats.mean(resultsArray);
	}
	
	public double stddev() {
		return StdStats.stddev(resultsArray);
	}
	
	public double confidenceLo()  {
		return mean() - ((1.96 * stddev())/Math.sqrt(trials));
	}
	
	public double confidenceHi() {
		return mean() + ((1.96 * stddev())/Math.sqrt(trials));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PercolationStats test = new PercolationStats (8, 10);
		System.out.println("sample's mean of percolation threshold =" + test.mean());
		System.out.println("sample's standard deviation of percolation threshold =" + test.stddev());
		System.out.println("sample's 95% confidence interval= " + "[" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
	}

}
