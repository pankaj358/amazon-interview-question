
import java.util.Stack;

public class MinStack
{

 private Stack<Integer> stack = new Stack<>();
 private Stack<Integer> minStack = new Stack<>();

 public void push(int val)
 {
    stack.push(val);
    if(minStack.isEmpty())
      minStack.push(val);
    else if(minStack.peek() >= val)
      {
        minStack.push(val);
      }
 }

 public void pop()
 {
    System.out.println("stack top " + stack.peek() + " minstack top" + minStack.peek());
    if(stack.peek().equals(minStack.peek())) minStack.pop();
    stack.pop();
 }

 public int getTop()
 {
    return stack.peek();
 }

 public int getMin()
 {
    return minStack.peek();
 }


 public static void main(String[] args) 
 {
   MinStack minStack = new MinStack();
   
   minStack.push(512);
   minStack.push(-1024);
   minStack.push(-1024);
   minStack.push(512);
  
   minStack.pop();
   System.out.println(minStack.getMin());
   minStack.pop();
   System.out.println(minStack.getMin());
   minStack.pop();
   System.out.println(minStack.getMin());

 }

}
