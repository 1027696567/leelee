webpackJsonp([17],{lfLh:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=o("BcEq"),n={name:"v-navbar",data:function(){return{circleUrl:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",user:"",dialogVisible:!1}},methods:{handleCommand:function(e){var t=this;"c"===e&&this.$confirm("确认退出？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.logout(),t.$message({type:"success",message:"退出成功"})}).catch(function(){t.$message({type:"info",message:"取消退出"})})},logout:function(){this.$store.commit(s.c),sessionStorage.removeItem("routes"),sessionStorage.removeItem("user"),sessionStorage.removeItem("menuData"),sessionStorage.removeItem("itemId"),sessionStorage.removeItem("costItemNumber"),this.$router.push("/login")}},created:function(){this.user=sessionStorage.getItem("user")}},a={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"outPatientName"},[o("span",[e._v("房地产企业管理系统")]),e._v(" "),o("div",{staticClass:"block"},[o("el-dropdown",{on:{command:e.handleCommand}},[o("span",{staticClass:"el-dropdown-link-one"},[o("div",{staticClass:"avatar"},[o("el-avatar",{attrs:{src:"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80",size:"small"}})],1),e._v("\n          "+e._s(e.user)),o("i",{staticClass:"el-icon-arrow-down el-icon--right"})]),e._v(" "),o("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[o("el-dropdown-item",{attrs:{command:"a"}},[e._v("修改密码")]),e._v(" "),o("el-dropdown-item",{attrs:{command:"b"}},[e._v("基本信息")]),e._v(" "),o("el-dropdown-item",{attrs:{command:"c"}},[e._v("退出系统")])],1)],1),e._v(" "),o("el-dialog",{attrs:{title:"提示",visible:e.dialogVisible,width:"20%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[o("span",[e._v("确认要退出系统！")]),e._v(" "),o("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:e.logout}},[e._v("确 定")])],1)])],1)])},staticRenderFns:[]};var i=o("C7Lr")(n,a,!1,function(e){o("xlzI")},null,null);t.default=i.exports},xlzI:function(e,t){}});
//# sourceMappingURL=17.c863688be0c4ae0f6443.js.map