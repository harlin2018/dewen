package com.dewen.project.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dewen.project.constants.Constants;
import com.dewen.project.domain.CommonRight;
import com.dewen.project.domain.CommonRole;
import com.dewen.project.domain.CommonUserRoleRelationship;
import com.dewen.project.domain.support.UserInfo;
import com.dewen.project.repository.CommonRightRepository;
import com.dewen.project.repository.CommonRoleRepository;
import com.dewen.project.repository.CommonUserRepository;
import com.dewen.project.repository.CommonUserRoleRelationshipRepository;
import com.dewen.project.utils.NullAwareBeanUtilsBean;
import com.dewen.project.utils.PageUtils;
import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dewen.project.domain.CommonUser;
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
@Slf4j
public class CommonUserService implements ICommonUserService {

    @Autowired
    private CommonUserRepository commonUserRepository;
    @Autowired
    private CommonRoleRepository commonRoleRepository;
    @Autowired
    private CommonUserRoleRelationshipRepository commonUserRoleRelationshipRepository;
    @Autowired
    private CommonRightRepository commonRightRepository;
    
    @Override
    @Transactional(value = "transactionManager")
    public int createCommonUser(CommonUser CommonUser) {
        queryFk(CommonUser);
        CommonUser.setEnabled(Constants.ENABLE);
        commonUserRepository.save(CommonUser);
        return Constants.RETURN_STATUS_SUCCESS;
    }

