<template>
    <div class="signup_display">
      <div class="signup_contents">
        <img src="../assets/ci_png.png" alt=""/>
        <br/><br/>
        <input
          type="text"
          placeholder="ID"
          class="form-control"
          v-model="id"
        />
        <br><br>
        <input
          type="password"
          placeholder="PW"
          class="form-control"
          v-model="pw"
        />
        <br><br>
        <input
          type="text"
          placeholder="Name"
          class="form-control"
          v-model="name"
          v-on:keyup.enter="getsignup"
        />
        <br><br>
        <div class="button_contents">
          <label>
            <input type="checkbox" v-model="permission">
            관리자
          </label>
          <button v-on:click = "getsignup">가입하기</button>
        </div>
    </div>
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
        let per = (this.permission) ? 0 : 1
        this.$axios.post('http://3.35.254.128/api/signup', {id: this.id, pw: this.pw, name: this.name, permission: per})
          .then((res) => {
            if (res.data === -1) {
              alert('회원가입 실패(아이디 중복입니다.)')
            } else {
              alert('회원가입 완료')
              this.$router.push('/')
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
img {
  width: 75%;
  border-radius: 15px;
}

.signup_display {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100vh;
  background-color: rgb(74, 167, 221);
}

.signup_contents{
  width: 50%;
  background-color: rgb(247, 247, 247);
  padding: 45px;
  border-radius: 15px;
  box-shadow: 5px 5px rgba(141, 140, 140, 0.295);
}

.form-control{
  width: 240px;
  height: 50px;
  border-radius: 5px;
  border: 1px solid rgba(141, 140, 140, 0.295)
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
  height: 40px;
  color: #000;
  cursor: pointer;
  display: block;
  position: relative;
  border-color: rgb(74, 167, 221);
  border-radius: 5px;
  transition: all 0.4s cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
}

button:hover {
  color: #000 !important;
  background-color: rgb(74, 167, 221);
  text-shadow: none;
}

button:hover:before {
  bottom: 0%;
  top: auto;
  height: 100%;
}

button:before {
  display: block;
  position: absolute;
  left: 0px;
  top: 0px;
  height: 0px;
  width: 100%;
  z-index: -1;
  content: '';
  color: #000 !important;
  background: rgb(74, 167, 221);
  transition: all 0.4s cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
}
</style>
