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
      name: '',
      permission: '1'
    }
  },
  methods: {
    signup () {
      axios.post('http://localhost:8081/input', {id: this.id, pw: this.pw, name: this.name, permission: this.permission})
        .then(response => {
          if (response.data === 'SUCCESS') {
            alert('회원가입에 성공했습니다.')
            this.$router.push('/login')
          } else {
            alert('중복되는 ID입니다. 다시 입력해주세요.')
          }
        }).catch((ex) => {
          console.warn('ERROR:', ex)
        })
    }
  }
}
</script>

<style scoped>
</style>
