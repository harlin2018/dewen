<template>
    <div>
        <head-top></head-top>
        <div class="my_container">
            <div class="my_save_btn">
                <el-button type="primary" @click="submitData">保存</el-button>
                <el-button type="info" @click="$router.go(-1)">返回</el-button>
            </div>
            <div class="my_main">
                <el-tabs v-model="activeName">
                    <el-tab-pane label="基本资料单元" name="tab1">
                        <tab1 ref="tab1" :main-form.sync="mainForm" @addRow="addRow" @removeRow="removeRow"></tab1>
                    </el-tab-pane>
                    <el-tab-pane label="废水单元" name="tab2">
                        <tab2 ref="tab2" :main-form.sync="mainForm" @addRow="addRow" @removeRow="removeRow"></tab2>
                    </el-tab-pane>
                    <el-tab-pane label="废气单元" name="tab3">
                        <tab3 ref="tab3" :main-form.sync="mainForm" @addRow="addRow" @removeRow="removeRow"></tab3>
                    </el-tab-pane>
                    <el-tab-pane label="固废及危废单元" name="tab4">
                        <tab4 ref="tab4" :main-form.sync="mainForm" @addRow="addRow" @removeRow="removeRow"></tab4>
                    </el-tab-pane>
                    <!-- <el-tab-pane label="巡查及处罚单元" name="tab5">
                        <tab5 ref="tab5" :main-form.sync="mainForm"></tab5>
                    </el-tab-pane> -->
                </el-tabs>
            </div>
        </div>
    </div>
</template>

<script>
import headTop from '@/components/headTop'
import PiSearchBar from '@/components/PiSearchBar'

import tab1 from './tab1'
import tab2 from './tab2'
import tab3 from './tab3'
import tab4 from './tab4'
import tab5 from './tab5'

import {userCount} from '@/api/getData'

