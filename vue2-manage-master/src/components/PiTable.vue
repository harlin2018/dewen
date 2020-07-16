<template>
    <div class="pi_table">
        <div class="pi_table_item" v-if="selectionCount">
            已选择<span>{{multipleSelection.length}}</span>项
        </div>
        <el-table
            border
            stripe
            highlight-current-row
            ref="pi_table"
            :data="data"
            :max-height="height"
            @row-click="rowClick"
            @row-dblclick="rowDblclick"
            :lazy="lazy"
            :load="load"
            :row-key="rowKey"
            :tree-props="treeProps"
            @selection-change="handleSelectionChange"
        >
            <el-table-column v-if="selection" type="selection" width="60" align="center"></el-table-column>
            <el-table-column type="expand" v-if="propDrop.length>0">
                <template slot-scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-row>
                            <el-col :span="span" v-for="(item,index) in propDrop" :key="index">
                                <el-form-item :label="item.label">
                                    <span v-if="item.format">{{handleFormat(item.format,props.row[item.prop])||'-'}}</span>
                                    <span v-else-if="item.transfer">{{item.transfer(props.row[item.prop]) || '-'}}</span>
                                    <span v-else-if="item.date">{{props.row[item.prop] | parseTime('{y}-{m}-{d}')}}</span>
                                    <span v-else-if="item.prop1">{{scope.row[item.prop]?scope.row[item.prop][item.prop1]:'-'}}</span>
                                    <span v-else>{{props.row[item.prop]||'-'}}</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column
                v-for="(item,index) in propCols"
                :key="'propCols_' + index"
                :min-width="item.width||''"
                :label="item.label"
                :show-overflow-tooltip="item.overflow?true:false"
                align="center"
            >
                <template slot-scope="scope">
                    <span v-if="item.button">
                        <el-tooltip
                            :content="item.label"
                            placement="bottom">
                            <span class="tableIcon iconBlue" @click.stop="handleButton(scope.row,item.button,scope.$index)" >
                                <i class="iconfont" :class="[item.button]"></i>
                            </span>
                        </el-tooltip>
                    </span>
                    <span v-else-if="item.format">{{handleFormat(item.format,scope.row[item.prop])||'-'}}</span>
                    <span v-else-if="item.transfer">{{item.transfer(scope.row[item.prop]) || '-'}}</span>
                    <span v-else-if="item.date">{{scope.row[item.prop] | parseTime('{y}-{m}-{d}')}}</span>
                    <span v-else-if="item.prop1">{{scope.row[item.prop]?scope.row[item.prop][item.prop1]:'-'}}</span>
                    <span v-else>{{scope.row[item.prop]||'-'}}</span>
                </template>
            </el-table-column>

            <el-table-column
              v-for="(item,index) in moreCols"
              :key="index"
              :min-width="item.width||''"
              :label="item.label"
              :show-overflow-tooltip="item.overflow?true:false"
              align="center"
            >
              <template slot-scope="scope">
                      <span v-if="item.button">
                          <el-tooltip
                            :content="item.label"
                            placement="bottom">
                              <span class="tableIcon iconBlue" @click.stop="handleButton(scope.row,item.button,scope.$index)" >
                                  <i class="iconfont" :class="[item.button]"></i>
                              </span>
                          </el-tooltip>
                      </span>
                <span v-else-if="item.format">{{handleFormat(item.format,scope.row[item.prop])||'-'}}</span>
                <span v-else-if="item.transfer">{{item.transfer(scope.row[item.prop]) || '-'}}</span>
                <span v-else-if="item.date">{{scope.row[item.prop] | parseTime('{y}-{m}-{d}')}}</span>
                <span v-else>{{scope.row[item.prop]||'-'}}</span>
              </template>
            </el-table-column>

            <el-table-column
                v-if="edit||del||buttons.length>0"
                label="操作"
                :width="operationWidth"
                :fixed="fixed"
                align="center">
                <template slot-scope="scope">
                    <el-tooltip content="编辑" placement="bottom" v-show="edit">
                        <span class="tableIcon iconBlue" @click.stop="handleEdit(scope.row)" >
                            <i class="iconfont icon-edit"></i>
                        </span>
                    </el-tooltip>
                    <el-tooltip
                        v-for="(item,index) in buttons"
                        :key="index"
                        :content="item.label"
                        placement="bottom">
                        <span class="tableIcon" :class="[item.color]" @click.stop="handleButton(scope.row,item.type,scope.$index)" >
                            <i class="iconfont" :class="['icon-'+item.type]"></i>
                        </span>
                    </el-tooltip>
                    <el-tooltip content="删除" placement="bottom" v-show="del">
                        <span class="tableIcon iconGray" @click.stop="handleDelete(scope.row)" >
                            <i class="iconfont icon-delete"></i>
                        </span>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            background
            v-if="page.limit&&page.total>page.limit"
			:total="page.total"
			:current-page.sync="page.page"
			:page-sizes="[10, 20, 30, 50]"
			:page-size.sync="page.limit"
			@size-change="handleSizeChange"
			@current-change="handleCurrentChange"
			:layout="layout">
		</el-pagination>
    </div>
