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
.container {
    height: 100%;
    display: grid;
    grid-template-rows: 50px 1px auto;
}
.header {
    height: 50px;
    display: flex;
    flex-direction: row;
    align-items: center;
    /* background: yellowgreen; */
}
</style>

<script>
import Search from "@/components/actions/Search1.vue"
import Table from "@/components/Table.vue";
import Group from "@/services/group"

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
                    key: 'fullName',
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
                    key: 'email',
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
            amembers: [
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
            actions: ['delete'],
            members: []
        }
    },
    beforeMount(){
        this.getMember();
    },
    methods: {
        async getMember(){
            const members = await Group.getMembers()
            .then(response => {
                return response;
            })
            .catch(error => {
                console.log(error);
                return [];
            })
            this.members = members;
        },
        textSearchOnChange(e) {
            this.inputTextSeach = e;
            // Loc nhan vien
        },
        async handleAction(e){
            const email = this.members.filter(item => item.id == e.id)[0].email;
            switch(e.name) {
                case 'delete':
                    // call api delete
                    await Group.delete(email)
                    .then(response => {
                        this.members = this.members.filter(item => item.id != e.id)
                    })
                    .catch(error => {
                        console.log(error);
                    })
                    break;
            }
        }
    }
}
</script>