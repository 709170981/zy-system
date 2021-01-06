package com.zy.common.service.dict;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.dict.SysDictData;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.dict.SysDictDataParam;

import java.util.List;

/**
 * 系统字典值service接口
 */
public interface SysDictDataService extends IService<SysDictData> {

    /**
     * 查询系统字典值
     *
     * @param sysDictDataParam 查询参数
     * @return 查询分页结果
     */
    PageResult<SysDictData> page(SysDictDataParam sysDictDataParam);

    /**
     * 系统字典值列表
     *
     * @param sysDictDataParam 查询参数
     * @return 系统字典值列表
     */
    List<SysDictData> list(SysDictDataParam sysDictDataParam);

    /**
     * 添加系统字典值
     *
     * @param sysDictDataParam 添加参数
     */
    void add(SysDictDataParam sysDictDataParam);

    /**
     * 删除系统字典值
     *
     * @param sysDictDataParam 删除参数
     */
    void delete(SysDictDataParam sysDictDataParam);

    /**
     * 编辑系统字典值
     *
     * @param sysDictDataParam 编辑参数
     */
    void edit(SysDictDataParam sysDictDataParam);

    /**
     * 查看系统字典值
     *
     * @param sysDictDataParam 查看参数
     * @return 系统字典值
     */
    SysDictData detail(SysDictDataParam sysDictDataParam);

    /**
     * 根据typeId下拉
     *
     * @param dictTypeId 字典类型id
     * @return 增强版hashMap，格式：[{"code:":"1", "value":"正常"}]
     */
    List<Dict> getDictDataListByDictTypeId(Long dictTypeId);

    /**
     * 根据typeId删除
     *
     * @param dictTypeId 字典类型id
     */
    void deleteByTypeId(Long dictTypeId);

    /**
     * 修改状态
     *
     * @param sysDictDataParam 修改参数
     */
    void changeStatus(SysDictDataParam sysDictDataParam);

    /**
     * 根据字典类型获取字典的code值
     *
     * @param dictTypeCodes 字典类型编码集合
     * @return 字典编码值列表
     */
    List<String> getDictCodesByDictTypeCode(String... dictTypeCodes);
}
