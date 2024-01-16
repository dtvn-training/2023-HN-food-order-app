<template>
    <div class="pagination">    
        <ul>
        <li>
            <button class="directional" type="button" @click="onClickFirstPage"
                :style="{ 'visibility': isInFirstPage || total == 0 ? 'hidden' : '' }">
                <DoubleArrow style="transform: rotate(180deg);"/>
            </button>
        </li>

        <li>
            <button class="directional" type="button" @click="onClickPreviousPage"
                :style="{ 'visibility': current === 1 || total == 0 ? 'hidden' : '' }">
                <Arrow style="transform: rotate(180deg);"/>
            </button>
        </li>

        <li v-for="page in pages">
            <button type="button"
                @click="onClickPage(page.name)"
                :style="{'pointer-events': page.name === current ? 'none' : ''}"
                :class="{ 'current': current == page.name }">
                {{ page.name }}
            </button>
        </li>

        <li>
            <button class="directional" type="button" @click="onClickNextPage"
                :style="{ 'visibility': current === totalPages || total == 0 ? 'hidden' : '' }">
                <Arrow/>
            </button>
        </li>

        <li>
            <button class="directional" type="button" @click="onClickLastPage"
                :style="{ 'visibility': isInLastPage || total == 0 ? 'hidden' : '' }">
                <DoubleArrow/>
            </button>
        </li>
    </ul>
    </div>
</template>

<style scoped>
.pagination {
    width: 100%;
    display: flex;
    justify-content: center;
    margin: 50px 0 20px;
}

ul {
    height: 35px;
    list-style: none;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 0;
}

li {
    height: 100%;
    display: flex;
    align-items: center;
}

button {
    margin-left: 7px;
    margin-right: 7px;
    height: 100%;
    width: 35px;
    height: 35px;
    font-size: 15px;
    border-radius: 50%;
    border: none;
    background: white;
    font-weight: 700;
    color: #D10A0A;
    cursor: pointer;
}
button:hover{ 
    background: rgb(238, 238, 238);
}

.directional {
    margin: 0;
    width: 35px;
    height: 35px;
    font-size: 18px;
    border: none;
}
/* .directional:hover {
    background: rgb(231, 231, 231);
} */

.current {  
    background: #D10A0A;
    color: white;
}
</style>


<script>
import Arrow from "./icons/Arrow.vue";
import DoubleArrow from "./icons/DoubleArrow.vue"

export default {
    components: {
        Arrow,
        DoubleArrow,
    },
    data() {
        return {
            // maxVisibleButtons: 5,
            // totalPages: 22,
            // total: 215,
            // current: 1,
            nextPage: 1,
            startPage: 1,
            current: 1,
        }
    },
    props: {
        maxVisibleButtons: {
            type: Number,
            required: false,
            default: 5
        },
        totalPages: {
            type: Number,
            required: true
        },
        total: {
            type: Number,
            required: true
        },
        currentPage: {
            type: Number,
            required: true
        }
    },
    computed: {
        pages() {
            this.current = this.currentPage;
            const range = [];
            if (this.nextPage == this.startPage - 1) this.startPage = this.startPage - 1;
            if (this.nextPage == this.startPage + this.maxVisibleButtons) this.startPage = this.startPage + 1;
            this.current = this.nextPage;
            
            for (let i = this.startPage;
                i <= Math.min(this.startPage + this.maxVisibleButtons - 1, this.totalPages);
                i += 1) {
                range.push({
                    name: i,
                    isDisabled: i === this.current
                });
            }
            return range;
        },
        isInFirstPage() {
            return this.startPage === 1;
        },
        isInLastPage() {
            if (this.totalPages <= this.maxVisibleButtons)return true;
            return this.startPage == Math.min(this.totalPages - this.maxVisibleButtons + 1, this.totalPages);
        },
    },
    methods: {
        onClickFirstPage() {
            this.startPage = 1;
            this.nextPage = 1;
            this.$emit('pagechanged', 1);
        },
        onClickPreviousPage() {
            this.nextPage = this.current - 1;
            this.$emit('pagechanged', this.current - 1);
        },
        onClickPage(page) {
            this.nextPage = page;
            this.$emit('pagechanged', page);
        },
        onClickNextPage() {
            this.nextPage = this.current + 1;
            this.$emit('pagechanged', this.current + 1);
        },
        onClickLastPage() {
            this.nextPage = this.totalPages;
            this.startPage = this.totalPages - this.maxVisibleButtons + 1;
            this.$emit('pagechanged', this.totalPages);
        }
    }
}
</script>
