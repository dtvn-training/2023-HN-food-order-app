<template>
    <div class="tab">
        <div class="header">
            <span class="header-text">{{ header }}</span>
        </div>
        <div class="tabs">
            <div class="tab-menu">
                <ul>
                    <li v-for="(tab, index) in tabs" :class="{'active': index == active}" @click="activeTab(index)">
                        <span href="">
                            {{ tab }}
                        </span>
                    </li>
                </ul>
            </div>
            <div class="export" v-if="isExport[active]" >
                <button @click="exportExcel">Export</button>
            </div>
        </div>
        <hr>
    </div>
</template>

<script>
export default {
    props: [
        'header',
        'tabs',
        'isExport',
        'activedTab',
    ],
    data () {
        return {
            active: this.activedTab,
        }
    },
    methods: {
        exportExcel() {
            this.$emit('excelExported');
        },
        activeTab (index) {
            this.active = index;
            this.$emit('tabActived', this.active);
        }
    }
}
</script>

<style scoped>
.tab {
    background: rgb(255, 255, 255);
    height: 89.5px;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.tab .header {
    color: #3d3d3d;
    font-size: 22px;
    font-weight: 600;
    height: 40px;
    display: flex;
    align-items: end;
}

.tab .tabs {
    display: flex;
    flex-direction: row;
    width: 100%;
    justify-content: space-between;
    flex-grow: 1;
}

.tab-menu {
    display: flex;
    align-items: end;
}

.tab-menu ul {
    list-style: none;
    padding: 0;
    display: flex;
}

.tab-menu ul span {
    text-decoration: none;
    font-size: 18px;
    color: #464646;
}

.tab-menu li {
    border: 1px rgb(114, 114, 114) solid;
    border-bottom: none;
    border-radius: 10px 10px 0 0;
    margin-left: 15px;
    padding-left: 45px;
    padding-right: 45px;
    height: 35px;
    align-items: center;
    display: flex;
    align-items: center;
    cursor: pointer;
}

.tab-menu .active {
    border: none;
    background: #D10A0A;
}

.tab-menu .active span {
    color: white;
    font-weight: 400;
}

hr {
    width: 100%;
    margin: 0;
}

.tab .export {
    display: flex;
    align-items: center;
}

.tab .export button {
    width: 120px;
    height: 35px;
    background: #299559;
    color: white;
    font-weight: 300;
    border: none;
    font-size: 16px;
    margin-right: 15px;
}
</style>

