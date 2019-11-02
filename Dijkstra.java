import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Dijkstra {

	private int numberOfVertices;
	private static Vertex[] adjList;
	private static int distance[];

	public class Vertex {
		private String vertexName;
		private ArrayList<Edge> edges = new ArrayList<>();
		private int index;

		Vertex(String vertexName, int index) {
			this.vertexName = vertexName;
			this.index = index;
		}
	}

	public class Edge {
		private String source;
		private String dest;
		private int weight;

		Edge(String v1, String v2, int weight) {
			source = v1;
			dest = v2;
			this.weight = weight;
		}
	}

	public Dijkstra(String filename) {
		File file = new File(filename);
		try {
			Scanner scanner = new Scanner(file);
			
			numberOfVertices = scanner.nextInt();
			adjList = new Vertex[numberOfVertices];
			distance = new int[numberOfVertices];

			for(int i = 0; i < numberOfVertices; i++)  {
				if (scanner.hasNext()) {
					String vertexName = scanner.next();
					adjList[i] = new Vertex(vertexName, i);
				}
			}

			// Construct edges
			while(scanner.hasNext()) {
				String v1 = scanner.next();
				String v2 = scanner.next();
				int weight = scanner.nextInt();

				storeEdgeInAdjList(v1, v2, weight);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void storeEdgeInAdjList(String v1, String v2, int weight) {
		int idx = getVertexIndex(adjList, v1);
		adjList[idx].edges.add(new Edge(v1, v2, weight));
	}

	public static int getVertexIndex(Vertex[] adjList, String vertex) {
		for (int i = 0; i < adjList.length; i++) {
			if (adjList[i].vertexName.equals(vertex)) {
				return i;
			}
		}
		return -1;
	}

	public static int getDestIndex(Vertex[] adjList, String vertex) {
		for (int i = 0; i < adjList.length; i++) {
			if (adjList[i].vertexName.equals(vertex)) {
				return adjList[i].index;
			}
		}
		return -1;
	}

	public void findShortestPath(String source) {

		ArrayList<Vertex> fringe = new ArrayList<>();

		int srcIdx = getVertexIndex(adjList, source);
		Vertex v = adjList[srcIdx];

		for (int i = 0; i < numberOfVertices; i++) {
			distance[i] = Integer.MAX_VALUE;
			distance[srcIdx] = 0;
		}

		for (Edge edge : v.edges) {
			distance[getDestIndex(adjList, edge.dest)] = edge.weight;
			fringe.add(adjList[getVertexIndex(adjList, edge.dest)]);
		}

		while (!fringe.isEmpty()) {
			Vertex ver = fringe.get(fringe.indexOf(adjList[getMinDistance(srcIdx, fringe)]));
			System.out.println("Min vertex being used is " + ver.vertexName);
			fringe.remove(fringe.indexOf(ver));
			for (Edge edge : ver.edges) {
				int destIdx = getDestIndex(adjList, edge.dest);
				if (distance[destIdx] == Integer.MAX_VALUE) {
					distance[destIdx] = distance[getDestIndex(adjList, ver.vertexName)] + edge.weight;
					System.out.println("Setting distance for destIdx " + destIdx + " to " + distance[destIdx]);
					fringe.add(adjList[destIdx]);
				}
			}
		}
	}

	public int getMinDistance(int srcIdx, ArrayList<Vertex> fringe) {
		int min = Integer.MAX_VALUE;
		int minIdx = 0;
		for (int i = 0; i < numberOfVertices; i++) {
			if (i != srcIdx && distance[i] != Integer.MAX_VALUE && distance[i] < min && fringe.indexOf(adjList[i]) != -1) {
				min = distance[i];
				minIdx = i;
			}
		}
		//distance[minIdx] = Integer.MAX_VALUE;
		return minIdx;
	}

	public void printAdjList() {
		for(int i = 0; i < numberOfVertices; i++) {
			Vertex vertex = adjList[i];
			System.out.print(vertex.vertexName);
			System.out.println("");
			for (Edge edge : vertex.edges) {
				System.out.println("Edge from " + edge.source + " to " + edge.dest + " with weight " 
					+ edge.weight);
			}
		}
	}

	public void printDistance() {
		for (int i = 0; i < numberOfVertices; i++) {
			System.out.println("Distance from source to " + i + " is " + distance[i]);
		}
	}

	public static void main(String args[]) {
		Dijkstra dij = new Dijkstra("def.txt");

		//dij.printAdjList();
		dij.findShortestPath("A");
		dij.printDistance();
	} 
}