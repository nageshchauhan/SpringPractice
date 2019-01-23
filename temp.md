---Video 146

# 1.5 Enhancement

## Queue (I, v1.5)

It is child interface of `Collection`. If we want to represent a group of individual objects prior to processing then we should go for Queue.
For eg. before sending SMS message, all mobile numbers need to store in some data structure. The order in which we added mobile number in the same order message will be delivered. For this first in first out requirement, Queue is best choice.

### [Add diagram of queue hierarchy](queue_hierarchy_diagram.png)

Usually `Queue` follows first in first out order, but based on our requirement we can implement our own priority order as well `(PriorityQueue)`.
From 1.5 onwards, `LinkedList` class also implements `Queue` interface. `LinkedList` based implementation of `Queue` always follows FIFO order.

#### Queue interface specific methods

1. `boolean offer(E e)` <br>
Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions. When using a capacity-restricted queue, this method is generally preferable to add(E), which can fail to insert an element only by throwing an exception.

2. `boolean add(E e)` <br>
Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions, returning true upon success and throwing an `IllegalStateException` if no space is currently available.

3. `E peek()` <br>
Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.

4. `E element()` <br>
Retrieves, but does not remove, the head of this queue. This method differs from peek only in that it throws an exception if this queue is empty.

5. `E poll()` <br>
Retrieves and removes the head of this queue, or returns null if this queue is empty.

6. `E remove()` <br>
Retrieves and removes the head of this queue. This method differs from poll only in that it throws an exception if this queue is empty.

## PriorityQueue (Class, v1.5)

--> If we want to represent a group of individual objects prior to processing according to some priority then we should go for `PriorityQueue`. <br>
--> The priority can be either default natural sorting order or customized sorting order defined by `Comparator`. <br>
--> Insertion order is not preserved and it is based on some priority. <br>
--> Duplicate objects are not allowed. <br>
--> If we are depending on default natural sorting order, compulsory objects should be homogeneous and comparable otherwise we will get ClassCastExcecption at runtime. <br>
--> If we are defining our own sorting by comparator then object need not be homogeneous and comparable. <br>
--> null is not allowed even as first element. <br>


### Constructors

1. `PriorityQueue q = new PriorityQueue()` <br>
Creates an empty priority queue with default initial capacity (11) and all object will be inserted according to default natural sorting order.

2. `PriorityQueue q = new PriorityQueue(int initialCapacity)` <br>
Creates an empty priority queue with specified initial capacity that orders its element according to their natural ordering.

3. `PriorityQueue q = new PriorityQueue(int initialCapacity, Comparator c)` <br>
Creates an empty priority queue with specified initial capacity that orders its element according to specified comparator.

4. `PriorityQueue q = new PriorityQueue(SortedSet s)` <br>
Creates priority queue containing the elements in the specified sorted set.

5. `PriorityQueue q = new PriorityQueue(Collection c)`<br>
Creates priority queue containing the elements in the specified collection.

6. `PriorityQueue q = new PriorityQueue(PriorityQueue q)`
Creates priority queue containing the elements in the specified priority queue.


#### Example:

```java
import java.util.PriorityQueue;
public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue q = new PriorityQueue();
        System.out.println(q.peek()); // return null if queue is empty
        //System.out.println(q.element());//throw RE: NoSuchElementException if queue is empty
        for(int i=0;i<10;i++){
            q.offer(i);
        }
        
        System.out.println(q);
        System.out.println(q.poll()); //remove and return head element
        System.out.println(q);
    }
}
```

Output:

```java
null
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
0
[1, 3, 2, 7, 4, 5, 6, 9, 8]
```

Note: Some platform won't provide proper support for Thread Priorities and PriorityQueue.

#### Example2:

```java
import java.util.Comparator;
import java.util.PriorityQueue;
public class PriorityQueueEx2 {
    
    public static void main(String[] args) {
		PriorityQueue q = new PriorityQueue(15, new MyComparator());
        
        q.offer("A");
        q.offer("Z");
        q.offer("L");
        q.offer("B");
        System.out.println(q);
    }
}
class MyComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}
```

Output:

```java
[Z, L, B, A]
```

---------------------------------------------video 147

# 1.6 Enhancement in Collection framework

