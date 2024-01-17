<template>
    <div>
        <span>{{ title }}</span>
        <input type="text" :value="value">
    </div>
</template>
<script>
    export default {
        props: [
            'title',
            'value',
            'disable',
            'style',
        ]
    }
</script>
<style scoped>
    input{
        widht: 100px
    }
</style>