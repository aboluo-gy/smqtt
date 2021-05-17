package io.github.quickmsg.common.core.utils.poi;

import com.alibaba.fastjson.JSONObject;
import io.github.quickmsg.common.core.exception.CustomException;
import io.github.quickmsg.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName: ExportJsonUtil
 * @Description: 导出json文件
 * @Date: 2021/3/11 16:23
 * @Author: songjg
 */
@Slf4j
public class ExportJsonUtil {

    /**
     * @description: 导出json压缩文件
     * @param json
     * @param type  类型(不需要时可为空)：1物模型导出，2产品模型导出
     * @return: {@link }
     * @throws:
     * @Author: songjg
     * @datetime: 2021/3/11 16:25
     */
    public static void exportJsonZip(String json, HttpServletResponse response, Integer type){
        try {

            boolean flag = isJson(json);
            if (!flag) {
                log.info("json验证失败");
                throw new CustomException("json验证失败",500);
            }
            JSONObject parse = (JSONObject) JSONObject.parse(json);
            // 文件名称
            String templateName = "EXPORT_" + System.currentTimeMillis();
            OutputStream os = null;
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(templateName + ".zip", "UTF-8"));
            os = response.getOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(os);
            //重点开始，创建压缩文件
            String fileName = parse.getString("name");
            if (type == 2) {
                //产品模型名称
                fileName = parse.getString("manufacturerName");
            }
            ZipEntry zipEntry = new ZipEntry(fileName + ".json");
            zipOutputStream.putNextEntry(zipEntry);
            Writer write = new OutputStreamWriter(zipOutputStream);
            write.write(json);
            write.close();
        } catch (Exception e) {
            log.info("导出json压缩文件失败" + e.getMessage());
        }
    }

    /**
     * 校验json对象
     *
     * @param json
     * @return
     */
    public static boolean isJson(String json) {
        if(StringUtils.isEmpty(json)){
            return false;
        }
        boolean isJsonObject = true;
        try {
            JSONObject.parseObject(json);
        } catch (Exception e) {
            isJsonObject = false;
        }
        return isJsonObject;
    }
}
