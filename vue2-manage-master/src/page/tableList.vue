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
    </div>
</template>

<script>
import headTop from '../components/headTop'
import PiSearchBar from '../components/PiSearchBar'
import PiTable from '../components/PiTable'

import {getCompanyList,getCompanyData,deleteCompanyData} from '@/api/getData'

export default {
    name:'tableList',
    components: {headTop,PiSearchBar,PiTable},
    data(){
        return {
            loading:false,
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
            }
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
.my_main{

}
</style>
