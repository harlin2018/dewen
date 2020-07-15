<template>
	<div class="myUploadFile">
        <input ref="myFileInput" accept="" type="file" style="display:none" @change="selectedFile">
        <el-button type="text" @click="triggerFileChange">上传附件</el-button>
	</div>
</template>

<script>
import {uploadFileData,createCompanyData} from '@/api/getData'
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
			default:2,
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
				this.$message({message:'附件不能超过2M',type:'warning'})
				return;
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
			uploadFileData(formData).then(response => {
				if(response.resultCode==0){
					this.$message({
						message:'上传成功',
						type:'success'
					})
					let fileData=response.data[0]
					this.$emit('success',response.data)
					let params={
						fileRefId:fileData.id,
						fileName:fileData.fileName,
					}
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
