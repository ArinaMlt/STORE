<template>
<div>
  Comments:
    <comment-item v-for="(comment, index) in comments"
                  :comment="comment"
                  :key="index">

    </comment-item>

  <comment-form :message-id="messageId"></comment-form>
</div>
</template>

<script>
import CommentItem from "./CommentItem.vue";
import CommentForm from "./CommentForm.vue";
import { mapState } from "vuex";
export default {
  name: "CommentList",
  components: {CommentForm, CommentItem},
  props: ['comments', 'messageId']
}
</script>

<style scoped>

</style>