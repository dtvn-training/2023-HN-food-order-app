<template>
    <div class="container">
        <div class="table">
            <div class="header">
                <div class="index">No.</div>
                <div class="header-item cell" v-for="column in columns" :style="{
                    'width': column.style.width,'text-align': column.style['text-align']
                }">
                    {{ column.header }}
                </div>
                <div v-if="actions.length > 0" class="cell header-item" :style="{ 'width': actions.length === 1 ?  actions.length * 70 + 30 + 'px' :  actions.length * 75 + 'px'}">Actions</div>
            </div>
            <hr/>
            <div class="data">
                <div class="row" v-for="(row, index) in dataPages">
                    <div class="index">{{ (currentPage - 1)*10 + index + 1 }}</div>
                    <!-- Row of data -->
                    <Tag 
                        class="cell"
                        v-for="column in columns"
                        :tag="column.tag" 
                        :value="row[column.key]"
                        :style="column.style"
                        :key="column.key"
                        :id="row.id"
                        @valueInputBinding="handleValueInput"
                    />
                    <!-- Action -->
                    <div class="action" v-if="actions.length > 0" :style="{ 'width': actions.length === 1 ?  actions.length * 70 + 30 + 'px' :  actions.length * 75 + 'px'}">
                        <div class="cell-action" v-for="action in actions" @click="handleAction(action, row.id)">
                            <Accept v-if="action == 'accept'" />
                            <Checked v-if="action == 'check' && row.selected == 1" />
                            <Uncheck v-if="action == 'check' && row.selected == 0" />
                            <Delete v-if="action == 'delete'" />
                            <Remove v-if="action == 'remove'" />
                            <Eye v-if="action == 'view'" />
                            <RadioChecked v-if="action == 'radio' && row.selected == 1" />
                            <RadioUncheck v-if="action == 'radio' && row.selected == 0" />
                            <HeartCheck v-if="action == 'heart' && row.selected == 1" />
                            <HeartUncheck v-if="action == 'heart' && row.selected == 0" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <Pagination 
                v-if="pagination == 1"
                :totalPages="datas.length % 10 == 0 ? Math.floor(datas.length / 10) : Math.floor(datas.length / 10) + 1" 
                :total="+datas.length" 
                :currentPage="1" 
                @pagechanged="currentPage = $event"
        />
    </div>
</template>

<script>
import HeartCheck from "./icons/HeartCheck.vue"
import HeartUncheck from "./icons/HeartUncheck.vue"
import Accept from "./icons/Accept.vue"
import Checked from "./icons/Checked.vue"
import Uncheck from "./icons/Uncheck.vue"
import Delete from "./icons/Delete.vue"
import Eye from "./icons/Eye.vue"
import RadioChecked from "./icons/RadioChecked.vue"
import RadioUncheck from "./icons/RadioUncheck.vue"
import Pagination from "./Pagination.vue"
import Remove from "./icons/Remove.vue"
import Tag from "./Tag.vue"

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
        Remove,
        Tag,
        HeartCheck,
        HeartUncheck,
    },
    props: {
        columns:{},
        datas:{
            type: Array
        },
        actions:{},
        pagination: {
            type: Number,
            default: 1,
        },
    },
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
            console.log(action);
            this.$emit('onClickAction', action)
        },
        handleValueInput(e){
            this.$emit('valueInputBinding', e);
        }
    },
}

</script>

<style scoped>
.container {
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
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
    margin-top: 15px;
}

.action {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    padding-left: 10px;
    padding-right: 10px;
}
.cell {
    height: auto;
    padding: 5px;
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
    align-items: start;
    padding: 5px;
    cursor: pointer;
}
</style>