package io.github.quickmsg.system.api;

import com.smqtt.common.core.constant.ServiceNameConstants;
import com.smqtt.common.core.domain.R;
import io.github.quickmsg.system.api.factory.RemoteDictDataFallbackFactory;
import io.github.quickmsg.system.api.model.AppDictDataVo;
import io.github.quickmsg.system.api.model.DictData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典服务
 *
 * @Author 冯可洋
 */
@FeignClient(contextId = "remoteDictDataService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteDictDataFallbackFactory.class)
public interface RemoteDictDataService {
    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 类型
     * @return 结果
     */
    @GetMapping(value = "/dict/data/getDictType/{dictType}")
    public R<List<DictData>> getDictType(@PathVariable("dictType") String dictType);

    /**
     * 根据字典类型与状态查询字详细数据
     *
     * @return 结果
     */
    @GetMapping("/SysAppDictData/getAppDictDataList")
    public R<List<AppDictDataVo>> getAppDictDataList(@RequestParam("dictType") String dictType, @RequestParam("status") String status);

    /**
     * 根据条件查询单条AppDictData数据
     * @param appDictDataVo
     * @return
     */
    @PostMapping("/SysAppDictData/getAppDictDataByAppDictData")
    public R<AppDictDataVo> getAppDictDataByAppDictData(@RequestBody AppDictDataVo appDictDataVo);

}
