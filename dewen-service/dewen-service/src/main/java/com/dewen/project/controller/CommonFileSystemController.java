package com.dewen.project.controller;

import com.dewen.project.domain.CommonFileSystem;
import com.dewen.project.exception.CommonException;
import com.dewen.project.service.impl.CommonFileSystemService;
import com.dewen.project.utils.BaseResponse;
import com.dewen.project.utils.IBaseManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/project/file")
public class CommonFileSystemController {

    @Resource
    private CommonFileSystemService commonFileService;
    @Resource
    private IBaseManager baseManager;

    /**
     * 上传附件
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public BaseResponse upload(HttpServletRequest request) {
        BaseResponse model = new BaseResponse();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request
                .getSession().getServletContext());
        boolean isMultipart = multipartResolver.isMultipart(request);
        try {
            if (isMultipart) {
                MultipartHttpServletRequest multiRequest;
                //转换成多媒体request
                if (request instanceof StandardMultipartHttpServletRequest) {
                    multiRequest = (MultipartHttpServletRequest) request;
                } else {
                    multiRequest = multipartResolver.resolveMultipart(request);
                }
//                String workflowIdStr = multiRequest.getParameter("workflowId");
//                if (StringUtils.isEmpty(workflowIdStr)) {
//                    throw new CommonException("{commonFile.workflowId.not.null}");
//                }
                //Integer workflowId = Integer.parseInt(workflowIdStr);

                List<CommonFileSystem> files = commonFileService.upload(multiRequest);
                model = baseManager.composeSuccessBaseResponse(files);
            } else {
                throw new CommonException("表单不是multipart/form-data类型");//"{commonFile.workflowId.not.null}");
            }
        } catch (Exception e) {
            log.error("", e);
            throw new CommonException(e.getMessage());
        }
        return model;
    }

    /**
     * 根据附件id下载附件
     *
     * @param request
     * @param response
     * @param flieId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download/{flieId}")
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, @PathVariable("flieId") Integer flieId) throws IOException {
        if (flieId == null) {
            String msg = "id不能为空";
            response.getOutputStream().write(msg.getBytes());
            return null;
        }
        return commonFileService.download(request, response, flieId);
    }

    /**
     * 下载
     *
     * @param request
     * @param response
     * @param flieId   经过base64加密的ID
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download/wechat/{flieId}")
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, @PathVariable("flieId") String flieId) throws IOException {
        Integer id = Integer.valueOf(new String(Base64Utils.decodeFromString(new String(Base64Utils.decodeFromString(flieId)))));
        return download(request, response, id);
    }

    /**
     * 删除附件
     *
     * @throws IOException
     */
    @RequestMapping(value = "/delete/{fileId}")
    @ResponseBody
    public BaseResponse delete(@PathVariable("fileId") Integer fileId) {
        BaseResponse model = new BaseResponse();
        try {
            commonFileService.delete(fileId);
            model.setResultCode("0");
        } catch (Exception e) {
            log.error("", e);
            throw new CommonException(e.getMessage());
        }
        return model;
    }

}