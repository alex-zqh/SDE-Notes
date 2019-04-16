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

[394. Decode String (Medium)](https://leetcode.com/problems/decode-string/)

```html
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
```
```java
public class Solution_394 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(decodeString(s));
    }
    static String decodeString(String s){
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