
export const mixin={
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
        }
    }
}
