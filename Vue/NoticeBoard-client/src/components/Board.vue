<template>
    <div>
        <h2>게시판 리스트</h2>
        <a href="javascript:;" @click="getList">허허</a>
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
    }
  }
}
</script>
