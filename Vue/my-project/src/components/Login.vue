<template>
    <div>
        <h3>로그인</h3><br/><br/>
        <label for="username">Username : </label>
        <input
            type="text"
            v-model="id"
        />
        <br /><br/>
        <label for="userpassword">UserPassword : </label>
        <input
            type="password"
            v-model="pw"
        />
        <br/><br/>
        <button type="String" @click="Login">로그인</button>
        <button type="button" @click="Join">회원가입</button>
        <button type="button" @click="Join">페이지 이동</button>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      id: null,
      pw: null
    }
  },
  methods: {
    Join () {
      this.$router.push('/Join')
    },
    Login () {
      axios.post('http://localhost:9000/login2', {id: this.id, pw: this.pw})
        .then(res => {
          if (res.data === '') {
            alert('로그인 싪패')
            console.log(res)
          } else {
            alert('로그인 성공')
            console.log(res)
            this.$router.push('/board')
          }
        })
        .catch(error => {
          alert('아이디와 비밀번호를 확인하세요')
          console.log(error)
        })
    }
  }
}
</script>
