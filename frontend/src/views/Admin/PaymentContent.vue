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
                <div class="bill">
                    <div class="header">

                    </div>
                    
                </div>
                <div class="deposit">

                </div>

            </div>
        </div>
    </div>
</template>

<script>
    import Tab from "@/components/admin/Tab.vue"

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