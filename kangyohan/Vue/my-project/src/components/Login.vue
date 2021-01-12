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
            v-on:keyup.enter="Login"
        />
        <br/><br/>
        <button type="String" @click="Login">로그인</button>
        <button type="button" @click="Join">회원가입</button>
    </div>
</template>
<script>
import axios from 'axios'

export default {
  mounted () {
    // const cookie = this.$cookie.get('test')
    // axios.post('http://localhost:9000/logincheck', {result: cookie})
    //   .then(res => {
    //     if (res.data.result === 'empty') {
    //       alert('로그인 아직안함,혹은 만료')
    //       console.log(res)
    //     } else {
    //       this.token = res.data.result
    //       console.log('아직 그대로임: ' + this.token)
    //       this.$router.push('/board')
    //     }
    //   })
  // const: 한번 선언한 값에 대해서 변경할수 없다.
    const cookie = this.$cookie.get('test')
    let token = cookie
    console.log(token)
    let config = {
      headers: {
        'Authorization': token
      }
    }
    axios.get('http://localhost:9000/headlogincheck', config)
      .then(res => {
        if (res.data.result === 'empty') {
          console.log('쿠키 없음: ' + this.token)
        } else {
          this.token = res.data.result
          console.log('쿠키 건재함: ' + this.token)
          this.$router.push('/board')
        }
      })
  },
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
            this.$cookie.set('test', this.token, 1)
            // const cookie = this.$cookie.get('test')
            // alert(cookie)
            this.$router.push('/board')
          }
        })
    }
  }
}
</script>
