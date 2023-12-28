<template>
    <div>
        <input type="text" v-if="tag == 'input'" :type="type" :value="value" :placeholder="placeholder">
        <a :href="href">{{ value }}</a>
    </div>
</template>

<script>
    export default {
        props: [
            'tag',
            'type',
            'href',
            'value',
            'placeholder',
            
        ]
    }
</script>