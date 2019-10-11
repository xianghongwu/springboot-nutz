package com.xhw.springbootnutz.function.dao.base.impl;

import com.xhw.springbootnutz.function.dao.base.BaseDao;
import com.xhw.springbootnutz.function.dao.base.IBaseOperate;
import com.xhw.springbootnutz.function.dao.base.ResultUtils;
import com.xhw.springbootnutz.model.dto.ajax.*;
import com.xhw.springbootnutz.util.CndType;
import com.xhw.springbootnutz.util.FunctionUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据库基础操作实现类
 *
 * @author Somer
 * @date 2018-01-09 11:00
 **/

@Service
public class BaseOperate extends BaseDao implements IBaseOperate {

    @Override
    public AjaxResult add(Object object) {
        return AjaxResultUtils.addMessage(null != dao.insert(object) ? object : null);
    }

    @Override
    public AjaxResult update(Object object) {
        return AjaxResultUtils.updateMessage(dao.updateIgnoreNull(object) > 0 ? object : null);
    }

    @Override
    public AjaxResult get(Class<?> clazz, String id) {
        return AjaxResultUtils.getInfoMessage(dao.fetch(clazz, id));
    }

    @Override
    public AjaxResult get(Class<?> clazz, Integer id) {
        return AjaxResultUtils.getInfoMessage(dao.fetch(clazz, id));
    }

    @Override
    public AjaxResult getPager(Map<String, CndType> params, String sql, Class<?> clazz, Pager pager) {
        PageResultData<Object> resultData = new PageResultData<>();
        Cnd cnd = null;
        if (null != params) {
            cnd = FunctionUtils.MapToCnd(params);
        }
        List list = null;
        Integer totalNumber = null;
        if (null == sql || "".equals(sql)) {
            list = dao.query(clazz, cnd, pager);
            totalNumber = dao.count(clazz, cnd);
        } else {
            list = ResultUtils.getSqlList(clazz, dao, sql, cnd, pager);
            String sqlSize = sql.substring(0, sql.indexOf(".")).concat(".totalSize");
            totalNumber = ResultUtils.getSql(TotalCount.class, dao, sqlSize, cnd).getTotalNumber();
        }
        if (null != list && list.size() > 0) {
            resultData.setTotal(totalNumber);
            resultData.setRow(list);
            return AjaxResultUtils.getInfoMessage(resultData);
        }
        return new AjaxResult(ResultState.CONTENT_ERROR, ResultState.NO_DATA);
    }

    @Override
    public AjaxResult getPagerCnd(Map<String, CndType> params, String sql, Class<?> clazz, Pager pager, Map<String, Object> condition) {
        PageResultData<Object> resultData = new PageResultData<>();
        Cnd cnd = null;
        if (null != params) {
            cnd = FunctionUtils.MapToCnd(params);
        }
        List list = null;
        Integer totalNumber = null;
        if (null == sql || "".equals(sql)) {
            list = dao.query(clazz, cnd, pager);
            totalNumber = dao.count(clazz, cnd);
        } else {
            list = ResultUtils.getSqlList(clazz, dao, sql, cnd, pager, condition);
            String sqlSize = sql.substring(0, sql.indexOf(".")).concat(".totalSize");
            totalNumber = ResultUtils.getSql(TotalCount.class, dao, sqlSize, cnd, condition).getTotalNumber();
        }
        if (null != list && list.size() > 0) {
            resultData.setRow(list);
            resultData.setTotal(totalNumber);
            return AjaxResultUtils.getInfoMessage(resultData);
        }
        return new AjaxResult(ResultState.CONTENT_ERROR, ResultState.NO_DATA);
    }

