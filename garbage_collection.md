------------------174------------------
Garbage Collection:

Introduction
The ways to make an object eligible for GC
The method for requesting JVM to run GC
Finalization

# Introduction

In old languages like c++, programmer is responsible to create new object and to destroy useless objects. Usually programmer taking much care while creating objects and neglecting destruction of useless objects. Because of neglectance, at certain point for creation of new object, sufficient memory may not be available (because total memory filled with useless objects). In this case the application will down with memory problem, hence OutOfMemoryError is very common problem in old languages.

But in java, programmer is responsible only for creation of objects, and programmer is not responsible for its destruction. Sun people provided an assistant to destroy useless objects. This assistant is always running in backgroud (Daemon Thread) and destroy useless objects. Just because of this assistant, the chance of failing java with memory problem is low. This assistant is nothing but **Garbage Collector**.

Hence the main objective of garbage collector is to destroy useless objects.

-------------------------------------------

# The ways to make an object eligible for GC

Even though programmer is not responsible to destroy useless objects, it is highly recommended to make object eligible for GC if it is no logner required. 

An object is said to be eligible for GC iff it doesn't contain any reference variable.

## Following are various ways to make an object eligible for GC:

### 1. Nullifying the reference variable

If an object no longer required then assign null to all its reference variable then that object automatically eligible for GC. This approach is nothing but nullifying reference variable.

#### Example:

```java
Student s1 = new Student();
Student s2 = new Student();

s1 = null; // s1 will be eligible for GC

s2 = null; // s2 will be eligible for GC
```


### 2. Re-assigning the reference variable

If an object no logner required then reassigned its reference variable to some other object so that old object by default eligible for GC.

#### Example:

```java
Student s1 = new Student();
Student s2 = new Student();

s1 = new Student(); // old object will be eligible for GC

s2 = s1; // both old objects will be eligible for GC
```


### 3. Object created inside a method

The objects which are created inside a method are by default eligible for GC, once method execution completes.

#### Example: 1

```java
public class Test{
	public static void main(String []args){
		method1();
		//After execution of method1, s1 and s2 reference variable will no logner available
		// So the value assigned to these variable will eligible for GC
	}
	
	public static void method1(){
		Student s1 = new Student();
		Student s2 = new Student();
	}
}
```

#### Example: 2

```java
class Test{
	public static void main(String []args){
		method1();
		// here both object(s1,s2) will be eligible for GC
		
		Student s = method1();
		// Here only one object will be eligible for GC.
	}
	
	public static Student method1(){
		Student s1 = new Student();
		Student s2 = new Student();
		return s2;
	}
}
```

#### Example: 3

```java
class Test{
	static Student s;
	public static void main(String []args){
		method1();
		//Here only one object will be eligible for GC ie s2(because it is local variable).
	}
	public static void method1(){
		s = new Student();
		Student s2 = new Student();
	}
	
}
```


### 4. Island of Isolation

#### Example:

```java
class Test{
	Test s;
	public static void main(String []args){
		Test t1 = new Test();
		Test t2 = new Test();
		Test t2 = new Test();
		//Till here no object will be eligible for GC
		
		t1 = null; //no object eligible for GC
		t2 = null; //no object eligible for GC
		t3 = null; // All objects will be eligible for GC
	}	
}
```

**Note:**

1. If an object doesn't contain any reference variable then it is eligible for GC always.
2. Even though object having references, sometimes it is eligible for GC(if all references are internal reference, Eg. Island of Isolation).

------------------------------------

# The ways for requesting JVM to run Garbage Collector

Once we made an object eligible for GC, it may not be destroyed immediately by GC. Whenever JVM runs GC then only the objects will be destroyed. But when exactly JVM runs GC, we can't predict. It varied from JVM to JVM.

Instead of waiting until jvm runs GC, we can request jvm to GC programmtically. But whether jvm accept our request or not there is no gurantee. But most of the time, jvm accept our request.

### Following are ways for requesting to run GC:

### 1. By using `System` class

`System` class contains static method named `gc()` for this purpose.

```java
System.gc();
```


### 2. By using Runtime class

Java application can communicate with JVM by using `Runtime` object. `Runtime` class present in `java.lang` package and it is a Singleton class. We can create runtime object by using 

```java
Runtime r = Runtime.getRuntime();
```

Once we got runtime object, we can call the following methods on that object:

i. `totalMemory()` 
It returns number of bytes of total memory present in heap (ie. heap size);

ii. `freeMemory()`
It returns number of bytes of free memory present in the heap.

iii. `gc()`
For requesting jvm to run garbage collector.

#### Example:

```java
import java.util.Date;

public class RuntimeDemo {
    public static void main(String[] args) {
        
        Runtime r = Runtime.getRuntime();
        
        System.out.println("r.totalMemory(): "+r.totalMemory());
        System.out.println("r.freeMemory(): "+r.freeMemory());
        System.out.println("r.availableProcessors(): "+r.availableProcessors());
        
        Date d = null;
        for(int i=0;i<10000;i++){
            d = new Date();
            d = null;
        }
        
        System.out.println("r.freeMemory(): "+r.freeMemory());
        r.gc();
        System.out.println("r.freeMemory(): "+r.freeMemory());
    }
}
```

