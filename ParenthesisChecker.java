
/**
*
*  https://leetcode.com/problems/valid-parentheses/solution/
*
*  https://practice.geeksforgeeks.org/problems/parenthesis-checker2744/1
*
*
*/


import java.util.Stack;

public class ParenthesisChecker
{

  public boolean solve(String str)
  {
     Stack<Character> stack = new Stack<>();
     int len = str.length();
     
     for(int i = 0; i < len; i++)
     {
        char ch = str.charAt(i);
        if(ch == '{' || ch == '(' || ch == '[')
        {
          stack.push(ch);
        }
        else if(stack.isEmpty() == false)
        {
          if((stack.peek() == '{' && ch == '}') || ( stack.peek() == '(' && ch == ')') 
             || (stack.peek() == '[' && ch == ']') )
            {
               stack.pop();
            }
           else 
           {
              return false;
           }
        }
        else
        {
           return false;
        }
     }
    
     return stack.isEmpty();

  }

}
