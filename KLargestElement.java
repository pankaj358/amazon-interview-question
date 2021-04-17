/**
*  
*
*  https://practice.geeksforgeeks.org/problems/k-largest-elements3736/1#
*
*  Complete the function kLargest() that takes the array, N and K as input parameters
*  and returns a list of k largest element in descending order. 
*  Expected Time Complexity: O(N log K)
*  Expected Auxiliary Space: O(K)
*
*
*
*/


import java.util.*;

public class KLargestElement{

  public ArrayList<Integer> kLargestElement(int[] A, int k)
  {
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      
      for(int i = 0; i < A.length; i++)
      {
          if(pq.size() == k && pq.peek() < A[i])
          {
             pq.poll();
             pq.add(A[i]);
          }
          else if( pq.size() < k)
          {
            pq.add(A[i]);
          }
      }

     ArrayList<Integer> ans = new ArrayList<>();
  
     while(!pq.isEmpty()) 
       ans.add(pq.poll());

    Collections.sort(ans, Collections.reverseOrder());

    return ans;
     
  }

}
