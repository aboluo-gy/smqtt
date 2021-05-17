package io.github.quickmsg.system.controller;

import com.smqtt.common.core.domain.R;
import com.smqtt.common.core.utils.SecurityUtils;
import com.smqtt.common.core.utils.StringUtils;
import com.smqtt.common.core.utils.bean.BeanUtils;
import com.smqtt.common.core.utils.poi.ExcelUtil;
import com.smqtt.common.core.web.controller.BaseController;
import com.smqtt.common.core.web.domain.AjaxResult;
import com.smqtt.common.core.web.page.TableDataInfo;
import com.smqtt.common.log.annotation.Log;
import com.smqtt.common.log.enums.BusinessType;
import com.smqtt.common.security.annotation.PreAuthorize;
import com.smqtt.system.api.model.DictData;
import io.github.quickmsg.system.domain.SysDictData;
import io.github.quickmsg.system.service.ISysDictDataService;
import io.github.quickmsg.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 *
 * @Author smqtt
 */
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @PreAuthorize(hasPermi = "system:dict:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:dict:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictData dictData) throws IOException
    {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        util.exportExcel(response, list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
    @PreAuthorize(hasPermi = "system:dict:query")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode)
    {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<SysDictData>();
        }
        return AjaxResult.success(data);
    }

    /**
     * 服务调用-根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/getDictType/{dictType}")
    public R<List<DictData>> getDictType(@PathVariable String dictType) {
        List<SysDictData> sysDictDataList = dictTypeService.selectDictDataByType(dictType);
        List<DictData> dataList = new ArrayList<>();
        if (!sysDictDataList.isEmpty()){
            DictData data;
            for (SysDictData sysDictData : sysDictDataList) {
                data = new DictData();
                BeanUtils.copyBeanProp(data, sysDictData);
                dataList.add(data);
            }
        }
        return R.ok(dataList);
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:add")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDictData dict)
    {
        dict.setCreateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:edit")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDictData dict)
    {
        dict.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize(hasPermi = "system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes)
    {
        return toAjax(dictDataService.deleteDictDataByIds(dictCodes));
    }
}
