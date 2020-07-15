<template>
    <el-form class="main_form" :model="mainForm" ref="mainForm">
        <table class="my_table_form">
            <tr>
                <td>排污种类</td>
                <td colspan="3">废气</td>
            </tr>
            <tr>
                <td>名称或产品</td>
                <td class="row_table" colspan="3">
                    <table class="my_table_form my_table_inner my_table_inner_4">
                        <tr>
                            <td>工艺</td>
                            <td>污源物</td>
                            <td>环保治理设施</td>
                            <td class="row_add">
                                排污口
                                <span class="td_btn" @click="addRow('wasteGasList')"><i class="iconfont icon-add1"></i></span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr v-for="(item,index) in mainForm.wasteGasList" :key="item.keyId">
                <td>
                    <el-form-item :prop="'wasteGasList.'+index+'.name'" :rules="[rule.required]">
                        <el-input v-model="item.name"></el-input>
                    </el-form-item>
                </td>
                <td class="row_table" colspan="3">
                    <table class="my_table_form my_table_inner my_table_inner_4">
                        <tr>
                            <td>
                                <el-form-item :prop="'wasteGasList.'+index+'.craft'" :rules="[rule.required]">
                                    <el-input v-model="item.craft"></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item :prop="'wasteGasList.'+index+'.wuYuanContent'" :rules="[rule.required]">
                                    <el-input v-model="item.wuYuanContent"></el-input>
                                </el-form-item>
                            </td>
                            <td>
                                <el-form-item :prop="'wasteGasList.'+index+'.environmentalProtectionFacilities'" :rules="[rule.required]">
                                    <el-input v-model="item.environmentalProtectionFacilities"></el-input>
                                </el-form-item>
                            </td>
                            <td class="row_add">
                                <el-form-item :prop="'wasteGasList.'+index+'.drainOutlet'" :rules="[rule.required]">
                                    <el-input v-model="item.drainOutlet"></el-input>
                                </el-form-item>
                                <span class="td_btn" @click="removeRow('wasteGasList',index)"><i class="iconfont icon-jian"></i></span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>监测项目</td>
                <td>监测指标</td>
                <td>检测指标</td>
                <td class="row_add">
                    检测时间
                    <span class="td_btn" @click="addRow('wasteGasMonitorList')"><i class="iconfont icon-add1"></i></span>
                </td>
            </tr>
            <tr v-for="(sitem,sindex) in mainForm.wasteGasMonitorList" :key="sitem.keyId">
                <td>
                    <el-form-item :prop="'wasteGasMonitorList.'+sindex+'.monitorProject'" :rules="[rule.required]">
                        <el-input v-model="sitem.monitorProject"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'wasteGasMonitorList.'+sindex+'.monitorIndex'" :rules="[rule.required]">
                        <el-input v-model="sitem.monitorIndex"></el-input>
                    </el-form-item>
                </td>
                <td>
                    <el-form-item :prop="'wasteGasMonitorList.'+sindex+'.testItem'" :rules="[rule.required]">
                        <el-input v-model="sitem.testItem"></el-input>
                    </el-form-item>
                </td>
                <td class="row_add">
                    <el-form-item :prop="'wasteGasMonitorList.'+sindex+'.testTime'" :rules="[rule.required]">
                        <el-input v-model="sitem.testTime"></el-input>
                    </el-form-item>
                    <span class="td_btn" @click="removeRow('wasteGasMonitorList',sindex)"><i class="iconfont icon-jian"></i></span>
                </td>
            </tr>
        </table>
    </el-form>
</template>

<script>

import {mixin} from './mixin'

export default {
    name:'tab3',
    mixins:[mixin],
    data(){
        return {
            title:'废气单元'
        }
    },
    methods:{
    }
}
</script>

<style>

</style>
