<template>
    <div class="sign-up">
        <p>회원가입</p>
        <input type="text" placeholder="ID" v-model="id"><br><br>
        <input type="password" placeholder="Password" v-model="pw"><br><br>
        <input type="text" placeholder="Name" v-model="name"><br><br>
        <label>
          <input type="checkbox" v-model="permission">
          관리자
        </label>
        <button v-on:click = "getsignup">가입하기</button>
    </div>
</template>

<script>
export default {
  data () {
    return {
      id: '',
      pw: '',
      name: '',
      permission: false
    }
  },
  methods: {
    getsignup () {
      if (this.id === '' || this.pw === '' || this.name === '') {
        alert('항목을 모두 입력해주세요.')
      } else {
        let per
        if (this.permission) {
          per = 0
        } else {
          per = 1
        }
        this.$axios.post('http://3.35.254.128/api/signup', {id: this.id, pw: this.pw, name: this.name, permission: per})
          .then((res) => {
            if (res.data === -1) {
              alert('회원가입 실패(아이디 중복입니다.)')
            } else {
              alert('회원가입 완료')
              this.$router.push('/test')
            }
          })
          .then((err) => {
            console.log(err)
          })
      }
    }
  }
}
</script>

<style scoped>
</style>
