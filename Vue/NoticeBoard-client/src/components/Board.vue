<template>
  <div>
      <h1>자유 게시판</h1>
      <div class = "list">
        <table class = "tbList">
          <tr>
            <th scope="cols">번호</th>
            <th scope="cols">제목</th>
            <th scope="cols">작성자</th>
            <th scope="cols">작성 시간</th>
          </tr>
          <tr v-for="(row, postindex) in list" :key="postindex" class="board_contents" href="javascript:;" @click="view(row.postindex)">
            <td>{{row.postindex}}</td>
            <td >{{row.title}}</td>
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

<style scoped>
table.tbList {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-left: 1px solid #ccc;
  margin: 20px 10px;
}

table.tbList th {
  padding: 10px;
  font-weight: bold;
  border-top: 1px solid #ccc;
  border-right: 1px solid #ccc;
  border-bottom: 5px solid rgb(53, 91, 112);;
  background: rgb(74, 167, 221);
}

table.tbList td {
  padding: 10px;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

table.tbList tr:hover{
  background-color:rgba(74, 167, 221, 0.3);
}

.btnRightWrap {
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  width: 240px;
}

button {
  width: 110px;
  height: 40px;
  color: #000;
  cursor: pointer;
  display: block;
  position: relative;
  border: 3px solid rgb(74, 167, 221);;
  transition: all 0.4s cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
}

button:hover {
  color: #000 !important;
  background-color: rgb(74, 167, 221);
  text-shadow: none;
}

button:hover:before {
  bottom: 0%;
  top: auto;
  height: 100%;
}

button:before {
  display: block;
  position: absolute;
  left: 0px;
  top: 0px;
  height: 0px;
  width: 100%;
  z-index: -1;
  content: '';
  color: #000 !important;
  background: rgb(74, 167, 221);
  transition: all 0.4s cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
}
</style>
