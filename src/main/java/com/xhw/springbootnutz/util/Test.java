package com.xhw.springbootnutz.util;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        String str="17685318292,15185122592,16685401097,15902608012,15285632723,18286181167,18786116520,18285126074,18302634519,18685118630,13595021585,15285070454,18593872973,18593871837,13765179989,15186999332,18798759712,18985155612,13511997727,17584710298,18585057849,15286021584,18085168921";
        String[] split = str.split(",");
        for (String s : split) {
            String temp=s.substring(0,7);
            list.add(temp);
        }

        List<String > list1 = new ArrayList();
        for (String s : list) {

            for (int i=0;i<=9;i++){
                list1.add(s+i);
            }
        }

        List<String > list2 = new ArrayList();
        for (String s : list1) {
            for (int i=0;i<=9;i++){
                list2.add(s+i);
            }
        }

        List<String > list3 = new ArrayList();
        for (String s : list2) {
            for (int i=0;i<=9;i++){
                list3.add(s+i);
            }
        }

        List<String > list4 = new ArrayList();
        for (String s : list3) {
            for (int i=0;i<=9;i++){
                list4.add(s+i);
            }
        }
        for (String s : split) {
            list4.remove(s);
        }

        System.out.println(list4.size());


    }
}
