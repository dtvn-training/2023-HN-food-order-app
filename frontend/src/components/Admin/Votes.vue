<template>
    <div class="votes">
        <Table 
        :columns="columns" 
        :datas="votes" 
        :actions="actions" 
        @onClickAction="handleAction" 
    />
    </div>
</template>

<script>
    import Table from "../Table.vue"
    import Vote from "@/services/vote"

    export default {
        components: {
            Table,
        },
        data(){
            return {
                columns: [
                {
                    key: 'createdName',
                    header: 'Nhân viên',
                    style: {
                        width: '20%',
                    },
                    tag: {
                        name: 'span',
                        style: {},
                    }
                },
                {
                    key: 'restaurantName',
                    header: 'Tên quán',
                    style: {
                        width: '25%',
                    },
                    tag: {
                        name: '',
                        style: {},
                    }
                },
                {
                    key: 'description',
                    header: 'Mô tả',
                    style: {
                        width: '25%',
                    },
                    tag: {
                        name: 'span',
                        style: {},
                    }
                },
                {
                    key: 'totalVote',
                    header: 'Bình chọn',
                    style: {
                        'text-align': 'center',
                        width: '15%',
                    },
                    tag: {
                        name: 'span',
                        style: {},
                    }
                },
                ],
                datas: [
                    {
                        id: 1,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 2,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 3,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 4,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 5,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 6,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 7,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 8,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 9,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 10,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 11,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 12,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 13,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 14,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                    {
                        id: 15,
                        employee: 'Nguyen Van Manh An',
                        restaurant: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
                        description: 'Quán bún chả ngon nhất',
                        vote: "25/35"
                    },
                ],
                actions: ['accept', 'delete'],
                votes: [],
            }
        },
        beforeMount(){
            this.getVotes();
        },
        methods: {
            async getVotes(){
                const votes = await Vote.getAll()
                .then(response => {
                    return response;
                })
                .catch(error => {
                    console.log(error);
                    return [];
                })
                console.log(votes);
                votes.forEach(element => {
                    element.restaurantName = '<a href="'+ element.restaurantUrl+'" target="_blank">'+element.restaurantName+'</a>';
                });
                this.votes = votes;
            },
            async handleAction(e){
                switch(e.name){
                    case 'accept':
                        // api accept vote
                        await Vote.accept(e.id)
                        .catch(error => {
                            console.log(error);
                        });
                        break;
                    case 'delete':
                        // api delete vote
                        await Vote.reject(e.id)
                        .catch(error => {
                            console.log(error);
                        })
                        break;
                }
                this.votes = this.votes.filter(item => item.id != e.id);
            }
        }
    }
</script>

<style scoped>
.votes {
    height: 100%;
    display: grid;
    grid-auto-rows:  auto;
}

</style>