package com.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

// java bean 变量可直接通过 bean 接口修改的漏洞测试
// http://rui0.cn/archives/1158
public class Parent {
    private String name;

    private Children child;

    public Parent() {
        child = new Children();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Children getChild() {
        return child;
    }

    public void setName(Children child) {
        this.child = child;
    }

    @Test
    public void testBeanSetProperty() {
        Parent parent = new Parent(); 
        BeanWrapper bw = new BeanWrapperImpl(parent); 
        bw.setPropertyValue("name", "parent");
        bw.setPropertyValue("child.name", "child");
        System.out.println("Parent name: " + parent.name + ", child name: " + parent.getChild().getName());
    }
}
