package com.xhw.springbootnutz.util;

import org.nutz.dao.pager.Pager;
import org.nutz.http.Http;
import org.nutz.json.Json;
import org.nutz.lang.Lang;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author Somer
 * @date 2018-08-21 10:53
 **/
public class StringUtils {



    public static final String API_URL = "https://cx.shouji.360.cn/phonearea.php";

    public static boolean NotEmpty(String str) {
        return str == null ? false : ("".equals(str) ? false : (("undefined".equals(str) ? false : true)));
    }

    public static boolean NotEmpty(Date date) {
        return date == null ? false : true;
    }

    public static boolean NotEmpty(Integer num) {
        return num == null ? false : true;
    }

    public static boolean NotEmpty(Long num) {
        return num == null ? false : true;
    }

    public static boolean NotEmpty(Double value) {
        return value == null ? false : true;
    }

    public static boolean isEmpty(String str) {
        return str == null ? true : ("".equals(str) ? true : (("undefined".equals(str) ? true : false)));
    }

    private static final String regExC = "1(33|53|8[019]|7[37])[0-9]{8}";
    //G网 18786664809
    private static final String regExcG = "((13[4-9])|(15([0-2]|[7-9]))|(18[2|3|4|7|8])|(178)|(147))[0-9]{8}";

    /**
     * 判断参数是否传递
     *
     * @param param
     * @return
     */
    public static <T> Boolean isTransfer(T... param) {
        if (param.length <= 0) {
            return false;
        }
        for (T temp : param) {
            if (null == temp || "".equals(temp) || "undefined".equals(temp) || "null".equals(temp)) {
                return false;
            }
        }
        return true;
    }

    /*
     * 1.一个运用基本类的实例
     * MessageDigest 对象开始被初始化。该对象通过使用 update 方法处理数据。
     * 任何时候都可以调用 reset 方法重置摘要。
     * 一旦所有需要更新的数据都已经被更新了，应该调用 digest 方法之一完成哈希计算。
     * 对于给定数量的更新数据，digest 方法只能被调用一次。
     * 在调用 digest 之后，MessageDigest 对象被重新设置成其初始状态。
     */
    public static String encrypByMd5(String context) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes());//update处理
            byte [] encryContext = md.digest();//调用该方法完成计算

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < encryContext.length; offset++) {//做相应的转化（十六进制）
                i = encryContext[offset];
                if (i < 0) {i += 256;}
                if (i < 16) {buf.append("0");}
                buf.append(Integer.toHexString(i));
            }
            System.out.println("32result: " + buf.toString());// 32位的加密
            System.out.println("16result: " + buf.toString().substring(8, 24));// 16位的加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断传入的所有参数是否都为空
     *
     * @return
     */
    public static <T> Boolean isNullAll(T... param) {
        for (T temp : param) {
            if (temp != null && !"".equals(temp) && !"null".equals(temp)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 获取访问客户端 IP
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = Lang.getIP(request);
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 返回 UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 判断分页
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public static Pager checkPager(Integer pageNumber, Integer pageSize) {
        if (!StringUtils.isTransfer(pageNumber, pageSize)) {
            return new Pager(1, 20);
        }
        return new Pager(pageNumber, pageSize);
    }

    /**
     * 判断字符串是否为空
     *
     * @param value
     * @return
     */
    public static String checkNull(String value) {
        if (value == null || "".equals(value) || "null".equals(value)) {
            value = "";
        }
        return value;
    }

    /**
     * 参数是否都传递
     *
     * @param param
     * @return
     */
    public static <T> boolean isAllTranslate(T... param) {
        if (param.length <= 0) {
            return false;
        }
        for (T t : param) {
            if (t == null || "".equals(t) || "undefined".equals(t) || "null".equals(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证手机号
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        Pattern p;
        Matcher m;
        boolean b = false;
        String s2 = "^(1[3456789][0-9]{9})$";// 验证手机号
        if (StringUtils.isTransfer(str)) {
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 验证只能包含数字和字母
     *
     * @param password
     * @return
     */
    public static boolean isValidatePassword(String password) {
        String s = "^[A-Za-z0-9]+$";//^[A-Za-z0-9]+$
        Pattern pattern = Pattern.compile(s);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * 字符串定长分隔
     *
     * @param
     * @return
     */
    public static String spiltLength(String src, int length) {
        if ("".equals(src) || src.length() == 0) {
            return src;
        }
        if (length >= src.length() || length <= 0) {
            return src;
        }
        String s = "";
        for (int i = 0; i + length <= src.length(); i = i + length) {
            s += src.substring(i, length + i) + ",";
        }
        return s.substring(0, s.lastIndexOf(","));
    }

    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }

    /**
     * 验证只能是汉字
     *
     * @param
     * @return
     */

    public static boolean isOnlyChinese(String... s) {
        String regx = "^[\\u4e00-\\u9fa5]{0,}$";
        for (String t : s) {
            if (t == null || "".equals(t) || "undefined".equals(t) || "null".equals(t)) {
                return false;
            }
            if (t.matches(regx) == false) {
                return false;
            }
        }
        return true;
    }

    public static int getRandom() {
        Random random = new Random();
        return random.nextInt(999);

    }

}