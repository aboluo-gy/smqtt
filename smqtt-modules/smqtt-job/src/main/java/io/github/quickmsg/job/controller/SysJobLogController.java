package io.github.quickmsg.job.controller;

import com.smqtt.common.core.utils.poi.ExcelUtil;
import com.smqtt.common.core.web.controller.BaseController;
import com.smqtt.common.core.web.domain.AjaxResult;
import com.smqtt.common.core.web.page.TableDataInfo;
import com.smqtt.common.log.annotation.Log;
import com.smqtt.common.log.enums.BusinessType;
import com.smqtt.common.security.annotation.PreAuthorize;
import io.github.quickmsg.job.domain.SysJobLog;
import io.github.quickmsg.job.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 调度日志操作处理
 *
 * @Author smqtt
 */
@RestController
@RequestMapping("/job/log")
public class SysJobLogController extends BaseController
{
    @Autowired
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @PreAuthorize(hasPermi = "monitor:job:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJobLog sysJobLog)
    {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        return getDataTable(list);
    }

    /**
     * 导出定时任务调度日志列表
     */
    @PreAuthorize(hasPermi = "monitor:job:export")
    @Log(title = "任务调度日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJobLog sysJobLog) throws IOException
    {
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
        util.exportExcel(response, list, "调度日志");
    }

    /**
     * 根据调度编号获取详细信息
     */
    @PreAuthorize(hasPermi = "monitor:job:query")
    @GetMapping(value = "/{configId}")
    public AjaxResult getInfo(@PathVariable Long jobLogId)
    {
        return AjaxResult.success(jobLogService.selectJobLogById(jobLogId));
    }

    /**
     * 删除定时任务调度日志
     */
    @PreAuthorize(hasPermi = "monitor:job:remove")
    @Log(title = "定时任务调度日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobLogIds}")
    public AjaxResult remove(@PathVariable Long[] jobLogIds)
    {
        return toAjax(jobLogService.deleteJobLogByIds(jobLogIds));
    }

    /**
     * 清空定时任务调度日志
     */
    @PreAuthorize(hasPermi = "monitor:job:remove")
    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return AjaxResult.success();
    }
}
