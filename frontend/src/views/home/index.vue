<template>
  <div class="dashboard-container">
    <component :is="currentRole" />
  </div>
</template>
  
<script>
import { mapGetters } from 'vuex'
import adminHome from './admin'
import editorHome from './user'

export default {
  name: 'Home',
  components: { adminHome, editorHome },
  data() {
    return {
      currentRole: 'adminHome'
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    if (!this.roles.includes('admin')) {
      this.currentRole = 'editorHome'
    }
  }
}
</script>
  