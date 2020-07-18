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

import com.dewen.project.domain.CommonRight;
import com.dewen.project.repository.CommonRightRepository;
import com.dewen.project.service.ICommonRightService;

/**
 * common_right
 * <p>Description: CommonRight ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-18
 */
@Service
public class CommonRightService implements ICommonRightService {

    @Autowired
    private CommonRightRepository CommonRightRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCommonRight(CommonRight CommonRight) {
        queryFk(CommonRight);
        CommonRightRepository.save(CommonRight);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCommonRight(CommonRight CommonRight,Integer id) {
        queryFk(CommonRight);
        Optional<CommonRight> CommonRightRes= CommonRightRepository.findById(id);
        if(CommonRightRes.isPresent()){
            CommonRight = NullAwareBeanUtilsBean.copyExculdeList(CommonRightRes.get(), CommonRight);
            CommonRightRepository.save(CommonRight);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CommonRight
     */
    private void queryFk(CommonRight CommonRight) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCommonRight(Integer id) {
        Optional<CommonRight> CommonRight= CommonRightRepository.findById(id);
        if(CommonRight.isPresent()){
            CommonRightRepository.deleteById(id);
            Optional<CommonRight> CommonRightRes= CommonRightRepository.findById(id);
            if(CommonRightRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CommonRight findById(Integer id) {
        Optional<CommonRight >  CommonRight = CommonRightRepository.findById(id);
        if(CommonRight.isPresent()){
            return CommonRight.get();
        }else{
            return null;
        }

        //return CommonRightRepository.findOne(id);
    }

    @Override
    public Page<CommonRight> list(CommonRight CommonRight,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CommonRight> CommonRightPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CommonRight == null){
            CommonRightPages = CommonRightRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CommonRight> example = Example.of(CommonRight, matcher);
            CommonRightPages  = CommonRightRepository.findAll(example, pageable);
        }

        return CommonRightPages;
    }
}