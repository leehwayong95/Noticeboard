<template>
  <div>
      <h2>게시판 리스트</h2>
      <div class = "list">
        <table class = "tbList">
          <tr>
            <th>no</th>
            <th>title</th>
            <th>id</th>
            <th>date</th>
          </tr>
          <tr v-for="(row, postindex) in list" :key="postindex">
            <td>{{row.postindex}}</td>
            <td class = "txt_left"><a href="javascript:;">{{row.title}}</a></td>
            <td>{{row.id}}</td>
            <td>{{row.date}}</td>
          </tr>
        </table>
      </div>
      <div class="btnRightWrap">
        <button type="submit" v-on:click="logout">로그아웃</button>
        <button type="button" v-on:click="write">글쓰기</button>
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
      no: ''
    }
  },
  methods: {
    getList () {
      this.$axios.get('http://localhost:9000/api/board')
        .then((res) => {
          this.list = res.data
        })
        .catch((err) => {
          console.log(err, 'fuck')
        })
    },
    logout () {
      document.cookie = 'jwt-auth-token=; expires=Web; 01 Jan 1970'
      this.$router.push('/test')
    },
    write () {
      this.$router.push('/write')
    }
  }
}
</script>
