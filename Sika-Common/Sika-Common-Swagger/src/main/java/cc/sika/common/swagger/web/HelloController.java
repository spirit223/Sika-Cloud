package cc.sika.common.swagger.web;

import org.springframework.web.bind.annotation.*;

/**
 * @author 吴畅
 * @创建时间 2022/12/14 - 13:11
 */
@RestController
@RequestMapping("/test")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "test get is success";
    }

    @GetMapping("/hello/{id}")
    public String hello(@PathVariable("id") int id) {
        return "test get is success and id is " + id;
    }

    @PostMapping("/add")
    public String add() {
        return "add post is success";
    }
}
