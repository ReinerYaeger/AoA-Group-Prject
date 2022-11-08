import java.util.Arrays;

public class Graph{

    private int[][] adjacencyMatrix;
    private int nov; //number of vertices
    private int noe; //number of edges
    private String vertexLabel[];

    public Graph(int nov){
        this.nov = nov;
        this.noe = 0;
        adjacencyMatrix = new int[nov][nov];
        for(int i = 0; i < nov; i++){
            for(int j = 0; j < nov; j++){
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    public void addEdge(int vertex1, int vertex2){
            adjacencyMatrix[vertex1][vertex2] = 1;
            adjacencyMatrix[vertex2][vertex1] = 1;
            noe++;
        }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(4, 2);
        graph.addEdge(0, 3);

        System.out.println(graph);
    }

    @Override
    public String toString( ) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nov; i++){
            sb.append(Arrays.toString(adjacencyMatrix[i])).append("\n");
        }
        return sb.toString();
    }

    public void addVertex(String firstName, String lastName, String phoneNumber, String emailAddress, String resCom, String school, String employer, String reqPrivacy) {
        // add vertex to graph

    }
}