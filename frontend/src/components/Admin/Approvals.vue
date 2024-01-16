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
                    key: 'member',
                    header: 'Tên thành viên',
                    style: {
                        width: '30%',
                    },
                },
                {
                    key: 'mail',
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
        getApprovals(){
            const approvals = Group.getApprovals()
            .then(response => {
                return response;
            })
            .catch(error => {
                console.log(error);
                return [];
            })
            this.approvals = approvals;
        },
        handleAction(e){
            // call api
        }
    }
}
</script>