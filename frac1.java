/*
ID: jmslocu1
LANG: JAVA
TASK: frac1
*/
import java.io.*;
import java.util.*;

class frac1 {
	static HashMap<String, Double> memoizer;
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
    int n = Integer.parseInt(f.readLine());
    HashSet<String> allFracs = new HashSet<String>();
    memoizer = new HashMap<String, Double>();
    allFracs.add("0/1");
    allFracs.add("1/1");
    for (int numerator = 1; numerator <= n; numerator++) {
    	for (int denominator = numerator + 1; denominator <=n; denominator++) {
    		allFracs.add(getReducedFrac(numerator, denominator));
    	}
    }
    ArrayList<String> results = new ArrayList<String>();
    results.addAll(allFracs);
    Collections.sort(results, new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			double val1 = getVal(o1);
			double val2 = getVal(o2);
			if (val1 < val2) return -1;
			else return 1;
		}
    	
    });
    for (String s: results) {
    	out.println(s);
    }
    out.close();                                  // close the output file
    System.exit(0);
  }
  
  private static String getReducedFrac(int num, int den) {
	  int gcd = getGCD(num, den);
	  num/=gcd;
	  den/=gcd;
	  StringBuilder sb = new StringBuilder();
	  sb.append(num);
	  sb.append('/');
	  sb.append(den);
	  return sb.toString();
  }
  
  private static int getGCD(int a, int b) {
	  if (b == 0) {
		  return a;
	  } else {
		  return getGCD(b, a % b);
	  }
  }
  
  private static double getVal(String frac) {
	  if (memoizer.containsKey(frac)) {
		  return memoizer.get(frac);
	  }
	  int indexOfDivide = frac.indexOf('/');
	  double result = 1.0*Integer.parseInt(frac.substring(0, indexOfDivide)) / Integer.parseInt(frac.substring(indexOfDivide+1));
	  memoizer.put(frac, result);
	  return result;
  }
}