<template>
  <div id="main">
    <Header></Header>


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


    <div class="table">

      <div class="column">
        <div class="column_name">
          <p class="id">id</p>
          <p class="name">Наименование</p>
          <p class="amount">Количество</p>
          <p class="amount">Сумма</p>
          <p class="price">Цена</p>
          <p class="data">Дата</p>
        </div>

        <InvoiceRow v-for="message in messages"
                    :message="message"
                    :key="message.id"
                    :edit-method="editMethod">
        </InvoiceRow>

      </div>

    </div>
    <div class="footer">
      Total List: {{ messages.length }}
    </div>


    <!--    FORM-->
    <div v-if="show" id="inputForm">
      <div id="inputFormWhite">


        <div class="from_header_btn">
          <input
              class="exit"
              value="X"
              type="button"
              @click="exitForm">
          <input
              id="delete_method"
              v-if="del"
              value="Удалить"
              type="button"
              @click="delMethod">

        </div>

        <div class="headerInput">

          <select class="form_select" name="contragent" v-model="contragent">
            <option disabled value="">Выберите контрагента</option>
            <option v-for="contragent in contragents">
              {{ contragent.name }}
            </option>
            <option>+ Добавить</option>
          </select>


          <p>Кол-во</p>
          <input class="form_input" type="text" placeholder="Amount" v-model="totalAmount">
          <p>Цена</p>
          <input class="form_input" type="text" placeholder="Price" v-model="totalPrice">
          <p>Сумма</p>
          <input class="form_input" type="text" placeholder="Value" v-model="totalValue">

          <input class="form_input" type="date" placeholder="Input date" v-model="date">
          <input class="form_input" type="text" placeholder="ProductId" v-model="productId" readonly>
          <input class="form_input" type="text" placeholder="id" v-model="id" readonly>

          %
          <select class="form_select" name="percent" v-model="percent">
            <option>20</option>
            <option>12</option>
            <option>15</option>
            <option>25</option>
            <option>50</option>
            <option>0</option>
          </select>


<!--          &lt;!&ndash;        Выбор продуктов по контрагенту&ndash;&gt;
          <select class="form_select" name="product" v-model="product">
            <option v-for="product in productsContr">
              {{ product.productName }}
            </option>
          </select>-->

        </div>


        <div class="table_input">


          <div class="table_input_nomenclature">
            <table>
              <tr>
                <th>id</th>
                <th>Наименование</th>
                <th>Категория</th>
              </tr>
              <tr v-for="nomenclature in nomenclatures" @click="nomenclatureAdd(nomenclature)">
                <td>{{ nomenclature.id }}</td>
                <td>{{ nomenclature.name }}</td>
                <td>{{ nomenclature.category }}</td>
              </tr>
<!--              <tr><a href="/contragents">Добавить</a></tr>-->
            </table>
          </div>

          <div class="table_input_products">
                    <div class="bodyInput">
                      <input class="form_input" type="text" placeholder="Имя" v-model="productName">

                      <input class="form_input" type="text" placeholder="Кол-во" v-model="productAmount">
                      <input class="form_input" type="text" placeholder="Стоимость" v-model="productPrice">
                      <input id="add" value="Добавить" type="button" @click="add">
                      <input id="del" value="X" type="button" @click="delProductForm">
                    </div>

                    <div class="productsForm">

                      <div class="productsForm_prod" v-for="product in products">
                        <Product
                            :product="product"
                            :key="product.id"
                            :edit-method-product="editMethodProduct"
                            :del-method-product="delMethodProduct"></Product>
                      </div>

                      <!--          <Products :products="products"></Products>-->

                      <!--          <div class="products_column" v-for="product in products">
                      &lt;!&ndash;            <div>{{ product.id }}</div>&ndash;&gt;
                                  <div>{{ product.productName }}</div>
                                  <div>{{ product.productAmount }}</div>
                                  <div>{{ product.productPrice }}</div>
                                </div>-->

                    </div>
          </div>

        </div>

        <div>List: {{ products.length }}</div>

        <input id="form_save" value="Сохранить" type="button" @click="save">

      </div>


    </div>


  </div>
