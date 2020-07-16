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
                    :buttons="buttons"
                    :page.sync="mainQuery"
                    @handleButton="handleButton"
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

import {getUserList,approveUser} from '@/api/common'


export default {
    name:'tableList',
    components: {headTop,PiSearchBar,PiTable},
    data(){
        return {
            loading:false,
            mainList:[],
            searchItem:[
                {label:'名称',prop:'userName',type:'input'},
            ],
            headers:[
                {label:'用户名',prop:'userName'},
                {label:'审核状态',prop:'status'},
                {label:'电话',prop:'mobile'},
                {label:'邮箱',prop:'email'},
            ],
            mainQuery:{
                userName:'',
                limit:10,
                page:1,
                total:0
            }
        }
    },
    computed:{
        buttons(){
            let info=this.$store.state.adminInfo
            if(info&&info.userName=='admin'){
                return [{label:'审核',color:'iconBlue',type:'eyes'}]
            }else{
                return []
            }
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
            getUserList(this.mainQuery).then(res=>{
                this.loading=false
                if(res.resultCode=='0'){
                    res.payload.content.map(item=>{
                        if(item.status==1){
                            item.status='审核通过'
                            item.eyesHide=true
                        }else{
                            item.status='待审核'
                        }
                    })
                    this.mainList=res.payload.content
                    this.mainQuery.total=res.payload.totalElements
                }
            }).catch(_=>{
                this.loading=false
            })
        },
        handleButton(data){
            this.$confirm('用户审核', '提示', {
                confirmButtonText: '通过',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(_=>{
                approveUser({id:data.row.id,status:1}).then(res=>{
                    if(res.resultCode=='0'){
                        this.getMainList()
                    }
                })
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
