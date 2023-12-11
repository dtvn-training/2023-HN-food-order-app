<template>
  <div class="dashboard-container">
    <component :is="currentRole" />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import adminHistory from './admin'
import userHistory from './user'

export default {
  name: 'History',
  components: { adminHistory, userHistory },
  data() {
    return {
      currentRole: 'adminHistory'
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    if (!this.roles.includes('admin')) {
      this.currentRole = 'userHistory'
    }
  }
}
</script>
