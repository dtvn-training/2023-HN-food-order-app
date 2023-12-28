<template>
    <div class="search">
        <img src="../assets/search.png" alt="">
        <input type="text" name="seach" :style="{'width': width}" :placeholder="placeholder" @v-model="input">
    </div>
</template>

<style scoped>
    .search {
        display: flex;
        align-items: center;
        position: relative;
    }
    input {
        border: 0.5px #c7c7c7 solid;
        width: 400px;
        height: 35px;
        border-radius: 8px;
        padding-left: 45px;
        font-size: 14px;
        color: #2c2c2c;
    }
    input:focus{
        outline: none;
    }
    img {
        position: absolute;
        left: 12px;
        width: 21px;
        height: 20px;
    }
</style>

<script>
    export default {
        data() {
            return {
                input: '',
            }
        },
        props: [
            'placeholder',
            'width'
        ],
        watch: {
            input () {
                this.$emit('textSearch', input);
            }
        }
    }
</script>