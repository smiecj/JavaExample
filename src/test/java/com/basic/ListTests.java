package com.basic;

import org.junit.jupiter.api.Test;

public class ListTests {

    // 测试自构建 list
    @Test
    public void testList() {
        com.basic.container.List list = new com.basic.container.List();
        int n = 10;
        for (int index = 0; index < n; index++) {
            list.add(index);
        }
        
        // 获取指定位置的元素
        System.out.println("第3个元素: " + list.get(2));
        System.out.println("第5个元素: " + list.get(4));

        // 指定位置插入元素
        list.insert(2, 100);
        // 删除最后一个元素
        list.pop();

        // 数组长度和遍历
        System.out.println("数组长度: " + list.size());
        System.out.printf("[");
        for (int index = 0; index < list.size(); index++) {
            if (index > 0) {
                System.out.printf(", ");
            }
            System.out.printf("%d", list.get(index));
        }
        System.out.println("]");

        // 平均值
        System.out.println("list average: " + list.average());

        // 最大值
        System.out.println("list max: " + list.max());

        // 输出排序结果
        int[] sortedArr = list.sort();
        System.out.printf("[");
        for (int index = 0; index < sortedArr.length; index++) {
            if (index > 0) {
                System.out.printf(", ");
            }
            System.out.printf("%d", sortedArr[index]);
        }
        System.out.println("]");
    }
}
