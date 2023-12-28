<template>
    <div>
        <div>
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
                <h1 style="font-weight: 200;">Data of Payments</h1>
            </div>
        </div>
    </div>
</template>

<script>
    import Tab from "../../components/Tab.vue"

    export default {
        components: {
            Tab,
        },
        data () {
            return {
                tabs: ["Hóa đơn", "Nạp tiền"],
                isExport: [true, true],
                content: ['list', 'order'],
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
                // console.log(this.active);
            }
        }
    }
</script>