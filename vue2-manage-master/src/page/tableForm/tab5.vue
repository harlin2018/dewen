<template>
    <table class="my_table_form">
        <tr>
            <td valign="top">巡查执法记录</td>
            <td class="td75 row_table">
                <div>
                    <el-form class="this_form" :model="inspectRecordList" label-width="60px">
                        <el-form-item label="描述">
                            <el-input v-model="inspectRecordList.content"></el-input>
                        </el-form-item>
                        <el-form-item label="附件">
                            <ul class="file_list">
                                <li v-for="(item,index) in inspectRecordList.fileIdList" :key="index">
                                    <a :href="'file/download/'+item.id">{{item.fileName}}</a>
                                    <span @click="removeFile(index,'inspectRecordList')"><i class="iconfont icon-closed"></i></span>
                                </li>
                            </ul>
                            <uploadFile @success="pushFile($event,'inspectRecordList')"></uploadFile>
                        </el-form-item>
                        <el-form-item label="">
                            <el-button type="primary" size="mini" @click="addRowByFile('inspectRecordList')">添加</el-button>
                        </el-form-item>
                    </el-form>
                </div>
                <table class="my_table_form my_table_file">
                    <tr>
                        <td>描述</td>
                        <td>附件</td>
                        <td>日期</td>
                        <td>操作</td>
                    </tr>
                    <tr v-for="(item,index) in mainForm.inspectRecordList" :key="index">
                        <td>{{item.content}}</td>
                        <td>
                            <span class="file_a" v-for="file in item.fileIdList" :key="file.id">
                                <a v-if="isAdmin" :href="'file/download/'+file.id" download>{{file.fileName}}</a>
                                <a v-else href="javascript:;">{{file.fileName}}</a>
                            </span>
                        </td>
                        <td>{{formatDate(item.createDate)}}</td>
                        <td>
                            <span class="file_del" @click="removeRow('inspectRecordList',index)"><i class="iconfont icon-delete"></i></span>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td valign="top">行政处罚记录</td>
            <td class="td75 row_table">
                <div>
                    <el-form class="this_form" :model="adminRecordList" label-width="60px">
                        <el-form-item label="描述">
                            <el-input v-model="adminRecordList.content"></el-input>
                        </el-form-item>
                        <el-form-item label="附件">
                            <ul class="file_list">
                                <li v-for="(item,index) in adminRecordList.fileIdList" :key="index">
                                    <a :href="'file/download/'+item.id">{{item.fileName}}</a>
                                    <span @click="removeFile(index,'adminRecordList')"><i class="iconfont icon-closed"></i></span>
                                </li>
                            </ul>
                            <uploadFile @success="pushFile($event,'adminRecordList')"></uploadFile>
                        </el-form-item>
                        <el-form-item label="">
                            <el-button type="primary" size="mini" @click="addRowByFile('adminRecordList')">添加</el-button>
                        </el-form-item>
                    </el-form>
                </div>
                <table class="my_table_form my_table_file">
                    <tr>
                        <td>描述</td>
                        <td>附件</td>
                        <td>日期</td>
                        <td>操作</td>
                    </tr>
                    <tr v-for="(item,index) in mainForm.adminRecordList" :key="index">
                        <td>{{item.content}}</td>
                        <td>
                            <span class="file_a" v-for="file in item.fileIdList" :key="file.id">
                                <a v-if="isAdmin" :href="'file/download/'+file.id" download>{{file.fileName}}</a>
                                <a v-else href="javascript:;">{{file.fileName}}</a>
                            </span>
                        </td>
                        <td>{{formatDate(item.createDate)}}</td>
                        <td>
                            <span class="file_del" @click="removeRow('adminRecordList',index)"><i class="iconfont icon-delete"></i></span>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
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
            thisForm:{

            },
            inspectRecordList:{
                content:'',
                fileIdList:[]
            },
            adminRecordList:{
                content:'',
                fileIdList:[]
            }
        }
    },
    methods:{
        pushFile(file,prop){
            this[prop].fileIdList.push(file)
        },
        removeFile(index,prop){
            this[prop].fileIdList.splice(index,1)
        },
        addRowByFile(prop){
            this.$emit('updateFile',{prop,data:this[prop]})
        },
        // addRow(prop,val,src){
        //     if(!val&&!src) return
        //     if(prop=='inspectRecordList'){
        //         this.first.value=''
        //     }else{
        //         this.second.value=''
        //     }
        //     this.$emit('addRow',{prop,val,src})
        // },
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
.this_form{
    padding: 20px;
    .el-form-item__label{
        display: block;
    }
}
.file_list{
    padding-top: 6px;
    li{
        display: inline-block;
        max-width: 300px;
        padding-right: 20px;
        position: relative;
        height: 20px;
        line-height: 20px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        margin: 6px 10px 0;
    }
    span{
        display: block;
        width: 20px;
        height: 20px;
        line-height: 20px;
        cursor: pointer;
        position: absolute;
        right: 0;
        top:0;
        transform-origin: right;
        transform: scale(0.8);
        opacity: 0;
        transition: all 0.3s;
    }
    .iconfont{
        font-size: 12px;
        color: #aaa;
    }
    li:hover span{
        opacity: 1;
    }
}
.my_table_file{
    td{
        font-size: 14px;
    }
    tr td:nth-child(1){
        width: 40%;
    }
    tr td:nth-child(2){
        width: calc(40% - 40px);
    }
    tr td:nth-child(3){
        width: 20%;
    }
    tr td:nth-child(4){
        width: 40px;
        text-align: center;
    }
    tr td:first-child{
        border-left: none;
    }
    tr:last-child td{
        border-bottom: none;
    }
    tr td:last-child{
        border-right: none;
    }
}
.file_a{
    margin: 2px 10px;
    a{
        text-decoration: underline;
    }
}
.file_del{
    cursor: pointer;
    .iconfont{
        color: #F56C6C;
    }
}
</style>
