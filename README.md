algorithms_4ed --- MOOC Coursera，Robert Sedgewick教授的“算法”公开课编程作业以及课后习题解答
=========================================================================================

### Author: [Leesper](pascal7718@gmail.com)

Project1 - 动态连通性问题的并查集算法 - 课后习题
----------------------------------------------

### [Binomial.java](https://github.com/leesper/algorithms_4ed/blob/master/project1/Binomial.java)
		解决什么问题：习题1.1.27，原基于递归的算法效率不高，采用动态规划的方法设计一个更的算法；
		思路：memoization，将第一次计算的值存入表中，用查表代替计算；
		策略：calculated[][]初始化为浮点数最大值（无穷大），计算过程中每次先查表，若已计算过，则直接返回，
		若没有计算过，则先将计算得到的值写入表中，然后再返回。

### [Rational.java](https://github.com/leesper/algorithms_4ed/blob/master/project1/Rational.java)
		解决什么问题：习题1.2.16，根据题目给出的API Specification编写一个不可变数据类型“有理数”，
		支持加减乘除；
		思路：将分子和分母定义成final类型，即为不可变语义。

### [Transaction.java](https://github.com/leesper/algorithms_4ed/blob/master/project1/Transaction.java)
		解决什么问题：习题1.2.13和1.2.14，通过编写Transaction类来学习Java的基本OOP机制，如toString(),
		equals(), compareTo(), hashCode()；

### [Wget.java](https://github.com/leesper/algorithms_4ed/blob/master/project1/Wget.java)
		解决什么问题：《Algorithms》1.1节Web练习1，编写一个简单的Wget程序，熟悉基本的第三方库

Project1 - 编程大作业 - Percolation
------------------------------------------------------

[Problem Specification](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)

1. [Percolation.java](https://github.com/leesper/algorithms_4ed/blob/master/project1/Percolation.java)

2. [PercolationStats.java](https://github.com/leesper/algorithms_4ed/blob/master/project1/PercolationStats.java)

		解决什么问题：物理化学研究中的“Percolation”模型，并查集算法的实际应用；
		思路：将渗滤系统的N × N网格建模为一维布尔值数组，初始化每个网格为堵塞态（false），然后不停循环，
		随机选择一个网格设置为true并与周围（上下左右）导通网格相连通（通过并查集算法），循环一直持续到
		整个渗滤系统从顶部导通到底部为止，统计此时导通的网格数，算出占全部网格百分比，多执行几次这样的计算
		将得到的频率值求期望和标准差，得到该系统导通的概率；
		策略：1. 设置一个虚拟顶部和虚拟底部，让第一行的导通网格与虚拟顶部连通，最后一行的导通网格与虚拟底部
		相连通，通过并查集算法检查虚拟顶部和虚拟底部就知道该渗滤系统是否是导通的，这种虚拟网格能够避免逐个
		检查顶部网格和底部网格，O(N^2)；2. 通过一个函数xyTo1D来将二维网格矩阵映射到一维数组是一个重要技巧，
		因为并查集算法中的数据结构就是一个一维数组；3. Monte-Carlo模拟法估算阀值：渗滤系统导通时导通网格所
		占比例为对该阀值的一次估计，重复N次得到N个估计，其样本平均值为对该阀值的概率估计。

Project2 - 基本数据结构 - 课后习题
---------------------------------

### [AnimalQueue.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/AnimalQueue.java)
		解决什么问题：“Cracking the Coding Interview”（以下简称CTCI）面试题3.7，让实现一个队列，队列中可
		存放猫和狗两种动物，要求实现enqueue(), dequeueAny(), dequeueCat()和dequeueDog()等操作；
		思路：编写一个Animal类，让Cat和Dog继承之，AnimalQueue维护一个队列

### [DigitAdder.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/DigitAdder.java)
		解决什么问题：CTCI面试题2.5，将两个用链表表示的数相加并返回所求和；
		思路：从数的低位到高位逆向存储，比如617，表示为7->1->6，遍历两个链表，按位相加再加上借位carry生成
		结果链表，最后将较长链表的剩余部分链入结果链表即可；
		策略：carry = sum / 10; sum = sum % 10;

### [DoublyLinkedList.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/DoublyLinkedList.java)
		解决什么问题：《Algorithms》1.3节web练习21，实现一个双链表，并在此基础上提供一个可正向和反向迭代，并
		可以添加，删除和覆盖结点的迭代器，熟悉iterator设计模式；
		思路：提供addFirst(), addLast(), removeFirst()和removeLast()四个主要功能，然后实现一个ListIterator，
		提供正向迭代（hasNext(), next(), nextIndex()），反向迭代（hasPrevious(), previous(), 
		previousIndex()）和add(), remove()以及set()功能；
		策略：使用链表数据结构中经典的“dummy pattern”，用pre和post分别代表两个“伪结点”，pre在第一个结点之前，
		post在最后一个结点之后，每个结点有prev和next指针，这样可以避免考虑额外情况，提高代码鲁棒性，具体编写
		链表操作时要注意对每个结点的prev和next指针进行维护，并考虑极端情况（链表为空）；迭代器的实现中，我们
		用lastAccessed变量表示最近一次next()和previous()访问过的结点，nextIndex变量表示下一次next()调用将要
		访问的结点，然后围绕lastAccessed编写添加，删除和设置操作。

### [FindCircular.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/FindCircular.java)
### [Hanoi.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/Hanoi.java)
### [Parentheses.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/Parentheses.java)
### [PartitionSolver.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/PartitionSolver.java)
### [QueueWithTwoStacks.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/QueueWithTwoStacks.java)
### [SinglyLinkedList.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/SinglyLinkedList.java)
### [StackSorter.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/StackSorter.java)

Project2 - 编程大作业 - Randomized Queues and Deques

1. [Deque.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/Deque.java)
2. [RandomizedQueue.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/RandomizedQueue.java)
3. [Subset.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/Subset.java)
