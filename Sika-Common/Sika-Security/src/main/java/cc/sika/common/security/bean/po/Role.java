package cc.sika.common.security.bean.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.asn1.cmc.PendInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应 sys_role 表的数据持久化对象
 * @author 吴畅
 * @创建时间 2022/12/19 - 13:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private int id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色权限字符串
     */
    private String roleKey;
    /**
     * 角色状态:
     * <ul>
     *     <li>0 正常</li>
     *     <li>1 停用</li>
     * </ul>
     */
    private char status;
    /**
     * 角色删除标志:
     * <ul>
     *     <li>0 未删除</li>
     *     <li>1 已删除</li>
     * </ul>
     */
    private int delFlag;
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
     * 备注
     */
    private String remark;

    public Role(String name, String roleKey, char status, int delFlag, int createBy, Date createTime, int updateBy, Date updateTime, String remark) {
        this.name = name;
        this.roleKey = roleKey;
        this.status = status;
        this.delFlag = delFlag;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.remark = remark;
    }
}
