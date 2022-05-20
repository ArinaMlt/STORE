<template>
  <div>
    <Header></Header>
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
import Header from '../../pages/Header.vue'
// import Header from "../../pages/Header";

export default {
  components: {
    Header,
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
