<template>
    <div class="votes">
        <div class="added">
            <button @click="voteCreate = 1">Thêm</button>
        </div>
        <div class="vote-data">
            <Table
                :columns="columns"
                :datas="votes"
                :actions="actions"
                @onClickAction="handleAction" 
            />
        </div>
        <VoteCreate v-if="voteCreate == 1"
            @onClickCancel="handleCancel"
        />
    </div>
</template>

<style scoped>
    .votes, .vote-data {
        height: 100%;
    }
    .votes {
        display: grid;
        grid-template-rows: 52px auto;
    }
    .added { 
        display: flex;
        justify-content: end;
        margin: 10px 0 10px;
    }
    .added button {
        height: 32px;
        width: 85px;
        background: #00bd78;
        color: white;
        border: none;
        border-radius: 5px;
        margin-right: 10px;
    }
    .added button:hover{
        cursor: pointer;
        background: #01d889;
    }
</style>

<script>
    import Table from "@/components/Table"
    import VoteCreate from "@/popup/VoteCreate.vue"
    import Vote from '@/services/vote'

    export default {
        components: {
            Table,
            VoteCreate,
        },
        data(){
            return {
                columns:[
                    {
                        key: 'createdName',
                        header: 'Nhân viên',
                        style: {
                            width: '20%',
                        },
                        tag: {
                            name: '',
                            style: ''
                        },
                    },
                    {
                        key: 'restaurantName',
                        header: 'Quán ăn',
                        style: {
                            width: '25%',
                        },
                        tag: {
                            name: '',
                            style: ''
                        },
                    },
                    {
                        key: 'description',
                        header: 'Mô tả',
                        style: {
                            width: '25%',
                        },
                        tag: {
                            name: '',
                            style: ''
                        },
                    },
                    {
                        key: 'totalVote',
                        header: 'Tổng vote',
                        style: {
                            width: '15%',
                            'text-align': 'center',
                        },
                        tag: {
                            name: '',
                            style: ''
                        },
                    },
                ],
                votess: [
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 0,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 0,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 0,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 0,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                    {
                        id: 1,
                        employee: "nguyen van an",
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả mon nhức nách',
                        totalVote: 15,
                        status: 'Approved',
                        selected: 1,
                    },
                ],
                actions:['heart'],
                voteCreate: 0,
                votes: []
            }
        },
        beforeMount(){
            this.getVotes();
        },
        methods: {
            async getVotes(){
                const votes = await Vote.userGetVote()
                .then(response => {
                    return response;
                })
                this.votes = votes;
                console.log(this.votes);
            },
            handleAction(){

            },
            handleCancel(){
                this.voteCreate = 0;
            }
        }

    }
</script>