<template>
    <div>
        <div class="tab">
            <Tab 
                header="NHÓM"
                :tabs="tabs"
                :isExport="isExport"
                :activedTab="active"
                @excelExported="exportExcel(content[active])"
                @tabActived="active = $event"
            />
        </div>
        <div class="data">
            <h1 style="font-weight: 200;">Data of Group</h1>
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
                tabs: ["Thông tin", "Danh sách thành viên", "Phê duyệt"],
                isExport: [false, true, false],
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