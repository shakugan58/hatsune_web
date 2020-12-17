/**
 * 用来计算介数
 * 要计算所有的最短路径，用DIJ计算最短路径的时候我们可以发现一个规律：最后生成的结果是最小生成树，而一棵树是可以用一个一维数组表示的。
 * 所以本代码在计算介数的时候具有和DIJ相同的时间复杂度。
 */
public class Betweeness {
    private double[][]     dis;
    private int            N;
    Betweeness(double[][] dis)
    {
        this.dis = dis;
        this.N = dis.length;
    }
    /**
     * 根据返回的树来计算经过每个节点的最短路径的数目
     * @return
     */
    public double[] getBetweeness()
    {
        double[] b = new double[N];
        for(int start = 0; start < N; start++)
        {
            int[] path = getPath(start);    // 保存树的结构
            int[] num = new int[N];            // 一个节点的路径的数目
            int[] used = new int[N];        // 0：初始-->1：有子节点  0--->2:把没有子节点的处理
            for(int i = 0; i < path.length; i++)
            {
                System.out.print(path[i]);

            } System.out.println("");
            // 每次处理的时候找到没有子节点的点，然后将它的个数加到它的父节点中
            for(int i = 0; i < N; i++)
            {
                for(int j = 0; j < N; j++)
                {
                    if(path[j] != -1 && used[path[j]] == 0)
                    {
                        used[path[j]] = 1;
                    }
                }
                for(int j = 0; j < N; j++)
                {
                    if(used[j] == 0 && path[j] >= 0)
                    {
                        num[path[j]] += 1 + num[j];
                        used[j] = 2;
                    }
                }
                for(int j = 0; j < N; j++)
                {
                    if(used[j] == 1)
                    {
                        used[j] = 0;
                    }
                }
            }
            for(int i = 0; i < N; i++)
            {
                b[i] += num[i];
            }
        }

        double sum = N*N - N;
        for(int i = 0; i < N; i++)
        {
            b[i] /= sum;
        }
        return b;
    }
    /**
     * 计算从start出发到各个节点的最短路径，返回这棵最小生成树
     * @param start
     * @return
     */
    public int[] getPath(int start)
    {
        int[] path = new int[N];
        boolean[] used = new boolean[N];
        double[] minDis = new double[N];
        for(int i = 0; i < N; i++)
        {
            path[i] = -1;
            minDis[i] = -1.0;
        }
        used[start] = true;
        minDis[start] = 0.0;

        for(int i = 1; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(used[j] == true || dis[start][j] < 0){
                    continue;
                }
                if(dis[start][j] >= 0.0 && (minDis[j] < 0.0 || minDis[j] > minDis[start] + dis[start][j]))
                {
                    path[j] = start;
                    minDis[j] = minDis[start] + dis[start][j];
                }
            }
            start = -1;
            for(int j = 0; j < N; j++)
            {
                if(minDis[j] < 0.0 || used[j] == true)
                {
                    continue;
                }
                if(start == -1 || minDis[start] > minDis[j])
                {
                    start = j;
                    used[start] = true;
                }
            }
            if(start == -1)
            {
                break;
            }
        }
        return path;
    }
    /////////////////////////////////////////////////////////////////////
    public static void main(String[] main){
        double[][] dis = {{0, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 0}};


        double[] b = new Betweeness(dis).getBetweeness();
        for(int i = 0; i < b.length; i++)
        {
            System.out.println(b[i]);
        }
    }
}
