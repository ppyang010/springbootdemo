package com.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ccy
 * @description
 * @time 2020-05-12 14:23
 */
public class BinarySearchDemo {

    /*
     * public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
            使用二分搜索法搜索指定列表，以获得指定对象。在进行此调用之前，必须根据列表元素的自然顺序对列表进行升序排序（通过 sort(List) 方法）。
            如果没有对列表进行排序，则结果是不确定的。如果列表包含多个等于指定对象的元素，则无法保证找到的是哪一个。
            此方法对“随机访问”列表运行 log(n) 次（它提供接近固定时间的位置访问）。如果指定列表没有实现 RandomAccess 接口并且是一个大型列表，
            其实ArrayList接口实现了RandomAccess
            则此方法将执行基于迭代器的二分搜索，执行 O(n) 次链接遍历和 O(log n) 次元素比较。
               参数：
        list - 要搜索的列表。
        key - 要搜索的键。
        返回：
        如果搜索键包含在列表中，则返回搜索键的索引；否则返回 (-(插入点) - 1)。插入点 被定义为将键插入列表的那一点：即第一个大于此键的元素索引；
        如果列表中的所有元素都小于指定的键，则为 list.size()。注意，这保证了当且仅当此键被找到时，返回的值将 >= 0。
        抛出：
        ClassCastException - 如果列表中包含不可相互比较 的元素（例如，字符串和整数），或者搜索键无法与列表的元素进行相互比较。
     */

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("abcd");//String类本身就是实现了Comparable接口
        list.add("kkkkk");
        list.add("z");
        list.add("zz1");
        list.add("zz");
        list.add("qq");
        list.add("qq");
        Collections.sort(list); //先进行排序
        int index = Collections.binarySearch(list, "zz1");
        System.out.println(list);
        System.out.println(index);

    }


    private static void test(List a){

    }
}
