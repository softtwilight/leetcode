# 二分查找
定义一个predicate `p`和一个有序的搜索空间`S`，那么二分查找能且只能在下列条件满足的时候使用：  
对`x` 属于 `S`，对于所有`y > x` 有 `p（x）-> p(y)` 即p(x) is true, p(y) is true。

也就是有一个回答true 和 false的问题，在回答true之后的点都是true，在回答false之前的点都是false。  
我们可以通过二分查找找到最小的合法的解，也就是最小的使`p（x）= true`的x；

## 解答二分查找问题的一般思路：

1. 构建一个合适的predicate，然后确定哪个解是我们要寻找的，第一个true还是最后一个false。
2. 在二分查找过程中执行predicate。

这样抽象的好处是，把问题还原为一个yes or no的问题，然后去找满足这个问题的最小的解。  
例如对于最简单的在有序数组`arr`中查找`target`值的问题，空间S是`arr`，predicate 是`x -> arr[x] >= target;`。

实现之前的第一件事要弄清楚搜索上下限的含义，是`[lo, hi]`闭区间还是开区间`[lo, hi)`, `(lo, hi)`, 明白了这点才会在移动上下限的时候选择合适的方式，这里很容易出bug。  

找最小true的一般代码：  

``` java
int binarySearch(int[] arr, int lo, int hi, Predicate p) {

	while (lo < hi) {
	    int mid = lo + (hi - lo) / 2; // 确保上界会移动
	    if (p(mid)) {
            hi = mid;
        } else {
            lo = mid + 1;
        }
	}
	if (!p(lo)) return notFoundNum; 
	return lo; // lo is the least x for which p(x) is true
}
```
找最大false的一般代码：  

``` java
int binarySearch(int[] arr, int lo, int hi, Predicate p) {

	while (lo < hi) {
	    int mid = lo + (hi - lo + 1) / 2;  // 确保下界会移动
	    if (p(mid)) {
            hi = mid - 1;
        } else {
            lo = mid;
        }
	}
	if (p(lo)) return notFoundNum; 
	return lo; // lo is the biggest x for which p(x) is false
}
```

#### 一些注意点：  
1. predicate的设计要尽量简单。
2. 要确定上下界包含了可能的解。
3. 小心上下界不会改变的情况，可以用二个元素的输入来测试。
4. 取mid的时候小心int的overflow，用 `lo + (hi - lo) / 2 或  lo + (hi - lo + 1) / 2`。