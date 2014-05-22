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
		解决什么问题：Algorithms，1.1节Web练习1，编写一个简单的Wget程序，熟悉基本的第三方库

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
		思路：编写一个Animal类，让Cat和Dog继承之，AnimalQueue维护一个队列；
		书上的解答思路：使用两个链表，Dog链表和Cat链表，每个结点维护一个order变量，表示进入队列的先后次序，
		order较小的那个更早进入队列。

### [DigitAdder.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/DigitAdder.java)
		解决什么问题：CTCI面试题2.5，将两个用链表表示的数相加并返回所求和；
		思路：从数的低位到高位逆向存储，比如617，表示为7->1->6，遍历两个链表，按位相加再加上借位carry生成
		结果链表，最后将较长链表的剩余部分链入结果链表即可；
		策略：carry = sum / 10; sum = sum % 10。

### [DoublyLinkedList.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/DoublyLinkedList.java)
		解决什么问题：Algorithms，1.3节web练习21，实现一个双链表，并在此基础上提供一个可正向和反向迭代，并
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
		解决什么问题：CTCI面试题2.6，判断一个链表中是否有环，且找出环的头结点；
		思路：链表类问题的经典“龟兔指针”法，从头结点开始，龟指针一次走一步，兔指针一次走两步，若龟兔再次遇上，
		说明链表有环，然后用一个指针从这个相遇的结点开始走，另一个指针从头结点开始走，两个指针每次一步，最后再次
		相遇的那个结点就是链表环的头结点。
				
### [Hanoi.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/Hanoi.java)
		解决什么问题：CTCI面试题3.4，不用递归，模拟求解汉诺塔游戏的步骤；
		思路：既然不能用递归，那么本质上是用栈来模拟递归的过程，先编写一个递归版本的汉诺塔程序，观察发现
		只要倒着将递归算法的过程参数做压栈处理即可，每次从堆栈中弹出参数，发现参数不是基本情况，就按照归纳情况
		分解，然后倒着压入堆栈，若发现参数满足基本情况，则打印，栈清空后程序结束。

### [Parentheses.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/Parentheses.java)
		解决什么问题：Algorithms，1.3节习题1.3.4，判断括号是否匹配；
		思路：遇到左括号就压栈，遇到右括号就弹出栈顶元素，然后判断二者是否匹配。

### [PartitionSolver.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/PartitionSolver.java)
		解决什么问题：CTCI面试题2.4，对链表进行分区，传入参数x，比x小的放前面，比x大的放后面；
		思路：检查x是否存在于链表中，若不存在抛出异常，否则交换x与头结点的值，从第二个结点开始遍历，将比x小的
		结点删除然后头插入链表（现在看来这个算法实现的有问题，首先题目并没有说x一定要存在于链表中，其次，该方法
		没有书上标准解答来的优雅）；
		书上的解题思路：维护四个指针beforeStart, beforeEnd, afterStart, afterEnd，遍历链表，将比x小的放入
		beforeXXX维护的链表中，比x大的放入afterXXX维护的链表中，最后归并两个链表即可。

### [QueueWithTwoStacks.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/QueueWithTwoStacks.java)
		解决什么问题：Algorithms，1.3节web练习31，用两个栈模拟队列，队列操作满足摊销的常量时间；
		思路：维护一个inStack和outStack，当dequeue操作发生时，将inStack中元素全部压入outStack中，然后弹出，
		当enqueue操作发生时，将元素压入inStack中；

### [SinglyLinkedList.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/SinglyLinkedList.java)
		解决什么问题：CTCI面试题2.1，2.2和2.3题，2.1题：从一个无序链表中删除重复元素；2.2题：求链表倒数第k个
		结点；2.3题：在只知道链表中某结点指针的情况下删除该结点；
		思路：
		2.1题思路：若允许使用额外的临时缓冲区，则创建一个hash表，并从头遍历一遍链表，若第一次碰到就存入hash表中，
		若不是第一次碰到就删除该元素（维护一个prev指针指向前一个元素），时间复杂度O(N)；2. 若不允许使用额外的
		缓冲区，则归并排序该链表，然后遍历删除重复元素，时间复杂度O(nlogn)；
		2.1题策略：findMiddle()函数使用了龟兔指针法来确定链表中间结点，方法龟指针一次走一步，兔指针一次走两步，
		兔指针走到最后一个结点时龟指针正好在链表中间，这也是常见的链表题；
		2.2题思路：“龟兔指针”法，兔指针先走k-1步，然后两个指针一起走，兔指针走到链表最后一个结点时，龟指针即指向
		倒数第k个结点，注意代码鲁棒性；
		2.3题思路：从要被删除的结点开始遍历，将后面的结点值向前拷贝，最后删除最后一个结点即可；

### [StackSorter.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/StackSorter.java)
		解决什么问题：CTCI面试题3.6，编写一个算法对栈进行排序，可以用额外的栈，但不允许将栈中元素拷贝到其他
		数据结构中（比如数组）；
		思路：创建两个临时栈tmpStk1和tmpStk2，将栈中元素e压入tmpStk1，若发现tmpStk1中原有元素小于e，则先弹出该
		元素压入tmpStk2，将e压入tmpStk2，然后将tmpStk2元素全部压入tmpStk1，最后将tmpStk1中元素全部压入目标栈

Project2 - 编程大作业 - Randomized Queues and Deques
----------------------------------------------------

[Problem Specification](http://coursera.cs.princeton.edu/algs4/assignments/queues.html)

1. [Deque.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/Deque.java)

2. [RandomizedQueue.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/RandomizedQueue.java)

3. [Subset.java](https://github.com/leesper/algorithms_4ed/blob/master/project2/Subset.java)

		解决什么问题：实现Deque和RandomizedQueue两种基本数据类型，熟悉链式实现和动态数组实现方式，然后实现一个
		Subset程序，传入参数k，然后随机地从标准输入中读k个值。
