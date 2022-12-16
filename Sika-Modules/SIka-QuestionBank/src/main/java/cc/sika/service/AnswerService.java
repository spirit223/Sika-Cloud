package cc.sika.service;

import cc.sika.api.bean.po.Answer;

/**
 * 操作答案
 *
 * @author 吴畅
 * @创建时间 2022/12/7 - 20:52
 */
public interface AnswerService {
    Answer getAnswerById(int id);
}
