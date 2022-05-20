<template>
  <div>
    <input
        type="Add comment"
        placeholder="Write comment"
        v-model="text"
        @keyup.enter="save"
    />
    <input type="button" value="add" @click="save"/>
  </div>
</template>

<script>
import { mapActions } from "vuex"
import comment from "../../api/comment";

export default {
  name: "CommentForm",
  props: ['messageId'],
  data() {
    return {
      text: '',
      id: ''
    }
  },
  methods: {
    ...mapActions(['addCommentAction']),
    async save() {
      await this.addCommentAction({
        text: this.text,
        message: {
          id: this.messageId
        }
      })



    }
  }
}
</script>

<style scoped>

</style>