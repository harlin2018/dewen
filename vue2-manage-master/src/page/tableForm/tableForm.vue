<template>
    <div>
        <head-top></head-top>
        <div class="my_container">
            <div class="my_save_btn">
                <el-button type="primary" @click="fillData">快速生产数据</el-button>
                <el-button type="primary" @click="submitData">保存</el-button>
                <el-button type="info" @click="$router.go(-1)">返回</el-button>
            </div>
            <div class="my_main">
                <el-tabs v-model="activeName">
                    <el-tab-pane label="基本资料单元" name="tab1">
                        <tab1 ref="tab1" :main-form.sync="mainForm" @addRow="addRow" @removeRow="removeRow" @updateFile="updateFile"></tab1>
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
                    <el-tab-pane label="巡查及处罚单元" name="tab5">
                        <tab5 ref="tab5" :main-form.sync="mainForm" @addRow="addRow" @removeRow="removeRow" @updateFile="updateFile"></tab5>
                    </el-tab-pane>
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

import {getCompanyData,createCompanyData,updateCompanyData,getCateHistory} from '@/api/common'

const cateListKey=['companyProductList','wasteWaterList','wasteWaterMonitorList','wasteGasList','wasteGasMonitorList','companyWasteList','inspectRecordList','adminRecordList']

const quickFill={"name":"大横琴科技","address":"横琴","legalRepresentative":"flame","organizationCode":"10010","environmentalProtectionOfficer":"pride","contactNumber":"12345678912","industryCategory":"科技","industryCode":"202020","industryDept":"行政部","completionDate":"2020-07-14T16:00:00.000+0000","fixedAssets":"10","envirProtFixedAssets":"20","enterpriseSize":2,"pollutionSourceManagementLevel":2,"sewageType":null,"createDate":null,"modifyDate":null,"generalIndustrialSolidWaste":"一般工业固体废物A","yearProduction":"1020","yearProcess":"2010","disposalWay":"2","mainSoundSourceName":"汽车","mmppc":"隔音棉","sewerageRain":"2","rowTo":"1","rowToRemark":"","enterprisePretreatment":"1","output":"1212","theSewageTo":"3","theSewageToRemark":"我是其他","stfds":"12","lifeProduced":"123","lifeLineTo":"2","lifeLineToRemark":"","environmentalProtectionPlan":"2","emissionPermit":"2","eiaProcess":"2","newEia":"2","epep":"2","supervisoryInspectionEnterprise":"2","sicfwwo":"2","sicfwwt":"2","sicfwws":"2","numberEmployees":"1000","eia":"1","officialReply":"1","officialReplyFileId":null,"breaks":"生产工艺描述1","officialTime":"2020-07-14T16:00:00.000Z"}