As a part of 1.6 version, following two concepts introduced in collection framework

## 1. `NavigableSet`
It is child interface of SortedSet and it defines several methods for navigation purposes.

NavigableSet defines following methods:

1. `E floor(e)` <br>
Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.

2. `E lower(e)` <br>
Returns the greatest element in this set strictly less than the given element, or null if there is no such element.

3. `E ceiling(e)`
Returns the least element in this set greater than or equal to the given element, or null if there is no such element.

4. `E higher(e)`
Returns the least element in this set strictly greater than the given element, or null if there is no such element.

5. `E pollFirst()`
Retrieves and removes the first (lowest) element, or returns null if this set is empty.

6. `E pollLast()`
Retrieves and removes the last (highest) element, or returns null if this set is empty.

7. `NavigableSet<E> descendingSet()`
Returns NavigableSet over the elements in this set, in descending order.

### Example

```java
import java.util.TreeSet;
public class NavigableSetExample {
    
    public static void main(String[] args) {
		TreeSet<Integer> t = new TreeSet<Integer>();
        t.add(10);
        t.add(20);
        t.add(30);
        t.add(40);
        t.add(50);
        
        System.out.println("t: "+t);
        System.out.println("t.ceiling(20) : "+t.ceiling(20));
        System.out.println("t.higher(20) : "+t.higher(20));
        System.out.println("t.floor(30) : "+t.floor(30));
        System.out.println("t.lower(30) : "+t.lower(30));
        System.out.println("t.pollFirst() : "+t.pollFirst());
        System.out.println("t.pollLast() : "+t.pollLast());
        System.out.println("t.descendingSet() : "+t.descendingSet());
        System.out.println("t : "+t);
    }
}
```

Output:

```java
t: [10, 20, 30, 40, 50]
t.ceiling(20) : 20
t.higher(20) : 30
t.floor(30) : 30
t.lower(30) : 20
t.pollFirst() : 10
t.pollLast() : 50
t.descendingSet() : [40, 30, 20]
t : [20, 30, 40]
```

## 2. `NavigableMap`

It is child interface of SortedMap. 

It defined several methods for navigation purposes:

1. `K floorKey(key)`
Returns the greatest key less than or equal to the given key, or null if there is no such key.

2. `K lowerKey(key)`
Returns the greatest key strictly less than the given key, or null if there is no such key.

3. `K ceilingKey(key)`
Returns the least key greater than or equal to the given key, or null if there is no such key.

4. `K higherKey(key)`
Returns the least key strictly greater than the given key, or null if there is no such key.

5. `Map.Entry<K,V> pollFirstEntry()`
Removes and returns a key-value mapping associated with the least key in this map, or null if the map is empty.

6. `Map.Entry<K,V> pollLastEntry()`
Removes and returns a key-value mapping associated with the greatest key in this map, or null if the map is empty.


### Example: 

```java
import java.util.TreeMap;
public class NavigableMapExample {
    
    public static void main(String[] args) {
		TreeMap<String, String> t = new TreeMap<String, String>();
        t.put("b", "banana");
        t.put("c", "cat");
        t.put("a", "apple");
        t.put("d", "dog");
        t.put("g", "gun");
        
        System.out.println("t: "+t);
        System.out.println("t.ceilingKey(c) : "+t.ceilingKey("c"));
        System.out.println("t.higherKey(e) : "+t.higherKey("e"));
        System.out.println("t.floorKey(e) : "+t.floorKey("e"));
        System.out.println("t.lowerKey(e) : "+t.lowerKey("e"));
        System.out.println("t.pollFirstEntry() : "+t.pollFirstEntry());
        System.out.println("t.pollLastEntry() : "+t.pollLastEntry());
        System.out.println("t.descendingMap() : "+t.descendingMap());
        System.out.println("t : "+t);
    }
}
```

Output: 

```java
t: {a=apple, b=banana, c=cat, d=dog, g=gun}
t.ceilingKey(c) : c
t.higherKey(e) : g
t.floorKey(e) : d
t.lowerKey(e) : d
t.pollFirstEntry() : a=apple
t.pollLastEntry() : g=gun
t.descendingMap() : {d=dog, c=cat, b=banana}
t : {b=banana, c=cat, d=dog}
```


