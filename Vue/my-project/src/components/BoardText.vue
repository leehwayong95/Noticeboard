<template>
  <div>
    <b-card>
      <div class="content-detail-content-info">
        <div class="content-detail-content-info-left">
          <div class="content-detail-content-info-left-number">{{list.postindex}}</div>
          <div class="content-detail-content-info-left-subject">{{list.title}}</div>
        </div>
        <div class="content-detail-content-info-right">
          <div class="content-detail-content-info-right-user">글쓴이: {{list.id}}</div>
          <div class="content-detail-content-info-right-created">등록일: {{list.date}}</div>
        </div>
      </div>
      <div class="content-detail-content">{{list.content}}</div>
      <div class="content-detail-button">
        <b-button variant="primary" @click="updateData"  v-if="Manager == 'admin'">수정</b-button>&nbsp;
        <b-button variant="success" @click="deleteData"  v-if="Manager == 'admin'">삭제</b-button>
         <b-button variant="success" @click="Back">목록</b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      num: null,
      Manager: '',
      list: []
    }
  },
  // name: 'BoardText',
  // num: $route.query.num,
  mounted () {
    const cookie = this.$cookie.get('test')
    axios.post('http://localhost:9000/logincheck', {result: cookie})
      .then(res => {
        if (res.data.result === 'empty') {
          alert('로그인 아직안함,혹은 만료')
          this.$router.push('/login')
          console.log(res)
        } else {
          this.token = res.data.result
          this.Manager = res.data.result
          console.log('아직 그대로임: ' + this.token)
        }
      })
    axios.post('http://localhost:9000/testgetboardtext', {postindex: this.$route.query.num})
      .then(res => {
        if (res.data === '') {
          alert('관리자 정보를 불러올수 없습니다 ')
          console.log(res)
        } else {
          // this.items = res.data
          this.list = res.data
          console.log(this.list)
          console.log(this.list.title)
        }
      })
      .catch(error => {
        alert('잘못된 접근')
        console.log(error)
      })
  },
  methods: {
    updateData () {
      console.log(this.list)
      this.$router.push({
        path: '/boardupdate',
        query: {num: this.$route.query.num, data: this.list}
      })
    },
    deleteData () {
      // confirm('Are you ready?')
      // this.$router.push('/board')
      var select = confirm('진짜로 삭제 하시겠습니까?')
      if (select === true) {
        axios.post('http://localhost:9000/deleteboardtext', {postindex: this.$route.query.num})
          .then(res => {
            if (res.data === '') {
              alert('삭제되었습니다. ')
              this.$router.push('/board')
              console.log(res)
            } else {
              console.log('삭제가 안됨')
            }
          })
      } else {
        alert('아니오')
      }
    },
    Back () {
      this.$router.push('/board')
    }
  }
}
</script>

<style scoped>
.content-detail-content-info {
  border: 1px solid black;
  display: flex;
  justify-content: space-between;
}

.content-detail-content-info-left {
  width: 720px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}

.content-detail-content-info-right {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.content-detail-content {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 720px;
}

.content-detail-button {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}

.content-detail-comment {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}
</style>
