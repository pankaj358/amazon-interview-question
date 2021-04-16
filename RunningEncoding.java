public class RunningEncoding
{
   public String encode(String input)
   {
      if(input == null || input.length() == 0) return "";

      char[] inputChars = input.toCharArray();

      StringBuilder sb = new StringBuilder();

      int count = 1;
      char prev = inputChars[0];

      for(int i = 1; i < inputChars.length; i++)
      {
         if(prev == inputChars[i]) count++;
         else
         {
           sb.append(count);
           sb.append(prev);
           prev = inputChars[i];
           count = 1;
         }
      }
      
      sb.append(count);
      sb.append(prev);
      return String.valueOf(sb);
   }

   public static void main(String[] args) 
   {
     RunningEncoding re = new RunningEncoding();
     String ans = re.encode("aaaabbccaaddeek");
     System.out.println(ans);
   }
}
