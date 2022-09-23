package com.javaguide.aggregate._01ArrayList.test;

import com.javaguide.aggregate._01ArrayList.MyArrayList;


import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;

public class Chapter08 {

    public static void main(String[] args) {
//        listIteratorTest();
//        subListTest();
        spliterator();
    }

    private static void spliterator() {
        Integer[] elements = {1,2,3,1,4,5,8};
        Integer[] tags = {1,1,1,1,1,1,1};
        TaggedArray<Integer> taggedArray = new TaggedArray<>(elements, tags);
        Spliterator<Integer> spliterator = taggedArray.spliterator();
        long l = spliterator.estimateSize();
        long n = spliterator.characteristics();
//        while (spliterator.tryAdvance(System.out::println)) {
//
//        }

//        spliterator.forEachRemaining(System.out::println);
        Spliterator<Integer> integerSpliterator = spliterator.trySplit();
        Spliterator<Integer> integerSpliterator1 = integerSpliterator.trySplit();

    }

    private static void subListTest() {
        List<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        myArrayList.add(8);
        MyArrayList<Integer> subList = (MyArrayList<Integer>) myArrayList.subList(2, 5);

        myArrayList.add(3,6);
        System.out.println("subList = " + subList);
    }

    private static void listIteratorTest() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        myArrayList.add(8);

        ListIterator<Integer> listIterator = myArrayList.listIterator(4);
        while (listIterator.hasPrevious()&&listIterator.hasNext()) {
            Integer previous = listIterator.previous();//3
            Integer previous1 = listIterator.previous();//2
            Integer previous2 = listIterator.previous();//1
            listIterator.remove();
            Integer previous3 = listIterator.previous();
            listIterator.add(6);
            Integer previous4 = listIterator.previous();
            listIterator.add(6);
            System.out.println("previous = " + previous);
            System.out.println("previous1 = " + previous1);
            int previousIndex = listIterator.previousIndex();
            System.out.println("previousIndex = " + previousIndex);
            int nextIndex = listIterator.nextIndex();
            boolean hasNext = listIterator.hasNext();
            System.out.println("hasNext = " + hasNext);
            Integer next = listIterator.next();
            System.out.println("next = " + next);
            System.out.println("nextIndex = " + nextIndex);

            System.out.println("next = " + next);
        }
    }
}
class TaggedArray<T> {
    private final Object[] elements; // immutable after construction

    TaggedArray(T[] data, Object[] tags) {
        int size = data.length;
        if (tags.length != size) throw new IllegalArgumentException();
        this.elements = new Object[2 * size];
        for (int i = 0, j = 0; i < size; ++i) {
            elements[j++] = data[i];
            elements[j++] = tags[i];
        }
    }

    public Spliterator<T> spliterator() {
        return new TaggedArraySpliterator<>(elements, 0, elements.length);
    }

    static class TaggedArraySpliterator<T> implements Spliterator<T> {
        private final Object[] array;
        private int origin; // current index, advanced on split or traversal
        private final int fence; // one past the greatest index

        TaggedArraySpliterator(Object[] array, int origin, int fence) {
            this.array = array;
            this.origin = origin;
            this.fence = fence;
        }

        public void forEachRemaining(Consumer<? super T> action) {
            for (; origin < fence; origin += 2) action.accept((T) array[origin]);
        }

        public boolean tryAdvance(Consumer<? super T> action) {
            if (origin < fence) {
                action.accept((T) array[origin]);
                origin += 2;
                return true;
            } else // cannot advance
                return false;
        }

        public Spliterator<T> trySplit() {
            int lo = origin;
            // divide range in half    & ~1：整除2的最后一个数据的位置。~：按位取反 &：按位与
            int mid = ((lo + fence) >>> 1) & ~1; // force midpoint to be even
            if (lo < mid) { // split out left half
                origin = mid; // reset this Spliterator's origin
                return new TaggedArraySpliterator<>(array, lo, mid);
            } else       // too small to split
                return null;
        }

        public long estimateSize() {
            return (long) ((fence - origin) / 2);
        }

        public int characteristics() {
            return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
        }
    }

    static <T> void parEach(TaggedArray<T> a, Consumer<T> action) {
        Spliterator<T> s = a.spliterator();
        long targetBatchSize = s.estimateSize() / (ForkJoinPool.getCommonPoolParallelism() * 8);
        new ParEach(null, s, action, targetBatchSize).invoke();
    }

    static class ParEach<T> extends CountedCompleter<Void> {
        final Spliterator<T> spliterator;
        final Consumer<T> action;
        final long targetBatchSize;

        ParEach(ParEach<T> parent, Spliterator<T> spliterator, Consumer<T> action, long targetBatchSize) {
            super(parent);
            this.spliterator = spliterator;
            this.action = action;
            this.targetBatchSize = targetBatchSize;
        }

        public void compute() {
            Spliterator<T> sub;
            while (spliterator.estimateSize() > targetBatchSize && (sub = spliterator.trySplit()) != null) {
                addToPendingCount(1);
                new ParEach<>(this, sub, action, targetBatchSize).fork();
            }
            spliterator.forEachRemaining(action);
            propagateCompletion();
        }
    }
}
