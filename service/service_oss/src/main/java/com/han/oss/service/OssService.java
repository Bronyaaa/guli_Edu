package com.han.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Han
 * @create 2020-09-11-3:55 下午
 */
@Service
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
