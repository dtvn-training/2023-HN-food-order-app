<template>
  <div class="app-container">
    <div class="filter-container">

      <el-select v-model="listQuery.status" placeholder="Status" clearable class="filter-item" style="width: 130px;margin-right:20px;">
        <el-option v-for="item in statusTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
      </el-select>

      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        {{ $t('history.search') }}
      </el-button>
      <el-tag style="float: right; margin-right: 20px; margin-top: 10px" type="warning" v-model="totalDebt">
      Tổng tiền còn nợ: {{ totalDebt }}
      </el-tag>
    </div>
    <!-- o thoi gian/ o image/ o Mon an / r cua hang / r link / o so luong / o tien / o trang thai -->
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="STT" align="center" width="80">
        <template slot-scope="{$index}">
          <span>{{ $index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('history.time')" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row[1] }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Ảnh" align="center" width="95">
        <template slot-scope="{row}">
          <span><img v-bind:src="row[2]" style="width: 60px;"></span>
        </template>
      </el-table-column>
      <el-table-column label="Món ăn" align="center" min-width="80">
        <template slot-scope="{row}">
          <span>{{ row[3] }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Cửa hàng" align="center" min-width="80">
        <template slot-scope="{row}">
          <span>{{ row[4] }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Link" align="center" width="95">
        <template slot-scope="{row}">
          <span class="link-type">
            <a :href="row[5]" target="_blank" rel="noreferrer"> Link </a>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="Số lượng" align="center" width="95">
        <template slot-scope="{row}">
          <span>{{ row[6] }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Tổng Tiền" align="center" width="95">
        <template slot-scope="{row}">
          <span>{{ row[7]*row[6] }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Trạng thái" align="center" width="150">
        <template slot-scope="{row}">
          <el-button style="margin-bottom: 20px" icon="el-icon-loading" type="warning" v-if="row[8]==0" >
            Chờ Duyệt
          </el-button>  
          <el-button style="margin-bottom: 20px" icon="el-icon-position" type="danger" v-else-if="row[8]==-1" @click="handlePay(row)">
            {{ $t('home.pay') }}
          </el-button>
          <el-button style="margin-bottom: 20px" icon="el-icon-circle-check" type="success" v-else-if="row[8]==1">
            Đã trả
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    
  </div>
</template>

<script>
import { getAllInforOrderByUser,paid} from '@/api/article'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

const statusTypeOptions = [
  { key: '-1', display_name: 'NotPay' },
  { key: '0', display_name: 'Process' },
  { key: '1', display_name: 'Paid' }  
]

// arr to obj, such as { CN : "China", US : "USA" }
// const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
//   acc[cur.key] = cur.display_name
//   return acc
// }, {})

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        Paid: 'success',
        Process: 'info',
        NotPay: 'danger'
      }
      return statusMap[status]
    }
    // typeFilter(type) {
    //   return calendarTypeKeyValue[type]
    // }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      totalDebt:0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        status: '',
      },
      statusTypeOptions,
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getAllInforOrderByUser(this.listQuery).then(response => {
        console.log(response.data);
        this.list = response.data
        this.total = response.data.length
        this.totalDebt = this.computeTotalDebt(this.list)
        // Just to simulate the time of the request
        
      })
      setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: 'Thành công',
        type: 'success'
      })
      row.status = status
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    computeTotalDebt(list){
      var totalDebt = 0;
      list.forEach((item) => {
        if(item[8] == -1)
        totalDebt += item[6]*item[7]
        });
        return totalDebt;
    },
    handlePay(row) {
      row[8] = 0;
      var obj = {
        id: row[0]
      }
      this.computeTotalDebt(this.list)
      paid(obj).then((response) => {
        this.$message({
          message: "Đang Chờ Xac Nhận",
          type: "success",
        });
      });
    }
  }
}
</script>
