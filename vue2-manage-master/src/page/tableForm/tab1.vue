<template>
    <el-form class="main_form" :model="mainForm" ref="mainForm">
        <table class="my_table_form">
            <tr>
                <td>单位名称010</td>
                <td colspan="3">
                    <el-form-item prop="name" :rules="[rule.required]">
                        <el-input v-model="mainForm.name"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>单位地址</td>
                <td colspan="3">
                    <el-form-item prop="address" :rules="[rule.required]">
                        <el-input v-model="mainForm.address"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>法定代表人</td>
                <td>
                    <el-form-item prop="legalRepresentative" :rules="[rule.required]">
                        <el-input v-model="mainForm.legalRepresentative"></el-input>
                    </el-form-item>
                </td>
                <td>组织机构代码</td>
                <td>
                    <el-form-item prop="organizationCode" :rules="[rule.required]">
                        <el-input v-model="mainForm.organizationCode"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>环保负责人</td>
                <td>
                    <el-form-item prop="environmentalProtectionOfficer" :rules="[rule.required]">
                        <el-input v-model="mainForm.environmentalProtectionOfficer"></el-input>
                    </el-form-item>
                </td>
                <td>联系电话</td>
                <td>
                    <el-form-item prop="contactNumber" :rules="[rule.required]">
                        <el-input v-model="mainForm.contactNumber"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>行业类别</td>
                <td>
                    <el-form-item prop="industryCategory" :rules="[rule.required]">
                        <el-input v-model="mainForm.industryCategory"></el-input>
                    </el-form-item>
                </td>
                <td>行业代码</td>
                <td>
                    <el-form-item prop="industryCode" :rules="[rule.required]">
                        <el-input v-model="mainForm.industryCode"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>行业主管部门</td>
                <td>
                    <el-form-item prop="industryDept" :rules="[rule.required]">
                        <el-input v-model="mainForm.industryDept"></el-input>
                    </el-form-item>
                </td>
                <td>建成时间</td>
                <td>
                    <el-form-item prop="completionDate" :rules="[rule.required]">
                        <el-date-picker
                            v-model="mainForm.completionDate"
                            type="date">
                        </el-date-picker>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>固定资产（万元）</td>
                <td>
                    <el-form-item prop="fixedAssets" :rules="[rule.required]">
                        <el-input v-model="mainForm.fixedAssets"></el-input>
                    </el-form-item>
                </td>
                <td>环保设施固定资产（万元）</td>
                <td>
                    <el-form-item prop="envirProtFixedAssets" :rules="[rule.required]">
                        <el-input v-model="mainForm.envirProtFixedAssets"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>企业规模</td>
                <td>
                    <el-form-item prop="enterpriseSize" :rules="[rule.required]">
                        <el-radio-group v-model="mainForm.enterpriseSize">
                            <el-radio :label="1">大型</el-radio>
                            <el-radio :label="2">中型</el-radio>
                            <el-radio :label="3">小型</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </td>
                <td>污染源管理级别</td>
                <td>
                    <el-form-item prop="pollutionSourceManagementLevel" :rules="[rule.required]">
                        <el-radio-group v-model="mainForm.pollutionSourceManagementLevel">
                            <el-radio :label="1">国控</el-radio>
                            <el-radio :label="2">省控</el-radio>
                            <el-radio :label="3">市控</el-radio>
                            <el-radio :label="4">区控</el-radio>
                            <el-radio :label="5">一般</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>企业在职人数</td>
                <td>
                    <el-form-item prop="numberEmployees" :rules="[rule.required]">
                        <el-input v-model="mainForm.numberEmployees"></el-input>
                    </el-form-item>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>环评</td>
                <td>
                    <el-form-item prop="eia" :rules="[rule.required]">
                        <el-select v-model="mainForm.eia">
                            <el-option label="报告书" value="1"></el-option>
                            <el-option label="报告表" value="2"></el-option>
                            <el-option label="无" value="3"></el-option>
                        </el-select>
                    </el-form-item>
                </td>
                <td>环评批复</td>
                <td>
                    <el-form-item prop="officialReply" :rules="[rule.required]">
                        <el-radio v-model="mainForm.officialReply" label="1">有</el-radio>
                        <el-radio v-model="mainForm.officialReply" label="2">无</el-radio>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>环评时间</td>
                <td>
                    <el-form-item prop="officialTime" :rules="[rule.required]">
                        <el-date-picker
                            v-model="mainForm.officialTime"
                            type="date">
                        </el-date-picker>
                    </el-form-item>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td class="row_center" colspan="4">主要产品生产情况</td>
            </tr>
            <tr>
                <td>产品名称</td>
                <td>产品产量（计量单位）</td>
                <td>主要原辅材料名称</td>
                <td class="row_add">
                    主要污染物
                    <span class="td_btn" @click="addRow('companyProductList')"><i class="iconfont icon-add1"></i></span>
                </td>
            </tr>
            <tr v-for="(item,index) in mainForm.companyProductList" :key="item.keyId">
                <td>
                    <el-form-item :prop="'companyProductList.'+index+'.name'" :rules="[rule.required]">
                        <el-input v-model="item.name"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'companyProductList.'+index+'.unit'" :rules="[rule.required]">
                        <el-input v-model="item.unit"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'companyProductList.'+index+'.mainMaterialsName'" :rules="[rule.required]">
                        <el-input v-model="item.mainMaterialsName"></el-input>
                    </el-form-item>
                </td>
                <td class="row_add">
                    <el-form-item :prop="'companyProductList.'+index+'.majorPollutants'" :rules="[rule.required]">
                        <el-input v-model="item.majorPollutants"></el-input>
                    </el-form-item>
                    <span class="td_btn" @click="removeRow('companyProductList',index)"><i class="iconfont icon-jian"></i></span>
                </td>
            </tr>
            <tr>
                <td colspan="4">生产工艺描述</td>
            </tr>
            <tr>
                <td colspan="4">
                    <el-form-item prop="breaks" :rules="[rule.required]">
                        <el-input type="textarea" rows="6" v-model="mainForm.breaks"></el-input>
                    </el-form-item>
                </td>
            </tr>
        </table>
    </el-form>
</template>

<script>

import {mixin} from './mixin'

export default {
    name:'tab1',
    mixins:[mixin],
    props:{
        mainForm:{
            type:Object,
            default(){
                return {

                }
            }
        }
    },
    data(){
        return {
            title:'基本资料单元'
        }
    },
    methods:{
    }
}

</script>

<style>

</style>
