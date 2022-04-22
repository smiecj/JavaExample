package com.basic.container;

import java.util.Arrays;

// 定义一个 非线程安全 list
public class List {

    // 默认初始容量
    private static final int DEFAULT_INIT_CAP = 10;
    // 默认最大容量
    private static final int MAX_CAP = Integer.MAX_VALUE - 8;

    private int[] arr;

    // cap: 数组预申请的容量
    // size: 数组真正大小
    private int cap;
    private int size;

    public List() {
        this.arr = new int[DEFAULT_INIT_CAP];
        this.cap = DEFAULT_INIT_CAP;
        this.size = 0;
    }
    
    public List(int n) {
        this.arr = new int[n];
        this.cap = n;
        this.size = 0;
    }
    
    // add
    public void add(int n) {
        // 判断是否需要扩容
        grow(size + 1);
        
        // 将元素加入到队尾
        arr[size++] = n;
    }
    
    // pop
    public int pop() {
        // 数组为空，抛出异常
        if (size == 0) {
            throw new RuntimeException("list is empty");
        }
        return arr[size--];
        // todo: 缩容
    }

    // get
    public int get(int index) {
        // 获取第 index 下标的元素
        if (index < 0 || index >= size) {
            throw new RuntimeException("index exceed range");
        }
        return this.arr[index];
    }

    // insert
    public void insert(int pos, int n) {
        if (pos > size) {
            throw new RuntimeException("insert position exceed list size");
        }
        grow(size + 1);
        
        // 方法: 时间复杂度 O(n)，将 pos 之后的元素全部往后移一位
        for (int index = size - 1; index >= pos; index--) {
            arr[index + 1] = arr[index];
        }
        arr[pos] = n;
        size++;
    }

    // sort
    public int[] sort() {
        // copy arr
        int []sortArr = new int[size];
        for (int index = 0; index < size; index++) {
            sortArr[index] = this.arr[index];
        }
        Arrays.sort(sortArr);
        return sortArr;
    }

    // size
    public int size() {
        return this.size;
    }

    // average
    public double average() {
        if (size == 0) {
            throw new RuntimeException("list size is 0");
        }
        double sum = 0;
        for (int i : this.arr) {
            sum += i;
        }
        return sum / size;
    }

    // max
    public int max() {
        if (size == 0) {
            throw new RuntimeException("list size is 0");
        }
        int currentMax = this.arr[0];
        for (int index = 1; index < size; index++) {
            if (this.arr[index] > currentMax) {
                currentMax = this.arr[index];
            }
        }
        return currentMax;
    }

    // grow
    // 按双倍方式扩容
    private void grow(int newSize) {
        if (newSize >= this.cap) {
            int newCap = this.cap * 2;
            // overflow
            if (newCap < 0 || newCap > MAX_CAP) {
                throw new RuntimeException("grow exceed max capacity");
            }
            int[] newArr = new int[newCap];
            for (int index = 0; index < size; index++) {
                newArr[index] = this.arr[index];
            }
            this.arr = newArr;
            this.cap = newCap;
        }
    }
}
