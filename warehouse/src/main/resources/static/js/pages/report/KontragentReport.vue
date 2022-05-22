<template>
<div>
  <Header></Header>

  <div>Найти конрагентов по имени:</div>
  <form method="post" action="contragent/filter">
    <input type="text" placeholder="Input name" v-model="text">
    <input type="button" value="save" @click="save">
    <div v-for="text in texts">
<!--      <div>{{message}}</div>-->
      <div v-for="m in text">
        {{m.id}}
        {{m.name}}
        {{m.address}}
      </div>
    </div>
  </form>

  <div class="search">
    <table >
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
    <p><input type="text" v-model="name" placeholder="Введите имя"/></p>
    <ul>
      <li v-for="message in filteredList">
        <p>{{ message.id }} - {{ message.name }} - {{message.inn}} - {{message.address}}</p>
      </li>
    </ul>
    <input type="button" value="input" @click="filteredList()">
  </div>

  <div class="footer">
    <div>
      <p> List1 : {{texts.length}}</p>
      <p> List1 : {{messages.length}}</p>
      <div></div>
    </div>
  </div>

</div>
</template>

<script>
import Header from "../Header.vue";
export default {
  name: "KontragentReport",
  components: {Header},
  data(){
    return{
      text: '',
      texts: [],
      messages: [
         {id: '1', name: 'one', inn: 'innOne', address: 'addressOne'},
         {id: '2', name: 'two', inn: 'innTwo', address: 'addressTwo'},
        {id: '3', name: 'three', inn: 'innThree', address: 'addressThree'}
      ],
      name: '',
      inn: '',
      address: ''
    }
  },
  // created() {
  //   this.$resource('/contragent{/id}').get().then(result =>
  //       // console.log(result)
  //       result.json().then(data =>
  //           // console.log(data)
  //           data.forEach(message => this.messages.push(message))
  //       )
  //   )
  // },
  computed: {
    filteredList() {
      var comp = this.name;

      return this.messages.filter(function (elem) {

        if (comp === '') return true;
        else return elem.name.indexOf(comp) > -1;
      })
    },
    // sortedList () {
    //   switch(this.sortParam){
    //     case 'title': return this.messages.sort(sortByTitle);
    //     case 'company': return this.messages.sort(sortByCompany);
    //     case 'price': return this.messages.sort(sortByPrice);
    //     default: return this.messages;
    //   }
    // }
  },
  methods: {
    save() {
      var text =  this.text;
      this.$resource('/contragent/filter').save({}, text).then(result =>
          result.json().then(data => {
            this.texts.push(data);
            this.text = ''
          })
      )
    }
  }
}
</script>

<style scoped>

</style>