package io.github.quickmsg.system.controller;

import cn.hutool.core.bean.BeanUtil;
import com.smqtt.common.core.domain.R;
import com.smqtt.common.core.utils.bean.BeanUtils;
import com.smqtt.common.core.utils.poi.ExcelUtil;
import com.smqtt.common.core.web.controller.BaseController;
import com.smqtt.common.core.web.domain.AjaxResult;
import com.smqtt.common.core.web.page.TableDataInfo;
import com.smqtt.common.log.annotation.Log;
import com.smqtt.common.log.enums.BusinessType;
import com.smqtt.common.security.annotation.PreAuthorize;
import com.smqtt.system.api.model.AppDictDataVo;
import io.github.quickmsg.system.domain.AppDictData;
import io.github.quickmsg.system.service.IAppDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * AppDictDataController
 *
 * @Author 冯可洋
 * @date 2021-03-12
 */
@RestController
@RequestMapping("/SysAppDictData")
public class AppDictDataController extends BaseController {
    @Autowired
    private IAppDictDataService appDictDataService;

    /**
     * 查询AppDictData列表
     */
    @PreAuthorize(hasPermi = "system:SysAppDictData:list")
    @GetMapping("/list")
    public TableDataInfo list(AppDictData appDictData) {
        startPage();
        List<AppDictData> list = appDictDataService.selectAppDictDataList(appDictData);
        return getDataTable(list);
    }

    /**
     * 导出AppDictData列表
     */
    @PreAuthorize(hasPermi = "system:SysAppDictData:export")
    @Log(title = "AppDictData", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppDictData appDictData) throws IOException {
        List<AppDictData> list = appDictDataService.selectAppDictDataList(appDictData);
        ExcelUtil<AppDictData> util = new ExcelUtil<AppDictData>(AppDictData.class);
        util.exportExcel(response, list, "SysAppDictData");
    }

    /**
     * 获取AppDictData详细信息
     */
    @PreAuthorize(hasPermi = "system:SysAppDictData:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(appDictDataService.selectAppDictDataById(id));
    }

    /**
     * 新增AppDictData
     */
    @PreAuthorize(hasPermi = "system:SysAppDictData:add")
    @Log(title = "AppDictData", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppDictData appDictData) {
        return toAjax(appDictDataService.insertAppDictData(appDictData));
    }

    /**
     * 修改AppDictData
     */
    @PreAuthorize(hasPermi = "system:SysAppDictData:edit")
    @Log(title = "AppDictData", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppDictData appDictData) {
        return toAjax(appDictDataService.updateAppDictData(appDictData));
    }

    /**
     * 删除AppDictData
     */
    @PreAuthorize(hasPermi = "system:SysAppDictData:remove")
    @Log(title = "AppDictData", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(appDictDataService.deleteAppDictDataByIds(ids));
    }


    /**
     * 根据字典类型查询字典数据信息
     * @return
     */
    @GetMapping("/getAppDictDataList")
    public R<List<AppDictDataVo>> getAppDictDataList(@RequestParam("dictType") String dictType, @RequestParam("status") String status) {
        List<AppDictData> list = appDictDataService.selectListByTypeAndStatus(dictType, status);
        List<AppDictDataVo> voList = new ArrayList<>();
        if (!list.isEmpty()) {
            AppDictDataVo data;
            for (AppDictData dictData : list) {
                data = new AppDictDataVo();
                BeanUtil.copyProperties(dictData, data);
                voList.add(data);
            }
        }
        return R.ok(voList);
    }

    @PostMapping("/getAppDictDataByAppDictData")
    public R<AppDictDataVo> getAppDictDataByAppDictData(@RequestBody AppDictDataVo appDictDataVo) {
        AppDictData dictData = new AppDictData();
        BeanUtils.copyBeanProp(dictData, appDictDataVo);
        AppDictData data = appDictDataService.selectOneByAppDictData(dictData);
        AppDictDataVo resultData = new AppDictDataVo();
        BeanUtils.copyBeanProp(resultData, data);
        return R.ok(resultData);
    }
}
