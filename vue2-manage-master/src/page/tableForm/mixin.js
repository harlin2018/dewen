const imageSuffix=['jepg','jpg','png','gif','bmp']
export const mixin={
    props:{
        mainForm:{
            type:Object,
            default(){
                return {
                }
            }
        },
        disabled:{
            type:Boolean,
            default:false
        },
        download:{
            type:Boolean,
            default:false
        }
    },
    data(){
        return {
        }
    },
    computed:{
        isAdmin(){
            let info=this.$store.state.adminInfo
            return info&&info.userName=='admin'
        }
    },
    created(){
    },
    methods:{
        getTabData(){
            return new Promise((resolve,reject)=>{
                this.$refs.mainForm.validate(vaild=>{
                    if(vaild){
                        resolve(this.title)
                    }else{
                        reject(this.title)
                    }
                })
            })
        },
        addRow(prop,val,src){
            this.$emit('addRow',{prop,val,src})
        },
        removeRow(prop,index){
            this.$emit('removeRow',{prop,index})
        },
        updateFile(data,prop,index){
            this.$emit('updateFile',{data,prop,index})
        },
        deleteFile(prop,index){
            this.$emit('updateFile',{prop,index})
        },
        previewFile(id){
            this.$emit('previewFile',id)
        },
        checkIsImage(fileName){
			let arr=fileName.split('.')
			let str=arr[arr.length-1]
			if(imageSuffix.includes(str.toLowerCase())){
				return true
			}
			return false
		},
    }
}
