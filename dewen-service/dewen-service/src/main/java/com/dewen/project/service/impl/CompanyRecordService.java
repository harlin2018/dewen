package com.dewen.project.service.impl;

import java.util.Optional;

import com.dewen.project.constants.Constants;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dewen.project.domain.CompanyRecord;
import com.dewen.project.repository.CompanyRecordRepository;
import com.dewen.project.service.ICompanyRecordService;

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

    @Override
    @Transactional(value = "transactionManager")
    public int createCompanyRecord(CompanyRecord CompanyRecord) {
        queryFk(CompanyRecord);
        CompanyRecordRepository.save(CompanyRecord);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCompanyRecord(CompanyRecord CompanyRecord,Integer id) {
        queryFk(CompanyRecord);
        Optional<CompanyRecord> CompanyRecordRes= CompanyRecordRepository.findById(id);
        if(CompanyRecordRes.isPresent()){
            CompanyRecord = NullAwareBeanUtilsBean.copyExculdeList(CompanyRecordRes.get(), CompanyRecord);
            CompanyRecordRepository.save(CompanyRecord);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CompanyRecord
     */
    private void queryFk(CompanyRecord CompanyRecord) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCompanyRecord(Integer id) {
        Optional<CompanyRecord> CompanyRecord= CompanyRecordRepository.findById(id);
        if(CompanyRecord.isPresent()){
            CompanyRecordRepository.deleteById(id);
            Optional<CompanyRecord> CompanyRecordRes= CompanyRecordRepository.findById(id);
            if(CompanyRecordRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CompanyRecord findById(Integer id) {
        Optional<CompanyRecord >  CompanyRecord = CompanyRecordRepository.findById(id);
        if(CompanyRecord.isPresent()){
            return CompanyRecord.get();
        }else{
            return null;
        }

        //return CompanyRecordRepository.findOne(id);
    }

    @Override
    public Page<CompanyRecord> list(CompanyRecord CompanyRecord,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CompanyRecord> CompanyRecordPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CompanyRecord == null){
            CompanyRecordPages = CompanyRecordRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CompanyRecord> example = Example.of(CompanyRecord, matcher);
            CompanyRecordPages  = CompanyRecordRepository.findAll(example, pageable);
        }

        return CompanyRecordPages;
    }
}