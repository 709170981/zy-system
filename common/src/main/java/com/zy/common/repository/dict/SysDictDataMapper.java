package com.zy.common.repository.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.common.entity.dict.SysDictData;

import java.util.List;

/**
 * 系统字典值mapper接口
 */
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    /**
     * 通过字典类型code获取字典编码值列表
     *
     * @param dictTypeCodes 字典类型编码集合
     * @return 字典编码值列表
     */
    List<String> getDictCodesByDictTypeCode(String[] dictTypeCodes);

}
