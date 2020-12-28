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
            <td>{{name}}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td class="content"><Viewer v-if = "content != null" :initialValue = "content"/></td>
        </tr>
        <tr v-if = 'file != null'>
            <th>첨부파일</th>
            <td><a href="javascript:;" v-on:click="fileDownload">{{file}}</a></td>
        </tr>
      </table>
    </form>
    <div class="btnWrap">
      <button type='button' v-on:click="list">목록</button>
      <button type='button' v-if='owner' v-on:click="editPost">수정</button>
      <button type='button' v-if='owner' v-on:click="deletePost">삭제</button>
    </div>
  </div>
</template>

<script>
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'
import { Viewer } from '@toast-ui/vue-editor'

export default {
  created () {
    this.getPost()
  },
  updated () {
    this.$axios.post('http://3.35.254.128/api/info')
      .then((res) => {
        this.owner = (res.data.Userid === this.id)
      })// follow the owner permission for update, delete button hidden
  },
  data () {
    return {
      title: '',
      content: null,
      name: '',
      file: null,
      id: null,
      owner: null,
      postindex: this.$route.query.index
    }
  },
  components: {
    Viewer
  },
  methods: {
    getPost () {
      this.$axios.get('http://3.35.254.128/api/board/post?index=' + this.postindex)
        .then((res) => {
          this.title = res.data.title
          this.content = res.data.content
          this.name = res.data.name
          this.id = res.data.id
          if (res.data.filepath !== 'NULL') {
            let path = res.data.filepath.split('/')
            this.file = path[path.length - 1]
          }
        })
    },
    deletePost () {
      var check = confirm('삭제하시겠습니까?')
      if (check) {
        this.$axios.get('http://3.35.254.128/api/board/post/delete?index=' + this.postindex)
          .then((res) => {
            if (res.data.status) {
              alert('삭제되었습니다.')
              this.$router.go(-1)
            } else {
              alert(res.data.reason)
            }
          })
          .catch((err) => {
            alert(err.response.data.reason.toString())
          })
      }
    },
    fileDownload () {
      this.$axios.get('http://3.35.254.128/api/board/post/download?index=' + this.postindex, {responseType: 'blob'})
        .then((res) => {
          const url = window.URL.createObjectURL(new Blob([res.data], { type: res.headers['content-type'] }))
          const link = document.createElement('a')
          link.href = url
          link.setAttribute('download', this.file)
          document.body.appendChild(link)
          link.click()
        })
        .catch((err) => {
          console.log(err)
          alert('download err')
        })
    },
    editPost () {
      this.$router.push('/edit?index=' + this.postindex)
    },
    list () {
      this.$router.go(-1)
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
  transition: all 0.4s cubic-bezier(0.215, 0.61, 0.355, 1) 0s;
  margin: 0px 10px;
}

button:hover {
  color: #000 !important;
  background-color: rgb(74, 167, 221);
  text-shadow: none;
}
</style>
