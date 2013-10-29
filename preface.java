/*
ID: jmslocu1
LANG: JAVA
TASK: preface
*/
import java.io.*;
import java.util.*;

class preface {
	static HashMap<Character, Integer>[] smallers;
	static HashMap<Integer, Character> charMap;
	  public static void main (String [] args) throws IOException {
	    // Use BufferedReader rather than RandomAccessFile; it's much faster
	    BufferedReader f = new BufferedReader(new FileReader("preface.in"));
	    
	    smallers = new HashMap[4];
	    smallers[0] = new HashMap<Character, Integer>();
	    charMap = new HashMap<Integer, Character>();
	    charMap.put(1, 'I');
	    charMap.put(5, 'V');
	    charMap.put(10, 'X');
	    charMap.put(50, 'L');
	    charMap.put(100, 'C');
	    charMap.put(500, 'D');
	    charMap.put(1000, 'M');
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
	    int num = Integer.parseInt(f.readLine());
	    f.close();                        
	    out.close();                                  // close the output file
	    HashMap<Character, Integer> result = getNums(num);
	    System.exit(0);                               // don't omit this!
	  }
	  
	  private static void getNums(int num) {
		  
		  int digits = 0;
		  int num2 = num;
		  while(num2 >= 10) {
			  num2/=10;
			  digits++;
		  }
		  int i = digits;
		  int topdigit = num2;
		  while(digits > 0) {
			  num2*=10;
			  digits--;
		  }
		  HashMap<Character, Integer> pow10nums = getPowOf10Nums(num2);
		  HashMap<Character, Integer> total = new HashMap<Character, Integer>(pow10nums);
		  if (topdi)
	  }
	  
	  private HashMap<Character, Integer> getPowOf10Nums(int num) {
		  if (num == 1) {
			  return smallers[0];
		  }
		  HashMap<Character, Integer> prev = getPowOf10Nums(num/10);
		  
	  }
	  
	  private void addBtoA(HashMap<Character, Integer> A, HashMap<Character, Integer> B, int multiplier) {
		  for (Character c: B.keySet()) {
			  if(A.get(c) == null) {
				  A.put(c, 0);
			  }
			  A.put(c, A.get(c) + multiplier * (B.get(c)));
		  }
	  }
	  
	  private void addCharToA(HashMap<Character, Integer> A, char c, int number) {
		  if (A.get(c) == null) {
			  A.put(c, 0);
		  }
		  A.put(c, A.get(c) + number);
	  }
	  
}