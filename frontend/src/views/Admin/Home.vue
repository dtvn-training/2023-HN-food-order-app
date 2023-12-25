<template>
    <div class="home" style="height: 500px;position: relative;">
        <div class="body" style="height: 955px;">
            <Navigation />
            <div class="content">
                <div class="wrapper">
                    <router-view></router-view>
                </div>
            </div>
        </div>
        <Footer />
    </div>
</template>
<script>
import Navigation from "@/components/Navigation.vue"
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
    display: flex;
    flex-direction: row;
}

.content {
    /* background: yellow;    */
    display: flex;
    flex-grow: 1;
    justify-content: center;
}
.home .wrapper {
    background: rgb(255, 255, 255);
    width: 98%;
    display: flex;
    flex-direction: column;
    justify-content: start;
}

hr {
    width: 100%;
}</style>