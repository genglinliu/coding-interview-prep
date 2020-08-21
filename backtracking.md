算法框架 - 回溯问题 = 遍历一个决策树

Backtracking = tree traversal

Three things:
1. path - the decisions you've already made
2. list of choices - the decisions you're about to make now
3. End condition - The bottom of the tree - where you can no longer make more decisions

Framework:

```python
result = []
def backtrack(path, list_of_choices):
    if end_condition_met:
        result.add(path)
        return
    
    for choice in list_of_choices:
        make a choice
        backtrack(path, list_of_choices)
        reverse the choice
```

1. Printing Permutation

Permutation for n distinct numbers is n!

How to print a list of permutations? [1, 2, 3] fix 1 and 2, we only have 3; fix 1 and change the second number to 3, then the third one can only be 2; change the first one, ... that is backtracking

You could draw a tree with number 1, 2, 3 on the edges. Traverse this tree and record the numbers on the edges, you get the permutation of [1, 2, 3]

What are the nodes? - path (numbers already taken) and list of choices (numbers to be taken)

`backtrack` function is like a pointer, traversing the tree, and then maintaining the node attributes each step of the way. Everytime you hit a leaf, its path is a permutation. 

Tree Traversal:

```java
void traverse(TreeNode root) {
    for (TreeNode child : root.children) {
        // pre-order
        traverse(child);
        // post-order traversal
    }
}
```

