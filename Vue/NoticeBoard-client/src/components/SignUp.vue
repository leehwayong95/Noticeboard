<template>
    <div class="sign-up">
        <p>회원가입</p>
        <input type="text" placeholder="ID" v-model="id"><br><br>
        <input type="password" placeholder="Password" v-model="pw"><br><br>
        <input type="text" placeholder="Name" v-model="name"><br><br>
        <button v-on:click = "getsignup">가입하기</button>
    </div>
</template>

<script>
export default {
  data () {
    return {
      id: '',
      pw: '',
      name: ''
    }
  },
  methods: {
    getsignup () {
      this.$axios.get('http://localhost:9000/signup/args?id=' + this.id + '&pw=' + this.pw + '&name=' + this.name)
        .then((res) => {
          if (res.data === -1) {
            alert('회원가입 실패(아이디 중복입니다.)')
          } else {
            alert('회원가입 완료')
            this.$router.push('/login')
          }
        })
        .then((err) => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
</style>
