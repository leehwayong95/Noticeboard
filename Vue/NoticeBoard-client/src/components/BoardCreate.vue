<template>
    <div>
        <b-input v-model="subject" placeholder="제목을 입력해주세요."></b-input>
        <b-form-textarea
            v-model="context"
            placeholder="내용을 입력해주세요"
            rows="5"
            max-rows="20"
            ></b-form-textarea>
            <br><br>
            <b-button>저장</b-button>&nbsp;
            <b-button>취소</b-button>
    </div>
</template>

<script>
export default {
}
</script>

<style scoped>

</style>
