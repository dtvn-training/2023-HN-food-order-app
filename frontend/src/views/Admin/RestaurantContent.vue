<template>
    <div style="grid-template-rows: 89.5px auto;display: grid;height:100%">
        <div class="tab">
            <Tab 
                header="DANH SÁCH QUÁN ĂN"
                :tabs="tabs"
                :isExport="isExport"
                :activedTab="tab"
                @excelExported="exportExcel(content[active])"
                @tabActived="tab = $event"
            />
        </div>
        <div class="data">
            <RestaurantList v-if="tab === 0"/>
            <Votes v-if="tab === 1"/>
        </div>
    </div>
</template>

<script>
    import Tab from "@/components/admin/Tab.vue"
    import RestaurantList from "@/components/admin/RestaurantList.vue"
    import Votes from "@/components/admin/Votes.vue"

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
                tab: 0,
            }
        },
        methods: {
            exportExcel (data) {
                // content[active]
                console.log(data);
               
            }
        },
        watch: {
            tab () {
                // console.log(this.tab);
            }
        }
    }
</script>