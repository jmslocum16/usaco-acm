/*
ID: jmslocu1
LANG: JAVA
TASK: pprime
*/
import java.io.*;
import java.util.*;

class pprime {
	  public static void main (String [] args) throws IOException {
	    // Use BufferedReader rather than RandomAccessFile; it's much faster
	    BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
	                                                  // input file name goes above
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
	    // Use StringTokenizer vs. readLine/split -- lots faster
	    StringTokenizer st = new StringTokenizer(f.readLine());
							  // Get line, break into tokens
	    int i1 = Integer.parseInt(st.nextToken());    // first integer
	    int i2 = Integer.parseInt(st.nextToken());    // second integer
	    stuff(i1, i2, out);                         // output result
	    out.close();                                  // close the output file
	    System.exit(0);                               // don't omit this!
	  }
	  
  public static boolean[] sieve(int a, int b) {
	  boolean[] primez = new boolean[b+1];
	  for(int i = 0; i < primez.length; i++) {
		  primez[i] = true;
	  }
	  for(int i = 2; i < primez.length/2+1; i++) {
		  if (primez[i]) {
			  int j = 2*i;
			  while(j <= b) {
				  primez[j] = false;
				  j+=i;
			  }
		  }
	  }
	  
	  return primez;
  }
  
  public static boolean primeCheck(int a, int b, boolean[] primes, int check) {
	  //return check >= a && check <=b && primes[check];
	  return check >= a && check <=b && isPrime(check);
  }
  
  public static boolean isPrime(int num) {
	  for (int i = 2; i <= Math.sqrt(num); i++) {
		  if(num % i == 0)
			  return false;
	  }
	  return true;
  }
  
  public static void stuff(int a, int b, PrintWriter out) {
	  //boolean[] primes = sieve(a, b);
	  boolean[] primes = new boolean[1];
	  
	  for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
    	  if (primeCheck(a, b, primes, d1)) {
    		  out.println(d1);
    	  }
	  }
	  if (b<=1e1) return;
	  for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
    	  int palindrome = 10*d1 + d1;
    	  if (primeCheck(a, b, primes, palindrome)) {
    		  out.println(palindrome);
    	  }
	  }
	  if(b<=1e2) return;
	  //3
	  for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
	      for (int d2 = 0; d2 <= 9; d2++) {
        	  int palindrome = 100*d1 +10*d2 + d1;
        	  if (primeCheck(a, b, primes, palindrome)) {
        		  out.println(palindrome);
        	  }
	      }
	  }
	  if (b <= 1e3) return;
	  //4
	  for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
	      for (int d2 = 0; d2 <= 9; d2++) {
        	  int palindrome = 1000*d1 + 100*d2 + 10*d2 + d1;
        	  if (primeCheck(a, b, primes, palindrome)) {
        		  out.println(palindrome);
        	  }
	      }
	  }
	  if (b <= 1e4) return;
	  //5
	  for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
	      for (int d2 = 0; d2 <= 9; d2++) {
	          for (int d3 = 0; d3 <= 9; d3++) {
	        	  int palindrome = 10000*d1 + 1000*d2 +100*d3 + 10*d2 + d1;
	        	  if (primeCheck(a, b, primes, palindrome)) {
	        		  out.println(palindrome);
	        	  }
	          }
	      }
	  }
	  if (b <= 1e5) return;
	  //6
	  for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
	      for (int d2 = 0; d2 <= 9; d2++) {
	          for (int d3 = 0; d3 <= 9; d3++) {
	        	  int palindrome = 100000*d1 + 10000*d2 + 1000*d3 + 100*d3 + 10*d2 + d1;
	        	  if (primeCheck(a, b, primes, palindrome)) {
	        		  out.println(palindrome);
	        	  }
	          }
	      }
	  }
	  if (b <= 1e6) return;
	  //7
	  for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
	      for (int d2 = 0; d2 <= 9; d2++) {
	          for (int d3 = 0; d3 <= 9; d3++) {
	        	  for (int d4 = 0; d4 <=9; d4++) {
		        	  int palindrome = 1000000*d1 + 100000*d2 + 10000*d3 + 1000*d4+ 100*d3 + 10*d2 + d1;
		        	  if (primeCheck(a, b, primes, palindrome)) {
		        		  out.println(palindrome);
		        	  }
	        	  }
	          }
	      }
	  }
	  if(b <= 1e7) return;
	  //8
	  for (int d1 = 1; d1 <= 9; d1+=2) {	/* only odd; evens aren't so prime */
	      for (int d2 = 0; d2 <= 9; d2++) {
	          for (int d3 = 0; d3 <= 9; d3++) {
	        	  for (int d4 = 0; d4 <=9; d4++) {
		        	  int palindrome = 10000000*d1 + 1000000*d2 + 100000*d3 + + 10000*d4+ 1000*d4+ 100*d3 + 10*d2 + d1;
		        	  if (primeCheck(a, b, primes, palindrome)) {
		        		  out.println(palindrome);
		        	  }
	        	  }
	          }
	      }
	  }
  }
}