export default {
    name:'tableForm',
    components: {headTop,PiSearchBar,tab1,tab2,tab3,tab4,tab5},
    data(){
        return {
            cid:'',
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
                officialReplyFileId:null,
                breaks:'',
                officialTime:'',
                companyProductList:[    //主要产品
                    {
                        keyId:'companyProductList_1',
                        name:'',
                        unit:'',
                        mainMaterialsName:'',
                        majorPollutants:'',
                    }
                ],
                wasteWaterList:[    //废水
                    {
                        keyId:'wasteWaterList_1',
                        name:'',
                        craft:'',
                        wuYuanContent:'',
                        environmentalProtectionFacilities:'',
                        drainOutlet:''
                    }
                ],
                wasteWaterMonitorList:[   //废水
                    {
                        keyId:'swasteWaterMonitorList_1',
                        monitorProject:'',
                        monitorIndex:'',
                        testItem:'',
                        testTime:''
                    }
                ],
                wasteGasList:[   //废气
                    {
                        keyId:'wasteGasList_1',
                        name:'',
                        craft:'',
                        wuYuanContent:'',
                        environmentalProtectionFacilities:'',
                        drainOutlet:''
                    }
                ],
                wasteGasMonitorList:[   //废气
                    {
                        keyId:'wasteGasMonitorList_1',
                        monitorProject:'',
                        monitorIndex:'',
                        testItem:'',
                        testTime:''
                    }
                ],
                companyWasteList:[
                    {
                        keyId:'companyWasteList_1',
                        nameType:'',
                        yearPro:'',
                        processMethods:'',
                        processMethodsRemark:'',
                        yearProcess:''
                    }
                ],
                inspectRecordList:[ //巡查记录
                    {
                        content:'巡查记录',
                        fileUrl:null
                    }
                ],
                adminRecordList:[ //处罚记录
                    {
                        content:'处罚记录',
                        fileUrl:null
                    }
                ]
            },
            companyProductList:{
                keyId:'companyProductList_1',
                name:'',
                unit:'',
                mainMaterialsName:'',
                majorPollutants:'',
            },
            wasteWaterList:{
                keyId:'wasteWaterList_1',
                name:'',
                craft:'',
                wuYuanContent:'',
                environmentalProtectionFacilities:'',
                drainOutlet:''
            },
            wasteWaterMonitorList:{
                keyId:'swasteWaterMonitorList_1',
                monitorProject:'',
                monitorIndex:'',
                testItem:'',
                testTime:''
            },
            wasteGasList:{
                keyId:'wasteGasList_1',
                name:'',
                craft:'',
                wuYuanContent:'',
                environmentalProtectionFacilities:'',
                drainOutlet:''
            },
            wasteGasMonitorList:{
                keyId:'wasteGasMonitorList_1',
                monitorProject:'',
                monitorIndex:'',
                testItem:'',
                testTime:''
            },
            companyWasteList:{
                keyId:'companyWasteList_1',
                nameType:'',
                yearPro:'',
                processMethods:'',
                processMethodsRemark:'',
                yearProcess:''
            }
        }
    },
    created(){
        this.cid=this.$route.query.id
        if(this.cid){
            this.getCompanyData(this.cid)
        }else{
            this.getCateHistory()
        }
    },
    mounted(){

    },
    methods: {
        getCompanyData(id){ //获取详情
            getCompanyData(id).then(res=>{
                if(res.resultCode==0){
                    this.setListKeyId(res.payload)
                    this.mainForm=res.payload
                }
            })
        },
        getCateHistory(){   //分类历史
            getCateHistory().then(res=>{
                if(res.resultCode=='0'){
                    for(let attr in res.payload){
                        res.payload[attr].map(item=>{
                            delete item.id
                        })
                        this.setListKeyId(res.payload)
                        this.mainForm[attr]=res.payload[attr]
                    }
                }
            })
        },
        setListKeyId(data){
            cateListKey.forEach(key=>{
                if(data[key]){
                    data[key].map((item,index)=>{
                        item.keyId=key+'_'+index
                    })
                }
            })
        },
        updateFile({data,prop}){   //更新附件信息
            if(prop=='officialReplyFileId'){
                this.mainForm[prop]=data.id?data:null
            }else{
                this.addRow({prop,val:'',src:data})
            }
        },
        submitData(){   //提交数据
            /* 调用子组件的检验方法 返回一个promise */
            let tab1=this.$refs.tab1.getTabData()
            let tab2=this.$refs.tab2.getTabData()
            let tab3=this.$refs.tab3.getTabData()
            let tab4=this.$refs.tab4.getTabData()
            Promise.all([tab1,tab2,tab3,tab4]).then((values) => {
                //全部校验通过才提交到后台
                let handleSubmit=createCompanyData
                if(this.mainForm.id){
                    handleSubmit=updateCompanyData
                }
                console.log(JSON.stringify(this.mainForm))
                handleSubmit(this.mainForm).then(res=>{
                    if(res.resultCode==0){
                        this.$message({type:'success',message:'保存成功'})
                        setTimeout(()=>{
                            this.$router.push('/tableList')
                        },1000)
                    }
                })


            }).catch(err=>{
                this.$message({
                    type:'error',
                    message:'【'+err+'】有资料未填写'
                })
            })
        },
        addRow({prop,val,src}){   //根据prop类型表格添加一行空数据
            if(prop=='inspectRecordList'||prop=='adminRecordList'){
                this.mainForm[prop].push({
                    content:val||'',
                    fileUrl:src?src:null
                })
                return
            }
            let row=this.deepClone(this[prop])
            let len=this.mainForm[prop].length
            let last=len>0?this.mainForm[prop][len-1]:row   //获取最后一条数据
            let arr=last.keyId.split('_')
            row.keyId=arr[0]+'_'+(parseInt(arr[1])+1)   //根据最后一条数据的keyId 加1给新增的一行
            this.mainForm[prop].push(row)
        },
        removeRow({prop,index}){   //根据prop类型删除一行
            this.mainForm[prop].splice(index,1)
        },
        fillData(){
            for(let attr in quickFill){
                if(quickFill[attr]){
                    this.mainForm[attr]=quickFill[attr]
                }
            }
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
