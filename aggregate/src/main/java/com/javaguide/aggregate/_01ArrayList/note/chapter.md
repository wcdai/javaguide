
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

   

