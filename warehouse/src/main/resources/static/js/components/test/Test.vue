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
          <p class="name">name</p>
          <p class="amount">amount</p>
          <p class="price">price</p>
          <p class="data">date</p>
        </div>

        <div class="column_data" v-for="message in messages">
          <div class="column_data_Price">
            <div class="id">{{ message.id }}</div>
            <div class="name">{{ message.contragent}}</div>
            <div class="amount">{{ message.amount }}</div>
            <div class="price">{{ message.price }}</div>
            <div class="data">{{ message.date }}</div>
<!--            <div class="data">date</div>-->
          </div>

<!--          <div class="column_data_product" v-for="product in products">
            <div class="column_data_product_ID">{{ product.id }}</div>
            <div class="column_data_product_TEXT">{{ product.text }}</div>
          </div>-->

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
            <select name="contragent" v-model="contragent">
              <option disabled value="">Выберите контрагента</option>
              <option v-for="contragent in contragents" >
                {{contragent.id}}
              </option>
            </select>

<!--            <input type="text" placeholder="Input name" v-model="contragent">-->
<!--            <input type="text" placeholder="Input name" v-model="contragent">-->

            <p>amount</p>
            <input type="text" placeholder="Input amount" v-model="amount">
            <p>price</p>
            <input type="text" placeholder="Input price" v-model="price">
            <p>date</p>
            <input type="date" placeholder="Input date" v-model="date">
          </div>

        <div class="bodyInput">
        <input type="text" placeholder="Input id">
        <input type="text" placeholder="Input product">
          <input id="add" value="Add" type="button" >
      </div>

<!--        <div class="products">-->
<!--          <div class="products_column" v-for="product in products">-->
<!--            <div>{{ product.id }}</div>-->
<!--            <div>{{ product.text }}</div>-->
<!--          </div>-->
<!--        </div>-->
        <input id="btnForm" value="Input" type="button" @click="save">

      </div>
    </div>
  </div>

</template>

<script>
import TestList from 'components/test/TestList.vue'
import Header from "../../pages/Header.vue"
// import contragent from "../../pages/Contragent";

export default {
  name: "test",
  components: {Header, TestList},
  data () {
    return {
      messages: [
        // {id: '1', name: 'one'}
      ],
      contragents: [
          // {id: '1', name: 'one', inn: 'inn', address: 'Lenin Street, 23'},
          // {id: '1', name: 'two', inn: 'inn2', address: 'Lenin Street, 53'}
      ],
      contragent: '',
      amount: '',
      price: '',
      date: '',
      show: false
      // show: true
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
    this.$resource('/contragent{/id}').get().then(result =>
        // console.log(result)
        result.json().then(data =>
            // console.log(data)
            data.forEach(message => this.contragents.push(message))
        )
    )
  },
  methods: {
    input() {
      this.show = true;
    },
    exitForm() {
      this.show = false;
    },
    save() {
      var message = {
        contragent:{
          id: this.contragent
        },
        amount: this.amount,
        price: this.price,
        date: this.date
      };
      console.log(this.contragent)

      this.$resource('/product{/id}').save({}, message).then(result =>
          result.json().then(data => {
            // console.log(data)
            this.messages.push(data);
            this.contragent = '',
                this.amount = '',
                this.price = '',
                this.date = ''
          })
      )
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
  margin-right: 5px;
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
.column_data_Price *{
  /*border: 1px solid black;*/
  padding: 5px;
  margin-right: 5px;
}
.name {
  width: 900px;
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

.column_data_product *{
  /*border: 1px solid black;*/
  padding: 5px;
  margin-right: 5px;
}

table {
  border-collapse: collapse;
  width: 1333px;
}

.table {
  border: 1px solid black;
  padding: 2px;
  /*width: 1300px;*/
  width: 100%;
  height: 400px;
  overflow: hidden;
  overflow-y: scroll;

}


th, td {
  border: 1px solid black;
}

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

.products_column *{
  padding-left: 10px;
}
</style>