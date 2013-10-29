/*
ID: jmslocu1
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.*;

class castle {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("castle.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
						  // Get line, break into tokens
    int m = Integer.parseInt(st.nextToken());    // first integer
    int n = Integer.parseInt(st.nextToken());    // second integer
    int[][] castle = new int[m][n];
    int[][] roomnumber = new int[m][n];
    for (int y = 0; y < n; y++) {
    	st = new StringTokenizer(f.readLine());
    	for (int x = 0; x < m; x++) {
    		castle[x][y] = Integer.parseInt(st.nextToken());
    		roomnumber[x][y] = 0;
    	}
    }
    int room = 0;
    for (int x = 0; x <  m; x++) {
    	for (int y = 0; y < n; y++) {
    		if (roomnumber[x][y] == 0) {
    			floodFillRoom(x, y, ++room, castle, roomnumber);
    		}
    	}
    }
    for (int j = 0; j < n; j++) {
    	for (int i = 0; i < m; i++) {
    		System.out.print(roomnumber[i][j] + " ");
    	}
    	System.out.println();
    }
    
    out.println(room);
    int maxRoomSize = 0;
    int size;
    HashMap<Integer, Integer> roomSize = new HashMap<Integer, Integer>();
    for (int x = 0; x < m; x++) {
    	for (int y = 0; y < n; y++) {
    		int curroom = roomnumber[x][y];
    		if (!roomSize.containsKey(curroom)) {
    			size = 1;
    		} else {
    			size = roomSize.get(curroom)  + 1;
    		}
    		roomSize.put(curroom, size);
    		maxRoomSize = Math.max(maxRoomSize, size);
    	}
    }
    for (Integer i: roomSize.keySet()) {
    	System.out.printf("%d: %d\n", i, roomSize.get(i));
    }
    out.println(maxRoomSize);
    int maxCombinedSize = 0;
    int bestWallX = -1;
    int bestWallY = -1;
    String bestWallDir = null;
    for (int x = 0; x < m; x++) {
    	for (int y = n - 1; y >=0; y--) {
    	//for (int y = 0; y < n; y++) {
    		if (hasNorthWall(x, y, castle)) {
    			int room1 = roomnumber[x][y];
    			int room2 = roomnumber[x][y-1];
    			int combinedsize = roomSize.get(room1) + roomSize.get(room2);
    			if (room1 != room2 && combinedsize > maxCombinedSize) {
    				maxCombinedSize = combinedsize;
    				bestWallX = x;
    				bestWallY = y;
    				bestWallDir = "N";
    			}
    		}
    		if (hasEastWall(x, y, castle)) {
    			int room1 = roomnumber[x][y];
    			int room2 = roomnumber[x+1][y];
    			int combinedsize = roomSize.get(room1) + roomSize.get(room2);
    			if (room1 != room2 && combinedsize > maxCombinedSize) {
    				maxCombinedSize = combinedsize;
    				bestWallX = x;
    				bestWallY = y;
    				bestWallDir = "E";
    			}
    		}
    	}
    }
    out.println(maxCombinedSize);
    out.printf("%d %d %s\n", bestWallY + 1, bestWallX + 1, bestWallDir);
    out.close();                                  // close the output file
    System.exit(0);                               // don't omit this!
  }
  
  private static void floodFillRoom(int x, int y, int room, int[][] castle, int[][] roomnumber) {
	  roomnumber[x][y] = room;
	  if (!hasNorthWall(x, y, castle) && y != 0 && roomnumber[x][y - 1] == 0) {
		  floodFillRoom(x, y-1, room, castle, roomnumber);
	  }
	  if (!hasWestWall(x, y, castle) && x != 0 && roomnumber[x - 1][y] == 0) {
		  floodFillRoom(x - 1, y, room, castle, roomnumber);
	  }
	  if (!hasEastWall(x, y, castle) && x != roomnumber.length - 1 && roomnumber[x + 1][y] == 0) {
		  floodFillRoom(x + 1, y, room, castle, roomnumber);
	  }
	  if (!hasSouthWall(x, y, castle) && y != roomnumber[x].length - 1 && roomnumber[x][y + 1] == 0) {
		  floodFillRoom(x, y+1, room, castle, roomnumber);
	  }
  }
  
  private static boolean hasWestWall(int x, int y, int[][] castle) {
	  if (x == 0) return false;
	  return (castle[x][y] & 1) == 1;
  }
  
  private static boolean hasNorthWall(int x, int y, int[][] castle) {
	  if (y == 0) return false;
	  return (castle[x][y] & 2) == 2;
  }
  
  private static boolean hasEastWall(int x, int y, int[][] castle) {
	  if (x == castle.length - 1) return false;
	  return (castle[x][y] & 4) == 4;
  }
  
  private static boolean hasSouthWall(int x, int y, int[][] castle) {
	  if (y == castle[x].length) return false;
	  return (castle[x][y] & 8) == 8;
  }
}