</template>

<script>
import Header from "./Header.vue"
import InvoiceRow from "../components/invoice/InvoiceRow.vue";
import Products from "../components/Products.vue";
import Product from "./Product.vue";

export default {
  name: "Invoice",
  components: {Product, Products, InvoiceRow, Header},
  data() {
    return {
      message: null,
      messages: [],
      contragents: [],
      contragent: '',
      totalAmount: 0,
      totalPrice: 0,
      totalValue: 0,
      date: new Date().toISOString().substr(0, 10),
      id: '',
      productName: '',
      productAmount: '',
      productPrice: '',
      productValue: '',
      productId: '',
      products: [],
      Amount: '',
      percent: 20,
      show: false,
      del: false,
      // contragentBool: true,
      // invoices: [],
      productsContr: [],
      product: '',
      filter: false,
      datefilter1: new Date().toISOString().substr(0, 10),
      datefilter2: new Date().toISOString().substr(0, 10),
      nomenclatures: [],
      nomenclature: '',
      nomenclatureId: '',
      nomenclatureName: '',
      nomenclatureCategory: '',
      // show: true
    }
  },
  created() {
    this.$resource('/invoice{/id}').get().then(result =>
        result.json().then(data => {
              console.log(data)
              data.forEach(message => this.messages.push(message))
            }
        )
    )
    this.$resource('/contragent{/id}').get().then(result =>
        result.json().then(data =>
            // console.log(data)
            data.forEach(message => this.contragents.push(message))
        )
    )

    /*    this.Amount = sumAmount()

      function sumAmount(){
        for (let i=0; i<messages.length; i++){
          console.log(messages[i])
        }
        return this.amount= messages[2]
      }*/
  },
  watch: {
    id() {
      if (this.id) {
        this.del = true
        // this.contragentBool = false
      } else {
        this.del = false
        // this.contragentBool = true
      }
    },
    contragent() {
      if ((!this.id) && (this.show)) {
        if (this.contragent == '+ Добавить') {
          console.log(this.contragent)
          this.$router.push('/contragents');
        } else {

          const contrWatch = this.contragents.find(item => item.name === this.contragent)
          console.log(this.contragent)
          this.nomenclatures = []
          this.productsContr = ''
          contrWatch.nomenclatures.forEach(message => this.nomenclatures.push(message))
          // this.nomenclatures.push(contrWatch.nomenclatures)
          // var nomenclature = this.contragents.find(item => item.id == )
          /*this.$resource('/invoice/byContragent').save({}, contragent).then(result =>
              result.json().then(data => {
                (data.content).forEach(message => this.invoices.push(message))
                var products = this.invoices[0]
                this.productsContr = products.products
                console.log(this.productsContr)
              })
          )*/

        }
      }

    },
    product() {
      this.productName = this.product
    }
  },
  methods: {
    input() {
      this.show = true;
    },
    exitForm() {
      this.show = false,
          this.contragent = '',
          this.totalAmount = 0,
          this.totalPrice = 0,
          this.totalValue = 0,
          this.date = new Date().toISOString().substr(0, 10),
          this.products = [],
          this.id = '',

          this.productId = '',
          this.productPrice = '',
          this.productAmount = '',
          this.productName = '',

          this.nomenclatures = [],
          this.nomenclatureId = '',
          this.nomenclatureName = '',
          this.nomenclatureCategory = ''
    },
    add() {
      this.productValue = Math.ceil(Number(this.productPrice) * ((Number(this.percent) + 100) / 100))

      var product = {
        productName: this.productName,
        productAmount: this.productAmount,
        productPrice: this.productPrice,
        productValue: this.productValue,
        invoice: {
          id: this.id
        }
      }

      this.totalAmount = Number(this.totalAmount) + Number(this.productAmount)
      this.totalValue = (Number(this.productValue) * Number(this.productAmount)) + Number(this.totalValue)
      this.totalPrice = (Number(this.productPrice) * Number(this.productAmount)) + Number(this.totalPrice)

      //already exists
      if (this.productId) {
        var product2 = {
          id: this.productId,
          productName: this.productName,
          productAmount: this.productAmount,
          productPrice: this.productPrice,
          productValue: Math.ceil(Number(this.productPrice) * Number((this.percent + 100) / 100)),
          invoice: {
            id: this.id
          }
        }
        this.$resource('/product{/id}').update({id: this.productId}, product2).then(result =>
            result.json().then(data => {
              const index = this.products.findIndex(item => item.id === this.id)
              this.products.splice(index, 1, data);
            }))

      } else {

        if (this.id) {
          console.log("already exists")
          this.$resource('/product{/id}').save({}, product).then(result =>
              result.json().then(data => {
                this.products.push(data);

              })
          )
        } else {
          this.products.push(product)
        }
      }


      // console.log(product)
      // console.log(this.id)

      /* if (this.productId) {
         var product2 = {
           id: this.productId,
           productName: this.productName,
           productAmount: this.productAmount,
           productPrice: this.productPrice,
           invoice: {
             id: this.id
           }
         }
         this.$resource('/product{/id}').update({id: this.productId}, product2).then(result =>
             result.json().then(data => {
               const index = this.products.findIndex(item => item.id === this.id)
               this.products.splice(index, 1, data);
             }))
       } else {
         console.log('new product')
         this.$resource('/product{/id}').save({}, product).then(result =>
             result.json().then(data => {
               this.products.push(data);

               this.productName = '',
               this.productAmount = '',
               this.productPrice = '',
               this.productId = ''
             })
         )
       }
 */
      this.productName = '',
          this.productAmount = '',
          this.productPrice = '',
          this.productValue = '',
          this.productId = ''
    },
    delProductForm() {
      this.productName = '';
      this.productAmount = '';
      this.productPrice = '';
      this.productId = '';
    },
    save() {
      const contrSave = this.contragents.find(item => item.name === this.contragent)

      var message = {
        contragent: {
          id: contrSave.id
        },
        totalAmount: this.totalAmount,
        totalPrice: this.totalPrice,
        totalValue: Number(this.totalValue),
        date: this.date,
        pay: false
        // products: this.products
      };

      //редактирование уже имеющейся записи
      if (this.id) {

        var message2 = {
          id: this.id,
          contragent: {
            id: contrSave.id
          },
          totalAmount: this.totalAmount,
          totalPrice: this.totalPrice,
          totalValue: Number(this.totalValue),
          date: this.date,
          pay: false
          // products: null
        };

        console.log(this.id)
        console.log(this.totalValue)


        this.$resource('/invoice{/id}').update({id: this.id}, message2).then(result =>
            result.json().then(data => {
              const index = this.messages.findIndex(item => item.id === this.id)
              console.log(index)
              data.contragent.name = this.contragent
              this.messages.splice(index, 1, data);

            }))


      } else {

        /* for(let i=0; i< this.products.length; i++) {
           var product = {
             productName: this.products[i].productName,
             productAmount: this.products[i].productAmount,
             productPrice: this.products[i].productPrice,
             productValue: this.products[i].productValue,
           }
           console.log(this.products[i])
         }*/


        this.$resource('/invoice{/id}').save({}, message).then(result =>
            result.json().then(data => {

              // this.messages.push(data);

              this.totalAmount = '',
                  this.totalPrice = '',
                  this.date = ''
              // this.products = []

              for (let i = 0; i < this.products.length; i++) {
                var product = {
                  productName: this.products[i].productName,
                  productAmount: this.products[i].productAmount,
                  productPrice: this.products[i].productPrice,
                  productValue: this.products[i].productValue,
                  invoice: {
                    id: data.id
                  }
                }

                this.$resource('/product{/id}').save({}, product).then(result =>
                    result.json().then(data => {
                      // this.products.push(data);

                      this.productName = '',
                          this.productAmount = '',
                          this.productPrice = '',
                          this.productId = ''
                    })
                )

                data.products = this.products
                data.contragent.name = this.contragent
                this.contragent = ''
                this.messages.push(data);

              }
            })
        )

      }

      this.show = false;

    },
    edit() {
      this.show = true;
    },
    editMethod(message) {
      this.show = true;
      this.message = message;
      this.contragent = message.contragent.name;
      this.totalPrice = message.totalPrice;
      this.totalAmount = message.totalAmount;
      this.totalValue = message.totalValue;
      this.date = message.date;
      this.id = message.id;
      this.products = message.products;

      const contrWatch = this.contragents.find(item => item.name === this.contragent)
      this.nomenclatures = []
      this.productsContr = ''
      contrWatch.nomenclatures.forEach(message => this.nomenclatures.push(message))

    },
    delMethod() {
      const message = this.messages.find(item => item.id === this.id)
      this.$resource('/invoice{/id}').remove({id: this.id}).then(result => {
        if (result.ok) {

          this.messages.splice(this.messages.indexOf(message), 1)
        }
      })
      this.exitForm()
    },
    editMethodProduct(product) {
      this.productName = product.productName;
      this.productAmount = product.productAmount;
      this.productPrice = product.productPrice;
      this.productId = product.id;


      if (this.id) {
        this.totalAmount = Number(this.totalAmount) - Number(product.productAmount)
        this.totalPrice = Number(this.totalPrice) - (Number(product.productPrice) * Number(product.productAmount))
        this.totalValue = Number(this.totalValue) - (Number(product.productValue) * Number(product.productAmount))
      }

    },
    delMethodProduct(product) {

      this.totalAmount = Number(this.totalAmount) - Number(product.productAmount)
      this.totalPrice = Number(this.totalPrice) - (Number(product.productPrice) * Number(product.productAmount))
      this.totalValue = Number(this.totalValue) - (Number(product.productValue) * Number(product.productAmount))

      if (product.id) {
        console.log(product.id)
        this.$resource('/product{/id}').remove({id: product.id}).then(result => {
          if (result.ok) {
            this.products.splice(this.products.indexOf(product), 1)
          }
        })
      } else {
        console.log(product.id)
        this.products.splice(this.products.indexOf(product), 1)
      }
    },
    filterOpen() {
      this.filter = true;
    },
    exitFilter() {
      this.filter = false;
    },
    searchingFilter() {
      var message = (this.datefilter1).toString() + '!' + (this.datefilter2).toString();

      this.$resource('/invoice/byDate').save({}, message).then(result =>
          result.json().then(data => {
            this.messages = []
            data.forEach(message => this.messages.push(message))
            // console.log(data)
          })
      )
    },
    nomenclatureAdd(nomenclature){
      this.nomenclatureId = nomenclature.id,
      this.nomenclatureName = nomenclature.name,
      this.nomenclatureCategory = nomenclature.category,
      this.productName = nomenclature.name
    }
  },
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

.filter_exit_div {
  display: flex;
  justify-content: flex-end;
}

.exit_filter {
  width: 40px;
}

#main {
  overflow: hidden;
  scroll-y: none;
}

