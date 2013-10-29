/*
ID: jmslocu1
LANG: JAVA
TASK: sort3
*/
import java.io.*;
import java.util.*;

class sort3 {
	  public static void main (String [] args) throws IOException {
	    // Use BufferedReader rather than RandomAccessFile; it's much faster
	    BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
	                                                  // input file name goes above
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
	    int total = Integer.parseInt(f.readLine());
	    int[] vals = new int[total];
	    int numones = 0;
	    int numtwos = 0;
	    int numthreesbeforetwos = 0;
	    for (int i = 0; i < total; i++) {
	    	vals[i] = Integer.parseInt(f.readLine());
	    	if (vals[i] == 1) {
	    		numones++;
	    	} else if (vals[i] == 2) {
	    		numtwos++;
	    	} else if (vals[i] == 3 && numtwos == 0) {
	    		numthreesbeforetwos++;
	    	}
	    }
	    f.close();
	    int swaps = 0;
	    /*for (int i = total - 1; i >= 1; i--) {
	    	int numtoget;
	    	if (i >= numones + numtwos) {
	    		numtoget = 3;
	    	} else if (i >= numones) {
	    		numtoget = 2;
	    	} else {
	    		numtoget = 1;
	    	}
	    	if (vals[i] == numtoget) continue;
    		for (int j = 0; j < i; j++) {
    			if (vals[j] == 3) {
    				vals[j] = vals[i];
    				vals[i] = 3;
    				swaps++;
    				break;
    			} else if (vals[i] == 1 && vals[j] == 2) {
	    			vals[i] = 2;
	    			vals[j] = 1;
	    			swaps++;
	    			break;
	    		}
    			if (vals[j] == numtoget) {
    				System.out.println(Arrays.toString(vals));
    				vals[j] = vals[i];
    				vals[i] = numtoget;
    				System.out.println("swapping " + i + " and " + j + ", numtoget: " + numtoget);
    				swaps++;
    				break;
    			}
    		}
	    }*/
	    for (int i = total - 1; i >= 1; i--) {
	    	int numtoget;
	    	if (i >= numones + numtwos) {
	    		numtoget = 3;
	    	} else if (i >= numones) {
	    		numtoget = 2;
	    	} else {
	    		numtoget = 1;
	    	}
	    	if (vals[i] == numtoget) continue;
    		for (int j = 0; j < i; j++) {
    			int numtogetj;
    	    	if (j >= numones + numtwos) {
    	    		numtogetj = 3;
    	    	} else if (j >= numones) {
    	    		numtogetj = 2;
    	    	} else {
    	    		numtogetj = 1;
    	    	}
    	    	// try to do a swap that puts both in correct place
    	    	if(numtogetj == vals[i] && numtoget == vals[j] && numtogetj != vals[j]) {
    	    		vals[i] = numtoget;
    	    		vals[j] = numtogetj;
    	    		swaps++;
    	    		break;
    	    	}
    		}
    		if (vals[i] != numtoget) {
    			//couldn't do a swap that puts both in correct place, so put one in correct place
    			for (int j = 0; j < i; j++) {
    				if (vals[j] == numtoget) {
    					vals[j] = vals[i];
    					vals[i] = numtoget;
    					swaps++;
    					break;
    				}
    			}
    		}
	    }
	    /*int i;
	    for (i = 0; i < numones; i++) {
	    	if (vals[i] != 1) {
	    		swaps++;
	    	}
	    }
	    for(; i< numones + numtwos; i++) {
	    	if (vals[i] != 2) {
	    		swaps++;
	    	}
	    }
	    for (; i < total; i++) {
	    	if (vals[i] == 1 && numthreesbeforetwos == 0) {
	    		swaps += 2;
	    	} else if (vals[i] == 1){
	    		swaps++;
	    		numthreesbeforetwos--;
	    	} else if (vals[i] == 2) {
	    		swaps++;
	    	}
	    }*/
	    /*if (swaps %2 != 0) {
	    	System.out.println("swaps is not even!!!!: ");
	    } else {
	    	System.out.println("swaps/2 is: " + (swaps/2));
	    }*/
 	    System.out.println(Arrays.toString(vals));
	    out.println(swaps);
	    out.close();                                  // close the output file
	    System.exit(0);                               // don't omit this!
	  }
}