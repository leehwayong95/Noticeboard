<template>
    <div class="sign-up">
        <p>회원가입</p>
        <input v-model="id" type="text" placeholder="userid"><br><br>
        <input v-model="pw" type="password" placeholder="password"><br><br>
        <input v-model="name" type="text" placeholder="username"><br><br>
        <button v-on:click="signup">가입하기</button>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'signUp',
  data () {
    return {
      result: null,
      id: '',
      pw: '',
      name: ''
    }
  },
  methods: {
    signup () {
      axios.post('http://localhost:8081/input', {id: this.id, pw: this.pw, name: this.name})
        .then(response => {
          console.warn(response)
          this.result = response.data
        }).catch((ex) => {
          console.warn('ERROR:', ex)
        })
    }
  }
}
</script>

<style scoped>
</style>
