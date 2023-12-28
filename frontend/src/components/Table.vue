<template>
    <div class="container">
        <div class="table">
            <div class="header">
                <div class="index">No.</div>
                <div class="header-item cell" v-for="column in columns" :style="column.style">
                    {{ column.header }}
                </div>
                <div class="cell header-item header-action" :style="{ 'width': actions.length * 70 + 'px' }">Actions</div>
            </div>
            <hr/>
            <div class="data">
                <div class="row" v-for="(row, index) in dataPages">
                    <div class="index">{{ (currentPage - 1)*10 + index + 1 }}</div>
                    <!-- Row of data -->
                    <div class="cell" v-for="column in columns" :style="column.style" v-html="row[column.key]"></div>
                    <!-- Action -->
                    <div class="action" v-if="actions.length > 0" :style="{ 'width': actions.length * 70 + 'px' }">
                        <div class="cell-action" v-for="action in actions" @click="handleAction(action, row.id)">
                            <Accept v-if="action == 'accept'" />
                            <Checked v-if="action == 'check' && row.selected == 1" />
                            <Uncheck v-if="action == 'check' && row.selected == 0" />
                            <Delete v-if="action == 'delete'" />
                            <Eye v-if="action == 'view'" />
                            <RadioChecked v-if="action == 'radio' && row.selected == 1" />
                            <RadioUncheck v-if="action == 'radio' && row.selected == 0" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <Pagination 
                :totalPages="Math.floor(datas.length / 10) + 1" 
                :total="datas.length" 
                :currentPage="1" 
                @pagechanged="currentPage = $event"
        />
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
import Pagination from "./Pagination.vue"

export default {
    components: {
        Accept,
        Checked,
        Delete,
        Eye,
        RadioChecked,
        RadioUncheck,
        Uncheck,
        Pagination,
    },
    props: [
        'columns',
        'datas',
        'actions'
    ],
    data() {
        return {
            currentPage: 1,
            offset: 10,
        }
    },
    computed: {
        dataPages () {
            const dataPages = []
            for (   var i = (this.currentPage - 1)*10;
                    i < Math.min((this.currentPage - 1) * 10 + this.offset, this.datas.length);
                    i++){
                dataPages.push(this.datas[i]);
            }
            return dataPages;
        }
    },
    methods: {
        handleAction(name, id) {
            const action = {
                name,
                id
            }
            // console.log(action);
            this.$emit('onClickAction', action)
        }
    },
}

</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-top: 10px;
}
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
.index {
    min-width: 50px;
    text-align: left;
    padding: 5px;
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
    width: auto;
    /* background-color: yellow; */
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: space-between;
    margin: 8px 0 8px;
}

.action {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding-left: 30px;
}
.cell {
    height: auto;

    padding: 5px;
}
.header-action{
    padding-left: 30px;
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
    display: flex;
    align-items: center;
    padding: 5px;
}
.cell-action:hover {
    background: rgb(238, 238, 238);
}
</style>