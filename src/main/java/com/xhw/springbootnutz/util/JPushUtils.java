package com.xhw.springbootnutz.util;


import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.schedule.ScheduleResult;
import com.google.common.collect.Maps;
import com.xhw.springbootnutz.model.param.JPushParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 极光推送帮助类
 */
@Component
public class JPushUtils {
    @Autowired
    JPushParam jpushParam;


    protected static final Logger LOG = LoggerFactory.getLogger(JPushUtils.class);

    /**
     * 推送消息
     *
     * @param alias   别名
     * @param message 推送的内容
     * @param extras  其他内容
     */
    public void jpushMessageByalias(List<String> alias, String message, Map<String, String> extras) {
        //创建JPushClient(极光推送的实例)
        JPushClient jpushClient = new JPushClient(jpushParam.getMasterSecret(), jpushParam.getAppKey());
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.alias(alias))//指定用户
                .setMessage(Message.newBuilder().setMsgContent(message).addExtras(extras).build())//自定义消息
                //发送内容
                .setOptions(Options.newBuilder()
                        .setTimeToLive(86400)//s 消息在JPush服务器的失效时间（测试使用参数）默认一天86400
                        .build())

                .build();
        try {
            PushResult pu = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
            LOG.error("连接错误。应该稍后重试。 ", e);
        } catch (APIRequestException e) {
            e.printStackTrace();
            LOG.error("来自JPush服务器的错误响应。应该检查并修复它。 ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }


    /**
     * 推送消息全部
     *
     * @param message 推送的内容
     * @param extras  其他内容
     */
    public void jpushMessageAndroidAll(String message, Map<String, String> extras) {
        //创建JPushClient(极光推送的实例)
        JPushClient jpushClient = new JPushClient(jpushParam.getMasterSecret(), jpushParam.getAppKey());
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(message).addExtras(extras).build())//自定义消息
                //发送内容
                .setOptions(Options.newBuilder()
                        .setTimeToLive(86400)//s 消息在JPush服务器的失效时间（测试使用参数）默认一天86400
                        .build())
                .build();
        try {
            jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
            LOG.error("连接错误。应该稍后重试。 ", e);
        } catch (APIRequestException e) {
            e.printStackTrace();
            LOG.error("来自JPush服务器的错误响应。应该检查并修复它。 ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 发生定时消息
     *
     * @param alias        别名
     * @param message      推送的内容
     * @param extras       其他内容
     * @param scheduleTime 好久发送
     * @param name         定时发送任务名称
     * @return
     */
    public ScheduleResult delayedJpushMessageByalias(String alias, String message, Map<String, String> extras, Date scheduleTime, String name) {
        JPushClient jPushClient = new JPushClient(jpushParam.getMasterSecret(), jpushParam.getAppKey());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(scheduleTime);
        ScheduleResult result = null;
        PushPayload push = PushPayload.newBuilder().setPlatform(Platform.android())
                .setMessage(Message.newBuilder().setMsgContent(message).addExtras(extras).build())
                .setAudience(Audience.alias(alias))
                .setOptions(Options.newBuilder()
                        .setTimeToLive(86400)//s 消息在JPush服务器的失效时间（测试使用参数）默认一天86400
                        .build())
                .build();
        try {
            result = jPushClient.createSingleSchedule(name, time, push);
        } catch (APIConnectionException e) {
            LOG.error("连接错误。应该稍后重试。 ", e);
        } catch (APIRequestException e) {
            LOG.error("来自JPush服务器的错误响应。应该检查并修复它。", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
        return result;
    }

    /**
     * 延迟推送消息全部
     *
     * @param message 推送的内容
     * @param extras  其他内容
     */
    public ScheduleResult delayedJpushMessageAndroidAll(String message, Map<String, String> extras, Date scheduleTime, String name) {
        //创建JPushClient(极光推送的实例)
        //JPushClient jpushClient = new JPushClient(jpushParam.getMasterSecret(), jpushParam.getAppKey());
        JPushClient jpushClient = new JPushClient("b162950868643647fdee0b01", "0f0f7f8c66cbe9346b499a94");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(scheduleTime);
        ScheduleResult result = null;
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(message).addExtras(extras).build())//自定义消息
                //发送内容
                .setOptions(Options.newBuilder()
                        .setTimeToLive(86400)//s 消息在JPush服务器的失效时间（测试使用参数）默认一天86400
                        .build())
                .build();
        try {
            result = jpushClient.createSingleSchedule(name, time, payload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
            LOG.error("连接错误。应该稍后重试。 ", e);
        } catch (APIRequestException e) {
            e.printStackTrace();
            LOG.error("来自JPush服务器的错误响应。应该检查并修复它。 ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
        return result;
    }

    /**
     * 根据scheduleId删除定时任务
     *
     * @param
     * @decripe
     */
    public void DeleteJpushSchedule(String scheduleId) {
        try {
            //JPushClient jPushClient = new JPushClient(jpushParam.getMasterSecret(), jpushParam.getAppKey());
            JPushClient jPushClient = new JPushClient("b162950868643647fdee0b01", "0f0f7f8c66cbe9346b499a94");
            jPushClient.deleteSchedule(scheduleId);
        } catch (APIConnectionException e) {
            LOG.error("连接错误。应该稍后重试。", e);
        } catch (APIRequestException e) {
            LOG.error("来自JPush服务器的错误响应。应该检查并修复它。 ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    public void test(String message, Map extras) {
        //创建JPushClient(极光推送的实例)
        JPushClient jpushClient = new JPushClient("b162950868643647fdee0b01", "0f0f7f8c66cbe9346b499a94");
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(message).addExtras(extras).build())//自定义消息
                //发送内容
                .setOptions(Options.newBuilder()
                        .setTimeToLive(86400)//s 消息在JPush服务器的失效时间（测试使用参数）默认一天86400
                        .build())
                .build();
        try {
            jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
            LOG.error("连接错误。应该稍后重试。 ", e);
        } catch (APIRequestException e) {
            e.printStackTrace();
            LOG.error("来自JPush服务器的错误响应。应该检查并修复它。 ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }


}
