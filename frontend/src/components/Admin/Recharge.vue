<template>
    <div class="recharge">
        <div class="header" style="display: grid;grid-template-columns: auto 270px 270px 280px 86px;">
            <Search 
                style="width: 60%; min-width: 190px;margin-right: 20px;" 
                width="100%" 
                placeholder="Tên nhân viên..."
                @textSearch="textSearch = $event" :value="textSearch" 
            />
            <DatePick 
                style="margin-right: 20px;" title="Từ ngày" 
                :value="fromDate" 
                @valueOnChange="fromDate = $event" 
            />
            <DatePick 
                style="margin-right: 20px;" 
                title="Đến ngày" :value="toDate" 
                @valueOnChange="toDate = $event" 
            />
            <Select 
                style="margin-right: 20px;" 
                title="Trạng thái" 
                :options="optionStatusBill" 
                :value="selectedStatusBill"
                @selectOnChange="selectedStatusBill = $event" 
            />
            <div class="reset" @click="handleReset">
                <span style="margin-right: 5px;">Reset</span>
                <Refresh />
            </div>
        </div>
        <hr>
        <div class="main">
            <Table
                :columns="columns"
                :datas="recharges"
                :actions="actions"
                @onClickAction="handleAction"
            />
        </div>
    </div>
</template>
<script>
import Search from "@/components/actions/Search1.vue"
import Select from "@/components/actions/Select.vue"
import DatePick from "@/components/actions/DatePick.vue"
import Refresh from "@/components/icons/Refresh.vue"
import Table from "../Table.vue";
import Payment from "@/services/payment"

export default {
    components: {
        Search,
        Select,
        DatePick,
        Refresh,
        Table,
    },
    data() {
        return {
            textSearch: '',
            fromDate: '',
            toDate: '',
            selectedStatusBill: 'none',
            optionStatusBill: [
                {
                    value: 'none',
                    label: '<none>'
                },
                {
                    value: 'selected',
                    label: 'Đã thanh toán'
                },
                {
                    value: 'unselect',
                    label: 'Chưa thanh toán'
                }
            ],
            columns: [
                {
                    key: 'datetime',
                    header: 'Thời gian',
                    style: {
                        width: '200px',
                        'text-align': 'center',
                    },
                },
                {
                    key: 'employee',
                    header: 'Tên nhân viên',
                    style: {
                        width: '30%',
                        'padding-left':'50px'
                    },
                },
                {
                    key: 'amount',
                    header: 'Tổng tiền',
                    style: {
                        width: '200px',
                        'text-align': 'center',
                    },
                },
                {
                    key: 'status',
                    header: 'Trạng thái',
                    style: {
                        width: '200px',
                        'text-align': 'center',
                    },
                    tag: {
                        name: 'span',
                        style: {
                            'padding': '3px 30px 3px 30px',
                            'border':'1px solid black',
                            'border-radius':'5px',
                        }
                    }
                },
            ],
            bills: [
                {
                    id: 1,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
                {
                    id: 2,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
                {
                    id: 3,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
                {
                    id: 4,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
                {
                    id: 5,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
                {
                    id: 6,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
                {
                    id: 7,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
                {
                    id: 8,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
                {
                    id: 9,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
                {
                    id: 10,
                    datetime: '19:30 29/12/2023',
                    employee: 'Nguyen Van Manh An',
                    amount: '100,000',
                    status: 'Paid',
                },
            ],
            actions: ['view'],
            recharges: [],
        }
    },
    beforeMount(){
        this.getRecharge();
    },
    methods: {
        async getRecharge(){
            const recharges = await Payment.getRecharge()
            .then(response => {
                return response;
            })
            .catch(error => {
                console.log(error);
                return [];
            })
            this.recharges = recharges;
        },
        handleReset(){
            location.reload();
            // this.textSearch = '';
            // this.fromDate = '';
            // this.toDate = '';
            // this.selectedStatusBill = 'none';
        },
        handleAction(){

        }
    }
}
</script>

<style scoped>
.recharge {
    height: 100%;
    display: grid;
    grid-auto-rows: 50px 1px auto;
}
.header {
    height: 50px;
    width: 100%;
    display: flex;
    flex-direction: row;
}

.reset {
    display: flex;
    flex-direction: row;
    align-items: center;
    cursor: pointer;
}
</style>