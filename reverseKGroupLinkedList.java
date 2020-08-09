// reverse linked list given head node

ListNode reverse(ListNode a) {
    ListNode pre, cur, nxt;
    pre = null;
    cur = a;
    next = a;
    while (cur != null) { 
        nxt = cur.next;
        // 逐个结点反转
        cur.next = pre;
        // 更新指针位置
        pre = cur;
        cur = nxt;
    }
    // 返回反转后的头结点
    return pre;
}

// reverse linked list from interval a to b
ListNode reverse_from_a_to_b(ListNode begin, ListNode end) {
    ListNode pre, cur, nxt;
    pre = null; cur = begin; nxt = begin;
    while (cur != end){
        nxt = cur.next;
        pre = cur;
        cur = nxt;
    }
    return pre;
}

// Reverse group of k nodes at a time
ListNode reverseKGroup(ListNode head, int k) {
    if (head == null) return null;
    // interval [a, b) includes the k nodes
    ListNode a, b;
    a = b = head;
    for (int i = 0; i < k; i++) {
        // base case: less than k elements left
        if (b == null) return head;
        b = b.next;
    }
    // reverse first k nodes
    ListNode newHead = reverse_from_a_to_b(a, b);
    // 递归反转后续链表并拼接起来
    a.next = reverseKGroup(b, k);
    return newHead;
}


