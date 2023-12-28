<template>
    <div>
        <div class="tab">
            <Tab 
                header="MÓN NGON HÔM NAY"
                :tabs="tabs"
                :isExport="isExport"
                :activedTab="active"
                @excelExported="exportExcel(content[active])"
                @tabActived="active = $event"
            />
        </div>
        <div class="data">
            <h1 style="font-weight: 200;">Data of Foods</h1>
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
                tabs: ["Danh sách món ăn", "Đặt món"],
                isExport: [true, false],
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