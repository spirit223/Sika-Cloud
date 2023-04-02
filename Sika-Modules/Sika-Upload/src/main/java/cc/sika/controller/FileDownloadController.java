package cc.sika.controller;

import cc.sika.api.web.BaseController;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author 吴畅
 * @创建时间 2023/2/3 - 19:41
 */
@RestController
@RequestMapping("/download")
public class FileDownloadController extends BaseController {
    @GetMapping
    public void downloadFile(String location, HttpServletResponse response) throws IOException {
        responseFile(new File(location), response, FilenameUtils.getName(location), "");
    }
}
