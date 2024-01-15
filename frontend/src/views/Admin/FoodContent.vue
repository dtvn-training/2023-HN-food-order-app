<template>
    <div style="grid-template-rows: 89.5px auto;display: grid;height:100%">
        <div class="tab">
            <Tab 
                header="MÓN NGON HÔM NAY"
                :tabs="tabs"
                :isExport="isExport"
                :activedTab="tab"
                @excelExported="exportExcel(content[active])"
                @tabActived="changeTab"
            />
        </div>
        <div class="data">
            <!-- <h1 style="font-weight: 200;">Data of Foods</h1> -->
            <FoodList v-if="tab === 0"/>
            <Order v-if="tab === 1"/>
        </div>
    </div>
</template>

<script>
    import Tab from "@/components/admin/Tab.vue"
    import FoodList from "@/components/admin/FoodList.vue"
    import Order from "@/components/admin/Order.vue"

    export default {
        components: {
            Tab,
            FoodList,
            Order,
        },
        data () {
            return {
                tabs: ["Danh sách món ăn", "Đặt món"],
                isExport: [true, false],
                content: ['list', 'order'],
                tab: 0,
            }
        },
        methods: {
            exportExcel (data) {
                // content[active]
               
            },
            changeTab (e) {
                this.tab = e;
                // console.log(this.tab);
            }
        },
    }
</script>