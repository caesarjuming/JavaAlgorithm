package ShortestPaths;

/**
 * Created by juming.wang on 2015/12/30.
 */

//单源最短路径
public class Dijkstra {

    public void GetDijkstraPath(MGraph g,int [] path,int []dist,int Who){
        boolean []visited=new boolean[MGraph.N];
        //initialize
        for (int i=0;i<MGraph.N;i++){
            // 两点之间有线段，且不是环
            if(g.getMatrix(Who,i)>0&&i!=Who){
                //原点到i点的路径长度，更新为两点之间直接的长度
                dist[i]=g.getMatrix(Who,i);
                //路径为原点
                path[i]=Who;
            }else{
                //两点不相连，设为无限大
                dist[i]=Integer.MAX_VALUE;
                //路径为-1,表示不经过
                path[i]=-1;
            }
            //初始化设置，所有节点都未访问过，路径所经过的
            visited[i]=false;
        }
        //设置原点访问路径为自己
        path[Who]=Who;
        dist[Who]=0;

        visited[Who]=true;
        //find the min value
        int m=-1;

        //遍历n-1次
        for(int j=1;j<MGraph.N;j++){
            int MIN=Integer.MAX_VALUE;
            m=-1;
            //寻找原点到其他节点中最小距离
            for(int k=0;k<MGraph.N;k++){
                if(visited[k]==false&&dist[k]<MIN){
                  m=k;
                  MIN=dist[k];
                }
            }
            //上面寻找的最小距离+原点到此点的距离<原点到此点的距离
            //更新最小距离表，增加路径
            for(int l=0;l<MGraph.N;l++){
                if(visited[l]==false&&g.getMatrix(m,l)+MIN<dist[l]){
                    dist[l]=g.getMatrix(m,l)+MIN;
                    path[l]=m;
                }
            }
        }
    }
}

class MGraph{
    public static final int N=100;
    private int matrix [][]=new int[N][N];
    private int nodes;
    private int edges;

    public int getMatrix(int i,int j) {
        return this.matrix[i][j];
    }

    public void setMatrix(int i,int j,int value) {
        this.matrix[i][j]=value;
    }

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    public int getEdges() {
        return edges;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    MGraph(){
        this.nodes=0;
        this.edges=0;
    }
}