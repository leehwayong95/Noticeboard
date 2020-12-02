<template>
    <div class="sign-up">
        <p>회원가입</p>
        <input type="text" placeholder="ID" v-model="id"><br><br>
        <input type="password" placeholder="Password" v-model="pw"><br><br>
        <button v-on:click = "signup_com">가입하기</button>
    </div>
</template>

<script>
export default {
  methods: {
    getsignup () {
      this.$axios.get('http://localhost:9000/signup/args?id=' + this.id + '&pw=' + this.pw)
        .then((res) => {
          if (res.data === -1) {
            alert('회원가입 실패(아이디 중복입니다.)')
          } else {
            alert('회원가입 성공')
            this.$$router.push('/login')
          }
        })
        .then((err) => {
          console.log(err)
        })
    },
    signup_com () {
      this.$router.push('/test')
    }
  }
}
</script>

<style scoped>
</style>
