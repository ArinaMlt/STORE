<template>
  <div class="headerMain">
  <header class="head">
    <nav>
      <a href="#">Приход</a>
      <a href="test">Расход</a>
      <a href="contragentDemo">Контрагенты</a>
      <a href="report">Отчет</a>
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
</style>