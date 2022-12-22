package cc.sika.common.security.bean.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 对饮 sys_permission 表的数据持久化对象
 * @author 吴畅
 * @创建时间 2022/12/19 - 13:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    private int id;
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限标识
     */
    private String permissions;
    /**
     * 创建人用户id
     */
    private int createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人用户id
     */
    private int updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志位:
     * <ul>
     *     <li>0 未删除</li>
     *     <li>1 已删除</li>
     * </ul>
     */
    private char delFlag;
    /**
     * 备注
     */
    private String remark;

    public Permission(String permissionName, String permissions, int createBy, Date createTime, int updateBy, Date updateTime, char delFlag, String remark) {
        this.permissionName = permissionName;
        this.permissions = permissions;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
        this.remark = remark;
    }
}
