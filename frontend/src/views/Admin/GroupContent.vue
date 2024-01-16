<template>
    <div style="grid-template-rows: 89.5px auto;display: grid;height:100%">
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
        <div class="data" style="height: 100%;">
            <GroupInfo v-if="active === 0"/>
            <Members v-if="active === 1"/>
            <Approvals v-if="active === 2"/>
        </div>
    </div>
</template>

<script>
    import Tab from "@/components/admin/Tab.vue"
    import GroupInfo from "@/components/admin/GroupInfo.vue"
    import Members from "@/components/admin/Members.vue"
    import Approvals from "@/components/admin/Approvals.vue"

    export default {
        components: {
            Tab,
            GroupInfo,
            Members,
            Approvals,
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
                console.log(this.active);
            }
        }
    }
</script>