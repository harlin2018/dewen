package com.dewen.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CompanyInfo;
import com.dewen.project.repository.CompanyInfoRepository;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import com.mysql.jdbc.StringUtils;
import org.springframework.data.jpa.domain.Specification;
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

import javax.persistence.criteria.Predicate;


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
        CommonUser.setEnabled(1);
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
            Specification<CommonUser> specification = (root, criteriaQuery, criteriaBuilder) -> {

                List<Predicate> predicateAndList = new ArrayList<Predicate>();
                if (CommonUser.getId()!=null) {
                    predicateAndList.add(criteriaBuilder.like(root.get("id"), "%" + CommonUser.getId() + "%"));
                }
                if (!StringUtils.isNullOrEmpty(CommonUser.getLoginName())) {
                    predicateAndList.add(criteriaBuilder.like(root.get("loginName"), "%" + CommonUser.getLoginName() + "%"));
                }
                if(!StringUtils.isNullOrEmpty(CommonUser.getUserName())){
                    predicateAndList.add(criteriaBuilder.like(root.get("userName"), "%" + CommonUser.getUserName() + "%"));
                }
                if (predicateAndList.size() > 0) {
                    return criteriaQuery.where(criteriaBuilder.and(predicateAndList.toArray(new Predicate[predicateAndList.size() - 1]))).getRestriction();
                }
                return criteriaQuery.getRestriction();
            };
            CommonUserPages = CommonUserRepository.findAll(specification, pageable);
        }

        return CommonUserPages;
    }

    @Override
    public CommonUser getUser(String loginName, String hashPassword) {
        return CommonUserRepository.findByLoginNameAndHashPassword(loginName, hashPassword);
    }

    @Override
    @Transactional(value = "transactionManager")
    public int approvalUser(Integer id, Integer status) {
        Optional<CommonUser> CommonUser = CommonUserRepository.findById(id);
        if (CommonUser.isPresent()) {
            CommonUser.get().setStatus(String.valueOf(status));
            CommonUserRepository.save(CommonUser.get());
            return Constants.RETURN_STATUS_SUCCESS;
        }
        return Constants.RETURN_STATUS_FAIL;
    }
}