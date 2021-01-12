<template>
    <div>
      <div> <h2>{{this.name}}</h2> </div>
        <div class="listWrap">
          <table class="tbList">
            <colgroup>
              <col width="6%" />
              <col width="*" />
              <col width="10%" />
              <col width="25%" />
            </colgroup>
            <tr>
              <th>no</th>
              <th>제목</th>
              <th>작성자</th>
              <th>날짜</th>
            </tr>
            <tr v-for="(item, postindex) in items" :key="postindex" v-on:click="rowclick(item)">
              <td>{{item.postindex}}</td>
              <td>{{item.title}}</td>
              <td>{{item.writer}}</td>
              <td>{{item.date}}</td>
            </tr>
            <tr v-if="items.length === 0">
              <td colspan="4">데이터가 없습니다.</td>
            </tr>
          </table>
        </div>
        <div class="pagination">
          <a href="javascript:;" @click="fnPage(1)" class="first">&lt;&lt;</a>
          <a href="javascript:;" v-for=" (n,index) in this.pagelist" v-on:click="fnPage(`${n}`)" :key="index">{{n}}</a>
          <a href="javascript:;" @click="fnPage(this.endPage)" class="last">&gt;&gt;</a>
        </div>
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
      endPage: '',
      startPage: 1,
      authority: '',
      items: [],
      listcount: '',
      listdivine: 10,
      pagelist: []
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
    rowclick (item) {
      this.$router.push({
        path: `/boarddetail/${item.postindex}`
      })
    },
    fnPage (pageNum) {
      axios.post('http://localhost:8081/postpage', {startnum: 0 + (pageNum - 1) * this.listdivine, num: this.listdivine})
        .then(response => {
          this.items = response.data
          console.log(this.items)
          console.log(response.data)
        }).catch((ex) => {
          console.warn(ex)
        })
    }
  },
  mounted () {
    axios.post('http://localhost:8081/pagecount')
      .then(response => {
        this.listcount = response.data
        if (this.listcount % this.listdivine !== 0) {
          this.endPage = this.listcount / this.listdivine + 1
        } else {
          this.endPage = this.listcount / this.listdivine
        }
        for (var i = this.startPage; i <= this.endPage; i++) this.pagelist.push(i)
      }).catch((ex) => {
        console.warn(ex)
      })
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
    // axios.post('http://localhost:8081/boardlist')
    //   .then(response => {
    //     this.items = response.data
    //     console.log(this.items)
    //   }).catch((ex) => {
    //     console.warn('ERROR:', ex)
    //   })
    axios.defaults.headers.common['Authorization'] = this.$cookies.get('authority')
    axios.post('http://localhost:8081/userpermission')
      .then(response => {
        this.permission = response.data[0].permission
        this.name = response.data[0].name
      }).catch((ex) => {
        console.warn('ERROR:', ex)
      })
    this.fnPage(1)
  },
  computed: {
    rows () {
      return this.items.length
    }
  }
}
</script>

<style scoped>
.pagination{
  text-align:center;
  margin:20px;
}
</style>
