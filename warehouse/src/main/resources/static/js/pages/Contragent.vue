<template xmlns="http://www.w3.org/1999/html">
  <div>
    <Header></Header>
    <!--    <h1>Contragents</h1>-->
    <div v-if="show" class="parent_input">

      <div class="input">
        <!--      <input class="name" type="text" placeholder="Наименование" v-model="name">-->
        <!--        <div>-->
        <div class="input_exit_div">
          <input class="input_buttn_exit" type="button" value="X" @click="cleanForm">
        </div>
        <!--      </div>-->

        <div class="input_tables">
          <div v-if="contragentExist" class="input_table">
            <table>
              <tr>
                <th>id</th>
                <th>Наименование</th>
                <th>Категория</th>
              </tr>
              <tr v-for="product in products" @click="editProduct(product)">
                <td>{{ product.id }}</td>
                <td>{{ product.name }}</td>
                <td>{{ product.category }}</td>
              </tr>
            </table>
          </div>

          <div class="input_buttns">
            <div v-if="contragentExist" class="input_buttns_top">
              <input class="input_buttn_del" type="button" value="Удалить номенклатуру" @click="deleteProduct">
              <textarea class="input_name" type="text" placeholder="Номенклатура" v-model="nomenclature"></textarea>

              <select class="input_name" v-model="category">
                <option disabled value="">категория</option>
                <optgroup label="молоко">
                  <option>молоко</option>
                  <option>йогурт</option>
                </optgroup>

                <optgroup label="мясо">
                  <option>колбаса</option>
                  <option>сосиски</option>
                  <option>сардельки</option>
                  <option>мясо</option>
                  <option>рыба</option>
                </optgroup>

                <optgroup label="хлеб">
                  <option>хлеб</option>
                  <option>выпечка</option>
                  <option>печенье</option>
                  <option>конфеты</option>
                </optgroup>

                <optgroup label="вода">
                  <option>вода</option>
                  <option>выпечка</option>
                  <option>печенье</option>
                  <option>конфеты</option>
                </optgroup>
                <optgroup label="фрукты">
                  <option>фрукты</option>
                  <option>овощи</option>
                </optgroup>
                <optgroup label="крупа">
                  <option>крупа</option>
                </optgroup>
                <optgroup label="неизвестно">
                  <option>нет</option>
                </optgroup>
              </select>


              <input class="input_buttn_add" type="button" value="Добавить" @click="add">
              <input class="input_buttn_add" type="button" value="X" @click="cleanProduct">


            </div>

            <div class="input_bottom">
            <input class="input_name" type="text" placeholder="Поставщик" v-model="name">
            <input class="input_inn" type="text" placeholder="ИНН" v-model="inn">
            <textarea class="input_address" type="text" placeholder="Адрес" v-model="address"></textarea>
          </div>

          </div>
        </div>
        <div class="input_save_div">
          <input class="input_buttn_save" type="button" value="Сохранить" @click="save">
        </div>
      </div>

    </div>

    <div>
      <input class="create_btn" id="input" value="Создать" type="button" @click="input">
      <input type="text" v-model="contragentName" placeholder="Наименование"/>
      <input  value="X" type="button" @click="cleanFormInputName">

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
            <th>Наименование</th>
            <th>ИНН</th>
            <th>Адрес</th>
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
<!--          <tr v-for="message in messages" @click="edit(message)">-->
          <tr v-for="message in filteredList" @click="edit(message)">
            <td>{{ message.id }}</td>
            <td>{{ message.name }}</td>
            <td>{{ message.inn }}</td>
            <td>{{ message.address }}</td>
            <td>
              <!--              <input class="edit" type="button" value="Edit" @click="edit(message)"/>-->
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
      show: false,
      contragentExist: false,
      messages: [],
      products: [
        {id: 0}
      ],
      name: '',
      inn: '',
      address: '',
      id: '',
      nomenclature: '',
      category: '',
      idProduct: '',
      contragentName: ''
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
  computed: {
    filteredList() {
      var comp = this.contragentName;

      return this.messages.filter(function (elem) {

        if (comp === '') return true;
        else return elem.name.indexOf(comp) > -1;
      })

    },
  },
  methods: {
    input() {
      this.show = true;
    },
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
              console.log(data)
              const index = this.messages.findIndex(item => item.id === this.id)
              this.messages.splice(index, 1, data);
              console.log(index)

            }))

      } else {
        this.$resource('/contragent{/id}').save({}, message).then(result =>
            result.json().then(data => {
              this.messages.push(data);
            }))
      }
      this.cleanForm()
    },
    add() {

      if (this.idProduct) {
        var product2 = {
          id: this.idProduct,
          name: this.nomenclature,
          category: this.category,
          contragent: {
            id: this.id
          }
        }
        this.$resource('/nomenclature{/id}').update({id: this.idProduct}, product2).then(result =>
            result.json().then(data => {
              const index = this.products.findIndex(item => item.id === this.idProduct)
              this.products.splice(index, 1, data);
            }))

      } else {

        var product = {
          name: this.nomenclature,
          category: this.category,
          contragent: {
            id: this.id
          }
        }
        this.$resource('/nomenclature{/id}').save({}, product).then(result =>
            result.json().then(data => {
              this.products.push(data);
            }))
      }
      this.cleanProduct()
    },
    del(message) {
      this.show = false
      console.log(message.id)
      this.$resource('/contragent{/id}').remove({id: message.id}).then(result => {
        if (result.ok) {
          this.messages.splice(this.messages.indexOf(message), 1)
        }
      })
    },
    edit(message) {
      this.show = true,
          this.name = message.name,
          this.address = message.address,
          this.inn = message.inn,
          this.id = message.id,
          this.products = message.nomenclatures
      if (this.id) {
        this.contragentExist = true
      }
    },
    editProduct(product) {
      this.nomenclature = product.name,
          this.category = product.category
      this.idProduct = product.id
    },
    deleteProduct(){

      this.$resource('/nomenclature{/id}').remove({id: this.idProduct}).then(result => {
        if (result.ok) {
          this.products.splice(this.messages.indexOf(this.idProduct), 1)
        }
      })

    },
    cleanProduct() {
      this.nomenclature = '',
          this.category = ''
      this.idProduct = ''
    },
    cleanForm() {
      this.show = false,
          this.name = '',
          this.address = '',
          this.inn = '',
          this.id = ''
      this.contragentExist = false
      this.nomenclature = '',
          this.category = ''
      this.idProduct = ''
    },
    cleanFormInputName(){
      this.contragentName = ''
    }
  }
}
</script>

