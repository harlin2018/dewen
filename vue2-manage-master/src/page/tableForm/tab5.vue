<template>
    <el-form class="main_form" :model="mainForm" ref="mainForm">
        <table class="my_table_form">
            <tr>
                <td valign="top">巡查执法记录</td>
                <td class="td75">
                    <div>
                        <el-radio-group v-model="first.type">
                            <el-radio label="1">文字</el-radio>
                            <el-radio label="2">附件</el-radio>
                        </el-radio-group>
                    </div>
                    <div v-show="first.type==1">
                        <el-input v-model="first.value">
                            <el-button :disabled="!first.value" slot="append" @click="addRow('inspectRecordList',first.value)">添加</el-button>
                        </el-input>
                    </div>
                    <ul class="my_record">
                        <li v-for="(item,index) in mainForm.inspectRecordList" :key="index">
                            <p v-if="item.content">{{item.content}}</p>
                            <a v-else>{{item.url}}</a>
                            <span @click="removeRow('inspectRecordList',index)"><i class="iconfont icon-delete"></i></span>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>行政处罚记录</td>
                <td class="td75">
                    <div>
                        <el-radio-group v-model="second.type">
                            <el-radio label="1">文字</el-radio>
                            <el-radio label="2">附件</el-radio>
                        </el-radio-group>
                    </div>
                    <div v-show="second.type==1">
                        <el-input v-model="second.value">
                            <el-button :disabled="!second.value" slot="append" @click="addRow('adminRecordList',second.value)">添加</el-button>
                        </el-input>
                    </div>
                    <ul class="my_record">
                        <li v-for="(item,index) in mainForm.adminRecordList" :key="index">
                            <p v-if="item.content">{{item.content}}</p>
                            <a v-else>{{item.url}}</a>
                            <span @click="removeRow('adminRecordList',index)"><i class="iconfont icon-delete"></i></span>
                        </li>
                    </ul>
                </td>
            </tr>
        </table>
    </el-form>
</template>

<script>

import {mixin} from './mixin'


import uploadFile from './uploadFile'

export default {
    name:'tab4',
    mixins:[mixin],
    components:{uploadFile},
    data(){
        return {
            first:{
                type:'',
                value:''
            },
            second:{
                type:'',
                value:''
            }
        }
    },
    methods:{
        addRow(prop,val,src){
            if(!val&&!src) return
            if(prop=='inspectRecordList'){
                this.first.value=''
            }else{
                this.second.value=''
            }
            this.$emit('addRow',{prop,val,src})
        },
    }
}
</script>

<style lang="less">
.my_table_form .td75{
    width: 75%;
}
.my_record{
    padding: 10px;
    li{
        padding: 10px 20px 10px 0;
        font-size: 14px;
        position: relative;
        border-bottom: 1px dashed #ccc;
    }
    li:hover span{
        opacity: 1;
    }
    span{
        display: block;
        width:20px;
        height: 20px;
        position: absolute;
        right: 0;
        top: 10px;
        opacity: 0;
        cursor: pointer;
        transition: all 0.3s;
    }
    .iconfont{
        color: #F56C6C;
    }
}
</style>
