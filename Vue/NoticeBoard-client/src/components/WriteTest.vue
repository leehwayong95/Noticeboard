<template>
  <div>
    <Editor ref='toastuiEditor'/>
    <b-button @click="alert">확인하기</b-button>
  </div>
</template>

<script>
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'
import { Editor } from '@toast-ui/vue-editor'

export default {
  components: {
    Editor
  },
  methods: {
    alert () {
      var content = this.$refs.toastuiEditor.invoke('getMarkdown')
      alert(content)
    }
  }
}
</script>
