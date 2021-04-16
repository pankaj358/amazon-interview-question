/*
* 
* proble link : https://practice.geeksforgeeks.org/problems/find-the-number-of-islands/1#
*
*/


public class FindTheNumberOfIslandIn8Direction 
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
    dfs(i + 1, j + 1, m, n, grid);
    dfs(i + 1, j - 1, m, n, grid);
    dfs(i - 1, j + 1, m, n, grid );
    dfs(i - 1, j - 1, m, n, grid);

  }


}
