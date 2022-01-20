package graphs_dijkstraI_and_mst_lab.mst;

public class Edge implements Comparable<Edge> {

    private int startNode;
    private int endNode;
    private int weight;

    public Edge(int startNode, int endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public int getStartNode() {
        return this.startNode;
    }

    public void setStartNode(int startNode) {
        this.startNode = startNode;
    }

    public int getEndNode() {
        return this.endNode;
    }

    public void setEndNode(int endNode) {
        this.endNode = endNode;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.getWeight());
    }

    @Override
    public String toString() {
        return String.format("(%s %s) -> %s", this.startNode, this.endNode, this.weight);
    }
}
