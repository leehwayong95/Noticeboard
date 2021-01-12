<template>
<div class="container">
    <div>
        <label for="name">제목</label>
        <input type="textarea" v-model="title" placeholder="제목입력"/>
    </div>
    <div>
     <label for="memo">내용</label >
     </div>
    <div>
        <textarea class = "nae"  v-model="content" placeholder="내용입력"></textarea>
    </div>
<button type="button" @click="insert">등록</button>
<button type="button" @click="back">돌아가기</button>
</div>
</template>

<style scoped>
.nae{
    height: 500px;
    width:  300px;
}
</style>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      title: '',
      usernaem: '',
      content: '',
      id: ''
    }
  },
  mounted () {
    const cookie = this.$cookie.get('test')
    axios.post('http://localhost:9000/logincheck', {result: cookie})
      .then(res => {
        if (res.data.result === 'admin') {
          console.log(res)
          this.token = res.data.result
        } else {
          alert('관리자가 아닌 접근')
          this.$router.push('/board')
        }
      })
  },
  methods: {
    insert () {
      axios.post('http://localhost:9000/insertboardtext', {title: this.title, content: this.content, id: this.token})
        .then(res => {
          if (res.status === 200) {
            alert('등록 성공?')
            console.log(res)
            this.$router.push('/board')
          } else {
            alert('등록 실패')
            console.log(res)
            this.$router.push('/board')
          }
        })
        .catch(error => {
          alert('에러')
          console.log(error)
        })
    },
    back () {
      this.$router.push('/board')
    }
  }
}
</script>
