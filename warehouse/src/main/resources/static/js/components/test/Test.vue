<template>

  <div id="main">
    <Header></Header>
    <div>
      <input id="input" value="Input" type="button" @click="input">
    </div>
    <div class="table">
      <!--      <table>
              <tr>
                <th>id</th>
                <th>name</th>
                <th>amount</th>
                <th>price</th>
                <th>date</th>
              </tr>
              <tr v-for="message in messages">
                <td>{{ message.id }}</td>
                <td id="name">{{ message.name }}</td>
                <td>{{ message.amount }}</td>
                <td>{{ message.price }}</td>
                <td>data</td>
              </tr>
            </table>-->

      <div class="column">
        <div class="column_name">
          <p class="id">id</p>
          <p>name</p>
          <p>amount</p>
          <p>price</p>
          <p>date</p>
        </div>
        <div class="column_data" v-for="message in messages">
          <div class="column_data_Price">
            <div class="id">{{ message.id }}</div>
            <div id="name">{{ message.name }}</div>
            <div>{{ message.amount }}</div>
            <div>{{ message.price }}</div>
            <div>data</div>
          </div>
          <div class="column_data_product" v-for="product in products">
            <div>{{ product.id }}</div>
            <div>{{ product.text }}</div>
          </div>
        </div>
      </div>

    </div>
    <div v-if="show" id="inputForm">
      <div id="inputFormWhite">
        <div>
          <input id="exit" value="exit" type="button" @click="exitForm">
        </div>

          <div class="headerInput">
            <p>Contragent</p>
            <input type="text" placeholder="Input name">
            <p>amount</p>
            <input type="text" placeholder="Input amount">
            <p>price</p>
            <input type="text" placeholder="Input price">
            <p>date</p>
            <input type="text" placeholder="Input date">
          </div>

        <div class="bodyInput">
        <input type="text" placeholder="Input id">
        <input type="text" placeholder="Input product">
          <input id="add" value="Add" type="button" >
      </div>

        <div class="products">
          <div class="products_column" v-for="product in products">
            <div>{{ product.id }}</div>
            <div>{{ product.text }}</div>
          </div>
        </div>
        <input id="btnForm" value="Input" type="button" @click="input">

      </div>
    </div>
  </div>

</template>

<script>
import TestList from 'components/test/TestList.vue'
import Header from "../../pages/Header.vue"

export default {
  name: "test",
  components: {Header, TestList},
  data: function () {
    return {
      messages: [
        // {id: '1', name: 'one'}
      ],
      products: [
        {id: '1', text: 'one'},
        {id: '2', text: 'two'},
        {id: '3', text: 'three'},
        {id: '4', text: 'four'},
      ],
      show: false
    }
  },
  created() {
    this.$resource('/product{/id}').get().then(result =>
        // console.log(result)
        result.json().then(data =>
            // console.log(data)
            data.forEach(message => this.messages.push(message))
        )
    )
  },
  methods: {
    input() {
      this.show = true;
    },
    exitForm() {
      this.show = false;
    }
  }
}
</script>

<style scoped>
#main *{
  padding-bottom: 5px;
}
.column {
  display: flex;
  flex-direction: column;
}

.column_name {
  display: inline-flex;
}

.column_name * {
  border: 1px solid black;
  padding: 5px;
  margin-left: 5px;
}

.column_data {
  display: flex;
  flex-direction: column;
  border: 1px solid black;
}

.column_data_Price {
  background: #a4b3dc;
  display: inline-flex;
  border: 1px solid black;
}

.column_data_product {
  display: inline-flex;
  border: 1px solid black;
}

.id {
  padding: 5px;
  border: 1px solid black;
}

table {
  border-collapse: collapse;
  width: 1333px;
}

.table {
  border: 1px solid black;
  /*width: 1300px;*/
  width: 100%;
  height: 400px;
  overflow: hidden;
}

#name {
  width: 800px;
}

th, td {
  border: 1px solid black;
}

/*#form {*/
/*  border: 1px solid black;*/
/*  display: inline-flex;*/
/*}*/

#inputForm {
  /*border: 1px solid black;*/
  position: absolute;
  top: 125px;
  /*padding: 50px;*/
  width: 1330px;
  background: rgba(64, 72, 100, 0.75);
  height: 457px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  /*align-items: center;*/
}
#inputFormWhite *{
  margin-bottom: 5px;
}

#inputFormWhite {
  background: white;
  height: 400px;
  width: 1000px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  padding: 10px;
  border-radius: 10px;
}

#input {
  /*padding: 10px;*/
  /*margin: 10px;*/
}

.headerInput {
  display: inline-flex;
  flex-wrap: wrap;
  border: 1px solid black;

}

.headerInput * {
  border: 1px solid black;
  padding: 5px;
  margin: 5px;
}
.bodyInput{
  border: 1px solid black;
  padding: 10px;
}
.products{
  border: 1px solid black;
  padding: 10px;
  display: flex;
  flex-direction: column;
}
.products_column{
  display: inline-flex;
  /*border: 1px solid black;*/
}
.products_column *{
  padding-left: 10px;
}
</style>