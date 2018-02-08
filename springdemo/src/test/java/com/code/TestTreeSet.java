package com.code;

import org.junit.Test;

import java.util.TreeSet;

public class TestTreeSet {
    class Combine implements Comparable<Combine> {
        private int p1;
        private int p2;

        public Combine(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public int hashCode() {
            System.out.println("hashcode");
            return p1 ;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("whether equal " + this + " and " + obj);
            if (this == obj) return true;
            if(obj == null|| getClass() != obj.getClass() ) return false;
            Combine combine = (Combine) obj;
            return combine.p1 == p1;

         }

        @Override
        public int compareTo(Combine o) {
            System.out.println("compare " + this + " and " + o);
            if(!this.equals(o)){
                System.out.println(Integer.valueOf(o.p2).compareTo(this.p2));
                return Integer.valueOf(o.p2).compareTo(this.p2);
            }
            System.out.println("--0");
            return 0;
        }

        @Override
        public String toString() {
            return "(" + p1 + "," + p2 + ")";
        }

        public int getP1() {
            return p1;
        }

        public void setP1(int p1) {
            this.p1 = p1;
        }

        public int getP2() {
            return p2;
        }

        public void setP2(int p2) {
            this.p2 = p2;
        }

    }

    @Test
    public void test() {
        Combine c1 = new Combine(1, 1);
        Combine c2 = new Combine(2, 2);
        Combine c3 = new Combine(3, 3);
        Combine c4 = new Combine(1, 3);
        Combine c5 = new Combine(1, 4);

        TreeSet<Combine> set = new TreeSet<Combine>();
        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(c4);
        set.add(c5);
        for(Combine c : set){
            System.out.println(c);
        }
        System.out.println("------------");
        System.out.println(c4.compareTo(c2));
    }
}
