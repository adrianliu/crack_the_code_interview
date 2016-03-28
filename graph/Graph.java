package cc150.graph;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by xiaopengliu on 28/03/16.
 */
public class Graph<V>{

    private Map<V, List<Node<V>>> adjList;
    private Set<V> vertices;
    private static final int DEFAULT_WEIGHT = Integer.MAX_VALUE;

    public Graph() {
        this.adjList = new HashMap<>();
        vertices = new HashSet<>();
    }

    private static class Node<V> {
        private V name; //vertext name
        private int weight;

        public Node(V name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public V getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int hashCode() {
            return this.getName().hashCode();
        }

        @Override
        public String toString() {
            return "(" + this.weight + ")" + this.name;
        }

    }

    public Map<V, List<Node<V>>> getGraph() {
        return this.adjList;
    }

    //check if graph is empty
    public boolean isEmpty() {
        return this.vertices.isEmpty();
    }

    /*Add a new neighbour node or adjacent node for the given source
    * vertext. Vertex to node connection froms Edge.
    * @param src
    * @param destNode
    * */
    private void addEdge(V src, Node<V> destNode) {
        List<Node<V>> adjVertices = adjList.get(src);
        if(adjVertices == null || adjVertices.isEmpty()) {
            adjVertices = new ArrayList<Node<V>>();
            adjVertices.add(destNode);
        } else {
            adjVertices.add(destNode);
        }
        adjList.put(src, adjVertices);
    }

    // Add an Edge between src and destination vertices
    /*@param src
    @param dest
    @param weight
    * */
    public void addEdge(V src, V dest, int weight) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(dest);

        this.addEdge(src, new Node<>(dest, weight));

        //update vertices set
        this.vertices.add(src);
        this.vertices.add(dest);
    }

    /* Add an Edge between src and destination vertices overloaded method
    to create Unweighted graph
    @param src
    @param dest
    @param weight
    * */
    public void addEdge(V src, V dest) {
        Objects.requireNonNull(src);
        Objects.requireNonNull(dest);

        this.addEdge(src, new Node<>(dest, DEFAULT_WEIGHT));

        //update vertices set
        this.vertices.add(src);
        this.vertices.add(dest);
    }

