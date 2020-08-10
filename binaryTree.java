
// 定义一个二叉树
class TreeNode {
    <t> val;
    TreeNode left;
    TreeNode right;

    TreeNode(<t> val){
        this.val = val;
        right = null;
        left = null;
    }
}

// 框架 - 明确每个节点要做的事 然后递归
void traverse(TreeNode root){
    // TODO: what does the root do?
    // then:
    traverse(root.left);
    traverse(root.right);
}

// warm up #1: 每个节点的值加一
void plusOne(TreeNode root) {
    if (root == null) return;
    root.val += 1;
    plusOne(root.left);
    plusOne(root.right);
}

// warm up number 2: 判断两棵树是否完全相同
boolean isSameTree(TreeNode root1, TreeNode root2) {
    // 都为空的话 显然相同
    if (root1 == null && root2 == null) return true;
    // 只有一个为空
    else if (root1 == null || root2 == null) return false;
    // 两个都非空但是值不一样
    else if (root1.val != root2.val) return false;

    // root1和root2该比的都比完了 recursion
    return isSameTree(root1.left, root2.left) 
        && isSameTree(root1.right, root2.right);
}

// BST property:
// 任意节点要大于等于所有左子树结点的值，且小于等于右子树节点的值
// 实现BST基本操作：判断合法性，增删查改

// 1. 判断合法性
boolean isValidBST(TreeNode root) {
    return boolean isValidBST_healper(root, null, null);
}

boolean isValidBST_healper(TreeNode root, TreeNode min, TreeNode max){
    if (root == null) return true;
    if (min != null && root.val <= min.val) return false;
    if (max != null && root.val >= max.val) return false;
    return isValidBST_healper(root.left, min, root)
        && isValidBST_healper(root.right, root, max);
}