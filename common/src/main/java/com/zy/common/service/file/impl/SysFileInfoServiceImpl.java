package com.zy.common.service.file.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.stylefeng.roses.file.FileOperator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.common.consts.MediaTypeConstant;
import com.zy.common.consts.SymbolConstant;
import com.zy.common.context.RequestNoContext;
import com.zy.common.entity.file.SysFileInfo;
import com.zy.common.enums.FileLocationEnum;
import com.zy.common.exception.ServiceException;
import com.zy.common.exception.enums.SysFileInfoExceptionEnum;
import com.zy.common.factory.PageFactory;
import com.zy.common.models.page.PageResult;
import com.zy.common.param.file.SysFileInfoParam;
import com.zy.common.repository.file.SysFileInfoMapper;
import com.zy.common.result.file.SysFileInfoResult;
import com.zy.common.service.file.SysFileInfoService;
import com.zy.common.utils.DownloadUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import static com.zy.common.config.FileConfig.DEFAULT_BUCKET;

/**
 * 文件信息表 服务实现类
 *
 * @author stylefeng
 * @date 2020/6/7 22:15
 */
@Service
public class SysFileInfoServiceImpl extends ServiceImpl<SysFileInfoMapper, SysFileInfo> implements SysFileInfoService {

    private static final Log log = Log.get();

    @Resource
    private FileOperator fileOperator;

