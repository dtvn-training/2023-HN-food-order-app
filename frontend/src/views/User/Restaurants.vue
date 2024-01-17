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
            />
        </div>
        <div class="wrapper">
        <div v-for="restaurant in restaurants">
            <Card 
                :img="restaurant.img"
                :restaurantName="restaurant.name"
                :address="restaurant.address"
                :star="restaurant.star"
            />
        </div>
    </div>
    </div>
</template>
<style scoped>
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

export default {
    components: {
        Card,
        Search
    },
    data() {
        return {
            restaurants: [
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },

                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
                {
                    id: 1,
                    name: "Com chay HOang Ha",
                    address: "123 truong chinh, dong da, ha noi, viet nam",
                    star: 4.3,
                    img: "https://images.foody.vn/res/g111/1103512/prof/s280x175/file_restaurant_photo_tztn_16321-c131f83c-210920135931.jpg",
                },
            ]
        }
    }
}
</script>