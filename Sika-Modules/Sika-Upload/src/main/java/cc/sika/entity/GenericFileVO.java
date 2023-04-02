package cc.sika.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示通用文件信息的VO模型, 包含
 * <ul>
 *     <li>文件名</li>
 *     <li>存储路径</li>
 *     <li>大小</li>
 *     <li>创建时间</li>
 *     <li>修改时间</li>
 *     <li>最后访问时间</li>
 * </ul>
 *
 * @author 吴畅
 * @创建时间 2023/2/3 - 16:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericFileVO {
    private String filename;
    private String location;
    private Boolean isFile;
    private Long size;
    private Long createTime;
    private Long updateTime;
    private Long lastVisited;
}
