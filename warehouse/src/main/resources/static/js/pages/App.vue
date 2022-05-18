<template>
<!--  <messages-list :messages="messages" />-->
 <router-view></router-view>

</template>
<script>

// import MessagesList from 'components/messages/MessageList.vue'
// export default {
//   components: {
//     MessagesList
//   },
  // data() {
  //   return {
  //     messages: frontendData.messages,
  //     profile: frontendData.profile
  //   }
  // }
// }

/*
import { mapState, mapMutations } from 'vuex'
  export default {
    computed: mapState(['profile']),
    methods: {
      ...mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
    }
      // showProfile() {
      //   this.$router.push('/profile')
      // }
    // },
    //   created() {
    //     addHandler(data => {
    //       if (data.objectType === 'MESSAGE') {
    //         switch (data.eventType) {
    //           case 'CREATE':
    //             this.addMessageMutation(data.body)
    //             break
    //           case 'UPDATE':
    //             this.updateMessageMutation(data.body)
    //             break
    //           case 'REMOVE':
    //             this.removeMessageMutation(data.body)
    //             break
    //           default:
    //             console.error(`Looks like the event type if unknown "${data.eventType}"`)
    //         }
    //       } else {
    //         console.error(`Looks like the object type if unknown "${data.objectType}"`)
    //       }
    //     })
    //   }
    // data() {
    //   return{
    //   messages: frontendData.messages,
    //   profile: frontendData.profile
    //   }
    // }
  }*/
</script>
<style>

</style>
