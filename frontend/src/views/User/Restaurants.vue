<template>
    <div class="restaurant">
        <div class="title">
            <span>Danh sách cửa hàng hôm nay</span>
        </div>
        <div class="searchBar">
            <Search
                placeholder="Tên cửa hàng"
                value=""
                width="100%"
                borderRadius="20px"
                height="40px"
                @textSearch="handleSearch"
            />
        </div>
        <div class="wrapper">
        <div v-for="restaurant in restaurants">
            <div class="cart" @click="handleOnClickCard(restaurant)">
                <Card
                :img="restaurant.img"
                :restaurantName="restaurant.name"
                :address="restaurant.address"
                :star="restaurant.rating"
            />
            </div>
        </div>
    </div>
    </div>
</template>
<style scoped>
.cart {
    cursor: pointer;
}
.wrapper {
    height: auto;
    width: 100%;
    /* background-color: rgb(162, 149, 196); */
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    margin-bottom: 20px;
}
.title {
    font-size: 45px;
    padding-left: 10px;
    font-weight: 500;
    color: rgb(56, 56, 56);
    margin-top: 20px;
}
.searchBar{
    margin: 10px 0 10px;
    padding: 0 10px 0 10px;
}
</style>

<script>
import Card from "@/components/user/Card.vue"
import Search from "@/components/actions/Search1"
import Restaurant from '@/services/restaurant.js'

export default {
    components: {
        Card,
        Search
    },
    data() {
        return {
            restaurants: [
            ],
            textSearch:'',
        }
    },
    beforeMount(){
        this.getRestaurant();
    },
    methods:{
        async getRestaurant(){
            const restaurants = await Restaurant.getRestaurantToday([])
            .then(response => {
                return response;
            })
            .catch(error => {
                console.log(error);
                return [];
            })
            this.restaurants = restaurants;
        },
        handleOnClickCard(restaurant){
            console.log(restaurant);
            const id = restaurant.id;
            this.$router.push({ name: 'restaurantInfo', params: { id } });
        },
        async handleSearch(e){
            let params = new Map();
            params.set('restaurantName', e);
            const restaurants = await Restaurant.getRestaurantToday(params)
            .then(response => {
                return response;
            })
            .catch(error => {
                console.log(error);
                return [];
            })
            this.restaurants = restaurants;
        }
    }
}
</script>