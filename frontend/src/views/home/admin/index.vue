<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.priceShip" :placeholder="$t('home.ship')" style="width: 200px;"
        class="filter-item" />
      <el-input v-model="listQuery.voucher" :placeholder="$t('home.voucher')" style="width: 200px; margin-left: 10px;"
        class="filter-item" />
      <el-input v-model="listQuery.fee" :placeholder="$t('home.fee')" style="width: 200px; margin-left:10px"
        class="filter-item" />
      <el-button class="filter-item" type="primary" icon="el-icon-position" style="margin-left: 10px;" @click="handleSubmitPrice">
        {{ $t('home.submit') }}
      </el-button>
      <el-tag style="margin-left: 10px" type="success" v-if="statusOrder">Đã đặt món</el-tag>
      <el-tag style="margin-left: 10px" type="warning" v-else>Chưa đặt món</el-tag>

    <h2>Cửa hàng đã chọn</h2>
    <el-table v-loading="listLoading" :data="listRestaurant" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="STT" width="80">
        <template slot-scope="{$index}">
          <span>{{ $index + 1 }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" align="center" label="Cửa hàng">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="200px" label="Link">
        <template slot-scope="{row}">
          <a :href=row.link target="_blank"><span class="link-type">{{ row.link }}</span></a>
        </template>
      </el-table-column>
    </el-table>


    <br>
    <br>


    </div>
    <h2>Danh sách món ăn được đặt</h2>
    <el-table v-loading="listLoading" :data="listDishOrdered" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="STT" width="80">
        <template slot-scope="{$index}">
          <span>{{ $index }}</span>
        </template>
      </el-table-column>

      <el-table-column width="150px" align="center" label="Ảnh hiển thị">
        <template slot-scope="{row}">
          <span><img :src="row[0]" style="width: 60px;"></span>
        </template>
      </el-table-column>

      <el-table-column min-width="200px" label="Tên món ăn">
        <template slot-scope="{row}">
          <span>{{ row[1] }}</span>
        </template>
      </el-table-column>
      <el-table-column min-width="200px" label="Cửa hàng">
        <template slot-scope="{row}">
          <span>{{ row[2] }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="Link" width="150px">
        <template slot-scope="{row}">
          <span class="link-type">
            <a :href="row[3]" target="_blank" rel="noreferrer" > Link </a>
          </span>
        </template>
      </el-table-column>

      <el-table-column width="100px" label="Số lượng" align="center">
        <template  slot-scope="{row}">
          <span>  {{ row[4] }}</span>
        </template>
      </el-table-column>

      <el-table-column width="90px" label="Tổng Giá Tạm Tính">
        <template slot-scope="{row}">
          <span>{{ row[5] }}</span>
        </template>
      </el-table-column>
    </el-table>


    <br>
    <br>


    <h2>Danh sách nhân viên đặt món</h2>
    <el-table v-loading="listLoading" :data="listEmployOrdered" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="STT" width="80">
        <template slot-scope="{$index}">
          <span>{{ $index }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="40px" align="center" label="Nhân viên">
        <template slot-scope="{row}">
          <span>{{ row[1] }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="80px" align="center" label="Món ăn">
        <template slot-scope="{row}">
          <span>{{ row[2] }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="80px" align="center" label="Cửa hàng">
        <template slot-scope="{row}">
          <span>{{ row[3] }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="Link" width="80">
        <template slot-scope="{row}">
          <span class="link-type">
            <a :href="row[4]" target="_blank" rel="noreferrer" > Link </a>
          </span>
        </template>
      </el-table-column>

      <el-table-column width="100px" label="Số lượng" align="center">
        <template slot-scope="{row}">
          <span>{{ row[5] }}</span>
        </template>
      </el-table-column>

      <el-table-column width="70px" label="Giá">
        <template slot-scope="{row}">
          <span v-if="row[8]">{{ row[8] }}</span>
          <span v-else>{{ row[6] }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="Trạng thái" width="120">
        <template slot-scope="{row}">
          <el-button v-if="row[7] == 1" type="success" size="small" icon="el-icon-circle-check">
            Pay
          </el-button>
          <el-button v-else-if="row[7] == -1" type="danger" size="small" icon="el-icon-circle-close">
            Not Pay
          </el-button>
          <el-button v-else type="warning" size="small" icon="el-icon-loading" @click="confirmPay(row)">
            Approve
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {getListRestaurantToday,getDishGroubBy, getAllDishOrderedByEmpls, setPrice, confirmPay} from '@/api/article'

export default {
  name: 'InlineEditTable',
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
      statusOrder: false,
      listRestaurant: null,
      listDishOrdered: null,
      listEmployOrdered: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        priceShip: null,
        voucher: null,
        fee: null,
      }
    }
  },
  created() {
    this.getAllDishOrderedByEmployee()
    this.getDishGroupBy()
    this.getRestaurant()
  },
  methods: {
    getRestaurant(){
      this.listLoading = true
      getListRestaurantToday().then((response) => {
        const items = response.data
        this.listRestaurant = items.map(v => {
          this.$set(v, 'edit', false) // https://vuejs.org/v2/guide/reactivity.html
          v.originalTitle = v.title //  will be used when user click the cancel botton
          return v
        })
      })
      setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
    },
    getDishGroupBy() {
      this.listLoading = true;
      getDishGroubBy().then((response) => {
        this.listDishOrdered = response.data;
      });
      setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
    },
    getAllDishOrderedByEmployee() {
      this.listLoading = true;
      getAllDishOrderedByEmpls().then((response) => {
        this.listEmployOrdered = response.data;
        console.log(this.listEmployOrdered)
        if(this.listEmployOrdered[0][8]){
          this.statusOrder = true
        }
      });
      setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
    },
    handleSubmitPrice() {
      var obj = {
        fee: Number(this.listQuery.fee),
        feeShip: Number(this.listQuery.priceShip),
        voucher: Number(this.listQuery.voucher)
      }
      setPrice(obj).then((response) => {
        this.getAllDishOrderedByEmployee()
        this.$message({
          message: 'Your submit has been send',
          type: 'success'
        })
      });
    },
    cancelEdit(row) {
      row.title = row.originalTitle
      row.edit = false
      this.$message({
        message: 'The title has been restored to the original value',
        type: 'warning'
      })
    },
    confirmPay(row) {
      row[7] = 1
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

<style scoped>
.edit-input {
  padding-right: 100px;
}

.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