export default {
    name:'tableForm',
    components: {headTop,PiSearchBar,tab1,tab2,tab3,tab4,tab5},
    data(){
        return {
            activeName:'tab1',
            searchItem:[
                {label:'名称',prop:'name',type:'input'},
            ],
            mainQuery:{
                name:''
            },
            mainForm:{
                name:'',
                address:'',
                legalRepresentative:'',
                organizationCode:'',
                environmentalProtectionOfficer:'',
                contactNumber:'',
                industryCategory:'',
                industryCode:'',
                industryDept:'',
                completionDate:'',
                fixedAssets:'',
                envirProtFixedAssets:'',
                enterpriseSize:'',
                pollutionSourceManagementLevel:'',
                sewageType:'',
                createDate:'',
                modifyDate:'',
                generalIndustrialSolidWaste:'',
                yearProduction:'',
                yearProcess:'',
                disposalWay:'',
                disposalWayRemark:'',
                mainSoundSourceName:'',
                mmppc:'',
                sewerageRain:'',
                rowTo:'',
                rowToRemark:'',
                enterprisePretreatment:'',
                output:'',
                theSewageTo:'',
                theSewageToRemark:'',
                stfds:'',
                lifeProduced:'',
                lifeLineTo:'',
                lifeLineToRemark:'',
                environmentalProtectionPlan:'',
                emissionPermit:'',
                eiaProcess:'',
                newEia:'',
                epep:'',
                supervisoryInspectionEnterprise:'',
                sicfwwo:'',
                sicfwwt:'',
                sicfwws:'',
                numberEmployees:'',
                eia:'',
                officialReply:'',
                breaks:'',
                officialTime:'',
                companyProductList:[    //主要产品
                    {
                        keyId:'companyProduct_1',
                        name:'',
                        unit:'',
                        mainMaterialsName:'',
                        majorPollutants:'',
                    }
                ],
                wasteWaterList:[    //废水
                    {
                        keyId:'wasteWater_1',
                        name:'',
                        craft:'',
                        wuYuanContent:'',
                        environmentalProtectionFacilities:'',
                        drainOutlet:''
                    }
                ],
                swasteWaterMonitorList:[   //废水
                    {
                        keyId:'swasteWaterMonitor_1',
                        monitorProject:'',
                        monitorIndex:'',
                        testItem:'',
                        testTime:''
                    }
                ],
                wasteGasList:[   //废气
                    {
                        keyId:'wasteGas_1',
                        name:'',
                        craft:'',
                        wuYuanContent:'',
                        environmentalProtectionFacilities:'',
                        drainOutlet:''
                    }
                ],
                wasteGasMonitorList:[   //废气
                    {
                        keyId:'wasteGasMonitor_1',
                        monitorProject:'',
                        monitorIndex:'',
                        testItem:'',
                        testTime:''
                    }
                ],
                companyWasteList:[
                    {
                        keyId:'companyWaste_1',
                        nameType:'',
                        yearPro:'',
                        processMethods:'',
                        processMethodsRemark:'',
                        yearProcess:''
                    }
                ]
            },
            companyProductList:{
                keyId:'companyProduct_1',
                name:'',
                unit:'',
                mainMaterialsName:'',
                majorPollutants:'',
            },
            wasteWaterList:{
                keyId:'wasteWater_1',
                name:'',
                craft:'',
                wuYuanContent:'',
                environmentalProtectionFacilities:'',
                drainOutlet:''
            },
            swasteWaterMonitorList:{
                keyId:'swasteWaterMonitor_1',
                monitorProject:'',
                monitorIndex:'',
                testItem:'',
                testTime:''
            },
            wasteGasList:{
                keyId:'wasteGas_1',
                name:'',
                craft:'',
                wuYuanContent:'',
                environmentalProtectionFacilities:'',
                drainOutlet:''
            },
            wasteGasMonitorList:{
                keyId:'wasteGasMonitor_1',
                monitorProject:'',
                monitorIndex:'',
                testItem:'',
                testTime:''
            },
            companyWasteList:{
                keyId:'companyWaste_1',
                nameType:'',
                yearPro:'',
                processMethods:'',
                processMethodsRemark:'',
                yearProcess:''
            }
        }
    },
    mounted(){

    },
    methods: {
        submitData(){
            let tab1=this.$refs.tab1.getTabData()
            let tab2=this.$refs.tab2.getTabData()
            let tab3=this.$refs.tab3.getTabData()
            let tab4=this.$refs.tab4.getTabData()
            Promise.all([tab1,tab2,tab3,tab4]).then((values) => {
                console.log(values)
            }).catch(err=>{
                this.$message({
                    type:'error',
                    message:'【'+err+'】有资料未填写'
                })
            })
        },
        addRow(prop){
            let row=this.deepClone(this[prop])
            let len=this.mainForm[prop].length
            let last=len>0?this.mainForm[prop][len-1]:row
            let arr=last.keyId.split('_')
            row.keyId=arr[0]+'_'+(parseInt(arr[1])+1)
            this.mainForm[prop].push(row)
        },
        removeRow({prop,index}){
            this.mainForm[prop].splice(index,1)
        }
    }
}
</script>

<style lang="less">
@import '../../style/mixin';
.my_container{
    padding: 20px;
    height: 100%;
}
.my_save_btn{
    text-align: right;
    padding-bottom: 20px;
}
.main_form{
    padding:20px;
}
.my_table_form{
    width: 100%;
    border-collapse: collapse;
    td{
        border: 1px solid #000;
        padding:14px 6px;
    }
    .el-form-item{
        margin-bottom: 0;
    }
    .el-form-item__label{
        display: none;
    }
    .el-form-item__error{
        padding-top:0px;
    }
    .el-radio{
        margin: 4px 10px;
    }
    .el-date-editor.el-input, .el-date-editor.el-input__inner{
        width: 100%;
    }
    .row_table{
        padding: 0;
    }
    .row_center{
        text-align: center;
    }
    .row_notice{
        color: #f00;
    }
    .row_add{
        position: relative;
        .td_btn{
           position: absolute;
            right: -21px;
            top: 50%;
            display: block;
            width: 20px;
            height: 30px;
            line-height: 30px;
            background: #e6e8ec;
            border-radius: 0 4px 4px 0;
            font-size: 14px;
            text-align: center;
            margin-top: -15px;
            cursor: pointer;
            .iconfont{
                font-size: 14px;
                color: #757272;
            }
        }
    }
}
.my_table_inner{
    td{
        border: none;
        border-left: 1px solid #000;
    }
    tr td:first-child{
        border: none;
    }
}
.my_table_inner_4{
    td{
        width: 25%;
    }
}
.my_table_inner_row{
    tr:first-child td{
        border-top: none;
    }
    tr td:first-child{
        border-left: none;
    }
    tr:last-child td{
        border-bottom: none;
    }
    tr td:last-child{
        border-right: none;
    }
}
</style>
