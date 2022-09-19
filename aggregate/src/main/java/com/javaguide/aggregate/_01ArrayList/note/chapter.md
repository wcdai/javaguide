
## 01_ArrayList源码调试环境搭建

> 先抄后写

1. 复制`AbstractCollection`、`AbstractList` `ArrayList`源码至`com.javaguide.aggregate._01ArrayList.source`包下

2. 将source包下`ArrayList`对`java.util.AbstractList`的引用全部改为引用`com.javaguide.aggregate._01ArrayList.source.AbstractList`

3. 在soure包中的ArrayList构造函数代码中增加控制台打印语句。Test包中编写Chapter01类进行测试

    1. soure包中的ArrayList类

       ```java
        public ArrayList() {
               System.out.println("ArrayList源码调试环境搭建 ");
               this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
           }
       ```

    2. Chapter01类

       ```java
       /**
        * ArrayList源码调试环境搭建
        */
       public class Chapter01 {
           public static void main(String[] args) {
               ArrayList arrayList = new ArrayList();
               //控制台输入--- ArrayList源码调试环境搭建
           }
       }
       ```

      

## 02_实现一个简单的能存取数据的List

- [ ] 存放数据的缓冲区（字段：`Object[] elementData`）
- [ ] 描述缓冲区数据数目的多少（字段：`int size`）
- [ ] 存一个数据的功能（方法：add(E e)）
- [ ] 取一个数据的功能（方法：get(int index)）

1. 新建一个MyArrayList类

2. Chapter02类

   1. 测试add和get方法



## 03_实现自动扩容

### 一、目标

- 数组中元素满时完成自动扩容

### 二、设计
- add(E e)中的扩容
  - 尾插法，当数组容量满时创建新数组进行扩容。将新老数组元素复制到新数组中
    ![](pic/arrayListadd(E).png)
- add(int index, E element)中的扩容
  - 指定索引位进行数据插入
      ![](pic/arraryListAdd(Index,E).webp)
  
### 三、详细目标
- 主要功能

- [ ] add(E e)中的扩容
- [ ] add(int index, E element)中的扩容

- 其他功能

- [ ] MyArrayList()
  
  - 无参构造
  
  - 使用无参构造进行数组懒初始化（扩容时再初始化数组位默认容量）
  
- [ ] MyArrayList(int initialCapacity)

  - 创建List时可指定初始化数组容量

- [ ] MyArrayList(MyArrayList<E> c) 

- [ ] addAll(MyArrayList<E>  c) （选做）
- [ ] addAll(int index, MyArrayList<E> c) （选做）
### 四、实现

见git commit节点 `03_实现自动扩容`代码

### 五、测试

见`Chapter03`类中代码

- SystemArraycopyTest1()
  - System.arraycopy() API测试
- ArraysCopyOfTest()
  - Arrays.copyOf() API测试
- autoExpansionTest1()
  - 测试add(E e) 中触发自动扩容
- autoExpansionTest2()
  - add(int index, E element) 中触发自动扩容
- autoExpansionTest3()
  - 测试addAll(MyArrayList<E>  c)中触发自动扩容
- autoExpansionTest4()
  - 测试addAll(int index, MyArrayList<E> c)中触发自动扩容

### 六、问题

- Q1：用户创建MyArrayLis不指定容量时，是否立即分配默认容量？

- Q2：数据达到该数组容量上限之后是否每添加一个元素就进行一次扩容？

- Q3:   是否可以无限制扩容，MyArrayLis可达的最大容量是多少？

- Q4：达到最大容量上限后，再次添加元素会怎样？

- Q5：每次扩容，扩容了多少倍？

- Q6：ArrayList扩容的时机是什么时候？

- Q7：为什么ArrayList没有loadFactor，不像HashMap一样数据达到数组⼤⼩*loadFactor时就开始扩容？

- Q8：当数组容量远大于实际元素大小时，ArrayList是否会进行容量缩减？

- Q9：为什么建议arraylist创建时指定容量大小？



### 七、重点

