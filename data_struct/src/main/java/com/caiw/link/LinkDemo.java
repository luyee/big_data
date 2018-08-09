package com.caiw.link;


import java.util.Arrays;
import java.util.Random;

/**
 * @ClassName LinkDemo
 * @Author caiwe
 * @CreateTime 2018/7/1815:05
 **/

public class LinkDemo<T extends Comparable<T>> {

    private Link<T> first;		//首结点
    public LinkDemo() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(T data) {// 插入 到 链头, 以从小到大排序
        Link<T> newLink = new Link<T>(data);
        Link<T> current = first, previous = null;
        while (current != null && data.compareTo(current.data) > 0) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            first = newLink;
        } else {
            previous.next = newLink;
        }
        newLink.next = current;
    }

    public Link<T>  deleteFirst() {//删除 链头
        Link<T> temp = first;
        first = first.next; //变更首结点，为下一结点
        return temp;
    }

    public Link<T> delete(T t) {
        if (isEmpty()) {
            return null;
        } else {
            if (first.data.equals(t)) {
                Link<T> temp = first;
                first = first.next; //变更首结点，为下一结点
                return temp;
            }
        }
        Link<T> p = first;
        Link<T> q = first;
        while (!p.data.equals(t)) {
            if (p.next == null) {//表示到链尾还没找到
                return null;
            } else {
                q = p;
                p = p.next;
            }
        }

        q.next = p.next;
        return p;
    }

    public void displayList() {//遍历
        System.out.println("List (first-->last):");
        Link<T> current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
    }

    class Link<T> {//链结点
        T data;		//数据域
        Link<T> next; //后继指针，结点		链域
        Link(T data) {
            this.data = data;
        }
        void displayLink() {
            System.out.println(data.toString());
        }
    }

    public static void main(String[] args) {
        LinkDemo<Integer> list = new LinkDemo<Integer>();
        Random random = new Random();
        int len = 5;
        Integer[] ary = new Integer[len];
        for (int i = 0; i < len; i++) {
            int x = random.nextInt(1000);
            ary[i] = x;
            list.insert(x);
        }
        System.out.println("----排序前----");
        System.out.println(Arrays.toString(ary));
        System.out.println("----链表排序后----");
        list.displayList();
    }
}
