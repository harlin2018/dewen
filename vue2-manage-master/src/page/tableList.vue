<template>
    <div>
        <head-top></head-top>
        <div class="my_container">
            <div class="my_main">
                <PiSearchBar
                    @handleSearch="searchMain"
                    :search-items="searchItem"
                    :query.sync="mainQuery">
                    <el-button type="success" @click="redirect">添加</el-button>
                    <el-button type="warning" @click="openDialog">导出</el-button>
                </PiSearchBar>

                <pi-table
                    v-loading="loading"
                    :data="mainList"
                    :prop="headers"
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

import {getCompanyList,deleteCompanyData} from '@/api/common'

import colKey from './tableForm/col'

export default {
    name:'tableList',
    components: {headTop,PiSearchBar,PiTable},
    data(){
        return {
            loading:false,
            dialogVisible:false,
            mainList:[],
            searchItem:[
                {label:'名称',prop:'name',type:'input'},
            ],
            headers:[
                {label:'公司名称',prop:'name'},
                {label:'联系人',prop:'environmentalProtectionOfficer'},
                {label:'电话',prop:'contactNumber'},
            ],
            mainQuery:{
                name:'',
                limit:10,
                page:1,
                total:0
            },
            colKey,
            colArr:['name']
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
            console.log(this.colArr)
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
</style>
