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
        <button type="submit" @click="getList">로그인</button>
        <button type="button">회원가입</button>
    </div>
</template>

<script>
export default {
  methods: {
    getList () {
      this.$axios.get('http://localhost:9000/test')
        .then((res) => {
          console.log(res.data)
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
