package com.dewen.project.service.impl;

import java.util.List;
import java.util.Optional;

import com.dewen.project.constants.CompanyInfoEnums;
import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CompanyProduct;
import com.dewen.project.domain.CompanyProject;
import com.dewen.project.domain.CompanyRecord;
import com.dewen.project.domain.CompanySewageWaste;
import com.dewen.project.domain.CompanyWaste;
import com.dewen.project.repository.CompanyProductRepository;
import com.dewen.project.repository.CompanyProjectRepository;
import com.dewen.project.repository.CompanyRecordRepository;
import com.dewen.project.repository.CompanySewageWasteRepository;
import com.dewen.project.repository.CompanyWasteRepository;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import org.springframework.beans.BeanUtils;
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
                newCompanyProduct.setCompanyId(companyInfo);
                BeanUtils.copyProperties(companyProduct, newCompanyProduct);
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
                companyRecordRepository.save(newCompanyRecord);
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
                companyRecordRepository.save(newCompanyRecord);
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
                    newCompanyProduct.setCompanyId(companyInfo);
                    BeanUtils.copyProperties(companyProduct, newCompanyProduct);
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
                    companyRecordRepository.save(newCompanyRecord);
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
                    companyRecordRepository.save(newCompanyRecord);
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
        Optional<CompanyInfo >  CompanyInfo = CompanyInfoRepository.findById(id);
        if(CompanyInfo.isPresent()){
            return CompanyInfo.get();
        }else{
            return null;
        }

        //return CompanyInfoRepository.findOne(id);
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
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CompanyInfo> example = Example.of(CompanyInfo, matcher);
            CompanyInfoPages  = CompanyInfoRepository.findAll(example, pageable);
        }

        return CompanyInfoPages;
    }
}