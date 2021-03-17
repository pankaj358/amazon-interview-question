
/**
*  Binary updates
*  Problem Description
*  Given an integer A denoting the size of the array consisting all ones.
*  You are given Q queries, for each query there are two integer x and y:
*  If x is 0, then update the value of array at index y to 0.
*  If x is 1, then output the index of yth one in the array. If there is no such index then output -1. 
*  NOTE 1: There will atleast 1 query where value of x is 1.
*
*
*   Problem Constraints
*      1 <= A, Q <= 105
*
*      0 <= x <= 1
*
*      1 <= y <= A
*
*   Input Format
*   
*   First argument is an integer A denoting the size of array. 
*   Second argument is a 2-D array B of size Q x 2 where B[i][0] denotes x and B[i][1] denotes y.
*   
*   Output Format
*    Return an integer array denoting the output of each query where x is 1.
*
*   Example Input
*   Input 1: 
*   
*   A = 4
*   B = [ [1, 2],
*         [0, 2], 
*         [1, 4] ]
*   
*
*
*   Input 2:
*   
*   A = 5
*   B = [ [0, 3],
*         [1, 4],
*         [0, 3],
*         [1, 5] ]
*
*
*   Example Output
*   Output 1:
*   [2, -1]
*   
*   Output 2:
*   [5, -1]
*   
*   Example Explanation
*   Explanation 1:
*   Initially array = [1, 1, 1, 1]. For first query 2nd one is at index 2.
*   After Second query array becomes [1, 0, 1, 1].
*   For third query there is no 4th one.
*   
*   Explanation 2:
*   Initially array = [1, 1, 1, 1, 1]. After first query array becomes [1, 1, 0, 1, 1]. 
*   For second query 4th one is at index 5.
*   After third query array remains same [1, 1, 0, 1, 1].
*   For fourth query there is no 5th one.
*
*/


import java.util.Arrays;

public class BinaryUpdates
{

  private Pair[] tree;
  private int[] A;

  public void build(int A[])
  {
     this.A = A;
     this.tree = new Pair[4 * A.length];
     build(0, 0, A.length - 1);
  }


  private void build(int idx, int start, int end)
  {
     if(start == end)
     {
     	Pair p = new Pair(start, A[start]);
     	tree[idx] = p;
     	return;
     }

     int mid = start + (end - start) / 2;

     build(2 * idx + 1, start, mid);
     build(2 * idx + 2, mid + 1, end);

     tree[idx] = new Pair(-1, tree[2 * idx + 1].count + tree[2 * idx + 2].count);

  }


  private void update(int idx, int start, int end, int pos, int val)
  {
  	if(pos < start || pos > end) return;

  	if(start == end)
  	{
  		Pair p = new Pair(pos, val);
  		tree[idx] = p;
  		return;
  	}

    int mid = start + (end - start) / 2;

    update(2 * idx + 1, start, mid, pos, val);
    update(2 * idx + 2, mid + 1, end, pos, val);

    tree[idx] = new Pair(-1, tree[2 * idx + 1].count + tree[2 * idx + 2].count);
     

  }


  private int query(int idx, int count)
  {
  	if(tree[idx].count < count) return -1;

  	if(tree[idx].count == 1 && tree[idx].index != -1)
  		 return tree[idx].index + 1;
      
    int lc = 2 * idx + 1;
    int rc = 2 * idx + 2;

    if(tree[lc].count >= count)
      return query(lc, count);

    return query(rc, count - tree[lc].count);  

  }
  

   public static void main(String[] args) {
        BinaryUpdates binaryUpdates = new BinaryUpdates();

        int[] A = new int[13];

        Arrays.fill(A, 1);

        int[][] B = {
                {0, 7},
                {1, 8},
                {0, 5},
                {0, 12},
                {1, 1},
                {1, 6},
                {1, 11},
                {1, 9},
                {1, 3}
        };

        binaryUpdates.build(A);

        for (int i = 0; i < B.length; i++) {
            int x = B[i][0];
            int y = B[i][1];

            if (x == 0) {
                binaryUpdates.update(0, 0, A.length - 1, y - 1, 0);
            } else {
                int t = binaryUpdates.query(0, y);
                System.out.println(t);
            }
        }

    }



  

 private class Pair {

   public int index;
   public int count;

   public Pair(int index, int count){
   	  this.index = index;
   	  this.count = count;
   }
    
  }  

}
