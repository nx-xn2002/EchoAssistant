package com.bupt.echoassistantbackend.config;
 
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

/**
 * Kaptcha 配置类
 * 用于生成图形验证码
 *
 * @author Ni Xiang
 */
@Configuration
public class KaptchaConfig {
    /**
     * 边框
     */
    @Value("${kaptcha.border}")
    private String border;
 
    /**
     * 边框颜色
     */
    @Value("${kaptcha.border-color}")
    private String borderColor;
 
    /**
     * 字体颜色
     */
    @Value("${kaptcha.text-producer.font.color}")
    private String textProducerFontColor;
 
    /**
     * 字体大小
     */
    @Value("${kaptcha.text-producer.font.size}")
    private String textProducerFontSize;
 
    /**
     * 字体样式
     */
    @Value("${kaptcha.text-producer.font.names}")
    private String textProducerFontNames;
 
    /**
     * 验证码长度
     */
    @Value("${kaptcha.text-producer.char.length}")
    private String textProducerCharLength;
 
    /**
     * 图片宽度
     */
    @Value("${kaptcha.image.width}")
    private String imageWidth;
 
    /**
     * 图片高度
     */
    @Value("${kaptcha.image.height}")
    private String imageHeight;
 
    /**
     * 存储的 Session Key
     */
    @Value("${kaptcha.session.key}")
    private String sessionKey;

    /**
     * 配置 kaptcha 参数
     *
     * @return {@link DefaultKaptcha }
     * @author Ni Xiang
     */
    @Bean
    public DefaultKaptcha getDefaultKapcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", border);
        properties.setProperty("kaptcha.border.color", borderColor);
        properties.setProperty("kaptcha.textproducer.font.color", textProducerFontColor);
        properties.setProperty("kaptcha.textproducer.font.size", textProducerFontSize);
        properties.setProperty("kaptcha.textproducer.font.names", textProducerFontNames);
        properties.setProperty("kaptcha.textproducer.char.length", textProducerCharLength);
        properties.setProperty("kaptcha.image.width", imageWidth);
        properties.setProperty("kaptcha.image.height", imageHeight);
        properties.setProperty("kaptcha.session.key", sessionKey);
        //图片的干扰样式：默认存在无规则划线干扰
        //无干扰：com.google.code.kaptcha.impl.NoNoise
        //properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        //图片干扰颜色：默认为黑色
        properties.setProperty("kaptcha.noise.color", "black");
        //图片渲染效果：默认水纹
        // 水纹com.google.code.kaptcha.impl.WaterRipple
        // 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy
        // 阴影com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty("kaptcha.obscurificator.impl",
                "com.google.code.kaptcha.impl.WaterRipple");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
 
}