Output:

```java
r.totalMemory(): 16252928
r.freeMemory(): 15766944
r.availableProcessors(): 4
r.freeMemory(): 15444424
r.freeMemory(): 15956136
```

**Note:** gc() method present in System class is a static method. whereas gc() method present in Runtime class is an instance method.
It is convenient to use System class gc() when compared with Runtime class gc().
With respect to performance, it is highly recommended to use Rumtime class gc() when compared with System class gc() because System class gc() method internally calls Runtime class gc() method.

----------------------------------

# Finalization

Before destroying an object, GC calls `finalize()` method to perform clean up activities.

Once finalize() method completes automatically garbage collector destroys that object.

`finalize()` method present in `Object` class with following declaration:

`proctected void finalize() throws Throwable;`

We can override `finalize()` method in our class to define our own clean up activities.

###Case 1: 
Just before destroying an object, gc calls finalize() on the object which is eligible for GC. Then the corresponding class finalize() method will be executed.
For example: if `String` object eligible for GC then `String` class `finalize()` will be executed but not any other class finalize method.

```java
public class Test{
	public static void main(String []args){
		String s = new String("Test");
		s = null;
		System.gc();
		
		System.out.println("End of main");
	}
	public void finalize(){
		System.out.println("finalize method called");
	}
}
```

In the above example, String object eligible for GC and hence String class finalize() method got executed which has empty implementation and hence the output is

```java
End of main
```

If we replace `String` object with `Test` class object then Test class finalize() will be executed. In this case, the output could be

```java
End of main
finalize method called
```

Or

```java
finalize method called
End of main
```

### Case 2:
Based on our requirement we can call finalize() method explicitly then it will be executed just like a normal method call and object won't be destroyed.

```java
public class Test{
	public static void main(String []args){
		Test t = new Test();
		t.finalize();
		
		t = null;
		System.gc();
		
		System.out.println("End of main");
	}
	public void finalize(){
		System.out.println("finalize method called");
	}
}
```
In the above program finalize method god executed twice, in which one call is by programmer and another is by garbage collector. In this case output is

```java
finalize method called
finalize method called
End of main
```

Note: If we are calling finalize() explicitly then it will be executed like a normal method call and object won't be destroyed. If GC calls finalize then it will destroy object immediately on completion of finalize().

init(), service() and destroy() method are considered as life cycle method of Servlet. Just before destroying servlet, web container call destroy method to perform clean up activities. But based on our requirement we can call destroy() from init() and service() then destroy method will be executed just like a normal method call and servlet object won't be destroyed.

### Case 3:

Even though object eligible for garbage collection multiple times but GC calls finalize() only once.

```java
public class Test {
    static Test b;
    
    public static void main(String[] args) throws InterruptedException {
        
        b = new Test();
        System.out.println(b.hashCode());
        b = null;
        
        System.gc();
        Thread.sleep(2000);
        
        System.out.println(b.hashCode());
        System.gc();
        
        Thread.sleep(2000);
        System.out.println("End of main");
    }
    
    public void finalize(){
        System.out.println("finalize method called");
        b = this;
    }
}
```

Output:

```java
29293232
finalize method called
29293232
End of main
```

In the above program, even though object eligible for GC two times but Garbage collector calls finalize() only once.

### Case 4:

We can't predict the behaviour of GC. It varied from jvm to jvm hence for the following questions, we cannot provide exact answer

1. When exactly JVM runs garbage collector?
2. In which order GC identifies eligible object for garbage collection ?
3. In which order GC destroys eligible objects ?
4. Whether GC destroys all eligible objects or not ?
5. What is the algorithm followed by GC ?

Note: 
1. Whenever program runs with low memory, then JVM runs GC but we cann't predict exactly at what time.
2. Most of the garbage collectors follow standard algorithm known as `Mark and Sweep` algorithm, but it doesn't mean that every GC follows the same algorithm.


```java
public class Test {
    static int count =0;
    
    public static void main(String[] args) {
        
        for(int i=0;i<10;i++){
            Test b = new Test();
            b = null;
        }
        System.out.println("End of main");
    }
    
    public void finalize(){
        System.out.println("finalize method called "+ ++count);
    }
}
```

If we keep on increasing the condition of above for loop then at certain point of time, memory problem will be raised. Then JVM runs GC. Garbage collector calls `finalize()` on every object separately and destroys that object.

### Case 5:

Memory Leaks: The object which are not getting used in program and which are not eligible for GC, such type of useless objects are called memory leaks.

In our program, if memory leaks present then the program will be terminated by raising `java.lang.OutOfMemoryError`. Hence if an object no longer required, it is highly recommended to make that object eligible for GC.

Following are various third party memory management tool to identify memory leaks

HP OVO
HP JMeter
JProbe
Patrol
IBM Tivoli
