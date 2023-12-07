package com.xty;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestTime {
    @Test
    public void test() {
        Date date = new Date();
        System.out.println(date);
        Date date1 = new Date(System.currentTimeMillis());
        System.out.println(date1);
    }
}
