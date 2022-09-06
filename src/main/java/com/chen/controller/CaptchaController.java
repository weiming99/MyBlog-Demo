package com.chen.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.chen.domain.ResponseResult;
import com.chen.utils.RedisCache;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 */
@RestController
public class CaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public ResponseResult getCode(HttpServletResponse response) throws IOException {
        ResponseResult responseResult = ResponseResult.ok();

        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = "CAPTCHA_CODE_KEY" + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = "char";
        if ("math".equals(captchaType)) {

            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);

        } else if ("char".equals(captchaType)) {

            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, 2, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return ResponseResult.error(e.getMessage());
        }

        HashMap<String, Object> hashMap = new HashMap<String, Object>() {{
            put("uuid", uuid);
            put("img", Base64.encode(os.toByteArray()));
        }};
        responseResult.setData(hashMap);

        return responseResult;
    }
}