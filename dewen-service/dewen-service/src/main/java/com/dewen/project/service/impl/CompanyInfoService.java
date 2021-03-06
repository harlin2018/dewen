package com.dewen.project.service.impl;

import com.dewen.project.constants.CompanyInfoEnums;
import com.dewen.project.constants.Constants;
import com.dewen.project.domain.*;
import com.dewen.project.repository.*;
import com.dewen.project.service.ICompanyInfoService;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 重点工业企业基本情况表
 * <p>Description: CompanyInfo ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class CompanyInfoService implements ICompanyInfoService {

    @Autowired
    private CompanyInfoRepository CompanyInfoRepository;
    @Autowired
    private CompanyProductRepository companyProductRepository;
    @Autowired
    private CompanySewageWasteRepository companySewageWasteRepository;
    @Autowired
    private CompanyProjectRepository companyProjectRepository;
    @Autowired
    private CompanyWasteRepository companyWasteRepository;
    @Autowired
    private CompanyRecordRepository companyRecordRepository;
    @Autowired
    private CommonModelFileRepository commonModelFileRepository;
    @Value("${fileDir.workflow}")
    public String fileDir;
    @Autowired
    private CustomerNoticeRepository customerNoticeRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(value = "transactionManager")
    public int createCompanyInfo(CompanyInfo companyInfo) {
        queryFk(companyInfo);
        companyInfo.setCreateDate(new Date());
        CompanyInfoRepository.save(companyInfo);
        // 主要生产产品表
        List<CompanyProduct> companyProductList = companyInfo.getCompanyProductList();
        if (companyProductList!=null) {
            for (CompanyProduct companyProduct : companyProductList) {
                CompanyProduct newCompanyProduct = new CompanyProduct();
                BeanUtils.copyProperties(companyProduct, newCompanyProduct);
                newCompanyProduct.setCompanyId(companyInfo);
                companyProductRepository.save(newCompanyProduct);
            }
        }
        // 排污种类：废水
        List<CompanySewageWaste> wasteWaterList = companyInfo.getWasteWaterList();
        if (wasteWaterList!=null) {
            for (CompanySewageWaste companySewageWaste : wasteWaterList) {
                CompanySewageWaste newCompanySewageWaste = new CompanySewageWaste();
                BeanUtils.copyProperties(companySewageWaste, newCompanySewageWaste);
                newCompanySewageWaste.setCompanyId(companyInfo);
                newCompanySewageWaste.setWasteType(CompanyInfoEnums.WasteType.wasteWater.getValue());
                companySewageWasteRepository.save(newCompanySewageWaste);
            }
        }
        // 排污种类：废气
        List<CompanySewageWaste> wasteGasList = companyInfo.getWasteGasList();
        if (wasteGasList!=null) {
            for (CompanySewageWaste companySewageWaste : wasteGasList) {
                CompanySewageWaste newCompanySewageWaste = new CompanySewageWaste();
                BeanUtils.copyProperties(companySewageWaste, newCompanySewageWaste);
                newCompanySewageWaste.setCompanyId(companyInfo);
                newCompanySewageWaste.setWasteType(CompanyInfoEnums.WasteType.wasteGas.getValue());
                companySewageWasteRepository.save(newCompanySewageWaste);
            }
        }
        // 监测项目（废水）
        List<CompanyProject> wasteWaterMonitorList = companyInfo.getWasteWaterMonitorList();
        if (wasteWaterMonitorList!=null) {
            for (CompanyProject companyProject : wasteWaterMonitorList) {
                CompanyProject newCompanyProject = new CompanyProject();
                BeanUtils.copyProperties(companyProject, newCompanyProject);
                newCompanyProject.setCompanyId(companyInfo);
                newCompanyProject.setWasteType(CompanyInfoEnums.WasteType.wasteWater.getValue());
                companyProjectRepository.save(newCompanyProject);
            }
        }
        // 监测项目（废气）
        List<CompanyProject> wasteGasMonitorList = companyInfo.getWasteGasMonitorList();
        if (wasteGasMonitorList!=null) {
            for (CompanyProject companyProject : wasteGasMonitorList) {
                CompanyProject newCompanyProject = new CompanyProject();
                BeanUtils.copyProperties(companyProject, newCompanyProject);
                newCompanyProject.setCompanyId(companyInfo);
                newCompanyProject.setWasteType(CompanyInfoEnums.WasteType.wasteGas.getValue());
                companyProjectRepository.save(newCompanyProject);
            }
        }
        // 危废
        List<CompanyWaste> companyWasteList = companyInfo.getCompanyWasteList();
        if (companyWasteList!=null) {
            for (CompanyWaste companyWaste : companyWasteList) {
                CompanyWaste newCompanyWaste = new CompanyWaste();
                BeanUtils.copyProperties(companyWaste, newCompanyWaste);
                newCompanyWaste.setCompanyId(companyInfo);
                companyWasteRepository.save(newCompanyWaste);
            }
        }
        // 巡查执法记录
        List<CompanyRecord> inspectRecordList = companyInfo.getInspectRecordList();
        if (inspectRecordList!=null) {
            for (CompanyRecord companyRecord : inspectRecordList) {
                CompanyRecord newCompanyRecord = new CompanyRecord();
                BeanUtils.copyProperties(companyRecord, newCompanyRecord);
                newCompanyRecord.setCompanyId(companyInfo);
                newCompanyRecord.setRecordType(CompanyInfoEnums.RecordType.inspectRecord.getValue());
                newCompanyRecord.setCreateDate(new Date());
                companyRecordRepository.save(newCompanyRecord);
                List<CommonFileSystem> fileIdList = companyRecord.getFileIdList();
                for (CommonFileSystem commonFileSystem : fileIdList) {
                    CommonModelFile commonModelFile = new CommonModelFile();
                    commonModelFile.setCompanyFileId(commonFileSystem);
                    commonModelFile.setCompanyRecord(newCompanyRecord);
                    commonModelFileRepository.save(commonModelFile);
                }
            }
        }
        // 行政执法记录
        List<CompanyRecord> adminRecordList = companyInfo.getAdminRecordList();
        if (adminRecordList!=null) {
            for (CompanyRecord companyRecord : adminRecordList) {
                CompanyRecord newCompanyRecord = new CompanyRecord();
                BeanUtils.copyProperties(companyRecord, newCompanyRecord);
                newCompanyRecord.setCompanyId(companyInfo);
                newCompanyRecord.setRecordType(CompanyInfoEnums.RecordType.adminRecord.getValue());
                newCompanyRecord.setCreateDate(new Date());
                companyRecordRepository.save(newCompanyRecord);
                List<CommonFileSystem> fileIdList = companyRecord.getFileIdList();
                for (CommonFileSystem commonFileSystem : fileIdList) {
                    CommonModelFile commonModelFile = new CommonModelFile();
                    commonModelFile.setCompanyFileId(commonFileSystem);
                    commonModelFile.setCompanyRecord(newCompanyRecord);
                    commonModelFileRepository.save(commonModelFile);
                }
            }
        }
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCompanyInfo(CompanyInfo companyInfo, Integer id) {
        queryFk(companyInfo);
        Optional<CompanyInfo> CompanyInfoRes = CompanyInfoRepository.findById(id);
        if (CompanyInfoRes.isPresent()) {
            companyInfo = NullAwareBeanUtilsBean.copyExculdeList(CompanyInfoRes.get(), companyInfo);
            CompanyInfoRepository.save(companyInfo);
            // 主要生产产品表
            // 删除原有的资料
            companyProductRepository.deleteByCompanyId(companyInfo);
            List<CompanyProduct> companyProductList = companyInfo.getCompanyProductList();
            if (companyProductList!=null) {
                for (CompanyProduct companyProduct : companyProductList) {
                    CompanyProduct newCompanyProduct = new CompanyProduct();
                    BeanUtils.copyProperties(companyProduct, newCompanyProduct);
                    newCompanyProduct.setCompanyId(companyInfo);
                    companyProductRepository.save(newCompanyProduct);
                }
            }
            // 排污种类：废水
            // 删除原有的资料
            companySewageWasteRepository.deleteByCompanyId(companyInfo);
            List<CompanySewageWaste> wasteWaterList = companyInfo.getWasteWaterList();
            if (wasteWaterList!=null) {
                for (CompanySewageWaste companySewageWaste : wasteWaterList) {
                    CompanySewageWaste newCompanySewageWaste = new CompanySewageWaste();
                    BeanUtils.copyProperties(companySewageWaste, newCompanySewageWaste);
                    newCompanySewageWaste.setCompanyId(companyInfo);
                    newCompanySewageWaste.setWasteType(CompanyInfoEnums.WasteType.wasteWater.getValue());
                    companySewageWasteRepository.save(newCompanySewageWaste);
                }
            }
            // 排污种类：废气
            List<CompanySewageWaste> wasteGasList = companyInfo.getWasteGasList();
            if (wasteGasList!=null) {
                for (CompanySewageWaste companySewageWaste : wasteGasList) {
                    CompanySewageWaste newCompanySewageWaste = new CompanySewageWaste();
                    BeanUtils.copyProperties(companySewageWaste, newCompanySewageWaste);
                    newCompanySewageWaste.setCompanyId(companyInfo);
                    newCompanySewageWaste.setWasteType(CompanyInfoEnums.WasteType.wasteGas.getValue());
                    companySewageWasteRepository.save(newCompanySewageWaste);
                }
            }
            // 监测项目（废水）
            // 删除原有的资料
            companyProjectRepository.deleteByCompanyId(companyInfo);
            List<CompanyProject> wasteWaterMonitorList = companyInfo.getWasteWaterMonitorList();
            if (wasteWaterMonitorList!=null) {
                for (CompanyProject companyProject : wasteWaterMonitorList) {
                    CompanyProject newCompanyProject = new CompanyProject();
                    BeanUtils.copyProperties(companyProject, newCompanyProject);
                    newCompanyProject.setCompanyId(companyInfo);
                    newCompanyProject.setWasteType(CompanyInfoEnums.WasteType.wasteWater.getValue());
                    companyProjectRepository.save(newCompanyProject);
                }
            }
            // 监测项目（废气）
            List<CompanyProject> wasteGasMonitorList = companyInfo.getWasteGasMonitorList();
            if (wasteGasMonitorList!=null) {
                for (CompanyProject companyProject : wasteGasMonitorList) {
                    CompanyProject newCompanyProject = new CompanyProject();
                    BeanUtils.copyProperties(companyProject, newCompanyProject);
                    newCompanyProject.setCompanyId(companyInfo);
                    newCompanyProject.setWasteType(CompanyInfoEnums.WasteType.wasteGas.getValue());
                    companyProjectRepository.save(newCompanyProject);
                }
            }
            // 危废
            // 删除原有的资料
            companyWasteRepository.deleteByCompanyId(companyInfo);
            List<CompanyWaste> companyWasteList = companyInfo.getCompanyWasteList();
            if (companyWasteList!=null) {
                for (CompanyWaste companyWaste : companyWasteList) {
                    CompanyWaste newCompanyWaste = new CompanyWaste();
                    BeanUtils.copyProperties(companyWaste, newCompanyWaste);
                    newCompanyWaste.setCompanyId(companyInfo);
                    companyWasteRepository.save(newCompanyWaste);
                }
            }
            // 巡查执法记录
            // 删除原有的资料
//            companyRecordRepository.deleteByCompanyId(companyInfo);
            customerNoticeRepository.deleteByCompanyId(companyInfo.getId());
            Map<Integer, CompanyRecord> recordMap = new HashMap<>();
            List<CompanyRecord> dbList = companyRecordRepository.findAllByCompanyIdAndRecordType(companyInfo, CompanyInfoEnums.RecordType.inspectRecord.getValue());
            List<CompanyRecord> inspectRecordList = companyInfo.getInspectRecordList();
            dbList.forEach(companyRecord -> {
                recordMap.put(companyRecord.getId(), companyRecord);
            });
            if (inspectRecordList!=null) {
                for (CompanyRecord companyRecord : inspectRecordList) {
                    if(companyRecord.getId()!=null){
                        recordMap.remove(companyRecord.getId());
                    }
                    if(companyRecord.getId()==null){
                        CompanyRecord newCompanyRecord = new CompanyRecord();
                        BeanUtils.copyProperties(companyRecord, newCompanyRecord);
                        newCompanyRecord.setCompanyId(companyInfo);
                        newCompanyRecord.setRecordType(CompanyInfoEnums.RecordType.inspectRecord.getValue());
                        newCompanyRecord.setCreateDate(new Date());
                        companyRecordRepository.save(newCompanyRecord);
                        List<CommonFileSystem> fileIdList = companyRecord.getFileIdList();
                        if (fileIdList!=null && fileIdList.size() > 0) {
                            for (CommonFileSystem commonFileSystem : fileIdList) {
                                CommonModelFile commonModelFile = new CommonModelFile();
                                commonModelFile.setCompanyFileId(commonFileSystem);
                                commonModelFile.setCompanyRecord(newCompanyRecord);
                                commonModelFileRepository.save(commonModelFile);
                            }
                        }
                    }
                }
                // 移除已删除的
                Set<Integer> removeSets = recordMap.keySet();
                removeSets.forEach(it -> {
                    CompanyRecord record = recordMap.get(it);
                    companyRecordRepository.deleteById(record.getId());
                });
            }
            // 行政执法记录
            Map<Integer, CompanyRecord> adminRecordMap = new HashMap<>();
            List<CompanyRecord> adminRecordDbList = companyRecordRepository.findAllByCompanyIdAndRecordType(companyInfo, CompanyInfoEnums.RecordType.adminRecord.getValue());
            List<CompanyRecord> adminRecordList = companyInfo.getAdminRecordList();
            if (adminRecordList!=null) {
                for (CompanyRecord companyRecord : adminRecordList) {
                    if(companyRecord.getId()!=null){
                        adminRecordMap.remove(companyRecord.getId());
                    }
                    if(companyRecord.getId()==null){
                        CompanyRecord newCompanyRecord = new CompanyRecord();
                        BeanUtils.copyProperties(companyRecord, newCompanyRecord);
                        newCompanyRecord.setCompanyId(companyInfo);
                        newCompanyRecord.setCreateDate(new Date());
                        newCompanyRecord.setRecordType(CompanyInfoEnums.RecordType.adminRecord.getValue());
                        companyRecordRepository.save(newCompanyRecord);
                        List<CommonFileSystem> fileIdList = companyRecord.getFileIdList();
                        if (fileIdList!=null && fileIdList.size() > 0) {
                            for (CommonFileSystem commonFileSystem : fileIdList) {
                                CommonModelFile commonModelFile = new CommonModelFile();
                                commonModelFile.setCompanyFileId(commonFileSystem);
                                commonModelFile.setCompanyRecord(newCompanyRecord);
                                commonModelFileRepository.save(commonModelFile);
                            }
                        }
                    }
                }
                // 移除已删除的
                Set<Integer> removeSets = adminRecordMap.keySet();
                removeSets.forEach(it -> {
                    CompanyRecord record = adminRecordMap.get(it);
                    companyRecordRepository.deleteById(record.getId());
                });
            }
            return Constants.RETURN_STATUS_SUCCESS;
        } else {
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     *
     * @param CompanyInfo
     */
    private void queryFk(CompanyInfo CompanyInfo) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCompanyInfo(Integer id) {
        Optional<CompanyInfo> CompanyInfo = CompanyInfoRepository.findById(id);
        if (CompanyInfo.isPresent()) {
            CompanyInfoRepository.deleteById(id);
            // 删除原有的资料
            companyProductRepository.deleteByCompanyId(CompanyInfo.get());
            companySewageWasteRepository.deleteByCompanyId(CompanyInfo.get());
            companyRecordRepository.deleteByCompanyId(CompanyInfo.get());
            customerNoticeRepository.deleteByCompanyId(CompanyInfo.get().getId());
            companyWasteRepository.deleteByCompanyId(CompanyInfo.get());
            companyProjectRepository.deleteByCompanyId(CompanyInfo.get());
            Optional<CompanyInfo> CompanyInfoRes = CompanyInfoRepository.findById(id);
            if (CompanyInfoRes.isPresent()) {
                return Constants.RETURN_STATUS_FAIL;
            } else {
                return Constants.RETURN_STATUS_SUCCESS;
            }
        } else {
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CompanyInfo findById(Integer id) {
        Optional<CompanyInfo> companyInfoOpt = CompanyInfoRepository.findById(id);
        if (companyInfoOpt.isPresent()) {
            // 主要生产产品表
            Integer companyId = companyInfoOpt.get().getId();
            CompanyInfo companyInfo = companyInfoOpt.get();
            companyInfo.setCompanyProductList(companyProductRepository.findAllByCompanyId(companyInfo).stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList()));
            // 排污种类：废水
            companyInfo.setWasteWaterList(companySewageWasteRepository.findAllByCompanyIdAndWasteType(companyInfo, CompanyInfoEnums.WasteType.wasteWater.getValue())
                    .stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList()));
            // 排污种类：废气
            companyInfo.setWasteGasList(companySewageWasteRepository.findAllByCompanyIdAndWasteType(companyInfo, CompanyInfoEnums.WasteType.wasteGas.getValue())
                    .stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList()));
            // 监测项目（废水）
            companyInfo.setWasteWaterMonitorList(companyProjectRepository.findAllByCompanyIdAndWasteType(companyInfo, CompanyInfoEnums.WasteType.wasteWater.getValue())
                    .stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList()));
            // 监测项目（废气）
            companyInfo.setWasteGasMonitorList(companyProjectRepository.findAllByCompanyIdAndWasteType(companyInfo, CompanyInfoEnums.WasteType.wasteGas.getValue())
                    .stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList()));
            // 危废
            companyInfo.setCompanyWasteList(companyWasteRepository.findAllByCompanyId(companyInfo)
                    .stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList()));
            // 巡查执法记录
            companyInfo.setInspectRecordList(companyRecordRepository.findAllByCompanyIdAndRecordType(companyInfo, CompanyInfoEnums.RecordType.inspectRecord.getValue())
                    .stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList()));
            companyInfo.getInspectRecordList().forEach(companyRecord -> {
                companyRecord.setFileIdList(commonModelFileRepository.findAllByCompanyRecord(companyRecord)
                        .stream().map(CommonModelFile::getCompanyFileId).collect(Collectors.toList()));
                companyRecord.getFileIdList().forEach(it->it.setFullPath(fileDir+it.getFilePath()));
            });

            // 行政执法记录
            companyInfo.setAdminRecordList(companyRecordRepository.findAllByCompanyIdAndRecordType(companyInfo, CompanyInfoEnums.RecordType.adminRecord.getValue())
                    .stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList()));
            companyInfo.getAdminRecordList().forEach(companyRecord -> {
                companyRecord.setFileIdList(commonModelFileRepository.findAllByCompanyRecord(companyRecord)
                        .stream().map(CommonModelFile::getCompanyFileId).collect(Collectors.toList()));
                companyRecord.getFileIdList().forEach(it->it.setFullPath(fileDir+it.getFilePath()));
            });
            companyInfo.setBasePath(fileDir);
            return companyInfo;
        } else {
            return null;
        }
    }

    @Override
    public Page<CompanyInfo> list(CompanyInfo CompanyInfo, int pageNumber, int pageSize, String sorts) {

        //add sorts to query
        Page<CompanyInfo> CompanyInfoPages = null;
        //Pageable
        Pageable pageable = PageUtils.pageable(pageNumber, pageSize, sorts);

        if (CompanyInfo==null) {
            CompanyInfoPages = CompanyInfoRepository.findAll(pageable);
        } else {
            Specification<CompanyInfo> specification = (root, criteriaQuery, criteriaBuilder) -> {

                List<Predicate> predicateAndList = new ArrayList<Predicate>();
                if (CompanyInfo.getId()!=null) {
                    predicateAndList.add(criteriaBuilder.like(root.get("id"), "%" + CompanyInfo.getId() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getName())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("name"), "%" + CompanyInfo.getName() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getCode())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("code"), "%" + CompanyInfo.getCode() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getCode())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("code"), "%" + CompanyInfo.getCode() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getAddress())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("address"), "%" + CompanyInfo.getAddress() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getBreaks())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("breaks"), "%" + CompanyInfo.getBreaks() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getLegalRepresentative())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("legalRepresentative"), "%" + CompanyInfo.getLegalRepresentative() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getEnvironmentalProtectionOfficer())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("environmentalProtectionOfficer"), "%" + CompanyInfo.getEnvironmentalProtectionOfficer() + "%"));
                }
                if (CompanyInfo.getOfficialTime()!=null) {
                    predicateAndList.add(criteriaBuilder.equal(root.get("officialTime"), CompanyInfo.getOfficialTime()));
                }
                if (CompanyInfo.getCreateDate()!=null) {
                    predicateAndList.add(criteriaBuilder.equal(root.get("createDate"), CompanyInfo.getCreateDate()));
                }
                // 废水监督性检测周期
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getSicfwwo())) {
                    predicateAndList.add(criteriaBuilder.equal(root.get("sicfwwo"), "%" + CompanyInfo.getSicfwwo() + "%" ));
                }
                // 土壤监督性检测周期
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getSicfwws())) {
                    predicateAndList.add(criteriaBuilder.equal(root.get("sicfwws"), "%" + CompanyInfo.getSicfwws() + "%" ));
                }
                // 废气监督性检测周期
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getSicfwwt())) {
                    predicateAndList.add(criteriaBuilder.equal(root.get("sicfwwt"), "%" + CompanyInfo.getSicfwwt() + "%" ));
                }
                if (CompanyInfo.getCreateDate()!=null) {
                    predicateAndList.add(criteriaBuilder.equal(root.get("createDate"), CompanyInfo.getCreateDate()));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getContactNumber())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("contactNumber"), "%" + CompanyInfo.getContactNumber() + "%"));
                }
                if (predicateAndList.size() > 0) {
                    return criteriaQuery.where(criteriaBuilder.and(predicateAndList.toArray(new Predicate[predicateAndList.size() - 1]))).getRestriction();
                }
                return criteriaQuery.getRestriction();
            };
            CompanyInfoPages = CompanyInfoRepository.findAll(specification, pageable);
        }

        return CompanyInfoPages;
    }

    @Override
    public Map<String, Object> record() {
        Map<String, Object> recordMap = new HashMap<>();
        // 废水
        List<CompanySewageWaste> wasteWaterList = companySewageWasteRepository.findAllByWasteTypeGroupByName(CompanyInfoEnums.WasteType.wasteWater.getValue()).stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList());
        // 废气
        List<CompanySewageWaste> wasteGasList = companySewageWasteRepository.findAllByWasteTypeGroupByName(CompanyInfoEnums.WasteType.wasteGas.getValue()).stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList());
        // 监测项目（废水）
        List<CompanyProject> wasteWaterMonitorList = companyProjectRepository.findAllByWasteTypeGroupByMonitorProject(CompanyInfoEnums.WasteType.wasteWater.getValue()).stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList());
        // 监测项目（废气）
        List<CompanyProject> wasteGasMonitorList = companyProjectRepository.findAllByWasteTypeGroupByMonitorProject(CompanyInfoEnums.WasteType.wasteGas.getValue()).stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList());
        // 巡查执法记录
        List<CompanyRecord> inspectRecordList = companyRecordRepository.findAllByRecordTypeGroupByContent(CompanyInfoEnums.RecordType.inspectRecord.getValue()).stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList());
        inspectRecordList.forEach(companyRecord -> {
            companyRecord.setFileIdList(commonModelFileRepository.findAllByCompanyRecord(companyRecord)
                    .stream().map(CommonModelFile::getCompanyFileId).collect(Collectors.toList()));
            companyRecord.getFileIdList().forEach(it -> it.setFullPath(fileDir + it.getFilePath()));
        });

        // 行政执法记录
        List<CompanyRecord> adminRecordList = companyRecordRepository.findAllByRecordTypeGroupByContent(CompanyInfoEnums.RecordType.adminRecord.getValue()).stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList());
        adminRecordList.forEach(companyRecord -> {
            companyRecord.setFileIdList(commonModelFileRepository.findAllByCompanyRecord(companyRecord)
                    .stream().map(CommonModelFile::getCompanyFileId).collect(Collectors.toList()));
            companyRecord.getFileIdList().forEach(it -> it.setFullPath(fileDir + it.getFilePath()));
        });
        recordMap.put("wasteWaterList", wasteWaterList);
        recordMap.put("wasteGasList", wasteGasList);
        recordMap.put("wasteWaterMonitorList", wasteWaterMonitorList);
        recordMap.put("wasteGasMonitorList", wasteGasMonitorList);
        recordMap.put("inspectRecordList", inspectRecordList);
        recordMap.put("adminRecordList", adminRecordList);
        recordMap.put("basePath", fileDir);


        return recordMap;
    }

    public List<Object> getListData(ExportParam exportParam){
        List<String> fieIds = exportParam.getFieIds();
        List<Integer> ids = exportParam.getIds();
        StringBuffer sql = new StringBuffer("select ");
        for (int i = 0; i < fieIds.size(); i++) {
            //查询字段
            String fieId = fieIds.get(i);

            if ("enterprise_size".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '大型' when 2 then '中型' when 3 then '小型' end ").append(fieId);
            } else if ("pollution_source_management_level".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '国控' when 2 then '省控' when 3 then '市控' when 4 then '区控' when 5 then '一般' end ").append(fieId);
            } else if ("eia".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '报告书' when 2 then '报告表' when 3 then '无' end ").append(fieId);
            } else if ("water_balance".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '正常' when 2 then '异常' when 3 then '其它' end ").append(fieId);
            } else if ("official_reply".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '有' when 2 then '无' end ").append(fieId);
            } else if ("row_to".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '三阳河' when 2 then '雷河' end ").append(fieId);
            } else if ("disposal_way".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '第三方' when 2 then '回收' when 3 then '回用' when 4 then '垃圾回收站' end ").append(fieId);
            } else if ("the_sewage_to".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '纳管（污水处理厂）' when 2 then '雨水管网' when 3 then '其他' end ").append(fieId);
            } else if ("life_line_to".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '纳管（污水处理厂）' when 2 then '雨水管网' when 3 then '其他' end ").append(fieId);
            } else if ("sicfwwo".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '1个月' when 2 then '3个月' when 3 then '6个月' when 4 then '12个月' end ").append(fieId);
            } else if ("sicfwwt".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '1个月' when 2 then '3个月' when 3 then '6个月' when 4 then '12个月' end ").append(fieId);
            } else if ("sicfwws".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '1个月' when 2 then '3个月' when 3 then '6个月' when 4 then '12个月' end ").append(fieId);
            } else if ("sewerage_rain".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '有' when 2 then '无' end ").append(fieId);
            } else if ("enterprise_pretreatment".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '有' when 2 then '无' end ").append(fieId);
            } else if ("environmental_protection_plan".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '有' when 2 then '无' end ").append(fieId);
            } else if ("emission_permit".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '有' when 2 then '无' end ").append(fieId);
            } else if ("new_eia".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '办理中' when 2 then '无' end ").append(fieId);
            } else if ("eia_process".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '有增、改' when 2 then '无增、改' end ").append(fieId);
            } else if ("supervisory_inspection_enterprise".equals(fieId)){
                sql.append(" case ").append(fieId).append(" when 1 then '是' when 2 then '不是' end ").append(fieId);
            } else {
                sql.append(fieId);
            }
            if ((i+1) != fieIds.size()){
                sql.append(", ");
            } else {
                sql.append(" from company_info where 1=1");
            }
        }

        if (ids!=null&&ids.size()>0){
            sql.append(" and id in (");
            StringJoiner sj =new StringJoiner(",");
            for (Integer id : ids) {
                sj.add(id.toString());
            }
            sql.append(sj.toString()).append(")");
        }
        if (!StringUtils.isNullOrEmpty(exportParam.getName())){
            sql.append(" and name like \"%").append(exportParam.getName()).append("%\"");
        }
        if (exportParam.getCreateDate()!=null){
            Date createDate = exportParam.getCreateDate();
            String befor = DateFormatUtils.format(createDate, "yyyy-MM-dd");
            Date date = DateUtils.addDays(createDate, 1);
            String after = DateFormatUtils.format(date, "yyyy-MM-dd");
            sql.append(" and create_date >=").append(befor).append(" and create_date <=").append(after);
        }
        if (exportParam.getOfficialTime()!=null){
            Date officialTime = exportParam.getOfficialTime();
            String befor = DateFormatUtils.format(officialTime, "yyyy-MM-dd");
            Date date = DateUtils.addDays(officialTime, 1);
            String after = DateFormatUtils.format(date, "yyyy-MM-dd");
            sql.append(" and official_time >=").append(befor).append(" and official_time <=").append(after);
        }
        System.out.println(sql.toString());
        List<Object> mapList = this.selectList(sql.toString());
        // List<Map<String, Object>> maps = this.selectData(sql.toString());

        return mapList;
    }

    @Override
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response, String fileName, String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        //为了解决中文名称乱码问题
        // String fname = CommonFileSystemService.processFileName(request, fileName);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String filePath = path + fileName;
        byte[] body;
//        if ("pro".equals(activeProfile)) {
//            body = new AliFileUtils().dowloadFile(filePath, fileParamConstant);
//        } else {
        body = FileUtils.readFileToByteArray(new File(filePath));
//        }
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
    }

    public List<Object> selectList(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        List<Object> list = query.getResultList();
        return list;
    }

    public List<Map<String, Object>> selectData(String dataSql) {
        Query query = entityManager.createNativeQuery(dataSql);
        query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = query.getResultList();
        return list;
    }

    public List<Object> getListData(List<String> fieIds) {

        StringBuffer sql = new StringBuffer("select ");
        for (int i = 0; i < fieIds.size(); i++) {
            sql.append(fieIds.get(i));
            if ((i + 1)!=fieIds.size()) {
                sql.append(", ");
            } else {
                sql.append(" from company_info");
            }
        }
        System.out.println(sql.toString());
        List<Object> mapList = this.selectList(sql.toString());
        // List<Map<String, Object>> maps = this.selectData(sql.toString());

        return mapList;
    }

}