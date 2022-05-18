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
import { mapActions} from "vuex"

export default {
  name: "CommentForm",
  props: ['messageId'],
  data() {
    return {
      text: ''
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
      this.text = ''

    }
  }
}
</script>

<style scoped>

</style>