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
      pw: ''
    }
  },
  methods: {
    getlogin () {
      this.$axios.get('http://localhost:9000/login/args?id=' + this.id + '&pw=' + this.pw)
        .then((res) => {
          if (res.data === -1) {
            alert('로그인 실패. 아이디와 비밀번호를 확인해주세요.')
          } else {
            this.$router.push('/board')
          }
        })
        .then((err) => {
          console.log(err)
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
