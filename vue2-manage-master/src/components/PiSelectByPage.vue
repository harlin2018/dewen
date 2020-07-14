<template>
    <el-select
        :style="{width}"
        ref="piSelect"
        :placeholder="placeholder"
        :filterable="!!searchKey"
        :remote="!!searchKey"
        :clearable="clearable"
        :popper-class="defaultQuery.totalPage>1?'pi_select_list':''"
        :disabled="disabled"
        :remote-method="showList"
        :loading="loading"
        @focus="showList('')"
        @change="selectChange"
        v-model.trim="form[formProp]">
        <el-option
            v-for="item in options"
            :key="item[selectValue]"
            :label="item[selectLabel]"
            :value="item[selectValue]">
        </el-option>
        <div class="pi_select" v-show="defaultQuery.totalPage>1">
            <el-button type="text" :disabled="defaultQuery.page==1" @click.stop="changePage(true)">上一页</el-button>
            <el-button type="text" :disabled="defaultQuery.page==defaultQuery.totalPage" @click.stop="changePage(false)">下一页</el-button>
            <span class="pi_total">{{defaultQuery.page}}/{{defaultQuery.totalPage}}</span>
        </div>
    </el-select>
</template>

<script>
export default {
    name:'TableBase',
    components:{},
    props:{
        autoSetting:{
            type:Boolean,
            default:false
        },
        formProp:{         //校验绑定的keyName
            type:String,
            default:''
        },
        selectApi:{       //获取数据的api
            type:Function
        },
        selectData:{
            type:Array,
            default(){
                return []
            }
        },
        searchKey:{     //搜索传的keyName
            type:String,
            default:''
        },
        searchParams:{          //表单数据
            type:Object,
            default(){
                return {}
            }
        },
        selectValue:{           //select value的keyName
            type:String,
            default:''
        },
        selectLabel:{         //下拉显示的keyName
            type:String,
            default:''
        },
        customLabel:{       //自定义select label名
            type:String,
            default:''
        },
        apiLabel:{       //若只给id，则根据api查回显的值
            type:Function
        },
        apiQuery:{      //api是否传query而不是{}
            type:Boolean,
            default:false
        },
        disabled:{      //是否禁止
            type:Boolean,
            default:false
        },
        clearable:{      //是否禁止
            type:Boolean,
            default:false
        },
        cascader:{      //是否需要级联
            type:Boolean,
            default:false
        },
        cascaderParams:{    //级联需要的参数，配合cascader使用
            type:Object,
            default(){
                return {}
            }
        },
        all:{      //change时是否返回选中的整个对象
            type:Boolean,
            default:false
        },
        assets:{      //库存进来的
            type:Boolean,
            default:false
        },
        selected:{      //是否绑定选中的对象 :selected.sync='xxx'
            type:Object
        },
        form:{          //表单数据   :form.sync='xxx'
            type:Object,
            default(){
                return {}
            }
        },
        placeholder:{
            type:String,
            default:''
        },
        width:{
            type:String,
            default:''
        },
        limit:{ //一页数量
            type:String,
            default:'10'
        }
    },
    data() {
        return {
            loading:false,
            options:[],
            defaultQuery:{
				limit:this.limit-0,
                page:1,
                sort:'id|desc',
                totalPage:1
            }
        }
    },
    created(){
        this.$set(this.defaultQuery,this.searchKey,'')
    },
    mounted(){
        // this.setDefaultValue()
    },
	methods:{
        focus(){
            this.$refs.piSelect.focus()
        },
        selectChange(value){
            if(this.autoSetting){
                let item=this.options.find(item=>item[this.selectValue]==value)
                this.form[this.formProp+'Name']=item?item[this.selectLabel]:''
            }
            if(this.all){
                let item=this.options.find(item=>item[this.selectValue]==value)
                let labelName=this.selectLabel
                this.$emit('selectChange',{value,item,labelName})
            }else{
                this.$emit('selectChange',value)
            }
            if(this.selected){
                let item=this.options.find(item=>item[this.selectValue]==value)
                item&&this.$emit('update:selected',item)
            }
        },
        resetList(){
            this.options=[]
        },
        showList(val){
            this.defaultQuery[this.searchKey]=val
            if(!val&&this.form[this.formProp]){
                this.loading=true
                setTimeout(()=>{
                    this.getList()
                },300)
            }else{
                this.getList()
            }
        },
        // 切换页码
        changePage(bool){
            if(bool){
                this.defaultQuery.page--
            }else{
                this.defaultQuery.page++
            }
            this.getList(true)
        },
        // 获取下拉的列表
        getList(bool){
            if(this.selectApi){
                if(!bool) this.defaultQuery.page=1
                let params={}
                if(this.searchParams) Object.assign(params,this.defaultQuery,this.searchParams)
                if(this.cascader) Object.assign(params,this.cascaderParams)
                this.loading=true
                this.selectApi(params).then(res=>{
                    if(res.resultCode==0&&res.data){
                        let arr=res.data.items||res.data
                        if(arr.length>0&&typeof arr[0]!='object'){  //若数组不是对象数组，则强行转为对象数组
                            arr=arr.map(item=>{
                                let d={}
                                d[this.selectValue]=item
                                return d
                            })
                        }
                        this.options=arr
                        this.defaultQuery.totalPage=res.data.totalPages
                    }else{
                        this.options=[]
                    }
                    this.loading=false
                }).catch((err)=>{
                    this.loading=false
                })
            }else if(this.selectData.length>0){
                this.options=this.selectData
                this.defaultQuery.totalPage=0
            }
        },
        setDefaultValue(){
            if(this.selectValue!=this.selectLabel&&this.form[this.formProp]){   //value和label不同时
                let labelTmp=this.customLabel?this.customLabel:this.selectLabel
                let obj={}
                obj[this.selectValue]=this.form[this.formProp]
                if(this.apiLabel){  //下拉显示label需要用api查的时候
                    let params={}
                    params[this.selectValue]=this.form[this.formProp]
                    if(this.apiQuery) params=this.form[this.formProp]
                    this.apiLabel(params).then(res=>{
                        if(res.resultCode==0){
                            obj[this.selectLabel]=res.data[labelTmp]
                            this.options=[obj]
                        }
                    })
                }else{  //为下拉构建一条数据
                    obj[this.selectLabel]=this.form[labelTmp]
                    this.options=[obj]
                }

            }
        }
    },
    watch:{
        form(){
            this.setDefaultValue()
        },
        // formPropChange(){
        // }
    }
}
</script>
<style lang="less" scoped>
.pi_select{
    width: 100%;
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    left: 0;
    bottom: 0;
    background: #fff;
    z-index: 9;
    .pi_btn{
        color: #1890ff;
        margin: 0 6px;
        cursor: pointer;
        text-decoration: underline;
        font-weight: 700;
    }
    .pi_total{
        columns: #C0C4CC;
        padding-left: 10px;
        padding-top: 4px;
    }
}
.pi_select_list .el-select-dropdown__list{
    padding-bottom: 40px;
}
</style>
