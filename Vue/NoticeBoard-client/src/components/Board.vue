<template>
    <div>
        <h2>게시판 리스트</h2>
        <a href="javascript:;" @click="getList">허허</a>
        <button type="submit" v-on:click ="logout">로그아웃</button>
    </div>
</template>

<script>
export default {
  methods: {
    getList () {
      this.$axios.get('http://localhost:9000/api/board')
        .then((res) => {
          console.log(res)
        })
        .then((err) => {
          console.log(err)
        })
    },
    logout () {
      document.cookie = 'jwt-auth-token =; expires = Web; 01 Jan 1970'
      this.$router.push('/test')
    }
  }
}
</script>
