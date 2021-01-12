<template>
    <div>
        <b-input v-model="subject" placeholder="제목을 입력해주세요."></b-input>
        <b-input v-model="writers" type="text"></b-input>
        <b-form-textarea
            v-model="content"
            placeholder="내용을 입력해주세요"
            rows="5"
            max-rows="20"
            ></b-form-textarea>
            <br><br>
            <b-button v-on:click="update">수정</b-button>&nbsp;
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
      content: '',
      index: this.$route.query.index
    }
  },
  methods: {
    cancel () {
      this.$router.push('/boardlist')
    },
    update () {
      axios.post('http://localhost:8081/boardupdate', {title: this.subject, content: this.content, postindex: this.index})
        .then(response => {
          if (response.data === 'SUCCESS') {
            alert('수정을 완료했습니다.')
            this.$router.push('boardlist')
          } else {
            alert('수정에 실패했습니다.')
          }
        })
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
    axios.defaults.headers.common['Authorization'] = this.$cookies.get('authority')
    axios.post('http://localhost:8081/boarddetail', {postindex: this.index})
      .then(response => {
        this.writers = response.data.writer
        this.subject = response.data.title
        this.content = response.data.content
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

</style>
