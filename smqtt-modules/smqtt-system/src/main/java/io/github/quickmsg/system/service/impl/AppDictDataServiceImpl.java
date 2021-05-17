package io.github.quickmsg.system.service.impl;

import io.github.quickmsg.system.domain.AppDictData;
import io.github.quickmsg.system.mapper.AppDictDataMapper;
import io.github.quickmsg.system.service.IAppDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AppDictDataService业务层处理
 *
 * @Author 冯可洋
 * @date 2021-03-12
 */
@Service
public class AppDictDataServiceImpl implements IAppDictDataService
{
    @Autowired
    private AppDictDataMapper appDictDataMapper;

    /**
     * 查询AppDictData
     *
     * @param id AppDictDataID
     * @return AppDictData
     */
    @Override
    public AppDictData selectAppDictDataById(Long id)
    {
        return appDictDataMapper.selectAppDictDataById(id);
    }

    /**
     * 查询AppDictData列表
     *
     * @param appDictData AppDictData
     * @return AppDictData
     */
    @Override
    public List<AppDictData> selectAppDictDataList(AppDictData appDictData)
    {
        return appDictDataMapper.selectAppDictDataList(appDictData);
    }

    /**
     * 新增AppDictData
     *
     * @param appDictData AppDictData
     * @return 结果
     */
    @Override
    public int insertAppDictData(AppDictData appDictData)
    {
        return appDictDataMapper.insertAppDictData(appDictData);
    }

    /**
     * 修改AppDictData
     *
     * @param appDictData AppDictData
     * @return 结果
     */
    @Override
    public int updateAppDictData(AppDictData appDictData)
    {
        return appDictDataMapper.updateAppDictData(appDictData);
    }

    /**
     * 批量删除AppDictData
     *
     * @param ids 需要删除的AppDictDataID
     * @return 结果
     */
    @Override
    public int deleteAppDictDataByIds(Long[] ids)
    {
        return appDictDataMapper.deleteAppDictDataByIds(ids);
    }

    /**
     * 删除AppDictData信息
     *
     * @param id AppDictDataID
     * @return 结果
     */
    @Override
    public int deleteAppDictDataById(Long id)
    {
        return appDictDataMapper.deleteAppDictDataById(id);
    }

    /**
     * 根据字典类型和状态查询数据
     *
     * @param status
     * @return
     */
    @Override
    public List<AppDictData> selectListByTypeAndStatus(String dictType, String status) {
        return appDictDataMapper.selectListByTypeAndStatus(dictType, status);
    }

    /**
     * 根据条件查询单条数据
     *
     * @param dictData
     * @return
     */
    @Override
    public AppDictData selectOneByAppDictData(AppDictData dictData) {
        return appDictDataMapper.selectOneByAppDictData(dictData);
    }
}
