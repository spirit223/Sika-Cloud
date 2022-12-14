package cc.sika.service;

import cc.sika.api.common.HttpStatus;
import cc.sika.api.domain.Answer;
import cc.sika.api.domain.Question;

/**
 * 同时操作问题和答案
 *
 * @author 吴畅
 * @创建时间 2022/12/7 - 20:50
 */
public interface QueAnsService {
    HttpStatus addQueAndAns(Question question, Answer answer);

    boolean binding(int answerId, int QuestionId);
}
