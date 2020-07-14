
export const mixin={
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
        addRow(prop){
            this.$emit('addRow',prop)
        },
        removeRow(prop,index){
            this.$emit('removeRow',{prop,index})
        }
    }
}