</template>

<script>
export default {
    name:'TableBase',
    components:{},
    props:{
        layout:{
            type:String,
            default:'total, sizes, prev, pager, next, jumper'
        },
        lazy:{ //是否多选
          type:Boolean,
          default:false
        },
        showMore:{ //是否显示更多
          type:Boolean,
          default:false
        },
        rowKey:{ //是否多选
          type:[Number,String]
        },
        load:{ //传入方法，以便后续回显
          type:Function
        },
        treeProps:{ //传入方法，以便后续回显
          type:Object
        },
        handleFormat:{ //传入方法，以便后续回显
            type:Function,
            default:function(type,value){}
        },
        selection:{ //是否多选
            type:Boolean,
            default:false
        },
        selectionCount:{ //是否显示选择多少行
            type:Boolean,
            default:false
        },
        edit:{ //是否显示编辑按钮
            type:Boolean,
            default:true
        },
        del:{ //是否显示删除按钮
            type:Boolean,
            default:true
        },
        data:{ //表格数据
            type:Array,
            default(){
                return []
            }
        },
        prop:{ //表头名字
            type:Array,
            default(){
                return []
            }
        },
        page:{ //搜索分页
            type:Object,
            default(){
                return {}
            }
        },
        height:{ //表格高度
            type:[Number,String],
            default:'auto'
        },
        span:{ //el-col span参数
            type:[Number,String],
            default:12
        },
        buttons:{ //按钮
            type:Array,
            default(){
                // [{label:'删除',color:'iconGray',type:'delete'}]
                return []
            }
        },
        fixed:{ //操作列是否固定
            type:[Boolean,String],
            default:false
        },
        buttonW:{
            type:String,
            default:''
        }
    },
    data() {
        return {
            multipleSelection:[]
        }
    },
	computed:{
        propDrop(){ //需要在表头显示的值
            return this.prop.filter(item=>item.drop && !item.more)
        },
        propCols(){ //需要下拉显示的值
            return this.prop.filter(item=>!item.drop && !item.more)
        },
        moreCols(){ //需要下拉显示的值
          return this.prop.filter(item=>item.more)
        },
        operationWidth(){
            if(this.buttonW) return parseInt(this.buttonW)
            let a=this.edit?1:0
            let b=this.del?1:0
            let c=this.buttons.length
            return parseInt(a+b+c)*80
        }
	},
	methods:{
        rowClick(row){ //点击一行方法
            this.$emit('rowClick',row)
        },
        rowDblclick(row){ //双击一行方法
            this.$emit('rowDblclick',row)
        },
        handleEdit(row){ //点击编辑方法
            this.$emit('handleEdit',row)
        },
        handleDelete(row){ //点击删除方法
            this.$confirm('确认删除?','提示',{
				confirmButtonText:'确定',
				cancelButtonText:'取消',
				type: 'warning'
			}).then(()=>{
                this.$emit('handleDelete',row)
			})
        },
        handleSizeChange(){ //分页每页数量改变时调用方法
            this.page.page=1
            this.pageChange()
        },
        handleCurrentChange(){ //分页跳转条用方法
            this.pageChange()
        },
        pageChange(){ //分页信息改变时调用方法
            this.$emit('pageChange')
        },
        getSelection(){ //获取多选的数据，父调用this.$refs.xxx.getTableSelection()
            return this.$refs.pi_table.selection
        },
        handleSelectionChange(val) {
            this.multipleSelection=val;
        },
        handleButton(row,type,index){
            this.$emit('handleButton',{row,type,index})
        }
	}
}
</script>
<style lang="less">
.pi_table .el-pagination{
    margin-top: 20px;
}
.pi_table_item{
    border: 1px solid #91d5ff;
    background: #e6f7ff;
    padding: 10px 20px;
    border-radius: 4px;
    margin-bottom: 10px;
    span{
        padding: 0 2px;
        color: #1890ff;
        font-weight: 600;
    }
}

// 按钮颜色
.pi_table .iconBlue .iconfont{
  color: #0091FF;
  font-size: 16px;
}
.pi_table .iconGreen .iconfont{
  color: #3CBD28;
  font-size: 16px;
}
.pi_table .iconGray .iconfont{
  color: #999;
  font-size: 16px;
}
.pi_table .iconOrange .iconfont{
  color: #f5ae2f;
  font-size: 16px;
}
.pi_table .iconRed .iconfont{
  color: #f56c6c;
  font-size: 16px;
}
.tableIcon{
  cursor: pointer;
  margin: 0 10px;
}
.icon-recall{
  font-weight: bold;
}
.tableIcon .iconfont{
  font-size: 16px;
}
.iconBlue .iconfont{
  color: #0091FF;
  font-size: 16px;
}
.iconGreen .iconfont{
  color: #3CBD28;
  font-size: 16px;
}
.iconGray .iconfont{
  color: #999;
  font-size: 16px;
}
.iconOrange .iconfont{
  color: #f5ae2f;
  font-size: 16px;
}
.iconRed .iconfont{
  color: red;
  font-size: 16px;
}
</style>
