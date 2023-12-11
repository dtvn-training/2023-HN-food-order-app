<template>
  <div class="app-container">
    <!-- $t is vue-i18n global function to translate lang -->
    <el-button style="margin-bottom: 20px" type="primary" icon="el-icon-document" v-if="isAdminOrderd" @click="dialogFormVisible=true">
      {{ $t('home.inforpay') }}
    </el-button>
    <el-button style="margin-bottom: 20px" icon="el-icon-loading" type="warning" v-if="pay==0&&isAdminOrderd" >
      Chờ Duyệt
    </el-button>  
    <el-button style="margin-bottom: 20px" icon="el-icon-position" type="danger" v-else-if="pay==-1&&isAdminOrderd" @click="handlePay">
      {{ $t('home.pay') }}
    </el-button>
    <el-button style="margin-bottom: 20px" icon="el-icon-position" type="success" v-else-if="pay==1">
      Đã trả {{ numberAccecptPay }}
    </el-button>
    
    <el-tag style="margin-bottom: 20px; margin-left: 20px" type="success">
      10:30
    </el-tag>

    <h2>Món đã chọn</h2>
    <el-table 
      v-loading="listLoading" 
      :data="listOrderedTemporOrdered" 
      border 
      fit 
      highlight-current-row 
      style="width: 100%"
    >
      <el-table-column align="center" label="STT" width="80">
        <template slot-scope="{$index}">
          <span>{{ $index+1 }}</span>
        </template>
      </el-table-column>

      <el-table-column width="150px" align="center" label="Ảnh">
        <template slot-scope="{row}">
          <span><img v-bind:src="row[3]" style="width: 60px;"></span>
        </template>
      </el-table-column>

      <el-table-column min-width="100px" label="Món ăn">
        <template slot-scope="{row}">
          <span>{{ row[1] }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="Cửa hàng" min-width="100px">
        <template slot-scope="{row}">
          <span>{{ row[5] }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="Link" width="150px">
        <template slot-scope="{row}">
          <span class="link-type">
            <a :href="row[6]" target="_blank" rel="noreferrer"> Link </a>
          </span>
        </template>
      </el-table-column>

      <el-table-column width="100px" label="Giá" align="center">
        <template slot-scope="{row}">
          <span v-if="row[2]===0">{{ row[9] }}</span>
          <span v-else>{{ row[2] }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" label="Số lượng" align="center">
        <template slot-scope="{ row }">
            <span text-align:center>{{ row[7] }}</span>
        </template>
      </el-table-column>
    </el-table>

    <el-tag style="float: right; margin-right: 20px; margin-top: 20px" type="warning" v-model="totalPrice">
      {{ $t('home.totalprice') }}: {{ totalPrice }}
    </el-tag>
    
    <el-button style="margin-top: 20px; float: center; margin-right: 20px" type="primary" align="left"

      @click="handleSubmit" v-if="!submit">

      {{ $t('home.submit') }}
    </el-button>
    <!-- <el-button style="margin-top: 20px; float: center; margin-right: 20px" type="success" align="left"
      @click="handleEdit">
      {{ $t('home.edit') }}
    </el-button> -->

    <br>
    <h2>Món ăn hôm nay</h2>
    <el-table 
      v-loading="listLoading" 
      :data="list" 
      border 
      fit 
      highlight-current-row 
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column v-if="!submit" type="selection" align="center" />
      <el-table-column align="center" label="STT" width="80">
        <template slot-scope="{$index}">
          <span>{{ $index+1 }}</span>
        </template>
      </el-table-column>

      <el-table-column width="150px" align="center" label="Ảnh">
        <template slot-scope="{row}">
          <span><img v-bind:src="row[3]" style="width: 60px;"></span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" label="Món ăn">
        <template slot-scope="{row}">
          <span>{{ row[1] }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Cửa hàng" min-width="90px">
        <template slot-scope="{row}">
          <span>
            {{ row[5] }}
          </span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Link" width="150px">
        <template slot-scope="{row}">
          <span class="link-type">
            <a :href="row[6]" target="_blank" rel="noreferrer"> Link </a>
          </span>
        </template>
      </el-table-column>
      <el-table-column width="100px" label="Giá" align="center">
        <template slot-scope="{row}">
          <span>{{ row[2] }}</span>
        </template>
      </el-table-column>

      <el-table-column width="160px" label="Số lượng" align="center">
        <template slot-scope="{ row }">
          <template v-if="!submit">
            <el-input-number
              v-model="row[7]"
              class="edit-input"
              :min="1"
              :max="100"
              size="small"
              controls-position="right"
            />
          </template>
            <span v-else text-align:center>{{ row[7] }}</span>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="Thông tin thanh toán" :visible.sync="dialogFormVisible">
      <template :data="infor">
        <span>{{ infor.content }}</span>
      </template>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          {{ $t('table.cancel') }}
        </el-button>
      </div>
      <div>
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJmokOMM42J9qA2w5wVZVc5gupfk1_QAkJpQ&usqp=CAU" style="height: 120px; width: 120px;" alt="">
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { getDish,order,getAllDishOrderedByEmpl, paid } from "@/api/article";

import { getInfor } from "@/api/remote-search";

export default {
  name: "SelectExcel",
  data() {
    return {
      numberAccecptPay: 0,
      isAdminOrderd: false,
      pay: -1,
      listOrderedTemporOrdered: null,
      totalPrice: 0,
      listOrderedTemp: null,
      listOrdered: null,
      list: null,
      listLoading: true,
      multipleSelection: [],
      downloadLoading: false,
      filename: "",
      originNumber: 0,
      submit: false,
      dialogFormVisible: false,
      infor:{
        id: 1,
        content:''
      }

    };
  },
  created() {
    this.fetchData();
    this.getDishOrdered();
    this.getInfor()
  }, 
  methods: {
    fetchData() {
      this.listLoading = true;
      getDish().then((response) => {     
        this.list = response.data[0];
      });
      setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
    },
    getDishOrdered(){
      this.listLoading = true;
      getAllDishOrderedByEmpl().then((response) => {
        this.listOrdered = response.data;
        this.isOrdered()
        this.totalPrice =  this.computeToTalPrice(this.listOrderedTemporOrdered)
      });
      setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
    },
    isOrdered(){

      console.log(this.listOrdered)

      if (this.listOrdered.length > 0){
        this.listOrderedTemporOrdered = this.listOrdered;
        this.submit = true
        if(this.listOrdered[0][2] != 0){
          this.isAdminOrderd = true;
        }
        if(this.listOrdered[0][8] == 0){
          this.pay = 0
        }
        this.checkPay(this.listOrdered)
      }else{
        this.listOrderedTemporOrdered = this.listOrderedTemp;
      }
      
    },
    getInfor(){
      getInfor().then((response) => {
        this.infor.id = response.data[0].id;
        this.infor.content = response.data[0].content;
      });

    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.listOrderedTemp = this.multipleSelection;
      this.isOrdered()
      this.totalPrice = this.computeToTalPrice(this.listOrderedTemporOrdered)
    },
    handlePay() {
      this.pay = 0;
      var obj = {
        id: null
      }
      paid(obj).then((response) => {
        this.$message({
          message: "Đang Chờ Xác Nhận",
          type: "success",
        });
      });
    },
    handleSubmit() {
      if(this.multipleSelection.length){
        this.submit = true;
        const data = this.formatJson(this.listOrderedTemporOrdered)
        console.log(data)

        order(data).then(() => {
          this.getDishOrdered()
        });
        this.$message({
          message: "Đã Đặt Món Ăn",
          type: "success",
        });
      }else{
        this.$message({
          message: "Chưa Chọn Món Ăn",
          type: "warning",
        });
      }
      
    },
    formatJson(jsonData){
      return jsonData.map((item) => {
        return {
          dish_id: item[0],
          name: item[1],
          price: item[2],
          image_link: item[3],
          id_store: item[4],
          store_name: item[5],
          link: item[6],
          quantity: item[7],
        }
      })
    },
    handleEdit() {
      this.submit = false;
    },
    checkPay(jsonData){
      return jsonData.map((item) => {
        if(item[8]==1){
          this.numberAccecptPay +=1
          return this.pay = 1;
        }
      })
    },
    computeToTalPrice(listOrdered) {
      var totalPrice = 0;
      if(listOrdered == null) return;
      listOrdered.forEach((item) => {
        if(typeof item[2] == "number"){
          if(item[2] !=0){
            totalPrice += item[2] * item[7];
          }else{
            totalPrice += item[9] * item[7];
          }
        }else{
        totalPrice += Number(item[2].replace(",","")) * Number(item[7]);
        }
      });
      return totalPrice;
    }
  }
};
</script>