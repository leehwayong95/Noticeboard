<template>
  <div>
    <b-card>
      <div class="content-detail-content-info">
        <div class="content-detail-content-info-left">
          <div class="content-detail-content-info-left-number">{{list.postindex}}</div>
          <div class="content-detail-content-info-left-subject">{{list.title}}</div>
        </div>
        <div class="content-detail-content-info-right">
            <div class="content-detail-content-info-right-user">{{list.writer}}</div>
            <div class="content-detail-content-info-right-created">{{list.date}}</div>
        </div>
      </div>
      <div class="content-detail-content">{{list.content}}</div>
      <div class="content-detail-button">
          <b-button v-on:click="listback"> 목록 </b-button>
          <b-button v-if="permission === '0'" variant="primary" v-on:click="update"> 수정 </b-button>&nbsp;
          <b-button v-if="permission === '0'" v-on:click="boarddelete"> 삭제</b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      index: '',
      list: [],
      permission: '',
      authority: ''
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
    this.index = this.$route.params.index
    axios.post('http://localhost:8081/boarddetail', {postindex: this.index})
      .then(response => {
        this.list = response.data
      }).catch((ex) => {
        console.warn('ERROR:', ex)
      })
    axios.post('http://localhost:8081/userpermission')
      .then(response => {
        this.permission = response.data[0].permission
      }).catch((ex) => {
        console.warn('ERROR:', ex)
      })
  },
  methods: {
    boarddelete () {
      axios.post('http://localhost:8081/boarddelete', {postindex: this.index})
        .then(response => {
          if (response.data === 'SUCCESS') {
            alert('삭제 성공했습니다.')
            this.$router.push('/boardlist')
          } else {
            alert('삭제에 실패했습니다.')
          }
        })
        .catch((ex) => {
          console.warn('ERROR:', ex)
        })
    },
    listback () {
      this.$router.push('/boardlist')
    },
    update () {
      this.$router.push({path: '/boardupdate', query: {index: this.index}})
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

.content-detail-content-info-left-subject{
  text-align: center;
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
</style>
