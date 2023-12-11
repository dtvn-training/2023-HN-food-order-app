<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.name" :placeholder="$t('history.employee')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.status" :placeholder="$t('table.status')" clearable style="width: 110px; margin-left: 10px;" class="filter-item">
        <el-option v-for="item in statusTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
      </el-select>
      <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-search" @click="handleFilter">
        {{ $t('table.search') }}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="warning" icon="el-icon-edit">
        {{ $t('table.approve_all') }}
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-upload2" @click="handleDownload">
        {{ $t('table.export') }}
      </el-button>
      <el-tag style="float: right; margin-right: 20px; margin-top: 10px" type="warning" v-model="totalDebt">
      Tổng tiền còn nợ: {{ totalDebt }}
      </el-tag>

    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="STT" prop="id" align="center" width="80">
        <template slot-scope="{$index}">
          <span>{{ $index+1 }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('history.employee')" min-width="70px" align="center">
        <template slot-scope="{row}">
          <span>{{ row[1] }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('history.dish')" min-width="100px">
        <template slot-scope="{row}">
          <span >{{ row[2] }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('history.restaurant')" min-width="100px" align="center">
        <template slot-scope="{row}">
          <span>{{ row[3] }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('history.link')" width="50px">
        <template slot-scope="{row}">
          <span class="link-type" ><a :href="row[4]" target="_blank" rel="noreferrer"> Link </a></span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.date')" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row[5] }}</span>
        </template>
      </el-table-column>

      <el-table-column :label="$t('history.number')" align="center" width="80px">
        <template slot-scope="{row}">
          <span>{{ row[6] }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('history.money')" align="center" width="80px">
        <template slot-scope="{row}">
          <span>{{ row[6]*row[7] }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center" min-width="90" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <el-button style="margin-bottom: 20px" icon="el-icon-loading" type="warning" v-if="row[8]==0" @click="confirmPay(row)" >
            Chờ Duyệt
          </el-button>  
          <el-button style="margin-bottom: 20px" icon="el-icon-position" type="danger" v-else-if="row[8]==-1">
            {{ $t('home.pay') }}
          </el-button>
          <el-button style="margin-bottom: 20px" icon="el-icon-circle-check" type="success" v-else>
            Đã trả
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  </div>
</template>

<script>
import { getAllInforOrder,confirmPay } from '@/api/article'
import waves from '@/directive/waves' // waves directive
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
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      totalDebt:0,
      listQuery: {
        page: 1,
        limit: 20,
        status: '',
        name: ''
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
      getAllInforOrder(this.listQuery).then(response => {
        this.list = response.data
        console.log(this.list)
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
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['Nhan vien', 'Ten mon', 'Cua hang', 'Link', 'Date', 'So luong', 'Gia', 'Trang thai']
        const filterVal = [1,2,3,4,5,6,7,8]
        const data = this.formatJson(filterVal)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'order-list'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j == 8) {
          if (v[j] == -1) {
            return 'Chưa thanh toán'
          } else if (v[j] == 0) {
            return 'Đang xử lý'
          } else {
            return 'Đã thanh toán'
          }
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
    confirmPay(row) {
      row[8] = 1
      var obj = {
        id: row[0]
      }
      confirmPay(obj).then((response) => {
        this.$message({
          message: 'Your submit has been send',
          type: 'success'
        })
      }); 
    }
  }
}
</script>
