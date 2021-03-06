class 最短的桥 {
    class Solution {
        private int dirx[] = {-1,1,0,0};
        private int diry[] = {0,0,-1,1};
        private LinkedList<int[]> edges = new LinkedList<>();

        public int shortestBridge(int[][] A) {
            int visited[][] = new int[A.length][A[0].length]; //初始化
            //找到起始点
            int []start = find(A);
            int x = start[0];
            int y = start[1];
            dfs(A, visited,x, y); // 找出一个小岛
            int depth = 0;
            // 开始扩展
            while(!edges.isEmpty()){
                int size = edges.size(); // 当前的层数
                while (size -- != 0){
                    int[] coor = edges.poll();
                    int coorx = coor[0], coory = coor[1];
                    for(int i = 0 ; i < 4 ; i++){
                        int nextx = coorx + dirx[i];
                        int nexty = coory + diry[i];
                        if (!isIn(A,nextx,nexty)) continue;
                        if (A[nextx][nexty] == 1) return depth;
                        if (A[nextx][nexty] == 0){
                            A[nextx][nexty] = 2;
                            int[]edge = {nextx,nexty};
                            edges.add(edge);
                        }
                    }

                }
                depth ++;
            }
            return depth;
        }

        private int[] find(int [][] A){
            int m = A.length;
            int n = A[0].length;
            for (int x = 0; x < m ; x++ )
                for (int y = 0; y < n ; y++ )
                    if (A[x][y] == 1){
                        return new int[]{x,y};
                    }
            // 不可能出现这种情况
            return new int[]{-1,-1};
        }

        private boolean isIn(int[][] A, int x , int y){
            int m = A.length;
            int n = A[0].length;
            return x < m && x >= 0 && y < n && y >= 0;
        }

        // 先使用dfs将两个小岛中的随意一个完全找出，x，y对应的是小岛上的某一个点
        private void dfs(int[][] A,int[][] visited, int x, int y){
            visited[x][y] = 1;
            A[x][y] = 2;
            for (int i = 0 ; i < 4 ;i ++){
                int nextx = x + dirx[i];
                int nexty = y + diry[i];
                if (!isIn(A,nextx,nexty) || visited[nextx][nexty] == 1) continue;
                //收集边缘的点的信息
                if (A[nextx][nexty] == 0)  {
                    int[] edge = {x,y};
                    edges.add(edge);
                    continue;
                }

                dfs(A,visited,nextx,nexty);
            }
        }
    }
}