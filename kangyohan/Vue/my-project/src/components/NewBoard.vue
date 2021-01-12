<template>
    <div>
        <h2>게시판 리스트</h2>
        <div>
            <table>
            <tr>
                <th>NO</th>
                <th>제목</th>
                <th>작성자</th>
                <th>날짜</th>
            </tr>
            <tr v-for="p in items" :key="p.no" @click="rowClick(p.postindex)">
                <td>{{ p.postindex }}</td>
                <td>{{ p.title }}</td>
                <td>{{ p.id }}</td>
                <td>{{ p.date }}</td>
            </tr>
            </table>
            <div>
              <template v-for=" (n,index) in paginavigation()">
                <template v-if="paging.page==n">
                  <strong :key="index">{{n}}</strong>
                </template>
                <template v-else>
                  <a href="javascript:;" @click="fnPage(`${n}`)" :key="index">{{n}}</a>
                </template>
              </template>
            </div>
            <button type="button" @click="write" >글쓰기</button>
            <button type="button" @click="logout">로그아웃</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import SlidingPagination from 'vue-sliding-pagination'

export default {
  mounted () {
    axios.get('http://localhost:9000/gettestboard', {params: { clickpage: 1 }})
      .then((res) => {
        if (res.data === '') {
          // this.items = res.data
          alert('데이터 실패')
          // this.paging = res.data.paging
          //  this.no = this.paging.totalCount - ((this.paging.page-1) * this.paging.ipp)
        } else {
          console.log(res)
          console.log(res.data)
          this.items = res.data
        }
      })
      .catch((err) => {
        console.log(err)
      })
    axios.get('http://localhost:9000//Allpagecount', {params: { pageNo: 0 }})
      .then((res) => {
        if (res.data > 0) {
          this.paging = res.data
          console.log(res)
          console.log(res.data)
        } else {
          console.log(res)
          alert('여기오류')
        }
      })
      .catch((err) => {
        console.log(err)
      })
  },
  components: {
    SlidingPagination
  },
  // components에 뭐지???
  data () {
    return {
      Manager: '',
      Boardlist: '',
      items: [],
      paging: '',
      start_page: '',
      page: '',
      test: '',
      paginavigation: function () {
        var pageNumber = []
        var startpage = 1
        var endpage = this.paging
        for (var i = startpage; i <= endpage; i++) pageNumber.push(i)
        return pageNumber
      }
    }
  },
  methods: {
    fnGetList () {
      this.test = this.page
      axios.get('http://localhost:9000/gettestboard', {params: { clickpage: this.test }})
        .then((res) => {
          if (res.data === '') {
            alert('실행중 실패했습니다.\n다시 이용해 주세요.')
          //  this.no = this.paging.totalCount - ((this.paging.page-1) * this.paging.ipp)
          } else {
            console.log(res)
            console.log(res.data)
            this.items = res.data
          }
        })
        .catch((err) => {
          console.log(err)
        })
    },
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
    fnPage (n) {
      if (this.page !== n) {
        this.page = n
        this.fnGetList()
      }
    },
    rowClick (items) {
      console.log(items)
      this.$router.push({
        path: '/boardtext',
        query: {num: items}
      })
    }
  }
}
</script>

<style scoped>
.first, .prev, .next, .last{border:1px solid #666; margin:0 5px;}
</style>
