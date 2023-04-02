package cc.sika.common.security.controller;

import cc.sika.common.security.captcha.CustomLineCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码相关接口
 *
 * @author 吴畅
 * @创建时间 2023/3/4 - 15:01
 */
@RestController
@RequestMapping("api/captcha")
public class CaptchaController {

    private final LineCaptcha lineCaptcha;

    public CaptchaController() {
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        lineCaptcha = new CustomLineCaptcha(200, 100);
        lineCaptcha.setGenerator(randomGenerator);
    }

    @GetMapping("/create")
    public void captcha(HttpServletResponse response) throws IOException {
        lineCaptcha.getCode();
        ServletOutputStream outputStream = response.getOutputStream();
        lineCaptcha.write(outputStream);
        outputStream.close();
    }

    @GetMapping("/verify/{code}")
    public boolean verify(@PathVariable("code") String code) {
        return lineCaptcha.verify(code);
    }

    @GetMapping("/refresh")
    public void refresh(HttpServletResponse response) throws IOException {
        lineCaptcha.createCode();
        ServletOutputStream outputStream = response.getOutputStream();
        lineCaptcha.write(outputStream);
        outputStream.close();
    }
}
