package com.javaguide.aggregate._01ArrayList.test;


import com.javaguide.aggregate._01ArrayList.source.ArrayList;

import java.util.Collection;
import java.util.List;

public class Chapter07 {
    public static void main(String[] args){

        // 创建一个动态数组
        ArrayList<String> sites = new ArrayList<>();

        sites.add("Runoob");
        sites.add("Google");
        sites.add("Wiki");
        sites.add("Taobao");
        System.out.println("网站列表: " + sites);

        // 创建一个新的 String 类型的数组
        // 数组长度和 ArrayList 长度一样
        String[] arr = new String[6];

        // 将ArrayList对象转换成数组
        String[] strings = sites.toArray(arr);

        // 输出所有数组的元素
        System.out.print("Array: ");
        for(String item:arr) {
            System.out.print(item+", ");
        }
        for (String string : strings) {
            System.out.println("string = " + string);
        }
        System.out.println("arr = " + arr);
        System.out.println("strings = " + strings);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
//        Integer[] res = new Integer[list.size()];
//        Integer[] aaa = (Integer[])list.toArray();

        Collection<String> coll = new ArrayList<>();
        coll.add("aa");
        String[] array = new String[coll.size()];
        coll.toArray(array);
        System.out.println("array = " + array);//数据已经从coll复制到了array
    }
}
