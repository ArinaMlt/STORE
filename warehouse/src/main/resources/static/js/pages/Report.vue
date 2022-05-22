<template>
  <div>
    <Header></Header>
    <div>Найти конрагентов по имени:</div>
    <form method="post" action="contragent/filter">
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
  </div>

</template>

<script>

import Header from "./Header.vue";
export default {
  name: "Report",
  components: {Header},
  data() {
    return {
      company: '',
      text: '',
      sortParam: '',
      phones: [
        {title:'iPhone 7', company:'Apple', price: '4'},
        {title:'iPhone 6S', company:'Apple', price: '3'},
        {title:'Galaxy S8', company:'Samsung', price: '4'},
        {title:'Galaxy S7 Edge', company:'Samsung', price: '4'},
        {title:'Nokia N8', company:'HMD Global', price: '4'}],
      messages: [],
      size: ''
    }

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
    }
  }

}
var sortByTitle = function (d1, d2) {return (d1.title.toLowerCase() > d2.title.toLowerCase()) ? 1 : -1;};
var sortByCompany = function (d1, d2) { return (d1.company.toLowerCase() > d2.company.toLowerCase()) ? 1 : -1; };
var sortByPrice = function (d1, d2) { return (d1.price > d2.price) ? 1 : -1; };
</script>

<style scoped>
a:hover{
  cursor:pointer;
  color: red;
}

</style>