<template>
  <div>
    <b-card>
      <div class="content-detail-content-info">
        <div class="content-detail-content-info-left">
          <div class="content-detail-content-info-left-number">{{list.postindex}}</div>
          <div class="content-detail-content-info-left-subject">{{list.title}}</div>
        </div>
        <div class="content-detail-content-info-right">
            <div class="content-detail-content-info-right-user">{{list.writer}}</div>
            <div class="content-detail-content-info-right-created">{{list.date}}</div>
        </div>
      </div>
      <div class="content-detail-content">{{list.content}}</div>
      <div class="content-detail-button">
          <b-button> 목록 </b-button>
          <b-button variant="primary"> 수정 </b-button>&nbsp;
          <b-button variant="success"> 삭제</b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      index: '',
      list: []
    }
  },
  mounted () {
    this.index = this.$route.params.index
    axios.post('http://localhost:8081/boarddetail', {postindex: this.index})
      .then(response => {
        this.list = response.data
      }).catch((ex) => {
        console.warn('ERROR:', ex)
      })
  }
}
</script>

<style scoped>
.content-detail-content-info {
  border: 1px solid black;
  display: flex;
  justify-content: space-between;
}

.content-detail-content-info-left {
  width: 720px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}

.content-detail-content-info-left-subject{
  text-align: center;
}
.content-detail-content-info-right {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.content-detail-content {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 720px;
}

.content-detail-button {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}
</style>
