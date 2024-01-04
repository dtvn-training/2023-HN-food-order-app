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
.main {
    height: auto;
    background-color: aquamarine;
}
.wrapper {
    margin: 0 auto;
    width: 80%;
    height: auto;
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

