<template>
    <div class="home" style="height: 500px;position: relative;">
        <div class="content" style="height: 955px;">
            <Navigation />
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
        }
    }
</script>
<style scoped>
    .content {
        display: flex;
        flex-direction: row;
    }
</style>