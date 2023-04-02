package cc.sika.service;

import cc.sika.entity.GenericFileVO;
import cc.sika.exception.NoNameException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * 文件存储和加载接口
 *
 * @author 吴畅
 * @创建时间 2023/2/2 - 20:13
 */
public interface StorageService {

    /**
     * <p>
     * 文件上传系统开启初始化方法, 可以用于处理文件系统信息或者进行文件清空
     * </p>
     * <p>
     * 默认实现采用加载所有文件, 将文件信息封装成 VO 对象放入堆中, 见 {@link GenericFileVO}
     * </p>
     */
    default void init() throws IOException {
        loadAll();
    }

    /**
     * <p>
     * 文件存储逻辑实现, 定义从前端接收的文件将以何种形式和方式被存储
     * </p>
     * <p>
     * 对文件名的确定以及文件名称重复的处理应当在该业务中进行
     * </p>
     *
     * @param file MultipartFile 控制层接收从前端发送过来的文件信息 {@link MultipartFile}
     */
    void store(MultipartFile file) throws NoNameException, IOException;

    /**
     * <p>
     * 加载所有文件信息, 如果启动时 init() 方法不对文件进行清空, 可以从该方法获取所有文件信息
     * </p>
     *
     * @return 多个包含文件信息的列表, 见 {@link cc.sika.entity.GenericFileVO}
     */
    List<GenericFileVO> loadAll() throws IOException;

    /**
     * <p>
     * 根据文件名称在文件系统中加载文件路径信息
     * </p>
     *
     * @param filename 文件名称
     * @return 文件路径的 {@link Path} 对象
     */
    GenericFileVO load(String filename);

    /**
     * 通过文件名将目标文件加载成 {@link Resource} 对象
     *
     * @param filename 文件名称
     * @return 目标文件对应的 {@link Resource} 对象, 不存在返回 null
     */
    default Resource loadAsResource(String filename) {
        return null;
    }

    void deleteAll();
}
