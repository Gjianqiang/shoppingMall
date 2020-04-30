package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private UploadProperties prop;
   // private static final List<String> ALLOW_TYPES= Arrays.asList("image/jpeg","image/png","image/bmp");


    public String uploadImage(MultipartFile file) {
        try{
            //校验文件类型
            String contentType = file.getContentType();
            if(!prop.getAllowTypes().contains(contentType)) {
                throw new LyException(ExceptionEnums.INVALID_FILE_TYPE);
                //检验文件内容
            }
            BufferedImage image = ImageIO.read(file.getInputStream());//如果不是图片,则返回空或异常
            if(image==null){
                throw new LyException(ExceptionEnums.INVALID_FILE_TYPE);
            }
            /*//准备路径
            File dest =new File("F:\\BaiduYunDownload\\微服务黑马电商\\资料\\乐优商城《项目笔记》\\upload\\",file.getOriginalFilename());
            //保存文件到本地
            file.transferTo(dest);*/
            //上传到FastDFS
            //String extension=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            String extension= StringUtils.substringAfterLast(file.getOriginalFilename(),".");//获取后缀名
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            //返回路径
            return prop.getBaseUrl()+storePath.getFullPath();
        } catch (IOException e) {
            log.error("上传文件失败",e);
            throw new LyException(ExceptionEnums.UPLOAD_FILE_ERROR);
        }
    }
}
