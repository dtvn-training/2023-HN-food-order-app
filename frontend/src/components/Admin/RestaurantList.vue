<template>
    <div class="restaurant-list">
        <div class="filter">
            <div class="search" style="width: 31.5%;">
                <Search class="search" width="100%" placeholder="Tìm quán ăn..." />
            </div>
            <div class="select">
                <Select class="status" title="Trạng thái" :options="statusOptions" :value="statusValue" />
                <Select class="sort" title="Sắp xếp" :options="sortOptions" :value="sortValue" />
            </div>
        </div>
        <hr>
        <Table :columns="columns" :datas="restaurantList" :actions="actions" @onClickAction="handleAction" />
        <RestaurantMenu 
        v-if="restaurantMenu === 1" 
            :categories="restaurant.dishCategoryList" 
            :restaurantName="restaurant.name" 
            @onClickCancel="cancelRestaurantMenu"
            @onClickSave="saveRestaurantMenu" 
        />
    </div>
</template>

<script>
import Search from "../actions/Search1.vue"
import Select from "../actions/Select.vue"
import Table from "../Table.vue"
import RestaurantMenu from "../../popup/RestaurantMenu.vue"
import Restaurant from "@/services/restaurant"

export default {
    components: {
        Search,
        Select,
        Table,
        RestaurantMenu,
    },
    data() {
        return {
            columns: [
                {
                    key: 'name',
                    header: 'Name',
                    style: {
                        width: '35%',
                    },
                    tag: {
                        name: '',
                        style: ''
                    }
                },
                {
                    key: 'description',
                    header: 'Description',
                    style: {
                        width: '45%',
                    },
                    tag: {
                        name: 'span',
                        style: ''
                    }
                },
            ],
            // datass: [
            //     {
            //         id: 1,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA" target="_blank">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 2,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 1,
            //     },
            //     {
            //         id: 3,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 4,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 5,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 1,
            //     },
            //     {
            //         id: 6,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 7,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 8,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 1,
            //     },
            //     {
            //         id: 9,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 10,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 11,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 12,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 13,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 14,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            //     {
            //         id: 15,
            //         name: '<a href="https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA">Bún chả mẹ Nga</a>',
            //         description: 'Quán bún chả ngon nhất',
            //         selected: 0,
            //     },
            // ],
            actions: ['check', 'view', 'delete'],
            statusOptions: [{
                value: 'none',
                label: '<none>'
            }, {
                value: 'selected',
                label: 'Đã chọn'
            }, {
                value: 'unselect',
                label: 'Chưa chọn'
            }],
            statusValue: 'none',
            sortOptions: [{
                value: 'none',
                label: '<none>'
            }, {
                value: 'anpha',
                label: 'A-Z'
            }, {
                value: 'dateAdded',
                label: 'Ngày thêm'
            }],
            sortValue: 'none',
            restaurantMenu: 0,
            // categories: [
            //     {
            //         name: "GA NGON",
            //         foods: [
            //             {
            //                 id: 1,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc MaGa oc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa Moc MaGa MMoc MaGa Moc MaGa Moc Mat",
            //                 price: "133,000",
            //                 selected: 0,
            //             },
            //             {
            //                 id: 2,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc Mat",
            //                 price: "133,000",
            //                 selected: 1,
            //             }
            //         ]
            //     },
            //     {
            //         name: "GA NGON",
            //         foods: [
            //             {
            //                 id: 3,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc Mat",
            //                 price: "133,000",
            //                 selected: 0,
            //             },
            //             {
            //                 id: 4,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc Mat",
            //                 price: "133,000",
            //                 selected: 1,
            //             }
            //         ]
            //     },
            //     {
            //         name: "GA NGON",
            //         foods: [
            //             {
            //                 id: 5,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc Mat",
            //                 price: "133,000",
            //                 selected: 0,
            //             },
            //             {
            //                 id: 6,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc Mat",
            //                 price: "133,000",
            //                 selected: 1,
            //             },
            //             {
            //                 id: 7,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc Mat",
            //                 price: "133,000",
            //                 selected: 0,
            //             },
            //             {
            //                 id: 8,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc Mat",
            //                 price: "133,000",
            //                 selected: 1,
            //             },
            //             {
            //                 id: 9,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc Mat",
            //                 price: "133,000",
            //                 selected: 0,
            //             },
            //             {
            //                 id: 10,
            //                 image: "https://cdn.tgdd.vn/2021/03/content/1-800x500-9.jpg",
            //                 name: "Ga Moc Mat",
            //                 price: "133,000",
            //                 selected: 1,
            //             }
            //         ]
            //     }
            // ],
            restaurantList: [],
            restaurant: {},
        }
    },
    beforeMount(){
        this.getRestaurantList();
    },
    methods: {
        async getRestaurantList(){
            const datas = await Restaurant.getAll([])
            .then(response => {
                    return response;
                }
            )
            .catch(error => {
                console.log(error);
                return [];
            });

            datas.forEach(item => {
                if (item.selected == false)item.selected = 0;
                else item.selected = 1;
                item.id = item.deliveryId;
                delete item.deliveryId;
            })
            this.restaurantList = datas;
        },
        cancelRestaurantMenu(e) {
            this.restaurantMenu = 0;
        },
        saveRestaurantMenu(e) {
            this.categories = e;
            this.restaurantMenu = 0;
            
        },
        async handleAction(e) {
            switch (e.name) {
                case 'delete':
                    const params = new Map();
                    params.set('delivery_id', e.id);
                    await Restaurant.removeFoodUserList(params);
                    this.restaurantList = this.restaurantList.filter(item => item.id != e.id)
                    break;
                case 'check':
                    this.restaurantList = this.restaurantList.map(item => {
                        if (item.id === e.id) {
                            // Nếu là đối tượng cần cập nhật, thì cập nhật thông tin name
                            const params = new Map();
                            params.set('delivery_id', item.id);
                            if (item.selected == 1){
                                Restaurant.unCheckFoodUserList(params);
                            }else{
                                Restaurant.addFoodUserList(params);
                            }
                            return { ...item, selected: !item.selected };
                        } else {
                            // Nếu không phải, giữ nguyên đối tượng
                            return item;
                        }
                    });
                    break;
                case 'view':
                    let restaurant = await Restaurant.getRestaurant(e.id)
                    .then(response => {
                        return response;
                    })
                    .catch(error => {
                        console.log(error);
                        return [];
                    })
                    this.restaurant = restaurant;
                    this.restaurantMenu = 1;
                    console.log(restaurant);
                    break;
            }
        }

    }
}
</script>

<style scoped>
.restaurant-list {
    height: 100%;
    display: grid;
    grid-auto-rows: 50px 1px auto;
}

.restaurant-list .filter {
    height: 50px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    background: rgb(255, 255, 255);
}

.restaurant-list .select {
    display: flex;
    flex-direction: row;
}

.restaurant-list .status,
.sort {
    margin-left: 20px;
    margin-right: 15px;
}
</style>