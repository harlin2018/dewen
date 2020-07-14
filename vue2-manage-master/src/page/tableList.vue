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
                    :data="mainList"
                    :prop="headers"
                    :page.sync="mainQuery"
                    @pageChange="getMainList"
                    @rowDblclick="showDetail"
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

import {getCompanyList} from '@/api/getData'

export default {
    name:'tableList',
    components: {headTop,PiSearchBar,PiTable},
    data(){
        return {
            mainList:[],
            searchItem:[
                {label:'名称',prop:'name',type:'input'},
            ],
            headers:[
                {label:'公司名称',prop:'name'},
                {label:'联系人',prop:'nacontactNumberme'},
                {label:'电话',prop:'nacontactNumberme'},
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
        redirect(){
            this.$router.push('/tableForm')
        },
        searchMain(){
            this.mainQuery.page=1
            this.getMainList()
        },
        getMainList(){
            getCompanyList(this.mainQuery).then(res=>{
                if(res.resultCode=='0'){
                    this.mainList=res.payload.content
                    this.mainQuery.total=res.payload.totalElements
                }
            })
        },
        showDetail(){

        },
        handleEdit(){

        },
        handleDelete(){

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
