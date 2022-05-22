<template>
  <div class="headerMain">
  <header class="head">
    <nav>
      <a href="invoices">Приход</a>
      <a href="checks">Расход</a>
      <a href="contragents">Контрагенты</a>

      <ul class="mmenuu">
        <li><a href="report">Отчет</a>
          <ul class="ssubmenuu">
            <li><a href="kontragentReport">Контрагенты</a></li>
            <li><a href=#>Расходы</a></li>
            <li><a href=#>Приходы</a></li>
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
  name: "Header"
}
</script>

<style scoped>

header{
  background: #637497;
  color: #fff;
  width: 100%;
}
a{
  color: #fff;
  text-decoration: none;
}

header nav {
 padding: 10px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
   border: 1px solid black;
   margin: 5px;
}
nav a{
  /*border: 1px solid black;*/
  padding: 10px;
  margin-left: 5px;
}
nav a:hover{
  text-decoration: underline;
  /*color: #55678b;*/
  /*background: #ffffff;*/
}
.headerMain{
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
  margin:0px;
  /* Убираю еще отступы! */
  padding:0px;
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
  background-color:  #637497;
}
ul.mmenuu > li > a:hover {
  /* Задаю цвет при наведении. */
  background-color:#a5b8e0;
}
ul.ssubmenuu {
  position: absolute;
  width: 240px;
  top: 37px;
  left:0px;
  /* Делаю субменю скрытыми. */
  display: none;
  /* Цвет — белый. */
  background-color: white;
  border: 1px solid black;
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
  background-color:  #637497;
}
ul.ssubmenuu > li > a:hover {
  /* Цвет бэкграунда при наведении. */
  background-color: #a5b8e0;
  /* Задаю подчеркивание*/
  text-decoration: underline;
}
ul.mmenuu > li:hover > ul.ssubmenuu {
  /* Делаю элементы блочными. */
  display: block;
}
</style>