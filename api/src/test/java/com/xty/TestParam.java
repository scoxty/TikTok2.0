package com.xty;

import org.junit.jupiter.api.Test;

public class TestParam {
    @Test
    public void test() {
        String s = null;
        try {
            Long t = Long.parseLong(s);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
