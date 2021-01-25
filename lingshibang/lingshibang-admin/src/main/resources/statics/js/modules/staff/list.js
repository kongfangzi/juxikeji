$(function () {

    $('.label-info').click(function () {
        var content = $(this).html();
        if (vm.staff.personLabels==null||vm.staff.personLabels==undefined){
            vm.staff.personLabels =content;
        }else{
            vm.staff.personLabels += ", "+content;
        }
        $("#personLabels").val(vm.staff.personLabels);
        console.log(vm.staff.personLabels);
    });

    //时间控件
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });

    $("#jqGrid").jqGrid({
        url: baseURL + 'DsCepStaff/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 75,key: true,display:'none'},
            { label: '头像', name: 'headUrl', index: "head_url", width: 100,formatter:function(value,options,row){
                    if (value!=null&&value!=""){
                        return "<img src='"+value+"' style='width: 30px;height: 30px;'></img>";
                    }else {
                        return "";
                    }
                }
            },
            { label: '员工编号', name: 'staffId', index: "staff_id", width: 75 },
            { label: '员工姓名', name: 'name', sortable: name, width: 75 },
            { label: '所在门店', name: 'deptId', width: 100 },
            { label: 'face ID', name: 'faceid', index: "faceid", width: 80},
            {label: '状态', name: 'dutyStatus', index: "duty_status", width: 80,formatter:function(value,options,row){
                    if (value==0){
                        return "离线";
                    }else if (value==1){
                        return "忙碌";
                    }else if (value==2){
                        return "在线"
                    }
                }},
            {label: '被关注数', name: 'facoriteCount', index: "facorite_count", width: 80},
            {label: '服务人数', name: 'servedCount', index: "served_count", width: 80},
            {label: '操作', name: 'operate', index: "operate", width: 80,formatter:function (value,options,row) {
                    return "<a onclick='showAttendance(\""+row.id+"\")'>考勤</a>" +
                        "<a style='margin-left: 20px' onclick='showStaffDetail(\""+row.id+"\")'>查看</a>";
                }
            }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 35,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

    $("#jqGrid").setGridParam().hideCol("id");//隐藏

    /*layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '/upload/' //上传接口
            ,done: function(res){
                //上传完毕回调
            }
            ,error: function(){
                //请求异常回调
            }
        });
    });*/
});

/**
 * 上传图片
 */
function uploadImg() {
    var fileName = $("input[id=img]:visible").val();
    if ($.trim(fileName)==""){
        alert("请选择图片!");
        return false;
    }
    fileName = fileName.toLowerCase();
    if(!fileName.endsWith('.png') && !fileName.endsWith('.jpg') && !fileName.endsWith('.jpeg') && !fileName.endsWith('.gif')){
        alert("请选择图片！");
        return false;
    }
    var files = $("input[id=img]:visible").prop('files');
    var formData = new FormData();
    formData.append('file', files[0]);

    console.log("file"+formData);

    $.ajax({
        url:baseURL+"sys/oss/upload2",
        type:'POST',
        data:formData,
        dataType:'json',
        cache: false,
        async:true,
        // 告诉jQuery不要去处理发送的数据
        processData : false,
        // 告诉jQuery不要去设置Content-Type请求头
        contentType : false,
        success:function (res) {
            console.log(res);
            if (res.code==0){
                vm.detailHeadUrl = res.url;
                vm.staff.headUrl = res.url;
                vm.faceid = res.faceid;
            }else {
                alert("图片上传有误");
            }
        },
        error:function () {
            alert('上传出错，请稍后重试!');
        }
    });
}


function createQRCode() {
    var qrcode = new QRCode(document.getElementById("qrcode"), {
        text: vm.qrcode,
        width: 150,
        height: 150,
        colorDark : '#000000',
        colorLight : '#ffffff',
        correctLevel : QRCode.CorrectLevel.H
    });
}

