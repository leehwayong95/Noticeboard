<template>
    <div>
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
        <button type="submit" v-on:click ="getList">로그인</button>
        <button type="button" v-on:click ="link">회원가입</button>
    </div>
</template>

<script>
export default {
  methods: {
    getList () {
      this.$axios.get('http://localhost:9000/test')
        .then((res) => {
          alert('안녕')
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
