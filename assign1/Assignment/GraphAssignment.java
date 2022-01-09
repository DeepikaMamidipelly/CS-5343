package Assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphAssignment {

	// constructor of GraphAssignment
	public GraphAssignment(int vertices) {

		// create vertices and adjacency lists for vertexes
		graphRepresentation createGraph = new graphRepresentation(vertices);

		// for every vertex create outgoing edges and add weights to the edges
		for (int i = 0; i < vertices; i++) {
			List<Integer> listOftoVertices = new ArrayList<>();

			// create outgoing edges
			for (int j = 0; j < vertices / 2; j++) {

				// randomly generating vertex
				int toVertex = (int) (Math.random() * vertices);
				if (listOftoVertices.contains(toVertex) || toVertex == i) {
					continue;
				} else {

					// generating random edge weight
					int edgeweight = ((int) (Math.random() * 10)) + 1;
					DirectedEdge directedEdge = new DirectedEdge(i, toVertex, edgeweight);
					createGraph.createEdge(directedEdge);
					listOftoVertices.add(toVertex);
				}
			}
		}

		System.out.println("Created Directional Graph is : ");
		System.out.println(createGraph.toString());

		// implementing dijkstras Algorithm on created Graph:
		System.out.println("Implementing Dijkstra algorithm from the vertex 0 ");
		DijkstraAlgo dijkstra = new DijkstraAlgo(createGraph, 0);

		System.out.println("The Shortest path tree with the edges");
		dijkstra.shortestPathTreeEdges();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphAssignment dijkstraSPT = new GraphAssignment(10);

	}

	class DijkstraAlgo {
		public DirectedEdge[] edgeTo;
		public double[] distTo;
		public priorityQueue<Double> pQueue;

		// DijkstraAlgo constructor
		public DijkstraAlgo(graphRepresentation graph, int s) {
			edgeTo = new DirectedEdge[graph.Vertices()];
			distTo = new double[graph.Vertices()];
			pQueue = new priorityQueue<Double>(graph.Vertices());

			Arrays.fill(distTo, Double.POSITIVE_INFINITY);
			distTo[s] = 0;

			pQueue.insert(s, 0.0);

			while (!pQueue.isEmpty()) {
				relax(graph, pQueue.delMinimum());
			}

		}

		// shortest path from a single vertex to all vertices
		public void relax(graphRepresentation G, int v) {
			for (DirectedEdge directedEdge : G.adj(v)) {
				int w = directedEdge.getTo();
				if (distTo[w] > distTo[v] + directedEdge.getWeight()) {
					distTo[w] = distTo[v] + directedEdge.edgeWeight;
					edgeTo[w] = directedEdge;
					if (pQueue.contains(w))
						pQueue.change(w, distTo[w]);
					else
						pQueue.insert(w, distTo[w]);
				}
			}
		}

		public double distTo(int v) {
			return distTo[v];
		}

		public boolean hasPathTo(int v) {
			return distTo[v] < Double.POSITIVE_INFINITY;
		}

		public void shortestPathTreeEdges() {
			System.out.println("Printing the tree after dijkstras algorithm");
			for (int i = 0; i < edgeTo.length; i++) {
				if (edgeTo[i] != null) {

					System.out.println(String.format(
							"fromVertex ---> toVertex :- %d ----> %d , distance from source vertex 0 with weight : %2.0f ",
							edgeTo[i].getFrom(), i, distTo[i]));
				}
			}
		}

	}

//priority queue
	class priorityQueue<Key extends Comparable<Key>> {
		private int N;
		private int[] pq, qp;
		private Key[] keys;

		public priorityQueue(int value) {
			pq = new int[value + 1];
			qp = new int[value + 1];
			keys = (Key[]) new Comparable[value + 1];
			for (int i = 0; i < value + 1; i++)
				qp[i] = -1;
		}

		public boolean isEmpty() {
			return N == 0;
		}

		public boolean contains(int k) {
			return qp[k] != -1;
		}

		public void insert(int k, Key key) {
			N++;
			qp[k] = N;
			pq[qp[k]] = k;
			keys[k] = key;
			perculateup(N);
		}

		private void perculateup(int k) {
			while (k > 1 && greater(k / 2, k)) {
				swap(k / 2, k);
				k = k / 2;
			}
		}

		private void perculatedown(int k) {
			while (2 * k <= N) {
				int j = 2 * k;
				if (j < N && greater(j, j + 1))
					j++;
				if (!greater(k, j))
					break;
				swap(k, j);
				k = j;
			}
		}

		public int delMinimum() {
			int indexOfMin = pq[1];
			swap(1, N--);
			perculatedown(1);
			keys[pq[N + 1]] = null;
			qp[pq[N + 1]] = -1;
			return indexOfMin;
		}

		public void change(int k, Key key) {
			Key current = keys[k];
			if (current.compareTo(key) > 0) {
				keys[k] = key;
				perculatedown(k);
			} else if (current.compareTo(key) < 0) {
				keys[k] = key;
				perculateup(k);
			}
		}

		public Key min() {
			return keys[pq[1]];
		}

		private boolean greater(int i, int j) {
			return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
		}

		private void swap(int i, int j) {
			int swap = pq[i];
			pq[i] = pq[j];
			pq[j] = swap;
			qp[pq[i]] = i;
			qp[pq[j]] = j;
		}

	}

//graph representation
	class graphRepresentation {
		private int vertices, edges;
		private List<DirectedEdge>[] adjacencyList;

		// creating adjacency lists for the every vertex
		public graphRepresentation(int noOfVertices) {
			this.vertices = noOfVertices;
			adjacencyList = new List[noOfVertices];
			for (int i = 0; i < noOfVertices; i++) {
				adjacencyList[i] = new ArrayList<DirectedEdge>();
			}
		}

		public void createEdge(DirectedEdge edge) {
			int v = edge.getFrom();
			adjacencyList[v].add(edge);
			edges++;
		}

		public List<DirectedEdge> adj(int v) {
			return adjacencyList[v];
		}

		public List<DirectedEdge> edges() {
			List<DirectedEdge> allEdges = new ArrayList<DirectedEdge>();
			for (int i = 0; i < vertices; i++) {
				for (DirectedEdge e : adj(i)) {
					allEdges.add(e);
				}
			}
			return allEdges;
		}

		public int Vertices() {
			return vertices;
		}

		public int E() {
			return edges;
		}

		public String toString() {
			String str = "";
			str += "No Of Vertices: " + vertices + " with Vertices {0,1,2,3,4,5,6,7,8,9}" + "\n" + "No Of Edges:"
					+ edges + "\n";
			for (int i = 0; i < vertices; i++) {
				str += "Adjacency lists for vertex" + " " + i + ":(toVertex,edgeWeight)";
				for (DirectedEdge e : adjacencyList[i]) {
					int w = e.getTo();
					double weight = e.getWeight();
					str += String.format("(%d, %2.0f)", w, weight);
				}
				str += "\n";
			}
			return str;
		}

	}

	// directedEdge class
	class DirectedEdge implements Comparable<DirectedEdge> {

		private final int fromVertex, toVertex;
		private final int edgeWeight;

		public DirectedEdge(int fromVertex, int toVertex, int edgeWeight) {
			this.fromVertex = fromVertex;
			this.toVertex = toVertex;
			this.edgeWeight = edgeWeight;
		}

		public int getFrom() {
			return fromVertex;
		}

		public int getTo() {
			return toVertex;
		}

		public double getWeight() {
			return edgeWeight;
		}

		@Override
		public int compareTo(DirectedEdge edge) {
			if (this.edgeWeight > edge.edgeWeight)
				return 1;
			else if (this.edgeWeight < edge.edgeWeight)
				return -1;
			else
				return 0;
		}
	}

}
