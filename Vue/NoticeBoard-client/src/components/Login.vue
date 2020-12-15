<template>
    <div class="login">
        <h3>로그인</h3><br/><br/>
        <label for="username">Username : </label>
        <input
            type="text"
            v-model="id"
            class="form-control"
            name="username"
            width:200px
        />
        <br /><br/>
        <label for="userpassword">UserPassword : </label>
        <input
            type="password"
            v-model="pw"
            class="form-control"
            name="userpassword"
            width:200px
        />
        <br/><br/>
        <button type="submit" v-on:click="login">로그인</button>
        <button type="button" v-on:click="signup">회원가입</button>
    </div>
</template>

<script>
import axios from 'axios'

export default{
  name: 'login',
  data () {
    return {
      id: '',
      pw: ''
    }
  },
  methods: {
    login () {
      axios.post('http://localhost:8081/logincheck', {id: this.id, pw: this.pw})
        .then(response => {
          if (response.data === 'TRUE') {
            alert('로그인에 성공했습니다')
            this.$router.push('/boardlist')
          } else {
            alert('ID와 PW를 잘못 입력했습니다. 다시 입력해주세요')
          }
        }).catch((ex) => {
          console.warn('ERROR:', ex)
        })
    },
    signup: function (event) {
      this.$router.push('/signup')
    }
  }
}
</script>

<style scoped>
  .form-control{
    width: 200px;
    margin: 0 auto;
  }
</style>
