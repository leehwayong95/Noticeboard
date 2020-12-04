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
        <button @click="login()">로그인</button>
        <button type="button">회원가입</button>
         <button @click="test">테스트</button>
    </div>
</template>

<script>
export default {
  data () {
    return {
      id: null,
      pw: null
    }
  }
}
</script>