1. ArrayLis的扩容机制
2. 熟悉`System.arraycopy()`和 `Arrays.copyOf` API的使用





## 04_实现自定义序列化
### 一、目标

- 使ArrayList支持序列化
- 自定义elementData的序列化

### 二、设计

- implements Serializable接口对象方可序列化
- 使用`writeObject(ObjectOutputStream s)`和`readObject(ObjectInputStream s)`来实现自定义MyArrayList中的数组元素序列化，缩减elementData所需序列化的数据

### 三、详细目标

- [ ] 显式指定 serialVersionUID 的值

  - 因为不同的 jdk 编译很可能会生成不同的 serialVersionUID 默认值，所以建议显式指定 serialVersionUID 的值

- [ ] Object[] elementData字段自定义序列化

  - 只序列化实际存储的那些元素，而不是整个数组，从而节省空间和时间

  - [ ] 自定义writeObject(java.io.ObjectOutputStream s)方法
  - [ ] 自定义readObject(java.io.ObjectInputStream s)方法

### 四、实现

见git commit节点 `04_实现自定义序列化`代码

### 五、测试

见`Chapter04`类中代码`serializationTest()`

- 测试用例
  
  未自定义序列化时:
  
  - 1.使用transient修饰字段Object[] elementData时测试
  
  - 2.不使用transient修饰字段Object[] elementData时测试
  
  自定义序列化后（新增writeObject、readObject方法）：
  
  - 3.使用transient修饰字段Object[] elementData时测试
  - 4.不使用transient修饰字段Object[] elementData时测试

### 六、问题

- Q1：ArrayList为何要实现自定义序列化？

- Q2：自定义的`writeObject(ObjectOutputStream s)`和`readObject(ObjectInputStream s)`是何时被触发的？
- Q3：自定义writeObject和readObject因注意哪些地方？



### 七、重点

1. 自定义对象序列化流程
2. 注意writeObject和readObject方法中数据的读写顺序以及数目





## 05_实现对象克隆

### 一、目标

- 使ArrayList支持对象克隆

### 二、设计

- implements Cloneable接口以支持`Object.clone()`
  - Cloneable为标记接口，未implements Cloneable接口时调用`Object.clone()`会触发`java.lang.CloneNotSupportedException`异常

- 重写`Object.clone()`方法以实现自定义克隆和赋予更大的方法访问权限
  - 自定义克隆，重新生成elementData以缩减数组占用空间
  - 赋予public方法访问权限，以使外部对象有使用该方法的权限


### 三、详细目标

- [ ] implements Cloneable

- [ ] 重写`Object.clone()`方法
  - 赋予更大的public方法访问权限
  - 对`elementData`复制时只复制数组实际拥有的元素（生成新数组，数组中的元素仍然是引用原元素地址（浅克隆））


见git commit节点 `05_实现对象克隆`代码

### 五、测试

见`MyArrayList`类中方法`objectCloneInThisTest()`

见`Chapter05`类中方法`objectCloneTest()`

- 测试用例

  在`MyArrayList`中测试:

  - 1.测试未implements Serializable接口使用Object.clone()方法进行对象克隆场景

  - 2.测试implements Serializable接口但未重写Object.clone()方法进行对象克隆场景

  在`Chapter05`中测试：

  - 3.测试重写`Object.clone()`但未使用`Arrays.copyOf()`复制`elementData`进行克隆对象后，再进行`add()`操作的场景
  - 4.测试重写`Object.clone()`并使用`Arrays.copyOf()`复制`elementData`进行克隆对象后，再进行`add()`操作的场景
  - 5.测试重写`Object.clone()`并使用`Arrays.copyOf()`复制`elementData`进行克隆对象后，再进行元素对象属性修改的场景

### 六、问题

- Q1：`Object.clone()`属于深克隆还是浅克隆？
- Q2：`ArrayList`对象克隆的过程是怎样的？

### 七、重点

1. 对象克隆的实现方式
2. 深克隆和浅克隆
3. `ArrayList`对象克隆的过程

