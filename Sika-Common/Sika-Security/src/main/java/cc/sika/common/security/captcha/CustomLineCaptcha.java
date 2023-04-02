package cc.sika.common.security.captcha;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;

import java.io.OutputStream;

/**
 * @author 吴畅
 * @创建时间 2023/3/4 - 17:10
 */
public class CustomLineCaptcha extends LineCaptcha {
    public CustomLineCaptcha(int width, int height) {
        super(width, height);
    }

    @Override
    public void write(OutputStream out) {
        byte[] bytes = getImageBytes();
        String s = "data:image/png;base64," + Base64.encode(bytes).trim();
        IoUtil.write(out, false, s.getBytes());
    }
}
