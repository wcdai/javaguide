package com.javaguide.aggregate._01ArrayList.test;

import com.javaguide.aggregate._01ArrayList.MyArrayList;

import java.io.*;

public class Chapter04 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * 测试用例：
         *   未自定义序列化时:
         *      1.使用transient修饰字段Object[] elementData时测试
         *      2.不使用transient修饰字段Object[] elementData时测试
         *   自定义序列化后（新增writeObject、readObject方法）：
         *      3.使用transient修饰字段Object[] elementData时测试
         *      4.不使用transient修饰字段Object[] elementData时测试
         */
        serializationTest();
    }


    private static void serializationTest() throws IOException, ClassNotFoundException {
        File file = new File("out.MyArrayList");

        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file));
        MyArrayList<Integer> myArrayList = new MyArrayList<>(10);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        oos.writeObject(myArrayList);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file));
        MyArrayList myArrayList2 = (MyArrayList) ois.readObject();

        System.out.println(myArrayList2);
        ois.close();
    }
}
