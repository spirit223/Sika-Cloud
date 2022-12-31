package cc.sika;

import cc.sika.entity.QuestionExcel;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author 吴畅
 * @创建时间 2022/12/30 - 20:35
 */
@SpringBootTest
@Slf4j
public class ExcelTest {

    @Resource
    private ObjectMapper JSON;

    @Test
    void testParsingExcel() throws IOException {
        String fileName = "D:\\develop\\Java\\graduation\\Sika-UI\\excel-data\\question.xlsx";
        EasyExcel.read(fileName, QuestionExcel.class, new PageReadListener<QuestionExcel>(dataList -> {
            for (QuestionExcel demoData : dataList) {
                try {
                    log.info("读取到一条数据{}", JSON.writeValueAsString(demoData));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        })).sheet().doRead();
    }
}
