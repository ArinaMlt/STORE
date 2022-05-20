<template>
<div>
  {{comment.id}}
  {{comment.text}}
</div>
</template>

<script>
export default {
  name: "CommentItem",
  props: ['comment']
}
</script>

<style scoped>

</style>