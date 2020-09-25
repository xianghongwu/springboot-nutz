package com.xhw.springbootnutz.model.dto.ajax;

/**
 * Ajax返回状态码
 *
 * @author Somer
 * @date 2018-05-25 15:52
 **/
public class AjaxCode {

    // 请求成功
    public static final Integer SUCCESS = 200;

    // 请求内容错误
    public static final Integer CONTENT_ERROR = 201;
    public static final String CONTENT_ERROR_MESSAGE = "请求错误";
    public static final String NO_DATA = "暂无数据";
    public static final String PARAM_ERROR_MESSAGE = "参数非法";
    public static final String PARAM_TYPE_MISMATCH = "参数类型不匹配";

    // 失败
    public static final Integer FAIL = 400;

    // 没有权限
    public static final Integer NO_PERMISSION = 403;
    public static final String NO_PERMISSION_MESSAGE = "没有访问权限";

    // 资源不存在
    public static final Integer NOT_FOUND = 404;
    public static final String NOT_FOUND_MESSAGE = "请求资源不存在";

    // 请求方式错误
    public static final Integer REQUEST_METHOD_ERROR = 405;
    public static final String REQUEST_METHOD_ERROR_MESSAGE = "请求方式错误";

    // 内部错误
    public static final Integer INNER_ERROR = 500;
    public static final String INNER_ERROR_MESSAGE = "服务器内部错误";

    // 添加成功
    public static final String ADD_SUCCESS = "添加成功";
    public static final String SAVE_SUCCESS = "保存成功";

    // 添加异常
    public static final String ADD_EXCEPTION = "添加异常";

    // 更新成功
    public static final String UPDATE_SUCCESS = "更新成功";

    // 操作成功
    public static final String OPERATION_SUCCESS = "操作成功";

    // 更新异常
    public static final String UPDATE_EXCEPTION = "更新异常";

    // 获取成功
    public static final String GET_SUCCESS = "获取成功";

    // 删除成功
    public static final String DELETE_SUCCESS = "删除成功";
    public final static String SELECT_DELETE_OBJECT = "请先选择要删除对象";

    // 删除异常
    public static final String DELETE_EXCEPTION = "删除异常";

    // 响应超时
    public static final Integer TIME_OUT_ERROR = 504;
    public static final String TIME_OUT_ERROR_TEXT = "响应超时";

    public static final String USER_LOGIN_SUCCESS = "登录成功";
    public static final String USER_NOT_EXIST = "用户不存在";
    public static final String USER_ALREADY_LOCKED = "账户已被冻结";
    public static final String USER_AUTH_FAIL = "认证失败";
    public static final String USER_NOT_LOGIN_IN = "用户未登录";
    public static final String USER_PASSWORD_ERROR = "密码错误";
    public static final String USER_ALREADY_LOGIN_IN = "用户已登录";
    public static final String ACCOUNT_DISABLE = "账户已被禁用";
    public static final String USERNAME_OR_PASSWORD_ERROR = "用户名或密码错误";
    public static final String USERNAME_OR_PASSWORD_EMPTY = "用户名和密码不能为空";
    public final static String TOKEN_OR_IMEI_ERROR = "token错误";

    public final static String JSON_EXCEPTION = "Json格式错误";

    public static final Integer LOGIN_INVALID = 601;
    public static final String LOGIN_INVALID_MESSAGE = "登陆已失效，请重新登陆！";

    public final static String TOKEN_EMPTY = "token不能为空";

    public final static String TOKEN_EXPIRED_TEXT = "token失效，请重新登录!";

    public final static String FILE_NOT_EXIST = "文件不存在";
    public final static String FILE_UPLOAD_FAIL = "文件上传失败";
    public final static String FILE_UPLOAD_SUCCESS = "文件上传成功";


}
