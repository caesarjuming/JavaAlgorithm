package ShortestPaths;

/**
 * Created by juming.wang on 2015/12/30.
 */

//��Դ���·��
public class Dijkstra {

    public void GetDijkstraPath(MGraph g,int [] path,int []dist,int Who){
        boolean []visited=new boolean[MGraph.N];
        //initialize
        for (int i=0;i<MGraph.N;i++){
            // ����֮�����߶Σ��Ҳ��ǻ�
            if(g.getMatrix(Who,i)>0&&i!=Who){
                //ԭ�㵽i���·�����ȣ�����Ϊ����֮��ֱ�ӵĳ���
                dist[i]=g.getMatrix(Who,i);
                //·��Ϊԭ��
                path[i]=Who;
            }else{
                //���㲻��������Ϊ���޴�
                dist[i]=Integer.MAX_VALUE;
                //·��Ϊ-1,��ʾ������
                path[i]=-1;
            }
            //��ʼ�����ã����нڵ㶼δ���ʹ���·����������
            visited[i]=false;
        }
        //����ԭ�����·��Ϊ�Լ�
        path[Who]=Who;
        dist[Who]=0;

        visited[Who]=true;
        //find the min value
        int m=-1;

        //����n-1��
        for(int j=1;j<MGraph.N;j++){
            int MIN=Integer.MAX_VALUE;
            m=-1;
            //Ѱ��ԭ�㵽�����ڵ�����С����
            for(int k=0;k<MGraph.N;k++){
                if(visited[k]==false&&dist[k]<MIN){
                  m=k;
                  MIN=dist[k];
                }
            }
            //����Ѱ�ҵ���С����+ԭ�㵽�˵�ľ���<ԭ�㵽�˵�ľ���
            //������С���������·��
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