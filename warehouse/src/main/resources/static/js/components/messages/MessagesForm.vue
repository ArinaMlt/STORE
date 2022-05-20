<template>
  <div>
    <input
        type="text"
        placeholder="Write something"
        v-model="text"
        @keyup.enter="save"
    />
    <input type="button" value="Save" @click="save"/>
  </div>
</template>
<script>
// import messagesApi from "api/messages";
import { mapActions } from 'vuex'

export default {
  props: ['messageAttr'],
  data() {
    return {
      text: '',
      id: ''
    }
  },
  watch: {
    messageAttr(newVal, oldVal) {
      this.text = newVal.text
      this.id = newVal.id
    }
  },
  methods: {
    ...mapActions(['updateMessageAction', 'addMessageAction']),

    save() {
      const message = {
        id: this.id,
        text: this.text
      }
      if (this.id) {
        this.updateMessageAction(message)
      } else {
        this.addMessageAction(message)
      }
      this.text = '',
          this.id = ''
    }
    /*save() {
      const message = {
        id: this.id,
        text: this.text }
      if (this.id) {
        messagesApi.update(message).then(result =>
            result.json().then(data => {
              const index = this.messages.findIndex(item => item.id === data.id)
              this.messages.splice(index, 1, data)
            })
        )
      } else {
        messagesApi.add( message).then(result =>
            result.json().then(data => {
              this.messages.push(data)
              this.text = ''
            })
        )
      }
    }*/
  }
}

</script>
<style>

</style>