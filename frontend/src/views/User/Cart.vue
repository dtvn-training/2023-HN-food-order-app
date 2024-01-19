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
                    <div class="food">{{ order.dishName }}</div>
                    <div class="restaurant">{{ order.restaurantName }}</div>
                    <div class="price">{{ order.unitPrice }}</div>
                    <div class="quantity">
                        <div class="sub" @click="handleDecrement(order)"><Sub/></div>
                        <input type="text" :value="order.quantity">
                        <div class="plus" @click="handleIncrement(order)"><Plus/></div>
                    </div>
                </div>
            </div>
            <div class="order">
                <button @click="order">
                    Order
                </button>
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
    .order{
        width: 100%;
        margin-top: 80px;
        display: flex;
        justify-content: center;
    }
    .order button {
        color: white;
        background: #00bd78;
        width: 110px;
        height: 30px;
        border-radius: 5px;
        border: none;
    }
    .order button:hover{
        cursor: pointer;
        background: #01d889;
    }
</style>

<script>
import Checked from "@/components/icons/Checked"
import Uncheck from "@/components/icons/Uncheck"
import Plus from '@/components/icons/Plus'
import Sub from '@/components/icons/Sub'
import Cart from '@/services/cart'
import Order from '@/services/order'

export default {
    components: {
        Checked,
        Uncheck,
        Plus,
        Sub,
    },
    data(){
        return {
            orders: []
        }
    },
    beforeMount(){
        this.getOrders();
    },
    methods: {
        async getOrders(){
            const orders = await Cart.list()
            .then(response => {
                return response;
            }) 
            orders.forEach(item => {
                item.selected = 0;
            })
            this.orders = orders;
        },
        handleDecrement(order){
            if (order.quantity == 1){
                Cart.delete(order.id);
                this.orders = this.orders.filter(item => item.id != order.id);
                return;
            }
            order.quantity--;
            const body = {
                userCartId: order.id,
                quantity: order.quantity
            }
            Cart.updateQuantity(body);
        },
        handleIncrement(order){
            order.quantity++;
            const body = {
                userCartId: order.id,
                quantity: order.quantity
            }
            Cart.updateQuantity(body);
        },
        order(){
            const body = [];
            this.orders.forEach(item => {
                if (item.selected == 1){
                    body.push(item.id);
                    Cart.delete(item.id);
                    this.orders = this.orders.filter(item => item.id != item.id);
                } 
            })
            Order.userOrder(body);

        }
    }
}
</script>