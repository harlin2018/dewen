<template>
    <el-form class="main_form" :model="mainForm" ref="mainForm" :disabled="disabled">
        <table class="my_table_form">
            <tr>
                <td>排污种类</td>
                <td class="row_center" colspan="4">废水</td>
            </tr>
            <tr>
                <td>名称或产品</td>
                <td>工艺</td>
                <td>污源物</td>
                <td>环保治理设施</td>
                <td class="row_add">
                    排口
                    <span v-show="!disabled" class="td_btn" @click="addRow('wasteWaterList')"><i class="iconfont icon-add1"></i></span>
                </td>
                <!-- <td class="row_table" colspan="4">
                    <table class="my_table_form my_table_inner my_table_inner_4">
                        <tr>
                            <td>工艺</td>
                            <td>污源物</td>
                            <td>环保治理设施</td>
                            <td class="row_add">
                                排口
                                <span class="td_btn" @click="addRow('wasteWaterList')"><i class="iconfont icon-add1"></i></span>
                            </td>
                        </tr>
                    </table>
                </td> -->
            </tr>
            <tr v-for="(item,index) in mainForm.wasteWaterList" :key="item.keyId">
                <td>
                    <el-form-item :prop="'wasteWaterList.'+index+'.name'">
                        <el-input v-model="item.name"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'wasteWaterList.'+index+'.craft'">
                        <el-input v-model="item.craft"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'wasteWaterList.'+index+'.wuYuanContent'">
                        <el-input v-model="item.wuYuanContent"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'wasteWaterList.'+index+'.environmentalProtectionFacilities'">
                        <el-input v-model="item.environmentalProtectionFacilities"></el-input>
                    </el-form-item>
                </td>
                <td class="row_add">
                    <el-form-item :prop="'wasteWaterList.'+index+'.drainOutlet'">
                        <el-input v-model="item.drainOutlet"></el-input>
                    </el-form-item>
                    <span v-show="!disabled" class="td_btn" @click="removeRow('wasteWaterList',index)"><i class="iconfont icon-jian"></i></span>
                </td>
                <!-- <td class="row_table" colspan="3">
                    <table class="my_table_form my_table_inner my_table_inner_4">
                        <tr>
                            <td>
                                <el-form-item :prop="'wasteWaterList.'+index+'.craft'">
                                    <el-input v-model="item.craft"></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item :prop="'wasteWaterList.'+index+'.wuYuanContent'">
                                    <el-input v-model="item.wuYuanContent"></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item :prop="'wasteWaterList.'+index+'.environmentalProtectionFacilities'">
                                    <el-input v-model="item.environmentalProtectionFacilities"></el-input>
                                </el-form-item>
                            </td>
                            <td class="row_add">
                                <el-form-item :prop="'wasteWaterList.'+index+'.drainOutlet'">
                                    <el-input v-model="item.drainOutlet"></el-input>
                                </el-form-item>
                                <span class="td_btn" @click="removeRow('wasteWaterList',index)"><i class="iconfont icon-jian"></i></span>
                            </td>
                        </tr>
                    </table>
                </td> -->
            </tr>
            <tr>
                <td>监测项目</td>
                <td>监测指标</td>
                <td>检测指标</td>
                <td>
                    检测时间
                </td>
                <td class="row_add">
                    <span v-show="!disabled" class="td_btn" @click="addRow('wasteWaterMonitorList')"><i class="iconfont icon-add1"></i></span>
                    附件
                </td>
            </tr>
            <tr v-for="(sitem,sindex) in mainForm.wasteWaterMonitorList" :key="sitem.keyId">
                <td>
                    <el-form-item :prop="'wasteWaterMonitorList.'+sindex+'.monitorProject'">
                        <el-input v-model="sitem.monitorProject"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'wasteWaterMonitorList.'+sindex+'.monitorIndex'">
                        <el-input v-model="sitem.monitorIndex"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'wasteWaterMonitorList.'+sindex+'.testItem'">
                        <el-input v-model="sitem.testItem"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'wasteWaterMonitorList.'+sindex+'.testTime'">
                        <el-date-picker
                            v-model="sitem.testTime"
                            type="date">
                        </el-date-picker>
                    </el-form-item>
                </td>
                <td class="row_add">
                    <div class="official_file" v-if="!!sitem.monitorFileId">
                        <a v-if="download" :href="'file/download/'+sitem.monitorFileId.id" download>{{sitem.monitorFileId.fileName}}</a>
                        <a v-else href="javascript:;">{{sitem.monitorFileId.fileName}}</a>
                        <span @click="deleteFile('wasteWaterMonitorList',sindex)"><i class="iconfont icon-delete"></i></span>
                    </div>
                    <uploadFile v-show="!sitem.monitorFileId"  @success="updateFile($event,'wasteWaterMonitorList',sindex)"></uploadFile>
                    <span v-show="!disabled" class="td_btn" @click="removeRow('wasteWaterMonitorList',sindex)"><i class="iconfont icon-jian"></i></span>
                </td>
            </tr>
        </table>
    </el-form>
</template>

<script>

import {mixin} from './mixin'
import uploadFile from './uploadFile'

export default {
    name:'tab2',
    mixins:[mixin],
    components:{uploadFile},
    data(){
        return {
            title:'废水单元'
        }
    },
    methods:{
    }
}
</script>

<style>

</style>
