<template>
    <div style="display: grid;grid-template-rows: 89.5px auto; height: 100%">
            <div class="tab">
                <Tab 
                    header="DANH SÁCH THANH TOÁN" 
                    :tabs="tabs" 
                    :isExport="isExport" 
                    :activedTab="active"
                    @excelExported="exportExcel(content[active])" 
                    @tabActived="active = $event" />
            </div>
            <div class="data">
                <Bill v-if="active == 0"/>
                <Recharge v-if="active == 1"/>
            </div>
        </div>
</template>

<style scoped>


</style>

<script>
    import Tab from "@/components/admin/Tab.vue"
    import Bill from "@/components/Admin/Bill.vue";
    import Recharge from "@/components/Admin/Recharge.vue"

    export default {
        components: {
            Tab,
            Bill,
            Recharge,
        },
        data () {
            return {
                tabs: ["Hóa đơn", "Nạp tiền"],
                isExport: [true, true],
                content: ['bill', 'recharge'],
                active: 0,
            }
        },
        methods: {
            exportExcel (data) {
                // content[active]
                console.log(data);
            },
        },
    }
</script>