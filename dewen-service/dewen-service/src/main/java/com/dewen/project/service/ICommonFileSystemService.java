package com.dewen.project.service;

import com.dewen.project.domain.CommonFileSystem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface ICommonFileSystemService {

    List<CommonFileSystem> upload(MultipartHttpServletRequest request, String workflowId);

    CommonFileSystem upload(InputStream inputStream, String fileName);

    ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, Integer fileId) throws IOException;

    List<CommonFileSystem> queryFileByWorkflowId(Integer workflowId);

    int delete(Integer fileId);

}
