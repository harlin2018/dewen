<template>
    <div>
        <head-top></head-top>
        <div class="my_container">
            <div class="my_main">
                <PiSearchBar
                    @handleSearch="searchMain"
                    :search-items="searchItem"
                    :query.sync="mainQuery">
                    <el-button type="success" @click="redirect" :disabled="!authList.edit">添加</el-button>
                    <el-button type="warning" @click="openDialog" :disabled="!authList.export" style="margin-left:16px">导出</el-button>
                </PiSearchBar>

                <pi-table
                    v-loading="loading"
                    :data="mainList"
                    :prop="headers"
                    :edit="!!authList.edit"
                    :del="!!authList.delete"
                    :page.sync="mainQuery"
                    @pageChange="getMainList"
                    @rowDblclick="handleEdit"
                    @handleEdit="handleEdit"
                    @handleDelete="handleDelete"
                ></pi-table>
            </div>
        </div>
        <el-dialog
            title="导出列选择"
            :visible.sync="dialogVisible"
            width="70%">
            <div class="my_checkbox">
                <el-checkbox-group v-model="colArr" size="mini">
                    <el-checkbox
                        v-for="(item,key) in colKey"
                        :key="key"
                        :label="key">
                            {{item}}
                        </el-checkbox>
                </el-checkbox-group>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="exportExcel">导出</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import headTop from '../components/headTop'
import PiSearchBar from '../components/PiSearchBar'
import PiTable from '../components/PiTable'

import {getCompanyList,deleteCompanyData,exportList,exportDownload} from '@/api/common'

import colKey from './tableForm/col'

let monthOption=[
    {value:'1',label:'1个月'},
    {value:'2',label:'3个月'},
    {value:'3',label:'6个月'},
    {value:'4',label:'12个月'}
]

export default {
    name:'tableList',
    components: {headTop,PiSearchBar,PiTable},
    data(){
        return {
            loading:false,
            dialogVisible:false,
            mainList:[],
            searchItem:[
                {label:'单位名称',prop:'name',type:'input'},
                {label:'企业编码',prop:'code',type:'input'},
                {label:'环评时间',prop:'officialTime',type:'date'},
                {label:'录入时间',prop:'createDate',type:'date'},
                {label:'废水监督性检测周期',prop:'sicfwwo',type:'data',
                    data:[
                        ...monthOption,
                        {value:'5',label:'在线监控'}
                    ]
                },
                {label:'废气监督性检测周期',prop:'sicfwwt',type:'data',data:monthOption},
                {label:'土壤监督性检测周期',prop:'sicfwws',type:'data',data:monthOption}
            ],
            headers:[
                {label:'企业名称',prop:'name'},
                {label:'联系人',prop:'environmentalProtectionOfficer'},
                {label:'电话',prop:'contactNumber'},
                {label:'区域',prop:'storeArea'},
                {label:'地址',prop:'address'},
                {label:'状态',prop:'recordStatus'},
            ],
            mainQuery:{
                name:'',
                code:'',
                officialTime:'',
                createDate:'',
                sicfwwo:'',
                sicfwwt:'',
                sicfwws:'',
                limit:10,
                page:1,
                total:0
            },
            colKey,
            colArr:['name']
        }
    },
    computed:{
        authList(){
            let menu=this.$store.state.userMenu
            if(!menu) return {}
            let cur=menu.find(item=>item.rightCode=='tableList')
            let auth={}
            cur.children.forEach(item=>{
                auth[item.rightCode]=true
            })
            return auth
        }
    },
    mounted(){
        this.getMainList()
    },
    methods: {
        redirect(){ //重定向
            this.$router.push('/tableForm')
        },
        searchMain(){   //搜索
            this.mainQuery.page=1
            this.getMainList()
        },
        getMainList(){  //获取列表
            this.loading=true
            getCompanyList(this.mainQuery).then(res=>{
                this.loading=false
                if(res.resultCode=='0'){
                    res.payload.content.map(item=>{
                        item.recordStatus=item.recordStatus==1?'保存':'暂存'
                    })
                    this.mainList=res.payload.content
                    this.mainQuery.total=res.payload.totalElements
                }
            }).catch(_=>{
                this.loading=false
            })
        },
        handleEdit(row){    //编辑跳转
            this.$router.push({path:'/tableForm',query:{id:row.id}})
        },
        handleDelete(row){  //删除数据
            deleteCompanyData(row).then(res=>{
                if(res.resultCode==0){
                    this.$message({type:'success',message:'已删除'})
                    this.getMainList()
                }
            })
        },
        openDialog(){
            this.dialogVisible=true
        },
        exportExcel(){
            if(this.colArr.length>0){
                let params={
                    title:'废弃单元',
                    fieIds:this.colArr,
                    ids:[],
                    name:this.mainQuery.name,
                    officialTime:this.mainQuery.officialTime,
                    createDate:this.mainQuery.createDate
                }
                exportList(params).then(res=>{
                    if(res.resultCode=='0'){
                        window.open('/companyInfo/download?fileName='+res.payload)
                        // var downloadFileUrl=res.payload
                        // var elemIF=document.createElement('iframe')
                        // elemIF.src=downloadFileUrl
                        // elemIF.style.display='none'
                        // document.body.appendChild(elemIF)
                    }else{
                        this.$message({type:'error',message:'导出错误'})
                    }
                })
            }
        }
    }
}
</script>

<style lang="less">
@import '../style/mixin';
.my_container{
    padding: 20px;
    height: 100%;
}
.my_checkbox{
    .el-checkbox{
        margin:10px;
    }
}
.el-pagination{
    text-align: center;
}
</style>
