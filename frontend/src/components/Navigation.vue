
<template>
    <nav :class="sidebarStyle">
        <header>
            <div class="image-text">
                <span class="image">
                    <Logo />
                </span>
            </div>
            <span class="toggle" @click="handleCollapse">
                <ViewHeadline />
            </span>
        </header>
        <hr>
        <div class="menu-bar">
            <ul class="menu-links">
                <li :class="restaurantSelected" @click="handleNavClick(0)">
                    <a>
                        <span class="icon">
                            <Restaurant />
                        </span>
                        <span class="text nav-text">Danh sách quán ăn</span>
                    </a>
                </li>
                <li :class="foodSelected" @click="handleNavClick(1)">
                    <a>
                        <span class="icon">
                            <Food />
                        </span>
                        <span class="text nav-text">Món ngon hôm nay</span>
                    </a>
                </li>
                <li :class="paymentSelected" @click="handleNavClick(2)">
                    <a>
                        <span class=" icon">
                            <Payment />
                        </span>
                        <span class="text nav-text">Danh sách thanh toán</span>
                    </a>
                </li>
                <li :class="groupSelected" @click="handleNavClick(3)">
                    <a>
                        <span class=" icon">
                            <Group />
                        </span>
                        <span class="text nav-text">Nhóm</span>
                    </a>
                </li>
            </ul>
            <div class="bottom-content">
                <li class="signout">
                    <span class="text nav-text">{{ adminName }}</span>
                    <span class="icon">
                        <Signout />
                    </span>
                </li>
            </div>
        </div>
    </nav>
</template>
  
<style scoped>
nav {
    height: 100%;
}
.sidebar .image-text .image {
    display: flex;
    align-items: center;
    margin-left: 30px;
    transition: all 0.2s ease;
}

.close .image-text .image {
    opacity: 0;
}

.sidebar {
    position: relative;
    top: 0;
    left: 0;
    height: 100%;
    width: 320px;
    background: #F3F3F3;
    transition: all 0.3s ease;
}

.sidebar.close {
    width: 85px;
}

hr {
    margin-bottom: 10px;
}

.sidebar header {
    position: relative;
    height: 88px;
    display: flex;
    align-items: center;
}

.sidebar header .toggle {
    position: absolute;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 40px;
    height: 40px;
    top: 50%;
    right: 22.5px;
    transform: translateY(-50%);
}

.sidebar header .toggle:hover {
    background: white;
}

.sidebar ul {
    padding: 0;
}

.sidebar li {
    height: 60px;
    padding-left: 30px;
    list-style: none;
    display: flex;
    align-items: center;
}

.sidebar li a {
    text-decoration: none;
    display: flex;
    align-items: center;
    height: 100%;
}

.sidebar li .icon {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    width: 25px;
    margin-right: 20px;
}

.sidebar li .text {
    font-size: 18px;
    color: rgb(71, 71, 71);
    transition: all 0.25s ease;
    white-space: nowrap;
    opacity: 1;
}

.close li .text {
    opacity: 0;
}

.sidebar .menu-links li:hover {
    background: white;
    cursor: pointer;
}

.sidebar .menu-bar .cur-page {
    background: white;
}

.sidebar .menu-bar .cur-page {
    font-weight: 600;
}

.bottom-content .signout {
    background-color: #9D0000 !important;
}

.bottom-content .signout .text {
    color: white;
}

.bottom-content .signout .icon {
    width: 40px;
    margin: 0;
    height: auto;
    position: absolute;
    right: 23px;
}

.bottom-content .signout .icon:hover {
    cursor: pointer;
}

.sidebar .menu-bar {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: calc(100% - 100px);
}
</style>
  
<script>
import Logo from "./icons/Logo.vue"
import Restaurant from "./icons/Restaurant.vue"
import Food from "./icons/Food.vue"
import Payment from "./icons/Payment.vue"
import Group from "./icons/Group.vue"
import ViewHeadline from "./icons/ViewHeadline.vue"
import Signout from "./icons/Signout.vue"

export default {
    data() {
        return {
            sidebarStyle: 'sidebar',
            isCollapse: true,
            adminName: 'Ta Quang Linh',
            page: 0,
            restaurantSelected: '',
            foodSelected: '',
            paymentSelected: '',
            groupSelected: '',
        };
    },
    components: {
        Logo,
        Restaurant,
        Food,
        Payment,
        Group,
        ViewHeadline,
        Signout
    },
    methods: {
        handleCollapse() {
            console.log(1);
            if (this.isCollapse === false) {
                this.sidebarStyle = 'sidebar';
                this.isCollapse = true;
            }
            else {
                this.sidebarStyle = 'sidebar close';
                this.isCollapse = false;
            }
        },
        handleNavClick(page) {
            this.restaurantSelected = '';
            this.foodSelected = '';
            this.paymentSelected = '';
            this.groupSelected = '';
            switch (page) {
                case 0:
                    this.restaurantSelected = 'cur-page';
                    break;
                case 1:
                    this.foodSelected = 'cur-page';
                    break;
                case 2:
                    this.paymentSelected = 'cur-page';
                    break;
                case 3:
                    this.groupSelected = 'cur-page';
                    break;
            }
        }
    }
}
</script>