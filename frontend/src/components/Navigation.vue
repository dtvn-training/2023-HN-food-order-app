
<template>
    <div class="container">
        <div class="header">
            <Logo class="logo" />
            <img class="view-headline" src="../assets/view_headline.png" alt="" @click="handleCollapse">
            <!-- <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
                <el-radio-button :label="false">expand</el-radio-button>
                <el-radio-button :label="true">collapse</el-radio-button>
            </el-radio-group> -->
        </div>
        <hr />
        <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
            :collapse="isCollapse">
            <el-menu-item index="1">
                <Restaurant />
                <span slot="title">Danh sách quán ăn</span>
            </el-menu-item>
            <el-menu-item index="2">
                <Food />    
                <span slot="title">Món ngon hôm nay</span>
            </el-menu-item>
            <el-menu-item index="3">
                <Payment />
                <span slot="title">Danh sách thanh toán</span>
            </el-menu-item>
            <el-menu-item index="4">
                <Group />
                <span slot="title">Nhóm</span>
            </el-menu-item>
        </el-menu>
    </div>
</template>
  
<style scoped>
.container {
    width: 320px;
    border: 1px solid rgb(83, 83, 83);
}
span {
    font-size: 18px;
}
el-menu-item {
    padding-left: 30px;
}
.header {
    height: 88px;
    padding-left: 20px;
    padding-right: 20px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
}

hr {
    width: 282px;
    width: 282px;
    border: 0.2px solid #d1d1d1;
}

.view-headline {
    width: 20px;
    height: 20px;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}
</style>
  
<script>
import Logo from "./icons/Logo.vue"
import Restaurant from "./icons/Restaurant.vue"
import Food from "./icons/Food.vue"
import Payment from "./icons/Payment.vue"
import Group from "./icons/Group.vue"

export default {
    data() {
        return {
            isCollapse: true
        };
    },
    components: {
        Logo,
        Restaurant,
        Food,
        Payment,
        Group,
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        handleCollapse() {
            this.isCollapse = !this.isCollapse;
        }
    }
}
</script>