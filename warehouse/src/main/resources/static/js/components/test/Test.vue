<template>

  <div>
    <Header></Header>
    <div id="one">
      <p>just test component</p>
      <div class="table">
        <div class="headTable">
          <div>Контрагент</div>
          <div>Колличество</div>
          <div>Сумма</div>
          <div>Дата</div>
        </div> <!--ряд с ячейками заголовков-->
        <test-list :messages="messages"></test-list>
      </div>
    </div>
    <!--   <div><test-list :messages="messages"></test-list></div>-->
  </div>

</template>

<script>
import TestList from 'components/test/TestList.vue'
import Header from "../../pages/Header.vue";

export default {
  name: "test",
  components: {Header, TestList},
  data: function () {
    return {
      messages: [
        // {id: '1', name: 'one'}
      ]
    }
  }
}
</script>


<style scoped>
#one {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.headTable {
  display: flex;
  align-items: center;
  border: 1px solid black;
  color: red;
}

.table {
  border: 1px solid black;
  width: 1300px;
  height: 400px;
  overflow: hidden;
  /*text-align: center;*/
}
</style>