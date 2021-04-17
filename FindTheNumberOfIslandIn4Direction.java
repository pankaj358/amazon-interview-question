/*
*
*  problem link : https://leetcode.com/problems/number-of-islands/
*
*  Given a 2-D matrix of 0s and 1s, find the number of groups of adjacent 1s given that diagonal 1s are not included.
*
*
*
*/


public class FindTheNumberOfIslandIn4Direction 
{

  public int solve(char[][] grid)
  {
    int ans = 0;
   
    int m = grid.length;
    int n = grid[0].length;

    for(int i = 0; i < m; i++)
    {
      for(int j = 0; j < n; j++)
       {
          if(grid[i][j] == '1')
          {
             dfs(i, j, m, n, grid);
             ans++;
          }
       }
    }     

    return ans;
  }

  public void dfs(int i, int j, int m, int n, char[][] grid)
  {
    if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0' || grid[i][j] == 'x')
      return;

    grid[i][j] = 'x';

    dfs(i + 1, j, m, n, grid);
    dfs(i - 1, j, m, n, grid);
    dfs(i,  j + 1, m, n, grid);
    dfs(i,  j - 1, m, n, grid);
  }


}
