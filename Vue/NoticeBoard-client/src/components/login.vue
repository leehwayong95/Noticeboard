<template>
    <div class = "login_display">
      <div class = "login_contents">
        <img src="../assets/unnamed.gif" alt= ""/>
        <h3>로그인</h3>
        <br/><br/>
        <label for="username"></label>
        <input
            v-model="id"
            type="text"
            class="form-control"
            name="username"
            placeholder="ID"
        />
        <br /><br/>
        <label for="userpassword"></label>
        <input
            v-model="pw"
            type="password"
            class="form-control"
            name="userpassword"
            placeholder="Password"
        />
        <br/><br/>
      <div class = "button_contents">
        <button type="submit" v-on:click ="getlogin">로그인</button>
        <button type="button" v-on:click ="link" v-on:keyup.enter="getlogin">회원가입</button>
      </div>
    </div>
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
            alert('로그인실패')
          } else {
            alert('로그인성공')
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
img {
  width: 75%;
  border-radius: 15px;
}

.login_display {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100vh;
  background-color: rgb(64, 196, 254);
}

.login_contents {
  width: 50%;
  background-color: rgb(247, 247, 247);
  padding: 45px 0;
  border-radius: 15px;
  box-shadow: 5px 5px rgba(141, 140, 140, 0.295);
}

input {
  width: 240px;
  height: 50px;
  border-radius: 5px;
  border: 1px solid rgba(141, 140, 140, 0.295);
}

input:first-child {
  margin-left: 10px;
}

.button_contents {
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  width: 240px;
}

button {
  width: 110px;
  height: 30px;
  border: 1px solid rgba(141, 140, 140, 0.295);
  border-radius: 5px;
}
</style>
