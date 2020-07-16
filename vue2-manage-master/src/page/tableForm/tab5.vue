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
                        <uploadFile style="margin-left:30px;" @success="updateFile($event,'inspectRecordList')" v-show="first.type==2"></uploadFile>
                    </div>
                    <div v-show="first.type==1">
                        <el-input v-model="first.value">
                            <el-button :disabled="!first.value" slot="append" @click="addRow('inspectRecordList',first.value)">添加</el-button>
                        </el-input>
                    </div>
                    <ul class="my_record">
                        <li v-for="(item,index) in mainForm.inspectRecordList" :key="index">
                            <p v-if="item.content">{{item.content}}</p>
                            <a v-else-if="isAdmin" :href="'file/download/'+item.fileUrl.id" download>{{item.fileUrl.fileName}}</a>
                            <a v-else href="javascript:;">{{item.fileUrl.fileName}}</a>
                            <div>{{formatDate(item.createDate)}}</div>
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
                        <uploadFile style="margin-left:30px;" @success="updateFile($event,'adminRecordList')" v-show="second.type==2"></uploadFile>
                    </div>
                    <div v-show="second.type==1">
                        <el-input v-model="second.value">
                            <el-button :disabled="!second.value" slot="append" @click="addRow('adminRecordList',second.value)">添加</el-button>
                        </el-input>
                    </div>
                    <ul class="my_record">
                        <li v-for="(item,index) in mainForm.adminRecordList" :key="index">
                            <p v-if="item.content">{{item.content}}</p>
                            <a v-else-if="isAdmin" :href="'file/download/'+item.fileUrl.id" download>{{item.fileUrl.fileName}}</a>
                            <a v-else href="javascript:;">{{item.fileUrl.fileName}}</a>
                            <div>{{formatDate(item.createDate)}}</div>
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

import {parseTime} from '@/utils'
import uploadFile from './uploadFile'

export default {
    name:'tab4',
    mixins:[mixin],
    components:{uploadFile},
    data(){
        return {
            first:{
                type:'1',
                value:''
            },
            second:{
                type:'1',
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
        updateFile(data,prop){
            this.$emit('updateFile',{data,prop})
        },
        formatDate(date){
            if(date){
                return parseTime(date)
            }else{
                return ''
            }
        }
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
        display: flex;
    }
    div{
        padding-right: 10px;
        width: 130px;
        font-size: 12px;
    }
    p{
        flex: 1;
        width: 0;
    }
    a{
        flex: 1;
        width: 0;
        text-decoration: underline;
        color: #409EFF;
        cursor: pointer;
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
