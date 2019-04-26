[从尾到头打印链表](https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)  
输入一个链表，按链表值从尾到头的顺序返回一个ArrayList
```java
/**
*    public class ListNode {
*        int val;
*        ListNode next = null;
*
*        ListNode(int val) {
*            this.val = val;
*        }
*    }
*
*/
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode head = new ListNode(-1);
        while(listNode!=null){
            ListNode mid = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode = mid;
        }
        ArrayList<Integer> output = new ArrayList<>();
        head = head.next;
        while (head!=null){
            output.add(head.val);
            head = head.next;
        }
        return output;
    }
}
```
