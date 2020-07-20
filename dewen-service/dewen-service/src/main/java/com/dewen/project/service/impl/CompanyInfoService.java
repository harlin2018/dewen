package com.dewen.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dewen.project.constants.CompanyInfoEnums;
import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonFileSystem;
import com.dewen.project.domain.CommonModelFile;
import com.dewen.project.domain.CompanyProduct;
import com.dewen.project.domain.CompanyProject;
import com.dewen.project.domain.CompanyRecord;
import com.dewen.project.domain.CompanySewageWaste;
import com.dewen.project.domain.CompanyWaste;
import com.dewen.project.repository.CommonModelFileRepository;
import com.dewen.project.repository.CompanyProductRepository;
import com.dewen.project.repository.CompanyProjectRepository;
import com.dewen.project.repository.CompanyRecordRepository;
import com.dewen.project.repository.CompanySewageWasteRepository;
import com.dewen.project.repository.CompanyWasteRepository;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import com.mysql.jdbc.StringUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.repository.CompanyInfoRepository;
import com.dewen.project.service.ICompanyInfoService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;

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

    @Override
    @Transactional(value = "transactionManager")
    public int createCompanyInfo(CompanyInfo companyInfo) {
        queryFk(companyInfo);
        CompanyInfoRepository.save(companyInfo);
        // 主要生产产品表
        List<CompanyProduct> companyProductList =  companyInfo.getCompanyProductList();
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
        Optional<CompanyInfo> CompanyInfoRes= CompanyInfoRepository.findById(id);
        if(CompanyInfoRes.isPresent()){
            companyInfo = NullAwareBeanUtilsBean.copyExculdeList(CompanyInfoRes.get(), companyInfo);
            CompanyInfoRepository.save(companyInfo);
            // 主要生产产品表
            // 删除原有的资料
            companyProductRepository.deleteByCompanyId(companyInfo);
            List<CompanyProduct> companyProductList =  companyInfo.getCompanyProductList();
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
            companyRecordRepository.deleteByCompanyId(companyInfo);
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
            // 行政执法记录
            List<CompanyRecord> adminRecordList = companyInfo.getAdminRecordList();
            if (adminRecordList!=null) {
                for (CompanyRecord companyRecord : adminRecordList) {
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
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CompanyInfo
     */
    private void queryFk(CompanyInfo CompanyInfo) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCompanyInfo(Integer id) {
        Optional<CompanyInfo> CompanyInfo= CompanyInfoRepository.findById(id);
        if(CompanyInfo.isPresent()){
            CompanyInfoRepository.deleteById(id);
            // 删除原有的资料
            companyProductRepository.deleteByCompanyId(CompanyInfo.get());
            companySewageWasteRepository.deleteByCompanyId(CompanyInfo.get());
            companyRecordRepository.deleteByCompanyId(CompanyInfo.get());
            companyWasteRepository.deleteByCompanyId(CompanyInfo.get());
            companyProjectRepository.deleteByCompanyId(CompanyInfo.get());
            Optional<CompanyInfo> CompanyInfoRes= CompanyInfoRepository.findById(id);
            if(CompanyInfoRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CompanyInfo findById(Integer id) {
        Optional<CompanyInfo>  companyInfoOpt = CompanyInfoRepository.findById(id);
        if(companyInfoOpt.isPresent()){
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
            });
            // 行政执法记录
            companyInfo.setAdminRecordList(companyRecordRepository.findAllByCompanyIdAndRecordType(companyInfo, CompanyInfoEnums.RecordType.adminRecord.getValue())
                    .stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList()));
            companyInfo.getAdminRecordList().forEach(companyRecord -> {
                companyRecord.setFileIdList(commonModelFileRepository.findAllByCompanyRecord(companyRecord)
                        .stream().map(CommonModelFile::getCompanyFileId).collect(Collectors.toList()));
            });
            return companyInfo;
        }else{
            return null;
        }
    }

    @Override
    public Page<CompanyInfo> list(CompanyInfo CompanyInfo,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CompanyInfo> CompanyInfoPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CompanyInfo == null){
            CompanyInfoPages = CompanyInfoRepository.findAll(pageable);
        }else{
            Specification<CompanyInfo> specification = (root, criteriaQuery, criteriaBuilder) -> {

                List<Predicate> predicateAndList = new ArrayList<Predicate>();
                if (CompanyInfo.getId()!=null) {
                    predicateAndList.add(criteriaBuilder.like(root.get("id"), "%" + CompanyInfo.getId() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getName())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("name"), "%" + CompanyInfo.getName() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getAddress())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("address"), "%" + CompanyInfo.getAddress() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyInfo.getBreaks())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("breaks"), "%" + CompanyInfo.getBreaks() + "%"));
                }
                if(!StringUtils.isNullOrEmpty(CompanyInfo.getLegalRepresentative())){
                    predicateAndList.add(criteriaBuilder.like(root.get("legalRepresentative"), "%" + CompanyInfo.getLegalRepresentative() + "%"));
                }
                if(!StringUtils.isNullOrEmpty(CompanyInfo.getEnvironmentalProtectionOfficer())){
                    predicateAndList.add(criteriaBuilder.like(root.get("environmentalProtectionOfficer"), "%" + CompanyInfo.getEnvironmentalProtectionOfficer() + "%"));
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
    public Map<String, List> record() {
        Map<String, List> recordMap = new HashMap<>();
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
        });
        // 行政执法记录
        List<CompanyRecord> adminRecordList = companyRecordRepository.findAllByRecordTypeGroupByContent(CompanyInfoEnums.RecordType.adminRecord.getValue()).stream().peek(it -> it.setCompanyId(null)).collect(Collectors.toList());
        adminRecordList.forEach(companyRecord -> {
            companyRecord.setFileIdList(commonModelFileRepository.findAllByCompanyRecord(companyRecord)
                    .stream().map(CommonModelFile::getCompanyFileId).collect(Collectors.toList()));
        });


        recordMap.put("wasteWaterList", wasteWaterList);
        recordMap.put("wasteGasList", wasteGasList);
        recordMap.put("wasteWaterMonitorList", wasteWaterMonitorList);
        recordMap.put("wasteGasMonitorList", wasteGasMonitorList);
        recordMap.put("inspectRecordList", inspectRecordList);
        recordMap.put("adminRecordList", adminRecordList);



        return recordMap;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object> selectList(String sql){
        Query query = entityManager.createNativeQuery(sql);
        List<Object> list = query.getResultList();
        return list;
    }

    public List<Map<String, Object>> selectData(String dataSql){
        Query query = entityManager.createNativeQuery(dataSql);
        query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = query.getResultList();
        return list;
    }

    public List<Object> getListData(List<String> fieIds){

        StringBuffer sql = new StringBuffer("select ");
        for (int i = 0; i < fieIds.size(); i++) {
            sql.append(fieIds.get(i));
            if ((i+1) != fieIds.size()){
                sql.append(", ");
            } else {
                sql.append(" from company_info");
            }
        }
        System.out.println(sql.toString());
        List<Object> mapList = this.selectList(sql.toString());
        List<Map<String, Object>> maps = this.selectData(sql.toString());

        return mapList;
    }

}