<template>
  <div class="column_data">
    <div class="column_data_Price">

      <div class="id">{{ message.id }}</div>
      <div class="name">{{ message.contragent.name}}</div>
      <div class="amount" >{{ message.totalAmount }}</div>
      <div class="price">{{ message.totalPrice }}</div>
      <div class="data">{{ message.date }}</div>
      <input type="button" value="Edit" @click="edit"/>
      <input type="button" value="X" @click="del"/>
      <!--            <div class="data">date</div>-->
    </div>

<!--    <div class="column_data_product" v-for="product in products">-->
<!--      <div class="column_data_product_ID">{{ product.id }}</div>-->
<!--      <div class="column_data_product_TEXT">{{ product.text }}</div>-->
<!--    </div>-->

  </div>
</template>

<script>
import messages from "../../api/messages";

export default {
  name: "InvoiceRow",
  props: ['message', 'editMethod', 'delMethod'],
  data(){
    return{

    }
},
  methods: {
    edit(){
      this.editMethod(this.message);
    },
    del(){
      this.delMethod(this.message);
      console.log(this.message.id);
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
  background: #a4b3dc;
  display: inline-flex;
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
  width: 700px;
}
.id{
  width: 20px;
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

.column_data_Price {
  background: #a4b3dc;
  display: inline-flex;
}
.column_data_Price * {
  /*border: 1px solid black;*/
  padding: 5px;
  margin-right: 5px;
}

</style>