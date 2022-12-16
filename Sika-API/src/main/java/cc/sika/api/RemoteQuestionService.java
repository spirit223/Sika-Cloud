package cc.sika.api;

import cc.sika.api.bean.dto.Topic;
import cc.sika.api.bean.po.Question;
import cc.sika.api.common.HttpStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 吴畅
 * @创建时间 2022/12/7 - 15:00
 */
@FeignClient(contextId = "remoteQuestionService", value = "question-bank-service")
@RequestMapping("/question")
public interface RemoteQuestionService {
    @PostMapping("/add")
    HttpStatus addQuestion(@RequestBody Question question);

    @PutMapping("/update")
    HttpStatus updateQuestion(@RequestBody Question question);

    @DeleteMapping("/delete/{id}")
    HttpStatus deleteQuestion(@PathVariable("id") int questionId);

    @GetMapping("/getOne/{id}")
    Question testQuestion(@PathVariable("id") int id);

    @GetMapping("getList/{topic}")
    List<Question> getQuestionByTopic(@PathVariable("topic") Topic questionTopic);

    @GetMapping("getAll")
    List<Question> getAllQuestion();
}
