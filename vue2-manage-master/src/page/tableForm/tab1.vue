<template>
    <el-form class="main_form" :model="mainForm" ref="mainForm">
        <table class="my_table_form">
            <tr>
                <td>单位名称</td>
                <td colspan="3">
                    <el-form-item prop="name">
                        <!-- <el-input autocomplete v-model="mainForm.name"></el-input> -->
                        <el-autocomplete
                            clearable
                            v-model.trim="mainForm.name"
                            :debounce="parseInt(0)"
                            :fetch-suggestions="(queryString,cb)=>{searchForAuto(queryString,cb,'name')}"
                            value-key="name">
                        </el-autocomplete>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>单位地址</td>
                <td colspan="3">
                    <el-form-item prop="address">
                        <!-- <el-input v-model="mainForm.address"></el-input> -->
                        <el-autocomplete
                            clearable
                            v-model.trim="mainForm.address"
                            :debounce="parseInt(0)"
                            :fetch-suggestions="(queryString,cb)=>{searchForAuto(queryString,cb,'address')}"
                            value-key="address">
                        </el-autocomplete>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>法定代表人</td>
                <td>
                    <el-form-item prop="legalRepresentative">
                        <el-input v-model="mainForm.legalRepresentative"></el-input>
                    </el-form-item>
                </td>
                <td>组织机构代码</td>
                <td>
                    <el-form-item prop="organizationCode">
                        <el-input v-model="mainForm.organizationCode"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>环保负责人</td>
                <td>
                    <el-form-item prop="environmentalProtectionOfficer">
                        <el-input v-model="mainForm.environmentalProtectionOfficer"></el-input>
                    </el-form-item>
                </td>
                <td>联系电话</td>
                <td>
                    <el-form-item prop="contactNumber">
                        <el-input v-model="mainForm.contactNumber"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>行业类别</td>
                <td>
                    <el-form-item prop="industryCategory">
                        <el-input v-model="mainForm.industryCategory"></el-input>
                    </el-form-item>
                </td>
                <td>行业代码</td>
                <td>
                    <el-form-item prop="industryCode">
                        <el-input v-model="mainForm.industryCode"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>行业主管部门</td>
                <td>
                    <el-form-item prop="industryDept">
                        <el-input v-model="mainForm.industryDept"></el-input>
                    </el-form-item>
                </td>
                <td>建成时间</td>
                <td>
                    <el-form-item prop="completionDate">
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
                    <el-form-item prop="fixedAssets">
                        <el-input v-model="mainForm.fixedAssets"></el-input>
                    </el-form-item>
                </td>
                <td>环保设施固定资产（万元）</td>
                <td>
                    <el-form-item prop="envirProtFixedAssets">
                        <el-input v-model="mainForm.envirProtFixedAssets"></el-input>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td>企业规模</td>
                <td>
                    <el-form-item prop="enterpriseSize">
                        <el-radio-group v-model="mainForm.enterpriseSize">
                            <el-radio :label="1">大型</el-radio>
                            <el-radio :label="2">中型</el-radio>
                            <el-radio :label="3">小型</el-radio>
                        </el-radio-group>
                    </el-form-item>
                </td>
                <td>污染源管理级别</td>
                <td>
                    <el-form-item prop="pollutionSourceManagementLevel">
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
                    <el-form-item prop="numberEmployees">
                        <el-input v-model="mainForm.numberEmployees"></el-input>
                    </el-form-item>
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>环评</td>
                <td>
                    <el-form-item prop="eia">
                        <el-select v-model="mainForm.eia">
                            <el-option label="报告书" value="1"></el-option>
                            <el-option label="报告表" value="2"></el-option>
                            <el-option label="无" value="3"></el-option>
                        </el-select>
                    </el-form-item>
                </td>
                <td>环评批复</td>
                <td>
                    <ul class="ul_flex">
                        <li class="left">
                            <el-form-item prop="officialReply">
                                <el-radio v-model="mainForm.officialReply" label="1">有</el-radio>
                                <el-radio v-model="mainForm.officialReply" label="2">无</el-radio>
                            </el-form-item>
                        </li>
                        <li class="right">
                            <div class="official_file" v-if="!!mainForm.officialReplyFileId&&mainForm.officialReply==1">
                                <a v-if="isAdmin" :href="'file/download/'+mainForm.officialReplyFileId.id" download>{{mainForm.officialReplyFileId.fileName}}</a>
                                <a v-else href="javascript:;">{{mainForm.officialReplyFileId.fileName}}</a>
                                <span @click="deleteFile('officialReplyFileId')"><i class="iconfont icon-delete"></i></span>
                            </div>
                            <uploadFile v-show="!mainForm.officialReplyFileId&&mainForm.officialReply==1" @success="updateFile($event,'officialReplyFileId')"></uploadFile>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>环评时间</td>
                <td>
                    <el-form-item prop="officialTime">
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
                    <el-form-item :prop="'companyProductList.'+index+'.name'">
                        <el-input v-model="item.name"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'companyProductList.'+index+'.unit'">
                        <el-input v-model="item.unit"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'companyProductList.'+index+'.mainMaterialsName'">
                        <el-input v-model="item.mainMaterialsName"></el-input>
                    </el-form-item>
                </td>
                <td class="row_add">
                    <el-form-item :prop="'companyProductList.'+index+'.majorPollutants'">
                        <el-input v-model="item.majorPollutants"></el-input>
                    </el-form-item>
                    <span class="td_btn" @click="removeRow('companyProductList',index)"><i class="iconfont icon-jian"></i></span>
                </td>
            </tr>
            <tr>
                <td>月进水量(平均)</td>
                <td>污水产生量(平均)</td>
                <td>消耗量</td>
                <td>水平衡</td>
            </tr>
            <tr>
                <td>
                    <el-form-item prop="mouthInflow">
                        <el-input v-model="mainForm.mouthInflow"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item prop="swageGeneration">
                        <el-input v-model="mainForm.swageGeneration"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item prop="consumption">
                        <el-input v-model="mainForm.consumption"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item prop="waterBalance">
                        <!-- <el-input v-model="mainForm.waterBalance"></el-input> -->
                        <el-select v-model="mainForm.waterBalance">
                            <el-option label="正常" value="1"></el-option>
                            <el-option label="异常" value="2"></el-option>
                            <el-option label="其它" value="3"></el-option>
                        </el-select>
                    </el-form-item>
                </td>
            </tr>
            <tr>
                <td colspan="4">生产工艺描述</td>
            </tr>
            <tr>
                <td colspan="4">
                    <el-form-item prop="breaks">
                        <!-- <el-input type="textarea" rows="6" v-model="mainForm.breaks"></el-input> -->
                        <el-autocomplete
                            clearable
                            v-model.trim="mainForm.breaks"
                            :debounce="parseInt(0)"
                            :fetch-suggestions="(queryString,cb)=>{searchForAuto(queryString,cb,'breaks')}"
                            value-key="breaks">
                        </el-autocomplete>
                    </el-form-item>
                </td>
            </tr>
        </table>
    </el-form>
