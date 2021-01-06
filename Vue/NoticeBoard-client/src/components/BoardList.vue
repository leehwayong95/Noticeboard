<template>
    <div>
      <div> {{this.name}} </div>
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
        <b-button v-if="permission === '0'" v-on:click="write">글쓰기</b-button>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      name: '',
      permission: '',
      currentPage: 1,
      perPage: 9,
      authority: '',
      fields: [
        {
          key: 'postindex',
          label: 'No',
          class: 'noclass',
          thStyle: {
            width: '100px'
          }
        },
        {
          key: 'title',
          label: '제목',
          class: 'titleclass',
          thStyle: {
            width: '700px'
          }
        },
        {
          key: 'writer',
          label: '작성자',
          class: 'writerclass',
          thStyle: {
            width: '200px'
          }
        },
        {
          key: 'date',
          label: '날짜',
          class: 'dateclass',
          thStyle: {
            width: '200px'
          }
        }],
      items: [
      ]
    }
  },
  methods: {
    logout () {
      this.$cookies.remove('authority')
      this.$router.push('/login')
      alert('로그아웃 되었습니다.')
    },
    write () {
      this.$router.push('/boardcreate')
    },
    rowclick (item, postindex) {
      this.$router.push({
        path: `/boarddetail/${item.postindex}`
      })
    }
  },
  mounted () {
    this.authority = this.$cookies.get('authority')
    if (this.authority !== null) {
      axios.defaults.headers.common['Authorization'] = this.authority
      axios.post('http://localhost:8081/tokencheck')
        .then(response => {
          if (response.data === 'FALSE') {
            alert('로그인이 필요합니다.')
            this.$router.push('/login')
          } else {
          }
        })
        .catch((ex) => {
          console.warn('ERROR:', ex)
        })
    }
    axios.post('http://localhost:8081/boardlist')
      .then(response => {
        this.items = response.data
      }).catch((ex) => {
        console.warn('ERROR:', ex)
      })
    axios.defaults.headers.common['Authorization'] = this.$cookies.get('authority')
    axios.post('http://localhost:8081/userpermission')
      .then(response => {
        this.permission = response.data[0].permission
        this.name = response.data[0].name
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

</style>
