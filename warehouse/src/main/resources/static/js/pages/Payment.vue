<template>
  <div>
    <Header></Header>

    <div v-if="show" class="parent_input">

      <div class="input">
        <!--      <input class="name" type="text" placeholder="Наименование" v-model="name">-->
        <!--        <div>-->
        <div class="input_exit_div">
          <input class="input_buttn_exit" type="button" value="X" @click="delForm">
        </div>
        <!--      </div>-->

        <div class="input_table_invoice">

          <div class="input_table">
          <table>
            <tr>
              <th>id</th>
              <th>Контрагент</th>
              <th>Дата</th>
              <th>Цена</th>
              <th>Сумма</th>
            </tr>
            <tr v-for="invoice in invoices" @click="edit(invoice)">
              <td>{{ invoice.id }}</td>
              <td>{{ invoice.contragent.name }}</td>
              <td>{{ invoice.date }}</td>
              <td>{{ invoice.totalPrice }}</td>
              <td>{{ invoice.totalValue }}</td>

            </tr>
          </table>
          </div>

          <div class="input_table">
            <table>
              <tr>
                <th>id</th>
                <th>Наименование</th>
                <th>Цена</th>
              </tr>
              <tr v-for="product in products">
                <td>{{ product.id }}</td>
                <td>{{ product.productName }}</td>
                <td>{{ product.productPrice }}</td>

              </tr>
            </table>
          </div>

          <div class="input_table_invoice_form">
            <input class="input_name" type="date" placeholder="Двтв" v-model="date">
            <input class="input_name" type="text" placeholder="ID Контрагента" v-model="invoiceId">
            <input class="input_name" type="text" placeholder="Контрагент" v-model="invoiceName">
            <input class="input_name" type="text" placeholder="Сумма" v-model="invoiceSum">
          </div>
        </div>
<!--
        <select class="form_select" name="invoice" v-model="invoice">
          <option disabled value="">Выберите invoice</option>
          <option v-for="invoice in invoices">
            {{ invoice.id }}
            {{ invoice.contragent.name }}
            {{ invoice.date }}
            {{ invoice.totalValue }}
          </option>
        </select>-->


<!--        <input class="input_name" type="text" placeholder="Поставщик" v-model="name">
        <input class="input_inn" type="text" placeholder="ИНН" v-model="inn">
        <textarea class="input_address" type="text" placeholder="Адрес" v-model="address"></textarea>-->

        <input class="input_buttn_save" type="button" value="Сохранить" @click="save">

      </div>
    </div>



    <div class="top_btn">
      <input class="create_btn" id="input" value="Создать" type="button" @click="input">
      <input class="filter_btn" value="Фильтровать" type="button" @click="filterOpen">
    </div>

    <div v-if="filter" class="filter">
      <div class="filter_exit_div">
        <input
            class="exit_filter"
            value="X"
            type="button"
            @click="exitFilter">
      </div>
      <div>
        <input type="date" placeholder="Дата" v-model="datefilter1">
        <input type="date" placeholder="Дата" v-model="datefilter2">
        <input class="search_form_btn" type="button" value="Поиск" @click="searchingFilter">
      </div>

    </div>

    <div class="listBlock">
    <div class="table">
      <!--        <div class="input">
                &lt;!&ndash;          <input type="text" placeholder="id" v-model="id">&ndash;&gt;
                <input class="input_name" type="text" placeholder="Поставщик" v-model="name">
                <input class="input_inn" type="text" placeholder="ИНН" v-model="inn">
                <input class="input_address" type="text" placeholder="Адрес" v-model="address">
                <input class="input_buttn_save" type="button" value="Save" @click="save">
                <input class="input_buttn_exit" type="button" value="X" @click="delForm">

              </div>-->

      <table>
        <tr>
          <th>id</th>
          <th>Контрагент</th>
          <th>Сумма</th>
          <th>Дата</th>
          <th></th>
        </tr>
        <!--          <tr>
                    <td>ID</td>
                    <td><input type="text" placeholder="Input name" v-model="name"></td>
                    <td><input type="text" placeholder="Input inn" v-model="inn"></td>
                    <td><input type="text" placeholder="Input address" v-model="address"></td>
                    <td>
                      <input  type="button" value="Save" @click="save">
                      <input  type="button" value="X" @click="delForm">
                    </td>
                  </tr>-->

<!--        date: "2022-06-08"
id: 353
invoice: {id: 333, contragent: null, totalAmount: null, totalPrice: null, totalValue: null, pay: null,…}
contragent: null
date: null
id: 333
pay: null
products: null
totalAmount: null
totalPrice: null
totalValue: null
sum: 240-->

        <tr v-for="payment in payments">
          <td>{{ payment.id }}</td>
          <td>{{ payment.invoice.contragent.name }}</td>
          <td>{{ payment.sum }}</td>
          <td>{{ payment.date }}</td>
          <td>
            <!--              <input class="edit" type="button" value="Edit" @click="edit(message)"/>-->
            <input class="del" type="button" value="X" @click="del(payment)">
          </td>
        </tr>
      </table>
    </div>
    </div>

  </div>
</template>

