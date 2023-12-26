<template>
    <div class="restaurant-list">
        <div class="filter">
            <div class="search" style="width: 31.5%;">
                <Search class="search"
                width="100%" 
                placeholder="Tìm quán ăn" 
            />
            </div>
            <div class="select">
                <Select class="status"
                    title="Trạng thái" 
                    :options="statusOptions"
                    :value="statusValue"    
                />
                <Select class="sort"
                    title="Sắp xếp" 
                    :options="sortOptions"
                    :value="sortValue"    
                />
            </div>
        </div>
        <hr>
        <Table 
            style="margin-top: 10px"
            :columns="columns"
            :datas="datas"
            :actions="actions"
        />
        <Pagination 
            :totalPages="22"
            :total="215"
            :currentPage="1"
        />
    </div>
</template>

<style scoped>
.restaurant-list .filter {
    height: 50px;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    background: rgb(255, 255, 255);
}
.restaurant-list .select {
    display: flex;
    flex-direction: row;
}
.restaurant-list .status, .sort {
    margin-left: 20px;
    margin-right: 15px;
}
</style>

<script>
import Search from "./Search1.vue"
import Select from "./Select.vue"
import Table from "./Table.vue"
import Pagination from "./Pagination.vue"

export default {
    components: {
        Search,
        Select,
        Table,
        Pagination,
    },
    data() {
        return {
            columns: [
                {
                    key: 'no',
                    header: 'No.',
                    style: {
                        width: '50px',
                        'text-align': 'center'
                    },
                },
                {
                    key: 'name',
                    header: 'Name',
                    style: {
                        width: '20%',
                    },
                },
                {
                    key: 'description',
                    header: 'Description',
                    style: {
                        width: '30%',
                    },
                },
                {
                    key: 'url',
                    header: 'Url',
                    style: {
                        width: '30%',
                    },
                },
            ],
            datas: [
                {
                    id: 3,
                    no: 1,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 0,
                },
                {
                    id: 4,
                    no: 2,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 1,
                },
                {
                    id: 5,
                    no: 3,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 0,
                },
                {
                    id: 3,
                    no: 4,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 0,
                },
                {
                    id: 4,
                    no: 5,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 1,
                },
                {
                    id: 5,
                    no: 6,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 0,
                },
                {
                    id: 3,
                    no: 7,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 0,
                },
                {
                    id: 4,
                    no: 8,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 1,
                },
                {
                    id: 5,
                    no: 9,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 0,
                },
                {
                    id: 5,
                    no: 10,
                    name: 'Bún chả mẹ Nga',
                    description: 'Quán bún chả ngon nhất',
                    url: 'https://www.google.com/search?q=excel&sca_esv=592395163&tbm=isch&sxsrf=AM9HkKnyvP3uL3R-94r2eAwI5-eXy58epA',
                    selected: 0,
                },
            ],
            actions: ['check', 'view', 'delete'],
            statusOptions: [{
                value: 'none',
                label: '<none>'
            }, {
                value: 'selected',
                label: 'Đã chọn'
            }, {
                value: 'unselect',
                label: 'Chưa chọn'
            }],
            statusValue: 'none',
            sortOptions: [{
                value: 'none',
                label: '<none>'
            }, {
                value: 'anpha',
                label: 'A-Z'
            }, {
                value: 'dateAdded',
                label: 'Ngày thêm'
            }],
            sortValue: 'none',
        }
    }

}
</script>