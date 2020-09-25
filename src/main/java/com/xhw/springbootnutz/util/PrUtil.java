package com.xhw.springbootnutz.util;


import com.xhw.springbootnutz.model.dto.PrResult;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;

import java.util.Iterator;
import java.util.Map;

public class PrUtil {
    /**
     * 调用有输出参数的存储过程
     * @param calSql
     * @param paramMap
     * @return
     */
    public static Record callPrOut(String calSql, Map<String,Object> paramMap, Dao dao) {
        Sql sql = Sqls.create(calSql);
        sql.setEntity(dao.getEntity(PrResult.class));
        Iterator<Map.Entry<String, Object>> iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            String key = next.getKey();
            Object value = next.getValue();
            sql.params().set(key, value); // 设置入参
        }
        dao.execute(sql);
        Record re = sql.getOutParams();
        return re;
       /* Sql sql = Sqls.create("call  pr_student_verify(@p_school_id,@p_class_id,@p_student_code,@p_phone_num,@p_id_card,@p_result_code,@OUTp_result_code)");
        //sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(PrResult.class));
        sql.params().set("p_school_id", "680873162103066624"); // 设置入参
        sql.params().set("p_class_id", "680874050016251904"); // 设置入参
        sql.params().set("p_student_code", "10186csxxyx0010001"); // 设置入参
        sql.params().set("p_phone_num", "aa"); // 设置入参
        sql.params().set("p_id_card", "bb"); // 设置入参
        sql.params().set("p_result_code", "-500"); // 设置入参
        sql.params().set("OUTp_result_code", Types.VARCHAR);// 设置出参类型,注意,必须加OUT开头
        dao.execute(sql);

        Record re = sql.getOutParams();*/
    }

}
