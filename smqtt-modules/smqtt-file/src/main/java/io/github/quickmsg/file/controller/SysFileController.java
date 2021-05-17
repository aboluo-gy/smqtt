package io.github.quickmsg.file.controller;

import com.smqtt.common.core.domain.R;
import com.smqtt.common.core.utils.file.FileUtils;
import com.smqtt.file.api.domain.SysFile;
import io.github.quickmsg.file.service.ISysFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件请求处理
 *
 * @Author smqtt
 */
@RestController
public class SysFileController {

    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Resource(name = "minioSysFileServiceImpl")
    private ISysFileService minioSysFileService;

    @Resource(name = "fastDfsSysFileServiceImpl")
    private ISysFileService fastDfsSysFileService;



    /**
     * 文件上传请求
     */
    @PostMapping("/minioUpload")
    public R<SysFile> upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = minioSysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 文件上传请求
     */
    @PostMapping("/fastDfsUpload")
    public R<SysFile> fastDfsUpload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = fastDfsSysFileService.uploadFile(file);
            SysFile sysFile = new SysFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return R.ok(sysFile);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return R.fail(e.getMessage());
        }
    }

}
