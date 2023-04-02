package cc.sika.service.impl;

import cc.sika.config.StorageProperties;
import cc.sika.entity.GenericFileVO;
import cc.sika.exception.NoNameException;
import cc.sika.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * 文件存储和加载的默认实现, 启动时会首先读取一次文件系统中的文件信息放入内存中,此后保持静默, 不会主动刷新文件信息
 * </p>
 * <em>只有当上传文件时会触发文件信息刷新行为</em>
 * <p>
 * 文件保存位置通过项目配置文件 sika.storage.location 配置, 未配置时使用默认存储, 即 <em>D:/sika/file/general/</em>
 * </p>
 * <p>
 * 可以通过重写该类减少内存占用
 * </p>
 *
 * @author 吴畅
 * @创建时间 2023/2/2 - 20:17
 */
@Service
@Slf4j
public class DefaultStorageService implements StorageService {

    private final String location;
    private final List<GenericFileVO> files = new ArrayList<>();

    public DefaultStorageService(StorageProperties StorageProperties) {
        // 局部变量推迟初始化避免空指针
        String location1;
        location1 = StorageProperties.getLocation();
        if ("".equals(location1.trim())) {
            location1 = "D:/sika/file/general/";
        }
        this.location = location1;
    }

    /**
     * <p>
     * 如果未指定文件名将直接抛出异常, 信息到达该方法时未能获取到文件的拓展名和文件属性, 存在安全问题, 所以不进行存储
     * </p>
     * <em>
     * 该实现默认在同名文件后追加 <b>(1)</b>
     * </em>
     *
     * @param file MultipartFile 控制层接收从前端发送过来的文件信息 {@link MultipartFile}
     * @throws NoNameException 未指定文件名称或者是获取文件名称失败时抛出
     */
    @Override
    public void store(MultipartFile file) throws NoNameException, IOException {
        if (file.isEmpty() || "".equals(file.getOriginalFilename())) {
            throw new NoNameException();
        }
        String fileName = location + file.getOriginalFilename();
        while (new File(fileName).exists()) {
            int i = fileName.lastIndexOf(".");
            String extension = FilenameUtils.getExtension(fileName);
            fileName = fileName.substring(0, i) + "(1)." + extension;
        }
        // 确保目录已经存在
        FileUtils.createParentDirectories(new File(fileName));
        // 持久化
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                InputStream inputStream = file.getInputStream()
        ) {
            IOUtils.copy(inputStream, fos, 1024 * 4);
        } finally {
            loadAll();
        }
    }

    @Override
    public List<GenericFileVO> loadAll() throws IOException {
        // 清空已有信息
        deleteAll();
        Stream<Path> walk = Files.walk(Paths.get(location), FileVisitOption.FOLLOW_LINKS);
        walk.forEach(path -> {
            try {
                BasicFileAttributes fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
                files.add(new GenericFileVO(
                        path.getFileName().toString(),
                        path.toFile().getAbsolutePath().replaceAll("\\\\", "/"),
                        !fileAttributes.isDirectory(),
                        fileAttributes.size(),
                        fileAttributes.creationTime().toMillis(),
                        fileAttributes.lastModifiedTime().toMillis(),
                        fileAttributes.lastAccessTime().toMillis()
                ));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("文件列表信息刷新成功");
        walk.close();
        return files;
    }

    /**
     * 通过文件名加载文件系统中的文件信息.
     * 此方法依赖于系统启动的 loadAll 方法, 不会独立发起一次文件系统遍历操作,
     * 如果未能成功载入文件信息到内存中会则永远为null
     *
     * @param filename 文件名称
     * @return 文件信息对象 {@link GenericFileVO}, 如果不存在返回 null
     */
    @Override
    public GenericFileVO load(String filename) {
        for (GenericFileVO file : files) {
            if (filename.equals(file.getFilename())) {
                return file;
            }
        }
        return null;
    }

    @Override
    public void deleteAll() {
        files.clear();
    }
}
