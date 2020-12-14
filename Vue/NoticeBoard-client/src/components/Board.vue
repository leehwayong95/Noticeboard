<template>
  <div>
      <h2>게시판 리스트</h2>
      <div class = "list">
        <table class = "tbList">
          <tr>
            <th>no</th>
            <th>title</th>
            <th>작성자</th>
            <th>date</th>
          </tr>
          <tr v-for="(row, postindex) in list" :key="postindex">
            <td>{{row.postindex}}</td>
            <td class = "txt_left"><a href="javascript:;" @click="view(row.postindex)">{{row.title}}</a></td>
            <td>{{row.name}}</td>
            <td>{{row.date}}</td>
          </tr>
        </table>
      </div>
      <div class="btnRightWrap">
        <button type="submit" v-on:click="logout">로그아웃</button>
        <button type="button" v-if="!permission" v-on:click="write">글쓰기</button>
      </div>
  </div>
</template>

<script>
export default {
  mounted () {
    this.getList()
  },
  data () {
    return {
      body: '',
      list: '',
      postindex: '',
      permission: true
    }
  },
  methods: {
    getList () {
      this.$axios.get('http://3.35.254.128/api/board')
        .then((res) => {
          this.list = res.data
        })
        .catch((err) => {
          console.log(err, 'fuck')
        })
      let cookie = document.cookie.split(';')
      for (let i in cookie) {
        if (cookie[i].split('=')[0].trim() === 'permission' && cookie[i].split('=')[1].trim() === '0') {
          this.permission = false
        }
      }
    },
    logout () {
      document.cookie = 'jwt-auth-token=; expires=Web; 01 Jan 1970'
      document.cookie = 'permission=; expires=Web; 01 Jan 1970'
      this.$router.push('/test')
    },
    write () {
      this.$router.push('/write')
    },
    view (num) {
      this.$router.push({
        path: 'board/view',
        query: {index: num}
      })
    }
  }
}
</script>
