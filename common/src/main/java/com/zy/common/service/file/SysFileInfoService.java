package com.zy.common.service.file;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.common.entity.file.SysFileInfo;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.file.SysFileInfoParam;
import com.zy.common.result.file.SysFileInfoResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件信息表 服务类
 */
public interface SysFileInfoService extends IService<SysFileInfo> {

    /**
     * 分页查询文件信息表
     *
     * @param sysFileInfoParam 查询参数
     * @return 查询分页结果
     */
    PageResult<SysFileInfo> page(SysFileInfoParam sysFileInfoParam);

    /**
     * 查询所有文件信息表
     *
     * @param sysFileInfoParam 查询参数
     * @return 文件信息列表
     */
    List<SysFileInfo> list(SysFileInfoParam sysFileInfoParam);

    /**
     * 添加文件信息表
     *
     * @param sysFileInfoParam 添加参数
     */
    void add(SysFileInfoParam sysFileInfoParam);

    /**
     * 删除文件信息表
     *
     * @param sysFileInfoParam 删除参数
     */
    void delete(SysFileInfoParam sysFileInfoParam);

    /**
     * 编辑文件信息表
     *
     * @param sysFileInfoParam 编辑参数
     */
    void edit(SysFileInfoParam sysFileInfoParam);

    /**
     * 查看详情文件信息表
     *
     * @param sysFileInfoParam 查看参数
     * @return 文件信息
     */
    SysFileInfo detail(SysFileInfoParam sysFileInfoParam);

    /**
     * 上传文件，返回文件的唯一标识
     *
     * @param file 要上传的文件
     * @return 文件id
     */
    Long uploadFile(MultipartFile file);

    /**
     * 获取文件信息结果集
     *
     * @param fileId 文件id
     * @return 文件信息结果集
     */
    SysFileInfoResult getFileInfoResult(Long fileId);

    /**
     * 判断文件是否存在
     *
     * @param field 文件id
     */
    void assertFile(Long field);

    /**
     * 文件预览
     *
     * @param sysFileInfoParam 文件预览参数
     * @param response         响应结果
     */
    void preview(SysFileInfoParam sysFileInfoParam, HttpServletResponse response);

    /**
     * 文件下载
     *
     * @param sysFileInfoParam 文件下载参数
     * @param response         响应结果
     */
    void download(SysFileInfoParam sysFileInfoParam, HttpServletResponse response);
}
