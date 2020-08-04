<template>
    <el-form class="main_form" :model="mainForm" ref="mainForm" :disabled="disabled">
        <table class="my_table_form">
            <tr>
                <td>排污种类</td>
                <td class="row_center td75">固废</td>
            </tr>
            <tr>
                <td>一般工业固体废物</td>
                <td class="td75">
                    <el-form-item prop="generalIndustrialSolidWaste">
                        <el-input v-model="mainForm.generalIndustrialSolidWaste"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>年产生量</td>
                <td class="td75">
                    <el-form-item prop="yearProduction">
                        <el-input v-model="mainForm.yearProduction"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>年处理量</td>
                <td class="td75">
                    <el-form-item prop="yearProcess">
                        <el-input v-model="mainForm.yearProcess"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>处置方式</td>
                <td class="td75">
                    <ul class="ul_flex_select">
                        <li class="left">
                            <el-form-item prop="disposalWay">
                                <el-select v-model="mainForm.disposalWay">
                                    <el-option label="第三方" value="1"></el-option>
                                    <el-option label="回收" value="2"></el-option>
                                    <el-option label="回用" value="3"></el-option>
                                    <el-option label="垃圾回收站" value="4"></el-option>
                                </el-select>
                            </el-form-item>
                        </li>
                        <li class="right" v-if="mainForm.disposalWay==1">
                            <el-form-item prop="disposalWayRemark">
                                <el-input v-model="mainForm.disposalWayRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>排污种类</td>
                <td class="row_center td75">危废</td>
            </tr>
            <tr v-for="(item,index) in mainForm.companyWasteList" :key="item.keyId">
                <td class="row_table" colspan="2">
                    <table class="my_table_form my_table_inner_row">
                        <tr>
                            <td>工业危险废物名称和类别</td>
                            <td class="td75 row_add">
                                <el-form-item :prop="'companyWasteList.'+index+'.nameType'">
                                    <el-input v-model="item.nameType"></el-input>
                                </el-form-item>
                                <span v-show="!disabled&&index==0" class="td_btn" @click="addRow('companyWasteList')"><i class="iconfont icon-add1"></i></span>
                                <span v-show="!disabled&&index!=0" class="td_btn td_btn_r" @click="removeRow('companyWasteList',index)"><i class="iconfont icon-jian"></i></span>
                            </td>
                        </tr>
                        <tr>
                            <td>年产生量</td>
                            <td class="td75">
                                <el-form-item :prop="'companyWasteList.'+index+'.yearPro'">
                                    <el-input v-model="item.yearPro"></el-input>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>年处理量</td>
                            <td class="td75">
                                <el-form-item :prop="'companyWasteList.'+index+'.yearProcess'">
                                    <el-input v-model="item.yearProcess"></el-input>
                                </el-form-item>
                            </td>
                        </tr>
                        <tr>
                            <td>处置方式</td>
                            <td class="td75">
                                <ul class="ul_flex_select">
                                    <li class="left">
                                        <el-form-item :prop="'companyWasteList.'+index+'.processMethods'">
                                            <el-select v-model="item.processMethods">
                                                <el-option label="第三方" value="1"></el-option>
                                                <el-option label="回收" value="2"></el-option>
                                                <el-option label="回用" value="3"></el-option>
                                                <el-option label="垃圾回收站" value="4"></el-option>
                                            </el-select>
                                        </el-form-item>
                                    </li>
                                    <li class="right" v-if="item.processMethods==1">
                                        <el-form-item :prop="'companyWasteList.'+index+'.processMethodsRemark'">
                                            <el-input v-model="item.processMethodsRemark"></el-input>
                                        </el-form-item>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>排污种类</td>
                <td class="row_center td75">噪声</td>
            </tr>
            <tr>
                <td>主要声源名称</td>
                <td class="td75">
                    <el-form-item prop="mainSoundSourceName">
                        <el-input v-model="mainForm.mainSoundSourceName"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>污染防治的主要措施</td>
                <td class="td75">
                    <el-form-item prop="mmppc">
                        <el-input v-model="mainForm.mmppc"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td class="row_center row_notice" colspan="2">雨水收集</td>
            </tr>
            <tr>
                <td>雨污分流</td>
                <td class="td75">
                    <ul class="ul_flex_radio">
                        <li class="left">
                            <el-form-item prop="sewerageRain">
                                <el-radio-group v-model="mainForm.sewerageRain">
                                    <el-radio label="1">有</el-radio>
                                    <el-radio label="2">无</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </li>
                        <li class="right">
                            <el-form-item prop="sewerageRainRemark">
                                <el-input v-model="mainForm.sewerageRainRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>排向</td>
                <td class="td75">
                    <el-form-item prop="rowTo">
                        <el-select v-model="mainForm.rowTo">
                            <el-option label="三阳河" value="1"></el-option>
                            <el-option label="雷河" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td class="row_center row_notice" colspan="2">生产污水</td>
            </tr>
            <tr>
                <td>企业预处理</td>
                <td class="td75">
                    <ul class="ul_flex_radio">
                        <li class="left">
                            <el-form-item prop="enterprisePretreatment">
                                <el-radio-group v-model="mainForm.enterprisePretreatment">
                                    <el-radio label="1">有</el-radio>
                                    <el-radio label="2">无</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </li>
                        <li class="right">
                            <el-form-item prop="enterprisePretreatmentRemark">
                                <el-input v-model="mainForm.enterprisePretreatmentRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>产生量（月平均）</td>
                <td class="td75">
                    <el-form-item prop="output">
                        <el-input v-model="mainForm.output"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>排向</td>
                <td class="td75">
                    <ul class="ul_flex_select">
                        <li class="left">
                            <el-form-item prop="theSewageTo">
                                <el-select v-model="mainForm.theSewageTo">
                                    <el-option label="纳管（污水处理厂）" value="1"></el-option>
                                    <el-option label="雨水管网" value="2"></el-option>
                                    <el-option label="其他" value="3"></el-option>
                                </el-select>
                            </el-form-item>
                        </li>
                        <li class="right" v-if="mainForm.theSewageTo==3">
                            <el-form-item prop="theSewageToRemark">
                                <el-input v-model="mainForm.theSewageToRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td class="row_center row_notice" colspan="2">生活污水</td>
            </tr>
            <tr>
                <td>化粪池</td>
                <td class="td75">
                    <el-form-item prop="stfds">
                        <el-input v-model="mainForm.stfds"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>产生量（月平均）</td>
                <td class="td75">
                    <el-form-item prop="lifeProduced">
                        <el-input v-model="mainForm.lifeProduced"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>排向</td>
                <td class="td75">
                    <ul class="ul_flex_select">
                        <li class="left">
                            <el-form-item prop="lifeLineTo">
                                <el-select v-model="mainForm.lifeLineTo">
                                    <el-option label="纳管（污水处理厂）" value="1"></el-option>
                                    <el-option label="雨水管网" value="2"></el-option>
                                    <el-option label="其他" value="3"></el-option>
                                </el-select>
                            </el-form-item>
                        </li>
                        <li class="right" v-if="mainForm.lifeLineTo==3">
                            <el-form-item prop="lifeLineToRemark">
                                <el-input v-model="mainForm.lifeLineToRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>环保应急预案情况</td>
                <td class="td75">
                    <ul class="ul_flex_radio">
                        <li class="left">
                            <el-form-item prop="environmentalProtectionPlan">
                                <el-radio-group v-model="mainForm.environmentalProtectionPlan">
                                    <el-radio label="1">有</el-radio>
                                    <el-radio label="2">无</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </li>
                        <li class="right">
                            <el-form-item prop="environmentalProtectionPlanRemark">
                                <el-input v-model="mainForm.environmentalProtectionPlanRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>排污许可证</td>
                <td class="td75">
                    <ul class="ul_flex_radio">
                        <li class="left">
                            <el-form-item prop="emissionPermit">
                                <el-radio-group v-model="mainForm.emissionPermit">
                                    <el-radio label="1">有</el-radio>
                                    <el-radio label="2">无</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </li>
                        <li class="right">
                            <el-form-item prop="emissionPermitRemark">
                                <el-input v-model="mainForm.emissionPermitRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>环评工艺</td>
                <td class="td75">
                    <ul class="ul_flex_radio">
                        <li class="left">
                            <el-form-item prop="eiaProcess">
                                <el-radio-group v-model="mainForm.eiaProcess">
                                    <el-radio label="1">有增、改</el-radio>
                                    <el-radio label="2">无增、改</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </li>
                        <li class="right">
                            <el-form-item prop="eiaProcessRemark">
                                <el-input v-model="mainForm.eiaProcessRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>新环评</td>
                <td class="td75">
                    <ul class="ul_flex_radio">
                        <li class="left">
                            <el-form-item prop="newEia">
                                <el-radio-group v-model="mainForm.newEia">
                                    <el-radio label="1">办理中</el-radio>
                                    <el-radio label="2">无</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </li>
                        <li class="right">
                            <el-form-item prop="newEiaRemark">
                                <el-input v-model="mainForm.newEiaRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>监督性检测企业</td>
                <td class="td75">
                    <ul class="ul_flex_radio">
                        <li class="left">
                            <el-form-item prop="supervisoryInspectionEnterprise">
                                <el-radio-group v-model="mainForm.supervisoryInspectionEnterprise">
                                    <el-radio label="1">是</el-radio>
                                    <el-radio label="2">不是</el-radio>
                                </el-radio-group>
                            </el-form-item>
                        </li>
                        <li class="right">
                            <el-form-item prop="supervisoryInspectionEnterpriseRemark">
                                <el-input v-model="mainForm.supervisoryInspectionEnterpriseRemark"></el-input>
                            </el-form-item>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>废水监督性检测周期</td>
                <td class="td75">
                    <el-form-item prop="sicfwwo">
                        <el-select v-model="mainForm.sicfwwo">
                            <el-option label="1个月" value="1"></el-option>
                            <el-option label="3个月" value="2"></el-option>
                            <el-option label="6个月" value="3"></el-option>
                            <el-option label="12个月" value="4"></el-option>
                            <el-option label="在线监控" value="5"></el-option>
                        </el-select>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>废气监督性检测周期</td>
                <td class="td75">
                    <el-form-item prop="sicfwwt">
                        <el-select v-model="mainForm.sicfwwt">
                            <el-option label="1个月" value="1"></el-option>
                            <el-option label="3个月" value="2"></el-option>
                            <el-option label="6个月" value="3"></el-option>
                            <el-option label="12个月" value="4"></el-option>
                        </el-select>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>土壤监督性检测周期</td>
                <td class="td75">
                    <el-form-item prop="sicfwws">
                        <el-select v-model="mainForm.sicfwws">
                            <el-option label="1个月" value="1"></el-option>
                            <el-option label="3个月" value="2"></el-option>
                            <el-option label="6个月" value="3"></el-option>
                            <el-option label="12个月" value="4"></el-option>
                        </el-select>
                    </el-form-item>
                </td>
            </tr>
        </table>
    </el-form>
</template>

<script>

import {mixin} from './mixin'

export default {
    name:'tab4',
    mixins:[mixin],
    data(){
        return {
            title:'固废及危废单元'
        }
    },
    methods:{
    }
}
</script>

<style lang="less">
.my_table_form .td75{
    width: 75%;
}
.ul_flex_radio{
    display: flex;
    .left{
        width: 140px;
    }
    .right{
        flex: 1;
    }
}
.ul_flex_select{
    display: flex;
    .left{
        width: 230px;
    }
    .right{
        flex: 1;
    }
}
</style>
