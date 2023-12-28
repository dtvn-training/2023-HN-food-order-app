<template>
    <div class="order">
        <div class="header">
            <div>
                <div class="back-btn" v-if="isPayment === 1" @click="handleBack">
                <Vector style="margin-right: 11px;"/>
                <span>Back</span>
            </div>
            </div>
            <div class="right-header" v-if="isPayment === 0">
                <button class="order-btn" @click="handleOrder">Order</button>
                <div class="select-all" @click="selectAll">
                <span>Select all</span>
                <Checked v-if="isSelectAll === 1" />
                <Uncheck v-if="isSelectAll === 0" />
            </div>
            </div>
        </div>
        <hr>
        <div class="data">
            <Table v-if="isPayment === 0"
                :datas="datas"
                :columns="columns"
                :actions="actions"
                @onClickAction="handleAction"
            />
            <div class="payment" v-if="isPayment === 1">

            </div>
        </div>
    </div>
</template>
<style scoped>
.order {
    height: 100%;
    display: grid;
    grid-auto-rows: 50px 1px auto;
}

.back-btn{
    width: 90px;
    height: 30px;
    background: rgb(53, 53, 53);
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 5px;
    margin-left: 10px;
    cursor: pointer;
}
.back-btn:hover {
    background: rgb(99, 99, 99);
}
.back-btn span {
    color: white;
    font-size: 14px;
}
.header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    /* background-color: aqua; */
    width: 100%;
    height: 50px;
}
.right-header{
    display: flex;
    flex-direction: row;
}

.order-btn {
    height: 35px;
    width: 123px;
    font-size: 14px;
    background: #00A2D6;
    border: none;
    border-radius: 5px;
    color: white;
    margin-right: 30px;
}

.order-btn:hover {
    background: #30abd4;
    cursor: pointer;
}

.select-all {
    cursor: pointer;
    width: 90px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    margin-right: 10px;
}

.select-all span {
    font-size: 14px;
    color: rgb(88, 88, 88);
}

.select-all:hover {
    font-weight: 500;
}
</style>
<script>
import Checked from './icons/Checked.vue';
import Uncheck from './icons/Uncheck.vue';
import Vector from './icons/Vector.vue';
import Table from './Table.vue'

export default {
    data() {
        return {
            columnsPayment: [
                {
                    key: 'retaurantName',
                    header: 'Quán ăn',
                    style: {
                        width: '35%',
                    },
                },
                {
                    key: 'address',
                    header: 'Địa chỉ',
                    style: {
                        width: '40%',
                    },
                },
                {
                    key: 'shipfee',
                    header: 'Phí ship',
                    style: {
                        width: '150px',
                    },
                },
            ],
            fee: [
                {
                    restaurantName: 'Bún chả phố cổ',
                    address: 'Pho Co, Hà noi',
                    price: 33000,
                    fee: '<input type="number" v-model=>'
                },
                {
                    restaurantName: 'Bún chả phố cổ',
                    address: 'Pho Co, Hà noi',
                    price: 33000,
                    fee: ''
                },
                {
                    restaurantName: 'Bún chả phố cổ',
                    address: 'Pho Co, Hà noi',
                    price: 33000,
                    fee: ''
                },
            ],
            columns: [
                {
                    key: 'orderAt',
                    header: 'Thời gian',
                    style: {
                        width: '150px',
                        "text-align":'center'
                    },
                },
                {
                    key: 'employee',
                    header: 'Nhân viên',
                    style: {
                        width: '20%',
                    },
                },
                {
                    key: 'dish',
                    header: 'Tên món ăn',
                    style: {
                        width: '25%',
                    },
                },
                {
                    key: 'quantity',
                    header: 'Số lượng',
                    style: {
                        width: '100px',
                        "text-align": 'center'
                    },
                },
                {
                    key: 'price',
                    header: 'Giá',
                    style: {
                        width: '120px',
                        'text-align': 'center',
                    },
                },

            ],
            datas: [
                {
                    id: 1,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 2,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 3,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 4,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 5,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 6,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 7,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 8,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 9,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 10,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 11,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 12,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
                {
                    id: 13,
                    orderAt: '10:43 20/12/2023',
                    employee: 'Nguyen Van A',
                    dish: 'Bún chả',
                    quantity: 1,
                    selected: 0,
                    price: 33000
                },
            ],
            actions: ['check', 'remove'],
            isSelectAll: 0,
            isPayment: 0,
        }
    },
    components: {
        Checked,
        Uncheck,
        Table,
        Vector,
    },
    methods: {
        selectAll() {
            this.isSelectAll = +!this.isSelectAll;
            if (this.isSelectAll == 1){
                this.datas.forEach(item => {
                    item.selected = 1;
                })
            }else {
                this.datas.forEach(item =>{
                    item.selected = 0;
                })
            }
        },
        handleOrder(){
            this.isPayment = 1;
        },
        handleBack(){
            this.isPayment = 0;
        },
        handleAction(e){
            switch(e.name){
                case 'check':
                    this.datas.forEach(item => {
                        if (item.id === e.id){
                            item.selected = +!item.selected;
                            return;
                        }
                    })
                    break;
                case 'remove':
                    this.datas = this.datas.filter(item => e.id !== item.id);
                    break;
            }
        }
    }
}
</script>