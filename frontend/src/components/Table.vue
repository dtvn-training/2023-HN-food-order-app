<template>
    <div class="table">
        <div class="header">
            <div class="header-item cell" v-for="column in columns" :style="column.style">
                {{ column.header }}
            </div>
            <div class="header-item cell header-action" :style="{ 'width': actions.length * 70 + 'px' }">Actions</div>
        </div>
        <div class="data">
            <div class="row" v-for="row in datas">
                <div class="cell" v-for="column in columns" :style="column.style">{{ row[column.key] }}</div>
                <div class="action" v-if="actions.length > 0" :style="{ 'width': actions.length * 70 + 'px' }">
                    <div class="cell-action" v-for="action in actions" @click="handleAction(action, row.id)">
                        <Accept v-if="action == 'accept'" />
                        <Checked v-if="action == 'check' && row.selected == 1" />
                        <Uncheck v-if="action == 'check' && row.selected == 0" />
                        <Delete v-if="action == 'delete'" />
                        <Eye v-if="action == 'view'" />
                        <RadioChecked v-if="action == 'radioChecked'" />
                        <RadioUncheck v-if="action == 'radioUncheck'" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import Accept from "./icons/Accept.vue"
import Checked from "./icons/Checked.vue"
import Uncheck from "./icons/Uncheck.vue"
import Delete from "./icons/Delete.vue"
import Eye from "./icons/Eye.vue"
import RadioChecked from "./icons/RadioChecked.vue"
import RadioUncheck from "./icons/RadioUncheck.vue"

export default {
    components: {
        Accept,
        Checked,
        Delete,
        Eye,
        RadioChecked,
        RadioUncheck,
        Uncheck,
    },
    props: [
        'columns',
        'datas',
        'actions'
    ],
    data() {
        return {
        }
    },
    methods: {
        handleAction(action, id) {
            console.log(action + " id: " +id);
            this.$emit('actionHandler', {action, id})
        }
    },
}

</script>

<style scoped>
.table {
    all: unset;
    display: flex;
    flex-direction: column;
}

.header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

.header-item {
    text-align: center;
    font-size: 16px;
    color: rgb(20, 20, 20);
    font-weight: 500;
}

.data {
    display: flex;
    flex-direction: column;
}

.row {
    widows: auto;
    /* background-color: yellow; */
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: space-between;
}

.action {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding-left: 40px;
}
.cell {
    height: auto;
    overflow: auto;
    padding: 5px;
}
.header-action{
    padding-left: 40px;
}

.cell::-webkit-scrollbar {
    height: 10px;
    border: #c4c4c4 0.5px solid;
    border-radius: 5px;
}

.cell::-webkit-scrollbar-thumb {
    background-color: #b1b1b1;
    border-radius: 5px;
}

.cell::-webkit-scrollbar-thumb:hover {
    background-color: #777777;
}

.cell-action {
    margin: 5px;
}
</style>