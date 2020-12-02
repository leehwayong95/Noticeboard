<template>
    <div>
        <b-table
            striped
        hover
        :items="items"
        :per-page="perPage"
        :current-page="currentPage"
        :fields="fields"
        @row-clicked="rowClick"
        ></b-table>
    </div>
</template>

<script>
export default {
}
</script>

<style scoped>

</style>
