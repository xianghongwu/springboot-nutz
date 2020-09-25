package com.xhw.springbootnutz.util.treeutil;

import java.util.List;

/**
 *  树形数据实体接口
 * @param <E>
 */
public interface TreeEntity<E> {
    public String getId();
    public String getParentId();
    public String getName();
    public void setChildList(List<E> childList);
}
