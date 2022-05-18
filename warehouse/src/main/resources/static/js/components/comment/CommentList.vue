<template>
<div>
  Comments:
  <comment-item v-for="comment in comments"
                :comment="comment"
                :key="comment.id">

  </comment-item>
  <comment-form :message-id="messageId"></comment-form>
</div>
</template>

<script>
import CommentItem from "./CommentItem.vue";
import CommentForm from "./CommentForm.vue";
export default {
  name: "CommentList",
  components: {CommentForm, CommentItem},
  props: ['comments', 'messageId']
}
</script>

<style scoped>

</style>