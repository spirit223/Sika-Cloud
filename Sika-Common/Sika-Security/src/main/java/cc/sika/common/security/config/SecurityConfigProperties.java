package cc.sika.common.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/20 - 19:26
 */
@ConfigurationProperties(prefix = "cc.security")
@Data
public class SecurityConfigProperties {
    private boolean enable = true;
    private List<Path4Permission> matches = generatorRule();
    private String logoutSuccessUrl = "http://ironcentury.cc";
    private String logoutUrl = "/user/logout";
    private String loginApi = "/user/login";
    private String registerApi = "/register";
    private boolean allowCors = true;


    @Data
    public static class Path4Permission {
        private String path;
        private List<String> permission;
    }

    /**
     * 生成默认接口校验权限
     */
    private List<Path4Permission> generatorRule() {
        Path4Permission getQuesP4P = new Path4Permission();
        getQuesP4P.setPath("/question/get/**");
        List<String> permissions = Arrays.asList("cc:admin:all", "cc:common:all", "cc:question:all", "cc:question:get");
        getQuesP4P.setPermission(permissions);

        Path4Permission addQuesP4P = new Path4Permission();
        addQuesP4P.setPath("/question/add/**");
        List<String> addPermissions = Arrays.asList("cc:admin:all", "cc:question:all");
        addQuesP4P.setPermission(addPermissions);

        Path4Permission updateQuesP4P = new Path4Permission();
        updateQuesP4P.setPath("/question/update/**");
        List<String> updatePermissions = Arrays.asList("cc:admin:all", "cc:question:all");
        updateQuesP4P.setPermission(updatePermissions);

        Path4Permission deleteQuesP4P = new Path4Permission();
        deleteQuesP4P.setPath("/question/delete/**");
        List<String> deletePermissions = Arrays.asList("cc:admin:all", "cc:question:all");
        deleteQuesP4P.setPermission(deletePermissions);

        Path4Permission testP4P = new Path4Permission();
        testP4P.setPath("/test/**");
        List<String> permissionList = Arrays.asList("cc:admin:all", "cc:question:all");
        testP4P.setPermission(permissionList);


        return new ArrayList<>(Arrays.asList(addQuesP4P, deleteQuesP4P, updateQuesP4P, getQuesP4P, testP4P));
    }
}