<script>
import Header from "./Header.vue";
export default {
  name: "Payment",
  components: {Header},
  data() {
    return {
      show: false,
      filter: false,
      datefilter1: new Date().toISOString().substr(0, 10),
      datefilter2: new Date().toISOString().substr(0, 10),
      date: new Date().toISOString().substr(0, 10),
      payments: [],
      invoices: [],
      invoice: '',
      invoiceName: '',
      invoiceId: '',
      invoiceSum: 0,
      products: [],
    }
  },
  created() {

    this.$resource('/payment{/id}').get().then(result =>
        // console.log(result)
        result.json().then(data =>
            // console.log(data)
            data.forEach(message => this.payments.push(message))
        )
    )

  },
  methods:{
    input() {
      this.show = true;
      this.invoices = []
      this.$resource('/invoice/byPay').save({}, false).then(result =>
          result.json().then(data => {
            this.invoices = []
            data.forEach(message => this.invoices.push(message))

            console.log(this.invoices)
          })
      )
    },
    filterOpen() {
      this.filter = true;
    },
    exitFilter() {
      this.filter = false;
    },
    delForm() {
      this.show = false

    },
    save() {
      this.show = false
      var payment = {
        date: this.date,
        sum: this.invoiceSum,
        invoice: {
          id: this.invoiceId
        }
      }

     const index =  this.invoices.find(item => item.id == this.invoiceId)
      console.log(index)

      var invoice = {
        id: index.id,
        contragent: {
          id: index.contragent.id
        },
        totalAmount: index.totalAmount,
        totalPrice: index.totalPrice,
        totalValue: index.totalValue,
        date: index.date,
        pay: true
      };
      console.log(invoice)

      this.$resource('/invoice{/id}').update({id: this.invoiceId}, invoice).then(result =>
          result.json().then(data => {
            console.log(data)
          }))

      this.$resource('/payment{/id}').save({}, payment).then(result =>
          result.json().then(data => {
            this.payments.push(data);
          }))

    },
    searchingFilter() {
     /* var message = (this.datefilter1).toString() + '!' + (this.datefilter2).toString();

      this.$resource('/invoice/byDate').save({}, message).then(result =>
          result.json().then(data => {
            this.messages = []
            data.forEach(message => this.messages.push(message))
            // console.log(data)
          })
      )*/
    },
    edit(invoice) {
      this.show = true,
          this.invoiceId = invoice.id,
          this.invoiceSum = invoice.totalPrice
      this.invoiceName = invoice.contragent.name
      this.products = []
      invoice.products.forEach(message => this.products.push(message))
    },
    del(payment) {
      console.log(payment.id)
      this.$resource('/payment{/id}').remove({id: payment.id}).then(result => {
        if (result.ok) {
          this.payments.splice(this.payments.indexOf(payment), 1)
        }
      })


    },
  }
}
</script>

<style scoped>
.top_btn {
  display: flex;
  justify-content: space-between;
}
.filter {
  position: absolute;
  background: white;
  box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.38);

  height: 100px;
  width: 400px;
  right: 0px;
  border-radius: 5px;
  /*display: flex;*/
  /*flex-direction: column;*/
}
.exit_filter {
  width: 40px;
}
.filter_exit_div {
  display: flex;
  justify-content: flex-end;
}

th {
  /*border: 1px solid black;*/
  color: rgba(107, 106, 106, 0.54);
  text-align: start;
}

td {
  border-bottom: 1px solid #d2d2d2;
  /*padding-left: 5px;*/
}


.table {
  margin-top: 5px;
  width: 100%;
}

table {
  /*width: 1150px;*/
  margin-top: 5px;
  width: 100%;

}

.input_table tr:hover {
  background: rgba(189, 200, 232, 0.37);
}
.listBlock {
  /*margin-top: 20px;*/
  display: inline-flex;
  justify-content: space-between;
  width: 100%;
  /*border: 1px solid black;*/
  height: 470px;
  overflow: hidden;
  overflow-y: scroll;
}

input {
  height: 20px;
}

.input {
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
  /*border: 1px solid black;*/

  background: white;
  margin-left: 10px;
  /*border: 1px solid black;*/
  border-radius: 5px;
  width: 80%;
  height: 350px;
  box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.38);
}
.input_table_invoice{
  /*border:  1px solid black;*/
  width: 100%;
  display: flex;
}
.input_table{
  border:  1px solid rgba(0, 0, 0, 0.38);
  overflow: hidden;
  height: 200px;
  width: 50%;
  overflow-y: scroll;
  margin-left: 3px;
}
.input_table_invoice_form{
  margin-left: 3px;
  margin-right: 3px;
  padding-left: 3px;
  border:  1px solid rgba(0, 0, 0, 0.38);
  width: 50%;
}
.input *{
  margin-top: 10px;
  /*border: 1px solid black;*/
}
.input_name{
  /*width: 275px;*/
  height: 30px;
  width: 200px;
  border: none;
  background: rgba(211, 214, 218, 0.63);
}

.input_buttn_save {
  background: rgba(21, 148, 21, 0.29);
  border: none;
  border-radius: 5px;
}

.input_buttn_save:hover {
  background: rgba(25, 178, 25, 0.63);
}

.input_buttn_exit, .del {
  background-color: rgba(255, 0, 0, 0.4);
  border: none;
  border-radius: 5px;
}

.input_buttn_exit:hover, .del:hover {
  background-color: rgba(255, 0, 0, 0.8);
}
.parent_input{
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top:0;
  overflow: hidden;
  scroll-x: none;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color:  rgba(64, 72, 100, 0.55);;
}
.input_exit_div{
  width: 100%;
  display: flex;
  justify-content: flex-end;
  margin-right: 20px;
}
.input_buttn_exit{
  width: 50px;
  height: 20px;
}
.input_buttn_save{
  width: 100px;
  height: 30px;
}
.create_btn{
  margin-top: 5px;
  background: rgba(23, 73, 196, 0.83);
  border: none;
  border-radius: 3px;
  padding: 5px;
  color: white;
  width: 100px;
  height: 30px;
}
*{
  font-family: "Trebuchet MS";
}
</style>