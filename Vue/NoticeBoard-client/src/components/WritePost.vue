<template>
    <div>
        <h1>게시글 등록</h1>
        <div class="AddWrap">
            <form>
                <table class="tbAdd">
                    <colgroup>
                        <col width="15%" />
                        <col width="*" />
                    </colgroup>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" v-model="title" ref="title" maxlength="30"/></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>
                          <Editor
                            ref='content'
                            initialEditType='wysiwyg'
                          />
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div class="btnWrap">
            <a href="javascript:;" @click="fnList" class="btn">목록</a>
            <a href="javascript:;" v-if='!edit' @click="fnAddProc" class="btnAdd btn">등록</a>
            <a href="javascript:;" v-else @click="fnAddProc" class="btnAdd btn">수정</a>
        </div>
    </div>
</template>

<script>
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'
import { Editor } from '@toast-ui/vue-editor'

export default {
  created () {
    this.permission()
    if (this.edit) {
      this.getPost()
    }
  },
  updated () {
    this.$refs.content.invoke('setMarkdown', this.cont)
  },
  data () {
    return {
      title: '',
      cont: '',
      form: '',
      edit: this.$route.query.index != null,
      postindex: this.$route.query.index
    }
  },
  components: {
    Editor
  },
  methods: {
    getPost () {
      this.$axios.get('http://3.35.254.128/api/board/post?index=' + this.postindex)
        .then((res) => {
          this.title = res.data.title
          this.cont = res.data.content
          this.name = res.data.name
          this.id = res.data.id
        })
    },
    permission () {
      let cookie = document.cookie.split(';')
      for (let i in cookie) {
        if (cookie[i].split('=')[0].trim() === 'permission' && cookie[i].split('=')[1].trim() === '1') {
          alert('권한이 없습니다.')
          this.$router.back()
        }
      }
    },
    fnList () {
      this.$router.push('./board')
    },
    fnAddProc () {
      if (!this.title) {
        alert('제목을 입력해 주세요')
        this.$refs.title.focus()
        return
      }
      this.cont = this.$refs.content.invoke('getMarkdown')
      this.form = {
        title: this.title,
        content: this.cont,
        postindex: this.postindex
      }
      if (this.edit) {
        this.$axios.post('http://3.35.254.128/api/edit', this.form)
          .then((res) => {
            if (res.data.status) {
              alert('수정되었습니다.')
              this.$router.push({
                path: 'board/view',
                query: {index: this.postindex}
              })
            }
          })
          .catch((err) => {
            alert('실행중 실패했습니다.\n다시이용해주세요')
            console.log(err)
          })
      } else {
        this.$axios.post('http://3.35.254.128/api/write', this.form)
          .then((res) => {
            if (res.data.status) {
              alert('등록되었습니다.')
              this.fnList()
            } else {
              alert('실행중 실패했습니다.\n다시 이용해 주세요')
            }
          })
          .catch((err) => {
            alert('실행중 실패했습니다.\n다시 이용해 주세요')
            console.log(err)
          })
      }
    }
  }
}
</script>

<style scoped>
.tbAdd{border-top:1px solid #888;}
.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0;}
.tbAdd td{padding:10px 10px; box-sizing:border-box;}
.tbAdd td input{width:100%; min-height:30px; box-sizing:border-box; padding:0 10px;}
.tbAdd td textarea{width:100%; min-height:300px; padding:10px; box-sizing:border-box;}
.btnWrap{text-align:center; margin:20px 0 0 0;}
.btnWrap a{margin:0 10px;}
.btnAdd {background:rgb(74, 167, 221)}
</style>
