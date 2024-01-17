<template>
    <div class="container">
        <Header class="header" />
        <div class="main">
            <div class="wrapper">
                <router-view></router-view>
            </div>
        </div>
        <Footer class="footer"/>
    </div>
</template>

<style scoped>
.container {
    width: 100%;
    display: grid;
    grid-template-rows: 80px auto auto;
}
.wrapper {
    width: 1170px;
    min-height: 875px;
}
.main {
    height: auto;
    background-color: white;
    display: flex;
    justify-content: center;
}

</style>

<script>
import Header from "../User/Header.vue"
import Footer from "../../components/Footer.vue"

export default {
    components: {
        Header, 
        Footer
    }
}

</script>

