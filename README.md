# javaguide
JDK源码学习笔记


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

- [ ] add时实现自动扩容（功能入口）

- [ ] 创建MyArrayList时可指定初始容量
- [ ] 创建MyArrayList不指定容量即使用默认容量
- [ ] 设置最大容量上限



#### Q1：用户创建MyArrayLis不指定容量时，是否立即分配默认容量？

#### Q2：数据达到该数组容量上限之后是否每添加一个元素就进行一次扩容？

#### Q3:   是否可以无限制扩容，MyArrayLis可达的最大容量是多少？

#### Q4：达到最大容量上限后，再次添加元素会怎样？

#### Q5：每次扩容，扩容了多少倍？

#### Q6：ArrayList扩容的时机是什么时候？

#### Q7：为什么ArrayList没有loadFactor，不像HashMap一样数据达到数组⼤⼩*loadFactor时就开始扩容？

#### Q8：当数组容量远大于实际数据时，ArrayList是否会进行容量缩减？



#### 重点：

1. ArrayLis的扩容机制
2. 熟悉`System.arraycopy()`和 `Arrays.copyOf` API的使用

