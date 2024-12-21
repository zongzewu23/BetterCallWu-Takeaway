package com.bettercallwu.test;

import org.junit.jupiter.api.Test;

public class UplaodFileTest {
    @Test
    public void test1(){
        String fileName = "ererer.jpg";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }
}
