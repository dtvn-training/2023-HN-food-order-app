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
            'disable'
        ]
    }
</script>
<style scoped>

</style>