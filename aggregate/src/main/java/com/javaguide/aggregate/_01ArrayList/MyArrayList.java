package com.javaguide.aggregate._01ArrayList;

import java.util.Collection;

public class MyArrayList<E> {

    /**
     * 存储 ArrayList 元素的数组缓冲区。暂不考虑扩容问题
     */
    private Object[] elementData = new Object[10];

    /**
     * ArrayList 的大小（它包含的元素数量，非容量）。
     *
     * @serial
     */
    private int size;



    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
    public boolean add(E e) {
        elementData[size] = e;
        size++;
        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        //校验一下，防止去获取超出size（非数组容量）范围的数据
        //加个友善的提示： Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 99, Size: 4
        rangeCheck(index);

        return (E) elementData[index];
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    }


}
