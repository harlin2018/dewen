package com.dewen.project.domain;

import lombok.Data;

import java.util.List;

@Data
public class ExportParam {

    private List<String> fieIds;

    private List<Integer> ids;

    private String path;

    private String title;

}
