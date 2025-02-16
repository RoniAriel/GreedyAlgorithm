import java.util.*;
import java.lang.*;
import java.io.*;

public class Graph implements Greedy<Graph.Edge> {
    List<Graph.Edge> lst; //Graph is represented in Edge-List. It is undirected. Assumed to be connected.
    int n; //nodes are in [0,..., n]
    UnionFind VertexinSet;

    Graph(int n1, List<Edge> lst1) {
        lst = lst1;
        n = n1;
        Collections.sort(this.lst);
        this.VertexinSet = new UnionFind(n);
    }

    public static class Edge implements Comparable<Edge> {
        int node1, node2;
        double weight;

        Edge(int n1, int n2, double w) {
            node1 = n1;
            node2 = n2;
            weight = w;
        }

        @Override
        public String toString() {
            return "{" + "(" + node1 + "," + node2 + "), weight=" + weight + '}';
        }


        @Override
        public int compareTo(Edge other) {
            if (this.weight != other.weight) {
                return Double.compare(this.weight, other.weight);
            } else {
                int Value = compareByVal(this.node1, other.node1);
                if (Value == 0) {
                    return compareByVal(this.node2, other.node2);
                }
                return Value;
            }
        }

        private int compareByVal(int compareVal1, int compareVal2) {
            if (compareVal1 > compareVal2) { // this> other
                return 1;
            }
            if (compareVal2 > compareVal1) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public Iterator<Graph.Edge> selection() {
        return lst.iterator();
    }

    @Override
    public boolean feasibility(List<Graph.Edge> candidates_lst,Graph.Edge element) {
        if (this.VertexinSet.Find(element.node1) != this.VertexinSet.Find(element.node2)){
            this.VertexinSet.Union(element.node1, element.node2);
            return true;
        }
        return false;
    }

    @Override
    public void assign(List<Edge> candidates_lst, Edge element) {
        candidates_lst.add(element);
    }

    @Override
    public boolean solution(List<Edge> candidates_lst) {
        return candidates_lst.size() == n;
    }

    public class UnionFind  {
        private int[] parent;
        public UnionFind (int numOfVertices) {
            parent = new int[n+1];
            for (var i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int Find(int x) {
            if (x == parent[x]) {
                return x;
            }
            // compress the paths
            return parent[x] = Find(parent[x]);
        }

        public void Union(int x, int y)  {
            var px = Find(x);
            var py = Find(y);
            if (px != py) {
                parent[px] = py;
            }
        }

        public int size() { // number of groups
            int ans = 0;
            for (int i = 0; i <= parent.length; ++ i) {
                if (i == parent[i]) ans ++;
            }
            return ans;
        }
    }
}
