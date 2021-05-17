package io.github.quickmsg.system.mapper;

import io.github.quickmsg.system.domain.AppDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AppDictDataMapper接口
 *
 * @Author 冯可洋
 * @date 2021-03-12
 */
public interface AppDictDataMapper
{
    /**
     * 查询AppDictData
     *
     * @param id AppDictDataID
     * @return AppDictData
     */
    public AppDictData selectAppDictDataById(Long id);

    /**
     * 查询AppDictData列表
     *
     * @param appDictData AppDictData
     * @return AppDictData集合
     */
    public List<AppDictData> selectAppDictDataList(AppDictData appDictData);

    /**
     * 新增AppDictData
     *
     * @param appDictData AppDictData
     * @return 结果
     */
    public int insertAppDictData(AppDictData appDictData);

    /**
     * 修改AppDictData
     *
     * @param appDictData AppDictData
     * @return 结果
     */
    public int updateAppDictData(AppDictData appDictData);

    /**
     * 删除AppDictData
     *
     * @param id AppDictDataID
     * @return 结果
     */
    public int deleteAppDictDataById(Long id);

    /**
     * 批量删除AppDictData
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppDictDataByIds(Long[] ids);

    /**
     * 根据字典类型和状态查询数据
     * @param dictType
     * @param status
     * @return
     */
    List<AppDictData> selectListByTypeAndStatus(@Param("dictType") String dictType, @Param("status") String status);

    AppDictData selectOneByAppDictData(AppDictData dictData);
}
