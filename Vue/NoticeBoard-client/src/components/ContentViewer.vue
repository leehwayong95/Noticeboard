<template>
    <div class='addWrap'>
        <h1>상세 보기</h1>
        <form>
            <table class='tdList'>
                <tr>
                    <th scope="cols">제목</th>
                    <td>{{title}}</td>
                </tr>
                <tr>
                    <th scope="cols">내용</th>
                    <td>{{content}}</td>
                </tr>
                <tr>
                    <th scope="cols">작성자</th>
                    <td>{{id}}</td>
                </tr>
            </table>
        </form>
        <button type='button' v-on:click="list"> 목록</button>
    </div>
</template>

<script>
export default {
  mounted () {
    this.getPost()
  },
  data () {
    return {
      title: '',
      content: '',
      id: '',
      postindex: this.$route.query.index
    }
  },
  methods: {
    getPost () {
      this.$axios.get('http://3.35.254.128/api/board/post?index=' + this.postindex)
        .then((res) => {
          this.title = res.data.title
          this.content = res.data.content
          this.id = res.data.name
        })
    },
    list () {
      this.$router.push('/board')
    }
  }
}
</script>

<style scoped>

</style>
