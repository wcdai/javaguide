package com.javaguide.aggregate._01ArrayList.test;

import com.javaguide.aggregate._01ArrayList.MyArrayList;

import java.util.*;

public class Chapter06 {
    public static void main(String[] args) {
//        iterableTest();
        randomAccessTest();
    }

    private static void randomAccessTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list(list);
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        list(linkedList);

    }

    private static void list(List<Integer> list) {
        if (list instanceof RandomAccess) {
            for (int i = 0; i < list.size(); i++) {
                Integer integer = list.get(i);
                System.out.println("integer = " + integer);
            }
        }else {
            for (Integer integer : list) {
                System.out.println("integer = " + integer);
            }
        }
    }

    private static void iterableTest() {
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
        for (Integer integer : myArrayList) {
            System.out.println("integer = " + integer);
        }
        Iterator var2 = myArrayList.iterator();
        while(var2.hasNext()) {
            Integer integer = (Integer)var2.next();
            System.out.println("integer = " + integer);
        }
    }
}
