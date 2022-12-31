package cc.sika.web;

import cc.sika.api.bean.bo.AnswerBO;
import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.bean.po.Answer;
import cc.sika.api.common.HttpStatus;
import cc.sika.api.web.BaseController;
import cc.sika.exception.NoAnswerException;
import cc.sika.exception.WriteFailException;
import cc.sika.service.AnswerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 吴畅
 * @创建时间 2022/12/23 - 18:47
 */
@RestController
@RequestMapping("/answer")
public class AnswerController extends BaseController {

    @Resource(name = "defaultAnswerService")
    private AnswerService answerService;

    @GetMapping("/getwa/{answerId}")
    public BaseResponse<AnswerBO> getAnswer(@PathVariable("answerId") int answerId) throws NoAnswerException {
        Answer answer = answerService.getAnswer(answerId);
        return responseSuccess(new AnswerBO(answer));
    }

    @GetMapping("/getwq/{questionId}")
    public BaseResponse<List<AnswerBO>> getAnswerByQue(@PathVariable("questionId") int questionId) throws NoAnswerException {
        List<Answer> answerList = answerService.getAnswerByQue(questionId);
        List<AnswerBO> answerBOList = answerList.stream().map(AnswerBO::new).collect(Collectors.toList());
        return responseSuccess(answerBOList);
    }

    @PostMapping("/add")
    public BaseResponse<HttpStatus> addAnswer(@NotNull(message = "添加答案数据不能为空")
                                              @RequestBody Answer answer) throws WriteFailException {
        answer.setAnswerId(null);
        return responseSuccess(answerService.addAnswer(answer), "答案添加成功");
    }

    @PostMapping("/add/batch")
    public BaseResponse addAnswerBatch(@RequestBody List<Answer> answerList) {
        if (answerList.isEmpty()) {
            return responseSuccess(HttpStatus.ACCEPTED, "添加列表为空");
        }
        answerList.forEach(item -> item.setAnswerId(null));
        int addCount = answerService.addAnswerBatch(answerList);
        return responseSuccess(null, "添加答案数量为: " + addCount);
    }

    @DeleteMapping("/delete/{answerId}")
    public BaseResponse<HttpStatus> deleteAnswer(@PathVariable("answerId") int answerId) throws WriteFailException {
        return responseSuccess(answerService.deleteAnswer(answerId), "删除成功");
    }

    @DeleteMapping("/delete/batch")
    @SuppressWarnings("rawtypes")
    public BaseResponse deleteAnswerBatch(@RequestBody List<Integer> deleteIdList) {
        if (deleteIdList.isEmpty()) {
            return responseSuccess(HttpStatus.ACCEPTED, "删除列表为空");
        }
        int deleteCount = answerService.deleteAnswerBatch(deleteIdList);
        return responseSuccess(null, "删除答案数量为: " + deleteCount);
    }

    @PutMapping("/update")
    @SuppressWarnings("rawtypes")
    public BaseResponse updateAnswer(@RequestBody Answer answer) throws WriteFailException {
        if (answer == null) {
            return responseSuccess(HttpStatus.ACCEPTED, "没有需要删除的数据");
        }
        if (answer.getAnswerId() == null) {
            return responseFail(HttpStatus.BAD_REQUEST, "更新数据未携带id");
        }
        return responseSuccess(answerService.updateAnswer(answer), "更新成功");
    }

    @PutMapping("/update/batch")
    @SuppressWarnings("rawtypes")
    public BaseResponse updateAnswerBatch(@RequestBody List<Answer> answerList) {
        answerList.forEach(answer -> {
            if (answer.getAnswerId() == null) {
                answerList.remove(answer);
            }
        });
        int updateCount = answerService.updateAnswerBatch(answerList);
        return responseSuccess(null, "修改数量为: " + updateCount);
    }
}
