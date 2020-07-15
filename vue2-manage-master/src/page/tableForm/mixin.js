
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
