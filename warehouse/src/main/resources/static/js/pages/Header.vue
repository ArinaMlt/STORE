<template>
  <div class="headerMain">
    <header class="head">
      <nav>
        <a v-bind:class="{ invoiceActive: invoiceActive }" href="invoices">Приход</a>
        <a v-bind:class="{ checkActive: checkActive }" href="checks">Расход</a>
        <a v-bind:class="{ contragentActive: contragentActive }" href="contragents">Контрагенты</a>
        <a v-bind:class="{ paymentActive: paymentActive }" href="payments">Оплата</a>

        <ul class="mmenuu">
          <li><a v-bind:class="{ reportActive: reportActive }"  href="report">Отчет</a>
            <ul class="ssubmenuu">
              <li><a href="kontragentReport">Контрагенты</a></li>
<!--              <li><a href=#>Расходы</a></li>-->
<!--              <li><a href=#>Приходы</a></li>-->
              <li><a href="prices">Товары</a></li>
            </ul>
          </li>
        </ul>

      </nav>
    </header>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      invoiceActive: false,
      checkActive: false,
      contragentActive: false,
      paymentActive: false,
      reportActive: false,
    }
  },
  created() {
    // console.log(this.$route.path)

    if (this.$route.path == '/invoices') {
      this.invoiceActive = true
    }
    if (this.$route.path == '/checks') {
      this.checkActive = true;
    }
    if (this.$route.path == '/contragents') {
      this.contragentActive = true;
    }
    if (this.$route.path == '/payments') {
      this.paymentActive = true;
    }
    if (this.$route.path == '/report') {
      this.reportActive = true;
    }


    }
  }
</script>

<style scoped>
.invoiceActive, .invoiceActive:hover, .checkActive, .contragentActive, .paymentActive, .reportActive {
  text-decoration: underline;
  text-decoration-thickness: 5px;
  text-underline-offset: 20px;

}

header {
  /*font-family: "Trebuchet MS";*/
  /*background: #637497;*/
  background: rgba(20, 36, 72, 0.78);
  /*background: rgba(21, 56, 75, 0.94);*/
  color: #fff;
  width: 100%;
  /*border-radius: 10px;*/
}

a {
  color: #fff;
  text-decoration: none;
}

header nav {
  padding: 10px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  /*border: 1px solid #5e5c5c;*/
  border-radius: 10px;

  margin: 5px;
}

nav a {
  /*border: 1px solid black;*/
  padding: 5px;
  margin-left: 5px;
}

nav a:hover {
  text-decoration: underline;
  /*color: #55678b;*/
  /*background: #ffffff;*/
}

.headerMain {
  position: relative;
  display: flex;
  align-content: center;
  width: 100%;
  /*border: 1px solid black;*/
}


ul {
  /* Убираю маркеры у списка*/
  list-style: none;
  /* Делаю элементы блочными. */
  display: block;
  /* Убираю отступы. */
  margin: 0px;
  /* Убираю еще отступы! */
  padding: 0px;
}

ul:after {
  /* Делаю элементы блочными. */
  display: block;
  /* Убираю выравнивание. */
  float: none;
  content: ' ';
  clear: both;
}

ul.mmenuu > li {
  /* Задаю выравнивание и позиционирование. */
  float: left;
  /* Считаем координаты относительно исходного места*/
  position: relative;
}

ul.mmenuu > li > a {
  /* Делаю элементы блочными: */
  display: block;
  /* Задаю белый цвет. */
  color: #fff;
  /* Задаю отступ 10px. */
  padding: 10px;
  /* Убираю форматирование*/
  text-decoration: none;
  /* Задаю цвет. */
  /*background-color:  #637497;*/
  /*background: rgba(20, 36, 72, 0.78);*/

}

ul.mmenuu > li > a:hover {
  /* Задаю цвет при наведении. */
  text-decoration: underline;
  /*background-color:#a5b8e0;*/
  /*background-color:rgba(20, 36, 72, 0.78);*/
}

ul.ssubmenuu {
  position: absolute;
  width: 150px;
  top: 37px;
  left: 0px;
  /* Делаю субменю скрытыми. */
  display: none;
  /* Цвет — белый. */
  background-color: white;
  /*background-color:rgba(20, 36, 72, 0.78);*/
  /*border: 1px solid black;*/
}

ul.ssubmenuu > li {
  /* Блочное расположение элементов*/
  display: block;

}

ul.ssubmenuu > li > a {
  /* Делаю элементы блочными. */
  display: block;
  /* Убираю форматирование*/
  text-decoration: none;
  /* Задаю отступ. */
  padding: 10px;
  /* Задаю цвет. */
  color: #ffffff;
  /* Еще цвет. */
  /*background-color:  #637497;*/
  background-color: rgba(20, 36, 72, 0.78);
}

ul.ssubmenuu > li > a:hover {
  /* Цвет бэкграунда при наведении. */
  /*background-color: #a5b8e0;*/
  /*background-color: rgba(20, 36, 72, 0.78);*/
  /* Задаю подчеркивание*/
  text-decoration: underline;
}

ul.mmenuu > li:hover > ul.ssubmenuu {
  /* Делаю элементы блочными. */
  display: block;
}
</style>