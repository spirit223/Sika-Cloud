package cc.sika.api.client;

import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.bean.po.Answer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/30 - 23:14
 */
@FeignClient(contextId = "remoteAnswerService", value = "question-bank-service")
public interface RemoteAnswerService {

    @PostMapping("/answer/add/batch")
    BaseResponse addAnswerBatch(List<Answer> answerList);
}
