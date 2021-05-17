package io.github.quickmsg.job.util;

import io.github.quickmsg.job.domain.SysJob;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 *
 * @Author smqtt
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
