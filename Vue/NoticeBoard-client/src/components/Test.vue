<template>
    <div>
        <h3>로그인</h3><br/><br/>
        <label for="username">Username : </label>
        <input
            v-model="id"
            type="text"
            class="form-control"
            name="username"
        />
        <br /><br/>
        <label for="userpassword">UserPassword : </label>
        <input
            v-model="pw"
            type="password"
            class="form-control"
            name="userpassword"
            v-on:keyup.enter="getlogin"
        />
        <br/><br/>
        <button type="submit" v-on:click ="getlogin">로그인</button>
        <button type="button" v-on:click ="link">회원가입</button>
    </div>
</template>

<script>
export default {
  data () {
    return {
      id: '',
      pw: '',
      token: '',
      permission: ''
    }
  },
  created () {
    let cookieArr = document.cookie.split(';')
    for (let i in cookieArr) {
      if (cookieArr[i].split('=')[0].trim() === 'jwt-auth-token') {
        this.$axios.defaults.headers.common['jwt-auth-token'] = cookieArr[i].split('=')[1].trim()
        this.$axios.post('http://localhost:9000/api/info')
          .then((res) => {
            this.$router.push('/board')
          })
      }
    }
  },
  methods: {
    getlogin () {
      this.$axios.post('http://localhost:9000/api/signin', {id: this.id, pw: this.pw})
        .then((res) => {
          document.cookie = 'jwt-auth-token = ' + res.data.token
          document.cookie = 'permission = ' + res.data.permission
          this.$axios.defaults.headers.common['jwt-auth-token'] = res.data.token
          this.$router.push('/board')
        })
        .catch(function (error) {
          if (error.response) {
            alert('아이디와 비밀번호를 확인해주세요.')
          }
        })
    },
    link: function (event) {
      this.$router.push('/signup')
    }
  }
}
</script>

<style scoped>
</style>
