package com.xty;

import org.junit.jupiter.api.Test;

public class TestGetIp {
    @Test
    public void test() {
        String addr = "127.0.0.1";
        String ip = addr.split(":")[0];
        System.out.println(ip);
    }
}
