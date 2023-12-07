package com.xty;

import org.junit.jupiter.api.Test;

public class PasswordEncry {
    @Test
    public void test() {
        String password = "1234";
        String p = Encryption.encryptPassword(password);
        String p2 = Encryption.encryptPassword(password);

        System.out.println(p2 == p);
        System.out.println(Encryption.matchPassword(password, p));
        System.out.println(Encryption.matchPassword(password, p2));
    }
}
