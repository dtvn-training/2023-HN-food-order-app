<template>
    <div class="home" style="position: relative;">
        <div class="body">
            <Navigation />
            <div class="content">
                <div class="wrapper">
                    <router-view></router-view>
                </div>
            </div>
        </div>
        <Footer class="footer" />
    </div>
</template>
<script>
import Navigation from "@/components/admin/Navigation.vue"
import Footer from "@/components/Footer.vue"

export default {
    components: {
        Navigation,
        Footer,
    },
    data() {
        return {
            page: 'retaurant',
        }
    },
    watch: {
        page() {
            console.log(this.page);
        }
    }
}
</script>
<style scoped>
.body {
    display: grid;
    grid-template-columns: auto 1fr;
}

.content {
    /* background: yellow;    */
    display: flex;
    flex-grow: 1;
    justify-content: center;
    min-height: 954px;
}
.home .wrapper {
    background: rgb(255, 255, 255);
    width: 98%;
    display: flex;
    flex-direction: column;
    justify-content: start;
    height: 100%;
}

.footer {
    grid-column: 1 / -1;
}

hr {
    width: 100%;
}</style>