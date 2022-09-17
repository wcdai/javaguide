package com.javaguide.aggregate._01ArrayList.test;

import com.javaguide.aggregate._01ArrayList.MyArrayList;

import java.util.Arrays;

public class Chapter03 {

    public static void main(String[] args) {
        //System.arraycopy()测试
//        SystemArraycopyTest1();
        //Arrays.copyOf()测试
//        ArraysCopyOfTest();
        //自动扩容测试1 测试add(E e) 中触发自动扩容
        autoExpansionTest1();
        //自动扩容测试2 测试add(int index, E element) 中触发自动扩容
//        autoExpansionTest2();
        //自动扩容测试3  测试addAll(MyArrayList<E>  c)中触发自动扩容
//        autoExpansionTest3();
        //自动扩容测试4  测试addAll(int index, MyArrayList<E> c)中触发自动扩容
//        autoExpansionTest4();
    }



    /**
     * 自动扩容测试1 测试add(E e) 中触发自动扩容
     * 自动扩容了，不会触发java.lang.ArrayIndexOutOfBoundsException
     */
    private static void autoExpansionTest1() {
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
        //自动扩容了，不会触发java.lang.ArrayIndexOutOfBoundsException
    }

    /**
     * 自动扩容测试2 测试add(int index, E element) 中触发自动扩容
     */
    private static void autoExpansionTest2() {
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
        myArrayList.add(5, 11);
        //自动扩容了，不会触发java.lang.ArrayIndexOutOfBoundsException
    }

    /**
     * 自动扩容测试3  测试addAll(MyArrayList<E>  c)中触发自动扩容
     */
    private static void autoExpansionTest3() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        MyArrayList<Integer> myArrayList2 = new MyArrayList<>();
        myArrayList2.add(1);
        myArrayList2.add(2);
        myArrayList2.add(3);
        myArrayList2.add(4);
        myArrayList2.add(4);
        myArrayList2.add(4);
        myArrayList2.add(4);
        myArrayList.addAll(myArrayList2);
    }

    /**
     * 自动扩容测试4  测试addAll(int index, MyArrayList<E> c)中触发自动扩容
     */
    private static void autoExpansionTest4() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        MyArrayList<Integer> myArrayList2 = new MyArrayList<>();
        myArrayList2.add(1);
        myArrayList2.add(2);
        myArrayList2.add(3);
        myArrayList2.add(4);
        myArrayList2.add(4);
        myArrayList2.add(4);
        myArrayList2.add(4);
        myArrayList.addAll(2,myArrayList2);
    }

    private static void ArraysCopyOfTest() {
        int[] a = new int[3];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        int[] b = Arrays.copyOf(a, 2);
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));
        System.out.println("b.length = " + b.length);
    }

    private static void SystemArraycopyTest1() {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        a[4] = 4;
        int[] b = new int[10];
        b[5] = 5;
        b[6] = 6;
        b[7] = 7;
        b[8] = 8;
        b[9] = 9;
        System.out.println("a =                    " + Arrays.toString(a));
        System.out.println("System.arraycopy前，b = " + Arrays.toString(b));
        System.arraycopy(a, 2, b, 3, 3);
        System.out.println("System.arraycopy后，b = " + Arrays.toString(b));
    }

    /**
     * System.arraycopy方法测试
     */
    private static void SystemArraycopyTest2() {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        System.arraycopy(a, 2, a, 3, 3);
        a[2] = 99;
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
