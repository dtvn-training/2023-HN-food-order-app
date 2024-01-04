<template>
    <div :style="styleCustomize">
        <input v-if="tag.name == 'input'" :type="tag.type" :placeholder="tag.placeholder" v-model="valueChild" :style="tag.style">
        <img v-if="tag.name == 'img'" :src="value" :alt="tag.alt" :style="tag.style">
        <a v-if="tag.name == 'a'" :href="tag.href" :style="tag.style">{{ value }}</a>
        <span v-if="tag.name == 'span'" :style="tag.style">{{ value }}</span>
        <button v-if="tag.name == 'button'" :style="tag.style">{{ value }}</button>
        <div v-if="tag.name == 'div'" :style="tag.style">{{ value }}</div>
        <div v-if="tag.name == ''" v-html="value"></div>    
    </div>
</template>

<style scoped>
input {
    padding-left: 10px;
    border: 1px rgb(83, 83, 83) solid;
    border-radius: 5px;
    color: rgb(56, 56, 56);
    font-size: 15px;
}
input:focus{
    outline: none;
}
</style>

<script>
    export default {
        props: {
            tag: {
                default() {
                    return {
                        name: '',
                        style: ''
                    }
                }
            },  
            value: {},  
            styleCustomize: {},
            id: {
                type: Number,
                default: 0,
            },
        },
        data(){
            return {
                valueChild: this.value,
            }
        },  
        watch: {
            valueChild(){
                console.log(this.id);
                const inputValue = {
                    id: this.id,
                    value: this.valueChild
                }
                this.$emit('valueInputBinding', inputValue);
            }
        },
    }
</script>

