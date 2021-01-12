<template>
  <div>
    <b-table striped hover
    :items="items"
    :fields="fields"
    :per-page="perPage"
    :current-page="currentPage"
    @row-clicked="rowClick"
    ></b-table>
     <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" align="center"></b-pagination>
     <b-button @click="write" v-if="Manager == 'admin'">글쓰기</b-button>
     <b-button @click="logout">로그아웃</b-button>
  </div>
</template>

<script>
// import data from '@/data'
import axios from 'axios'

export default {
  mounted () {
    const cookie = this.$cookie.get('test')
    axios.post('http://localhost:9000/logincheck', {result: cookie})
      .then(res => {
        if (res.data.result === 'empty') {
          alert('로그인 아직안함,혹은 만료')
          console.log(res)
          this.$router.push('/login')
        } else {
          this.Manager = res.data.result
          console.log(res)
        }
      })
    axios.post('http://localhost:9000/getboard', {id: 'admin'})
      .then(res => {
        if (res.data === '') {
          alert('관리자 정보를 불러올수 없습니다 ')
          console.log(res)
        } else {
          // this.Boardlist = res.data
          this.items = res.data
          console.log(res)
        }
      })
      .catch(error => {
        alert('잘못된 접근')
        console.log(error)
      })
  },
  data () {
    return {
      Manager: '',
      Boardlist: '',
      currentPage: 1,
      perPage: 5,
      items: [],
      fields: [
        {
          key: 'postindex',
          label: '번호'
        },
        {
          key: 'title',
          label: '제목'
        },
        {
          key: 'id',
          label: '글쓴이'
        },
        {
          key: 'date',
          label: '작성일'
        }
      ]
    }
  },
  methods: {
    write () {
      this.$router.push({
        path: '/boardnew'
      })
    },
    logout () {
      this.$cookie.delete('test')
      this.$router.push({
        path: '/login'
      })
    },
    rowClick (items) { // 두개 지워도 잘된다 보통 2개의 값을 가지고 온것인데 이걸 어떻게 해석해야할까?
      this.$router.push({
        path: '/boardText',
        query: {num: items.postindex}
      })
    }
  },
  computed: {
    rows () {
      return this.items.length
    }
  }
}
</script>
