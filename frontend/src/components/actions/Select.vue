<template>
    <div class="select">
        <div class="title">
            <span>{{ title }}</span>
        </div>
        <select class="form-select" v-model="selectValue">
        <option v-for="option in options" :value="option.value">{{ option.label }}</option>
    </select>
    </div>
</template>
<style scoped>
    .select{
        display: flex;
        flex-direction: row;
        align-items: center;
    }
    .title {
        margin-right: 10px;
        color: #404040;
        font-weight: 400;
    }
    select {
        border: 0.5px #666666 solid;
        border-radius: 5px;
        width: auto;
        height: 30px;
        font-size: 14px;
        padding-left: 7px;
        padding-right: 5px;
        /* background-position: 2px center; */
    }
    select:focus{
        outline: none;
    }
</style>
<script>
export default {
    props: [
        'title',
        'options',
        'value',
    ],
    data () {
        return {
            selectValue: this.value,
        }
    },
    watch: {
        selectValue(){
            this.$emit('selectOnChange', this.selectValue);
        }
    }
}
</script>