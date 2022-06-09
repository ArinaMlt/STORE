<template>
  <div>
    <Header></Header>
<!--    <input type="button" value="PDF" @click="pdf">-->

    <div class="btns_serching">
      <input class="searching_data" type="date" placeholder="Дата" v-model="date1">
      <input class="searching_data" type="date" placeholder="Дата" v-model="date2">
      <input  class="search_form_btn" type="button" value="Поиск" @click="searching">
    </div>

    <div class="parent_tables">
    <div class="header_tables">

      <div class="table_invoices">
      <table>
        <tr>
          <th>id</th>
          <th>Контрагент</th>
          <th>Количество</th>
          <th>Цена</th>
          <th>Сумма</th>
          <th>Дата</th>
        </tr>
        <tr v-for="invoice in invoices">
          <td>{{invoice.id}}</td>
          <td>{{invoice.contragent.name}}</td>
          <td>{{invoice.totalAmount }}</td>
          <td>{{invoice.totalPrice}}</td>
          <td>{{invoice.totalValue}}</td>
          <td>{{invoice.date}}</td>
        </tr>
      </table>
      </div>


      <div class="table_cheks">
      <table>
        <tr>
          <th>id</th>
          <th>Наименование</th>
          <th>Сумма</th>
          <th>Дата</th>
        </tr>
        <tr v-for="check in checks">
          <td>{{check.id}}</td>
          <td>{{check.name}}</td>
          <td>{{check.sum}}</td>
          <td>{{check.date}}</td>
        </tr>
      </table>
      </div>


    </div>
  </div>

    <div class="parent_total">

      <div class="total">
        <div class="total_checks">
        <div> Приход: {{this.sumInvoice}}</div>
        <div class="check_sum"> Расход: {{this.sumCheck}}</div>
      </div>


        <div class="total_footer">
          <div>Остаток: {{this.remainder}}</div>

          <input  type="button" value="Записать в консоль" @click="write">
        </div>

        <div>
          Итого: {{this.remainderFinish}}
        </div>

      </div>

    </div>


    <div>
      <input type="text" placeholder="date" v-model="date">
      <input  type="button" value="Save" @click="remainderIT">
    </div>


    <table>
      <tr>
        <th>id</th>
        <th>date</th>
        <th>sumInvoice</th>
        <th>sumCheck</th>
        <th>period</th>
        <th>remind</th>
      </tr>
      <tr v-for="remainder in remainders">
        <td>{{remainder.id}}</td>
        <td>{{remainder.date}}</td>
        <td>{{remainder.sumInvoice }}</td>
        <td>{{remainder.sumCheck}}</td>
        <td>{{remainder.period}}</td>
        <td>{{remainder.remind}}</td>
        <td>
          <input class="del" type="button" value="X" @click="del(remainder)">
        </td>
      </tr>
    </table>



<!--    <div ref="content" >-->
<!--    <p id="test">Остаток: </p>-->

<!--    <div>
      <input  type="date" placeholder="Дата" v-model="date1">
      <input  type="date" placeholder="Дата" v-model="date2">
      <input  class="search_form_btn" type="button" value="Поиск" @click="searching">
    </div>-->

<!--    <div>
      <p>Расход:</p>
      <div v-for="check in checks">
        <div v-for="i in check">

          {{i.id}}
          {{i.name}}
          {{i.sum}}
          {{i.date}}

&lt;!&ndash;          {id: 304, name: "Налог", sum: 50, date: "2022-05-31"}&ndash;&gt;
        </div>
        <p>List: {{check.length}}</p>
        <p>List: </p>
      </div>
    </div>-->

<!--    <div>
      <p>Приход: </p>
      <div v-for="invoice in invoices">
        <div v-for="i in invoice">
          {{i.id}}
          {{i.contragent.name}}
          {{i.totalAmount}}
          {{i.totalPrice}}
