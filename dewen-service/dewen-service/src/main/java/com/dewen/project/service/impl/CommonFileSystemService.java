package com.dewen.project.service.impl;

import com.dewen.project.constants.Constants;
import com.dewen.project.constants.FileParamConstant;
import com.dewen.project.domain.CommonFileSystem;
import com.dewen.project.exception.CommonException;
import com.dewen.project.exception.DataException;
import com.dewen.project.repository.CommonFileSystemRepository;
import com.dewen.project.service.ICommonFileSystemService;
import com.dewen.project.utils.PageUtils;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


/**
 * <p>Title:</p>
 * <p>Description: CommonFileSystem ServiceImpl  文件系统表</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author shenjl
 * @version 1.0
 * @date 2019-07-01
 */
@Service
public class CommonFileSystemService implements ICommonFileSystemService {

    public static final Logger log = LoggerFactory.getLogger(CommonFileSystemService.class);

    private String activeProfile;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private FileParamConstant fileParamConstant;

    @Value("${fileDir.workflow}")
    public String fileDir;

    @Value(value = "${file.upload.types:pdf,xls,xlsx,doc,docx,ppt,pptx,jpg,png,gif}")
    private String fileTypes;

    @Autowired
    private CommonFileSystemRepository commonFileSystemRepository;

    @PostConstruct
    public void getActiveProfiles() {
        activeProfile = applicationContext.getEnvironment().getActiveProfiles()[0];
    }

