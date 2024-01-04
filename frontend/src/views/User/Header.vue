<template>
    <div class="container">
        <div class="logo"><span>
                <Logo />
            </span></div>
        <div class="wrapper">
            <div class="left">
                <ul>
                    <li :class="{ 'active': isActive == 0 }" @click="isActive = 0">Đồ ăn</li>
                    <li :class="{ 'active': isActive == 1 }" @click="isActive = 1">Bình chọn</li>
                    <li :class="{ 'active': isActive == 2 }" @click="isActive = 2">Thanh toán</li>
                </ul>
            </div>
            <div class="right">
                <div class="balance">
                    <Token />
                    <span>{{ balance }}</span>
                </div>
                <div class="cart">
                    <Cart />
                </div>
                <div class="account" >
                    <Account />
                    <div class="menu" v-if="menuDisplay == 1">
                        <ul>
                            <li>Thông tin</li>
                            <li>Nạp tiền</li>
                            <li>Lịch sử nạp tiền</li>
                            <li>Nhóm</li>
                            <li>Đăng xuất</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.container {
    height: 80px;
    background: #F6F6F6;
    display: grid;
    grid-template-columns: 10% 80% 10%;
}

.wrapper {
    display: grid;
    grid-template-columns: 60% 40%;
}

.logo {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px;
}

.logo span {
    max-width: 100px;
}

.left ul {
    list-style: none;
    height: 100%;
    padding: 0;
}

.left li {
    display: inline-block;
    cursor: pointer;
    font-size: 18px;
    color: #222222;
    height: 100%;
    padding: 25px 20px 25px 20px;
}

.active {
    border-bottom: 3px solid #CF2127;
    color: #CF2127 !important;
    font-weight: 600;
}

.right {
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: end;
}

.balance {
    display: flex;
    align-items: center;
    margin-right: 50px;
}

.balance span {
    margin-left: 5px;
    color: #777777;

}

.cart {
    margin-right: 30px;
    cursor: pointer;
}

.account {
    cursor: pointer;
    position: relative;
}
.menu {
    position: absolute;
    right: 0px;
    top: 45px;
}
.right ul {
    padding: 0;
    list-style: none;
    min-width: 170px;
    background: #ffffff;
}
.right li {
    font-size: 15px;
    padding: 7px 10px 7px 15px;
}
.right li:hover {
    font-size: 16px;
    background-color: #CF2127;
    color: white;
}
</style>

<script>
import Logo from '@/components/icons/Logo.vue';
import Token from '@/components/icons/Token.vue'
import Account from '@/components/icons/Account.vue'
import Cart from '@/components/icons/Cart.vue'

export default {
    components: {
        Logo,
        Token,
        Cart,
        Account,
    },
    data() {
        return {
            isActive: 1,
            balance: '400,000 đ',
            menuDisplay: 1
        }
    },
    methods: {

    }
}
</script>