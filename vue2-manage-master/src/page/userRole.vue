<template>
    <div>
        <head-top></head-top>
        <div class="my_container">
            <div class="my_main">
                <PiSearchBar
                    @handleSearch="searchMain"
                    :search-items="searchItem"
                    :query.sync="mainQuery">
                    <el-button type="success" @click="showAdd" :disabled="!authList.edit">添加</el-button>
                </PiSearchBar>

                <pi-table
                    v-loading="loading"
                    :data="mainList"
                    :prop="headers"
                    :buttons="buttons"
                    :page.sync="mainQuery"
                    :edit="!!authList.edit"
                    :del="!!authList.delete"
                    @handleButton="handleButton"
                    @handleEdit="handleEdit"
                    @handleDelete="handleDelete"
                    @pageChange="getMainList"
                ></pi-table>
            </div>
        </div>
        <el-dialog :title="opCreate?'新增角色':'编辑角色'" :visible.sync="dialogVisible">
            <el-form :model="mainForm" ref="mainForm" label-width="80px">
                <el-form-item label="角色名称" prop="roleName" :rules="[rule.required]">
                    <el-input v-model.trim="mainForm.roleName" type="text"></el-input>
                </el-form-item>
                <el-form-item label="角色编码" prop="roleCode" :rules="[rule.required]">
                    <el-input v-model.trim="mainForm.roleCode" type="text"></el-input>
                </el-form-item>
                <el-form-item label="备注" prop="roleCode">
                    <el-input v-model.trim="mainForm.remark" type="remark"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="submitRole">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="关联权限" :visible.sync="authDialogVisible">
            <el-tree
                ref="tree"
                :data="authRoleList"
                :props="defaultProps"
                show-checkbox
                node-key="id"
                default-expand-all
                :expand-on-click-node="false"
                :default-checked-keys="checkedKey"
                >
            </el-tree>
            <span slot="footer" class="dialog-footer">
                <el-button @click="authDialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="submitAuthRole">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import headTop from '../components/headTop'
import PiSearchBar from '../components/PiSearchBar'
import PiTable from '../components/PiTable'

import {getRoleList,deleteRole,createRole,updateRole,getUserRoleList,getRoleAuthList,submitAuthRole} from '@/api/common'


export default {
    name:'userRoad',
    components: {headTop,PiSearchBar,PiTable},
    data(){
        return {
            loading:false,
            dialogVisible:false,
            authDialogVisible:false,
            opCreate:true,
            roleId:'',
            mainList:[],
            searchItem:[
                {label:'角色名',prop:'roleName',type:'input'},
            ],
            headers:[
                {label:'角色名称',prop:'roleName'},
                {label:'角色代号',prop:'roleCode'},
                {label:'备注',prop:'remark'},
            ],
            mainQuery:{
                roleName:'',
                limit:10,
                page:1,
                total:0
            },
            mainForm:{
                roleName:'',
                roleCode:'',
                remark:''
            },
            authRoleList:[],
            checkedKey: [],
            defaultProps: {
                children:'children',
                label:'rightName'
            },
        }
    },
    computed:{
        authList(){
            let menu=this.$store.state.userMenu
            if(!menu) return {}
            let cur=menu.find(item=>item.rightCode=='userRole')
            let auth={}
            cur.children.forEach(item=>{
                auth[item.rightCode]=true
            })
            return auth
        },
        buttons(){
            let buttons=[]
            if(this.authList.relation){
                buttons.push({label:'关联权限',color:'iconOrange',type:'relation'})
            }
            return buttons
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
            getRoleList(this.mainQuery).then(res=>{
                this.loading=false
                if(res.resultCode=='0'){
                    this.mainList=res.payload.content
                    this.mainQuery.total=res.payload.totalElements
                }
            }).catch(_=>{
                this.loading=false
            })
        },
        handleButton(data){
            if(data.type=='relation'){
                this.showAuthRole(data.row.id)
            }
        },
        submitRole(){   //提交角色基础数据
            this.$refs.mainForm.validate(valid=>{
                if(valid){
                    this.submitRoleData()
                }
            })
        },
        submitRoleData(){   //提交数据
            let method=createRole
            if(this.mainForm.id){
                method=updateRole
            }
            method(this.mainForm).then(res=>{
                if(res.resultCode=='0'){
                    this.dialogVisible=false
                    this.getMainList()
                }
            })
        },
        handleDelete(row){
            deleteRole(row).then(res=>{
                if(res.resultCode=='0'){
                    this.getMainList()
                }
            })
        },
        handleEdit(row){    //编辑角色
            this.resetForm()
            this.opCreate=false
            this.mainForm=this.deepClone(row)
        },
        showAdd(){  //弹窗新增角色框
            this.resetForm()
            this.opCreate=true
        },
        resetForm(){
            this.mainForm={
                roleName:'',
                roleCode:'',
                remark:''
            }
            this.dialogVisible=true
            this.$nextTick(_=>{
                this.$refs.mainForm.clearValidate()
            })
        },


        showAuthRole(id){
            this.roleId=id
            this.authDialogVisible=true
            this.checkedKey=[]
            this.getRoleAuthList()
        },
        getRoleAuthList(){  //角色关联权限列表
            getRoleAuthList({roleId:this.roleId}).then(res=>{
                if(res.resultCode=='0'){
                    this.checkedDefault(res.payload)
                    this.authRoleList=res.payload
                }
            })
        },
        submitAuthRole(){  //提交角色关联权限数据
            let nodes = this.$refs.tree.getCheckedNodes(false, true)
            let rights = []
            nodes.forEach(item=> {
                rights.push({rightId:item.id,rightType:'1'})
            })
            let params={
                roleId: this.roleId,
                rights: rights
            }
            submitAuthRole(params).then(res=>{
                if(res.resultCode=='0'){
                    this.$message({type:'success',message:'保存成功'})
                    this.authDialogVisible=false
                }
            })
        },
        checkedDefault(tree) {
            for (const index in tree) {
                if (tree[index].checked) {
                    this.checkedKey.push(tree[index].id)
                }
                if (tree[index].children) {
                    this.checkedDefault(tree[index].children)
                }
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
</style>
