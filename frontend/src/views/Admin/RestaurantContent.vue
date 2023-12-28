<template>
    <div style="grid-template-rows: 89.5px auto;display: grid;height:100%">
        <div class="tab">
            <Tab 
                header="DANH SÁCH QUÁN ĂN"
                :tabs="tabs"
                :isExport="isExport"
                :activedTab="active"
                @excelExported="exportExcel(content[active])"
                @tabActived="active = $event"
            />
        </div>
        <div class="data">
            <RestaurantList v-if="active === 0"/>
            <Votes v-if="active === 1"/>
        </div>
    </div>
</template>

<script>
    import Tab from "../../components/Tab.vue"
    import RestaurantList from "../../components/RestaurantList.vue"
    import Votes from "../../components/Votes.vue"

    export default {
        components: {
            Tab,
            RestaurantList,
            Votes
        },
        data () {
            return {
                tabs: ["Quán ăn", "Bình chọn"],
                isExport: [true, true],
                content: ['restaurant', 'votes'],
                active: 0,
            }
        },
        methods: {
            exportExcel (data) {
                // content[active]
                console.log(data);
               
            }
        },
        watch: {
            active () {
                console.log(this.active);
            }
        }
    }
</script>