<template>
    <div class="login">
        <h3>로그인</h3><br/><br/>
        <label for="username">Username : </label>
        <input
            type="text"
            class="form-control"
            name="username"
        />
        <br /><br/>
        <label for="userpassword">UserPassword : </label>
        <input
            type="password"
            class="form-control"
            name="userpassword"
        />
        <br/><br/>
        <button type="submit" @click="test">로그인</button>
        <button type="button" v-on:click="signup">회원가입</button>
    </div>
</template>

<script>
import axios from 'axios'

export default{
  name: 'login',
  data () {
    return {
      id: null,
      pw: null
    }
  },
  methods: {

    test () {
      axios.get('http://localhost:8081/test')
        .then(res => console.log(res))
        .catch(error => console.log(error))
    },
    signup: function (event) {
      this.$router.push('/signup')
    }
  }
}
</script>

<style scoped>
</style>