    @Override
    public PageResult<SysFileInfo> page(SysFileInfoParam sysFileInfoParam) {

        // 构造条件
        LambdaQueryWrapper<SysFileInfo> queryWrapper = new LambdaQueryWrapper<>();

        // 拼接查询条件-文件存储位置（1:阿里云，2:腾讯云，3:minio，4:本地）
        if (ObjectUtil.isNotNull(sysFileInfoParam)) {
            if (ObjectUtil.isNotEmpty(sysFileInfoParam.getFileLocation())) {
                queryWrapper.like(SysFileInfo::getFileLocation, sysFileInfoParam.getFileLocation());
            }

            // 拼接查询条件-文件仓库
            if (ObjectUtil.isNotEmpty(sysFileInfoParam.getFileBucket())) {
                queryWrapper.like(SysFileInfo::getFileBucket, sysFileInfoParam.getFileBucket());
            }

            // 拼接查询条件-文件名称（上传时候的文件名）
            if (ObjectUtil.isNotEmpty(sysFileInfoParam.getFileOriginName())) {
                queryWrapper.like(SysFileInfo::getFileOriginName, sysFileInfoParam.getFileOriginName());
            }
        }

        // 查询分页结果
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    @Override
    public List<SysFileInfo> list(SysFileInfoParam sysFileInfoParam) {

        // 构造条件
        LambdaQueryWrapper<SysFileInfo> queryWrapper = new LambdaQueryWrapper<>();

        return this.list(queryWrapper);
    }

    @Override
    public void add(SysFileInfoParam sysFileInfoParam) {

        // 将dto转为实体
        SysFileInfo sysFileInfo = new SysFileInfo();
        BeanUtil.copyProperties(sysFileInfoParam, sysFileInfo);

        this.save(sysFileInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(SysFileInfoParam sysFileInfoParam) {

        // 查询文件的信息
        SysFileInfo sysFileInfo = this.getById(sysFileInfoParam.getId());

        // 删除文件记录
        this.removeById(sysFileInfoParam.getId());

        // 删除具体文件
        this.fileOperator.deleteFile(sysFileInfo.getFileBucket(), sysFileInfo.getFileObjectName());
    }

    @Override
    public void edit(SysFileInfoParam sysFileInfoParam) {

        // 根据id查询实体
        SysFileInfo sysFileInfo = this.querySysFileInfo(sysFileInfoParam);

        // 请求参数转化为实体
        BeanUtil.copyProperties(sysFileInfoParam, sysFileInfo);

        this.updateById(sysFileInfo);
    }

    @Override
    public SysFileInfo detail(SysFileInfoParam sysFileInfoParam) {
        return this.querySysFileInfo(sysFileInfoParam);
    }

    @Override
    public Long uploadFile(MultipartFile file) {

        // 生成文件的唯一id
        Long fileId = IdWorker.getId();

        // 获取文件原始名称
        String originalFilename = file.getOriginalFilename();

        // 获取文件后缀
        String fileSuffix = null;

        if (ObjectUtil.isNotEmpty(originalFilename)) {
            fileSuffix = StrUtil.subAfter(originalFilename, SymbolConstant.PERIOD, true);
        }
        // 生成文件的最终名称
        String finalName = fileId + SymbolConstant.PERIOD + fileSuffix;

        // 存储文件
        byte[] bytes;
        try {
            bytes = file.getBytes();
            fileOperator.storageFile(DEFAULT_BUCKET, finalName, bytes);
        } catch (IOException e) {
            throw new ServiceException(SysFileInfoExceptionEnum.ERROR_FILE);
        }

        // 计算文件大小kb
        long fileSizeKb = Convert.toLong(NumberUtil.div(new BigDecimal(file.getSize()), BigDecimal.valueOf(1024))
                .setScale(0, BigDecimal.ROUND_HALF_UP));

        //计算文件大小信息
        String fileSizeInfo = FileUtil.readableFileSize(file.getSize());

        // 存储文件信息
        SysFileInfo sysFileInfo = new SysFileInfo();
        sysFileInfo.setId(fileId);
        sysFileInfo.setFileLocation(FileLocationEnum.LOCAL.getCode());
        sysFileInfo.setFileBucket(DEFAULT_BUCKET);
        sysFileInfo.setFileObjectName(finalName);
        sysFileInfo.setFileOriginName(originalFilename);
        sysFileInfo.setFileSuffix(fileSuffix);
        sysFileInfo.setFileSizeKb(fileSizeKb);
        sysFileInfo.setFileSizeInfo(fileSizeInfo);
        this.save(sysFileInfo);

        // 返回文件id
        return fileId;
    }

    @Override
    public SysFileInfoResult getFileInfoResult(Long fileId) {
        byte[] fileBytes;
        // 获取文件名
        SysFileInfo sysFileInfo = this.getById(fileId);
        if (sysFileInfo == null) {
            throw new ServiceException(SysFileInfoExceptionEnum.NOT_EXISTED_FILE);
        }
        try {
            // 返回文件字节码
            fileBytes = fileOperator.getFileBytes(DEFAULT_BUCKET, sysFileInfo.getFileObjectName());
        } catch (Exception e) {
            log.error(">>> 获取文件流异常，请求号为：{}，具体信息为：{}", RequestNoContext.get(), e.getMessage());
            throw new ServiceException(SysFileInfoExceptionEnum.FILE_STREAM_ERROR);
        }

        SysFileInfoResult sysFileInfoResult = new SysFileInfoResult();
        BeanUtil.copyProperties(sysFileInfo, sysFileInfoResult);
        sysFileInfoResult.setFileBytes(fileBytes);

        return sysFileInfoResult;
    }

    @Override
    public void assertFile(Long field) {
        SysFileInfo sysFileInfo = this.getById(field);
        if (ObjectUtil.isEmpty(sysFileInfo)) {
            throw new ServiceException(SysFileInfoExceptionEnum.NOT_EXISTED);
        }
    }

    @Override
    public void preview(SysFileInfoParam sysFileInfoParam, HttpServletResponse response) {

        byte[] fileBytes;
        //根据文件id获取文件信息结果集
        SysFileInfoResult sysFileInfoResult = this.getFileInfoResult(sysFileInfoParam.getId());
        //获取文件后缀
        String fileSuffix = sysFileInfoResult.getFileSuffix().toLowerCase();
        //获取文件字节码
        fileBytes = sysFileInfoResult.getFileBytes();
        //如果是图片类型，则直接输出
        if (this.isPic(fileSuffix)) {
            try {
                //设置contentType
                response.setContentType("image/jpeg");
                //获取outputStream
                ServletOutputStream outputStream = response.getOutputStream();
                //输出
                IoUtil.write(outputStream, true, fileBytes);
            } catch (IOException e) {
                throw new ServiceException(SysFileInfoExceptionEnum.PREVIEW_ERROR_NOT_SUPPORT);
            }

        } else {
            //否则不支持预览（暂时）
            throw new ServiceException(SysFileInfoExceptionEnum.PREVIEW_ERROR_NOT_SUPPORT);
        }
    }

    @Override
    public void download(SysFileInfoParam sysFileInfoParam, HttpServletResponse response) {
        // 获取文件信息结果集
        SysFileInfoResult sysFileInfoResult = this.getFileInfoResult(sysFileInfoParam.getId());
        String fileName = sysFileInfoResult.getFileOriginName();
        byte[] fileBytes = sysFileInfoResult.getFileBytes();
        DownloadUtil.download(fileName, fileBytes, response);
    }

    /**
     * 获取文件信息表
     */
    private SysFileInfo querySysFileInfo(SysFileInfoParam sysFileInfoParam) {
        SysFileInfo sysFileInfo = this.getById(sysFileInfoParam.getId());
        if (ObjectUtil.isEmpty(sysFileInfo)) {
            throw new ServiceException(SysFileInfoExceptionEnum.NOT_EXISTED);
        }
        return sysFileInfo;
    }

    /**
     * 根据文件后缀判断是否图片
     */
    public boolean isPic(String fileSuffix) {
        return MediaTypeConstant.IMG_JPG.equals(fileSuffix)
                || MediaTypeConstant.IMG_JPEG.equals(fileSuffix)
                || MediaTypeConstant.IMG_PNG.equals(fileSuffix)
                || MediaTypeConstant.IMG_GIF.equals(fileSuffix)
                || MediaTypeConstant.IMG_TIF.equals(fileSuffix)
                || MediaTypeConstant.IMG_BMP.equals(fileSuffix);
    }

}