##################

# Collections utility classes

Collections class defined several utility methods for collection object like sorting, searching, reversing etc.

### Sorting elements of List
Collection class defines following methods for sorting

1. `public static void sort(List<E> l)`
To sort based on default natural sorting order. In this case list should compulsory contain homogeneous and comparable objects otherwise will get ClassCastExcecption at runtime.
List should not contain null otherwise we will get NullPointerException at runtime.

2. `public static void sort(List<E> l, Comparator<E> c)`
To sort based on customized sorting order.

#### Example1:

```java
import java.util.ArrayList;
import java.util.Collections;
public class SortExample {
    
    public static void main(String[] args) {
		ArrayList list = new ArrayList();
        
        list.add("A");
        list.add("C");
        list.add("X");
        list.add("M");
        
        //list.add(10); // will throw ClassCastException on runtime
        //list.add(null); // will throw NullPointerException while comparing elements
        
        System.out.println("before sorting: "+list);
        Collections.sort(list);
        System.out.println("after sorting: "+list);
    }
}
```

Output:

```java
before sorting: [A, C, X, M]
after sorting: [A, C, M, X]
```

#### Example2:

```java
import java.util.ArrayList;
import java.util.Collections;
public class SortExample {
    
    public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
        
        list.add("A");
        list.add("C");
        list.add("X");
        list.add("M");
      
        System.out.println("before sorting: "+list);
        Collections.sort(list,new MyComparator());
        System.out.println("after sorting: "+list);
    }
}
class MyComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}
```

Output:

```java
before sorting: [A, C, X, M]
after sorting: [X, M, C, A]
```

-----------------------video 148

### Searching element in List

Collections clas defines following binary search methods:

1. `public static int binarySearch(List<E> l, E target)`
If the list is sorted according to default natural sorting order then we have to use this method.

2. `public static int binarySearch(List<E> l, E target, Comparator<E> c)`
We have to use this method if the list is sorted according to customized sorting order.

Scenarios:
--> The above search method internally uses binary search algorithm. <br>
--> Successful search returns index(positive value). <br>
--> Unsuccessful search returns insertion point (negative value). Note: insertion point start from -1. <br>
--> Insertion point is the location where we can place target element in sorted list.
--> Before calling binarySearch method, compulsory list should be sorted, otherwise we will get unpredictable result.
--> If the list is sorted according to Comparator, then at the time of search operation, we have to pass same Comparator object otherwise we will get unpredictable result.

#### Example1:

```java
import java.util.ArrayList;
import java.util.Collections;
public class SearchExample {
    
    public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
        
        list.add("A");
        list.add("C");
        list.add("X");
        list.add("M");
		list.add("b");
      
        System.out.println("before sorting: "+list);
        Collections.sort(list);
        System.out.println("after sorting: "+list);
        System.out.println("binarySearch(list,C): "+Collections.binarySearch(list, "C"));
        System.out.println("binarySearch(list,N): "+Collections.binarySearch(list, "N"));
    }
}
```

Output:

```java
before sorting: [A, C, X, M, b]
after sorting: [A, C, M, X, b]
binarySearch(list,C): 1
binarySearch(list,N): -4
```

#### Example2:

```java
import java.util.ArrayList;
import java.util.Collections;
public class SearchExample {
    
    public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
        
        list.add("A");
        list.add("C");
        list.add("X");
        list.add("M");
		list.add("b");
		
		MyComparator myComparator = new MyComparator();
		
        System.out.println("before sorting: "+list);
        Collections.sort(list, myComparator);
        System.out.println("after sorting: "+list);
        System.out.println("binarySearch(list,C,myComparator): "+Collections.binarySearch(list, "C", myComparator));
        System.out.println("binarySearch(list,N,myComparator): "+Collections.binarySearch(list, "N", myComparator));
        System.out.println("binarySearch(list,T): "+Collections.binarySearch(list, "T"));
    }
}

class MyComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}
```

Output:

```java
before sorting: [A, C, X, M, b]
after sorting: [b, X, M, C, A]
binarySearch(list,C,myComparator): 3
binarySearch(list,N,myComparator): -3
binarySearch(list,T): -6
```

