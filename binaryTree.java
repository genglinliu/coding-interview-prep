
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

// 在BST中查找一个数是否存在
boolean isInBST(TreeNode root, int target) {
    if (root == null) return false;
    if (root.val == target) return true;

    return isInBST(root.left, target) || isInBST(root.right, target);
}

// better solution: using the BST property
// binary search
boolean isInBST(TreeNode root, int target) {
    if (root == null) return false;
    if (root.val == target) return true;

    if (root.val < target) return isInBST(root.right, target);
    if (root.val > target) return isInBST(root.left, target);
}

// 抽象出BST框架
void BST(TreeNode root, int target) {
    // base cases
    if (root == null) return false
    if (root.val == target){
        // 找到目标，做什么(TODO)
    }

    if (root.val < target) {
        BST(root.right, target);
    }

    if (root.val > target) {
        BST(root.left, target);
    }
}

// 在BST中插入一个数 (inplace, return the updated BST)
TreeNode insertIntoBST(TreeNode root, int data) { 
    if (root == null) {
        return new TreeNode(data);
    }
    if (root.val == data) {
        // some error message: 
        // can't insert a number that already exists in the BST
    }
    if (root.val < data) {
        // don't forget to assign it to root.right
        // it's not a void method!
        root.right = insertIntoBST(root.right, data);
    }

    if (root.val > data) {
        root.left = insertIntoBST(root.left, data);
    }

    return root; // don't forget this too
}


// 从BST中删除一个数
