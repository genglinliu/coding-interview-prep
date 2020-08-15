
1. Fast and slow pointers - linked list problems 
2. Left and right pointers - array problems

-----
### Fast and Slow Pointers

 - initialize both at the head node, one moves faster than the other\

#### 1. Decide if there is a cycle in a linked list

Cannot decide if the linked list contains a cycle with only one pointer.\

solution: set two pointers. IF there is a cycle, then both pointers will get stuck in the loop and the faster pointer will eventually lap the slow one and that's when we know there is a cycle. If there is no cycle then the faster pointer will meet the null pointer as the end 

```java
boolean hasCycle(ListNode head) {
    ListNode fast, slow;
    fast = slow = head;
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;

        if (fast == slow) return true
    }
    return false;
}
```

#### 2. Now suppose the link list does have a cycle. Question: return the starting node of this cycle.

```java
boolean hasCycle(ListNode head) {
    ListNode fast, slow;
    fast = slow = head;
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;

        if (fast == slow) break; // they met
    }
    /* think about it: suppose the distance between where they meet and the start of the loop is m. Also suppose the slow pointer has gone a distance of k and the fast pointer has gone 2k. Then the distance between the head node of the list to the start of the cycle is k-m, and the rest of the cycle from where they met is also k-m. Therefore to find where k-m is, we just need to set one of the pointers back to the head node and let them move at the same rate. When they meet again, that's the start of the cycle. */
    slow = head;
    while (slow != head) {
        slow = slow.next;
        head = head.next;
    }
    return slow;
}
```

#### 3. Find the midpoint of a linked list

simple approach: fast pointer goes twice as fast. When it gets to the end, slow pointer will get to the middle.

```java
while (fast != null && fast.next != null) { // this condition is important because of the .next.next
    fast = fast.next.next;
    slow = slow.next;
}
return slow;
```

Note: when the length is an even number, this slow pointer will end up **one node to the right**

#### 4. Find the k-th to last element in the linked list

倒数第k个元素 - assuming k <= length of the list

approach: let fast pointer go `k` steps first and then move them both at the same rate. 

```java
ListNode slow, fast;
slow = fast = head;

while (k-- > 0){ // count to k
    fast = fast.next;
}

while (fast != null) {
    slow = slow.next;
    fast = fast.next;
}

return slow;
```

## Left and Right Pointers

Initialize left = 0, right = nums.length-1

#### 1. Binary Search

```java
int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while(left <= right) {
        int mid = (right + left) / 2;
        if (nums[mid] == target){
            return mid;
        }
        else if (nums[mid] < target) {
            left = mid + 1;
        }
        else if (nums[mid] > target) {
            right = mid - 1;
        }
    }
    return -1;
}
```

