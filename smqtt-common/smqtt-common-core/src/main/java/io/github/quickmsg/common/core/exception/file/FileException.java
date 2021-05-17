package io.github.quickmsg.common.core.exception.file;

import io.github.quickmsg.common.core.exception.BaseException;

/**
 * 文件信息异常类
 *
 * @Author smqtt
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
