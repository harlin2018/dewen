package com.dewen.project.domain.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title:</p>
 * <p>Description:BaseResponse</p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: PCCW</p>
 *
 * @author Welge
 * @version 1.0
 * @date 2019/6/20
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password;

    private Integer userId;

    private String userName;

    private String loginName;

    private String weChatOpenid;

    private String token;

    // 拥有的角色
    private List<RoleInfo> roles = new ArrayList<>();
    // 拥有的用户组
    private List<GroupInfo> groups= new ArrayList<>();
    // 拥有的资源权限
    private List<RightInfo> rights= new ArrayList<>();
    // 拥有的Api权限
    private List<RightInfo> apiRights= new ArrayList<>();

    public List<String> getRoleCodes(){
        return roles.stream().map(RoleInfo::getRoleCode).collect(Collectors.toList());
    }

    public List<String> getApiRightUrls(){
        return apiRights.stream().map(RightInfo::getRightUrl).collect(Collectors.toList());
    }

    public List<String> getRightUrls(){
        return rights.stream().map(RightInfo::getRightUrl).collect(Collectors.toList());
    }

    public List<Integer> getRightIds(){
        return rights.stream().map(RightInfo::getRightId).collect(Collectors.toList());
    }

    public List<String> getGroupCodes(){
        return groups.stream().map(GroupInfo::getGroupCode).collect(Collectors.toList());
    }

    public List<String> getRightCode(){
        return rights.stream().map(RightInfo::getRightCode).collect(Collectors.toList());
    }

    public void addRole(Integer roleId, String roleCode) {
        roles.add(new RoleInfo(roleId, roleCode));
    }

    public void addGroup(Integer groupId, String groupCode) {
        groups.add(new GroupInfo(groupId, groupCode));
    }

    public void addRight(Integer rightId, Integer parentId, String rightName, String rightCode,
                         String rightUrl, String sysType, String resourceType, String icon, String component, String rightType, Integer sortNum) {
        rights.add(new RightInfo(rightId, parentId, rightName,
                rightCode, rightUrl, sysType, resourceType, icon, component, rightType, sortNum, null, null));
    }

    public void addApiRight(Integer rightId, Integer parentId, String rightName, String rightCode,
                            String rightUrl, String sysType, String resourceType, String icon, String component, String rightType, Integer sortNum) {
        apiRights.add(new RightInfo(rightId, parentId, rightName,
                rightCode, rightUrl, sysType, resourceType, icon, component, rightType, sortNum, null, null));
    }

//    @Override
//    public String getUsername() {
//        return userName;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoleInfo implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer roleId;

        private String roleCode;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupInfo implements Serializable {

        private static final long serialVersionUID = 1L;
        private Integer groupId;

        private String groupCode;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RightInfo implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer rightId;

        private Integer parentId;

        private String rightName;

        private String rightCode;

        private String rightUrl;

        private String sysType;

        private String resourceType;

        private String icon;

        private String component;

        private String rightType;

        private Integer sortNum;

        private List<RightInfo> children;

        private List<RightInfo> apis;



    }



}
