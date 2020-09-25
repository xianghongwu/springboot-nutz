package com.xhw.springbootnutz.model.param;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: FengYunJun
 * @Date: 2019/4/8 08:52
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "jpush")
@Data
public class JPushParam {
    private String masterSecret;
    private String appKey;
}
