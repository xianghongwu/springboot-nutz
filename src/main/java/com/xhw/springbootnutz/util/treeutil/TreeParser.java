package com.xhw.springbootnutz.util.treeutil;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析树形数据工具类
 */
public class TreeParser {
    /**
     * 解析树形数据
     * @param topId
     * @param entityList
     * @return
     */
    public static List<Menu> getTreeList(String topId, List<Menu> entityList) {
        List<Menu> resultList=new ArrayList<>();
        //获取顶层元素集合
        String parentId;
        for (Menu entity : entityList) {
            parentId=entity.getParentId();
            if(parentId==null||topId.equals(parentId)){
                resultList.add(entity);
            }
        }
        //获取每个顶层元素的子数据集合
        for (Menu entity : resultList) {
            entity.setChildList(getSubList(entity.getId(),entityList));
        }
        return resultList;
    }

    /**
     * 获取子数据集合
     * @param id
     * @param entityList
     * @return
     */
    private  static  List<Menu> getSubList(String id, List<Menu> entityList) {
        List<Menu> childList=new ArrayList<>();
        String parentId;
        //子集的直接子对象
        for (Menu entity : entityList) {
            parentId=entity.getParentId();
            if(id.equals(parentId)){
                childList.add(entity);
            }
        }
        //子集的间接子对象
        for (Menu entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }
        //递归退出条件
        if(childList.size()==0){
            return null;
        }
        return childList;
    }
}
