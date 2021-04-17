
import java.util.Map;
import java.util.HashMap;


public class LRU
{

  private Map<Integer, Node> map;
  private int capacity; 
  private Node start;
  private Node end;

  public LRU(int capacity)
  {
    this.capacity = capacity;
    map = new HashMap<>();
    start = new Node(0, 0);
    end = new Node(0, 0);
    start.next = end;
    end.prev = start;
  }  

  private void addNode(Node node)
  {
    node.next = start.next;
    start.next = node;
    node.prev = start;
    node.next.prev = node;
  }
 
  private void removeNode(Node node)
  {
    Node tmp = node.next;
    node.prev.next = tmp;
    tmp.prev = node.prev;
  }


  public int get(int key)
  {
     if(map.containsKey(key))
     {
       Node node = map.get(key);
       removeNode(node);
       addNode(node);
       return node.val;
     }

     return -1;
  }

  public void set(int key, int val)
  {
     Node node = new Node(key, val);
     if(map.containsKey(key))
     {
       Node temp = map.get(key);
       removeNode(temp);
       addNode(node);
     }
     else {
          if(capacity == map.size())
          {
            Node tmp = end.prev;
            removeNode(tmp);
            map.remove(tmp.key);             
          }
         addNode(node);
      }
    map.put(key, node);
  }  

}

class Node 
{
   int key;
   int val;
   Node next;
   Node prev;

   Node(int key, int val)
   {
     this.key = key;
     this.val = val;
   }
}