#main * {
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
  /*border: 1px solid black;*/
  padding: 5px;
  color: rgba(49, 48, 48, 0.6);
}


.column_data_Price * {
  /*border: 1px solid black;*/
  padding: 5px;
  margin-right: 5px;
}

.name {
  width: 500px;
  overflow: hidden;

}

.id {
  width: 30px;
}

.amount, .price {
  width: 110px;
}

.data {
  width: 120px;
}


.column_data_product * {
  /*border: 1px solid black;*/
  padding: 5px;
  margin-right: 5px;
}


/*table {*/
/*  border-collapse: collapse;*/
/*  width: 1333px;*/
/*    border: 1px solid red;*/
/*}*/

.table {
  border: 1px solid black;
  border-bottom: 1px solid rgba(58, 56, 56, 0.48);
  border-top: 1px solid rgba(58, 56, 56, 0.48);
  border-radius: 3px;
  padding: 2px;
  /*width: 1300px;*/
  width: 100%;
  height: 450px;
  overflow: hidden;
  overflow-y: scroll;

}


/*th, td {*/
/*  border: 1px solid black;*/
/*}*/


#inputForm {
  /*border: 1px solid black;*/
  position: absolute;
  top: 0px;
  left: 0px;
  /*padding: 50px;*/
  /*width: 1335px;*/
  width: 100%;
  background: rgba(64, 72, 100, 0.55);
  height: 620px;
  /*height:100%;*/
  overflow: hidden;
  scroll-x: none;
  /*height: 100%;*/
  display: flex;
  justify-content: center;
  align-items: center;
  /*border-radius: 10px;*/
  /*align-items: center;*/
}


