package ShortestPaths;

/**
 * Created by juming.wang on 2016/1/4.
 */
public class Floyd {

    void Floyd(DGraph g) {

        int i, j, k;
        int A[][] = new int[DGraph.N][DGraph.N];
        int path[][] = new int[DGraph.N][DGraph.N];

        for (i = 0; i < DGraph.N; i++) {
            for (j = 0; j < DGraph.N; j++) {
                A[i][j] = g.edges[i][j];
                path[i][j] = -1;
            }
        }

        for (k = 0; k < DGraph.N; k++) {
            for (i = 0; i < DGraph.N; i++) {
                for (j = 0; j < DGraph.N; j++) {
                    if (A[i][j] > (A[i][k] + A[k][j])) {
                        A[i][j] = A[i][k] + A[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }
}

class DGraph {
    public static final int N = 10;
    public String[] vertex;
    public int edges[][];
    public int n, e;
}