<template>
  <div class="components-container">
    <div >
      <textarea id="w3review" name="w3review" rows="25" cols="100" v-model="list.content"></textarea>
    </div>
    <el-button style="margin-top: 20px; float: center; margin-right: 20px" type="primary" align="left"
      @click="handleSubmit">
      Submit
    </el-button>
  </div>
</template>

<script>
import { getInfor, updateInfor } from "@/api/remote-search";
export default {
  name: 'InforPay',
  data() {
    return {
      list:{
        id: 1,
        content: '',
      },
    }
  },
  created() {
    this.getInfor();
  },
  methods:{
    getInfor(){
      getInfor().then((response) => {
        this.formatJson(response.data[0]);
        console.log(this.list)
      });

    },
    handleSubmit(){
      updateInfor(this.list).then((response) => {
        this.formatJson(response.data);
        this.$message({
          message: 'thành công',
          type: 'success'
        });
      });
    },
    formatJson(data){
      this.list.id = data.id;
      this.list.content = data.content;
    }
  }
}
</script>

  <style scoped>
  .editor-content{
    margin-top: 20px;
  }
  </style>

