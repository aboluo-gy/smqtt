package io.github.quickmsg.file.api;


import com.smqtt.common.core.constant.ServiceNameConstants;
import com.smqtt.common.core.domain.R;
import io.github.quickmsg.file.api.domain.SysFile;
import io.github.quickmsg.file.api.factory.RemoteFileFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 *
 * @Author smqtt
 */
@FeignClient(contextId = "remoteFileService", value = ServiceNameConstants.FILE_SERVICE, fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {
    /**
     * minio上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/minioUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<SysFile> minioUpload(@RequestPart(value = "file") MultipartFile file);

    /**
     * fastDfs上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    @PostMapping(value = "/fastDfsUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<SysFile> fastDfsUpload(@RequestPart(value = "file") MultipartFile file);
}
