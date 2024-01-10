<template>
    <div class="date-pick">
        <span class="title">{{ title }}</span>
        <input type="date" placeholder="1990-07-20" v-model="dateValue">
    </div>
</template>
<script>
export default {
    props: [
        'title',
        'value'
    ],
    data(){
        return {
            dateValue: this.value,
        }
    },
    watch: {
        dateValue(){
            this.$emit('valueOnChange', this.dateValue);
        }
    }

}
</script>

<style scoped>
.date-pick{
    display: flex;
    flex-direction: row;
    align-items: center;
    width: auto;
}
.title {
    color: rgb(53, 53, 53);
    font-size: 16px;
    margin-right: 7px;
    margin-top: 1px;
}
input {
    width: 155px;
    height: 35px;
    color: rgb(12, 12, 12);
    border: 1px rgb(126, 126, 126) solid;
    border-radius: 5px;
    font-size: 16px;
    position: relative;
}
input[type="date"]::-webkit-calendar-picker-indicator {
    background-image: url("../../assets/date.png");   
    background-position-y: -2px;
    background-size: 110%;
    position: absolute; /* Set position to absolute for proper alignment */
    top: 6px;
    right: 7px;
}
input[type="date"]::-webkit-calendar-picker-indicator:hover {
    cursor: pointer;
}
</style>