<template>
  <div>
    <b-table striped hover
    :items="items"
    :fields="fields"
    :per-page="perPage"
    :current-page="currentPage"
    @row-clicked="rowClick"
    ></b-table>
     <b-pagination v-model="currentPage" :per-page="perPage" align="center"></b-pagination>
     <b-button @click="write">글쓰기</b-button>
  </div>
</template>

<script>
// import data from '@/data'
import axios from 'axios'
// let items = data.Content.sort((a, b) => { return b.content_id - a.content_id })
// items = items.map(contentItem => {  return { ...contentItem, user_name: data.User.filter(userItem => userItem.user_id === contentItem.user_id)[0].name}})
export default {
  mounted () {
    axios.post('http://localhost:9000/getboard', {id: 'admin'})
      .then(res => {
        if (res.data === '') {
          alert('관리자 정보를 불러올수 없습니다 ')
          console.log(res)
        } else {
          this.Boardlist = res.data
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
      Boardlist: '',
      currentPage: 1,
      perPage: 10,
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
        path: '/Boardcreate'
      })
    },
    rowClick (items, postindex) { // 두개 지워도 잘된다 보통 2개의 값을 가지고 온것인데 이걸 어떻게 해석해야할까?
      console.log(items)
      this.$router.push({
        path: '/BoardText',
        query: {num: items}
      })
    }
  }
}
</script>
