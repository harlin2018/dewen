<template>
    <el-form class="main_form" :model="mainForm" ref="mainForm">
        <table class="my_table_form">
            <tr>
                <td>巡查执法记录</td>
                <td class="td75">

                </td>
            </tr>
            <tr>
                <td>行政处罚记录</td>
                <td class="td75">

                </td>
            </tr>
        </table>
    </el-form>
</template>

<script>
export default {
    name:'tab4',
    props:{
        mainForm:{
            type:Object,
            default(){
                return {

                }
            }
        }
    },
    data(){
        return {
        }
    }
}
</script>

<style>
.my_table_form .td75{
    width: 75%;
}
</style>
