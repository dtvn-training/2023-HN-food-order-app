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
            <Table
                v-if="isPayment === 0"
                :datas="orders"
                :columns="columns"
                :actions="actions"
                :pagination=1
                @onClickAction="handleAction"
            />
            <div class="payment" v-if="isPayment === 1">
                <Table
                    style="margin-bottom: 20px;"
                    :datas="fees"
                    :columns="columnsPayment"
                    :actions="[]"
                    :pagination=0
                    @valueInputBinding="handleValueInput"
                />
                <hr>
                    <div class="discount">
                        <span>Giảm giá</span>
                        <div class="right">
                            <input type="text" v-model="discount" class="discount">
                        </div>
                    </div>
                <hr>
                <button class="save" @click="save">Save</button>
            </div>
        </div>
    </div>
</template>
<style scoped>
.save {
    border-radius: 5px;
    color: white;
    background: #00A2D6;
    font-size: 15px;
    font-weight: 400;
    width: 120px;
    height: 35px;
    border: none;
    margin: 0 auto;
    margin-top: 80px;
    cursor: pointer;
}
.save:hover{
    background: #30abd4;
}
.payment{
    display: flex;
    flex-direction: column;
    justify-content: center;
}
.order {
    height: 100%;
    display: grid;
    grid-auto-rows: 50px 1px auto;
}
.discount{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    height: 45px;
}
.discount .right{
    display: flex;
    width: 120px;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin: 7px 0 7px;
}
.discount input {
    text-align: center;
    width: 110px;
    height: 30px;
}
.discount span {
    font-size: 18px;
    font-weight: 500;
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
import Checked from '../icons/Checked.vue';
import Uncheck from '../icons/Uncheck.vue';
import Vector from '../icons/Vector.vue';
import Table from '../Table.vue'
import Order from '@/services/order.js'

export default {
    components: {
        Checked,
        Uncheck,
        Table,
        Vector,
    },
    data() {
        return {
            columnsPayment: [
                {
                    key: 'restaurantName',
                    header: 'Quán ăn',
                    style: {
                        width: '35%',
                    },
                    tag: {
                        name: 'span',
                        style: {}
                    }
                },
                {
                    key: 'restaurantAddress',
                    header: 'Địa chỉ',
                    style: {
                        width: '40%',
                    },
                    tag: {
                        name: 'span',
                        style: {}
                    }
                },
                {
                    key: 'fee',
                    header: 'Phí ship',
                    style: {
                        width: '120px',
                        display: 'flex',
                        'justify-content': 'center',
                    },
                    tag: {
                        name: 'input',
                        style: {
                            width: '110px',
                            height: '30px',
                            'text-align':'center'
                        }
                    }
                },
            ],
            columns: [
                {
                    key: 'createdTime',
                    header: 'Thời gian',
                    style: {
                        width: '150px',
                        "text-align":'center'
                    },
                    tag: {
                        name: 'span',
                        style: {}
                    }
                },
                {
                    key: 'userFullName',
                    header: 'Nhân viên',
                    style: {
                        width: '20%',
                    },
                    tag: {
                        name: 'span',
                        style: {}
                    }
                },
                {
                    key: 'dishName',
                    header: 'Tên món ăn',
                    style: {
                        width: '25%',
                    },
                    tag: {
                        name: 'span',
                        style: {}
                    }
                },
                {
                    key: 'quantity',
                    header: 'Số lượng',
                    style: {
                        width: '100px',
                        "text-align": 'center'
                    },
                    tag: {
                        name: 'span',
                        style: {}
                    }
                },
                {
                    key: 'unitPrice',
                    header: 'Giá',
                    style: {
                        width: '120px',
                        'text-align': 'center',
                    },
                    tag: {
                        name: 'span',
                        style: {}
                    }
                },

            ],
            fees: [],
            actions: ['check', 'remove'],
            isSelectAll: 0,
            isPayment: 0,
            discount: '',
            orders: [],
        }
    },
    beforeMount(){
        this.getOrders();
    },
    methods: {
        async getOrders(){
            const orders = await Order.getOrders()
            .then(response => {
                return response;
            })
            .catch(error => {
                console.log(error);
                return [];
            })
            orders.forEach(item => {
                item.selected = 0;
            });
            console.log(orders);
            this.orders = orders;
        },
        selectAll() {
            this.isSelectAll = +!this.isSelectAll;
            if (this.isSelectAll == 1){
                this.orders.forEach(item => {
                    item.selected = 1;
                })
            }else {
                this.orders.forEach(item =>{
                    item.selected = 0;
                })
            }
        },
        handleOrder(){
            this.isPayment = 1;
            let restaurantIdMap = {};
            this.orders.forEach(item => {
                if (item.selected == 1)
                if (!restaurantIdMap[item.restaurantId]){
                    this.fees.push(item);
                    restaurantIdMap[item.restaurantId] = true;
                }
            })
        },
        handleBack(){
            this.isPayment = 0;
            this.fees = [];
        },
        handleAction(e){
            switch(e.name){
                case 'check':
                    this.orders.forEach(item => {
                        if (item.id === e.id){
                            item.selected = +!item.selected;
                            return;
                        }
                    })
                    break;
                case 'remove':
                    Order.delete(e.id);
                    this.orders = this.orders.filter(item => e.id !== item.id);
                    break;
            }
        },
        handleValueInput(e){
            this.fees.forEach(item => {
                if (item.id == e.id){
                    item.fee = +e.value;
                    return;
                }
            })
        },
        save(){
            let orderIds = [];
            this.orders.forEach(item => {
                if (item.selected == 1)orderIds.push(item.id);
            });
            console.log(this.orders);
            let restaurantIds = [];
            let restaurantFees = [];
            this.fees.forEach(item => {
                restaurantIds.push(item.restaurantId);
                restaurantFees.push(item.fee);
            })
            const body = {
                orderIds, 
                restaurantIds,
                restaurantFees,
                discount: this.discount,
            }
            Order.order(body);
            
        }
    }
}
</script>
