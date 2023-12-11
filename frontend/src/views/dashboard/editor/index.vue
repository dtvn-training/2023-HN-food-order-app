<template>
  <div class="app-container">
    <!-- $t is vue-i18n global function to translate lang -->
    <el-button style="margin-bottom:20px" type="primary" icon="el-icon-document">
      Thông tin thanh toán
    </el-button>
    <el-button style="margin-bottom:20px" type="danger">
      Thanh toán
    </el-button>
    <el-tag style="margin-bottom:20px; margin-left:20px" type="success">
      10:30
    </el-tag>
    <el-table ref="multipleTable" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row
      @selection-change="handleSelectionChange">
      <el-table-column v-if="!submit" type="selection" align="center" />
      <el-table-column align="center" label="Id" width="95" />
      <el-table-column label="Tên món Ăn" />
      <el-table-column label="Giá" width="110" align="center" />
      <el-table-column width="110px" label="Số lượng">
        <template slot-scope="{row}">
          <template v-if="!submit">
            <el-input v-model="row.number" type="number" class="edit-input" size="small" />
          </template>
          <span v-else text-align:center>{{ row.number }}</span>
        </template>
      </el-table-column>

    </el-table>
    <el-tag style="float:right; margin-right: 120px;margin-top:20px" type="warning">
      Tổng tền:
    </el-tag>
    <el-button style="margin-top:20px; float: center;margin-right: 20px;" type="primary" align="left"
      @click="handleSubmit">
      Submit
    </el-button>
    <el-button style="margin-top:20px; float: center;margin-right: 20px;" type="success" align="left"
      @click="handleEdit">
      Edit
    </el-button>
  </div>
</template>

<script>
import { fetchList } from '@/api/article'

export default {
  name: 'SelectExcel',
  data() {
    return {
      list: null,
      listLoading: true,
      multipleSelection: [],
      downloadLoading: false,
      filename: '',
      originNumber: 0,
      submit: false
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.listLoading = false
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    handleSubmit() {
      this.submit = true
      this.$message({
        message: 'Đã Đặt Món Ăn',
        type: 'success'
      })
    },
    handleEdit() {
      this.submit = false
    }
  }
}
</script>
