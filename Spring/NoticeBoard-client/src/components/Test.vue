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
        <button type="submit">로그인</button>
        <button type="button" v-on:click="signup">회원가입</button>
    </div>
</template>

<script>
  export default{
    name: 'signup',
    data () {
      return {
      }
    },
    metohds: {
      signup() {
        this.$router.replace('signup')
      }
    }
  }
</script>

<style scoped>
</style>
