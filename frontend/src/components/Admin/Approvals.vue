<template>
    <Table 
            :columns="columns"
            :datas="approvals"
            :actions="actions"
            @onClickAction="handleAction"
        />
</template>

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
                },
                {
                    key: 'email',
                    header: 'Email',
                    style: {
                        width: '40%',
                    },
                },
            ],
            approvalss: [
                {
                    id: 1,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                },
                {
                    id: 2,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                },
                {
                    id: 3,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                },
                {
                    id: 4,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                },
                {
                    id: 5,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                },
                {
                    id: 6,
                    member: "Tran Quang Duc",
                    mail: "tranquangduc@gmail.com",
                },
            ],
            actions: ['accept', 'delete'],
            approvals: [],
        }
    },
    beforeMount(){
        this.getApprovals();
    },
    methods: {
        async getApprovals(){
            const approvals = await Group.getApprovals()
            .then(response => {
                return response;
            })
            .catch(error => {
                console.log(error);
                return [];
            })
            this.approvals = approvals;
        },
        async handleAction(e){
            const email = this.approvals.filter(item => item.id == e.id)[0].email;
            // call api
            switch(e.name){
                case 'accept':
                    await Group.accept(email)
                    .then(response => {
                        this.approvals = this.approvals.filter(item => item.id != e.id)
                    })
                    .catch(error => {
                        console.log(error);
                    })
                    break;
                case 'delete':
                    await Group.delete(email)
                    .then(response => {
                        this.approvals = this.approvals.filter(item => item.id != e.id)
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