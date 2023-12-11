<template>
  <div class="app-container">
    <div class="filter-container">

      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        {{ $t('table.add') }}
      </el-button>
      <el-button style="margin-left: 10px;" class="filter-item" type="primary" icon="el-icon-s-order" @click="handleListDish">
        {{ $t('table.make_list') }}
      </el-button>
      <el-button v-if="isListDish" style="margin-left: 10px;" class="filter-item" type="primary" icon="el-icon-s-promotion" @click="handleSubmitListDish">
        {{ $t('table.confirm') }}
      </el-button>
      <p>Đặt thời gian</p>
      <el-time-picker v-model="value1" :picker-options="{ selectableRange: '0:00:00 - 23:59:59'}" placeholder="Arbitrary time"></el-time-picker>
    </div>

    <el-table
      ref="selectRestaurant"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
      @selection-change="handleSelectionChange"
    >
      <el-table-column v-if="isListDish" type="selection" align="center" />
      <el-table-column :label="$t('table.id')" prop="id" sortable="custom" align="center" width="80" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column label="Tên cửa hàng" min-width="150px">
        <template slot-scope="{row}">
          <span >{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Link" min-width="150px" align="center">
        <template slot-scope="{row}">
          <a :href=row.link target="_blank"><span class="link-type">{{ row.link }}</span></a>
        </template>
      </el-table-column>
      <el-table-column label="Mã cửa hàng" min-width="50px">
        <template slot-scope="{row}">
          <span >{{ row.shop_code }}</span>
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

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="temp" label-position="left" label-width="150px" style="width: 400px; margin-left:50px;">
        <el-form-item label="ShopID" prop="title">
          <el-input v-model="temp.shop_code" placeholder="E.g. 6524" style="width: 500px;" />
        </el-form-item>
        <el-form-item label="Tên Cửa Hàng" prop="title">
          <el-input v-model="temp.name" placeholder="E.g. Gimbab Hàn Quốc - Ngọc Khánh" style="width: 500px;" />
        </el-form-item>
        <el-form-item label="Link" prop="title">
          <el-input v-model="temp.link" placeholder="E.g. https://shopeefood.vn/ha-noi/gimbab-han-quoc-ngoc-khanh" style="width: 500px;" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          {{ $t('table.cancel') }}
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          {{ $t('table.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, createRestaurant, updateRestaurant, deleteRestaurant,makeListRestaurantToday } from '@/api/article'
import waves from '@/directive/waves' // waves directive
import { getDate, parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination


// arr to obj, such as { CN : "China", US : "USA" }

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      value1: new Date(2016, 9, 10, 10, 30),
      isListDish: false,
      list: null,
      total: 0,
      listLoading: true,
      multipleSelection: [],
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id'
      },
      temp: {
        id: undefined,
        shop_code: undefined,
        name:undefined,
        link:undefined,
        status:1
      },
      dialogFormVisible: false,
      dialogStatus: '',
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
      fetchList(this.listQuery).then((response) => {
        this.list = response.data
        console.log(this.list)
        this.total = response.data.length
        // Just to simulate the time of the request
      })
      setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    handleSubmitListDish(){
      if(this.multipleSelection.length){
        const list  = this.multipleSelection
        const data = this.formatJson(list)
        makeListRestaurantToday(data)
        this.$message({
          message: 'Lên danh sách thành công',
          type: 'success'
        })
        this.isListDish = !this.isListDish
      }else{
        this.$message({
          message: 'Bạn chưa chọn cửa hàng',
          type: 'warning'
        })
      }
    },
    formatJson(jsonData) {
      return jsonData.map(v => {
        var Obj = {}
        Obj['res_id'] = v['id'];
        Obj['date'] = parseTime(new Date());
        return Obj;
      })
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          createRestaurant(this.temp).then(() => {
            this.dialogFormVisible = false
            this.$notify({
              title: 'Thành công',
              message: 'Thêm thành công',
              type: 'success',
              duration: 2000
            })
          })
        }
        this.getList()
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          updateRestaurant(tempData).then(() => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Thành',
              message: 'Sửa thành công',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row, index) {
      row.status = 0
      deleteRestaurant(row)
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
        shop_code: undefined,
        name:undefined,
        link:undefined,
        status:1
      }
    },
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}` ? 'ascending' : 'descending'
    }
  }
}
</script>
