<template>
<div class="container">
    <div>
        <label for="name">제목</label>
        <input type="textarea" v-model="title">
    </div>
    <div>
     <label for="contenrt">내용</label>
     </div>
    <div>
        <textarea class = "nae" for="memo"  v-model="content" ></textarea>
    </div>
<button type="button" @click="reset">수정</button>
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
      title: this.$route.query.data.title,
      // memo: '',
      // usernaem: '',
      content: this.$route.query.data.content,
      id: this.$route.query.data.id,
      test: this.$route.query.data.title,
      post: this.$route.query.data.postindex
    }
  },
  mounted () {
    const cookie = this.$cookie.get('test')
    axios.post('http://localhost:9000/logincheck', {result: cookie})
      .then(res => {
        if (res.data.result === 'empty') {
          alert('로그인 아직안함,혹은 만료')
          this.$router.push('/login')
          console.log(res)
        } else {
          console.log('아직 그대로임: ' + this.token)
        }
      })
  },
  methods: {
    reset () {
      var select = confirm('수정 하시겠습니까?')
      if (select === true) {
        axios.post('http://localhost:9000/updateboardtext', {title: this.title, content: this.content, id: this.id, postindex: this.post})
          .then(res => {
            if (res.data === '') {
              alert('수정 되었습니다. ')
              this.$router.push('/board')
              console.log(res)
            } else {
              console.log('수정이 안됨')
            }
          })
      } else {
        alert('아니오')
      }
    },
    back () {
      this.$router.push('/board')
    }
  }
}
</script>
