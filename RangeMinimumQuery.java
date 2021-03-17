
import java.util.List;
import java.util.ArrayList;

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


