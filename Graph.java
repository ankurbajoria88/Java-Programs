import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

	public class Vertex {
		private String vertexName;
		private Neighbours edges;
		private boolean visited;

		Vertex(String vertexName, Neighbours edges) {
			this.vertexName = vertexName;
			this.edges = edges;
		}
	}

	public class Neighbours {

		private String vertexValue;
		private Neighbours next;

		Neighbours(String vertexValue, Neighbours next) {
			this.vertexValue = vertexValue;
			this.next = next;
		}
	}

	private boolean matrix[][];
	private int numberOfVertices;
	private ArrayList<String> vertices;
	private static Vertex[] adjList;

	public Graph(String filename) {
		File file = new File(filename);
		try {
			Scanner scanner = new Scanner(file);
			
			numberOfVertices = scanner.nextInt();
			vertices = new ArrayList<>();
			matrix = new boolean[numberOfVertices][numberOfVertices];
			adjList = new Vertex[numberOfVertices];

			for(int i = 0; i < numberOfVertices; i++)  {
				if (scanner.hasNext()) {
					String vertexName = scanner.next();
					vertices.add(vertexName);
					adjList[i] = new Vertex(vertexName, null);
				}
			}

			// Construct edges
			while(scanner.hasNext()) {
				int v1 = vertices.indexOf(scanner.next());
				int v2 = vertices.indexOf(scanner.next());

				storeEdgeInMatrix(v1, v2);
				storeEdgeInAdjList(v1, v2);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void storeEdgeInMatrix(int v1, int v2) {
		matrix[v1][v2] = true;
		matrix[v2][v1] = true;
	}

	public void storeEdgeInAdjList(int v1, int v2) {
		String vertexName1 = vertices.get(v1);
		String vertexName2 = vertices.get(v2);

		adjList[v1].edges = new Neighbours(vertexName2, adjList[v1].edges);
		adjList[v2].edges = new Neighbours(vertexName1, adjList[v2].edges);	
	}

	public void printMatrix() {
		for(int i = 0; i < numberOfVertices; i++) {
			for(int j = 0; j < numberOfVertices; j++) {
				System.out.print(matrix[i][j] + "|");
			}
			System.out.println();
		}
	}

	public void printAdjList() {
		for(int i = 0; i < numberOfVertices; i++) {
			Vertex vertex = adjList[i];
			System.out.print(vertex.vertexName);
			for (Neighbours nbr = vertex.edges; nbr != null; nbr=nbr.next)  {
				System.out.print("-->" + nbr.vertexValue);
			}
			System.out.println();
		}
	}

	public void runDfs(Vertex[] adjList, int v) {
	
		// Mark this vertex as visited
		adjList[v].visited = true;
		System.out.println("Visiting vertex " + adjList[v].vertexName);

		// Loop through the neighbours of this vertex
		for (Neighbours nbr = adjList[v].edges; nbr != null; nbr = nbr.next) {
			int idx = getVertexIndex(adjList, nbr.vertexValue);
			if (!adjList[idx].visited) {
				System.out.println("Visiting neighbour " + nbr.vertexValue);
				runDfs(adjList, idx);
			}
		}

		for (int j = 0; j < adjList.length; j++) {
			if (!adjList[j].visited) {
				runDfs(adjList, j);
			}
		}
	}

	public void runBfs(Vertex[] adjList, int v) {

		LinkedList<Vertex> queue = new LinkedList<>();

		// Mark this vertex as visited
		adjList[v].visited = true;
		System.out.println("Visiting vertex " + adjList[v].vertexName);
		queue.add(adjList[v]);

		while(!queue.isEmpty()) {
			Vertex vertex = queue.getFirst();
			queue.removeFirst();
			// Loop through all the neighbours
			for (Neighbours nbr = vertex.edges; nbr != null; nbr = nbr.next) {
				int idx = getVertexIndex(adjList, nbr.vertexValue);
				if (!adjList[idx].visited) {
					System.out.println("Visiting neighbour " + nbr.vertexValue);
					adjList[idx].visited = true;
					queue.add(adjList[idx]);
				}
			}
		}
	}

	public static int getVertexIndex(Vertex[] adjList, String vertex) {
		for (int i = 0; i < adjList.length; i++) {
			if (adjList[i].vertexName.equals(vertex)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String args[]) {
		Graph graph = new Graph("abc.txt");

		System.out.println("***************** PRINTING THE GRAPH IN MATRIX FORMAT *************************");
		graph.printMatrix();
		System.out.println("***************** PRINTING THE GRAPH IN ADJLIST FORMAT ************************");
		graph.printAdjList();
		System.out.println("************************ RUNNING DFS ON THE GRAPH *****************************");
		//graph.runDfs(adjList, 3);
		System.out.println("************************ RUNNING BFS ON THE GRAPH *****************************");
		graph.runBfs(adjList, 0);
	}
}