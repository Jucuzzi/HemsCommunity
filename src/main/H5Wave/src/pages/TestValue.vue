<template>
    <div>
        <ValueChuanZhi v-on:SearchData="SearchDataHome" :valueFromParent="valueToChild"></ValueChuanZhi>
        <div class="parent">
            <label>这是父组件</label><br/>
            <div class="fromChild">
                <label>这是获取子组件传过来的值</label>
                <input type="text" v-model="valueFromChile" />
            </div>
            
            <div class="ToChild">
                <label>这是传递值给子组件</label>
                <input type="text" v-model="valueToChild" />
            </div>
        </div>
        
    </div>
</template>

<script>
import ValueChuanZhi from './../components/valueChuandi.vue'

export default {
  data () {
    return {
      valueFromChile: '',
      valueToChild: '这是从父组件传过来的值'
    }
  },
  components: {
    ValueChuanZhi
  },
  methods: {
    SearchDataHome: function (data) {
      this.valueFromChile = ''          // 为了防止之前的值继续存在,根据需求此句可要可不要
      data.forEach(function (element) {
        this.valueFromChile += element
      }, this)
      console.log(data)
      console.log(this.valueFromChile)
    }
  },
  beforeCreate () {   // 加载页面前执行的函数
    alert('进来了')
  }
}
</script>

<style>
  .parent{
      width: 300px;
      height: 300px;
      border: 1px solid red;
      margin-top: 20px;
  }
  .fromChild{
      width: 200px;
      height: 100px;
      border: 1px solid pink;
  }
  .ToChild{
      width: 200px;
      height: 100px;
      border: 1px solid yellow;
      margin-top: 20px
  }
</style>
