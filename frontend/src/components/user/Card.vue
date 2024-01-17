<template>
    <div class="container">
        <div class="img">
            <img :src="img"
                alt="">
        </div>
        <div class="wrapper">
            <div class="restaurant-name">
                <span>{{ restaurantName }}</span>
            </div>
            <div class="address">
                <Location />
                <span>{{ address }}</span>
            </div>
            <hr>
            <div class="rating">
                <Star />
                <span>{{ star }}</span>
            </div>
        </div>
    </div>
</template>

<style scoped>
.container {
    width: 210px;
    height: 210px;
    background-color: white;
    border: 1px solid rgb(150, 150, 150);
    border-radius: 7px;
    margin: 12px;
    display: flex;
    flex-direction: column;
}

.wrapper {
    padding: 0 7px 0 7px;
}

.img {
    width: 100%;
    overflow: hidden;
}

.img img {
    width: 210px;
    height: 140px;
    object-fit: cover;
}

.restaurant-name {
    font-size: 14px;
    font-weight: 500;
    margin-top: 5px;
    color: rgb(48, 48, 48);
    overflow: hidden;
}
.restaurant-name span{
    white-space: nowrap;
}

.address{
    font-size: 11px;
    margin-bottom: 5px;
    display: flex;
    align-items: center;
    color: rgb(92, 92, 92);
    overflow: hidden;
}

.address span{
    margin-left: 5px;
    white-space: nowrap;
}

.rating {
    font-size: 12px;
    margin-bottom: 4px;
    display: flex;
    align-items: center;
}
.rating span{
    margin-left: 3px;
    margin-top: 5px;
}
</style>

<script>
import Location from "@/components/icons/Location.vue"
import Star from "@/components/icons/Star.vue";

export default {
    components: {
        Location,
        Star
    },
    props: [
        'img',
        'restaurantName',
        'address',
        'star',
    ]

}
</script>