<template>
<div>
  <div id="lineProduct" v-for="message in messages">
    {{ message.id }}
    {{ message.name }}
    {{ message.amount }}
     {{ message.price }}
  </div>
  </div>
</template>

<script>

export default {
  name: "TestList",
  props: ['messages'],
  created() {
    this.$resource('/product{/id}').get().then(result =>
        // console.log(result)
        result.json().then(data =>
            // console.log(data)
            data.forEach(message => this.messages.push(message))
        )
    )
  }

}
</script>

<style scoped>
#lineProduct{
  border: 1px solid #5b5757;
}
</style>