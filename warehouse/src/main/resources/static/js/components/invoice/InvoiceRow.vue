<template>
  <div class="column_data">
    <div class="column_data_Price" @click="edit">

      <div class="id">{{ message.id }}</div>
      <div class="name">{{ message.contragent.name}}</div>

<!--      <div class="amount" >{{ amount() }}</div>-->
      <div class="amount" >{{ message.totalAmount }}</div>
      <div class="price">{{ message.totalPrice }}</div>
      <div class="amount" >{{ message.totalValue }}</div>

      <!--      <div class="price">{{ price() }}</div>-->

      <div class="data">{{ message.date }}</div>

<!--      <input class="input_buttn_save" type="button" value="Edit" @click="edit"/>-->
<!--      <input class="input_buttn_exit" type="button" value="x" @click="del"/>-->
      <!--            <div class="data">date</div>-->
    </div>


<!--    <div class="column_data_product" v-for="product in message.products">
      <div class="column_data_product_ID">{{ product.name }}</div>
      <div class="column_data_product_TEXT">{{ product.productAmount }}</div>
      <div class="column_data_product_TEXT">{{ product.productPrice }}</div>
    </div>-->

<!--    <Products :products="message.products"></Products>-->

    <div >

    </div>

<!--    <div class="product">{{ message.products}}</div>-->

  </div>
</template>

<script>
import messages from "../../api/messages";
import Products from "../Products.vue";

export default {
  name: "InvoiceRow",
  components: {Products},
  props: ['message', 'editMethod'],
  data(){
    return{
      // amount: 0
      products: this.message.products
    }
},
  created() {
  /*  amount: {
      return 5
    }*/

/*    console.log(this.message.products.length)
    console.log(this.message.products[1].productName)
    */
    /*for(let i =0; i < this.message.products.length; i++){
      count++
    }
    this.amount =count*/

  },
  methods: {
    edit(){
      this.editMethod(this.message);
    },
    amount(){
      // var products = this.message.products
      var amount = 0

      console.log(this.products)
      // console.log(products.length)

      if(this.products !== null){
        for (let i = 0; i < this.products.length; i++){
          amount = amount + Number(this.products[i].productAmount)
          // console.log(amount)
          this.message.totalAmount = String(amount)
        }
      }
      return amount
    },
    price(){
      var price =0

      if(this.products !== null){
        for (let i = 0; i < this.products.length; i++){
          price = price + Number(this.products[i].productPrice)
          this.message.totalPrice = String(price)
        }
      }
      return price
    }
  }
}
</script>

<style scoped>
.column_data {
  display: flex;
  flex-direction: column;
  /*border: 1px solid black;*/
}

.column_data_Price {
  /*background: rgba(189, 200, 232, 0.37);*/
  background: white;
  border-bottom: 1px solid rgba(133, 133, 133, 0.53);
  display: inline-flex;
}
.column_data_Price:hover{
  /*background: rgba(164, 179, 220, 0.82);*/
  background: rgba(189, 200, 232, 0.37);

}
.column_data_product {
  display: inline-flex;
  margin-left: 35px;
  border: 1px solid black;
  margin-top: 2px;
  border-radius: 5px;
}
.column_data_product_ID{
  width: 30px;
}

.name {
  width: 500px;
}
.id{
  width: 30px;
}
.amount, .price{
  width: 110px;
}
.data{
  width: 120px;
}

.column_data {
  display: flex;
  flex-direction: column;
  /*border: 1px solid black;*/
}

/*.column_data_Price {
  background: #a4b3dc;
  display: inline-flex;
}*/
.column_data_Price * {
  /*border: 1px solid black;*/
  padding: 5px;
  /*margin-right: 5px;*/
}
.input_buttn_exit{
  /*background-color: rgba(255, 0, 0, 0.4);*/
  border: none;
  border-radius: 5px;
  margin-left: 5px;
  height: 25px;
  /*padding: 5px;*/
}
.input_buttn_exit:hover {
  background-color: rgba(255, 0, 0, 0.47);
}
.input_buttn_save{
  /*background: rgba(21, 148, 21, 0.29);*/
  border: none;
  border-radius: 5px;
  margin-left: 5px;
  padding: 5px;
}

</style>