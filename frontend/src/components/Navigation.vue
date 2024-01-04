
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
                <li :class="{ active: page === 'restaurants'}" @click="handleActive('restaurants')">
                    <router-link to="/admin/restaurants">
                        <span class="icon">
                            <Restaurant />
                        </span>
                        <span class="text nav-text">Danh sách quán ăn</span>
                    </router-link>
                </li>
                <li :class="{ active: page === 'foods'}" @click="handleActive('foods')">
                    <router-link to="/admin/foods">
                        <span class="icon">
                            <Food />
                        </span>
                        <span class="text nav-text">Món ngon hôm nay</span>
                    </router-link>
                </li>
                <li :class="{ active: page === 'payments'}" @click="handleActive('payments')">
                    <router-link to="/admin/payments">
                        <span class=" icon">
                            <Payment />
                        </span>
                        <span class="text nav-text">Danh sách thanh toán</span>
                    </router-link>
                </li>
                <li :class="{ active: page === 'groups'}" @click="handleActive('groups')">
                    <router-link to="/admin/groups">
                        <span class=" icon">
                            <Group />
                        </span>
                        <span class="text nav-text">Nhóm</span>
                    </router-link>
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
            page: '',
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
    beforeMount() {
        // this.page = this.$route.name;
    },
    methods: {
        handleCollapse() {
            if (this.isCollapse === false) {
                this.sidebarStyle = 'sidebar';
                this.isCollapse = true;
            }
            else {
                this.sidebarStyle = 'sidebar close';
                this.isCollapse = false;
            }
        },
        handleActive(page) {
           this.page = page;
        }
    },
    watch: {
        $route(to, from) {
            this.page = to.name;
        }
    }
}
</script>

<style scoped>
nav {
    height: 100%;
}
.sidebar .image-text .image {
    display: flex;
    align-items: center;
    margin-left: 30px;
    transition: all 0.2s ease;
    max-width: 100px;
}

.close .image-text .image {
    opacity: 0;
}

.sidebar {
    position: relative;
    top: 0;
    left: 0;
    min-height: 646px;
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
    width: 100%;
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
    display: none;
}


.sidebar .menu-links li:hover {
    background: white;
    cursor: pointer;
}

.sidebar .menu-bar .active {
    font-weight: 600;
    background: white;
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
    width: 100%;
    flex-direction: column;
    justify-content: space-between;
    height: calc(100% - 100px);
}
</style>