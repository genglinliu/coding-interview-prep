
算法逻辑 - 随机选取元素交换
## Logic:
starting from the first position `i = 0`, randomly choose a position and swap the chosen element with the `i-th` element

```java
// random number generator over an interval
int randInt(int min, int max);

// given array, shuffle the elements:
void shuffle(int[] arr) {
    int n = arr.length();
    for (int i = 0; i < n; i++) { // note: (n-1) is fine too
        int rand = randInt(i, n-1);
        swap(arr[i], arr[rand]);
    }
}
```

**Correctness**：
Has to produce `n!` results after shuffling
because the permutation of `n` distinct objects is `n!`

### Monte Carlo 
idea: repeat running the same program for a large number of times and validate the correctness of the algorithm by showing that the probablity distribution meets our expetation

psuedo code:
```java

void shuffle(int[] arr);

// monte carlo
int N = 1000000
HashMap count; // histogram
for (i = 0; i < N; i++) { 
    int[] arr = {1, 2, 3}; // just test on a small array 
    shuffle(arr);
    count.get(arr) += 1;
}
for (int freq : count.values()){
    print(freq / N + " "); // frequency
}
```