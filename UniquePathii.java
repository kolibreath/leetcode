public class UniquePathii {
//    static
 class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length, n = obstacleGrid[0].length;
                if(obstacleGrid[0][0]==1||obstacleGrid[m-1][n-1]==1)
                    return 0;
            int dp[][] = new int[m][n];
            dp[0][0] = (obstacleGrid[0][0] == 0 ? 1 : 0);

            for(int i = 1; i < m; i++) {
                dp[i][0] = ((dp[i - 1][0] == 1 && obstacleGrid[i][0] == 0) ? 1 : 0);
            }

            for(int j = 1; j < n; j++) {
                dp[0][j] = ((dp[0][j - 1] == 1 && obstacleGrid[0][j] == 0) ? 1 : 0);
            }

            for(int i = 1; i < m; i++) {
                for(int j = 1; j < n; j++) {
                    if(obstacleGrid[i][j] == 1) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            return dp[m-1][n-1];
        }
    }

}