**Note: ** For list of n elements in case of binarySearch method <br>
successful search result range: 0 to (n-1) <br>
unsuccessful search result range: -(n+1) to -1 <br>
Total result range: -(n+1) to (n-1) <br>

Eg. for 3 elements: <br>
successful search result range: 0 to 2 <br>
unsuccessful search result range: -4 to -1 <br>
Total result range: -4 to 2 <br>



### Reversing elements of List

Collections class defines following reverse methods to reverse elements of list

1. `public static void reverse(List<E> list)`

#### Example1:

```java
import java.util.ArrayList;
import java.util.Collections;
public class ReverseExample {
    
    public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
        
        list.add("A");
        list.add("C");
        list.add("X");
        list.add("M");
		list.add("b");
		
		System.out.println("Before reversing: "+list);
        Collections.reverse(list);
        System.out.println("After reversing: "+list);
    }
}
```

Output:

```java
Before reversing: [A, C, X, M, b]
After reversing: [b, M, X, C, A]
```

### reverse() vs reverseOrder()

We can use `reverse()` method to reverse order of elements of list whereas `reverseOrder()` to get reversed comparator.

Eg. Comparator c1 = Collections.reverseOrder(Comparator c);
if c is meant for ascending order then c1 will result in descending order.


--------------------------------------------------video 149------------

## Arrays class

It is an utility class to define several utility methods for array.

### Sorting elements of Array

1. `public static void sort(primitive[] arr)`
To sort primitive type array according to Natural sorting order. Note: for each primitive type there are overloaded sort function, so based on parameter type, Arrays will call corresponding method.

2. `public static void sort(Object[] arr)`
To sort according to natural sorting order.

3. `public static void sort(Object[] arr, Comparator c)`
To sort given object type array according to customized sorting order defined in comparator.

#### Example:

```java
import java.util.Arrays;
public class SortingArrayExample {
    
    public static void main(String[] args) {
		int []intArray = {10,50,30,6,4,3};
        System.out.println("primitive array before sorting");
        for(int a : intArray){
            System.out.print(a+", ");
        }
        
        Arrays.sort(intArray);
        System.out.println("\n primitive array After sorting");
        for(int a : intArray){
            System.out.print(a+", ");
        }
        //--------------------------------------//
        String []strArray = {"A","E","S","F","O"};
        System.out.println("\n String array before sorting");
        for(String a : strArray){
            System.out.print(a+", ");
        }
        
        Arrays.sort(strArray);
        System.out.println("\n String array after sorting");
        for(String a : strArray){
            System.out.print(a+", ");
        }
        
        Arrays.sort(strArray, new MyComparator());
        System.out.println("\n String array after sorting by comparator");
        for(String a : strArray){
            System.out.print(a+", ");
        }
    }
}
class MyComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
    
}
```

Output:

```java
primitive array before sorting
10, 50, 30, 6, 4, 3, 
 primitive array After sorting
3, 4, 6, 10, 30, 50, 
 String array before sorting
A, E, S, F, O, 
 String array after sorting
A, E, F, O, S, 
 String array after sorting by comparator
S, O, F, E, A, 
```

**Note:** Primitive arrays can be sort only based on default natural sorting order. Whereas we can sort object array either based on default natural sorting order or based on customized sorting order.


### Searching elements of Array

Arrays class defines the following binary search methods:

1. `public static int binarySearch(primitive[] p, primitive target)`

2. `public static int binarySearch(Object[] a, Object target)`

3. `public static int binarySearch(Object[] a, Object target, Comparator<Object> c)`

**Note:** All rules of Arrays class binarySearch() methods are exactly same as Collections class binarySearch() method.

#### Example:

