/*
ID: jmslocu1
LANG: JAVA
TASK: holstein
*/
import java.io.*;
import java.util.*;

class holstein {
	static int[] vitreq;
	static int[][] feedvitnums;
	static boolean[] bestfeedcomb;
	static int bestFeedNum;
	  public static void main (String [] args) throws IOException {
	    // Use BufferedReader rather than RandomAccessFile; it's much faster
	    BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
	                                                  // input file name goes above
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
	    // Use StringTokenizer vs. readLine/split -- lots faster
	    int numvits = Integer.parseInt(f.readLine());
	    StringTokenizer st = new StringTokenizer(f.readLine());
							  // Get line, break into tokens
	    vitreq = new int[numvits];
	    for (int i = 0; i < numvits; i++) {
	    	vitreq[i] = Integer.parseInt(st.nextToken());
	    }
	    int numfeeds = Integer.parseInt(f.readLine());
	    // note: these are 0-indexed, need to increment on output 
	    feedvitnums = new int[numfeeds][numvits];
	    for (int i = 0; i < numfeeds;i++) {
	    	st = new StringTokenizer(f.readLine());
	    	for (int j = 0; j < numvits; j++) {
	    		feedvitnums[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    }
	    bestFeedNum = Integer.MAX_VALUE;
	    int[] currentvits = new int[numvits];
	    boolean[] curfeedcomb = new boolean[numfeeds];
	    permFeeds(0, 0, currentvits, curfeedcomb);
	    out.print(bestFeedNum);
	    for (int i = 0; i < bestfeedcomb.length; i++) {
	    	if (bestfeedcomb[i]) {
	    		out.printf(" %d", i + 1);
	    	}
	    }
	    out.println();
	    f.close();
	    out.close();                                  // close the output file
	    System.exit(0);                               // don't omit this!
	  }
	  
	  static void permFeeds(int curfeed, int curfeednum, int[] curvits, boolean[] curfeedcomb) {
		  if (curfeednum < bestFeedNum && enoughVits(curvits)) {
			  bestFeedNum = curfeednum;
			  bestfeedcomb = Arrays.copyOf(curfeedcomb, curfeedcomb.length);
			  return;
		  } else if (curfeednum >= bestFeedNum || curfeed >= curfeedcomb.length) {
			  return;
		  }
		  int[] oldvits = Arrays.copyOf(curvits, curvits.length);
		  for (int i = 0; i < curvits.length; i++) {
			  curvits[i] += feedvitnums[curfeed][i];
		  }
		  curfeedcomb[curfeed] = true;
		  permFeeds(curfeed+1, curfeednum + 1, curvits, curfeedcomb);
		  curfeedcomb[curfeed] = false;
		  permFeeds(curfeed + 1, curfeednum, oldvits, curfeedcomb);
		  
	  }
	  
	  static boolean enoughVits(int[] vits) {
		  for (int i = 0; i < vits.length; i++) {
			  if (vits[i] < vitreq[i]) return false;
		  }
		  return true;
	  }
	  
}
	