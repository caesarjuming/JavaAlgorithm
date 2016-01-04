package ShortestPaths;

/**
 * Created by juming.wang on 2015/12/30.
 */
public class Dijkstra {

    public void GetDijkstraPath(MGraph g,int [] path,int []dist,int Who){
        boolean []visited=new boolean[MGraph.N];
        //initialize
        for (int i=0;i<MGraph.N;i++){
            if(g.getMatrix(Who,i)>0&&i!=Who){
                dist[i]=g.getMatrix(Who,i);
                path[i]=Who;

            }else{
                dist[i]=Integer.MAX_VALUE;
                path[i]=-1;
            }
            visited[i]=false;
            path[Who]=Who;
            dist[Who]=0;

        }
        visited[Who]=true;
        //find the min value
        int MIN=Integer.MAX_VALUE;
        int m=-1;
        for(int j=1;j<MGraph.N;j++){

            m=-1;
            for(int k=0;k<MGraph.N;k++){
                if(visited[k]==false&&dist[k]<MIN){
                  m=k;
                  MIN=dist[k];
                }
            }
        }
        // union find
        for(int l=1;l<MGraph.N;l++){
            if(visited[l]==false&&g.getMatrix(m,l)+MIN<dist[l]){
                dist[l]=g.getMatrix(m,l)+MIN;
                path[l]=m;
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