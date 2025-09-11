package com.taylorsz.shortenerurl.core;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Stack;

@Component
public class Base64 {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final char[] CHAP_MAP = ALPHABET.toCharArray();
    private static final HashMap<Character, Integer> INDEX_MAP = new HashMap<>();

    static {
        for (int i = 0; i < CHAP_MAP.length; i++) {
            INDEX_MAP.put(CHAP_MAP[i], i);
        }
    }

    public String encode(long number) {
        Stack<Character> stack = new Stack<>();
        String encode = "";

        while (number > 0) {
            int value = (int) number % 62;
            stack.push(CHAP_MAP[value]);
            number /= 62;
        }

        while (!stack.isEmpty()) {
            encode += stack.pop();
        }

        return encode;
    }

    public Long decode(String url){
        long num = 0;

        for (int i = 0; i < url.length(); i++) {
            char c = url.charAt(i);
            int value = INDEX_MAP.get(c);
            num = num * 62 + value;
        }

        return num;
    }
}
