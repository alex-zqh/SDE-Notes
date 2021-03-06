## **1.硬币找零**
使用m种硬币，凑某个数量最少硬币。
```python
//sum是对应数量
//a是m种硬币的列表
dp = []
for i in range(sum+1):
    dp.append(i)
for i in range(1,sum+1):
    for j in range(m):
        if i>a[j] and dp[i-a[j]]+1<dp[i]:
            dp[i] = dp[i-a[j]]+1
```
## 1.1.走方格
m&times;n大小的格子，从左上到右下。
1. 每个格子都有一个值，问走路的值最大/最小。  
2. 没有值，问多少种走法。 
 
**解题思路**  
先计算边界值（0行0列），情况1是值叠加（由于只能向右或者向下走），情况2都是1。后用动态规划递推公式，**dp[i][j]=Math.max/Math.min (dp[i-1][j],dp[i][j-1])**，最后返回dp[m-1][n-1].
```python
dp = [[0] * n for i in range(m)]
dp[0][0] = grid[0][0]
for i in range(1,m):
    dp[i][0] = dp[i-1][0]+grid[i][0]
for j in range(1,n):
    dp[0][j] = dp[0][j-1]+grid[0][j]
for i in range(1,m):
    for j in range(1,n):
        dp[i][j] = max/min(dp[i-1][j],dp[i][j-1])+grid[i][j]
return dp[m-1][n-1]
```
LeetCode: [64. Minium Path Sum (Medium)](https://leetcode.com/problems/minimum-path-sum/)  
牛客网: [方格走法](https://www.nowcoder.com/questionTerminal/79b289947d854a759525dd937aa14762)  

## **2.字符串相似度/编辑距离 (edit distance)**
对于序列S和T，它们之间距离定义为：对二者其一进行几次以下的操作(1)删去一个字符；(2)插入一个字符；(3)改变一个字符。每进行一次操作，计数增加1。将S和T变为同一个字符串的最小计数即为它们的距离。给出相应算法。
```java
int[][] dp = new int[m+1][n+1]
int i=0,j=0;
for(i=0;i<=m;i++)
    dp[i][0] = i;
for(j=0;j<=n;j++)
    dp[0][j] = j;
for(i=1;i<m;i++){
    for(j=1;j<=n;j++){
        if(word1.charAt(i-1) == word2.charAt(j-1)){
            dp[i][j] = dp[i-1][j-1];
        }
        else{
            int replace = dp[i-1][j-1]+1;
            int delete = dp[i-1][j]+1;
            int insert = dp[i][j-1]+1;
            int min = Math.min(relpace,delete);
            dp[i][j] = Math.min(min,insert);
        }
    }
}
return dp[m][n];
```
LeetCode: [72. Edit Distance (Hard)](https://leetcode.com/problems/edit-distance/)

## **3.最长公共子序列 (Longest Common Substring)**
```java
int[][] dp = new int[m+1][n+1]
StringBuilder sb = new StringBuilder();
int i=0,j=0;
for(i=0;i<=m;i++)
    dp[i][0] = 0;
for(j=0;j<=n;j++)
    dp[0][j] = 0;
for(i=1;i<m;i++){
    for(j=1;j<=n;j++){
        if(word1.charAt(i-1) == word2.charAt(j-1)){
            dp[i][j] = dp[i-1][j-1]+1;
            sb.add(word1.charAt(i-1));
        }
        else{
            dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
        }
    }
}
return dp[m][n];
```
## **4.最长递增子序列 (Longest Increasing Subsequence)**
```java
int max = 1;
int[] dp = new int[n];
dp[0] = 1;
for(int i=1;i<n;i++){
    dp[i] = 1;
    for(int j=0;j<i;j++){
          if(num[i] > num[j] && dp[i]<dp[j]+1){
              dp[i] = dp[j]+1;
          }      
    }
    if(dp[i] > max){
        max = dp[i];
    }
}   
return max;
```
```java
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
}
return res;
```
LeetCode: [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)

## **5.最大连续子序列和/积 (Maximum Product)**
```java
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
```
Leetcode: [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)