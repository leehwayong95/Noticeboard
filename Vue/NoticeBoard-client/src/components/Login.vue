<template>
  <div class="login_display">
    <div class="login_contents">
      <img src="../assets/ci_png.png" alt=""/>
      <br/><br/>
      <label for="username"></label>
      <input
          v-model="id"
          type="text"
          class="form-control"
          name="username"
          placeholder="ID"
      />
      <br /><br/>
      <label for="userpassword"></label>
      <input
          v-model="pw"
          type="password"
          class="form-control"
          name="userpassword"
          placeholder="PW"
          v-on:keyup.enter="getlogin"
      />
      <br/><br/>
      <div class = "button_contents">
        <button type="submit" v-on:click ="getlogin">Login</button>
        <button type="button" v-on:click ="link">Signup</button>
      </div>
    </div>
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
        this.$axios.post('http://3.35.254.128/api/info')
          .then((res) => {
            this.$router.push('/board')
          })
      }
    }
  },
  methods: {
    getlogin () {
      this.$axios.post('http://3.35.254.128/api/signin', {id: this.id, pw: this.pw})
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
img {
  width: 75%;
}

.login_display {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100vh;
  background-color: rgb(74, 167, 221);
}

.login_contents{
  width: 50%;
  background-color: rgb(247, 247, 247);
  padding: 45px;
  border-radius: 15px;
  box-shadow: 5px 5px rgba(141, 140, 140, 0.295);
}

input{
  width: 240px;
  height: 50px;
  border-radius: 5px;
  border: 1px solid rgba(141, 140, 140, 0.295)
}

input:first-child {
  margin-left: 10px;
}

.button_contents {
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  width: 240px;
}

button {
  width: 110px;
  height: 40px;
  color: #000;
  cursor: pointer;
  display: block;
  position: relative;
  border-color: rgb(74, 167, 221);
  border-radius: 5px;
  transition: all 0.4s cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
}

button:hover {
  color: #000 !important;
  background-color: rgb(74, 167, 221);
  text-shadow: none;
}

button:hover:before {
  bottom: 0%;
  top: auto;
  height: 100%;
}

button:before {
  display: block;
  position: absolute;
  left: 0px;
  top: 0px;
  height: 0px;
  width: 100%;
  z-index: -1;
  content: '';
  color: #000 !important;
  background: rgb(74, 167, 221);
  transition: all 0.4s cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
}
</style>
