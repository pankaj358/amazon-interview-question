/**
*
*
*  https://practice.geeksforgeeks.org/problems/peak-element/1
*  
*  https://leetcode.com/problems/find-peak-element/solution/
*
*/

public class FindPeakElement
{

  public int solve(int []A)
  {
    int l = 0;
    int r = A.length - 1;

    while(l < r)
    {
       int mid = (l  + r) / 2;
       
       if(A[mid] > A[mid + 1])
       {
         r = mid;
       }
       else
       {
         l = mid + 1;
       }
    }

    return l;
  }
}
