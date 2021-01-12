<template>
    <div>
        <b-input v-model="subject" placeholder="제목을 입력해주세요."></b-input>
        <b-input v-model="writers" type="text"></b-input>
        <b-form-textarea
            v-model="context"
            placeholder="내용을 입력해주세요"
            rows="5"
            max-rows="20"
            class="textareaclass"
            ></b-form-textarea>
            <br><br>
            <b-button v-on:click="create">저장</b-button>&nbsp;
            <b-button v-on:click="cancel">취소</b-button>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      subject: '',
      writers: '',
      context: '',
      authority: ''
    }
  },
  methods: {
    create () {
      axios.post('http://localhost:8081/boardcreate', {title: this.subject, content: this.context, writer: this.writers})
        .then(response => {
          alert('작성 완료')
          this.$router.push('/boardlist')
        })
        .catch((ex) => {
          alert('작성 실패')
        })
    },
    cancel () {
      this.$router.push('/boardlist')
    }
  },
  created () {
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
    axios.post('http://localhost:8081/tokenname')
      .then(response => {
        this.writers = response.data
      })
      .catch((ex) => {
        console.log(ex)
      })
    axios.post('http://localhost:8081/userpermission')
      .then(response => {
        if (response.data[0].permission === '1') {
          alert('관리자가 아닙니다.')
          this.$router.push('/login')
        } else {
        }
      }).catch((ex) => {
        console.warn('ERROR:', ex)
      })
  }
}
</script>

<style scoped>
.b-form.textareaclass{
 height: '400px';
}
*{margin:30px;}
</style>