    //    @Override
    @Transactional
    public int createCommonFileSystem(CommonFileSystem commonFileSystem) {
        queryFk(commonFileSystem);
        commonFileSystemRepository.save(commonFileSystem);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    //    @Override
    @Transactional
    public int updateCommonFileSystem(CommonFileSystem commonFileSystem, Integer id) {
        queryFk(commonFileSystem);
        Optional<CommonFileSystem> commonFileSystemRes = commonFileSystemRepository.findById(id);
        if (commonFileSystemRes.isPresent()) {
            commonFileSystem.setId(id);
            commonFileSystemRepository.save(commonFileSystem);
            return Constants.RETURN_STATUS_SUCCESS;
        } else {
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     *
     * @param commonFileSystem
     */
    private void queryFk(CommonFileSystem commonFileSystem) {

    }


    //    @Override
    @Transactional
    public int deleteCommonFileSystem(Integer id) {
        Optional<CommonFileSystem> commonFileSystem = commonFileSystemRepository.findById(id);
        if (commonFileSystem.isPresent()) {
            commonFileSystemRepository.deleteById(id);
            Optional<CommonFileSystem> commonFileSystemRes = commonFileSystemRepository.findById(id);
            if (commonFileSystemRes.isPresent()) {
                return Constants.RETURN_STATUS_FAIL;
            } else {
                return Constants.RETURN_STATUS_SUCCESS;
            }
        } else {
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    //    @Override
    public CommonFileSystem findById(Integer id) {
        Optional<CommonFileSystem> commonFileSystem = commonFileSystemRepository.findById(id);
        if (commonFileSystem.isPresent()) {
            return commonFileSystem.get();
        } else {
            return null;
        }

        //return commonFileSystemRepository.findOne(id);
    }

    //    @Override
    public Page<CommonFileSystem> list(CommonFileSystem commonFileSystem, int pageNumber, int pageSize, String sorts) {

        //add sorts to query
        Page<CommonFileSystem> commonFileSystemPages = null;
        //Pageable
        Pageable pageable = PageUtils.pageable(pageNumber, pageSize, sorts);

        if (commonFileSystem == null) {
            commonFileSystemPages = commonFileSystemRepository.findAll(pageable);
        } else {
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            //create instant
            Example<CommonFileSystem> example = Example.of(commonFileSystem, matcher);
            commonFileSystemPages = commonFileSystemRepository.findAll(example, pageable);
        }

        return commonFileSystemPages;
    }

    public List<CommonFileSystem> uploadToLoc(MultipartHttpServletRequest multiRequest, String workflowId) {

        //校验工单号

        //构造file记录

        //保存文件
        Iterator<String> iter = multiRequest.getFileNames();

        String moduleTypeStr = multiRequest.getParameter("moduleType");
        String systemType = multiRequest.getParameter("systemType");
        String toBeFollowUpStr = multiRequest.getParameter("toBeFollowUp");
        String processStatusStr = multiRequest.getParameter("processStatus");
        Integer moduleType = StringUtils.isEmpty(moduleTypeStr) ? 0 : Integer.parseInt(moduleTypeStr);
        Integer toBeFollowUp = StringUtils.isEmpty(toBeFollowUpStr) ? 1 : Integer.parseInt(toBeFollowUpStr);

        File directory = new File(fileDir + File.separator + workflowId);
        try {
            if (!directory.exists()) {
                FileUtils.forceMkdir(directory);
                directory.setReadable(true, false);
                directory.setWritable(true, false);
            }
        } catch (IOException e) {
            throw new CommonException(e.getMessage());
        }
        List<CommonFileSystem> commonFiles = new ArrayList<CommonFileSystem>();
//        UserInfo user = RequestContextUtils.getUser();

        while (iter != null && iter.hasNext()) {
            // 取得上传文件
            MultipartFile file = multiRequest.getFile(iter.next());
            if (file != null) {
                // 取得当前上传文件的文件名称
                String originalName = file.getOriginalFilename();
                log.info("文件名称: " + originalName);
                log.info("文件长度: " + file.getSize());
                log.info("文件类型: " + file.getContentType());
                log.info("========================================");
                // 如果名称长度大于0,说明有文件上传
                if (originalName.trim().length() > 0) {
                    // 判断文件大小
                    if (file.getSize() > Constants.MAX_FILE_SIZE) {
                        log.info(originalName + "文件太大");
                        continue;
                    }
                    //获取文件的扩展名
                    String fileExtends = "";
                    int pos = originalName.lastIndexOf(".");
                    if (pos > -1) {
                        fileExtends = originalName.substring(pos + 1);
                    }
                    File dest = new File(directory, originalName);
                    try {
                        if (dest.exists()) {
                            FileUtils.forceDelete(dest);
                        }
                        FileUtils.copyToFile(file.getInputStream(), dest);
                        dest.setReadable(true, false);
                        dest.setWritable(true, false);
                    } catch (IOException e) {
                        log.error(e.getMessage());
                        throw new CommonException(e.getMessage());
                    }

                    CommonFileSystem commonFile = new CommonFileSystem();
                    commonFile.setFilePath(File.separator + workflowId + File.separator + originalName);
                    commonFile.setFileName(originalName);
                    commonFile.setFileExtends(fileExtends);
                    commonFile.setEnabled(1);
//                    commonFile.setEmployeeId(user.getUserId());
//                    commonFile.setCreateStaff(user.getUserId());
//                    commonFile.setModifyStaff(user.getUserId());

                    Date now = new Date();
                    commonFile.setUplDate(now);
                    commonFile.setCreateDate(now);
                    commonFile.setModifyDate(now);
                    commonFile.setModuleType(moduleType);
                    commonFile.setSystemType(systemType);
                    commonFile.setToBeFollowUp(toBeFollowUp);
                    commonFile.setProcessStatus(processStatusStr);

                    commonFiles.add(commonFile);

                    createCommonFileSystem(commonFile);
                    commonFile.setVisitUri("/file/download/" + commonFile.getId());
                    updateCommonFileSystem(commonFile, commonFile.getId());
                    log.info(originalName + "上传成功");


                } else {
                    log.info("文件名为空");
                    //break;
                    continue;
                }
            }
        }
        //返回文件记录列表
        return commonFiles;
    }

    @Override
    public List<CommonFileSystem> upload(MultipartHttpServletRequest multiRequest, String workflowId) {
//        if (!"pro".equals(activeProfile)) {
            return uploadToLoc(multiRequest, workflowId);
//        }
//
//        //校验工单号
//
//        //构造file记录
//
//        //保存文件
//        Iterator<String> iter = multiRequest.getFileNames();
//
//        String moduleTypeStr = multiRequest.getParameter("moduleType");
//        String systemType = multiRequest.getParameter("systemType");
//        String toBeFollowUpStr = multiRequest.getParameter("toBeFollowUp");
//        String processStatusStr = multiRequest.getParameter("processStatus");
//        Integer moduleType = StringUtils.isEmpty(moduleTypeStr) ? 0 : Integer.parseInt(moduleTypeStr);
//        Integer toBeFollowUp = StringUtils.isEmpty(toBeFollowUpStr) ? 1 : Integer.parseInt(toBeFollowUpStr);
//
//
//        AliFileUtils aliFileUtils = new AliFileUtils();
//        log.debug("开始校验bucketName是否存在:" + fileParamConstant.getBucketName());
//        boolean existsBucket = aliFileUtils.existsBucket(fileParamConstant);
//        log.debug("开始校验bucketName是否存在:" + existsBucket);
//        if (!existsBucket) {
//            log.warn("阿里云OSS文件系统的BucketName：" + fileParamConstant.getBucketName() + "不存在");
//            throw new CommonException("阿里云OSS文件系统的BucketName" + fileParamConstant.getBucketName() + "不存在");
//            // log.debug("创建bucketName："+fileParamConstant.getBucketName());
//            // aliFileUtils.createBucket(fileParamConstant.getBucketName(), fileParamConstant);
//            // log.warn("创建bucketName："+fileParamConstant.getBucketName()+"====end");
//        }
//        List<CommonFileSystem> commonFiles = new ArrayList<>();
//        UserInfo user = RequestContextUtils.getUser();
//        try {
//            while (iter != null && iter.hasNext()) {
//                // 取得上传文件
//                MultipartFile file = multiRequest.getFile(iter.next());
//                if (file != null) {
//                    // 取得当前上传文件的文件名称
//                    String originalName = file.getOriginalFilename();
//                    log.info("文件名称: " + originalName);
//                    log.info("文件长度: " + file.getSize());
//                    log.info("文件类型: " + file.getContentType());
//                    log.info("========================================");
//
//                    if (!checkType(originalName)) {
//                        throw new DataException(String.format("文件格式错误，系统允许上传的附件格式：%s", fileTypes));
//                    }
//
//                    // 如果名称长度大于0,说明有文件上传
//                    if (originalName.trim().length() > 0) {
//                        // 判断文件大小
//                        if (file.getSize() > Constants.MAX_FILE_SIZE) {
//                            log.info(originalName + "文件太大");
//                            continue;
//                        }
//                        String objectName = workflowId + "_" + originalName + "_" + System.currentTimeMillis();
//                        //上傳文件
//                        aliFileUtils.uploadFileStream(file.getInputStream(), objectName, fileParamConstant);
//                        //保存操作記錄
//                        saveCommonFile(systemType, processStatusStr, moduleType, toBeFollowUp, commonFiles, user, originalName, objectName);
//                    } else {
//                        log.info("文件名为空");
//                        //break;
//                        continue;
//                    }
//                }
//            }
//        } catch (IOException e) {
//            log.warn("文件流报错：" + e.getMessage());
//        }
//        //返回文件记录列表
//        return commonFiles;
    }

    @Override
    public CommonFileSystem upload(InputStream inputStream, String fileName) {
        String filePath = null;
        try {
            if (!checkType(fileName)) {
                throw new DataException(String.format("文件格式错误，系统允许上传的附件格式：%s", fileTypes));
            }
//            if ("pro".equals(activeProfile)) {
//                String objectName = fileName + "_" + System.currentTimeMillis();
//                filePath = objectName;
//                AliFileUtils aliFileUtils = new AliFileUtils();
//                if (!aliFileUtils.existsBucket(fileParamConstant)) {
//                    throw new CommonException("阿里云OSS文件系统的BucketName" + fileParamConstant.getBucketName() + "不存在");
//                }
//                aliFileUtils.uploadFileStream(inputStream, objectName, fileParamConstant);
//            } else {
                File directory = checkDirectory();
                filePath = directory.getPath().replace(fileDir, "") + File.separator + fileName;
                File dest = new File(directory, fileName);
                if (dest.exists()) {
                    FileUtils.forceDelete(dest);
                }
                FileUtils.copyToFile(inputStream, dest);
                dest.setReadable(true, false);
                dest.setWritable(true, false);
//            }
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
        return saveCommonFileSystem(fileName, filePath);
    }

    private File checkDirectory() {
        String yearMonth = DateFormatUtils.format(new Date(), "yyyy-MM");
        File directory = new File(fileDir + File.separator + yearMonth);
        try {
            if (!directory.exists()) {
                FileUtils.forceMkdir(directory);
                directory.setReadable(true, false);
                directory.setWritable(true, false);
            }
        } catch (IOException e) {
            throw new CommonException(e.getMessage());
        }
        return directory;
    }

    private CommonFileSystem saveCommonFileSystem(String originalName, String filePath) {
        CommonFileSystem commonFile = new CommonFileSystem();
        commonFile.setFilePath(filePath);
        commonFile.setFileName(originalName);
        commonFile.setFileExtends(StringUtils.substringAfterLast(originalName, "."));
        commonFile.setEnabled(1);

        Date now = new Date();
        commonFile.setUplDate(now);
        commonFile.setCreateDate(now);
        commonFile.setModifyDate(now);

        createCommonFileSystem(commonFile);
        commonFile.setVisitUri("/file/download/" + commonFile.getId());
        updateCommonFileSystem(commonFile, commonFile.getId());
        return commonFile;
    }

    private boolean checkType(String originalName) {
        String fileExtends = "";
        int pos = originalName.lastIndexOf(".");
        if (pos > -1) {
            fileExtends = originalName.substring(pos + 1);
        }
        return StringUtils.contains(fileTypes, fileExtends);
    }

//    private void saveCommonFile(String systemType, String processStatusStr, Integer moduleType, Integer toBeFollowUp, List<CommonFileSystem> commonFiles, UserInfo user, String originalName, String objectName) {
//        //获取文件的扩展名
//        String fileExtends = "";
//        int pos = originalName.lastIndexOf(".");
//        if (pos > -1) {
//            fileExtends = originalName.substring(pos + 1);
//        }
//
//        CommonFileSystem commonFile = new CommonFileSystem();
//        commonFile.setFilePath(objectName);
//        commonFile.setFileName(originalName);
//        commonFile.setFileExtends(fileExtends);
//        commonFile.setEnabled(1);
////        commonFile.setEmployeeId(user.getUserId());
////        commonFile.setCreateStaff(user.getUserId());
////        commonFile.setModifyStaff(user.getUserId());
//
//        Date now = new Date();
//        commonFile.setUplDate(now);
//        commonFile.setCreateDate(now);
//        commonFile.setModifyDate(now);
//        commonFile.setModuleType(moduleType);
//        commonFile.setSystemType(systemType);
//        commonFile.setToBeFollowUp(toBeFollowUp);
//        commonFile.setProcessStatus(processStatusStr);
//
//        commonFiles.add(commonFile);
//
//        createCommonFileSystem(commonFile);
//        commonFile.setVisitUri("/file/download/" + commonFile.getId());
//        updateCommonFileSystem(commonFile, commonFile.getId());
//        log.info(originalName + "上传成功");
//    }

    @Override
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, Integer fileId) throws IOException {
        CommonFileSystem commonFile = findById(fileId);
        if (commonFile == null) {
            throw new CommonException("不存在文件");
        }
        HttpHeaders headers = new HttpHeaders();
        //为了解决中文名称乱码问题
        String fileName = processFileName(request, commonFile.getFileName());
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String filePath = commonFile.getFilePath();
        byte[] body;
//        if ("pro".equals(activeProfile)) {
//            body = new AliFileUtils().dowloadFile(filePath, fileParamConstant);
//        } else {
            body = FileUtils.readFileToByteArray(new File(fileDir + filePath));
//        }
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
    }

    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = fileNames;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie
                codedfilename = java.net.URLEncoder.encode(fileNames, "UTF8");
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
                codedfilename = new String(fileNames.getBytes(CharEncoding.UTF_8),
                        CharEncoding.ISO_8859_1);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return codedfilename;
    }


    @Override
    public List<CommonFileSystem> queryFileByWorkflowId(Integer workflowId) {
        return null;
    }

    @Override
    public int delete(Integer fileId) {
        CommonFileSystem commonFile = findById(fileId);
        if (commonFile == null) {
            throw new CommonException("不存在文件");
        }
        commonFile.setProcessStatus("4");
//        //阿里OSS文件系统删除文件
//        AliFileUtils aliFileUtils = new AliFileUtils();
//        aliFileUtils.deleteFile(commonFile.getFilePath(), fileParamConstant);

        return updateCommonFileSystem(commonFile, commonFile.getId());

    }
}