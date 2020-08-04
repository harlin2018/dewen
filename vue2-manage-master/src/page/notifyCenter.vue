<template>
    <div>
        <head-top></head-top>
        <div class="my_container">
            <div class="my_main">
                <PiSearchBar
                    @handleSearch="searchMain"
                    :search-items="searchItem"
                    :query.sync="mainQuery">
                </PiSearchBar>

                <pi-table
                    v-loading="loading"
                    :data="mainList"
                    :prop="headers"
                    :edit="false"
                    :del="false"
                    :page.sync="mainQuery"
                    @pageChange="getMainList"
                ></pi-table>
            </div>
        </div>
    </div>
</template>

<script>
import headTop from '../components/headTop'
import PiSearchBar from '../components/PiSearchBar'
import PiTable from '../components/PiTable'

import {getNotifyList} from '@/api/common'

import colKey from './tableForm/col'

export default {
    name:'notifyCenter',
    components: {headTop,PiSearchBar,PiTable},
    data(){
        return {
            loading:false,
            mainList:[],
            searchItem:[
                {label:'标题',prop:'title',type:'input'},
            ],
            headers:[
                {label:'标题',prop:'title'},
                {label:'内容',prop:'content'},
                {label:'备注',prop:'remark'},
                {label:'时间',prop:'createDate',date:true}
            ],
            mainQuery:{
                name:'',
                limit:10,
                page:1,
                total:0
            }
        }
    },
    computed:{
        authList(){
            let menu=this.$store.state.userMenu
            if(!menu) return {}
            let cur=menu.find(item=>item.rightCode=='notifyCenter')
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
        searchMain(){   //搜索
            this.mainQuery.page=1
            this.getMainList()
        },
        getMainList(){  //获取列表
            this.loading=true
            getNotifyList(this.mainQuery).then(res=>{
                this.loading=false
                if(res.resultCode=='0'){
                    this.mainList=res.payload.content
                    this.mainQuery.total=res.payload.totalElements
                }
            }).catch(_=>{
                this.loading=false
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
.my_checkbox{
    .el-checkbox{
        margin:10px;
    }
}
.el-pagination{
    text-align: center;
}
</style>
