<template>
    <div>
        <head-top></head-top>
        <div class="my_container">
            <div class="my_main">
                <el-tree
                    v-loading="loading"
                    class="setting_tree"
                    :data="mainList"
                    node-key="id"
                    accordion
                    :expand-on-click-node="false">
                    <span class="tree_node" slot-scope="{ node, data }">
                        <span>{{data.rightName}}</span>
                        <span>
                            <el-button
                                v-if="!data.parentId"
                                type="text"
                                size="mini"
                                @click="appendNode(data)">
                                添加
                            </el-button>
                            <el-button
                                v-if="!!data.parentId"
                                type="text"
                                size="mini"
                                @click="deleteData(node,data)">
                                删除
                            </el-button>
                        </span>
                    </span>
                </el-tree>
            </div>
        </div>
        <el-dialog title="添加权限" :visible.sync="dialogVisible">
            <el-form :model="mainForm" ref="mainForm" label-width="80px">
                <el-form-item label="权限名称" prop="rightName" :rules="[rule.required]">
                    <el-input v-model.trim="mainForm.rightName" type="text"></el-input>
                </el-form-item>
                <el-form-item label="权限编码" prop="rightCode" :rules="[rule.required]">
                    <el-input v-model.trim="mainForm.rightCode" type="text"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="submitForm">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import headTop from '../components/headTop'
import PiTable from '../components/PiTable'

import {getAuthList,createAuth,updateAuth,deleteAuth} from '@/api/common'


export default {
    name:'userAuth',
    components: {headTop,PiTable},
    data(){
        return {
            loading:false,
            dialogVisible:false,
            mainList:[],
            mainForm:{
                rightName:'',
                rightCode:''
            },
            dblclick:false,
            curData:{}
        }
    },
    mounted(){
        this.getMainList()
    },
    methods: {
        getMainList(){  //获取列表
            this.loading=true
            getAuthList().then(res=>{
                this.loading=false
                if(res.resultCode=='0'){
                    this.mainList=res.payload
                }
            }).catch(_=>{
                this.loading=false
            })
        },
        submitForm(){
            this.$refs.mainForm.validate(valid=>{
                if(valid){
                    this.submitData()
                }
            })
        },
        submitData(){
            let params=this.deepClone(this.mainForm)
            let methods=createAuth
            if(params.id){
                methods=updateAuth
            }else{
                if(this.curData.id){
                    params.parentId=this.curData.id
                }else{
                    params.parent=null
                }
            }
            if(this.dblclick) return
            this.dblclick=true

            methods(params).then(res=>{
                this.dblclick=false
                if(res.resultCode==0){
                    this.dialogVisible=false
                    this.$message({type:'success',message:'成功'})
                    if(params.id){
                        this.curData[this.labelName]=this.mainForm[this.labelName]
                    }else if(this.curData.id){
                        if(!this.curData.children) {
                            this.$set(this.curData,'children',[])
                        }
                        this.curData.children.push(res.payload)
                    }else{
                        this.mainList.push(res.payload)
                    }
                }
            })
        },
        appendNode(data){
            this.dblclick=false
            this.dialogVisible=true
            this.curData=data
            this.mainForm={
                rightName:'',
                rightCode:''
            }
            this.$nextTick(_=>{
                this.$refs.mainForm.clearValidate()
            })
        },
        deleteData(node,data){
            this.$confirm('确认删除?','提示',{
				confirmButtonText:'确定',
				cancelButtonText:'取消',
				type: 'warning'
			}).then(()=>{
                let params={id:data.id}
                deleteAuth(params).then(res=>{
                    if(res.resultCode==0){
                        this.$message({type:'success',message:'已删除'})
                        let parent=node.parent
                        let children=parent.data.children||parent.data
                        let index=children.findIndex(d=>d.id==data.id)
                        children.splice(index,1)
                    }
                })
			})
        },
        showDialog(){
            this.dblclick=false
            this.dialogVisible=true
            this.curData={}
            this.mainForm.rightName=''
            this.mainForm.id=''
            this.$nextTick(_=>{
                this.$refs.mainForm.clearValidate()
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
.setting_tree{
    max-width: 800px;
    .tree_node{
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        padding: 10px 0;
        padding-right: 8px;
    }
    .el-tree-node__content{
        height: 46px;
    }
    .el-tree-node__expand-icon{
        font-size: 18px;
    }
}
</style>
