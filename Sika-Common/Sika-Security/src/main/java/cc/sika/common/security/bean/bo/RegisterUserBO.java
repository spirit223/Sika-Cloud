package cc.sika.common.security.bean.bo;

import cc.sika.common.security.bean.vo.RegisterVerifyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 收集注册表单信息的BO对象
 *
 * @author 吴畅
 * @创建时间 2023/3/4 - 19:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserBO {
    private String username;
    private String nickName;
    private String password;
    private Date birthday;
    private String email;

    public RegisterUserBO(RegisterVerifyVO vo) {
        this.username = vo.getName();
        this.nickName = vo.getNick();
        this.password = vo.getPass();
        this.birthday = vo.getBirthday();
        this.email = vo.getEmail();
    }
}
