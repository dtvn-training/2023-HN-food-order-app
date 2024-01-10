<template>
    <div class="food-list">
        <Table 
            :columns="columns"
            :datas="datas"
            :actions="actions"
            @onClickAction="handleAction"
        />
    </div>
</template>

<style scoped>
.food-list {
    height: 100%;
    display: grid;
    grid-auto-rows:  auto;
}
</style>

<script>
import Table from "../Table.vue"

export default {
    data() {
        return {
            columns: [
                {
                    key: 'image',
                    header: '',
                    style: {
                        width: '70px',
                        height: '55px',
                    },
                    tag: {
                        name: 'img',
                        style: {
                            width: '60px',
                            height: '45px',
                        },
                    }
                },
                {
                    key: 'name',
                    header: 'Tên món ăn',
                    style: {
                        width: '30%',
                    },
                    tag: {
                        name: 'span',
                        style: ''
                    }
                },
                {
                    key: 'restaurantName',
                    header: 'Tên cửa hàng',
                    style: {
                        width: '25%',
                    },
                    tag: {
                        name: 'span',
                        style: ''
                    }
                },
                {
                    key: 'price',
                    header: 'Giá',
                    style: {
                        width: '100px',
                        'text-align': 'center',
                        'font-weight': 500,
                        color: '#066ECD',
                    },
                    tag: {
                        name: 'span',
                        style: ''
                    }
                },

            ],
            datas: [
                {
                    id: 1,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 2,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 3,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 4,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 5,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 6,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 7,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 8,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 9,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 10,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 11,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 12,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
                {
                    id: 13,
                    name: 'Bún chả',
                    restaurantName: 'Bún chả mẹ Nga',
                    image: 'https://kipor.vn//upload/images/lam-bun-cha-bang-noi-chien-khong-dau-6.jpg',
                    selected: 0,
                    price: 33000
                },
            ],
            actions: ['remove']

        }
    },
    components: {
        Table,
    },
    methods: {
        handleAction(e) {
            // call api remove food from food-group
            this.datas = this.datas.filter(item => item.id != e.id)

        }
    }
}

</script>