```java
import java.util.Arrays;
public class SearchingArrayExample {
    
    public static void main(String[] args) {
		int []intArray = {10,50,30,6,4,3};
        Arrays.sort(intArray);
        System.out.println("intArray after sorting: ");
        for(int a : intArray){
            System.out.print(a+", ");
        }
        System.out.println("\nbinarySearch(intArray,6): "+Arrays.binarySearch(intArray, 6));
        System.out.println("binarySearch(intArray,80): "+Arrays.binarySearch(intArray, 80));
        
        //--------------------------------------//
        String []strArray = {"A","E","S","F","O"};
        Arrays.sort(strArray);
        System.out.println("String array after sorting");
        for(String a : strArray){
            System.out.print(a+", ");
        }
        System.out.println("\nbinarySearch(strArray,Z): "+Arrays.binarySearch(strArray, "Z"));
        System.out.println("binarySearch(strArray,O): "+Arrays.binarySearch(strArray, "O"));

        MyComparator comparator = new MyComparator();
        Arrays.sort(strArray, comparator);
        System.out.println("String array after sorting by comparator");
        for(String a : strArray){
            System.out.print(a+", ");
        }
        System.out.println("\nbinarySearch(strArray,E,comparator): "+Arrays.binarySearch(strArray, "E", comparator));
        System.out.println("binarySearch(strArray,H,comparator): "+Arrays.binarySearch(strArray, "H", comparator));
        System.out.println("binarySearch(strArray,L): "+Arrays.binarySearch(strArray, "L"));
    }
}
class MyComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
    
}
```

Output:

```java
intArray after sorting: 
3, 4, 6, 10, 30, 50, 
binarySearch(intArray,6): 2
binarySearch(intArray,80): -7
String array after sorting
A, E, F, O, S, 
binarySearch(strArray,Z): -6
binarySearch(strArray,O): 3
String array after sorting by comparator
S, O, F, E, A, 
binarySearch(strArray,E,comparator): 3
binarySearch(strArray,H,comparator): -3
binarySearch(strArray,L): -6
```

### Conversion of Array to List

`public static List asList(Object[] arr)`

This method won't create an independent `List` object. For the existing array, we are getting List view.

--> By using array reference if we perform any change automatically that change will be reflected to list. Similarly by using List reference if we perform any change that change will be reflected automatically to array.

--> By using list reference we can't perform any operation which varies the size otherwise we will get `UnsupportedOperationException` at runtime
Eg. 

```java
list.add("D"); // Invalid operation
list.remove("A"); //invalid operation
list.set(1,"N"); //valid operation
```

--> By using list reference, we are not allowed to replace with heterogeneous objects otherwise `ArrayStoreException` will be thrown at runtime.
Eg. `l.set(1, new Integer(20));`

#### Example:

```java
import java.util.Arrays;
public class ArrayAsListExample {
    
    public static void main(String[] args) {
		String []strArray = {"A","E","S","F","O"};
        
        List list = Arrays.asList(strArray);
        System.out.println("list: "+list);
        
        strArray[0] = "U";
        System.out.println("list: "+list);
        list.set(1, "P");
        for(String s:strArray){
            System.out.print(s+", ");
        }
        
        //list.add("XYZ"); //UnsupportedOperationException will be thrown
        //list.remove(2); //UnsupportedOperationException will be thrown
        //list.set(1, new Integer(5)); // ArrayStoreException will be thrown
	}
}
```

Output:

```java
list: [A, E, S, F, O]
list: [U, E, S, F, O]
U, P, S, F, O,
```


----------------------------------------150------------

## Need of concurrent collection

Traditional collection objects (like ArrayList, HashMap, HashSet etc) can be accessed by multiple threads simultaneously and there may be chance of data inconsistency problem, hence these are not thread safe.

Already present Thread safe collections (like Vector, Hashtable synchoronizedList() etc) are performance wise not upto the mark. Because for each operation even for read operation collection will be accessed by only one thread at a time hence it increases waiting time of Threads.

Another big problem with traditional collection is while one thread iterating collection, other threads are not allowed to modify collection object simultaneously. If we are trying to modify list with multiple thread then will get `ConcurrentModificationException` at runtime.

Example:

```java
import java.util.ArrayList;
import java.util.Iterator;
public class ConcurrentExample extends Thread {
    
    static ArrayList<String> al = new ArrayList<String>();
    
    public void run(){
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Child thread updating list");
        al.add("D");
    }
    
    public static void main(String[] args) throws InterruptedException {
        al.add("A");
        al.add("B");
        al.add("C");
        ConcurrentExample t = new ConcurrentExample();
        t.start();
        Iterator<String> itr = al.iterator();
        while(itr.hasNext()){
            System.out.println("main thread iterating list and current object is "+itr.next());
            Thread.sleep(1000);
        }
        System.out.println(al);
        
    }
}
```

