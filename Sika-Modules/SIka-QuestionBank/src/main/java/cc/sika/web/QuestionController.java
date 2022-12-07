package cc.sika.web;

import cc.sika.api.common.HttpStatus;
import cc.sika.api.domain.Question;
import cc.sika.api.domain.Topic;
import cc.sika.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 问题相关操作接口
 * </p>
 * todo Swagger配置
 *
 * @author 吴畅
 * @创建时间 2022/12/7 - 16:31
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService service;

    @GetMapping("/get/{id}")
    public Question getQueById(@PathVariable("id") int questionId) {
        return service.getQuesById(questionId);
    }

    @GetMapping("/topic/{topic}")
    public List<Question> getQueByTopic(@PathVariable("topic") Topic topic) {
        return service.getQuesByTopic(topic);
    }

    @GetMapping("/get/all")
    public List<Question> getAllQues() {
        return service.getAllQues();
    }

    @PostMapping("/add")
    public HttpStatus addQue(@RequestBody Question question) {
        return service.addQuestion(question);
    }

    @PutMapping("/update")
    public HttpStatus updateQue(@RequestBody Question question) {
        return service.updateQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteQue(@PathVariable("id") int questionId) {
        return service.deleteQuestionById(questionId);
    }

}
