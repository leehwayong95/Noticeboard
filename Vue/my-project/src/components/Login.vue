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
        <button type="String" @click="tt">로그인</button>
        <button type="button" @click="login">회원가입</button>
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
    login () {
      axios.get('http://localhost:9000/test')
        .then(res => console.log(res))
        .catch(error => console.log(error))
    },
    tt () {
      axios.post('http://localhost:9000/login2', {id: this.id, pw: this.pw})
        .then(res => console.log(res))
        .catch(error => console.log(error))
    }
  }
}
</script>
