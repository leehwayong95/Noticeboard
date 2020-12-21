<template>
    <div>
        <b-table
            striped
            hover
            :items="items"
            :per-page="perPage"
            :current-page="currentPage"
            :fields="fields"
            @row-clicked="rowclick"
        ></b-table>
        <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" align="center"></b-pagination>
        <b-button v-on:click="logout">로그아웃</b-button>
        <b-button v-on:click="write">글쓰기</b-button>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      currentPage: 1,
      perPage: 9,
      fields: [
        {
          key: 'postindex',
          label: 'No'
        },
        {
          key: 'title',
          label: '제목',
          tdclass: 'titleclass'
        },
        {
          key: 'writer',
          label: '작성자',
          tbclass: 'writerclass'
        },
        {
          key: 'date',
          label: '날짜',
          tbclass: 'dateclass'
        }],
      items: [
      ]
    }
  },
  methods: {
    logout () {
      this.$router.push('/login')
    },
    write () {
      this.$router.push('/boardcreate')
    },
    rowclick (item, postindex) {
      this.$router.push({
        path: `/boarddetail/${item.postindex}`
      })
      console.warn(item)
    }
  },
  mounted () {
    axios.post('http://localhost:8081/boardlist')
      .then(response => {
        this.items = response.data
      }).catch((ex) => {
        console.warn('ERROR:', ex)
      })
  },
  computed: {
    rows () {
      return this.items.length
    }
  }
}
</script>

<style scoped>
.titleclass{
  max-width: 500px;
}
.writerclass{
  max-width: 200px;
}
.dateclass{
  max-width: 200px;
}
</style>
