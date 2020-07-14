package com.dewen.project.service.impl;

import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import com.dewen.project.domain.CommonUser;
import com.dewen.project.repository.CommonUserRepository;
import com.dewen.project.service.ICommonUserService;


/**
 * common_user
 * <p>Description: CommonUser ServiceImpl  /p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: PCCW</p>
 *
 * @author Flame.Lai
 * @version 1.0
 * @date 2020-07-13
 */
@Service
public class CommonUserService implements ICommonUserService {

    @Autowired
    private CommonUserRepository CommonUserRepository;

    @Override
    @Transactional(value = "transactionManager")
    public int createCommonUser(CommonUser CommonUser) {
        queryFk(CommonUser);
        CommonUserRepository.save(CommonUser);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCommonUser(CommonUser CommonUser,Integer id) {
        queryFk(CommonUser);
        Optional<CommonUser> CommonUserRes= CommonUserRepository.findById(id);
        if(CommonUserRes.isPresent()){
            CommonUser = NullAwareBeanUtilsBean.copyExculdeList(CommonUserRes.get(), CommonUser);
            CommonUserRepository.save(CommonUser);
            return Constants.RETURN_STATUS_SUCCESS;
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    /**
     * 处理外键对象
     * @param CommonUser
     */
    private void queryFk(CommonUser CommonUser) {

    }


    @Override
    @Transactional(value = "transactionManager")
    public int deleteCommonUser(Integer id) {
        Optional<CommonUser> CommonUser= CommonUserRepository.findById(id);
        if(CommonUser.isPresent()){
            CommonUserRepository.deleteById(id);
            Optional<CommonUser> CommonUserRes= CommonUserRepository.findById(id);
            if(CommonUserRes.isPresent()){
                return Constants.RETURN_STATUS_FAIL;
            }else{
                return Constants.RETURN_STATUS_SUCCESS;
            }
        }else{
            return Constants.RETURN_STATUS_FAIL;
        }

    }

    @Override
    public CommonUser findById(Integer id) {
        Optional<CommonUser >  CommonUser = CommonUserRepository.findById(id);
        if(CommonUser.isPresent()){
            return CommonUser.get();
        }else{
            return null;
        }

        //return CommonUserRepository.findOne(id);
    }

    @Override
    public Page<CommonUser> list(CommonUser CommonUser,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CommonUser> CommonUserPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CommonUser == null){
            CommonUserPages = CommonUserRepository.findAll(pageable);
        }else{
            //create matcher ,if need ,please modify here
            ExampleMatcher matcher = ExampleMatcher.matchingAll();
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
            matcher = matcher.withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
            //create instant
            Example<CommonUser> example = Example.of(CommonUser, matcher);
            CommonUserPages  = CommonUserRepository.findAll(example, pageable);
        }

        return CommonUserPages;
    }

    @Override
    public CommonUser getUser(String loginName, String hashPassword) {
        return CommonUserRepository.findByLoginNameAndHashPassword(loginName, hashPassword);
    }
}