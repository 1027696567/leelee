package com.example;

/**
 * @author chen
 * @program leelee
 * @description
 * @create 2019-10-16 16:38
 */

public class Test {
    public static void main(String[] args) {
        String s = "abcdef";
        char[] a = s.toCharArray();
        StringBuffer sb = new StringBuffer(s);
        sb.reverse();
        sb.length();
        for (char b :a){
            System.out.println(b);
            String.valueOf(b);
        }
    }
}
