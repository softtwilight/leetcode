## Depth-First Search
dfs的题很多都是Tree（graph），有着分形一样的结构，也就是问题的解和子问题的解是同构的。
这样我们可以解一个子问题的方式求最终的解。

关键是构建一个Function，满足两点：
1. 有终止条件，而且越执行越接近终止条件 
2. 当前的解可以通过求子问题的解来求出。

首先我们需要明白这个Function做了一件什么事情，有时候这个Function并不完全等同于题目要求解答的问题，但是存在某种关联。
比如我们在#236题里看到的，Function的含义是比题目要求解的问题更大的，这个是比较难的地方。
一般来说这种关系是很容易发现的，不少题甚至是一样的。

然后我们关注怎么构建这个Function，终止条件一般是边界条件或者满足题意的值。
然后是具体的过程，这时候我们可以假想，这个Function已经写出来了，然后还是正确的。
我们可以用这个function 求子问题的解。
比如说判断两个Tree， A B是否相同，相等于同时满足下列三个条件：
1. A.val == B.val
2. A.left == B.left
3. A.right == B.right

然后23就是我们运用这个function的地方。
这样加上边界条件就构成了我们最终的Function：
``` java
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) return q == null;
        if (q == null) return false;
        return  p.val == q.val
                && isSameTree(p.left, q.left) 
                && isSameTree(p.right, q.right);
    }
```


DFS是我非常喜欢的算法（或者说递归）。首先一般很简洁，美；第二是思维的量不大，你只需要关于局部问题，当前这一层的逻辑和这一层与下一层的关系，
然后就可以解决非常复杂的问题了，是一种非常好的抽象。
