package com.javaguide.aggregate._01ArrayList.test;

import com.javaguide.aggregate._01ArrayList.MyArrayList;

public class Chapter02 {
    public static void main(String[] args) {

        testAdd();
        testGet();
    }

    private static void testGet() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        Integer integer = myArrayList.get(3);
        System.out.println("integer = " + integer);
        Integer integer1 = myArrayList.get(6);
        System.out.println("integer1 = " + integer1);
    }

    /**
     * 会触发java.lang.ArrayIndexOutOfBoundsException
     * 由自动扩容解决
     */
    private static void testAdd() {
        try {
            MyArrayList<Integer> myArrayList = new MyArrayList<>();
            myArrayList.add(1);
            myArrayList.add(2);
            myArrayList.add(3);
            myArrayList.add(4);
            myArrayList.add(5);
            myArrayList.add(6);
            myArrayList.add(7);
            myArrayList.add(8);
            myArrayList.add(9);
            myArrayList.add(10);
            myArrayList.add(11);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
