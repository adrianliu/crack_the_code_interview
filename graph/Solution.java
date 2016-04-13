package cc150.graph;

import java.util.*;

/**
 * Created by xiaopengliu on 13/04/16.
 */
class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}

class UnionFind {

    HashMap<Integer, Integer> father = new HashMap<>();

    UnionFind(HashSet<Integer> hashSet) {
        for(Integer now: hashSet) {
            father.put(now, now);
        }
    }
    int find(int x) {
        int parent = father.get(x);
        while(parent != father.get(parent)) {
            parent = father.get(parent);
        }
        return parent;
    }
    int compressed_find(int x) {
        int parent = father.get(x);
        while(parent != father.get(parent)) {
            parent = father.get(parent);
        }
        int temp = -1;
        int fa = father.get(x);
        while(fa != father.get(fa)) {
            temp = father.get(fa);
            father.put(fa, parent);
            fa = temp;
        }
        return parent;
    }

    void union(int x, int y) {
        int fa_x = find(x);
        int fa_y = find(y);
        if(fa_x != fa_y) {
            father.put(fa_x, fa_y);
        }
    }
}

public class Solution {

    public static void main(String[] args) {
        UndirectedGraphNode A = new UndirectedGraphNode(1);
        UndirectedGraphNode B = new UndirectedGraphNode(2);
        UndirectedGraphNode C = new UndirectedGraphNode(3);
        UndirectedGraphNode D = new UndirectedGraphNode(4);
        UndirectedGraphNode E = new UndirectedGraphNode(5);
        A.neighbors.add(B);
        A.neighbors.add(D);

        B.neighbors.add(A);
        B.neighbors.add(D);

        D.neighbors.add(A);
        D.neighbors.add(B);

        C.neighbors.add(E);
        E.neighbors.add(C);

        ArrayList<UndirectedGraphNode> graph = new ArrayList<>();
        graph.add(A);
        graph.add(B);
        graph.add(C);
        graph.add(D);
        graph.add(E);

        List<List<Integer>> res = connectedSet2(graph);
        printListOfList(res);

    }

    //Using Union Find to find connected components
    public static List<List<Integer>> connectedSet2(ArrayList<UndirectedGraphNode> nodes) {
        List<List<Integer>> ans =new ArrayList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        for(UndirectedGraphNode node: nodes) {
            hashSet.add(node.label);
        }
        UnionFind uf = new UnionFind(hashSet);

        for(UndirectedGraphNode node: nodes) {
            for(UndirectedGraphNode neighbour: node.neighbors) {
                uf.union(node.label, neighbour.label);
            }
        }
        return print(hashSet , uf, nodes.size());

    }

    static List<List<Integer>> print(HashSet<Integer> hashSet, UnionFind uf, int n) {
        List<List<Integer>> ans =new ArrayList<>();
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for(int i: hashSet) {
            int fa = uf.find(i);
            if(!hashMap.containsKey(fa)) {
                hashMap.put(fa, new ArrayList<Integer>());
            }
            List<Integer> now = hashMap.get(fa);
            now.add(i);
            hashMap.put(fa, now);

        }

        for(List<Integer> now: hashMap.values()) {
            Collections.sort(now);
            ans.add(now);
        }
        return ans;
    }

    private static void printListOfList(List<List<Integer>> lists) {
        for(List<Integer> list: lists) {
            System.out.print("[");
            for (Integer i: list) {
                System.out.print(i + " ");
            }
            System.out.print("]");
        }
    }

    public static List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nodes == null || nodes.size() == 0) {
            return res;
        }
        HashSet<UndirectedGraphNode> visited = new HashSet<>();
        for(UndirectedGraphNode node: nodes) {
            if(!visited.contains(node)) {
                List<Integer> list = new ArrayList<>();
                //bfs(list, visited, node);
                dfs(list, visited, node);
                Collections.sort(list);
                res.add(list);
            }
        }
        return res;
    }

    private static void dfs(List<Integer> list, HashSet<UndirectedGraphNode> visited, UndirectedGraphNode origin) {

        for(UndirectedGraphNode node: origin.neighbors) {
            if(!visited.contains(node)) {
                visited.add(node);
                list.add(node.label);
                dfs(list, visited, node);
            }
        }

    }


    private static void bfs(List<Integer> list, HashSet<UndirectedGraphNode> visited, UndirectedGraphNode origin) {
        if(origin == null) {
            return;
        }
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        q.offer(origin);
        visited.add(origin);
        while(!q.isEmpty()) {
            UndirectedGraphNode front = q.poll();
            list.add(front.label);
            for(UndirectedGraphNode node: front.neighbors) {
                if(!visited.contains(node)) {
                    q.offer(node);
                    visited.add(node);
                }
            }
        }
//        Collections.sort(list);
    }
}
