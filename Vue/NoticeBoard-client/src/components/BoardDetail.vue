<template>
  <div>
    <b-card>
      <div class="content-detail-content-info">
        <div class="content-detail-content-info-left">
          <div class="content-detail-content-info-left-number">1</div>
          <div class="content-detail-content-info-left-subject">시작이다</div>
        </div>
        <div class="content-detail-content-info-right">
            <div class="content-detail-content-info-right-user">글쓴이 : 나</div>
            <div class="content-detail-content-info-right-created">등록일 : 어제</div>
        </div>
      </div>
      <div class="content-detail-content">글내용여기에 들어간다.</div>
      <div class="content-detail-button">
          <b-button variant="primary"> 수정 </b-button>&nbsp;
          <b-button variant="success"> 삭제</b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
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
