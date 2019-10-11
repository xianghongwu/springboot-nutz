package com.xhw.springbootnutz.util;

import org.nutz.dao.Cnd;
import org.nutz.dao.util.cri.Static;

import java.util.Map;

/**
 * 查询工具类
 *
 * @author Somer
 * @date 2018-04-18 20:33
 */
public class FunctionUtils {

    /**
     * 将Map转换为查询条件Cnd
     *
     * @param map
     * @return
     */
    public static Cnd MapToCnd(Map<String, CndType> map) {
        Cnd result = Cnd.where("1", "=", 1);
        map.forEach((k, v) -> {
            if (v.getType().equals("likeRight")) {
                result.and(k, "like", v.getDate() + "%");
            } else if (v.getType().equals("like")) {
                result.and(k, "like", "%" + v.getDate() + "%");
            } else if (v.getType().equals("desc")) {
                result.desc(k);
            }else if (v.getType().equals("descValue")) {
                result.desc(v.getDate()+"");
            } else if (v.getType().equals("asc")) {
                result.asc(k);
            } else if (v.getType().equals("today")) {
                result.and(k, ">=", v.getDate().toString() + " 00:00:00").and(k, "<=",
                        v.getDate().toString() + " 23:59:59");
            } else if (v.getType().equals("todaynew")) {
                result.and(new Static(k + " BETWEEN '" + v.getDate().toString() + " 00:00:00" + "' AND '" + v.getDate().toString() + " 23:59:59'"));
            }else if (v.getType().equals("toweeknew")) {
                Integer month = DateUtils.getMonthByTime(v.getDate().toString());
                Integer day = DateUtils.getDayByTime(v.getDate().toString());
                Integer year = DateUtils.getYearByTime(v.getDate().toString());
                String beginTime=year+"-"+month+"-"+(day-7)+" 00:00:00";
                String endTime=year+"-"+month+"-"+day+" 23:59:59";
                result.and(new Static(k + " BETWEEN '" + beginTime + "' AND '" + endTime + "'"));
            }else if (v.getType().equals("tomonthnew")) {
                String beginTime=v.getDate()+"-01";
                String endTime=v.getDate()+"-"+DateUtils.getLastDayOfMonth(beginTime);
                result.and(new Static(k + " BETWEEN '" + beginTime + "' AND '" + endTime + "'"));
            } else if (v.getType().equals("inDateweek")) {
                Map<String, String> weekDate = DateUtils.getWeekDate();
                String beginTime=weekDate.get("mondayDate");
                String endTime=weekDate.get("sundayDate");
                result.and(new Static(k + " BETWEEN '" + beginTime + "' AND '" + endTime + "'"));
            } else if (v.getType().equals("in")) {
                //String ss="'123','3456'";
                result.and(k, "in", v.getDate());
            } else if (v.getType().equals("or")) {
                result.or(k, "=", v.getDate());
            } else if (v.getType().equals("between")) {
                result.and(k, ">=", "1").and(k, "<=", v.getDate());
            } else if (v.getType().equals("lessThan")) {
                result.and(k, "<=", v.getDate());
            } else if (v.getType().equals("not in")) {
                result.and(k, "not in", v.getDate());
            } else if (v.getType().equals("stringDate")) {
                result.and(new Static(k+" = " + "'"+v.getDate()+"'"));
            }else {
                result.and(k, v.getType(), v.getDate());
            }
        });
        return result;
    }

    /**
     * 生成新的查询条件
     *
     * @param type
     * @param obj
     * @return
     */
    public static CndType Cond(String type, Object obj) {
        return new CndType(type, obj);
    }

    /**
     * 判断参数是否为空
     *
     * @param param
     * @return
     */
    public static Boolean checkParam(String... param) {
        if (param.length <= 0) {
            return false;
        }
        for (String temp : param) {
            if (null == temp || "".equals(temp) || "undefined".equals(temp) || "null".equals(temp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断参数是否为空
     *
     * @param param
     * @return
     */
    public static Boolean checkParam(Integer... param) {
        if (param.length <= 0) {
            return false;
        }
        for (Integer temp : param) {
            if (null == temp || "".equals(temp) || "undefined".equals(temp) || "null".equals(temp)) {
                return false;
            }
        }
        return true;
    }
}