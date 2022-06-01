<template>
  <div class="products">

    <div class="column_data_product">

      <div class="column_data_product_Name">{{ product.productName }}</div>
      <div class="column_data_product_Amount">{{ product.productAmount }}</div>
      <div class="column_data_product_Price" >{{ product.productPrice }}</div>
      <div class="column_data_product_Price">{{product.productValue}}</div>

      <input  v-bind:class="{active: productSave}"  type="button" value="Edit" @click="editProduct"/>
      <input type="button" value="X" @click="delProduct"/>

    </div>
  </div>
</template>

<script>
// import Vue from 'vue'
// var productApi = Vue.resource('/product{/id}')

export default {
  name: "Product",
  props: ['product', 'editMethodProduct', 'delMethodProduct'],
  data(){
    return{
      productSave: '',
    }
  },
  created() {
    if(this.product.id) {
      this.productSave= false
    } else {
      this.productSave= true
    }
  },
  methods:{
    editProduct(){
      this.editMethodProduct(this.product);
      if(!this.product.id){
        this.delProduct()
      }
    },
    delProduct(){
      this.delMethodProduct(this.product);
    }
  }
}
</script>

<style scoped>
.active{
  background: rgba(255, 0, 0, 0.52);
}
.column_data_product {
  display: inline-flex;
  /*margin-left: 2px;*/
  /*border: 1px solid black;*/
  /*margin-top: 2px;*/
  /*border-radius: 5px;*/
}
.column_data_product:hover{
  background: rgba(220, 220, 220, 0.34);
}
.column_data_product * {
  border: 1px solid #bebebe;
  padding: 2px;
  margin-left: 2px;
  margin-bottom: 0px;
  height: 20px;
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
  /*padding: 10px;*/
  display: flex;
  flex-direction: column;
  /*max-height: 150px;*/
  /*overflow: hidden;*/
  /*overflow-y: scroll;*/
}


/*.products_column * {*/
/*  padding-left: 10px;*/
/*}*/
</style>