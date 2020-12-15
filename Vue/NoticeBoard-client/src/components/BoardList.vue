<template>
    <div>
        <b-table
            striped
            hover
            :items="items"
            :per-page="perPage"
            :current-page="currentPage"
            :fields="fields"
        ></b-table>
        <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" align="center"></b-pagination>
        <b-button v-on:click="logout">로그아웃</b-button>
        <b-button v-on:click="write">글쓰기</b-button>
    </div>
</template>

<script>
export default {
  data () {
    return {
      fields: ['index', 'context', 'writer', 'date'],
      items: [
        {isActive: true, date: 40, context: 'thomas', writer: 'vue', index: '1'},
        {isActive: false, date: 21, context: 'cinder', writer: 'vue', index: '2'},
        {isActive: false, date: 34, context: 'woslei', writer: 'vue', index: '3'},
        {isActive: true, date: 83, context: 'diejfosid', writer: 'vue', index: '4'}
      ]
    }
  },
  methods: {
    logout () {
      this.$router.push('/login')
    },
    write () {
      this.$router.push('/boardcreate')
    }
  }
}
</script>

<style scoped>

</style>
