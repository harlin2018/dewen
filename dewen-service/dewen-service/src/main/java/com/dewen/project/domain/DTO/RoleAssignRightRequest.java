package com.dewen.project.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class RoleAssignRightRequest extends BaseRequest {
    @NotNull
    private Integer roleId;
    private List<RoleGrantRightRequest> rights;

    @Data
    static class RoleGrantRightRequest extends BaseRequest {
        private Integer rightId;
        private String rightType;
    }

    @JsonIgnore
    public Map<Integer, String> getRightIds() {
        if (rights == null) {
            return new HashMap<>();
        }
        return rights.stream().collect(Collectors.toMap(RoleGrantRightRequest::getRightId, RoleGrantRightRequest::getRightType));
    }
}
