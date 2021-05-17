package io.github.quickmsg.system.service;

import io.github.quickmsg.system.domain.AppDictData;

import java.util.List;

/**
 * AppDictDataService接口
 *
 * @Author 冯可洋
 * @date 2021-03-12
 */
public interface IAppDictDataService
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
     * 批量删除AppDictData
     *
     * @param ids 需要删除的AppDictDataID
     * @return 结果
     */
    public int deleteAppDictDataByIds(Long[] ids);

    /**
     * 删除AppDictData信息
     *
     * @param id AppDictDataID
     * @return 结果
     */
    public int deleteAppDictDataById(Long id);

    /**
     * 根据字典类型和状态查询数据
     * @param dictType
     * @return
     */
    List<AppDictData> selectListByTypeAndStatus(String dictType, String status);

    /**
     * 根据条件查询单条数据
     * @param dictData
     * @return
     */
    AppDictData selectOneByAppDictData(AppDictData dictData);
}