    @Override
    public AjaxResult getPagerCnd(Map<String, CndType> params, String sql, Class<?> clazz, Pager pager, Map<String, Object> condition, Map<String,Object> conditionValue) {
        PageResultData<Object> resultData = new PageResultData<>();
        Cnd cnd = null;
        if (null != params) {
            cnd = FunctionUtils.MapToCnd(params);
        }
        List list = null;
        Integer totalNumber = null;
        if (null == sql || "".equals(sql)) {
            list = dao.query(clazz, cnd, pager);
            totalNumber = dao.count(clazz, cnd);
        } else {
            list= ResultUtils.getSqlList(clazz, dao, sql, cnd,pager, condition, conditionValue);
            //list = ResultUtils.getSqlList(clazz, dao, sql, cnd, pager, condition);
            String sqlSize = sql.substring(0, sql.indexOf(".")).concat(".totalSize");
            totalNumber = ResultUtils.getSql(TotalCount.class, dao, sqlSize, cnd, condition).getTotalNumber();
        }
        if (null != list && list.size() > 0) {
            resultData.setRow(list);
            resultData.setTotal(totalNumber);
            return AjaxResultUtils.getInfoMessage(resultData);
        }
        return new AjaxResult(ResultState.CONTENT_ERROR, ResultState.NO_DATA);
    }

    @Override
    public AjaxResult delete(Class<?> clazz, String id) {
        int state = dao.clear(clazz, Cnd.where("id", "=", id));
        return AjaxResultUtils.delInfoMessage(state > 0 ? true : false);
    }

    @Override
    public AjaxResult delete(Class<?> clazz, Integer id) {
        int state = dao.clear(clazz, Cnd.where("id", "=", id));
        return AjaxResultUtils.delInfoMessage(state > 0 ? true : false);
    }

    @Override
    public AjaxResult get(Class<?> clazz, String sql, Map<String, CndType> params) {
        Cnd cnd = FunctionUtils.MapToCnd(params);
        return AjaxResultUtils.getInfoMessage(ResultUtils.getSql(clazz, dao, sql, cnd));
    }

    @Override
    public AjaxResult getNoPager(Map<String, CndType> params, String sql, Class<?> clazz) {
        Cnd cnd = null;
        if (null != params) {
            cnd = FunctionUtils.MapToCnd(params);
        }
        List list = null;
        if (null == sql || "".equals(sql)) {
            list = dao.query(clazz, cnd);
        } else {
            list = ResultUtils.getSqlListNoPager(clazz, dao, sql, cnd);
        }
        if (null != list && list.size() > 0) {
            return new AjaxResult(ResultState.OK, ResultState.GET_SUCCESS, list);
        } else {
            return new AjaxResult(ResultState.CONTENT_ERROR, ResultState.NO_DATA);
        }
    }

    @Override
    public AjaxResult getNoPagerCnd(Map<String, CndType> params, String sql, Class<?> clazz, Map<String, Object> condition) {
        Cnd cnd = FunctionUtils.MapToCnd(params);
        List list = ResultUtils.getSqlList(clazz, dao, sql, cnd, condition);
        if (null != list && list.size() > 0) {
            return new AjaxResult(ResultState.OK, ResultState.GET_SUCCESS, list);
        } else {
            return new AjaxResult(ResultState.CONTENT_ERROR, ResultState.NO_DATA);
        }
    }

    @Override
    public Object getOneByColumn(Class<?> clazz, Cnd cnd) {
        return ResultUtils.getByOneColumn(clazz, dao, cnd);
    }

    @Override
    public Object get(Integer id, Class<?> clazz) {
        return dao.fetch(clazz, id);
    }

    @Override
    public Object get(String id, Class<?> clazz) {
        return dao.fetch(clazz, id);
    }

    @Override
    public Boolean judgeWhetherExist(Class<?> clazz, String column, String value) {
        int size = dao.query(clazz, Cnd.where(column, "=", value)).size();
        return size > 0 ? true : false;
    }

    @Override
    public Boolean judgeWhetherExist(Class<?> clazz, Cnd cnd) {
        int size = dao.query(clazz, cnd).size();
        return size > 0 ? true : false;
    }
}
