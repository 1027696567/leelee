webpackJsonp([14],{BPo0:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("lC5x"),r=a.n(n),l=a("J0Oq"),i=a.n(l),s=a("P9l9"),o={data:function(){return{tableData:[],currentRow:""}},methods:{setCurrent:function(t){this.$refs.singleTable.setCurrentRow(t)},handleCurrentChange:function(t){this.currentRow=t},getLogList:function(){var t=this;return i()(r.a.mark(function e(){var a;return r.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(s.c)();case 2:a=e.sent,t.tableData=a.data,console.log(a.data);case 5:case"end":return e.stop()}},e,t)}))()}},created:function(){this.getLogList()}},c={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"menu5_item2"},[e("el-main",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:this.tableData,"max-height":"750px",border:""},on:{"current-change":this.handleCurrentChange}},[e("el-table-column",{attrs:{type:"index",width:"50"}}),this._v(" "),e("el-table-column",{attrs:{prop:"user",label:"用户账号",width:"200"}}),this._v(" "),e("el-table-column",{attrs:{prop:"ip",label:"IP地址",width:"200"}}),this._v(" "),e("el-table-column",{attrs:{prop:"visitTime",label:"访问日期",width:"200"}}),this._v(" "),e("el-table-column",{attrs:{prop:"action",label:"动作",width:"200"}}),this._v(" "),e("el-table-column",{attrs:{prop:"time",label:"响应时间"}})],1)],1)],1)},staticRenderFns:[]};var u=a("C7Lr")(o,c,!1,function(t){a("VWVY")},null,null);e.default=u.exports},VWVY:function(t,e){}});
//# sourceMappingURL=14.4873daf061149cc71202.js.map