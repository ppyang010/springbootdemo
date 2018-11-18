package com.code;

import java.util.HashSet;
import java.util.Objects;
import java.util.TreeSet;

public class TestBlackRedTree implements Comparable<TestBlackRedTree>{
    private int i;
    private int sort;

    public TestBlackRedTree(int i, int sort) {
        this.i = i;
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestBlackRedTree that = (TestBlackRedTree) o;
        return i == that.i ;
    }

    @Override
    public int hashCode() {
        //这个demo中无用
        return i;
    }

    @Override
    public int compareTo(TestBlackRedTree o) {
        if(!this.equals(o)){
            return Integer.valueOf(o.sort).compareTo(this.sort);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "TestBlackRedTree{" +
                "i=" + i +
                ", sort=" + sort +
                '}';
    }

    public static void main(String[] args) {
        TreeSet<TestBlackRedTree> objects = new TreeSet<>();
        objects.add(new TestBlackRedTree(1,1));
        objects.add(new TestBlackRedTree(2,2));
        objects.add(new TestBlackRedTree(3,3));
        objects.add(new TestBlackRedTree(1,3));
        objects.add(new TestBlackRedTree(1,4));
        for (TestBlackRedTree item :objects){
            System.out.println(item);
        }
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        HashSet<TestBlackRedTree> hashSet = new HashSet<>();
        hashSet.add(new TestBlackRedTree(1,1));
        hashSet.add(new TestBlackRedTree(2,2));
        hashSet.add(new TestBlackRedTree(3,3));
        hashSet.add(new TestBlackRedTree(1,3));
        hashSet.add(new TestBlackRedTree(1,4));
        hashSet.forEach(node->{
            System.out.println(node);
        });

    }
}
