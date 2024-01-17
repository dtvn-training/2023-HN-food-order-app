<template>
    <div style="height: 100%;">
        <div class="payment-data">
            <Table
                :columns="columns"
                :datas="payments"
                :actions="actions"
                @onClickAction="handleAction" 
            />
        </div>
        <UserPayment v-if="userPayment == 1"
            @onClickCancel="handleCancel"
            @isWallet="handleViaWallet"
        />
    </div>
</template>

<style>
.payment-data{
    margin-top: 20px;
    height: 100%;
    padding-bottom: 25px;
}
</style>
 
<script>
    import Table from '@/components/Table.vue';
    import UserPayment from '@/popup/UserPayment';

    export default {
        components: {
            Table,
            UserPayment,
        },
        data(){
            return {
                columns:[
                    {
                        key: 'time',
                        header: 'Thời gian',
                        style: {
                            width: '30%',
                            'text-align': 'center',
                        },
                        tag: {
                            name: '',
                            style: ''
                        },
                    },
                    {
                        key: 'totalAmount',
                        header: 'Tổng tiền',
                        style: {
                            width: '25%',
                            'text-align': 'center',
                            'color': '#066ECD',
                            'font-weight': 500,
                        },
                        tag: {
                            name: '',
                            style: ''
                        },
                    },
                    {
                        key: 'status',
                        header: 'Trạng thái',
                        style: {
                            width: '15%',
                            'text-align': 'center',
                            'font-weight': 500,
                            'color': 'rgb(103 103 103)',
                        },
                        tag: {
                            name: '',
                            style: ''
                        },
                    },
                ],
                payments: [
                    {
                        id: 1,
                        time: '19/12/2023 10:30',
                        totalAmount: '150,000',
                        status: 'unpaid',
                    },
                    {
                        id: 1,
                        time: '19/12/2023 10:30',
                        totalAmount: '150,000',
                        status: 'unpaid',
                    },
                    {
                        id: 1,
                        time: '19/12/2023 10:30',
                        totalAmount: '150,000',
                        status: 'paid',
                    },
                    {
                        id: 1,
                        time: '19/12/2023 10:30',
                        totalAmount: '150,000',
                        status: 'paid',
                    },
                    {
                        id: 1,
                        time: '19/12/2023 10:30',
                        totalAmount: '150,000',
                        status: 'unpaid',
                    },
                    {
                        id: 1,
                        time: '19/12/2023 10:30',
                        totalAmount: '150,000',
                        status: 'paid',
                    },
                    {
                        id: 1,
                        time: '19/12/2023 10:30',
                        totalAmount: '150,000',
                        status: 'unpaid',
                    },
                    {
                        id: 1,
                        time: '19/12/2023 10:30',
                        totalAmount: '150,000',
                        status: 'paid',
                    },
                ],
                actions: ['view'],
                userPayment: 1,
            }
        },
        methods:{
            handleAction(){
                //call api 
            },
            handleCancel(){
                this.userPayment = 0;
            },
            handleViaWallet(){}
        }
    }
</script>