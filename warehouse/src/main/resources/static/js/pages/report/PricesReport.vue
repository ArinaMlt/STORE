<template>
<div >
  <Header></Header>

  <div class="productRepo_main">
  <input type="text" v-model="productName" placeholder="Наименование"/>
  <ul>
    <li v-for="message in filteredList">
      <p> {{ message.productName }} - {{message.productAmount}} - {{message.productPrice}}</p>
    </li>
  </ul>
</div>

</div>
</template>

<script>
import Header from "../Header.vue";
export default {
  name: "PricesReport",
  components: {Header},
  data(){
    return{
      productName: '',
      messages: [
        {id: '1', name: 'one', inn: 'innOne', address: 'addressOne'},
        {id: '2', name: 'two', inn: 'innTwo', address: 'addressTwo'},
        {id: '3', name: 'three', inn: 'innThree', address: 'addressThree'}
      ],
      products: [],
    }
  },
  created() {
    this.$resource('/product{/id}').get().then(result =>
        result.json().then(data =>{
          data.forEach(product => this.products.push(product))
        }
        )
    )
  },
  computed: {
    filteredList() {
      var comp = this.productName;

      return this.products.filter(function (elem) {

        if (comp === '') return true;
        else return elem.productName.indexOf(comp) > -1;
      })

    },
  }
}
</script>

<style scoped>
.productRepo_main {
  padding: 5px;
  margin-top: 5px;
  border: 1px solid black;
}
</style>