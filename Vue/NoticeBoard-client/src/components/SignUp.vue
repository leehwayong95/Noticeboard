<template>
    <div class="sign-up">
        <p>회원가입</p>
        <input type="text" placeholder="text"><br><br>
        <input type="password" placeholder="password"><br><br>
        <button v-on:click = "signup_com">가입하기</button>
    </div>
</template>

<script>
export default {
  methods: {
    signup_com () {
      this.$router.push('/test')
    }
  }
}
</script>

<style scoped>
</style>
