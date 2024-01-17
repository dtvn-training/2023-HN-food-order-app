<template>
    <div>
        <div class="header">GIỎ HÀNG</div> 
        <hr>
        <div class="wrapper">
            <div class="title">
                <div></div>
                <div>Tên món ăn</div>
                <div>Quán ăn</div>
                <div>Đơn giá</div>
                <div>Số lượng</div>
            </div>
            <div class="orders">
                <div class="item-order" v-for="order in orders">
                    <div @click="order.selected = !order.selected" class="check">
                        <Checked v-if="order.selected == 1"/>
                        <Uncheck v-if="order.selected == 0"/>
                    </div>
                    <div class="food">{{ order.foodName }}</div>
                    <div class="restaurant">{{ order.restaurantName }}</div>
                    <div class="price">{{ order.price }}</div>
                    <div class="quantity">
                        <div class="sub" @click="order.quantity > 1 ? order.quantity-- : handleDecrement(order)"><Sub/></div>
                        <input type="text" :value="order.quantity">
                        <div class="plus" @click="order.quantity++"><Plus/></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
    .header{
        font-size: 48px;
        font-weight: 500;
        color: rgb(71, 71, 71);
        margin-top: 10px;
    }
    .wrapper {
        margin-top: 20px;
        margin-bottom: 40px;
    }
    .title, .item-order{
        display: grid;
        grid-template-columns: 5% 35% 35% 15% 10%;
    }
    .item-order{
        margin-top: 15px;
    }
    .title div {
        text-align: center;
        font-weight: 500;
        color: rgb(94, 94, 94);
        font-size: 18px;
    }
    .food, .restaurant, .price{
        text-align: center;
    }

    input{
        width: 40px;
        text-align: center;
        padding: 0;
    }
    .quantity, .check{
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .quantity input {
        margin: 0 5px 0 5px;
    }
    .plus, .sub {
        height: 100%;
        width: 30px;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .plus:hover, .sub:hover{
        background: rgb(228, 228, 228);
        cursor: pointer;
    }
</style>

<script>
import Checked from "@/components/icons/Checked"
import Uncheck from "@/components/icons/Uncheck"
import Plus from '@/components/icons/Plus'
import Sub from '@/components/icons/Sub'

export default {
    components: {
        Checked,
        Uncheck,
        Plus,
        Sub,
    },
    data(){
        return {
            orders: [
                {
                    id: 2,
                    foodName:'Cơm rang dưa bò',
                    restaurantName:'Cơm Rang Hà Nội',
                    price: 34000,
                    quantity: 1,
                    selected: 1,
                },
                {
                    id: 3,
                    foodName:'Cơm rang dưa bò',
                    restaurantName:'Cơm Rang Hà Nội',
                    price: 34000,
                    quantity: 1,
                    selected: 0,
                },
                {
                    id: 4,
                    foodName:'Cơm rang dưa bò',
                    restaurantName:'Cơm Rang Hà Nội',
                    price: 34000,
                    quantity: 1,
                    selected: 0,
                },
                {
                    id: 5,
                    foodName:'Cơm rang dưa bò',
                    restaurantName:'Cơm Rang Hà Nội',
                    price: 34000,
                    quantity: 1,
                    selected: 0,
                },
            ]
        }
    },
    methods: {
        handleDecrement(order){
            this.orders = this.orders.filter(item => item.id != order.id);
        }
    }
}
</script>