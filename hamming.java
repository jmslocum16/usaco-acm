/*
ID: jmslocu1
LANG: JAVA
TASK: hamming
*/
import java.io.*;
import java.util.*;

class hamming {
	static boolean[] sol;
	  public static void main (String [] args) throws IOException {
	    // Use BufferedReader rather than RandomAccessFile; it's much faster
	    BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
	                                                  // input file name goes above
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
	    // Use StringTokenizer vs. readLine/split -- lots faster
	    StringTokenizer st = new StringTokenizer(f.readLine());
							  // Get line, break into tokens
	    int N = Integer.parseInt(st.nextToken());    // first integer
	    int B = Integer.parseInt(st.nextToken());    // second integer
	    int D = Integer.parseInt(st.nextToken());
	    f.close();
	    //ohgod
	    sol = null;
	    boolean[] cursol = new boolean[2<<B];
	    perm(0, cursol, 0, N, B, D);
	    //System.out.println(Arrays.toString(sol));
	    int index = 0;
	    for (int i = 0; i < N; i++) {
	    	if (i % 10 != 0) {
	    		out.print(" ");
	    	}
	    	while (!sol[index]) index++;
	    	out.print(index);
	    	System.out.print(" " + index);
	    	index++;
	    	if (i % 10 == 9 && i != N - 1) {
	    		out.println();
	    	}
	    }
	    out.println();
	    out.close();                                  // close the output file
	    System.exit(0);                               // don't omit this!
	  }
	  
	  private static void perm(int cur, boolean[] cursol, int curnum, int N, int B, int D) {
		  if (cur == 127) {
			  System.out.print("");
		  }
		  if (sol != null || cur >= (2<<B)) {
			  return;
		  }
		  cursol[cur] = true;
		  //we found it
		  if (curnum == N - 1) {
			  sol = cursol;
			  return;
		  }
		  for (int i = cur + 1; i < (2<<B); i++) {
			  boolean oneofus = true;
			  for (int j = 0; j < i; j++) {
				  if (cursol[j] && numBitsDifferent(j, i) < D) {
					  oneofus = false;
					  break;
				  }
			  }
			  if (oneofus) {
				  perm(i, cursol, curnum + 1, N, B, D);
			  }
			  if (sol != null) {
				  return;
			  }
		  }
		  cursol[cur] = false;
		  perm(cur + 1, cursol, curnum, N, B, D);
	  }
	  
	  private static int numBitsDifferent(int a, int b) {
		  int c = a ^ b;
		  int total = 0;
		  while (c > 0 ) {
			  if ((c & 1) > 0) {
				  total++;
			  }
			  c>>=1;
		  }
		  return total;
	  }
}