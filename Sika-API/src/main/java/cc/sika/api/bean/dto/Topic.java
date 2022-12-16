package cc.sika.api.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分类标识, 对字符串的封装
 *
 * @author 吴畅
 * @创建时间 2022/12/7 - 15:08
 */
@Data
@AllArgsConstructor
public class Topic {
    private String topicName;

    @Override
    public String toString() {
        return this.getTopicName();
    }
}