    @Override
    @Transactional(value = "transactionManager")
    public int updateCommonUser(CommonUser CommonUser,Integer id) {
        queryFk(CommonUser);
        Optional<CommonUser> CommonUserRes= commonUserRepository.findById(id);
        if(CommonUserRes.isPresent()){
            CommonUser = NullAwareBeanUtilsBean.copyExculdeList(CommonUserRes.get(), CommonUser);
            commonUserRepository.save(CommonUser);
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
        Optional<CommonUser> CommonUser= commonUserRepository.findById(id);
        if(CommonUser.isPresent()){
            commonUserRepository.deleteById(id);
            Optional<CommonUser> CommonUserRes= commonUserRepository.findById(id);
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
        Optional<CommonUser >  CommonUser = commonUserRepository.findById(id);
        if(CommonUser.isPresent()){
            return CommonUser.get();
        }else{
            return null;
        }

        //return commonUserRepository.findOne(id);
    }

    @Override
    public Page<CommonUser> list(CommonUser CommonUser,int pageNumber,int pageSize,String sorts)  {

        //add sorts to query
        Page<CommonUser> CommonUserPages =null;
        //Pageable
        Pageable pageable =  PageUtils.pageable(pageNumber,pageSize,sorts);

        if(CommonUser == null){
            CommonUserPages = commonUserRepository.findAll(pageable);
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
            CommonUserPages = commonUserRepository.findAll(specification, pageable);
        }

        return CommonUserPages;
    }

    @Override
    public CommonUser getUser(String loginName, String hashPassword) {
        return commonUserRepository.findByLoginNameAndHashPasswordAndStatus(loginName, hashPassword, "1");
    }

    @Override
    @Transactional(value = "transactionManager")
    public int approvalUser(Integer id, Integer status) {
        Optional<CommonUser> CommonUser = commonUserRepository.findById(id);
        if (CommonUser.isPresent()) {
            CommonUser.get().setStatus(String.valueOf(status));
            commonUserRepository.save(CommonUser.get());
            return Constants.RETURN_STATUS_SUCCESS;
        }
        return Constants.RETURN_STATUS_FAIL;
    }


    @Override
    public boolean assignRole(Integer userId, List<Integer> roleIds) {
        Optional<CommonUser> commonUser = commonUserRepository.findById(userId);
        if (!commonUser.isPresent()) {
            log.error("用户({})不存在", userId);
            return false;
        }

        List<CommonUserRoleRelationship> commonUserRoleRelationshipAdds = new ArrayList<>();
        List<CommonUserRoleRelationship> commonUserRoleRelationshipDeletes = new ArrayList<>();
        List<CommonUserRoleRelationship> commonUserRoleRelationships = commonUser.get().getCommonUserRoleRelationship();
        // 找出要删除的，和要添加的
        for (int i = commonUserRoleRelationships.size() - 1; i >= 0; i--) {
            CommonUserRoleRelationship commonUserRoleRelationship = commonUserRoleRelationships.get(i);
            Integer roleId = commonUserRoleRelationship.getCommonRole().getId();
            if (!roleIds.contains(roleId)) {
                commonUserRoleRelationshipDeletes.add(commonUserRoleRelationship);
                commonUserRoleRelationships.remove(commonUserRoleRelationship);
            } else {
                roleIds.remove(roleId);
            }
        }
        // 需要添加的
        CommonUserRoleRelationship commonRoleRightRelationship;
        for (Integer roleId : roleIds) {
            Optional<CommonRole> role = commonRoleRepository.findById(roleId);
            if (!role.isPresent()) {
                log.error("角色（{}）不存在", roleId);
                return false;
            }
            commonRoleRightRelationship = new CommonUserRoleRelationship();
            commonRoleRightRelationship.setCommonRole(role.get());
            commonRoleRightRelationship.setCommonUser(commonUser.get());
            commonUserRoleRelationshipAdds.add(commonRoleRightRelationship);
        }

        if (CollectionUtils.isNotEmpty(commonUserRoleRelationshipDeletes)) {
            commonUserRoleRelationshipRepository.deleteAll(commonUserRoleRelationshipDeletes);
        }

        if (CollectionUtils.isNotEmpty(commonUserRoleRelationshipAdds)) {
            commonUserRoleRelationshipRepository.saveAll(commonUserRoleRelationshipAdds);
        }
        return true;
    }

    @Override
    public List<UserInfo.RightInfo> findRight(Integer userId) {
        List<CommonRight> rightList =  commonRightRepository.findAllByUserId(userId);
        List<UserInfo.RightInfo> rightInfos = new ArrayList<>();
        for (CommonRight commonRight : rightList) {
            UserInfo.RightInfo rightInfo= new UserInfo.RightInfo();
            BeanUtils.copyProperties(commonRight, rightInfo);
            rightInfo.setRightId(commonRight.getId());
            rightInfos.add(rightInfo);
        }
        return rightInfos;
    }


//    /**
//     * 查询所有菜单
//     *
//     * @return
//     */
//    public List<Map<String, Object>> listAll() {
//        // 查询出所有的一级菜单[pid=""为一级菜单]
//        List<CommonRight> treeRight = commonRightRepository.selectParent();
//        List<Map<String, Object>> list = new ArrayList<>();
//        if (treeRight.size() > 0) {
//            for (CommonRight commonRight : treeRight) {
//                Map<String, Object> map = new LinkedHashMap<>();
//                map.put
//
//            }
//            for (CommonCategories commonCategories : treeCommonCategories) {
//                Map<String, Object> map = new LinkedHashMap<>();
//                map.put("id", commonCategories.getId());
//                map.put("guid", commonCategories.getGuid());
//                map.put("name", commonCategories.getName());
//                map.put("level", commonCategories.getLevel());
//                map.put("parent", commonCategories.getParent());
//                map.put("children", getChildren(commonCategories.getGuid()));
//                list.add(map);
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 递归
//     *
//     * @param id
//     * @return
//     */
//    private List<Object> getChildren(String id) {
//        List<Object> list = new ArrayList<>();
//        List<CommonRight> treeRight = commonRightRepository.selectByPid(id);
//        for (CommonCategories commonCategories : treeCommonCategories) {
//            Map<String, Object> map = new LinkedHashMap<>();
//            map.put("id", commonCategories.getId());
//            map.put("guid", commonCategories.getGuid());
//            map.put("name", commonCategories.getName());
//            map.put("level", commonCategories.getLevel());
//            map.put("parent", commonCategories.getParent());
//            List childreList = getChildren(commonCategories.getGuid());
//            if (childreList.size() > 0) {
//                map.put("children", childreList);
//            }
//            list.add(map);
//        }
//        return list;
//    }
}