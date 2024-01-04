<template>
    <div class="container">
        <div class="code row">
            <span class="title">Code</span>
            <span class="info">{{ code }}</span>
        </div>
        <div class="numMember row">
            <span class="title">Số lượng</span>
            <span class="info">{{ numMember }} thành viên</span>
        </div>
    </div>
</template>

<script>

export default {  
    components: {
    },
    data(){
        return {
            code: 'flksjf83',
            numMember: 5
        }
    }
}
</script>

<style scoped>
.container {
    padding: 0 20px 0 20px;
}
.code {
    margin-top: 20px !important;
}
.row{
    height: 35px;
    margin: 5px 0 5px;
    /* background: yellowgreen; */
}
.title {
    color: rgb(63, 63, 63);
    width: 200px;
    display: inline-block;
}
.info {
    color: rgb(26, 26, 26);
}

</style>