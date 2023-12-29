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
                :datas="datas"
                :columns="columns"
                :actions="actions"
                :pagination=1
                @onClickAction="handleAction"
            />
            <div class="payment" v-if="isPayment === 1">
                <Table
                    style="margin-bottom: 20px;"
                    :datas="fee"
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
                <button class="save">Save</button>
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
import Checked from './icons/Checked.vue';
import Uncheck from './icons/Uncheck.vue';
import Vector from './icons/Vector.vue';
import Table from './Table.vue'

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
                    key: 'address',
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
            fee: [
                {
                    id: 1,
                    restaurantName: 'Bún chả phố cổ',
                    address: 'Pho Co, Hà noi',
                    price: 33000,
                    fee: ''
                },
                {
                    id: 2,
                    restaurantName: 'Bún chả phố cổ',
                    address: 'Pho Co, Hà noi',
                    price: 33000,
                    fee: ''
                },
                {
                    id: 3,
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
                    tag: {
                        name: 'span',
                        style: {}
                    }
                },
                {
                    key: 'employee',
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
                    key: 'dish',
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
                    key: 'price',
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
            discount: '',
        }
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
        },
        handleValueInput(e){
            this.fee.forEach(item => {
                if (item.id == e.id){
                    item.fee = e.value;
                    return;
                }
            })
        }
    }
}
</script>