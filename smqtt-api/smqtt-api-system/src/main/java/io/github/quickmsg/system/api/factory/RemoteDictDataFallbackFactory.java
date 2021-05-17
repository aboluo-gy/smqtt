package io.github.quickmsg.system.api.factory;

import com.smqtt.common.core.domain.R;
import feign.hystrix.FallbackFactory;
import io.github.quickmsg.system.api.RemoteDictDataService;
import io.github.quickmsg.system.api.model.AppDictDataVo;
import io.github.quickmsg.system.api.model.DictData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字典服务降级处理
 *
 * @Author 冯可洋
 */
@Component
public class RemoteDictDataFallbackFactory implements FallbackFactory<RemoteDictDataService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteDictDataFallbackFactory.class);

    @Override
    public RemoteDictDataService create(Throwable throwable) {
        log.error("字典服务调用失败:{}", throwable.getMessage());
        return new RemoteDictDataService() {
            @Override
            public R<List<DictData>> getDictType(String dictType) {
                return R.fail("获取字典数据失败:" + throwable.getMessage());
            }

            @Override
            public R<List<AppDictDataVo>> getAppDictDataList(String dictType, String status) {
                return R.fail("查询字典详细数据失败:" + throwable.getMessage());
            }

            @Override
            public R<AppDictDataVo> getAppDictDataByAppDictData(AppDictDataVo appDictDataVo) {
                return R.fail("查询字典详细数据失败:" + throwable.getMessage());
            }
        };
    }
}
