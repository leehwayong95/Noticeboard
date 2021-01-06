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
        <tr v-if='filepath !== null'>
            <th>첨부파일</th>
            <td>
              <img v-if="file" :src="file" style="height:80px;width:80px;"/>
              <a href="javascript:;" v-on:click="fileDownload">{{filepath}}</a>
            </td>
        </tr>
      </table>
    </form>
    <div class="btnWrap">
      <button type='button' v-on:click="list">목록</button>
      <button type='button' v-if='viewer_id === id' v-on:click="editPost">수정</button>
      <button type='button' v-if='viewer_id === id' v-on:click="deletePost">삭제</button>
    </div>
    <div>
      <form>
        <table class="commentList">
          <tr>
            <th>번호</th>
            <th>댓글</th>
            <th>작성자</th>
            <th>작성시간</th>
          </tr>
          <tr v-for="(comment, list) in comment_list" :key="list">
            <td>{{list+1}}</td>
            <td>{{comment.content}}</td>
            <td>{{comment.name}}</td>
            <td>{{comment.date}}</td>
          </tr>
        </table>
      </form>
      <div class ="input_comment">
        <input class="comment_inputarea" type="text" placeholder="댓글 입력" v-model="input_comment"/>
        <button type="button" v-on:click="addComment">댓글달기</button>
      </div>
    </div>
  </div>
</template>

<script>
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'
import { Viewer } from '@toast-ui/vue-editor'

export default {
  created () {
    this.$axios.post('http://3.35.254.128/api/info')
      .then((res) => {
        this.viewer_id = res.data.Userid
      })// follow the owner permission for update, delete button hidden
    this.getPost()
    this.getComment()
  },

  data () {
    return {
      title: '',
      content: null,
      name: '',
      file: null,
      filepath: null,
      id: null,
      viewer_id: null,
      postindex: this.$route.query.index,
      comment_list: null,
      input_comment: null
    }
  },
  components: {
    Viewer
  },
  methods: {
    previewImage () {
      this.$axios.get('http://3.35.254.128/api/board/post/download?index=' + this.$route.query.index, {responseType: 'blob'})
        .then((res) => {
          if (res.data.type.split('/')[0] === 'image') {
            this.file = URL.createObjectURL(res.data)
          } else {
            this.file = null
          }
        })
        .catch((err) => {
          console.log(err)
        })
    },
    getPost () {
      this.$axios.get('http://3.35.254.128/api/board/post?index=' + this.postindex)
        .then((res) => {
          this.title = res.data.title
          this.content = res.data.content
          this.name = res.data.name
          this.id = res.data.id
          if (res.data.filepath !== 'NULL') {
            this.filepath = res.data.filepath.split('/')[res.data.filepath.split('/').length - 1]
            this.previewImage()
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
          link.setAttribute('download', this.filepath)
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
    },
    getComment () {
      this.$axios.get('http://3.35.254.128/api/loadcomment?index=' + this.postindex)
        .then((res) => {
          this.comment_list = res.data
        })
    },
    addComment () {
      if (this.input_comment == null) {
        alert('댓글을 입력해주세요')
      } else {
        this.$axios.post('http://3.35.254.128/api/addcomment', {content: this.input_comment, postindex: this.postindex})
          .then((res) => {
            if (res.data.status) {
              alert('등록되었습니다.')
              this.getComment()
              this.input_comment = null
            } else {
              alert('서버오류')
            }
          })
          .catch((err) => {
            alert('잘못된 접근입니다.')
            console.log(err)
          })
      }
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

.input_comment {
  display: flex;
  margin: 20px;
  align-content: center;
}

.comment_inputarea {
  width: 600px;
}
</style>
