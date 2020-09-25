package com.xhw.springbootnutz.util.treeutil;

import org.nutz.json.Json;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        /*List<Menu> list=new ArrayList<>();
        Menu menu1=new Menu();
        menu1.setId("1");
        menu1.setParentId("0");
        menu1.setName("菜单1");
        list.add(menu1);

        Menu menu2=new Menu();
        menu2.setId("2");
        menu2.setParentId("0");
        menu2.setName("菜单2");
        list.add(menu2);

        Menu menu3=new Menu();
        menu3.setId("3");
        menu3.setParentId("1");
        menu3.setName("菜单11");
        list.add(menu3);

        Menu menu4=new Menu();
        menu4.setId("4");
        menu4.setParentId("3");
        menu4.setName("菜单111");
        list.add(menu4);

        List<Menu> menus=TreeParser.getTreeList("0",list);
        System.out.println(Json.toJson(menus));*/
        List<Integer> delList = new ArrayList<>();
        delList.add(1);
        delList.add(2);
        delList.add(4);

        List<Integer> addList = new ArrayList<>();
        addList.add(4);
        addList.add(5);
        addList.add(1);

        List<Integer> delList_copy = new ArrayList<>();
        delList_copy.addAll(delList);

        for (int i=0;i<delList.size();i++) {
            for (Integer add : addList) {
                if(delList.get(i).equals(add)){
                    delList.remove(i);
                    i--;
                    break;
                }
            }
        }

        for (int i=0;i<addList.size();i++) {
            for (Integer del : delList_copy) {
                if(del.equals(addList.get(i))){
                    addList.remove(i);
                    i--;
                    break;
                }
            }
        }

        System.out.println(delList);
        System.out.println(addList);

    }
}
