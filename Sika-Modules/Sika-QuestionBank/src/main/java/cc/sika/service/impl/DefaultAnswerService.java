package cc.sika.service.impl;

import cc.sika.api.bean.po.Answer;
import cc.sika.api.common.HttpStatus;
import cc.sika.exception.NoAnswerException;
import cc.sika.exception.WriteFailException;
import cc.sika.mapper.AnswerMapper;
import cc.sika.service.AnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 吴畅
 * @创建时间 2022/12/23 - 16:56
 */
@Service
public class DefaultAnswerService implements AnswerService {
    @Resource
    private AnswerMapper answerMapper;

    @Override
    public Answer getAnswer(int id) throws NoAnswerException {
        Answer answer = answerMapper.getAnswerById(id);
        if (Objects.isNull(answer)) {
            throw new NoAnswerException();
        }
        return answer;
    }

    @Override
    public List<Answer> getAnswerByQue(int questionId) throws NoAnswerException {
        List<Answer> answerList = answerMapper.getAnswerByQueId(questionId);
        if (Objects.isNull(answerList) || answerList.isEmpty()) {
            throw new NoAnswerException();
        }
        return answerList;
    }

    @Override
    public HttpStatus deleteAnswer(int answerId) throws WriteFailException {
        int deleteResult = answerMapper.deleteAnswerById(answerId);
        if (deleteResult == 0) {
            throw new WriteFailException("删除失败, 请检查数据库状态");
        }
        return HttpStatus.SUCCESS;
    }

    @Override
    public int deleteAnswerBatch(List<Integer> deleteIdList) {
        deleteIdList = deleteIdList.stream().distinct().collect(Collectors.toList());
        return answerMapper.deleteAnswerBatch(deleteIdList);
    }

    @Override
    public HttpStatus addAnswer(Answer answer) throws WriteFailException {
        int addResult = answerMapper.addAnswer(answer);
        if (addResult == 0) {
            throw new WriteFailException("添加失败, 请检查数据库状态");
        }
        return HttpStatus.SUCCESS;
    }

    @Override
    public int addAnswerBatch(List<Answer> answerList) {
        /* 列表去重 */
        answerList = answerList.stream().distinct().collect(Collectors.toList());
        return answerMapper.addAnswerList(answerList);
    }

    @Override
    public HttpStatus updateAnswer(Answer answer) throws WriteFailException {
        int updateResult = answerMapper.updateAnswer(answer);
        if (updateResult == 0) {
            throw new WriteFailException("更新失败, 请检查答案id是否存在 或 数据库运行状态");
        }
        return HttpStatus.SUCCESS;
    }

    @Override
    public int updateAnswerBatch(List<Answer> answerList) {
        return answerMapper.updateAnswerBatch(answerList);
    }
}
