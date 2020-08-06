<template>
    <div>
        <table class="my_table_form">
            <tr>
                <td valign="top" style="width:120px"><div style="width:120px">巡查执法记录</div></td>
                <td class="row_table">
                    <div>
                        <el-form class="this_form" :model="inspectRecordList" label-width="40px" :disabled="disabled">
                            <el-row :gutter="30">
                                <el-col :span="12">
                                    <el-form-item label="描述">
                                        <el-input type="textarea" rows="4" v-model="inspectRecordList.content"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12" class="col_btn">
                                    <el-form-item label="时间">
                                        <el-date-picker
                                            style="max-width:340px"
                                            v-model="inspectRecordList.completeDate"
                                            type="date">
                                        </el-date-picker>
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
                                    <el-button class="add_btn" type="primary" size="mini" @click="addRowByFile('inspectRecordList')">添加</el-button>
                                </el-col>
                            </el-row>
                        </el-form>
                    </div>
                     <el-table class="my_table_form my_table_file" :data="mainForm.inspectRecordList" style="width: 100%">
                        <el-table-column label="ID" align="center" width="60">
                            <template slot-scope="scope">
                                <span>{{scope.row.id}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="描述" show-overflow-tooltip>
                            <template slot-scope="scope">
                                <span>{{scope.row.content}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="附件">
                            <template slot-scope="scope">
                                <span class="file_a file_ra" v-for="file in scope.row.fileIdList" :key="file.id">
                                    <a v-if="download" :href="'file/download/'+file.id" download>{{file.fileName}}</a>
                                    <a v-else href="javascript:;">{{file.fileName}}</a>
                                    <span class="img" v-if="checkIsImage(file.fileName)">
                                        <span class="see">预览</span>
                                        <img :preview="file.id" :src="'/file/download/'+file.id" />
                                    </span>
                                    <span v-else class="see" @click="previewFile(file.id)">预览</span>
                                    ;
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column label="完成日期" align="center">
                            <template slot-scope="scope">
                                <span>{{formatDate(scope.row.completeDate)}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="审批描述" show-overflow-tooltip>
                            <template slot-scope="scope">
                                <span>{{scope.row.completeContent}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="审批附件">
                            <template slot-scope="scope">
                                <span class="file_a file_ra" v-if="scope.row.completeFileId&&scope.row.completeFileId.id">
                                    <a v-if="download" :href="'file/download/'+scope.row.completeFileId.id" download>{{scope.row.completeFileId.fileName}}</a>
                                    <a v-else href="javascript:;">{{scope.row.completeFileId.fileName}}</a>
                                    <span class="img" v-if="checkIsImage(scope.row.completeFileId.fileName)">
                                        <span class="see">预览</span>
                                        <img :preview="scope.row.completeFileId.id" :src="'/file/download/'+scope.row.completeFileId.id" />
                                    </span>
                                    <span v-else class="see" @click="previewFile(scope.row.completeFileId.id)">预览</span>
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="80" align="center" class-name="t_btn">
                            <template slot-scope="scope">
                                <span v-show="scope.row.status!=1&&scope.row.id" title="审批" class="file_btn file_approve" @click="approveShow(scope.row)">
                                    <i class="iconfont icon-eyes"></i>
                                </span>
                                <span title="删除" v-show="!disabled" class="file_btn file_del" @click="removeRow('inspectRecordList',scope.$index)">
                                    <i class="iconfont icon-delete"></i>
                                </span>
                            </template>
                        </el-table-column>
                    </el-table>
                </td>
            </tr>

            <tr>
                <td valign="top" style="width:120px">行政处罚记录</td>
                <td class="row_table">
                    <div>
                        <el-form class="this_form" :model="adminRecordList" label-width="40px" :disabled="disabled">
                            <el-row :gutter="30">
                                <el-col :span="12">
                                    <el-form-item label="描述">
                                        <el-input type="textarea" rows="4" v-model="adminRecordList.content"></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12" class="col_btn">
                                    <el-form-item label="时间">
                                        <el-date-picker
                                            style="max-width:340px"
                                            v-model="adminRecordList.completeDate"
                                            type="date">
                                        </el-date-picker>
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
                                    <el-button class="add_btn" type="primary" size="mini" @click="addRowByFile('adminRecordList')">添加</el-button>
                                </el-col>
                            </el-row>
                        </el-form>
                    </div>
                     <el-table class="my_table_form my_table_file" :data="mainForm.adminRecordList" style="width: 100%">
                        <el-table-column label="ID" align="center" width="60">
                            <template slot-scope="scope">
                                <span>{{scope.row.id}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="描述" show-overflow-tooltip>
                            <template slot-scope="scope">
                                <span>{{scope.row.content}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="附件">
                            <template slot-scope="scope">
                                <span class="file_a file_ra" v-for="file in scope.row.fileIdList" :key="file.id">
                                    <a v-if="download" :href="'file/download/'+file.id" download>{{file.fileName}}</a>
                                    <a v-else href="javascript:;">{{file.fileName}}</a>
                                    <span class="img" v-if="checkIsImage(file.fileName)">
                                        <span class="see">预览</span>
                                        <img :preview="file.id" :src="'/file/download/'+file.id" />
                                    </span>
                                    <span v-else class="see" @click="previewFile(file.id)">预览</span>
                                    ;
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column label="完成日期" align="center">
                            <template slot-scope="scope">
                                <span>{{formatDate(scope.row.completeDate)}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="审批描述" show-overflow-tooltip>
                            <template slot-scope="scope">
                                <span>{{scope.row.completeContent}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="审批附件">
                            <template slot-scope="scope">
                                <span class="file_a file_ra" v-if="scope.row.completeFileId&&scope.row.completeFileId.id">
                                    <a v-if="download" :href="'file/download/'+scope.row.completeFileId.id" download>{{scope.row.completeFileId.fileName}}</a>
                                    <a v-else href="javascript:;">{{scope.row.completeFileId.fileName}}</a>
                                    <span class="img" v-if="checkIsImage(scope.row.completeFileId.fileName)">
                                        <span class="see">预览</span>
                                        <img :preview="scope.row.completeFileId.id" :src="'/file/download/'+scope.row.completeFileId.id" />
                                    </span>
                                    <span v-else class="see" @click="previewFile(scope.row.completeFileId.id)">预览</span>
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="80" align="center" class-name="t_btn">
                            <template slot-scope="scope">
                                <span v-show="scope.row.status!=1&&scope.row.id" title="审批" class="file_btn file_approve" @click="approveShow(scope.row)">
                                    <i class="iconfont icon-eyes"></i>
                                </span>
                                <span title="删除" v-show="!disabled" class="file_btn file_del" @click="removeRow('adminRecordList',scope.$index)">
                                    <i class="iconfont icon-delete"></i>
                                </span>
                            </template>
                        </el-table-column>
                    </el-table>
                </td>
            </tr>
        </table>
        <el-dialog
            title="审批"
            :visible.sync="dialogVisible"
            width="50%">
            <el-form :model="approveForm" ref="approveForm" label-width="60px">
                <el-form-item label="描述" prop="completeContent" :rules="[rule.required]">
                    <el-input type="textarea" rows="3" v-model="approveForm.completeContent"></el-input>
                </el-form-item>
                <el-form-item label="附件">
                    <span class="file_a" v-if="approveForm.fileIdList.length>0">
                        <a v-if="download" :href="'file/download/'+approveForm.fileIdList[0].id" download>{{approveForm.fileIdList[0].fileName}}</a>
                        <a v-else href="javascript:;">{{approveForm.fileIdList[0].fileName}}</a>
                        <span class="img" v-if="checkIsImage(approveForm.fileIdList[0].fileName)">
                            <span class="see">预览</span>
                            <img :preview="approveForm.fileIdList[0].id" :src="'/file/download/'+approveForm.fileIdList[0].id" />
                        </span>
                        <span v-else class="see" @click="previewFile(approveForm.fileIdList[0].id)">预览</span>
                        <span class="del2" @click="delApproveFile"><i class="iconfont icon-delete"></i></span>
                    </span>
                    <uploadFile v-show="approveForm.fileIdList.length==0" @success="pushFile($event,'approveForm')"></uploadFile>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible=false">取消</el-button>
                <el-button type="primary" @click="submitData">提交</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

import {mixin} from './mixin'

import {parseTime} from '@/utils'
import uploadFile from './uploadFile'

import {approveRecord,getRecordList} from '@/api/common'

export default {
    name:'tab4',
    mixins:[mixin],
    components:{uploadFile},
    data(){
        return {
            dialogVisible:false,
            record:{},
            approveForm:{
                id:'',
                completeContent:'',
                completeFileId:null,
                fileIdList:[]
            },
            inspectRecordList:{
                content:'',
                completeDate:'',
                fileIdList:[],
            },
            inspectRecordListQuery:{
                limit:1000,
                page:1,
                recordType:1,
                companyId:{id:''}
            },
            adminRecordList:{
                content:'',
                completeDate:'',
                fileIdList:[]
            },
            adminRecordListQuery:{
                limit:1000,
                page:1,
                recordType:2,
                companyId:{id:''}
            },
        }
    },
    created(){
        if(this.$route.query.id){
            this.inspectRecordListQuery.companyId.id=this.$route.query.id
            this.adminRecordListQuery.companyId.id=this.$route.query.id
        }
    },
    methods:{
        pushFile(file,prop){    //上传附件
            this[prop].fileIdList.push(file)
            this.$previewRefresh()
            if(prop=='approveForm'){
                this.approveForm.completeFileId=file.id
            }
        },
        removeFile(index,prop){ //删除附件
            this[prop].fileIdList.splice(index,1)
        },
        addRowByFile(prop){ //添加一行记录
            this.$emit('updateFile',{prop,data:this[prop]})
            this.$previewRefresh()
            this[prop]={
                content:'',
                fileIdList:[]
            }
        },
        formatDate(date){   //格式化时间
            if(date){
                return parseTime(date,'{y}-{m}-{d}')
            }else{
                return ''
            }
        },
        approveShow(item){  //显示审批框
            this.approveForm.fileIdList=[]
            this.approveForm.completeFileId=null
            this.record=item
            this.approveForm.id=item.id
            this.dialogVisible=true
            this.$nextTick(_=>{
                this.$refs.approveForm.resetFields()
            })
        },
        submitData(){   //提交审批数据
            this.$refs.approveForm.validate(valid=>{
                if(valid){
                    let params=this.deepClone(this.approveForm)
                    delete params.fileIdList
                    approveRecord(params).then(res=>{
                        if(res.resultCode=='0'){
                            this.dialogVisible=false
                            this.getRecordList()
                        }
                    })
                }
            })
        },
        delApproveFile(){   //删除审批时的附件
            this.approveForm.fileIdList=[]
            this.approveForm.completeFileId=null
        },
        getRecordList(){    //获取记录列表
            let type=this.record.recordType==1?'inspectRecordList':'adminRecordList'
            getRecordList(this[type+'Query']).then(res=>{
                if(res.resultCode=='0'){
                    this.$emit('updateRecord',{type,list:res.payload.content})
                }
            })
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
    .el-form-item{
        margin-bottom: 10px;
    }
}
.file_list{
    padding-right: 90px;
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
    .del{
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
    td,th{
        font-size: 14px;
        border: none;
        border-left: 1px solid #000;
        border-bottom: 1px solid #000 !important;
    }
    th{
        border-top: 1px solid #000;
    }
    tr td:first-child,tr th:first-child{
        border-left: none !important;
    }
    tr:last-child td{
        border-bottom: none !important;
    }
    tr td:last-child,tr th:last-child{
        border-right: none !important;
    }
}
.my_table_file{
    .file_a{
        a{
            max-width:80px;
            overflow: hidden;
            white-space:nowrap;
            text-overflow:ellipsis;
            text-decoration: underline;
            position: relative;
            top:2px;
        }
    }
}
.file_a{
    position: relative;
    margin: 2px 10px;
    a{
        display: inline-block;
    }
    .see{
        font-size: 14px;
        color: #409EFF;
        cursor: pointer;
        margin-left: 6px;
    }
    .del{
        position: absolute;
        right: -2px;
        top: 0;
        opacity: 0;
        transition: all 0.3s;
        cursor: pointer;
    }
    .img{
        margin-left: 6px;
        position: relative;
        .see{
            margin-left: 0px;
        }
        img{
            position: absolute;
            width: 28px;
            height: 20px;
            left: 0;
            top: 0;
            opacity: 0;
            z-index:9;
            cursor: pointer;
        }
    }
    .del2{
        position: absolute;
        right: -20px;
        top: 0;
        opacity: 0;
        transition: all 0.3s;
        cursor: pointer;
        line-height:18px;
        .iconfont{
            font-size: 12px;
        }
    }
}
.file_ra{
    .see{
        position: relative;
        top:-3px;
    }
}
.file_a:hover .del2{
    opacity: 1;
}
.file_btn{
    margin:0 4px;
    cursor: pointer;
}
.file_del{
    .iconfont{
        color: #F56C6C;
    }
}
.file_approve{
    .iconfont{
        color: #409EFF;
    }
}
.pswp--open{
    z-index: 9999;
}
.t_btn{
    padding:0 !important;
    .cell{
        padding:0;
    }
    .iconfont{
        font-size: 14px;
    }
}
.col_btn{
    position: relative;
    .add_btn{
        position: absolute;
        right:45px;
        top:68px;
    }
}
</style>
