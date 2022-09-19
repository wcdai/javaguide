package com.javaguide.aggregate._01ArrayList.test;

import com.javaguide.aggregate._01ArrayList.MyArrayList;

public class Chapter05 {

    public static void main(String[] args) {

        objectCloneTest1();
//        objectCloneTest2();

    }

    /**
     * 测试用例：
     *  5.测试重写`Object.clone()`并使用`Arrays.copyOf()`复制`elementData`进行克隆对象后，再进行元素对象属性修改的场景
     */
    private static void objectCloneTest2() {
        MyArrayList<SmsCoupon> srcList = new MyArrayList<>();
        Book item1 = new Book();
        item1.setId("1");
        item1.setName("《Java零基础从入门到崩溃》");
        srcList.add(item1);
        System.out.println("srcList = " + srcList);
        MyArrayList<SmsCoupon> clone = (MyArrayList<SmsCoupon>) srcList.clone();
        System.out.println("clone = " + clone);
        item1.setId("6666");
        System.out.println("clone = " + clone);
    }

    /**
     * 测试用例：
     *  3.测试重写`Object.clone()`但未使用`Arrays.copyOf()`复制`elementData`进行克隆对象后，再进行`add()`操作的场景
     *  4.测试重写`Object.clone()`并使用`Arrays.copyOf()`复制`elementData`进行克隆对象后，再进行`add()`操作的场景
     */
    private static void objectCloneTest1() {
        MyArrayList<SmsCoupon> srcList = new MyArrayList<>();
        Book item1 = new Book();
        item1.setId("1");
        item1.setName("《Java零基础从入门到崩溃》");
        srcList.add(item1);
        Book item2 = new Book();
        item2.setId("2");
        item2.setName("《10年Java大牛之秃头经验》");
        srcList.add(item2);
        System.out.println(srcList);
        MyArrayList<SmsCoupon> clone = (MyArrayList<SmsCoupon>) srcList.clone();
        Book item3 = new Book();
        item3.setId("3");
        item3.setName("《30年Java大牛之秃头经验》");
        srcList.add(item3);
        System.out.println(clone);
    }


}

class Book extends SmsCoupon {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
class SmsCoupon {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SmsCoupon{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}