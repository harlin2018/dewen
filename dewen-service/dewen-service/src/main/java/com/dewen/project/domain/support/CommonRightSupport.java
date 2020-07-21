package com.dewen.project.domain.support;

import com.dewen.project.constants.ResourceTypeEnum;
import com.dewen.project.domain.CommonRoleRightRelationship;
import com.dewen.project.domain.DTO.CommonRightDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommonRightSupport {
    private static final Integer ROOT_RIGHT_ID = 0;

    public static List<CommonRightDTO> toTree(List<CommonRightDTO> commonRightDTOList) {
        return toTree(commonRightDTOList, null);
    }

    public static List<CommonRightDTO> toTree(List<CommonRightDTO> commonRightDTOList, Map<Integer, CommonRoleRightRelationship> rightIds){
        List rootList = new ArrayList();

        Map<Integer, CommonRightDTO> hashMap = commonRightDTOList.stream()
                .collect(Collectors.toMap(CommonRightDTO::getId, Function.identity(), (s1, s2) -> s2));

        for (CommonRightDTO commonRightDTO : commonRightDTOList) {
            if (Objects.nonNull(rightIds) && rightIds.keySet().contains(commonRightDTO.getId())) {
                commonRightDTO.setChecked(true);
                commonRightDTO.setRightType(rightIds.get(commonRightDTO.getId()).getRightType());
            }
            if (hashMap.containsKey(commonRightDTO.getParentId()) || Objects.equals(ROOT_RIGHT_ID, commonRightDTO.getParentId())) {
                //hashMap.put(commonRightDTO.getId(), commonRightDTO);
                CommonRightDTO parent = hashMap.get(commonRightDTO.getParentId());
                if (parent == null) {
                    rootList.add(commonRightDTO);
                    continue;
                }
                parent.setChecked(false);
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                if (parent.getApis() == null) {
                    parent.setApis(new ArrayList<>());
                }
                if (Objects.equals(ResourceTypeEnum.Api.getValue(), commonRightDTO.getResourceType())) {
                    parent.getApis().add(commonRightDTO);
                } else {
                    parent.getChildren().add(commonRightDTO);
                }
            }

        }

        return rootList;
    }


//    public static List<UserInfo.RightInfo> toRightInfoTree(List<UserInfo.RightInfo> rightInfos){
//        List<UserInfo.RightInfo> rootList = new ArrayList();
//
//        Map<Integer, UserInfo.RightInfo> hashMap = rightInfos.stream()
//                .collect(Collectors.toMap(UserInfo.RightInfo::getRightId, Function.identity(), (s1, s2) -> s2));
//
//        for (UserInfo.RightInfo rightInfo : rightInfos) {
//            if (hashMap.containsKey(rightInfo.getParentId()) || Objects.equals(ROOT_RIGHT_ID, rightInfo.getParentId())) {
//                UserInfo.RightInfo parent = hashMap.get(rightInfo.getParentId());
//                if (parent == null) {
//                    rootList.add(rightInfo);
//                    continue;
//                }
//                if (parent.getChildren() == null) {
//                    parent.setChildren(new ArrayList<>());
//                }
//                if (parent.getApis() == null) {
//                    parent.setApis(new ArrayList<>());
//                }
//                if (Objects.equals(ResourceTypeEnum.Api.getValue(), rightInfo.getResourceType())) {
//                    parent.getApis().add(rightInfo);
//                } else {
//                    parent.getChildren().add(rightInfo);
//                }
//            }
//
//        }
//
//        return rootList;
//    }
}
