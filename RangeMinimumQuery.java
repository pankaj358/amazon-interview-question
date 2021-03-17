
import java.util.List;
import java.util.ArrayList;

/**
*  Range Minimum Query
*  Problem Description
*
*  Given an integer array A of size N.
*  You have to perform two types of query, in each query you are given three integers x,y,z.
* 
*  If x = 0, then update A[y] = z.
*  If x = 1, then output the minimum element in the array A between index y and z inclusive.
*  Queries are denoted by a 2-D array B of size M x 3 where B[i][0] denotes x, B[i][1] denotes y, B[i][2] denotes z. 
*  
*  Problem Constraints
*  1 <= N, M <= 105
*   1 <= A[i] <= 109
*  If x = 0, 1<= y <= N and 1 <= z <= 109
*
*  If x = 1, 1<= y <= z <= N
*
*  Input Format
*   First argument is an integer array A of size N. 
*   Second argument is a 2-D array B of size M x 3 denoting queries.
*  
*  Output Format
*   Return an integer array denoting the output of each query where value of x is 1.
*
*  Example Input 
*   Input 1:
*   
*   A = [1, 4, 1]
*   B = [ 
*          [1, 1, 3],
*          [0, 1, 5],
*          [1, 1, 2]  
*        ]
*
*
*
*  Input 2 : 
*   A = [5, 4, 5, 7]
*   B = [
*         [1, 2, 4],
*         [0, 1, 2],
*         [1, 1, 4]
*      ]
*
*
*   Example Output 
*    Output 1:
*     [1, 4]
*    
*    Output 2:
*     [4, 2]
*
*   Example Explanation
*   Explanation 1:
*    For 1st query, the minimum element from range (1, 3) is 1.
*    For 2nd query, update A[1] = 5, now A = [5, 4, 1].
*    For 3rd query, the minimum element from range (1, 2) is 4.
*   
*   Explanation 2:
*    For 1st query, the minimum element from range (2, 4) is 4.
*    For 2nd query, update A[1] = 2, now A = [2, 4, 5, 7]. 
*    For 3rd query, the minimum element from range (1, 4) is 2.
*
*
*
*
*  @author Pankaj Tirpude [tirpudepankaj@gmail.com]
*
*  @notes  query TC 4logn ~ logn
*  
*  @ref  https://xiaoguan.gitbooks.io/leetcode/content/Algorithms/segment-tree-range-minimum-query.html
*
*/


public class RangeMinimumQuery
{
  
  private int[] A;
  private int[] tree;

  public void build(int []A)
  {
    this.A = A; 
    this.tree = new int[4 * A.length];
    build(0, 0, A.length - 1);
  }


 private void update(int idx, int start, int end, int pos, int val)
 {
   if(pos < start || pos > end) return;
   
   if(start == end )
   {
     tree[idx] = val;
     return;
   }

   int mid = start + (end - start) / 2;

   update(2 * idx + 1, start, mid, pos, val);
   update(2 * idx + 2, mid + 1, end, pos, val);
  
   tree[idx] = Math.min(tree[idx * 2 + 1], tree[idx * 2 + 2]);
 }

  private int query(int idx, int start, int end, int rStart, int rEnd)
  {
    // complete overlap
    if(rStart <= start && rEnd >= end)
     return tree[idx];
    
    // No Overlap 
    if(rStart > end || rEnd < start)  return Integer.MAX_VALUE;
   
    int mid = start + (end - start)  / 2;

    return Math.min(
      query(idx * 2 + 1, start, mid, rStart, rEnd),
      query(idx * 2 + 2, mid + 1, end, rStart, rEnd)
    );

  }

  private void build(int idx, int start, int end)
  {
     //base condition
     if(start == end)
     {
       tree[idx] = A[start];
       return;
     }

     int mid = start + (end - start) / 2;
    
     build(2 * idx + 1, start, mid);
     build(2 * idx + 2, mid + 1, end);
    
     tree[idx] = Math.min(tree[ idx * 2 + 1 ], tree[ idx * 2 + 2 ]);
  }

  public static void main(String[] args) {
     RangeMinimumQuery  rmq = new RangeMinimumQuery();
     System.out.println(" ***********rmq************* " );
    
     int[] A = {18, 10, 1, 20, 25, 4, 9, 13, 15, 6, 21,  };
      
     int[][]B = {
          {1, 8, 12},
          {0, 4, 7 },
          {1, 1, 3 },
          {1, 5, 11},
          {1, 3, 9},
          {1, 7, 12},
          {1, 7, 10},
          {0, 12, 20}
      };
    
     rmq.build(A);
     
     List<Integer> ans = new ArrayList<>();

     for(int i = 0; i < B.length; i++)
     {
        int x = B[i][0];
        int y = B[i][1];
        int z = B[i][2];
        
        if(x == 0)
        {
          rmq.update(0, 0, A.length - 1, y - 1, z);
        }
        else if(x == 1)
        {
           int t = rmq.query(0, 0, A.length - 1, y - 1, z - 1);
           ans.add(t);
        }
    }

    ans.stream().forEach(System.out::println);
      
  }
}


