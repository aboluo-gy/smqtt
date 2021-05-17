package io.github.quickmsg.common.core.web.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.quickmsg.common.core.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 *
 * @Author smqtt
 */
@Data
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 搜索值 */
    private String searchValue;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 请求参数 */
    private Map<String, Object> params;



    private String beginCreatedate;
    private String endCreatedate;

    private String beginUpdatedate;
    private String endUpdatedate;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        if (!StringUtils.isEmpty(beginCreatedate)){
            params.put("beginCreatedate",beginCreatedate);
            params.put("endCreatedate",endCreatedate);
        }
        if (!StringUtils.isEmpty(beginUpdatedate)){
            params.put("beginUpdatedate",beginUpdatedate);
            params.put("endUpdatedate",endUpdatedate);
        }
        return params;
    }

}
