public class RainWaterTrapping
{

   public int solve(int[] A)
   {
      int lptr = 0;
      int rptr = A.length - 1;

      int lMax = 0;
      int rMax = 0;
    
      int ans = 0;
     
      while(lptr <= rptr)
      {
         if(A[lptr] <= A[rptr])
         {
           if(A[lptr] >= lMax)
             lMax = A[lptr];
           else
             ans+= lMax - A[lptr];
           lptr++;
         }
         else
         {
            if(A[rptr] >= rMax)
             rMax = A[rptr];
            else
             ans+= rMax - A[rptr];
            rptr++;
         }
      }
      return ans;
   }

}
