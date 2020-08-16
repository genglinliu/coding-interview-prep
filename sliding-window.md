
This chapter is written in C++

`unordered_map ` is a map container (dictionary), and `count(key)` is the function to check if a key exists in the map.

use `map[key]` to access the corresponding value. Note: If key does not exist, C++ will automatically create one and initialize it to 0

So `map[key]++` means `map.put(key, map.getOrDefault(key, 0) + 1)` in java. (if we have the value, add 1 to it)

### 1. 最小覆盖子串 
given a string S, a string T, find the shortest substring in S such that it contains every character in T

example:\
input: S = "ADOBECODEBANC", T = "ABC"\
output: "BANC"\

If such substring does not exist, output ""\
If such substring exist, it is unique

Brute force solution: just find one substring first and see if there is any other smaller substring

```
for (int i = 0; i < s.size(); i++) {
    for (int j = i+1; j < s.size(); j++) {
        if s[i:j] contains every char in t:
            update result
    }
}
```
Complexity: O(N^2)

#### sliding window

1. Use left-right pointers on S. Init left = right = 0, and we call the interval [left, right] a window

2. we first increase the window `[left, right]` by moving the right pointer, until the substring inside the window meets our requirement (has all chars in t)

3. Now we stop increase to the right and instead we shrink the window from the left side, until the requirement is no longer met. Now everytime we increment `left` we need to update the result

4. repeat step 2 and 3 until `right` hits the end of `s`

**step 2 is finding a solution, step 3 is optimizing it**

s: ['E', B, B, A, N, C, F]\
t: [A, B, C]

needs = {A:1, B:1, C:1}\
window = {A:0, B:0, C:0}

Now Let's look at a bigger window:\
s: ['E, B, B, A, N, C', F]  
t: [A, B, C]

needs = {A:1, B:1, C:1}\
window = {A:1, B:2, C:1}

Then shrink:\
s: [E, B, 'B, A, N, C', F]  
t: [A, B, C]

needs = {A:1, B:1, C:1}\
window = {A:1, B:1, C:1}

Keep moving to the left and it breaks:\
s: [E, B, B, 'A, N, C', F]  
t: [A, B, C]

needs = {A:1, B:1, C:1}\
window = {A:1, B:0, C:1}

Algorithm terminates

pseudo code:

```java
string s, t;
int left = 0; right = 0;
string res = s;

while(right < s.size()) {
    window.add(s[right]);
    right++;
    // if the requirement is met, shrink
    while(window is good) {
        res = minLen(res, window);
        window.remove(s[left]);
        left++;
    }
}
return res;
```

how to decide if the `window` aka `s[left...right]` meets our condition?

Solution: 2 maps. `needs` and `window`

```cpp
string minWindow(string s, string t) {
    // 在s中找t的最小覆盖子串
    int left = 0; right = 0;
    int start = 0, minLen = INT_MAX;

    // two counters
    unordered_map<char, int> window;
    unordered_map<char, int> needs;
    for (char c : t) {
        needs[c]++;
    }

    // keep track of the letters that already met the confition
    int match = 0;

    while (right < s.size()) {
        char c1 = s[right];
        if (needs.count(c1)) { // if c1 exists in need{}
            window[c1]++; // add c1 to window
            if (window[c1] == needs[c1]) // met condition
                match++;
        } 
        right++; // expand

        // if the window is large enough -> shrink
        while (macth == needs.size()) {
            if (right - left < minLen) { // init at maxint
                // update the substring position and length
                start = left;
                minLen = right - left;
            }
            char c2 = s[left];
            if (needs.count(c2)) { // if c2 exist
                window[c2]--; // update windows{}
                if (window[c2] < needs[c2]) 
                    match--; // then break the while loop
            }
            left++; // shrink
        }
    }
    // return "" if none but smallest substr otherwise
    return minLen == INT_MAX ?
                "" : s.substr(start, minLen);

```

Analysis: Time complexity: O(M + N) where M and N are the length of s and t. Because we used a for loop to traverse `t` to initialize `needs`, that's O(N); and the two while loops will execute `2M` times at most, so O(M)

--------
Problem 2: Find anagram - given a string `s` and a non-empty string `p` 找到s中所有是p的字母异位词的子串，返回这些子串的起始索引

字母异位 (anagram) - letters are the same but not in the same sequence

example:  
input: s: "cbaebabacd", p: "abc"\
output: [0, 6]\
because "cba" and "bac"

solution: same idea, slide the window until the condition is met and then shrink back until it's no longer met. Update the result. 

```cpp
vector<int> findAnagrams(string s, string t) {
    // return an array
    vector<int> res;
    int left = 0, right = 0;
    unordered_map<char, int> needs;
    unordered_map<char, int> window;
    for (char c : t){
        needs[c]++; // initialize needs{}
    }
    int match = 0;

    while (right < s.size()) {
        char c1 = s[right];
        if (needs.count(c1)) {
            window[c1]++;
            if (window[c1] == needs[c1]) {
                match++;
            }
        }
        right++; // expand

        while (match == needs.size()) {
            // if the size of the window is right
            // put the starting index left to res
            if (right - left == t.size()) {
                res.push_back(left);
            }
            char c2 = s[left];
            if (needs.count(c2)) {
                window[c2]--;
                if (window[c2] < needs[c2]){
                    match-;
                }
            }
            left++;
        }
    }
    return res;
}
```

The only difference from the last problem is that this time we're looking for substrings of the same length intead of shortest length


---------
3. 无重复字符的最长子串
longest substring with all unique characters

example:\
input: "abcabcbb"\
output: 3\
becasue: "abc"

input: "pwwkew"    output: 3

Note that you're looking for substring not sub-sequence, so it has to be contiguous 

solution: slide window to right until you find repeated letters in the window, then shrink. After you no longer have repeated characters, slide to right again until the end of string.

```cpp
int lengthOfLongestSubstring(string s) {
    int left = 0;
    right = 0;
    unordered_map<char, int> window;
    int res = 0; // record the length of substring

    while (right < s.size()) {
        char c1 = s[right];
        window[c1]++; // add a char to map
        right++;

        while (window[c1] > 1) {
            char c2 = s[left]; // get left-most char
            window[c2]--; // remove it from the map
            left++; // shrink
        }
        // update res - whenever the window gets larger
        res = max(res, right-left); 
    }
}
```

解题框架

```cpp
int left = 0, right = 0;

while (right < s.size()) {
    window.add(s[right]);
    right++;

    while (condition is met) {
        window.remove(s[left]);
        left++;
    }
}
```

The data structure for `window` is often a dict, and the condition verifier is often the difficult part. 

Python3 solution for the minWindow Problem

```python
class Solution:
    def minWindow(self, s: str, t: str):
        # initialization
        start, min_len = 0, float('inf')
        left, right = 0, 0
        res = s

        # 2 dicts as counters
        needs = Counter(t)
        window = collections.defaultdict(int)
        # defaultdict 在key不存在的时候默认为0 

        match = 0

        while right < len(s):
            c1 = s[right]
            if needs[c1] > 0:
                window[c1] += 1
                if window[c1] == needs[c1]:
                    match +=
            right += 1

            while match == len(needs):
                if right - left < min_len:
                    min_len = right - left
                    start = left
                c2 = s[left]
                if needs[c2] > 0:
                    window[c2] -= 1
                    if window[c2] < needs[c2]:
                        match -= 1
                left += 1
    return s[start:start+min_len] if min_len != float('inf') else ""

```