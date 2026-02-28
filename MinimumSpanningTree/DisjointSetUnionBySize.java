package MinimumSpanningTree;

import java.util.*;

class DisjointSet1 {
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet1(int n) {
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar1(int node) {
        if(node == parent.get(node)) {
            return node;
        }
        int ulp = findUPar1(parent.get(node));
        parent.set(node, ulp);
        return ulp;
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar1(u);
        int ulp_v = findUPar1(v);

        if(ulp_u == ulp_v) return;

        if(size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

public class DisjointSetUnionBySize {
    public static void main(String[] args) {

        DisjointSet1 ds = new DisjointSet1(7);

        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        if (ds.findUPar1(3) == ds.findUPar1(7)) {
            System.out.println("Same Component");
        } else {
            System.out.println("Different Component");
        }

        ds.unionBySize(3, 7);

        if (ds.findUPar1(3) == ds.findUPar1(7)) {
            System.out.println("Same Component");
        } else {
            System.out.println("Different Component");
        }
    }
}
