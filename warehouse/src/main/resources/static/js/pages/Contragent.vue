<template xmlns="http://www.w3.org/1999/html">
  <div>
    <Header></Header>
    <!--    <h1>Contragents</h1>-->
    <div class="listBlock">
      <div class="table">
        <div class="input">

          <input class="input_name" type="text" placeholder="Поставщик" v-model="name">
          <input class="input_inn" type="text" placeholder="ИНН" v-model="inn">
          <input class="input_address" type="text" placeholder="Адрес" v-model="address">
          <input class="input_buttn_save"  type="button" value="Save" @click="save">
          <input class="input_buttn_exit"  type="button" value="X" @click="delForm">

        </div>
        <table>
          <tr>
            <th>id</th>
            <th>name</th>
            <th>inn</th>
            <th>address</th>
            <th>buttons</th>
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
          <tr v-for="message in messages">
            <td>{{ message.id }}</td>
            <td>{{ message.name }}</td>
            <td>{{ message.inn }}</td>
            <td>{{ message.address }}</td>
            <td>
              <input class="edit" type="button" value="Edit" @click="edit(message)"/>
              <input class="del" type="button" value="X" @click="del(message)">
            </td>
          </tr>
        </table>
      </div>
      <div>

        <!--
        <div id="inputFORM">

         <div>
            <h3>Контрагент: </h3>
            <p>ИМЯ</p>
            <input type="text" placeholder="Input name" v-model="name">
            <p>ИНН</p>
            <input type="text" placeholder="Input inn" v-model="inn">
            <p>АДРЕС</p>
            <input type="text" placeholder="Input address" v-model="address">
          </div>
          <input id="btn" type="button" value="Save" @click="save">
          <input id="btn" type="button" value="X" @click="delForm">
        </div>-->

      </div>
    </div>
  </div>
</template>

<script>
import ContragentList from 'components/contragent/ContragentList.vue'
import Header from "./Header.vue"
import messagesApi from "../api/messages"

export default {
  name: "Contragent",
  components: {Header},
  data() {
    return {
      messages: [
        // {id: '1', name: 'one', inn: 'innOne', address: 'addressOne'},
        // {id: '2', name: 'two', inn: 'innTwo', address: 'addressTwo'},
        // {id: '3', name: 'three', inn: 'innThree', address: 'addressThree'}
      ],
      name: '',
      inn: '',
      address: '',
      id: ''
    }
  },
  created() {
    this.$resource('/contragent{/id}').get().then(result =>
        // console.log(result)
        result.json().then(data =>
            // console.log(data)
            data.forEach(message => this.messages.push(message))
        )
    )
  },
  methods: {
    save() {
      var message = {
        name: this.name,
        inn: this.inn,
        address: this.address
      };

      if (this.id) {
        var message2 = {
          id: this.id,
          name: this.name,
          inn: this.inn,
          address: this.address
        };
        console.log(this.id)
        this.$resource('/contragent{/id}').update({id: this.id}, message2).then(result =>
            result.json().then(data => {
              const index = this.messages.findIndex(item => item.id === this.id)
              this.messages.splice(index, 1, data);
            }))

      } else {
        this.$resource('/contragent{/id}').save({}, message).then(result =>
            result.json().then(data => {
              this.messages.push(data);
            }))
      }
      this.delForm()
    },
    del(message) {
      console.log(message.id)
      this.$resource('/contragent{/id}').remove({id: message.id}).then(result => {
        if (result.ok) {
          this.messages.splice(this.messages.indexOf(message), 1)
        }
      })
    },
    edit(message) {
      this.name = message.name,
          this.address = message.address,
          this.inn = message.inn,
          this.id = message.id
    },
    delForm() {
      this.name = '',
          this.address = '',
          this.inn = '',
          this.id = ''
    }
  }
}
</script>

<style scoped>
th {
  border: 1px solid black;
}
td{
  border: 1px solid #d2d2d2;
}

.table{
  margin-top: 5px;
width: 100%;
}
table {
  /*width: 1150px;*/
  margin-top: 5px;
  width: 100%;

}


.listBlock {
  /*margin-top: 20px;*/
  display: inline-flex;
  justify-content: space-between;
  width: 100%;
  border: 1px solid black;
  height: 440px;
  overflow: hidden;
}

#inputFORM {
  height: 100%;
  background: #E4E4E4;
  padding: 10px;
  border: 1px solid black;
  display: flex;
  flex-direction: column;
  align-items: start;
}

#inputFORM * {
  /*border: 1px solid black;*/
}

#btn {
  margin-left: 50px;
  margin-top: 50px;
  margin-bottom: 10px;
  border-radius: 4px;
  background: #9b9b9b;
  color: white;
  padding-left: 20px;
  padding-right: 20px;
  border: 1px solid #9b9b9b;
}

#btn:hover {
  border: 1px solid black;
}
input{
  height: 20px;
}
.input{
  /*border: 1px solid black;*/
  margin-left: 80px;
}
.input_name{
  width: 275px;
}
.input_inn{
  width: 260px;
}
.input_address{
  width: 510px;
}
.input_buttn_save{
background: rgba(21, 148, 21, 0.29);
  border: none;
  border-radius: 5px;
}
.input_buttn_save:hover{
  background: rgba(25, 178, 25, 0.63);
}
.input_buttn_exit, .del{
background-color: rgba(255, 0, 0, 0.4);
  border: none;
  border-radius: 5px;
}

.input_buttn_exit:hover , .del:hover{
  background-color: rgba(255, 0, 0, 0.8);
}
</style>