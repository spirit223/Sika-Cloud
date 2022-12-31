package cc.sika.web;

import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.bean.po.Answer;
import cc.sika.api.bean.po.Question;
import cc.sika.api.common.HttpStatus;
import cc.sika.api.web.BaseController;
import cc.sika.service.QueAnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/31 - 17:12
 */
@RestController
@RequestMapping("/qa")
public class QAController extends BaseController {

    @Autowired
    private QueAnsService qaService;

    @PostMapping("/add")
    public BaseResponse addOne(@RequestBody QuestionWithAnswerBO qa) {
        Question question = qa.getQuestion();
        Answer answer = qa.getAnswer();
        HttpStatus httpStatus = qaService.addQA(question, answer);
        if (httpStatus == HttpStatus.SUCCESS) {
            return responseSuccess();
        }
        return responseFail(HttpStatus.ERROR);
    }

    @PostMapping("/addBatch")
    public BaseResponse addBatch(@RequestBody List<QuestionWithAnswerBO> qaList) {
        HttpStatus httpStatus = qaService.addQABatch(qaList);
        if (httpStatus == HttpStatus.SUCCESS) {
            return responseSuccess();
        }
        return responseFail(HttpStatus.ERROR);
    }
}
