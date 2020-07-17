package com.dewen.project.constants;

import java.util.HashMap;
import java.util.Map;

public class ExportFieIdConstant {

    public static Map<String, String> fieIds = new HashMap<>();



    public static final String id = "主键";
    public static final String name = "单位名称";
    public static final String address = "单位地址";
    public static final String legal_representative = "法定代表人";
    public static final String organization_code = "组织机构代码";
    public static final String environmental_protection_officer = "环保负责人";
    public static final String contact_number = "联系电话";
    public static final String industry_category = "行业类别";
    public static final String industry_code = "行业代码";
    public static final String industry_dept = "行业主管部门";
    public static final String completion_date = "建成时间";
    public static final String fixed_assets = "固定资产（万元）";
    public static final String envir_prot_fixed_assets = "环保设施固定资产（万元）";
    public static final String enterprise_size = "企业规模：1大型/2中型/3小型；备注：用枚举";
    public static final String pollution_source_management_level = "污染源管理级别：1国控/2市控/3区控/4一般；备注：用枚举";
    public static final String sewage_type = "排污种类：1废水/废气/噪声/固废/其它";
    public static final String create_date = "创建时间";
    public static final String modify_date = "修改时间";
    public static final String general_industrial_solid_waste = "一般工业固体废物";
    public static final String year_production = "年产生量";
    public static final String year_process = "年处理量";
    public static final String disposal_way_remark = "处置方式第三方";
    public static final String disposal_way = "处置方式";
    public static final String main_sound_source_name = "主要声源名称";
    public static final String mmppc = "污染防治的主要措施";
    public static final String sewerage_rain = "雨污分流";
    public static final String row_to_remark = "备注";
    public static final String row_to = "排向";
    public static final String enterprise_pretreatment = "企业预处理";
    public static final String output = "产生量（月平均）";
    public static final String the_sewage_to_remark = "污水排向说明";
    public static final String the_sewage_to = "污水排向";
    public static final String stfds = "生活污水化粪池";
    public static final String life_produced = "生活产生量（月平均）";
    public static final String life_line_to_remark = "生活排向说明";
    public static final String life_line_to = "生活排向";
    public static final String environmental_protection_plan = "环保应急预案情况";
    public static final String emission_permit = "排污许可证";
    public static final String eia_process = "环评工艺";
    public static final String new_eia = "新环评";
    public static final String epep = "环保应急预案情况";
    public static final String supervisory_inspection_enterprise = "监督性检测企业";
    public static final String sicfwwo = "废水监督性检测周期";
    public static final String sicfwwt = "废气监督性检测周期";
    public static final String sicfwws = "土壤监督性检测周期";
    public static final String number_employees = "企业在职人数";
    public static final String eia = "环评";
    public static final String official_reply = "环评批复";
    public static final String official_reply_file_id = "环评批复文件Id";
    public static final String breaks = "生产工艺描述";
    public static final String official_time = "环评时间";
    public static final String mouth_inflow = "月进水量（平均）";
    public static final String swage_generation = "污水产生量";
    public static final String consumption = "消耗量";
    public static final String water_balance = "水平衡";
    public static final String sewerage_rain_remark = "雨污分流备注";

