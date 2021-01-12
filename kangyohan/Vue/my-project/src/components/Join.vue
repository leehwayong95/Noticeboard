<template>
<div class="container">
    <div>
        <label for="name">신규ID</label>
        <input type="text" v-model="id" />
    </div>
    <div>
        <label for="password">비밀번호</label>
        <input type="password" v-model="pw" />
    </div>
    <div>
        <label for="usernaem">이름</label>
        <input type="text" v-model="name" />
    </div>
<button type="button" @click="join">회원가입</button>
</div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      id: null,
      pw: null,
      name: null
    }
  },
  methods: {
    join () {
      axios.post('http://localhost:9000/test', {id: this.id, pw: this.pw, name: this.name})
        .then((response) => {
          if (response.data === 1) {
            console.log(response)
            alert('가입 성공')
            this.$router.push('/login')
          } else {
            alert('이미 등록된 유저입니다')
          }
        })
    }
  }
}
</script>