    /*Takes two vertices and checks if there is a path between v1 and v2
    @param source
    @param destination
    @return
     */
    public boolean hasRelationship(V source, V destination) {
        if(source == null && destination == null) {
            return true;
        }
        if(source != null && destination == null) {
            return true;
        }
        if(source == null && destination != null) {
            return false;
        }
        List<Node<V>> nodes = null;
        if(adjList.containsKey(source)) {
            nodes = adjList.get(source);
            if(nodes != null && !nodes.isEmpty()) {
                for(Node<V> neighbours: nodes) {
                    if(neighbours.getName().equals(destination)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* get weight of the edge
    @param src
    @param dest
    @return weight
    * */
    public int getWeight(V src, V dest) {
        int weight = DEFAULT_WEIGHT;
        if(this.hasRelationship(src, dest)) {
            List<Node<V>> adjNodes = this.adjList.get(src);
            for(Node<V> node: adjNodes) {
                if(node.getName().equals(dest)) {
                    weight = node.getWeight();
                }
            }
        }
        return weight;
    }

    /* Get adjacent vertices of the given vertext. This method returns all edges starting
     from the given vertex. You can call this method iteratively on all vertices to get all edges.
     @param vertex
        source vertex
     @return List<V> list of adjacent vertices
    * */
    public List<V> getAdjacentVertices(V vertex) {
        List<Node<V>> adjNodes = this.adjList.get(vertex);
        List<V> neighbourVertex = new ArrayList<>();

        if((adjNodes != null) && !adjNodes.isEmpty()) {
            for(Node<V> v: adjNodes) {
                neighbourVertex.add(v.getName());
            }
        }
        return neighbourVertex;
    }

    /* Returns the unmodifiable collection of all vertices of the graph
    @return Set<V>
    * */
    public Set<V> getAllVertices() {
        return Collections.unmodifiableSet(this.vertices);
    }

    public boolean removeVertex(V vertex) {
        Objects.requireNonNull(vertex);

        if(!this.vertices.contains(vertex)) {
            return false;
        }

        Iterator<Entry<V, List<Node<V>>>> iter = this.adjList.entrySet().iterator();

        while(iter.hasNext()) {
            Entry<V, List<Node<V>>> e = iter.next();
            List<Node<V>> vs = e.getValue();
            //remove vertex if its found as a key in a map
            if(vertex.equals(e.getKey())) {
                iter.remove();
            }

            Iterator<Node<V>> listIterator = vs.iterator();
            while (listIterator.hasNext()) {
                Node<V> ver = listIterator.next();
                //remove vertex if its found as a value in mapped list
                if(vertex.equals(ver.getName())) {
                    listIterator.remove();
                }
            }
        }

        //remove vertex from vertices collection
        Iterator<V> iterVertices = this.vertices.iterator();
        while(iterVertices.hasNext()) {
            if(vertex.equals(iterVertices.next())) {
                iterVertices.remove();
                break;
            }
        }
        return true;


    }

    //Returns graph as string returns set of edges and vertices
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Set of Edges: \n");
        for(V v: this.adjList.keySet()) {
            List<Node<V>> neighbour = this.adjList.get(v);
            for(Node<V> vertex: neighbour) {
                if(vertex.getWeight() != DEFAULT_WEIGHT) {
                    sb.append(v + " -- (" + vertex.getWeight() + " ) --->" + vertex.getName() + "\n");
                } else {
                    sb.append(v + " ------->" + vertex.getName() + "\n");
                }
            }
        }

        sb.append("& Set of vertices :" + this.getAllVertices());
        return sb.toString();

    }

    private boolean hasUnvisitedNeighbour(List<V> neighbours, Set<V> visited) {
        for(V i: neighbours) {
            if(!visited.contains(i)) {
                return true;
            }
        }
        return false;
    }

    public void bfs(Graph graph, V source, Set<V> visited) {
        Queue<V> q = new LinkedList<>();
        q.add(source);
        System.out.print(" " + source);
        visited.add(source);
        while(!q.isEmpty()) {
            V temp = q.poll();
            List<V> neighbours = graph.getAdjacentVertices(temp);
            for(V a: neighbours) {
                if(!visited.contains(a)) {
                    System.out.print(" " + a);
                    visited.add(a);
                    q.add(a);
                }
            }
        }
    }

    public void dfsRecursive(Graph graph, V source, Set<V> visited) {

        System.out.print(" " + source);
        visited.add(source);
        List<V> neighbours = graph.getAdjacentVertices(source);
        for(V a: neighbours) {
            if(!visited.contains(a)) {
                dfsRecursive(graph, a, visited);
            }
        }


    }

    public void dfsNonRecursive(Graph graph, V source) {
        Objects.requireNonNull(source, "source is mandatory!");
        if(graph == null || graph.isEmpty()) {
            throw new IllegalStateException("Valid graph object is required !");
        }
        Stack<V> stack = new Stack<>();
        Set<V> visited = new HashSet<>();
        System.out.print(" " + source);
        visited.add(source);
        stack.push(source);

        boolean pop = false;
        V topVertex;

        while(!stack.isEmpty()) {
            if(pop == true) {
                topVertex = stack.pop();
            } else {
                topVertex = stack.peek();
            }

            List<V> neighbours = graph.getAdjacentVertices(topVertex);
            if(!neighbours.isEmpty() && hasUnvisitedNeighbour(neighbours, visited)) {
                for(V a: neighbours) {
                    if(!visited.contains(a)) {
                        System.out.print(" " + a);
                        visited.add(a);
                        stack.push(a);
                        //break from loop if an unvisited neighbour is found.
                        break;
                    }
                }
            } else {
                pop = true;
            }
        }


    }


    /**
     * Method to unit test
     */
    public static void main(String[] args) throws IOException{

        //create a Graph from input file.
        Graph<String> graph = new Graph<String>();

        FileInputStream fsStream = new FileInputStream("input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fsStream));

        String strLine;
        //read file line by line
        while((strLine = br.readLine()) != null) {
            String[] strs = strLine.split(" ");
            String source = strs[0];
            for(int i = 1;i < strs.length;i++) {
                String dest = strs[i];
                graph.addEdge(source, dest);
            }
        }
        System.out.println(graph.toString());

        //close the input stream
        br.close();

        //graph.dfsNonRecursive(graph, "1");

        Set<String> visited = new HashSet<>();
        //graph.dfsRecursive(graph,"0", visited);

        graph.bfs(graph,"0", visited);





//        Graph<String> graph = new Graph<String>();
//        graph.addEdge("0", "1");
//        graph.addEdge("BLR", "HK", 50);
//        graph.addEdge("BLR", "LA", 70);
//        graph.addEdge("LA", "SFO", 20);
//        graph.addEdge("HK", "LA", 60);
//
//        System.out.println(graph.toString());

//        System.out.println("\n Path between BLR and LA exists ? :"
//                + graph.hasRelationship("BLR", ""));
//
//        System.out.println(" Remove vertex BLR ? " + graph.removeVertex("LA"));
//        System.out.println(graph.toString());
    }





}
