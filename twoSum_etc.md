### TwoSum I
given array and int `target` , if we assume that there exists two numbers in the array whose sum is `target`, how do you find the index of the two numbers?

ex. `nums = [3, 1, 3, 6], target = 6`, the algorithm should return arrya `[0, 2]` because 3 + 3 = 6

### Solution - brute force
```
for each number X in list of numbers:
    for each number Y in list of numbers starting from X:
        if X+Y = target, return indices
```


```java
int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
        // j starts at i + 1
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] == target){
                return new int[] {i, j};
            }
        }
    }
    // if such numbers don't exist
    return new int[] {-1, -1};
}
```

Analysis: time complexity `O(n^2)`, space complexity `O(1)`

### Time Optimization - with HashMap

Idea:
```
instantiate an empty dict
for each `number` in list of numbers:
    result = subtract `number` from `target`
    look for result in the dict (instant lookup)
    if found:
        return index of number and idex of dict lookup
    else:
        add number to dict as key with value being the index
```

```Python
def twoSum(self, nums: List[int], target: int) -> List[int]:
    map = {} # mapping from numbers to index
    for i in range(len(nums)):
        res = target - nums[i] # res ofr residual: nums[i] + res = target
        if res in map: # O(1) look up - this means we have a number
            return [map[res], i] # map[res] is the index of res
        else:
            map[nums[i]] = i # put key-value pair (num, index) into the map
```

Analysis: Time complexity O(N), Space complexity O(N) to store the dict

--------

Let's have a class `TwoSum` with two APIs: add() and find()

```java
class TwoSum {
    // add a number
    public void add(int number);
    // find if there exists two numbers s.t. the sum is `value`
    public boolean find(int value);
}
```

Implementation:

```java
class TwoSum {
    Map<Integer, Integer> freq = new HashMap<>();

    public void add(int number) {
        freq.put(number, freq.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (Integer key : freq.keySet()){
            int other = value - key;
            // case 1: ex. [3, 3, 2, 5] find(6)
            if (other == key && freq.get(key) > 1)
                return true;
            // case 2 ex. [3, 3, 2, 5] find(7)
            if (other != key && freq.containsKey(other))
                return true;
        }
        return false;
    }
}
```

Analysis: time complexity: add() `O(1)`, find() `O(N)`
          space complexity: O(N)


Optimize find()

```java
class TwoSum {
    Set<Integer> sum = new HashSet<>();
    List<Integer> nums = new ArrayList<>();

    public void add(int number) {
        for (int n : nums) {
            // set storing all possible sums
            sum.add(n + number); 
        }
        nums.add(number);
    }

    public boolean find(int value) {
        return sum.contains(value);
    }
}
```

now find() is O(1)

-----
### Conclusion
For unordered array, we can use HashMap or HashSet
Or.. we can sort them first and use two  pointers 

```java
int[] twoSum(int[] nums, int target) {
    int left = 0, right = nums.length -1;
    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) {
            return new int[] {left, right};
        }
        else if (sum < target){
            left++;
        }
        else if (sum > target){
            right--;
        }
    }
    // does not exist
    return new int[] {-1, -1};
}
```

------

## 3SUM?

Given an array `nums` of *n* integers, are there elemnents a, b, c in `nums` such that `a + b + c = 0`?
Find all unique triplets in the array which gives the sum of zero

```python
def threeSum(self, nums: List[int]) -> List[List[int]]:

```