<style scoped>

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
  /*margin-top: 5px;*/
  width: 100%;
}

table {
  /*width: 1150px;*/
  /*margin-top: 5px;*/
  width: 100%;

}

.table tr:hover, .input_tables tr:hover {
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
  /*margin-left: 10px;*/
  /*border: 1px solid black;*/
  border-radius: 5px;
  width: 70%;
  height: 400px;
  box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.38);

}

.input * {
  margin-top: 3px;
  /*border: 1px solid black;*/
}

/*.input_table{*/
/*  border: 1px solid black;*/
/*  */
/*}*/
.input_table {
  border: 1px solid rgba(0, 0, 0, 0.38);
  overflow: hidden;
  height: 250px;
  width: 50%;
  overflow-y: scroll;
  margin-left: 3px;
  border-radius: 3px;
}

.input_buttns {
  width: 50%;
  margin-left: 3px;
  /*border: 1px solid black;*/
}

.input_buttns_top {
  border: 1px solid rgba(0, 0, 0, 0.38);
  border-radius: 3px;
  padding: 3px;
  margin-right: 3px;
}
.input_bottom{
  margin-top: 3px;
  /*border: 1px solid rgba(0, 0, 0, 0.38);*/
}
.input_tables {
  display: flex;
  /*border: 1px solid rgba(0, 0, 0, 0.38);*/

}

.input_name, .input_inn, .input_address {
  /*width: 275px;*/
  height: 30px;
  width: 300px;
  border: none;
  background: rgba(211, 214, 218, 0.63);
}


.input_buttn_save:hover {
  background: rgba(25, 178, 25, 0.63);
}

.input_buttn_exit, .del {
  background-color: rgba(255, 0, 0, 0.4);
  border: none;
  border-radius: 5px;
}

.input_buttn_exit:hover, .del:hover, .input_buttn_del:hover {
  color: white;
  background-color: rgba(255, 0, 0, 0.8);
}

.parent_input {
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  overflow: hidden;
  scroll-x: none;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(64, 72, 100, 0.55);;
}

.input_exit_div {
  width: 100%;
  display: flex;
  justify-content: flex-end;
  margin-right: 20px;
}

.input_save_div {
  width: 100%;
  display: flex;
  justify-content: center;
  /*border: 1px solid rgba(0, 0, 0, 0.38);*/

}

.input_buttn_exit {
  width: 50px;
  height: 20px;
}

.input_buttn_save {
  background: rgba(23, 73, 196, 0.83);
  border: none;
  border-radius: 3px;
  padding: 5px;
  color: white;
  width: 98%;
  height: 30px;
}

.input_buttn_add {
  background: white;
  border: 1px solid rgba(136, 137, 138, 0.4);
  border-radius: 5px;
  width: 70px;
  height: 30px;
}

.input_buttn_add:hover {
  background: #ececec;
}
.input_buttn_del{
  background: white;
  border: 1px solid rgba(255, 0, 0, 0.4);
  border-radius: 3px;
  width: 100%;
}
.create_btn {
  margin-top: 5px;
  background: rgba(23, 73, 196, 0.83);
  border: none;
  border-radius: 3px;
  padding: 5px;
  color: white;
  width: 100px;
  height: 30px;
}

* {
  font-family: "Trebuchet MS";
}
</style>