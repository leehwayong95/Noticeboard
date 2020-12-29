<template>
    <div>
        <b-input v-model="subject" placeholder="제목을 입력해주세요."></b-input>
        <b-input v-model="writers" type="text"></b-input>
        <b-form-textarea
            v-model="context"
            placeholder="내용을 입력해주세요"
            rows="5"
            max-rows="20"
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
      test: ''
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
    axios.defaults.headers.common['Authorization'] = this.$cookies.get('test')
    axios.post('http://localhost:8081/tokenname')
      .then(response => {
        this.writers = response.data
      })
      .catch((ex) => {
        console.log(ex)
      })
  }
  /* mounted () {
    axios.defaults.headers.common['Authorization'] = this.$cookies.get('test')
    axios.post('http://localhost:8081/tokenname')
      .then(response => {
        this.test = response.data
      })
      .catch((ex) => {
        console.log(ex)
      })
  } */
}
</script>

<style scoped>

</style>
