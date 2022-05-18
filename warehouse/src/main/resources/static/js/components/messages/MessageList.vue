<template>
  <div style="position: relative; width: 300px;">
    <div>
      <messages-form :messageAttr="message"/>
      <message-row v-for="message in messages"
                   :key="message.id"
                   :message="message"
                   :editMessage="editMessage"/>
    </div>

  </div>
</template>
<script>
import { mapState } from "vuex"
import MessageRow from 'components/messages/MessageRow.vue'
import MessagesForm from 'components/messages/MessagesForm.vue'

export default {
  components: {
    MessagesForm,
    MessageRow,
  },
  data() {
    return {
      message: null
    }
  },
  computed: mapState(['messages']),
  methods: {
    editMessage(message) {
      this.message = message
    }
  }
}

</script>
<style>

</style>
