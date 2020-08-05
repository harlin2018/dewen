package com.dewen.project.service.impl;

import java.util.Optional;

import com.dewen.project.constants.Constants;
import com.dewen.project.repository.CompanyRecordRepository;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dewen.project.domain.CustomerNotice;
import com.dewen.project.repository.CustomerNoticeRepository;
import com.dewen.project.service.ICustomerNoticeService;

/**
 * customer_notice
 * <p>Description: CustomerNotice ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-08-04
 */
@Service
@Slf4j
public class CustomerNoticeService implements ICustomerNoticeService {

    @Autowired
    private CustomerNoticeRepository CustomerNoticeRepository;
    @Autowired
    private CustomerNoticeRepository customerNoticeRepository;

    @Override
    public int noReadCount() {
        return customerNoticeRepository.noReadCount();
    }

    @Override
    @Transactional(value = "transactionManager")
    public Integer cleanNoRead() {
        customerNoticeRepository.updateIsRead();
        return Constants.RETURN_STATUS_SUCCESS;
    }


    @Override
    @Transactional(value = "transactionManager")
    public int createCustomerNotice(CustomerNotice CustomerNotice) {
        queryFk(CustomerNotice);
        CustomerNoticeRepository.save(CustomerNotice);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCustomerNotice(CustomerNotice CustomerNotice,Integer id) {
        queryFk(CustomerNotice);
        Optional<CustomerNotice> CustomerNoticeRes= CustomerNoticeRepository.findById(id);
        if(CustomerNoticeRes.isPresent()){
            CustomerNotice = NullAwareBeanUtilsBean.copyExculdeList(CustomerNoticeRes.get(), CustomerNotice);
            CustomerNoticeRepository.save(CustomerNotice);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CustomerNotice
     */
    private void queryFk(CustomerNotice CustomerNotice) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCustomerNotice(Integer id) {
        Optional<CustomerNotice> CustomerNotice= CustomerNoticeRepository.findById(id);
        if(CustomerNotice.isPresent()){
            CustomerNoticeRepository.deleteById(id);
            Optional<CustomerNotice> CustomerNoticeRes= CustomerNoticeRepository.findById(id);
            if(CustomerNoticeRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CustomerNotice findById(Integer id) {
        Optional<CustomerNotice >  CustomerNotice = CustomerNoticeRepository.findById(id);
        if(CustomerNotice.isPresent()){
            return CustomerNotice.get();
        }else{
            return null;
        }

        //return CustomerNoticeRepository.findOne(id);
    }

    @Override
    public Page<CustomerNotice> list(CustomerNotice CustomerNotice,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CustomerNotice> CustomerNoticePages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CustomerNotice == null){
            CustomerNoticePages = CustomerNoticeRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CustomerNotice> example = Example.of(CustomerNotice, matcher);
            CustomerNoticePages  = CustomerNoticeRepository.findAll(example, pageable);
        }
//        CustomerNoticePages.getContent().forEach(customerNotice -> {
//            customerNotice.setCompanyRecord(null);
//        });
        return CustomerNoticePages;
    }

}