function showAttendance(id) {
    vm.showList=false;
    vm.attendance=false;
    vm.id =id;
    vm.title="查看考勤";

    $("#jqGridAttendance").jqGrid({
        url: baseURL + 'DsCepStaff/attendance/detail?id='+id,
        datatype: "json",
        colModel: [
            { label: '采集日期', name: 'date', index: "date", width: 200,key: true},
            { label: '第一次采集时间', name: 'startTime', index: "start_time", width: 200 },
            { label: '最后一次采集时间', name: 'endTime', index:"end_time", width: 200 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPagerAttendance",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGridAttendance").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
}


function exportAttendence() {
   window.location.href = baseURL + 'DsCepStaff/attendance/export?id='+vm.id;
}

function getDeptInfo(obj) {
    $.ajax({
        url:baseURL+"dsCepStore/list",
        type:'GET',
        dataType:'json',
        cache: false,
        async:true,
        success:function (res) {
            var list = res.list;
            $(obj).empty();
            var html = "";
            for (var i=0;i<list.length;i++){
                html += "<option value='"+list[i].id+"'>"+list[i].storeName+"</option>";
            }
            $(obj).append(html);
        },
        error:function () {
            alert('获取门店信息有误');
        }
    });
}

function showStaffDetail(id) {
    vm.showList=false;
    vm.staffDetail =false;
    vm.title="查看详情";
    $.ajax({
        type : "GET",
        contentType: "application/json;charset=UTF-8",
        url : baseURL+"DsCepStaff/detail?id="+id,
        success : function(result) {
            console.log(result);
            var list = result.list;
            var nameArr = ['face ID','员工编号','员工姓名','性别','是否在职','所在门店','人员类型','状态','标签','被关注数','服务人数',
                '个人宣言','推荐车型','二维码链接'];
            var fieldArr = [list.faceid,list.staffId,list.name,list.sex,list.postStatus,list.deptId,list.staffType,list.dutyStatus,
                list.personLabels,list.facoriteCount,list.servedcount,list.motto,list.labels,list.qrcode];
            vm.detailHeadUrl = list.headUrl;
            vm.qrcode = list.qrcode;

            vm.detailObj=[];
            for (var i=0;i<nameArr.length;i++){
                if (i==3){
                    if (fieldArr[i]==1){fieldArr[i]="男"}
                    else {fieldArr[i]="女"}
                }
                if (i==4){
                    if (fieldArr[i]==1){fieldArr[i]="是"}
                    else{fieldArr[i]="否"}
                }

                if (i==6){
                    if (fieldArr[i]==1){fieldArr[i]="销售顾问"}
                    else{fieldArr[i]="其它"}
                }

                if (i==7){
                    if (fieldArr[i]==0){fieldArr[i]="离线"}
                    else if (fieldArr[i]==1){fieldArr[i]="忙碌"}
                    else if (fieldArr[i]==2){fieldArr[i]="在线"}
                }
                var obj = {name:nameArr[i],value:fieldArr[i]};
                vm.detailObj.push(obj);
            }
            //生成二维码
            // $("#qrcode").html("");
            // createQRCode();
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}


var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            username: null
        },
        showList: true,
        addPage:true,
        updatePage:true,
        attendance:true,
        staffDetail:true,
        sale:true,
        detailObj:[],
        detailHeadUrl:null,
        faceid:null,
        qrcode:null,
        title:null,
        id:null,
        staff:{},
        ops: []
    },
    created: function() {
        this.getDept();
    },
    methods: {
        query: function () {
            vm.reload1(1);
        },
        add: function(){
            vm.showList=false;
            vm.title = "新增";
            vm.addPage=false;
            vm.staff = {};
            vm.sale=false;
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            //vm.staffId = id;
            vm.staff.id=id;
            vm.showList = false;
            vm.updatePage=false;
            vm.title = "修改";
            vm.getStaffInfo(id);
        },
        getStaffInfo:function(id){
            $.get(baseURL +"/DsCepStaff/detail?id="+id, function(r){
                vm.staff = Object.assign({}, r.list);
                vm.isSale();
            });
        },
        del: function () {
            var staffIds = getSelectedRows();
            if(staffIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "DsCepStaff/delete",
                    contentType: "application/json",
                    data: JSON.stringify(staffIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        execUpdate: function () {
            var arr = Object.keys(vm.staff);
            if (arr.length==0){
                alert("参数不可为空");
                return false;
            }
            if (vm.staff.staffType===null || vm.staff.staffType=== ''){
                vm.staff.staffType = 0;
            }
            if (vm.staff.staffId===null || vm.staff.staffId=== '' ||
                vm.staff.name===null || vm.staff.name== '' ||
                vm.staff.sex===null || vm.staff.sex=== '' ||
                vm.staff.postStatus===null || vm.staff.postStatus=== '' ||
                vm.staff.deptId===null || vm.staff.deptId== '' ||
                vm.staff.staffType===null || vm.staff.staffType=== ''){
                alert("必填参数不可为空");
                return false;

            }

            if (vm.staff.staffType === 1){
                // if(vm.staff.id!=null&&vm.staff.sale==true&&(!/^[0-9]+$/.test(vm.staff.servedCount)||vm.staff.servedCount.length>10)){
                if(!/^[0-9]+$/.test(vm.staff.servedCount)){
                    alert("请输入合法的服务人数!");
                    return;
                }

                if (vm.staff.servedCount.length>10){
                    alert("请输入合法的服务人数!");
                    return;
                }

                if (vm.staff.servedCount >= 10000000000){
                    alert("请输入合法的服务人数!");
                    return;
                }

                // if(vm.staff.id!=null&&vm.staff.sale==true&&(!/^[0-9]+$/.test(vm.staff.facoriteCount)||vm.staff.facoriteCount.length>10)){
                if(!/^[0-9]+$/.test(vm.staff.facoriteCount)){
                    alert("请输入合法的被关注数!");
                    return;
                }

                if(vm.staff.facoriteCount.length>10){
                    alert("请输入合法的被关注数!");
                    return;
                }
                if(vm.staff.facoriteCount >= 10000000000){
                    alert("请输入合法的被关注数!");
                    return;
                }
            }

            if(!vm.staff.staffId){
                alert("请输入员工编号!");
                return;
            }
            if(!vm.staff.name){
                alert("请输入员工姓名!");
                return;
            }
            if(vm.staff.postStatus!='0' && vm.staff.postStatus!='1'){
                alert("请输入在职状态!");
                return;
            }
            if(vm.staff.staffType!=0&&vm.staff.staffType!=1){
               alert("请选择人员类型!");
               return;
            }

            if(vm.staff.sale){
                if(vm.staff.dutyStatus!='0' && vm.staff.dutyStatus!='1' && vm.staff.dutyStatus!='2'){
                    alert("请选择服务状态!");
                    return;
                }
            }

            if (vm.staff.flag==true){
                vm.staff.flag=1;
            }else{
                vm.staff.flag=0;
            }
            vm.staff.headUrl = vm.detailHeadUrl;
            vm.staff.faceid = vm.faceid;
            var url = vm.staff.id == null ? "DsCepStaff/save" : "DsCepStaff/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.staff),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        reload1: function (pageNo) {
            vm.showList = true;
            vm.addPage = true;
            vm.updatePage = true;
            vm.attendance = true;
            vm.staffDetail= true;
            vm.detailHeadUrl = null;
            $("#img").val("");
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            if(pageNo){
                page = pageNo;
            }
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name,'staffType':vm.q.staffType,'deptId':vm.q.deptId,'postStatus':vm.q.postStatus,
                'beginTime':vm.q.beginTime,'endTime':vm.q.endTime},
                page:page
            }).trigger("reloadGrid");
        },
        reload: function () {
            vm.showList = true;
            vm.addPage = true;
            vm.updatePage = true;
            vm.attendance = true;
            vm.staffDetail= true;
            vm.detailHeadUrl = null;
            $("#img").val("");
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name,'staffType':vm.q.staffType,'deptId':vm.q.deptId,'postStatus':vm.q.postStatus,
                    'beginTime':vm.q.beginTime,'endTime':vm.q.endTime},
                page:page
            }).trigger("reloadGrid");
        },
        uploadFile:function () {
            var fileName = $("#file").val();
            if ($.trim(fileName)==""){
                alert("请检查选择文件");
                return false;
            }
            fileName = fileName.toLowerCase();
            var b1 = fileName.endsWith(".xlsx");
            var b2 = fileName.endsWith(".xls");
            if (!fileName.endsWith(".xlsx")){
                if(!fileName.endsWith(".xls")){
                    alert("请选择正确的Excel文件");
                    return false;
                }
            }

            var files = $('#file').prop('files');
            var formData = new FormData();
            formData.append('file', files[0]);

            console.log("file"+formData);

            $.ajax({
                url:baseURL+"DsCepStaff/import",
                type:'POST',
                data:formData,
                dataType:'json',
                cache: false,
                async:true,
                // 告诉jQuery不要去处理发送的数据
                processData : false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType : false,
                success:function (res) {
                    console.log(res);
                    alert('上传成功!');
                    $('#file').val('');
                },
                error:function () {
                    alert('上传出错，请稍后重试!');
                }
            });
        },
        getBase64: function () {
            $.ajax({
                url:baseURL+"face/faceid",
                type:'GET',
                data:"url="+vm.staff.headUrl,
                cache: false,
                async:true,
                // 告诉jQuery不要去处理发送的数据
                processData : false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType : false,
                success:function (res) {
                    vm.staff.faceid=res.faceid;
                    vm.faceid=res.faceid;
                },
                error:function () {
                    alert('上传出错，请稍后重试!');
                }
            });
        },
        getDept: function() {
            $.ajax({
                url:baseURL+"dsCepStore/list",
                type:'GET',
                dataType:'json',
                success:function (res) {
                    vm.ops = res.list;
                },
                error:function () {
                    alert('获取门店信息有误');
                }
            });
        },
        isSale:function () {
            if (vm.staff.staffType==1){
                vm.sale=true;
            }else{
                vm.sale=false;
                vm.staff.dutyStatus = null;
                vm.staff.flag = null;
                vm.staff.personLabels = null;
                vm.staff.facoriteCount = null;
                vm.staff.servedCount = null;
                vm.staff.qrcode = null;
                vm.staff.motto = null;
                vm.staff.labels = null;
            }
        }
    }
});