&lt;!&ndash;           {id: 279,…}
contragent: {id: 2, name: "Бухарова", inn: "382133726351", address: "г.Иркутск, улица Ленина 50"}
date: "2022-05-31"
id: 279
products: [{id: 280, productName: "test1", productAmount: "1", productPrice: "3", productValue: 3}]
totalAmount: "5"
totalPrice: "13"&ndash;&gt;

        </div>
        <p>List: {{invoice.length}}</p>
      </div>
    </div>-->

<!--    </div>-->

<!--    <form method="post" action="contragent/filter">
      <input type="text" placeholder="Input name" v-model="text">
      <input type="button" value="save" @click="save">
      <div v-for="message in messages">
        <div>{{message}}</div>
      </div>
    </form>


    <div>
      <p><input type="text" v-model="company" placeholder="Введите имя"/></p>
      <ul>
        <li v-for="phone in filteredList">
          <p>{{ phone.title }} - {{ phone.company }}</p>
        </li>
      </ul>
      <div>List: {{filteredList.length}}</div>
      <input type="button" value="input" @click="filteredList()">

    </div>

    <div id="app">
      <table>
        <tr>
          <td><a @click="sortParam='title'">Модель</a></td>
          <td><a @click="sortParam='company'">Компания</a></td>
          <td><a @click="sortParam='price'">Цена</a></td></tr>

        <tr v-for="phone in sortedList">
          <td>{{phone.title}}</td><td>{{phone.company}}</td><td>{{phone.price}}</td>
        </tr>
      </table>
    </div>



    <div class="footer">
      <div>
       <p v-model="size"> List: {{sizeList}}</p>
        <div></div>
      </div>
    </div>
    -->


  </div>

</template>

<script>

import Header from "./Header.vue";
import jsPdf from  'jspdf'
export default {
  name: "Report",
  components: {Header},
  data() {
    return {
      date1: new Date().toISOString().substr(0, 10),
      date2: new Date().toISOString().substr(0, 10),
      invoices: [],
      checks: [
      ],
      company: '',
      text: 'work',
      sortParam: '',
      phones: [
        {title:'iPhone 7', company:'Apple', price: '4'},
        {title:'iPhone 6S', company:'Apple', price: '3'},
        {title:'Galaxy S8', company:'Samsung', price: '4'},
        {title:'Galaxy S7 Edge', company:'Samsung', price: '4'},
        {title:'Nokia N8', company:'HMD Global', price: '4'}],
      messages: [],
      size: '',
      totalSum: 0,
      totalPrice: 0,
      totalCount: 0,
      sum: 0,
      value: 0,
      test: [
        {sum: 1},
        {sum: 2},
        {sum: 3},
        {sum: 4}
      ],
      items: [
        { title: "Item 1", body: "I am item 1 body text" },
        { title: "Item 2", body: "I am item 2 body text" },
        { title: "Item 3", body: "I am item 3 body text" },
        { title: "Item 4", body: "I am item 4 body text" }
      ],
      sumInvoice: 0,
      sumCheck: 0,
      remainder: 0,
      remainderFinish: 0,
      date: new Date().toISOString().substr(0, 10),
      period: '',
      remind: '',
      remainders: [],
    }
    },
  created() {
    this.$resource('/remainder{/id}').get().then(result =>
        // console.log(result)
        result.json().then(data => {
            // console.log(data)
            // data.forEach(message => this.remainders.push(message))
          var massiv = []
          massiv.push(data.content);
          // console.log(massiv)
          this.remainders.push(massiv[0][0])
          this.remainders.forEach(invoice => this.remind = invoice.remind)
        })
    )
  },
  watch: {
    sumInvoice(){
      this.remainderFinish = this.sumInvoice - this.sumCheck + this.remind

      this.remainder = this.sumInvoice - this.sumCheck
    },
    invoices(){
        var invoice = this.invoices[0]
      for(let i = 0; i < invoice.length; i++){
        this.value = this.value + Number(invoice[i].totalValue)
      }
      console.log(invoice)
      console.log(this.value)
    },
  },
  computed: {

    filteredList() {
      var comp = this.company;
      return this.phones.filter(function (elem) {

        if (comp === '') return true;
        else return elem.company.indexOf(comp) > -1;
         this.size = this.elem.price;
      })
    },
    sortedList () {
      switch(this.sortParam){
        case 'title': return this.phones.sort(sortByTitle);
        case 'company': return this.phones.sort(sortByCompany);
        case 'price': return this.phones.sort(sortByPrice);
        default: return this.phones;
      }
    },
    sizeList(){
     return Number(this.size)
    }
  },
  methods: {
    save() {
      var text =  this.text;
      this.$resource('/contragent/filter').save({}, text).then(result =>
          result.json().then(data => {
            this.messages.push(data);
            this.text = ''
          })
      )
    },
    searching(){
      var message = (this.date1).toString() +'!'+ (this.date2).toString();

      this.$resource('/invoice/byDate').save({},message).then(result =>
          result.json().then(data => {
            data.forEach(message => this.invoices.push(message))
            this.invoices.forEach(invoice => this.sumInvoice += invoice.totalValue)
            // this.invoices.push(data);
          })
      )


      this.$resource('/check/byDate').save({},message).then(result =>
          result.json().then(data => {
            data.forEach(message => this.checks.push(message))
            // this.checks.push(data);
            this.checks.forEach(check => this.sumCheck += check.sum)
          })
      )



    },
    remainderIT(){
      var remainder = {
        date: this.date,
        sumInvoice: this.sumInvoice,
        sumCheck: this.sumCheck,
        period: (this.date1).toString() +'!'+ (this.date2).toString(),
        remind: this.remainderFinish
      }
      console.log(remainder)

      this.$resource('/remainder{/id}').save({}, remainder).then(result =>
          result.json().then(data => {
            this.remainders.push(data);
          }))
    },
    del(remainder) {
      console.log(remainder.id)
      this.$resource('/remainder{/id}').remove({id: remainder.id}).then(result => {
        if (result.ok) {
          this.remainders.splice(this.remainders.indexOf(remainder), 1)
        }
      })
    },
    write(){
      console.log(this.sumCheck)
      console.log(this.sumInvoice)
      console.log(this.remainder)
    }
  }

}
var sortByTitle = function (d1, d2) {return (d1.title.toLowerCase() > d2.title.toLowerCase()) ? 1 : -1;};
var sortByCompany = function (d1, d2) { return (d1.company.toLowerCase() > d2.company.toLowerCase()) ? 1 : -1; };
var sortByPrice = function (d1, d2) { return (d1.price > d2.price) ? 1 : -1; };
</script>

