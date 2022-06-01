<template>
  <div id="main">
    <Header></Header>
    <div>
      <input id="input" value="Input" type="button" @click="input">
    </div>


    <div class="table">
      <!--      <table>
              <tr>
                <th>id</th>
                <th>name</th>
                <th>amount</th>
                <th>price</th>
                <th>date</th>
              </tr>
              <tr v-for="message in messages">
                <td>{{ message.id }}</td>
                <td id="name">{{ message.name }}</td>
                <td>{{ message.amount }}</td>
                <td>{{ message.price }}</td>
                <td>data</td>
              </tr>
            </table>-->

      <div class="column">
        <div class="column_name">
          <p class="id">id</p>
          <p class="name">name</p>
          <p class="amount">amount</p>
          <p class="price">price</p>
          <p class="data">date</p>
        </div>

        <InvoiceRow v-for="message in messages"
                    :message="message"
                    :key="message.id"
                    :edit-method="editMethod"
                    :del-method="delMethod">
        </InvoiceRow>
        <!--        <div class="column_data" v-for="message in messages">
                  <div class="column_data_Price">
                    <div class="id">{{ message.id }}</div>
                    <div class="name">{{ message.contragent}}</div>
                    <div class="amount">{{ message.totalAmount }}</div>
                    <div class="price">{{ message.totalPrice }}</div>
                    <div class="data">{{ message.date }}</div>
                    <input type="button" value="Edit" @click="edit"/>
                    <input type="button" value="X" @click="del"/>
                    &lt;!&ndash;            <div class="data">date</div>&ndash;&gt;
                  </div>

                  <div class="column_data_product" v-for="product in products">
                    <div class="column_data_product_ID">{{ product.id }}</div>
                    <div class="column_data_product_TEXT">{{ product.text }}</div>
                  </div>
                </div>-->
      </div>

    </div>
    <div class="footer">
      Total List: {{ messages.length }}
      <!--      Total Amount: {{Amount}}-->
    </div>

    <div v-if="show" id="inputForm">
      <div id="inputFormWhite">
        <div>
          <input id="exit" value="exit" type="button" @click="exitForm">
        </div>

        <div class="headerInput">
          <p>Контрагент</p>
          <select name="contragent" v-model="contragent">
            <option disabled value="">Выберите контрагента</option>
            <option v-for="contragent in contragents">
              {{ contragent.id }}
            </option>
          </select>

          <!--            <input type="text" placeholder="Input name" v-model="contragent">-->
          <!--            <input type="text" placeholder="Input name" v-model="contragent">-->

          <p>Кол-во</p>
          <input type="text" placeholder="Input amount" v-model="totalAmount">
          <p>Цена</p>
          <input type="text" placeholder="Input price" v-model="totalPrice">
          <p>Дата</p>
          <input type="date" placeholder="Input date" v-model="date">
          <input type="text" placeholder="ProductId" v-model="productId" readonly>
          <input type="text" placeholder="id" v-model="id" readonly>

          <!--          <select name="contragent" v-model="contragent">
                      <option disabled value="">Выберите контрагента</option>
                      <option v-for="contragent in contragents">
                        {{ contragent.id }}
                      </option>
                    </select>-->

        </div>

        <div class="bodyInput">
          <input type="text" placeholder="Имя" v-model="productName">
          <input type="text" placeholder="Кол-во" v-model="productAmount">
          <input type="text" placeholder="Стоимость" v-model="productPrice">
          <input id="add" value="Add" type="button" @click="add">
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

        <div>List: {{ products.length }}</div>

        <input id="btnForm" value="Input" type="button" @click="save">

      </div>


    </div>
  </div>
</template>

