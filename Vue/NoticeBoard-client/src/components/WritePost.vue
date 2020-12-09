<template>
    <div>
        <h1>게시판 등록</h1>
        <div class="AddWrap">
            <form>
                <table class="tbAdd">
                    <colgroup>
                        <col width="15%" />
                        <col width="*" />
                    </colgroup>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" v-model="title" ref="title" /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea v-model="cont" ref="cont"></textarea></td>
                    </tr>
                </table>
            </form>
        </div>

        <div class="btnWrap">
            <a href="javascript:;" @click="fnList" class="btn">목록</a>
            <a href="javascript:;" @click="fnAddProc" class="btnAdd btn">등록</a>
        </div>
    </div>
</template>

<script>
export default {
  mounted () {
    this.permission()
  },
  data () {
    return {
      title: '',
      cont: '',
      form: ''
    }
  },
  methods: {
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
      this.form = {
        title: this.title,
        content: this.cont
      }
      this.$axios.post('http://localhost:9000/api/write', this.form)
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
</script>

<style scoped>
.tbAdd{border-top:1px solid #888;}
.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0;}
.tbAdd td{padding:10px 10px; box-sizing:border-box;}
.tbAdd td input{width:100%; min-height:30px; box-sizing:border-box; padding:0 10px;}
.tbAdd td textarea{width:100%; min-height:300px; padding:10px; box-sizing:border-box;}
.btnWrap{text-align:center; margin:20px 0 0 0;}
.btnWrap a{margin:0 10px;}
.btnAdd {background:#43b984}
.btnDelete{background:#f00;}
</style>
