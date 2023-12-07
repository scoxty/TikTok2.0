package com.xty;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

public class TestJwtUtils {
    @Test
    public void test() {
        try {
            Claims claims = JwtUtil.parseJWT("");
        } catch (Exception e) {
            Class<? extends Exception> aClass = e.getClass();
            System.out.println(aClass);
            System.out.println(e.getMessage());
        }

        try {
            Claims claims = JwtUtil.parseJWT("adsadsadsa");
        } catch (Exception e) {
            Class<? extends Exception> aClass = e.getClass();
            System.out.println(aClass);
            System.out.println(e.getMessage());
        }

        Integer userId = 1, userId2 = 2, userId3 = 3;
        String jwt = JwtUtil.createJWT(userId.toString()); // 有效期1s
        try {
            Claims claims = JwtUtil.parseJWT(jwt);
            userId2 = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(userId2);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            Claims claims2 = JwtUtil.parseJWT(jwt);
            userId3 = Integer.parseInt(claims2.getSubject());
        } catch (Exception e) {
            Class<? extends Exception> aClass = e.getClass();
            System.out.println(aClass);
            System.out.println(e.getMessage());
        }

        System.out.println(userId3);
    }
}
