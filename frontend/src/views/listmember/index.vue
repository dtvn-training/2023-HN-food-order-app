<template>
  <div class="app-container">
    <h2>Danh sách nhân viên chờ phê duyệt</h2>
    <br>
    <el-table
      v-loading="listLoading"
      :data="listUserNotApprove"
      border
      fit
      highlight-current-row
      style="width: 80%;margin-left: 50px;"
    >
      <el-table-column label="STT" prop="id" align="center" width="80" >
        <template slot-scope="{$index}">
          <span>{{ $index+1 }}</span>
        </template>
      </el-table-column>

      <el-table-column label="User" min-width="50px" align="center">
        <template slot-scope="{row}">
          <span >{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Name User" min-width="50px" align="center">
        <template slot-scope="{row}">
          <span >{{ row.fullname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Mật khẩu" min-width="80px" align="center">
        <template slot-scope="{row}">
          <span >{{ row.password }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="approveData(row)">
            Approve
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)">
            Reject
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <br>
    <br>


    <h2>Danh sách nhân viên</h2>
    <br>
    <el-table
      v-loading="listLoading"
      :data="listUserApprove"
      border
      fit
      highlight-current-row
      style="width: 80%;margin-left: 50px;"
    >
    <el-table-column label="STT" prop="id" align="center" width="80" >
        <template slot-scope="{$index}">
          <span>{{ $index+1 }}</span>
        </template>
      </el-table-column>

      <el-table-column label="User" min-width="50px" align="center">
        <template slot-scope="{row}">
          <span >{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Name User" min-width="50px" align="center">
        <template slot-scope="{row}">
          <span >{{ row.fullname }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Mật khẩu" min-width="80px" align="center">
        <template slot-scope="{row}">
          <span >{{ row.password }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            {{ $t('table.edit') }}
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)">
            {{ $t('table.delete') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="Edit" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="150px" style="width: 400px; margin-left:50px;">
        <el-form-item label="User" prop="title">
          <el-input v-model="temp.name" placeholder="Name" style="width: 500px;" />
        </el-form-item>
        <el-form-item label="User Name" prop="title">
          <el-input v-model="temp.fullname" placeholder="User Name" style="width: 500px;" />
        </el-form-item>
        <el-form-item label="Password" prop="title">
          <el-input v-model="temp.password" placeholder="Password" style="width: 500px;" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          {{ $t('table.cancel') }}
        </el-button>
        <el-button type="primary" @click="updateData()">
          {{ $t('table.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getMembers,updateMember } from '@/api/role'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination


export default {
  name: 'ComplexTable',
  components: { Pagination },
  data() {
    return {
      isListDish: false,
      listUserApprove: null,
      listUserNotApprove:null,
      total: 0,
      listLoading: true,
      multipleSelection: [],
      temp: {
        id: undefined,
        fullname: undefined,
        loan:undefined,
        name:undefined,
        password: undefined,
        role: undefined,
        status: undefined
      },
      dialogFormVisible: false,

      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    handleListDish() {
      this.isListDish = !this.isListDish
    },
    getList() {
      this.listLoading = true
      getMembers().then((response) => {
        this.listUserNotApprove = response.data[0]
        this.listUserApprove = response.data[1]
        console.log(this.listUserApprove)
        this.total = response.data.length
        // Just to simulate the time of the request
      })
      setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
    },
    formatJson(jsonData) {
      return jsonData.map(v => {
        var Obj = {}
        Obj['res_id'] = v['id'];
        Obj['date'] = parseTime(new Date());
        return Obj;
      })
    },
    handleUpdate(row) {
      this.temp.id = row.id
      this.temp.fullname = row.fullname
      this.temp.name = row.name
      this.temp.password = row.password
      this.temp.loan = row.loan
      this.temp.role = row.role
      this.temp.status = row.status
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          updateMember(tempData).then(() => {
            const index = this.listUserApprove.findIndex(v => v.id === this.temp.id)
            this.listUserApprove.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Thành Công',
              message: 'Sửa thành công',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    approveData(row){
      this.temp.id = row.id
      this.temp.fullname = row.fullname
      this.temp.name = row.name
      this.temp.password = row.password
      this.temp.loan = row.loan
      this.temp.role = row.role
      this.temp.status = 'active'
      const tempData = Object.assign({}, this.temp)
          updateMember(tempData).then(() => {
            const index = this.listUserNotApprove.findIndex(v => v.id === this.temp.id)
            this.listUserNotApprove.splice(index, 1)
            this.listUserApprove.splice(this.listUserApprove.length,0,tempData)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Thành Công',
              message: 'Approve thành công',
              type: 'success',
              duration: 2000
            })
        })
    },
    handleDelete(row, index) {
      row.status = 0
      deleteUser(row)
      this.$notify({
        title: 'Thành công',
        message: 'Xóa thành công',
        type: 'success',
        duration: 2000
      })
      this.list.splice(index, 1)
    },
    
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        fullname: undefined,
        loan:undefined,
        name:undefined,
        password: undefined,
        role: undefined,
        status: undefined
      }
    },
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}` ? 'ascending' : 'descending'
    }
  }
}
</script>
