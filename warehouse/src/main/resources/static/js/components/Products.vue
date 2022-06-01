<template>
<div class="products">
  <div class="column_data_product" v-for="product in products">

    <div class="column_data_product_Name">{{ product.productName }}</div>
    <div class="column_data_product_Amount">{{ product.productAmount }}</div>
    <div class="column_data_product_Price" >{{ product.productPrice }}</div>
    <div class="column_data_product_Price">{{Number(product.productAmount) * Number(product.productPrice)}}</div>

<!--    <input type="button" value="Edit" @click="edit"/>
    <input type="button" value="X" @click="del"/>-->
  </div>
  <div v-if="products !== null">List: {{products.length}}</div>
</div>
</template>

<script>
export default {
  name: "Products",
  props: ['products'],
  data(){
    return{
      sum: '',
    }
  },
  methods:{
    edit(){

    },
    del(){
     /* this.$resource('/product{/id}').remove({id: message.id}).then(result => {
        if (result.ok) {
          this.messages.splice(this.messages.indexOf(message), 1)
        }
      })*/
    }
  }
}
</script>

<style scoped>

.column_data_product {
  display: inline-flex;
  margin-left: 27px;
  /*border: 1px solid black;*/
  margin-top: 2px;
  border-radius: 5px;
}
.column_data_product * {
  border: 1px solid black;
  padding: 5px;
  margin-right: 5px;
}
/*.column_data_product_ID{
  width: 30px;
}*/
.column_data_product_Amount, .column_data_product_Price{
  width: 100px;
}
.column_data_product_Name{
  width: 700px;
}

.products {
  /*border: 1px solid red;*/
  padding: 10px;
  display: flex;
  flex-direction: column;
  max-height: 150px;
  overflow: hidden;
  overflow-y: scroll;
}


.products_column * {
  padding-left: 10px;
}
</style>