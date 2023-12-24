<template>
    <div>
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
            <h1 style="font-weight: 200;">Data of Restaurant</h1>
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
                // console.log(this.active);
            }
        }
    }
</script>