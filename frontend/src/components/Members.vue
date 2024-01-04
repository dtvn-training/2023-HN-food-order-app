<template>
    <div class="container">
        <div class="header">
            <Search width="476px" placeholder="Tên thành viên..." @textSearch="textSearchOnChange" />
        </div>
        <hr>
        <div class="data">
            <Table 
                :columns="columns"
                :datas="members"
                :actions="actions"
                @onClickAction="handleAction"
            />
        </div>
    </div>
</template>

<style scoped>
.header {
    height: 50px;
    display: flex;
    flex-direction: row;
    align-items: center;
    /* background: yellowgreen; */
}
</style>

<script>
import Search from "../components/actions/Search1.vue"
import Table from "./Table.vue";

export default {
    components: {
        Table,
        Search,
    },  
    data() {
        return {
            inputTextSeach: '',
            columns: [
                {
                    key: 'member',
                    header: 'Tên thành viên',
                    style: {
                        width: '30%',
                    },
                    tag: {
                        name: '',
                        style: ''
                    }
                },
                {
                    key: 'mail',
                    header: 'Email',
                    style: {
                        width: '40%',
                    },
                    tag: {
                        name: '',
                        style: ''
                    }
                },
            ],
            members: [
                {
                    id: 1,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                    phone: "0923123456",
                },
                {
                    id: 2,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                    phone: "0923123456",
                },
                {
                    id: 3,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                    phone: "0923123456",
                },
                {
                    id: 4,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                    phone: "0923123456",
                },
                {
                    id: 5,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                    phone: "0923123456",
                },
                {
                    id: 6,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                    phone: "0923123456",
                },
            ],
            actions: ['delete']
        }
    },
    methods: {
        textSearchOnChange(e) {
            this.inputTextSeach = e;
            // Loc nhan vien
        },
        handleAction(e){
            switch(e.action) {
                case 'accept':
                    // call api accept
                    break;
                case 'delete':
                    // call api delete
                    break;
            }
        }
    }
}
</script>