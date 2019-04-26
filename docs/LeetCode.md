[1. Two Sum (Easy)](https://leetcode.com/problems/two-sum/)  
1. 暴力双循环 i->(0~n-1)，j->(i+1~n-1)，O(n×(n−1))≈O(n^2 )  
2. 使用map循环一次  i->(0~n-1) target-nums[i] 是否在map key中 不在存入(nums[i],i) 在返回{map.get差值，i}

[2. Add Two Numbers (Medium)](https://leetcode.com/problems/add-two-numbers/)   
结果链表头设0  
中间链表=结果链表头  
加法器实现，每位输出用中间链表链表存，进位用int存储    
不为Null取value，为Null设为0，和加上进位，输出取除10余，进位除10（链表先设置next，然后等于next）  
不为Null的取next  
返回结果链表的next

[20. Valie Parentheses (Easy)](https://leetcode.com/problems/valid-parentheses/) 
1. 字典右对左，存左比较左
2. 字典左对右，存右比较右 

[24. Swap Nodes in Pairs (Medium)](https://leetcode.com/problems/swap-nodes-in-pairs/)
```html
Given 1->2->3->4, you should return the list as 2->1->4->3.
```
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while(pre.next!=null && pre.next.next!=null){
            ListNode l1 = pre.next;
            ListNode l2 = pre.next.next;
            l1.next = l2.next;
            l2.next = l1;
            pre.next = l2;
            pre = l1;
        }
        return node.next;
    }
}
```

[64. Minium Path Sum (Medium)](https://leetcode.com/problems/minimum-path-sum/)

```html
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
```
```java
public class Solution_64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int i=0,j=0;
        dp[0][0] = grid[0][0];
        for(j=1;j<n;j++){
            dp[0][j] = dp[0][j-1]+grid[0][j];
        }
        for(i=1;i<m;i++){
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for(i=1;i<m;i++){
            for(j=1;j<n;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
```
[72. Edit Distance (Medium)](https://leetcode.com/problems/edit-distance/)
```html
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
```
```html
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
```
```java
public class Solution_72 {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        int i=0,j=0;
        for(i=0;i<=m;i++){
            dp[i][0] = i;
        }
        for(j=1;i<=n;j++){
            dp[0][j] = j;
        }
        for(i=1;i<=m;i++){
            for(j=1;j<=n;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }
                else{
                    int delete = dp[i-1][j]+1;
                    int insert = dp[i][j-1]+1;
                    int replace = dp[i-1][j-1]+1;
                    int min = Math.min(delete,insert);
                    dp[i][j] = Math.min(min,replace);
                }
            }
        }
        return dp[m][n];
    }
}
```
[75. Sort Colors](https://leetcode.com/problems/sort-colors/)  
```html
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
```
```java
class Solution {
    public void sortColors(int[] nums) {
        int zero = -1, one = 0, two = nums.length;
        while (one < two) {
            if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else if (nums[one] == 2) {
                swap(nums, --two, one);
            } else {
                ++one;
            }
        }   
    }
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

[152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)
```html
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
```
```html
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
```
```java
public class Solution_152 {
    public int maxProduct(int[] nums) {
        int dp = 0;
        int i,maxm,minm;
        dp = maxm = minm = nums[0];
        for(i=1;i<nums.length;i++){
            if(nums[i]>=0){
               maxm = Math.max(maxm*nums[i],nums[i]);
               minm = Math.min(minm*nums[i],nums[i]);
            }
            else{
                int tmp;
                tmp = maxm;
                maxm = Math.max(minm*nums[i],nums[i]);
                minm = Math.min(maxm*nums[i],nums[i]);
            }
            dp = Math.max(dp,maxm);
        }
        return dp;
    }
}
```

[300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)
```html
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
```
```java
class Solution_300 {
    public int lengthOfLIS(int[] nums,int n) {
        if(nums == null || n ==0)
            return 0;
        int max = 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=1;i<n;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(nums[i] > nums[j] && dp[i]<dp[j]+1){
                    dp[i] = dp[j]+1;
                }
            }
            if(dp[i] > max){
                max = dp[i];
            }
        }
        return max;
    }

    public int[] findLIS(int[] nums,int n){
        ArrayList<Integer> list = new ArrayList<>();
        int[] dp = new int[n];
        int max = 1;
        dp[0] = 1;
        for(int i=1;i<n;i++) {
            ArrayList<Integer> now = new ArrayList<>();
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    now.add(nums[j]);
                }
            }
            now.add(nums[i]);
            if(dp[i]>max){
                list = now;
            }
        }
        int[] res = new int[list.size()];
        for(int i=0;i<list.size();i++){
            res[i] = list.get(i);
            System.out.print(res[i]+" ");
        }
        System.out.println();
        return res;
    }
}
```

[394. Decode String (Medium)](https://leetcode.com/problems/decode-string/)

```html
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
```
```java
public class Solution_394 {
    public String decodeString(String s){
        Stack<Character> stack = new Stack<>();
        StringBuilder res = new  StringBuilder();
        StringBuilder repeat;
        for(char c:s.toCharArray()){
            if(c == ']'){
                repeat = new StringBuilder();
                while(stack.peek() != '['){
                   repeat.append(stack.pop());
                }
                stack.pop();
                StringBuilder count = new StringBuilder();
                while (!stack.isEmpty() && (stack.peek() >= '0' && stack.peek() <= '9')) {
                    count.append(stack.pop());
                }
                Integer num = Integer.parseInt(count.reverse().toString());
                String str = repeat.reverse().toString();
                String strs = "";
                for(int i=0;i<num;i++){
                    strs+=str;
                }
                for(char ch:strs.toCharArray()){
                    stack.push(ch);
                }
            }else{
                stack.push(c);
            }
        }
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
}
```