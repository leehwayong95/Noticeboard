<template>
  <div class='addWrap'>
    <h1>상세 보기</h1>
    <form>
      <table class='tbList'>
        <colgroup>
          <col width="15%"/>
          <col width="*"/>
        </colgroup>
        <tr>
            <th scope="cols">제목</th>
            <td>{{title}}</td>
        </tr>
        <tr>
            <th scope="cols">작성자</th>
            <td>{{id}}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td class="content">{{content}}</td>
        </tr>
      </table>
    </form>
    <div class="btnWrap">
      <button type='button' v-on:click="list"> 목록</button>
    </div>
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
.tbList th, .tbList td{border-bottom:1px solid #eee; padding:5px 0;}
.tbList td input{width:100%; min-height:30px; box-sizing:border-box; padding:0 10px;}
.tbList td{padding:10px 10px; box-sizing:border-box;}
.content {
  /* display: block;
  text-align: left; */ /* --> content align top-left option*/
  width:100%;
  height:300px;
  padding:10px;
  box-sizing:border-box;
}
.btnWrap{
  display: flex;
  justify-content: center;
  margin: 30px
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
}
</style>
