package cc.sika.api.client;

import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.bean.po.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 15:00
 */
@FeignClient(contextId = "remoteQuestionService", value = "question-bank-service")
public interface RemoteQuestionService {

    @GetMapping("/question/get/{id}")
    BaseResponse<QuestionWithAnswerBO> testQuestion(@PathVariable("id") int id);

    @PostMapping("/question/add")
    BaseResponse addQue(Question question);

    @PostMapping("/question/add/batch")
    BaseResponse addBatch(List<Question> questionList);
}
