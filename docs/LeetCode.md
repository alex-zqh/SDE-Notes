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