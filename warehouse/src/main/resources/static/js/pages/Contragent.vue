<template xmlns="http://www.w3.org/1999/html">
  <div>
    <Header></Header>
    <!--    <h1>Contragents</h1>-->
    <div class="listBlock">
      <div class="table">
        <table>
          <tr>
            <th>id</th>
            <th>name</th>
            <th>inn</th>
            <th>address</th>
          </tr>
          <tr v-for="message in messages">
            <td>{{ message.id }}</td>
            <td>{{ message.name }}</td>
            <td>{{ message.inn }}</td>
            <td>{{ message.address }}</td>
          </tr>
        </table>
      </div>
      <div>
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
        </div>
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
  data: function () {
    return {
      messages: [
        // {id: '1', name: 'one', inn: 'innOne', address: 'addressOne'},
        // {id: '2', name: 'two', inn: 'innTwo', address: 'addressTwo'},
        // {id: '3', name: 'three', inn: 'innThree', address: 'addressThree'}
      ],
      name: '',
      inn: '',
      address: ''
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
      this.$resource('/contragent{/id}').save({}, message).then(result =>
          result.json().then(data => {
            this.messages.push(data);
            this.name = '',
            this.inn = '',
            this.address = ''
          })
      )
    }
  }
}
</script>

<style scoped>
th, td {
  border: 1px solid black;
}

table {
  width: 1150px;
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
</style>