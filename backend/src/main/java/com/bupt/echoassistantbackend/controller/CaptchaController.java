package com.bupt.echoassistantbackend.controller;

import cn.hutool.core.codec.Base64;
import com.bupt.echoassistantbackend.common.BaseResponse;
import com.bupt.echoassistantbackend.common.utils.PasswordUtils;
import com.bupt.echoassistantbackend.common.utils.ResultUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * captcha controller
 *
 * @author Ni Xiang
 */
@RestController
@RequestMapping("/captcha")
@Slf4j
public class CaptchaController {
    /**
     * Kaptcha 配置
     */
    @Resource
    private DefaultKaptcha defaultKaptcha;

    /**
     * 验证码
     *
     * @param request request
     * @author Ni Xiang
     */
    @GetMapping("/getCode")
    public BaseResponse<String> defaultKaptcha(HttpServletRequest request) throws IOException {
        // 生成验证码字符串并保存加密后的结果到 session 中
        String capText = defaultKaptcha.createText();
        log.info("capText:" + capText);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, PasswordUtils.encode(capText));

        // 向客户端写出
        BufferedImage bi = defaultKaptcha.createImage(capText);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        // 将图片字节数组进行 Base64 编码
        String base64EncodedImage = Base64.encode(byteArray);
        // 返回 Base64 编码后的图片字符串
        return ResultUtils.success(base64EncodedImage);
    }
}