#inputFormWhite {
  background: white;
  box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.38);

  /*max-height: 550px;*/
  /*height: 530px;*/
  height: 80%;
  /*width: 1000px;*/
  width: 70%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  padding: 10px;
  border-radius: 5px;
  /*margin-top: 10px;*/
}

#input {
  /*padding: 10px;*/
  /*margin: 10px;*/
}

.headerInput {
  display: inline-flex;
  flex-wrap: wrap;
  /*border: 1px solid black;*/

}

.headerInput * {
  /*border: 1px solid #bebebe;*/
  padding: 2px;
  margin: 2px;
  /*border-radius: 5px;*/
}

.table_input {
  display: flex;
  /*border: 1px solid red;*/
  height: 50%;
}

.table_input_nomenclature {
  width: 30%;
  border: 1px solid rgba(0, 0, 0, 0.38);
  overflow: hidden;
  height: 250px;
  overflow-y: scroll;
  margin-left: 3px;
  border-radius: 3px;
  height: 100%;
}
.table_input_nomenclature table{
  width: 100%;
}

.table_input_products {
  width: 70%;
  height: 100%;
  /*border: 1px solid black;*/
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
.table_input_nomenclature tr:hover{
  background: rgba(189, 200, 232, 0.37);
}

.bodyInput {
  border: 1px solid rgba(58, 56, 56, 0.48);
  border-bottom: none;
  padding: 3px;
  height: 10%;
  /*border: 1px solid black;*/
}

.productsForm {
  border: 1px solid rgba(58, 56, 56, 0.48);
  /*padding: 10px;*/
  /*height: 250px;*/
  height: 85%;

  display: flex;
  flex-direction: column;
  overflow: hidden;
  overflow-y: scroll;
}



.products_column {
  display: inline-flex;
  /*border: 1px solid black;*/
}

.create_btn {
  width: 100px;
  height: 30px;
  /*box-shadow: 0px 5px 10px 2px rgba(34, 60, 80, 0.11);*/
}

#form_save {
  height: 30px;
}

#del {
  width: 30px;
}

.create_btn, #form_save {
  background: rgba(23, 73, 196, 0.83);
  border: none;
  border-radius: 3px;
  padding: 5px;
  color: white;
}

.exit, .exit_filter, #add, #del, #delete_method, .filter_btn {
  background: rgba(175, 177, 182, 0.35);
  border: none;
  border-radius: 3px;
  padding: 5px;
}

.exit, .exit_filter {
  width: 30px;
  height: 30px;
}

.create_btn:hover, #add:hover, #form_save:hover {
  background: rgba(25, 178, 25, 0.45);
}

.exit:hover, .exit_filter:hover, #del:hover, #delete_method:hover {
  background-color: rgba(255, 0, 0, 0.38);
}

.from_header_btn {
  display: flex;
  justify-content: space-between;
}

.form_select, .form_input {
  border-radius: 3px;
  border: 1px solid rgba(187, 183, 183, 0.43);
}

* {
  font-family: "Trebuchet MS";
  /*font-family: "Open Sans";*/
}
</style>