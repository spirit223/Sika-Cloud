package cc.sika.web;

import cc.sika.api.bean.bo.QuestionWithAnswerBO;
import cc.sika.api.bean.dto.BaseResponse;
import cc.sika.api.bean.dto.Topic;
import cc.sika.api.bean.po.Question;
import cc.sika.exception.NoQuestionNumberException;
import cc.sika.exception.WriteQuestionFailException;
import cc.sika.service.QuestionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 问题相关操作接口
 * </p>
 *
 * @author 吴畅
 * @创建时间 2022/12/7 - 16:31
 */
@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController extends BaseController {
    @Resource
    private QuestionService service;


    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取题目", notes = "<span style='color: red'>通过id来获获取题目信息</span>")
    @ApiImplicitParams(@ApiImplicitParam(name = "questionId", dataTypeClass = Integer.class, value = "要获取信息的题目的id"))
    @ApiResponse(code = 200, message = "返回BaseResponse对象, 对象信息存储在data域中")
    public BaseResponse<QuestionWithAnswerBO> getQueById(@PathVariable("id") int questionId) throws NoQuestionNumberException {
        return responseSuccess(service.getQuesByIdWithAnswer(questionId));
    }

    @GetMapping("/topic/{topic}")
    @ApiOperation(value = "获取多个题目", notes = "通过分类来获取题目信息")
    @ApiImplicitParams(@ApiImplicitParam(name = "topic", dataTypeClass = Topic.class, value = "要获取信息的题目的分类"))
    @ApiResponse(code = 200, message = "返回BaseResponse对象, data域中为 符合分类的题目信息列表")
    public BaseResponse<List<QuestionWithAnswerBO>> getQueByTopic(@PathVariable("topic") Topic topic) {
        return responseSuccess(service.getQuesByTopicWithAnswer(topic));
    }

    @GetMapping("/get/all")
    @ApiOperation(value = "获取所有题目")
    @ApiResponse(code = 200, message = "返回BaseResponse对象, data域中为 所有题目信息列表")
    public BaseResponse<List<QuestionWithAnswerBO>> getAllQues() {
        return responseSuccess(service.getAllQuesWithAnswer());
    }

    /*----------------------------------------------------------------------------------*/

    /**
     * 通过 POST 方式添加一个题目
     *
     * @param question 获取到请求体中的整个 Question 对象写入数据库
     * @return 返回写入的结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "通过 POST 方式添加题目")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "question",
                    dataTypeClass = Question.class,
                    value = "要添加的题目对象",
                    required = true)
    )
    @SuppressWarnings("rawtypes")
    public BaseResponse addQue(@NotEmpty(message = "Question对象不能为空")
                               @RequestBody Question question) throws WriteQuestionFailException {
        return responseSuccess(service.addQuestion(question), "题目添加成功");
    }

    /**
     * 通过 POST 方式批量插入题目
     *
     * @param questionList 题目列表
     */
    @PostMapping("/addBatch")
    @ApiOperation(value = "POST 批量添加题目")
    @ApiImplicitParam(name = "questionList", value = "要添加的题目列表", required = true)
    @SuppressWarnings("rawtypes")
    public BaseResponse addQueList(@NotEmpty(message = "Question列表不能为空")
                                   @RequestBody List<Question> questionList) throws WriteQuestionFailException {
        int insertCount = service.addQuestionList(questionList);
        return responseSuccess("请求插入题目数量为:" + questionList.size() + ", 插入成功数据数量为: " + insertCount);
    }

    /**
     * 通过 PUT 方式更新问题
     *
     * @param question 获取到请求体中的整个 Question 对象覆盖写入数据库
     * @return 返回写入的结果
     */
    @PutMapping("/update")
    @ApiOperation(value = "通过 PUT 方式更新题目")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "question",
                    dataTypeClass = Question.class,
                    value = "要更新的题目对象, 通过id来确定, 如果更新的id值未存在, 则不会有任何操作",
                    required = true)
    )
    @SuppressWarnings("rawtypes")
    public BaseResponse updateQue(@NotEmpty(message = "Question对象不能为空")
                                  @RequestBody Question question) throws WriteQuestionFailException {
        return responseSuccess(service.updateQuestion(question), "题目修改成功");
    }

    /**
     * 通过问题 id, 使用 DELETE 请求方式删除问题
     *
     * @param questionId 要删除的问题id
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除题目", notes = "通过id来删除题目")
    @ApiImplicitParams(@ApiImplicitParam(name = "questionId", dataTypeClass = Integer.class, value = "要删除信息的题目id"))
    @SuppressWarnings("rawtypes")
    public BaseResponse deleteQue(@PathVariable("id") int questionId) throws WriteQuestionFailException {
        return responseSuccess(service.deleteQuestionById(questionId), "题目删除成功");
    }

}
