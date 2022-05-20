<template>
  <div>
    <div>
    <i>({{ message.id }})</i>
    {{ message.text }}
<!--    {{ message.comments }}-->
    <span style="position: absolute; right: 0">
        <input type="button" value="Edit" @click="edit"/>
        <input type="button" value="X" @click="del"/>
        </span>
    </div>
    <div>
      <comment-list
      :comments="message.comments"
      :message-id="message.id"
      ></comment-list>
    </div>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
import CommentList from "../comment/CommentList.vue"
export default {
  components: { CommentList },
  props: ['message', 'editMessage'],
  methods: {
    ...mapActions(['removeMessageAction']),
    edit() {
      this.editMessage(this.message)
    },
    del() {
      this.removeMessageAction(this.message)
    }
  }
}
</script>
<style>

</style>