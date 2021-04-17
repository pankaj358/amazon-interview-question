
/**
*
*  https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
*
*
*
*
*/



import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FindFirstNegativeNumber
{

  public long[] solve(long[] A, int K)
  {
     Queue<Integer> queue = new LinkedList<>();
     List<Long> ans = new ArrayList<>();

     for(int i = 0; i < K; i++)
     {
        if(A[i] < 0)
         queue.add(i);
     }

     int i = K;

     for(; i < A.length; i++)
     {
        if(queue.isEmpty() == false)
         ans.add(A[queue.peek()]);
        else ans.add(0L);

        while(queue.isEmpty() == false && queue.peek() < i - K + 1)
         queue.poll();
   
        if(A[i] < 0) 
          queue.add(i);
     }

    if(queue.isEmpty() == false)
     ans.add(A[queue.peek()]);
    else ans.add(0L);

    return ans.stream().mapToLong(Long::longValue).toArray();
     
  }  

}