    public static Map<String, String> getFieIds(){
        fieIds.put("id",ExportFieIdConstant.id);
        fieIds.put("name",ExportFieIdConstant.name);
        fieIds.put("address",ExportFieIdConstant.address);
        fieIds.put("legal_representative",ExportFieIdConstant.legal_representative);
        fieIds.put("organization_code",ExportFieIdConstant.organization_code);
        fieIds.put("environmental_protection_officer",ExportFieIdConstant.environmental_protection_officer);
        fieIds.put("contact_number",ExportFieIdConstant.contact_number);
        fieIds.put("industry_category",ExportFieIdConstant.industry_category);
        fieIds.put("industry_code",ExportFieIdConstant.industry_code);
        fieIds.put("industry_dept",ExportFieIdConstant.industry_dept);
        fieIds.put("completion_date",ExportFieIdConstant.completion_date);
        fieIds.put("fixed_assets",ExportFieIdConstant.fixed_assets);
        fieIds.put("envir_prot_fixed_assets",ExportFieIdConstant.envir_prot_fixed_assets);
        fieIds.put("enterprise_size",ExportFieIdConstant.enterprise_size);
        fieIds.put("pollution_source_management_level",ExportFieIdConstant.pollution_source_management_level);
        fieIds.put("sewage_type",ExportFieIdConstant.sewage_type);
        fieIds.put("create_date",ExportFieIdConstant.create_date);
        fieIds.put("modify_date",ExportFieIdConstant.modify_date);
        fieIds.put("general_industrial_solid_waste",ExportFieIdConstant.general_industrial_solid_waste);
        fieIds.put("year_production",ExportFieIdConstant.year_production);
        fieIds.put("year_process",ExportFieIdConstant.year_process);
        fieIds.put("disposal_way_remark",ExportFieIdConstant.disposal_way_remark);
        fieIds.put("disposal_way",ExportFieIdConstant.disposal_way);
        fieIds.put("main_sound_source_name",ExportFieIdConstant.main_sound_source_name);
        fieIds.put("mmppc",ExportFieIdConstant.mmppc);
        fieIds.put("sewerage_rain",ExportFieIdConstant.sewerage_rain);
        fieIds.put("row_to_remark",ExportFieIdConstant.row_to_remark);
        fieIds.put("row_to",ExportFieIdConstant.row_to);
        fieIds.put("enterprise_pretreatment",ExportFieIdConstant.enterprise_pretreatment);
        fieIds.put("output",ExportFieIdConstant.output);
        fieIds.put("the_sewage_to_remark",ExportFieIdConstant.the_sewage_to_remark);
        fieIds.put("the_sewage_to",ExportFieIdConstant.the_sewage_to);
        fieIds.put("stfds",ExportFieIdConstant.stfds);
        fieIds.put("life_produced",ExportFieIdConstant.life_produced);
        fieIds.put("life_line_to_remark",ExportFieIdConstant.life_line_to_remark);
        fieIds.put("life_line_to",ExportFieIdConstant.life_line_to);
        fieIds.put("environmental_protection_plan",ExportFieIdConstant.environmental_protection_plan);
        fieIds.put("emission_permit",ExportFieIdConstant.emission_permit);
        fieIds.put("eia_process",ExportFieIdConstant.eia_process);
        fieIds.put("new_eia",ExportFieIdConstant.new_eia);
        fieIds.put("epep",ExportFieIdConstant.epep);
        fieIds.put("supervisory_inspection_enterprise",ExportFieIdConstant.supervisory_inspection_enterprise);
        fieIds.put("sicfwwo",ExportFieIdConstant.sicfwwo);
        fieIds.put("sicfwwt",ExportFieIdConstant.sicfwwt);
        fieIds.put("sicfwws",ExportFieIdConstant.sicfwws);
        fieIds.put("number_employees",ExportFieIdConstant.number_employees);
        fieIds.put("eia",ExportFieIdConstant.eia);
        fieIds.put("official_reply",ExportFieIdConstant.official_reply);
        fieIds.put("official_reply_file_id",ExportFieIdConstant.official_reply_file_id);
        fieIds.put("breaks",ExportFieIdConstant.breaks);
        fieIds.put("official_time",ExportFieIdConstant.official_time);
        fieIds.put("mouth_inflow",ExportFieIdConstant.mouth_inflow);
        fieIds.put("swage_generation",ExportFieIdConstant.swage_generation);
        fieIds.put("consumption",ExportFieIdConstant.consumption);
        fieIds.put("water_balance",ExportFieIdConstant.water_balance);
        fieIds.put("sewerage_rain_remark",ExportFieIdConstant.sewerage_rain_remark);


        return fieIds;
    }

}
