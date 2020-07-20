<template>
    <div class="pageRow">
		<div class="page_search_bar">
			<div class="pItem" v-for="(item,index) in searchItems" :key="index">
                <el-input
                    clearable
                    v-if="item.type=='input'"
                    v-model="query[item.prop]"
                    :placeholder="item.label"
                    @keyup.enter.native="handleSearch">
				</el-input>
                <el-date-picker
                    clearable
                    v-if="item.type=='date'"
                    v-model="query[item.prop]"
                    :placeholder="item.label"
                    type="date">
                </el-date-picker>
                <el-select
                    clearable
                    @change="handleSearch"
                    v-if="item.type=='data'"
                    v-model="query[item.prop]"
                    :placeholder="item.label">
                    <el-option
                        v-for="(option,m) in item.data"
                        :label="option.label"
                        :value="option.value"
                        :key="m">
                    </el-option>
                </el-select>
                <PiSelectByPage
                    clearable
                    :placeholder="item.label"
                    v-if="item.type=='ref'"
                    :form.sync="query[item.prop]"
                    form-prop="id"
                    :select-value="item.select.selectValue"
                    :search-key="item.select.search?item.select.searchKey:''"
                    :search-params="item.select.params?item.select.params:{}"
                    :select-label="item.select.selectLabel"
                    :select-api="item.select.api">
                </PiSelectByPage>
			</div>
			<div class="pItem">
				<el-button
					:size="size"
					@click="handleSearch"
					type="primary">
					搜索
				</el-button>
			</div>
			<div class="pItem">
				<el-button
					:size="size"
					@click="handleReset"
					type="info">
					重置
				</el-button>
			</div>
			<slot></slot>
		</div>
    </div>
</template>

<script>
import PiSelectByPage from '@/components/PiSelectByPage'

export default {
	name: 'PiSearchBar',
	components:{PiSelectByPage},
	props:{
        size:{
            type:String,
            default:''
        },
        searchItems:{
            type:Array,
            default(){
                return []
            }
        },
        query:{
            type:Object,
            default(){
                return {}
            }
        }
	},
	data(){
        return {
            mainQuery:this.deepClone(this.query)
        }
    },
	methods:{
        handleReset(){
            let query=this.deepClone(this.mainQuery)
            query.total=0
            this.$emit('update:query',query)
            this.$emit('handleReset')
        },
        handleSearch(){
            this.query.page=1
            this.$emit('handleSearch')
        }
	}
}
</script>
<style lang="less" scoped>
.pageRow{
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    padding-bottom: 20px;
    position: relative;
    .pMl100{
        margin-left: 100px;
    }
    .pBlod{
        font-weight: bold;
    }
    .pItem{
        display: inline-block;
        margin-right: 20px;
        margin-bottom: 10px;
        .iconfont{
            font-size: 12px;
        }
    }
    .pItemR{
        position: absolute;
        right: 0;
        top: 0;
    }
    .pLeft{
        font-size: 14px;
        margin-right: 6px;
        color: #303133;
    }
    .pRight{
        font-size: 14px;
        color: #303133;
    }
    .el-input{
        min-width: 200px;
    }
}
.page_search_bar{
	.el-input{
		width: 160px;
	}
	.el-button{
		margin-top: 4px;
	}
	.iconfont{
		font-size: 12px;
	}
}
</style>
