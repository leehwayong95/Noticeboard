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
      pw: null,
      token: ''
    }
  },
  methods: {
    Join () {
      this.$router.push('/Join')
    },
    Login () {
      axios.post('http://localhost:9000/login2', {id: this.id, pw: this.pw})
        .then(res => {
          if (res.data.result === 'empty') {
            alert('로그인 실패')
            console.log(res)
          } else {
            alert('로그인 성공')
            console.log(res)
            this.token = res.data.result
            console.log('토큰입니다: ' + this.token)
            // this.$cookie.set('accesstoken', res.data.data.token, 1)
            this.$cookie.set('test', this.token, 60)
            const cookie = this.$cookie.get('test')
            alert(cookie)
            this.$router.push('/board')
          }
        })
    }
  }
}
</script>
