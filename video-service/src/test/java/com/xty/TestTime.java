package com.xty;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestTime {
    @Test
    public void test() {
        long time = new Date().getTime();
        System.out.println(time);
    }
}