Output:

```java
main thread iterating list and current object is A
main thread iterating list and current object is B
main thread iterating list and current object is C
Child thread updating list
Exception in thread "main" java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:859)
	at java.util.ArrayList$Itr.next(ArrayList.java:831)
	at ConcurrentExample.main(ConcurrentExample.java:22)
```

Hence these traditional collection objects are not suitable for scalable multi threaded environment. To overcome these problem java introduced concurrent collection in version 1.5


----------------------------151--------------

## Difference between Traditional collection and Concurrent collection

Concurrent collections are always thread safe.

Compared with thread safe traditional collection, performance of concurrent collection is high because of locking mechanism.

While one thread interacting with collection, the other thread are allowed to modify collection in safe manner.

Hence concurrent collections never throw `ConcurrentModificationException`.

The important concurrent classes are :
1. `ConcurrentHashMap`
2. `CopyOnWriteArrayList`
3. `CopyOnWriteArraySet`

-----------------------------------152--------------------

## ConcurrentMap (I, v1.5)

	Map
	 |
	 |
ConcurrentMap(I)
	 |
	 |
ConcurrentHashMap(Class)

ConcurrentMap defines following specific methods 

1. `V putIfAbsent(K key, V value)` <br>
This method will add the given Key Value pair as Entry if the key is not present in the map. If given key is already present then it will not override the value of existing one.

`Map` interface provides `put(k,v)` method which **will override** the value of given key if the key is already present in map.
Whereas `putIfAbsent(k,v)` of `ConcurrentMap` **will not override** the value of given key if the key is alreayd present.

#### Example:

```java
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
public class Example{
	public static void main(String[] args){
		ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.putIfAbsent("A", "A");
        map.putIfAbsent("A", "B");
        System.out.println(map);
	}
}
```

Output:

```java
{A=A}
```

2. `boolean remove(K key, V value)` <br>
Removes the Entry if the key associated with specified value. In short while remove Entry from ConcurrentMap, it checks key and value both, if matched then only it will remove.

Example:

```java
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
public class Example{
	public static void main(String[] args){
		ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("A", "A");
        map.remove("A", "B");
        System.out.println(map);
        map.remove("A", "A");
        System.out.println(map);
	}
}
```

Output:

```java
{A=A}
{}
```

3. `boolean replace(K key, V oldValue, V newValue)` <br>
Replace the oldValue with newValue only if map contains key mapped to oldValue.

Example:

```java
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
public class Example{
	public static void main(String[] args){
		ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("A", "A");
        map.remove("A", "B");
        System.out.println(map);
        map.remove("A", "A");
        System.out.println(map);
	}
}
```

Output:

```java
{A=A}
{A=C}
```

---------------------------------------------153-----

## ConcurrentHashMap [class, v1.5]

--> Underlying data structure is Hashtable.
--> ConcurrentHashMap allows concurrent read and threas safe update operations.
--> To perform read operation, thread won't require any lock. But to perform update operation, thread requires lock, but the lock of only a particular part of map(bucket level/segment leve).
--> Hashtable class uses object level lock for thread safety, but instead of whole map, concurrent update achieved by internally dividing map into Smaller portion which is defined by concurrency level.
--> The default concurrency level is 16.
--> ConcurrentHashMap allows simultaneous read operation and simultaneous 16 write/update operations by default.
--> `null` is not allowed for both key and value.
--> while one thread iterating, the other thread can perform update operation and ConcurrentHashMap never throw `ConcurrentModificationException`.

### Constructors

1. `ConcurrentHashMap chm = new ConcurrentHashMap();` <br>
creates an empty ConcurrentHashMap with default intitialCapacity (16), default fill ration (0.75) and default concurrency level (16).

2. `ConcurrentHashMap chm = new ConcurrentHashMap(int initialCapacity);`

3. `ConcurrentHashMap chm = new ConcurrentHashMap(int initialCapacity, float fillRatio);`

4. `ConcurrentHashMap chm = new ConcurrentHashMap(int initialCapacity, float fillRatio, int concurrencyLevel);`

5. `ConcurrentHashMap chm = new ConcurrentHashMap(Map m);`
