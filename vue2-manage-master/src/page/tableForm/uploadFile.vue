<template>
	<div class="myUploadFile">
        <input ref="myFileInput" accept="application/pdf" type="file" style="display:none" @change="selectedFile">
        <el-button type="text" @click="triggerFileChange">上传附件</el-button>
	</div>
</template>

<script>
import {uploadFileData} from '@/api/getData'
import {uploadFile} from '@/api/common'
export default {
	name:'uploadFile',
	props:{
		disabled:{
			type:Boolean,
			default:false
		},
		multi:{
			type:Boolean,
			default:true
		},
		limit:{
			type:[String,Number],
			default:10
		},
	},
	data() {
		return {
			base:process.env.VUE_APP_GATEWAY_API
		}
	},
	methods:{
		selectedFile(ev){
			let file=ev.target.files[0]
			if(file.size>this.limit*1024*1024){
				this.$message({message:'附件不能超过10M',type:'warning'})
				return
            }
            let arr=file.name.split('.')
            if(arr[arr.length-1].toLowerCase()!='pdf'){
				this.$message({message:'只允许上传pdf文件',type:'warning'})
                return
            }
            // this.$emit('selectedFile',file)
            this.uploadFile(file)
		},
		triggerFileChange(){
			this.$refs.myFileInput.value=null
			this.$refs.myFileInput.click()
        },
        uploadFile(file){
			var formData=new FormData()
            formData.append('file',file)
			uploadFile(formData).then(res => {
				if(res.resultCode==0){
					this.$message({
						message:'上传成功',
						type:'success'
					})
					let fileData=res.payload[0]
					this.$emit('success',res.payload[0])
				}else{
					this.$message({
						message:'上传失败',
						type:'error'
					})
				}
			})
        }
	}
}
</script>
<style lang="less">
.myUploadFile{
}

</style>
