
Union Find Algorithm

并查集算法

动态连通性 - 

Two APIs : 

```java
class UF {
    // connect p to q
    public void union(int p, int q);
    // is p and q connected?
    public boolean isConnected(int p, int q);
    // return the number of connected components
    public int count();
}
```

Connected is an equivalence relation: 

reflextive: `p` and `p` is connected  
symmetric: `p` and `q` connected == `q` and `p` connected  
transitive: `p` <-> `q`, `q` <-> `r` ==> `p` <-> `r`

----
example\
graph with 9 nodes, disconnected from each other\
call `union(0, 1)`, now node 0 and 1 are connected; 9 connected components total\
Then call `union(1, 2)`, now node 0, 1, 2 are connected, 8 connected components total\

Applications:\
compliers: count the number of references to a variable, social media network calculations, etc\

--------
### Algorithm

using forest (a number of trees) represent the connectivity of a graph 

```java
class UF {
    // keep track of connected components
    private int count;
    // parent of x should be parent[x]
    print int[] parent;

    /* constructor, n is the total number of nodes in the graph */ 
    public UF(int n) {
        this.count = n;
        // parent pointer pointing to self at the init
        parent = new int[n];
        for (int i = 0; i < n; i++){
            parent[i] = i;
        }
    }
    //... other methods
}
```

**If two nodes are being connected (i.e. `union(p, q)`), then we point `p`'s root node to `q`'s root node**

```java
public void union(int p, int q) {
    int rootP = findRoot(p);
    int rootQ = findRoot(q);
    if (rootP == rootQ) {
        return;
    }
    // connect the two trees, make rootP a child of rootQ
    parent[rootP] = rootQ;
    count--; // now decrement the connected component count
}

/* return the root of a given tree node*/
public int findRoot(int x) {
    while (parent[x] != x) {
        x = parent[x];
    }
    return x;
}
```

Consequence: IF `p` and `q` are connected, then they share a root node.

```java
public boolean isConnected(int p, int q){
    int rootP = findRoot(p);
    int rootQ = findRoot(q);
    return rootP == rootQ;
}
```

#### Analysis - Time complexity
The complexity of `union()` and `isConnected()` are all the same as the complexity of `findRoot()`\

And the complexity of `findRoot()` is the height of the tree. Right now it's `O(N)` because it could be a linked list in the worst case.\  

Optimize: balance the tree and make `findRoot()` O(logN)\

**小一些的树接到大一些的树下面，避免头重脚轻，更平衡一些**

Solution:

```java
class UF {
    private int count;
    private int[] parent;
    // new array to store the "weight" of a tree
    private int[] size;

    public UF(int n) {
        this.count = n;
        this.parent = new int[n];
        // init size to 1 because every tree starts out with 1 node
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = 1;
            parent[i] = i;
        }
    }
    // ... other methods
}
```

Ex. `size[3] = 5` means that the tree whose root is `3` has `size 5`, aka `5` nodes

fix `union()`:

```java
public void union(int p, int q) {
    int rootP = findRoot(p);
    int rootQ = findRoot(q);
    // already sharing a root, done
    if (rootP == rootQ) return;

    // append smaller tree to the larger tree
    if (size[rootP] < size[rootQ]) {
        parent[rootP] = rootQ;
        size[rootQ] += size[rootP];
    }
    else {
        parent[rootQ] = rootP;
        size[rootP] += size[rootQ];
    }
    count--;
}
```

Analysis: now `findRoot`, `union`, `isConnected` are all O(logN)\

Thought: can we **compress** the height of each tree and let it remain **constant**? - then we have O(1) `findRoot()`\

```java
private int findRoot(int x) {
    while (parent[x] != x) {
        // 路径压缩
        parent[x] = parent[parent[x]];
        x = parent[x] // compress the height
    }
    return x;
}
```

Analysis: Now all of our algorithms are O(1)

