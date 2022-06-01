<template>
<div>
  <Header></Header>
  <div>


    <div class="search_form">

<!--      <v-select :options="invoice"/>-->
<!--      <select name="invoice" v-model="invoice" >
        <option disabled value="">Выберите</option>

        <option v-for="i in invoice">
&lt;!&ndash;          {{ invoice.id }}&ndash;&gt;
              {{i.id}}
        </option>
      </select>-->


      <input  type="date" placeholder="Дата" v-model="date1">
      <input  type="date" placeholder="Дата" v-model="date2">
      <input  class="search_form_btn" type="button" value="Поиск" @click="searching">

    </div>

    <div class="input">
      <input class="name" type="text" placeholder="Наименование" v-model="name">
      <input class="tdSum" type="number" placeholder="Сумма" v-model="sum">
      <input  class="tdDate" type="date" placeholder="Дата"  v-model="date">
      <input  class="input_save" type="button" value="Save" @click="save">
      <input  class="input_exit" type="button" value="X" @click="delForm">
    </div>
    <table>
      <tr>
        <th class="tdId">ID</th>
        <th>Наименование</th>
        <th >Сумма</th>
        <th>Дата</th>
        <th></th>
      </tr>
      <tr v-for="check in checks">
        <td class="tdId">{{check.id}}</td>
        <td>{{check.name}}</td>
        <td class="tdSum">{{check.sum}}</td>
        <td class="tdDate">{{check.date}}</td>
        <td class="tdBtn">
          <input class="edit" type="button" value="Edit" @click="edit(check)"/>
          <input class="del" type="button" value="X" @click="del(check)">
        </td>
      </tr>

    </table>

  </div>
</div>
</template>

<script>
import Header from "./Header.vue"
export default {
  name: "Check",
  components: {Header},
  data(){
    return{
      checks:[],
      date: new Date().toISOString().substr(0, 10),
      name: '',
      sum: '',
      id: '',
      invoices: [
        // {id:'1', name: 'one'},
        // {id:'2', name: 'two'},
        // {id:'3', name: 'three'}
      ],
      invoice: '',
      date1: new Date().toISOString().substr(0, 10),
      date2: new Date().toISOString().substr(0, 10),
    }
  },
  methods:{
    save(){
      var check = {
        name: this.name,
        sum: this.sum,
        date: this.date
      }
      console.log(check)
      if(this.id){
        var check2 = {
          id: this.id,
          name: this.name,
          sum: this.sum,
          date: this.date,
        }
        this.$resource('/check{/id}').update({id: this.id}, check2).then(result =>
            result.json().then(data => {
              const index = this.checks.findIndex(item => item.id === this.id)
              this.checks.splice(index, 1, data);
            }))
      }
      else {

      this.$resource('/check{/id}').save({}, check).then(result =>
          result.json().then(data => {
            this.checks.push(data);
          }))

    }
      this.delForm()
    },
    delForm(){
     this.id ='',
     this.name ='',
     this.sum = '',
     this.date = new Date().toISOString().substr(0, 10)
    },
    edit(check){
      this.name = check.name,
          this.sum = check.sum,
          this.id = check.id,
          this.date = check.date
    },
    del(check){
      this.$resource('/check{/id}').remove({id: check.id}).then(result => {
        if (result.ok) {
          this.checks.splice(this.checks.indexOf(check), 1)
        }
      })
    },
    searching(){

      // var date = {date: this.date1}
      var date =  this.date1
      console.log(date)
      this.$resource('/invoice/byDate').save({}, date).then(result =>
          result.json().then(data => {
            this.invoices.push(data);
          })
      )

    }
  },
  created() {
    this.$resource('/check{/id}').get().then(result =>
        // console.log(result)
        result.json().then(data =>
            // console.log(data)
            data.forEach(message => this.checks.push(message))
        )
    )
  },
}
</script>

<style scoped>
.search_form{
  border: 1px solid black;
  height: 100px;
}
.input_save, .edit{
  width: 50px;
  background: rgba(21, 148, 21, 0.29);
  border: none;
  border-radius: 5px;
}
.input_save:hover, .edit:hover{
  background: rgba(25, 178, 25, 0.63);
}
.input_exit, .del{
  width: 40px;
  background-color: rgba(255, 0, 0, 0.4);
  border: none;
  border-radius: 5px;
}
.input_exit:hover, .del:hover{
  background-color: rgba(255, 0, 0, 0.8);
}
.input{
  margin-left: 60px;
  margin-top: 5px;
  width: 100%;
  /*border: 1px solid black;*/
}
.name{
  width: 930px;
}
.input *{
  height: 30px;
}
th {
  border: 1px solid black;
}
td{
  border: 1px solid #d2d2d2;
}
table{
  width: 100%;
  margin-top: 5px;
}
.tdSum, .tdDate, .tdBtn{
  width: 100px;
}
.tdId{
  width: 50px;
}
</style>