<script>
import Header from "./Header.vue"
import messages from "../api/messages";
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
      date: new Date().toISOString().substr(0, 10),
      id: '',
      productName: '',
      productAmount: '',
      productPrice: '',
      productValue: '',
      productId: '',
      products: [],
      Amount: '',
      show: false,
      // show: true
    }
  },
  created() {
    this.$resource('/invoice{/id}').get().then(result =>
        // console.log(result)
        result.json().then(data => {
              console.log(data)
              data.forEach(message => this.messages.push(message))
            }
        )
    )
    this.$resource('/contragent{/id}').get().then(result =>
        // console.log(result)
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
  methods: {
    input() {
      this.show = true;
    },
    exitForm() {
      this.show = false,
          this.contragent = '',
          this.totalAmount = 0,
          this.totalPrice = 0,
          this.date = new Date().toISOString().substr(0, 10),
          this.products = [],
          this.id = '',

          this.productId = '',
          this.productPrice = '',
          this.productAmount = '',
          this.productName = ''
    },
    add() {
      var product = {
        productName: this.productName,
        productAmount: this.productAmount,
        productPrice: this.productPrice,
        productValue: Number(this.productPrice) * Number(this.productAmount),
        invoice: {
          id: this.id
        }
      }

      this.totalAmount = Number(this.totalAmount) + Number(this.productAmount)
      this.totalPrice = (Number(this.productPrice) * Number(this.productAmount)) + Number(this.totalPrice)

      //already exists
      if (this.productId) {
        var product2 = {
          id: this.productId,
          productName: this.productName,
          productAmount: this.productAmount,
          productPrice: this.productPrice,
          productValue: Number(this.productPrice) * Number(this.productAmount),
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

        if(this.id){
          console.log("already exists")
          this.$resource('/product{/id}').save({}, product).then(result =>
              result.json().then(data => {
                this.products.push(data);

              })
          )
        }else {
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
          this.productValue ='',
          this.productId = ''
    },
    delProductForm() {
      this.productName = '';
      this.productAmount = '';
      this.productPrice = '';
      this.productId = '';
    },
    save() {
      var message = {
        contragent: {
          id: this.contragent
        },
        totalAmount: this.totalAmount,
        totalPrice: this.totalPrice,
        date: this.date,
        // products: this.products
      };

      //редактирование уже имеющейся записи
      if (this.id) {

        var message2 = {
          id: this.id,
          contragent: {
            id: this.contragent
          },
          totalAmount: this.totalAmount,
          totalPrice: this.totalPrice,
          date: this.date,
          // products: null
        };

        console.log(this.id)
        console.log(message2)


        this.$resource('/invoice{/id}').update({id: this.id}, message2).then(result =>
            result.json().then(data => {
              const index = this.messages.findIndex(item => item.id === this.id)
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

              this.contragent = '',
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
      this.contragent = message.contragent.id;
      this.totalPrice = message.totalPrice;
      this.totalAmount = message.totalAmount;
      this.date = message.date;
      this.id = message.id;
      this.products = message.products;

    },
    delMethod(message) {
      this.$resource('/invoice{/id}').remove({id: message.id}).then(result => {
        if (result.ok) {
          this.messages.splice(this.messages.indexOf(message), 1)
        }
      })
    },
    editMethodProduct(product) {
      this.productName = product.productName;
      this.productAmount = product.productAmount;
      this.productPrice = product.productPrice;
      this.productId = product.id;
    },
    delMethodProduct(product) {

      this.totalAmount = Number(this.totalAmount) - Number(product.productAmount)
      this.totalPrice = Number(this.totalPrice) - (Number(product.productPrice) * Number(product.productAmount))

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
    }
  },
}
</script>
<style scoped>
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
  border: 1px solid black;
  padding: 5px;
  margin-right: 5px;
}

.column_data {
  display: flex;
  flex-direction: column;
  /*border: 1px solid black;*/
}

.column_data_Price {
  background: #a4b3dc;
  display: inline-flex;
}

.column_data_Price * {
  /*border: 1px solid black;*/
  padding: 5px;
  margin-right: 5px;
}

.name {
  width: 700px;
}

.id {
  width: 20px;
}

.amount, .price {
  width: 110px;
}

.data {
  width: 120px;
}

.column_data_product {
  display: inline-flex;
  margin-left: 35px;
  border: 1px solid black;
  margin-top: 2px;
  border-radius: 5px;
}

.column_data_product * {
  /*border: 1px solid black;*/
  padding: 5px;
  margin-right: 5px;
}

.column_data_product_ID {
  width: 30px;
}

/*.id {*/
/*  padding: 5px;*/
/*  border: 1px solid black;*/
/*}*/

table {
  border-collapse: collapse;
  width: 1333px;
}

.table {
  border: 1px solid black;
  padding: 2px;
  /*width: 1300px;*/
  width: 100%;
  height: 450px;
  overflow: hidden;
  overflow-y: scroll;

}


th, td {
  border: 1px solid black;
}

/*#form {*/
/*  border: 1px solid black;*/
/*  display: inline-flex;*/
/*}*/

#inputForm {
  /*border: 1px solid black;*/
  position: absolute;
  top: 5px;
  /*padding: 50px;*/
  /*width: 1330px;*/
  width: 100%;
  background: rgba(64, 72, 100, 0.75);
  /*height: 457px;*/
  overflow: hidden;
  height: 100%;
  display: flex;
  justify-content: center;
  /*align-items: center;*/
}

#inputFormWhite * {
  margin-bottom: 5px;
}

#inputFormWhite {
  background: white;
  max-height: 550px;
  width: 1000px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  padding: 10px;
  border-radius: 10px;
  margin-top: 10px;
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
  border: 1px solid #bebebe;
  padding: 2px;
  margin: 2px;
}

.bodyInput {
  border: 1px solid black;
  padding: 10px;
}

.productsForm {
  border: 1px solid black;
  /*padding: 10px;*/
  max-height: 300px;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  overflow-y: scroll;
}

.productsForm_prod {
  /*border: 1px solid black;*/
  height: 15px;
  margin: 0;
  padding: 0;
}

.products_column {
  display: inline-flex;
  /*border: 1px solid black;*/
}

/*.products_column * {*/
/*  padding-left: 10px;*/
/*}*/
</style>