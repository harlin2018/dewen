package com.dewen.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonModelFile;
import com.dewen.project.repository.CommonFileSystemRepository;
import com.dewen.project.repository.CommonModelFileRepository;
import com.dewen.project.repository.CompanyInfoRepository;
import com.mysql.jdbc.StringUtils;
import com.dewen.project.repository.CustomerNoticeRepository;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dewen.project.domain.CompanyRecord;
import com.dewen.project.repository.CompanyRecordRepository;
import com.dewen.project.service.ICompanyRecordService;

import javax.persistence.criteria.Predicate;

/**
 * 记录类型表
 * <p>Description: CompanyRecord ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class CompanyRecordService implements ICompanyRecordService {

    @Autowired
    private CompanyRecordRepository CompanyRecordRepository;
    @Autowired
    private CommonFileSystemRepository commonFileSystemRepository;
    @Autowired
    private CustomerNoticeRepository customerNoticeRepository;
    @Autowired
    private CommonModelFileRepository commonModelFileRepository;
    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCompanyRecord(CompanyRecord CompanyRecord) {
        queryFk(CompanyRecord);
        CompanyRecord.setStatus("0");
        CompanyRecordRepository.save(CompanyRecord);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCompanyRecord(CompanyRecord CompanyRecord, Integer id) {
        queryFk(CompanyRecord);
        Optional<CompanyRecord> CompanyRecordRes = CompanyRecordRepository.findById(id);
        if (CompanyRecordRes.isPresent()) {
            CompanyRecord = NullAwareBeanUtilsBean.copyExculdeList(CompanyRecordRes.get(), CompanyRecord);
            CompanyRecordRepository.save(CompanyRecord);
            return Constants.RETURN_STATUS_SUCCESS;
        } else {
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     *
     * @param CompanyRecord
     */
    private void queryFk(CompanyRecord CompanyRecord) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCompanyRecord(Integer id) {
        Optional<CompanyRecord> CompanyRecord = CompanyRecordRepository.findById(id);
        if (CompanyRecord.isPresent()) {
            // 删除消息记录
            customerNoticeRepository.deleteByCompanyRecord(CompanyRecord.get());
            CompanyRecordRepository.deleteById(id);
            Optional<CompanyRecord> CompanyRecordRes = CompanyRecordRepository.findById(id);
            if (CompanyRecordRes.isPresent()) {
                return Constants.RETURN_STATUS_FAIL;
            } else {
                return Constants.RETURN_STATUS_SUCCESS;
            }
        } else {
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CompanyRecord findById(Integer id) {
        Optional<CompanyRecord> CompanyRecord = CompanyRecordRepository.findById(id);
        if (CompanyRecord.isPresent()) {
            return CompanyRecord.get();
        } else {
            return null;
        }

        //return CompanyRecordRepository.findOne(id);
    }

    @Override
    public Page<CompanyRecord> list(CompanyRecord CompanyRecord, int pageNumber, int pageSize, String sorts) {

        //add sorts to query
        Page<CompanyRecord> CompanyRecordPages = null;
        //Pageable
        Pageable pageable = PageUtils.pageable(pageNumber, pageSize, sorts);

        if (CompanyRecord==null) {
            CompanyRecordPages = CompanyRecordRepository.findAll(pageable);
        } else {
            Specification<CompanyRecord> specification = (root, criteriaQuery, criteriaBuilder) -> {

                List<Predicate> predicateAndList = new ArrayList<Predicate>();
                if (!StringUtils.isNullOrEmpty(CompanyRecord.getCompleteContent())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("completeContent"), "%" +CompanyRecord.getCompleteContent() + "%"));
                }
                if (CompanyRecord.getCompanyId()!=null && CompanyRecord.getCompanyId().getId() != null ) {
                    predicateAndList.add(criteriaBuilder.equal(root.get("companyId"), companyInfoRepository.findById( CompanyRecord.getCompanyId().getId()).get()));
                }
                if (!StringUtils.isNullOrEmpty(CompanyRecord.getRecordType())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("recordType"), "%" +CompanyRecord.getRecordType() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CompanyRecord.getContent())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("content"), "%" +CompanyRecord.getContent() + "%"));
                }
                if (predicateAndList.size() > 0) {
                    return criteriaQuery.where(criteriaBuilder.and(predicateAndList.toArray(new Predicate[predicateAndList.size() - 1]))).getRestriction();
                }
                return criteriaQuery.getRestriction();
            };

            CompanyRecordPages = CompanyRecordRepository.findAll(specification, pageable);
        }
        CompanyRecordPages.getContent().forEach(companyRecord -> {
            companyRecord.setFileIdList(commonModelFileRepository.findAllByCompanyRecord(companyRecord)
                    .stream().map(CommonModelFile::getCompanyFileId).collect(Collectors.toList()));
        });
        return CompanyRecordPages;
    }

    @Override
    public int approvalData(Integer id, String completeContent, Integer completeFileId) {
        Optional<CompanyRecord> companyRecord = CompanyRecordRepository.findById(id);
        if (!companyRecord.isPresent()) {
            return Constants.RETURN_STATUS_FAIL;
        }
        companyRecord.get().setCompleteDate(new Date());
        companyRecord.get().setCompleteContent(StringUtils.isNullOrEmpty(completeContent)?"":completeContent);
        companyRecord.get().setCompleteFileId(completeFileId==null?null:commonFileSystemRepository.findById(completeFileId).get());
        companyRecord.get().setStatus("1");
        CompanyRecordRepository.save(companyRecord.get());
        return Constants.RETURN_STATUS_SUCCESS;
    }
}