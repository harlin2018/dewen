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
        <el-dialog class="my_userManager" width="400px" title="关联角色" :visible.sync="dialogVisible">
             <el-checkbox-group v-model="roleIds">
                <el-checkbox
                    v-for="item in roleList"
                    :label="item.id"
                    :key="item.id">
                    {{item.roleName}}
                </el-checkbox>
            </el-checkbox-group>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="submitRealtionRole">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import headTop from '../components/headTop'
import PiSearchBar from '../components/PiSearchBar'
import PiTable from '../components/PiTable'

import {getUserList,approveUser,getRoleList,assignRole,getUserRoleList } from '@/api/common'


export default {
    name:'userManager',
    components: {headTop,PiSearchBar,PiTable},
    data(){
        return {
            loading:false,
            dialogVisible:false,
            userId:'',
            roleIds:[],
            roleList:[],
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
        authList(){
            let menu=this.$store.state.userMenu
            if(!menu) return {}
            let cur=menu.find(item=>item.rightCode=='userManager')
            let auth={}
            cur.children.forEach(item=>{
                auth[item.rightCode]=true
            })
            return auth
        },
        buttons(){
            let buttons=[]
            if(this.authList.audit){
                buttons.push({label:'审核',color:'iconBlue',type:'eyes'})
            }
            if(this.authList.relation){
                buttons.push({label:'关联角色',color:'iconOrange',type:'relation'})
            }
            return buttons
        }
    },
    mounted(){
        this.getMainList()
        this.getRoleList()
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
        getRoleList(){  //获取列表
            getRoleList({page:1,limit:1000}).then(res=>{
                if(res.resultCode=='0'){
                    this.roleList=res.payload.content
                }
            })
        },
        getUserRoleList(){
            getUserRoleList({page:1,limit:1000,commonUser:{id:this.userId}}).then(res=>{
                if(res.resultCode=='0'){
                    if(res.payload.content){
                        let tmp=[]
                        this.roleIds=res.payload.content.map(item=>item.commonRole.id)
                    }
                }
            })
        },
        submitRealtionRole(){   //提交关联角色数据
            if(this.roleIds.length>0){
                let params={
                    userId:this.userId,
                    roleIds:this.roleIds
                }
                assignRole(params).then(res=>{
                    if(res.resultCode=='0'){
                        this.dialogVisible=false
                    }
                })
            }
        },
        handleButton(data){
            if(data.type=='eyes'){
                this.handleApprove(data)
            }else{
                this.handleRelation(data)
            }
        },
        handleRelation(data){   //关联角色
            this.userId=data.row.id
            this.dialogVisible=true
            this.getUserRoleList()
        },
        handleApprove(data){    //审核
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
.my_userManager{
    .el-checkbox{
        margin-bottom: 16px;
    }
}
</style>
