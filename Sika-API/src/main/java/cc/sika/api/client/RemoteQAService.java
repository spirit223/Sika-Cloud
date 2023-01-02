package cc.sika.api.client;

import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/31 - 17:19
 */
@FeignClient(contextId = "remoteQAService", value = "question-bank-service")
public interface RemoteQAService {

    @PostMapping("/qa/add")
    BaseResponse addOne(@RequestBody QuestionWithAnswerBO qa);

    @PostMapping("/qa/add-batch")
    BaseResponse addBatch(@RequestBody List<QuestionWithAnswerBO> qaList);

    @GetMapping("/qa/get-all")
    BaseResponse<List<QuestionWithAnswerBO>> getAll();
}
