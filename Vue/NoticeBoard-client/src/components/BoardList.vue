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
        <b-button>글쓰기</b-button>
    </div>
</template>

<script>
export default {
  data () {
    return {
      fields: ['first_name', 'last_name', 'age'],
      items: [
        {isActive: true, age: 40, first_name: 'thomas', last_name: 'vue'},
        {isActive: false, age: 21, first_name: 'cinder', last_name: 'vue'},
        {isActive: false, age: 34, first_name: 'woslei', last_name: 'vue'},
        {isActive: true, age: 83, first_name: 'diejfosid', last_name: 'vue'}
      ]
    }
  }
}
</script>

<style scoped>

</style>
