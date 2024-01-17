<template>
    <div class="wrapper" @click="cancel">
        <div class="vote-create" @click.stop="">
            <div class="container">
                <div class="header">THANH TOÁN</div>
                <div class="qrcode">
                    <img src="data:image/png;base64,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"
                        alt="">
                </div>
                <div class="time">
                    <span>Thời gian {{ '18/12/2023 10:31' }}</span>
                </div>
                <div class="bill">
                    <div class="item-bill" v-for="itemBill in bill.itemBills">
                        <div class="food-name">{{ itemBill.food }}</div>
                        <div class="quantity">{{ itemBill.quantity }}</div>
                        <div class="price">{{ itemBill.price }}</div>
                    </div>
                    <hr>
                    <div class="shipfee">
                        <span>Phí vận chuyển</span>
                        <span>{{ bill.shipfee }}</span>
                    </div>
                    <div class="discount">
                        <span>Giảm giá</span>
                        <span>- {{ bill.discount }}</span>
                    </div>
                    <div class="total">
                        <span>Tổng tiền</span>
                        <span>{{ bill.total }}</span>
                    </div>
                </div>
                <div class="note1" @click="isWallet = +!isWallet">
                    <span>Thanh toán qua ví Foodify</span>
                    <Uncheck v-if="isWallet == 0" />
                    <Checked v-if="isWallet == 1" />
                </div>
                <div class="note2">
                    <span>*Hoàn tất chuyển khoản sau đó bấm chọn "Pay"</span>
                </div>
                <button class="pay" @click="pay">Pay</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
.wrapper {
    background: #373737b0;
    width: 100%;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
}

.vote-create {
    background: #ffffff;
    min-width: 550px;
    width: 50%;
    max-width: 730px;
    position: fixed;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.container {
    width: 70%;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
}

.header {
    font-size: 24px;
    font-weight: 500;
}

.qrcode img {
    width: 220px;
    height: 220px;
}

.qrcode {
    overflow: hidden;
}

.time {
    width: 100%;
    font-size: 16px;
    font-weight: 500;
    color: rgb(75, 75, 75);
}

.bill {
    width: 100%;

}

.item-bill {
    display: grid;
    grid-template-columns: 70% 5% 25%;
    margin-top: 3px;
    font-size: 14px;
    margin-bottom: 3px;
    font-weight: 200;
}

.price {
    text-align: end;
}

.shipfee,
.discount,
.total {
    display: flex;
    justify-content: space-between;
    margin-top: 5px;
    font-weight: 200;
    font-size: 14px;
}

.total {
    font-size: 16px;
    font-weight: 400;
}

.note1 {
    width: 100%;
    display: flex;
    align-items: center;
    font-size: 14px;
    justify-content: start;
    font-weight: 100;
    margin-top: 25px;
}

.note1 span {
    margin-right: 5px;
}

.note1:hover{
    cursor: pointer;
    font-weight: 400;
}

.note2 {
    font-size: 14px;
    width: 100%;
    margin-top: 5px;
    color: red;
}

.pay {
    width: 90px;
    height: 30px;
    color: white;
    background: #00A2D6;
    border: none;
    border-radius: 5px;
    margin-top: 25px;
    margin-bottom: 10px;
}

.pay:hover {
    cursor: pointer;
    background: #08afe7;
}
</style>

<script>
import Uncheck from '@/components/icons/Uncheck.vue';
import Checked from '@/components/icons/Checked.vue';

export default {
    components: {
        Uncheck,
        Checked,
    },
    props: [
        'bills'
    ],
    data() {
        return {
            bill: {
                shipfee: 15000,
                discount: 7640,
                total: 72000,
                itemBills: [
                    {
                        id: 1,
                        food: 'Cơm rang',
                        quantity: 1,
                        price: 34500
                    },
                    {
                        id: 2,
                        food: 'Cơm rang',
                        quantity: 1,
                        price: 34500
                    },
                    {
                        id: 3,
                        food: 'Cơm rang',
                        quantity: 1,
                        price: 34500
                    },
                ]
            },
            isWallet: 0,
        }
    },
    methods: {
        pay() {
            // call api
            this.$emit('onClickCancel');
        },
        cancel() {
            this.$emit('onClickCancel');
        }
    }
}
</script>