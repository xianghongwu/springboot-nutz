package com.xhw.springbootnutz.util.treeutil;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    public String id;
    public String name;
    public String parentId;
    public List<Menu> childList;

}
