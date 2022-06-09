<template>
<div>
  <Header></Header>
  <div>

    <div v-if="show" class="parent_input">
      <div class="input">
        <!--      <input class="name" type="text" placeholder="Наименование" v-model="name">-->
<!--        <div>-->
       <div class="input_exit_div">
         <input  class="input_exit" type="button" value="X" @click="delForm">
       </div>

        <select class="input_name" v-model="name">
          <option>Терминал1</option>
          <option>Терминал2</option>
          <option>Наличные</option>
        </select>
<!--      </div>-->

        <input  class="input_Date" type="date" placeholder="Дата"  v-model="date">
        <input class="input_Sum" type="number" placeholder="Сумма" v-model="sum">

        <input  class="input_save" type="button" value="Сохранить" @click="save">

      </div>
    </div>

<div>
  <input class="create_btn" id="input" value="Создать" type="button" @click="input">
</div>



    <table>
      <tr>
        <th class="tdId">ID</th>
        <th>Наименование</th>
        <th >Сумма</th>
        <th>Дата</th>
        <th></th>
      </tr>
      <tr v-for="check in checks" @click="edit(check)">
        <td class="tdId">{{check.id}}</td>
        <td>{{check.name}}</td>
        <td class="tdSum">{{check.sum}}</td>
        <td class="tdDate">{{check.date}}</td>
        <td class="tdBtn">
<!--          <input class="edit" type="button" value="Edit" @click="edit(check)"/>-->
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
      show: false,
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
      this.show =false,
     this.id ='',
     this.name ='',
     this.sum = '',
     this.date = new Date().toISOString().substr(0, 10)
    },
    edit(check){
      this.show = true,
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
      var message = {
        data1:  this.date1,
        date2:  this.date2
      }
      var message2 = (this.date1).toString() +'!'+ (this.date2).toString();
      console.log(message2)

      this.$resource('/invoice/byDate').save({},message2).then(result =>
          result.json().then(data => {
            this.invoices.push(data);
          })
      )

    },
    input() {
      this.show = true;
    },
    exitForm() {
      this.show = false
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
 /* background: white;
  margin-left: 10px;
  !*border: 1px solid black;*!
  border-radius: 5px;
  width: 300px;
  height: 300px;
  box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.38);*/

}
.input_name, .input_Sum, .input_Date{
  height: 30px;
  width: 150px;
  border: none;
  background: rgba(211, 214, 218, 0.63);
}
.input_save {
  width: 100px;
  height: 30px;
  background: rgba(21, 148, 21, 0.29);
  border: none;
  border-radius: 5px;
}
.input_save:hover{
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
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
  /*border: 1px solid black;*/

  background: white;
  margin-left: 10px;
  /*border: 1px solid black;*/
  border-radius: 5px;
  width: 350px;
  height: 200px;
  box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.38);
}
.input *{
  margin-top: 5px;
  margin-left: 5px;
  /*border: 1px solid black;*/
}

th {
  color: rgba(107, 106, 106, 0.54);
  text-align: start;
}
td{
  border-bottom: 1px solid #d2d2d2;
  /*padding-left: 5px;*/
}
table{
  width: 100%;
  margin-top: 5px;
}
tr:hover{
  background: rgba(189, 200, 232, 0.37);
}
.tdSum, .tdDate, .tdBtn{
  width: 100px;
}
.tdId{
  width: 50px;
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
.input_exit_div{
  width: 100%;
  display: flex;
  justify-content: flex-end;
  margin-right: 20px;
}
*{
  font-family: "Trebuchet MS";
}
</style>