<style scoped>
.parent_tables{
  display: flex;
  justify-content: center;
}
.parent_total{
  display: flex;
  justify-content: center;
  /*border: 1px solid black;*/

}
.total{
  border: 1px solid black;
  /*height: 50px;*/
  width: 1200px;
}
.total_checks{
  display: inline-flex;
}
.total_footer{
  /*height: 100px;*/
  border: 1px solid black;
  display: flex;
  justify-content: space-between;
}
table{
  width: 100%;

}
.header_tables{
  /*border: 1px solid black;*/
  /*height: 500px;*/
  width: 1200px;
  overflow: hidden;
  display: inline-flex;
}
.table_invoices{
  margin-right: 1px;
}
.table_invoices, .table_cheks{

  width: 100px;
  /*height: 400px;*/
  /*border: 1px solid black;*/
  border: 1px solid rgba(58, 56, 56, 0.55);
  border-radius: 3px;
  overflow: hidden;
  /*overflow-y: scroll;*/
  flex-grow: 1;
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

a:hover{
  cursor:pointer;
  color: red;
}
.btns_serching{
  left: 0px;
  margin-top: 5px;
  display: flex;
  justify-content: center;
  margin-bottom: 5px;
}
.btns_serching * {
margin-right: 5px;
}
.check_sum{
  margin-left: 5px;
}
.search_form_btn{
  /*margin-top: 5px;*/
  background: rgba(23, 73, 196, 0.83);
  border: none;
  border-radius: 3px;
  padding: 5px;
  color: white;
  width: 100px;
  height: 30px;
}
.searching_data{
  width: 100px;
  height: 25px;
}
*{
  font-family: "Trebuchet MS";
}
</style>