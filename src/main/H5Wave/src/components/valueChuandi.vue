<template>
    <div class="child">
        <label>这是子组件</label><br/>
        <div class="toparent">
            <label>这是往父组件传值</label>
            <input type="text" v-model="testModel" />
            <input type="text" v-model="testModel2" />
            <input type="button" value="向父组件传值" v-on:click="valueToParent"/>
        </div>
    
    <div class="fromparent">
        <label>这是接收从父组件传过来的值</label>
        <input type="text" v-model="valueFromParent" />
    </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      testModel: '',
      testModel2: ''
    }
  },
  props: [ 'valueFromParent' ],
  methods: {
    valueToParent: function () {
      this.$emit('SearchData', [ this.testModel, this.testModel2 ])     // SearchData为父组件的一个名字
    }
  }
}
</script>

<style>
  .child{
      width:300px;
      height: 300px;
      border: 1px solid red;
  }
  .toparent{
      width: 200px;
      height: 150px;
      border: 1px solid blue;
  }
  .fromparent{
      width:200px;
      height: 70px;
      margin-top: 10px;
      border: 1px solid green;
  }
</style>
