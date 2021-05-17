package io.github.quickmsg.system.domain;

import com.smqtt.common.core.annotation.Excel;
import com.smqtt.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AppDictData对象 app_dict_data
 *
 * @Author 冯可洋
 * @date 2021-03-12
 */
public class AppDictData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 字典标签 */
    @Excel(name = "字典标签")
    private String dictLabel;

    /** 字典键值 */
    @Excel(name = "字典键值")
    private String dictValue;

    /** 字典类型 */
    @Excel(name = "字典类型")
    private String dictType;

    /** toptic 前缀 */
    @Excel(name = "toptic 前缀")
    private String prefix;

    /** toptic 后缀 */
    @Excel(name = "toptic 后缀")
    private String suffix;

    /** 状态，正常，停用 */
    @Excel(name = "状态，正常，停用")
    private String status;

    /** 创建者 */
    @Excel(name = "创建者")
    private String account;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createDate;

    /** 区分type一样的 */
    @Excel(name = "区分type一样的")
    private Long flag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setDictLabel(String dictLabel)
    {
        this.dictLabel = dictLabel;
    }

    public String getDictLabel()
    {
        return dictLabel;
    }
    public void setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
    }

    public String getDictValue()
    {
        return dictValue;
    }
    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getDictType()
    {
        return dictType;
    }
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public String getPrefix()
    {
        return prefix;
    }
    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }

    public String getSuffix()
    {
        return suffix;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getAccount()
    {
        return account;
    }
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getCreateDate()
    {
        return createDate;
    }
    public void setFlag(Long flag)
    {
        this.flag = flag;
    }

    public Long getFlag()
    {
        return flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dictLabel", getDictLabel())
            .append("dictValue", getDictValue())
            .append("dictType", getDictType())
            .append("prefix", getPrefix())
            .append("suffix", getSuffix())
            .append("status", getStatus())
            .append("account", getAccount())
            .append("createDate", getCreateDate())
            .append("flag", getFlag())
            .append("remark", getRemark())
            .toString();
    }
}
