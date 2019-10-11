package com.xhw.springbootnutz.function.dao.base;

import com.xhw.springbootnutz.model.dto.ajax.AjaxResult;
import com.xhw.springbootnutz.util.CndType;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;

import java.util.Map;

/**
 * 数据库基础操作
 *
 * @author Somer
 * @date 2018-01-09 10:53
 **/
public interface IBaseOperate {

    /**
     * 添加
     *
     * @param object
     * @return
     */
    AjaxResult add(Object object);

    /**
     * 更新
     *
     * @param object
     * @return
     */
    AjaxResult update(Object object);

    /**
     * 根据字符串id获取详情
     *
     * @param clazz
     * @param id
     * @return
     */
    AjaxResult get(Class<?> clazz, String id);

    /**
     * 根据数字类型id获取详情
     *
     * @param clazz
     * @param id
     * @return
     */
    AjaxResult get(Class<?> clazz, Integer id);

    /**
     * 分页获取
     *
     * @param params
     * @param sql
     * @param clazz
     * @param pager
     * @return
     */
    AjaxResult getPager(Map<String, CndType> params, String sql, Class<?> clazz, Pager pager);

    /**
     * 自定义SQL分页返回多条记录，可传单个参数 ( @ )
     *
     * @param params
     * @param sql
     * @param clazz
     * @param pager
     * @param condition
     * @return
     */
    AjaxResult getPagerCnd(Map<String, CndType> params, String sql, Class<?> clazz, Pager pager, Map<String, Object> condition);

    /**
     * 自定义SQL分页返回多条记录，可传（$） （@）
     * @param params
     * @param sql
     * @param clazz
     * @param pager
     * @param condition @
     * @param conditionValue $
     * @return
     */
    AjaxResult getPagerCnd(Map<String, CndType> params, String sql, Class<?> clazz, Pager pager, Map<String, Object> condition, Map<String, Object> conditionValue);

    /**
     * 删除数据
     *
     * @param clazz
     * @param id String
     * @return
     */
    AjaxResult delete(Class<?> clazz, String id);

    /**
     * 删除数据
     *
     * @param clazz
     * @param id  Integer
     * @return
     */
    AjaxResult delete(Class<?> clazz, Integer id);

    /**
     * 自定义SQL返回单条记录
     *
     * @param clazz
     * @param sql
     * @param params
     * @return
     */
    AjaxResult get(Class<?> clazz, String sql, Map<String, CndType> params);

    /**
     * 无分页获取
     *
     * @param params
     * @param sql
     * @param clazz
     * @return
     */
    AjaxResult getNoPager(Map<String, CndType> params, String sql, Class<?> clazz);

    /**
     * 自定义SQL无分页返回多条记录，可传单个参数
     *
     * @param params
     * @param sql
     * @param clazz
     * @param condition
     * @return
     */
    AjaxResult getNoPagerCnd(Map<String, CndType> params, String sql, Class<?> clazz, Map<String, Object> condition);

    /**
     * 根据条件返回唯一对象
     *
     * @param clazz
     * @param cnd
     * @return
     */
    Object getOneByColumn(Class<?> clazz, Cnd cnd);

    /**
     * 根据主键返回单个对象
     *
     * @param id
     * @param clazz
     * @return
     */
    Object get(Integer id, Class<?> clazz);

    /**
     * 根据主键返回单个对象
     *
     * @param id
     * @param clazz
     * @return
     */
    Object get(String id, Class<?> clazz);

    /**
     * 根据唯一标识判断某对象是否已经存在
     *
     * @param clazz
     * @param column
     * @param value
     * @return
     */
    Boolean judgeWhetherExist(Class<?> clazz, String column, String value);

    /**
     * 判断是否已经存在
     *
     * @param clazz
     * @param cnd
     * @return
     */
    Boolean judgeWhetherExist(Class<?> clazz, Cnd cnd);
}