</template>

<script>

import {mixin} from './mixin'

import uploadFile from './uploadFile'

import {debounce} from '@/utils'

import {getCompanyList} from '@/api/common'

export default {
    name:'tab1',
    mixins:[mixin],
    components:{uploadFile},
    data(){
        return {
            title:'基本资料单元'
        }
    },
    methods:{
        searchForAuto(val,cb,key){ //autocomplete
			debounce(()=>{
				let params={limit:20,page:1}
				if(val) params[key]=val
				getCompanyList(params).then(res=>{
					if(res.resultCode==0){
						cb(res.payload.content)
					}else{
						cb([])
					}
				}).catch(err=>{
					cb([])
				})
			},300)
        }
    }
}

</script>

<style lang="less">
.ul_flex{
    display: flex;
    max-width: 400px;
    .left{
        width: 140px;
    }
    .right{
        flex: 1;
        width: 0;
        display: flex;
        align-items: center;
    }
}
.official_file{
    box-sizing: border-box;
    max-width: 100%;
    display: inline-block;
    position: relative;
    height: 20px;
    line-height: 20px;
    overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;
    padding-right: 16px;
    a{
        font-size:14px;
        text-decoration: underline;
    }
    span{
        position: absolute;
        right: 0px;
        top: 0;
        opacity: 0;
        transition: all 0.3s;
        cursor: pointer;
    }
    .iconfont{
        font-size: 12px;
    }
}
.official_file:hover span{
    opacity: 1;
}
.is-required{
    position: relative;
}
.is-required:after{
    content: '*';
    color: red;
    position: absolute;
    right: 10px;
    top: 50%;
    margin-top: -10px;
}
.el-autocomplete{
    width: 100%;
}
</style>
