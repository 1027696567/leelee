webpackJsonp([7],{"4+AD":function(t,e,r){var a=r("8leu");a(a.S,"Number",{isInteger:r("YGy9")})},"4Xi4":function(t,e,r){t.exports={default:r("6zNI"),__esModule:!0}},"6zNI":function(t,e,r){r("4+AD"),t.exports=r("Rv9F").Number.isInteger},HbIV:function(t,e){},YGy9:function(t,e,r){var a=r("PUvy"),s=Math.floor;t.exports=function(t){return!a(t)&&isFinite(t)&&s(t)===t}},kohf:function(t,e,r){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=r("lC5x"),s=r.n(a),i=r("J0Oq"),l=r.n(i),o=r("4Xi4"),n=r.n(o),m=r("0pcc"),u={data:function(){var t=this;return{dialogVisible:!1,form:{status:null},submitForm:{taskReportTime:null,dutyUsername:null,taskAmount:null,remarks:null},rules:{taskReportTime:[{required:!0,message:"请选择汇报时间",trigger:"change"}],dutyUsername:[{required:!0,message:"请输入责任人",trigger:"change"}],taskAmount:[{validator:function(e,r,a){null===r&&a(new Error("请输入本次工作量")),setTimeout(function(){n()(r)?r>100-t.form.taskRate?a(new Error("数值过大")):a():a(new Error("请输入数字"))},500)},trigger:"blur"}]}}},methods:{init:function(t){this.dialogVisible=!0,this.form=t,this.submitForm.taskReportUsername=sessionStorage.getItem("user"),this.submitForm.taskReportTime=new Date,this.submitForm.taskNumber=this.form.taskNumber},onSubmit:function(t){var e,r=this;this.$refs[t].validate((e=l()(s.a.mark(function e(a){return s.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(!a){e.next=5;break}return e.next=3,Object(m.b)(r.submitForm).then(function(e){r.form.taskRate+=r.submitForm.taskAmount,100===r.form.taskRate&&(r.form.status=1),r.updateTask(),r.$message.success("新建成功"),r.dialogVisible=!1,r.$refs[t].resetFields(),r.parent()});case 3:e.next=6;break;case 5:return e.abrupt("return",!1);case 6:case"end":return e.stop()}},e,r)})),function(t){return e.apply(this,arguments)}))},updateTask:function(){var t=this;return l()(s.a.mark(function e(){return s.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(m.f)(t.form);case 2:case"end":return e.stop()}},e,t)}))()},parent:function(){this.$parent.selectAll()},resetForm:function(t){this.$refs[t].resetFields(),this.dialogVisible=!1},closeDiv:function(t){this.$refs.form.resetFields(),t()}},created:function(){}},c={render:function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"addTaskReportInfo"},[r("el-dialog",{attrs:{title:"进度汇报",visible:t.dialogVisible,width:"700px","before-close":t.closeDiv},on:{"update:visible":function(e){t.dialogVisible=e}}},[r("el-form",{ref:"form",attrs:{"label-position":"right",rules:t.rules,model:t.submitForm,"label-width":"80px"}},[r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"任务编号：",prop:"taskNumber"}},[r("el-input",{attrs:{readOnly:""},model:{value:t.form.taskNumber,callback:function(e){t.$set(t.form,"taskNumber",e)},expression:"form.taskNumber"}})],1)],1),t._v(" "),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"任务名称：",prop:"taskName"}},[r("el-input",{attrs:{readOnly:""},model:{value:t.form.taskName,callback:function(e){t.$set(t.form,"taskName",e)},expression:"form.taskName"}})],1)],1)],1),t._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"是否完成：",prop:"status"}},[r("el-radio",{attrs:{label:1,disabled:""},model:{value:t.form.status,callback:function(e){t.$set(t.form,"status",e)},expression:"form.status"}},[t._v("是")]),t._v(" "),r("el-radio",{attrs:{label:0,disabled:""},model:{value:t.form.status,callback:function(e){t.$set(t.form,"status",e)},expression:"form.status"}},[t._v("否")])],1)],1),t._v(" "),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"完成进度：",prop:"taskRate"}},[r("el-input",{attrs:{readOnly:""},model:{value:t.form.taskRate,callback:function(e){t.$set(t.form,"taskRate",e)},expression:"form.taskRate"}})],1)],1)],1),t._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"计划开始：",prop:"planBeginTime"}},[r("el-date-picker",{staticStyle:{width:"225px"},attrs:{type:"datetime",placeholder:"请选择计划开始时间","default-time":"12:00:00",disabled:""},model:{value:t.form.planBeginTime,callback:function(e){t.$set(t.form,"planBeginTime",e)},expression:"form.planBeginTime"}})],1)],1),t._v(" "),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"计划完成：",prop:"planEndTime"}},[r("el-date-picker",{staticStyle:{width:"225px"},attrs:{type:"datetime",placeholder:"请选择计划完成时间","default-time":"12:00:00",disabled:""},model:{value:t.form.planEndTime,callback:function(e){t.$set(t.form,"planEndTime",e)},expression:"form.planEndTime"}})],1)],1)],1),t._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"实际开始：",prop:"realityBeginTime"}},[r("el-date-picker",{staticStyle:{width:"225px"},attrs:{type:"datetime",placeholder:"请选择计划开始时间","default-time":"12:00:00"},model:{value:t.form.realityBeginTime,callback:function(e){t.$set(t.form,"realityBeginTime",e)},expression:"form.realityBeginTime"}})],1)],1),t._v(" "),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"实际完成：",prop:"realityEndTime"}},[r("el-date-picker",{staticStyle:{width:"225px"},attrs:{type:"datetime",placeholder:"请选择计划完成时间","default-time":"12:00:00"},model:{value:t.form.realityEndTime,callback:function(e){t.$set(t.form,"realityEndTime",e)},expression:"form.realityEndTime"}})],1)],1)],1),t._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"汇报人：",prop:"taskReportUsername"}},[r("el-input",{attrs:{readOnly:""},model:{value:t.submitForm.taskReportUsername,callback:function(e){t.$set(t.submitForm,"taskReportUsername",e)},expression:"submitForm.taskReportUsername"}})],1)],1),t._v(" "),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"汇报日期：",prop:"taskReportTime"}},[r("el-date-picker",{staticStyle:{width:"225px"},attrs:{type:"datetime",placeholder:"请选择实际完成时间","default-time":"12:00:00"},model:{value:t.submitForm.taskReportTime,callback:function(e){t.$set(t.submitForm,"taskReportTime",e)},expression:"submitForm.taskReportTime"}})],1)],1)],1),t._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"责任人：",prop:"dutyUsername"}},[r("el-input",{model:{value:t.submitForm.dutyUsername,callback:function(e){t.$set(t.submitForm,"dutyUsername",e)},expression:"submitForm.dutyUsername"}})],1)],1),t._v(" "),r("el-col",{attrs:{span:12}},[r("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"本次工作量：",prop:"taskAmount"}},[r("el-input",{model:{value:t.submitForm.taskAmount,callback:function(e){t.$set(t.submitForm,"taskAmount",t._n(e))},expression:"submitForm.taskAmount"}})],1)],1)],1),t._v(" "),r("el-form-item",{staticStyle:{width:"650px"},attrs:{label:"完成情况：",prop:"remarks"}},[r("el-input",{attrs:{type:"textarea",autosize:{minRows:4,maxRows:4},placeholder:"请说明完成情况",resize:"none"},model:{value:t.submitForm.remarks,callback:function(e){t.$set(t.submitForm,"remarks",e)},expression:"submitForm.remarks"}})],1),t._v(" "),r("el-form-item",{staticStyle:{margin:"20px 0 0 170px"}},[r("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.onSubmit("form")}}},[t._v("确认")]),t._v(" "),r("el-button",{on:{click:function(e){return t.resetForm("form")}}},[t._v("取消")])],1)],1)],1)],1)},staticRenderFns:[]};var p=r("C7Lr")(u,c,!1,function(t){r("HbIV")},null,null);e.default=p.exports}});
//# sourceMappingURL=7.2641026604b29666e1bb.js.map