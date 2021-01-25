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

    $("#jqGrid").jqGrid({
        url: baseURL + 'DsCepStaff/consultant/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 75,key: true},
            { label: '排序', name: 'priority', index: "priority", width: 75,formatter:function (value,options,row) {
                    if (row.flag==1){
                        return "销售冠军";
                    }else{
                        return "<input type='text' style='width: 40px;' value='"+value+"' onchange='updateSort("+row.id+",this)'/>";
                    }
                }
             },
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
            {label: '服务人数', name: 'servedCount', index: "served_count", width: 80}
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

    $(".sort").on('change',function () {
        alert('1');
    })
});


/**
 * 查看店员详细信息
 * @param id 主键
 */
function showStaffDetail(id) {
    vm.showList=false;
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
            var fieldArr = [list.faceid,list.staffId,list.name,list.sex,list.postStatus,list.deptId,'',list.dutyStatus,
                list.personLabels,list.facoriteCount,list.servedcount,list.motto,'',list.qrcode];
            vm.detailHeadUrl = list.headUrl;
            vm.qrcode = list.qrcode;
            for (var i=0;i<nameArr.length;i++){
                var obj = {name:nameArr[i],value:fieldArr[i]};
                vm.detailObj.push(obj);
            }
            //生成二维码
            //createQRCode();
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

/**
 * 更新排名
 * @param id 主键
 * @param 当前对象 this
 */
function updateSort(id,obj) {
    $.ajax({
        type : "POST",
        contentType: "application/json;charset=UTF-8",
        url : baseURL+"DsCepStaff/update/selective",
        data: JSON.stringify({"id":id,"priority":$(obj).val()}),
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
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

/**
 * 上传图片
 */
function uploadImg() {

    if ($("#img").val()==null||$("#img").val()==""){
        alert("请检查选择图片!");
        return false;
    }

    var files = $('#img').prop('files');
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


var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            username: null
        },
        showList: true,
        detailObj:[],
        detailHeadUrl:null,
        faceid:null,
        title:null,
        staff:{
            staffId: null
        },
        ops: []
    },
    created: function() {
        this.getDept();
    },
    methods: {
        query: function () {
            vm.reload1(1);
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.staff.id = id;
            vm.showList = false;
            vm.title = "修改";
            showStaffDetail(id);
            vm.getStaffInfo(id);
        },
        getStaffInfo:function(id){
            $.ajax({
                type: "GET",
                url: baseURL +"/DsCepStaff/consultant/detail?id="+id,
                contentType: "application/json",
                success: function(staff){
                    vm.staff = staff.staff;
                    console.log(vm.staff);
                }
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
            if (vm.staff.staffId==null){
                alert("请选择一条记录");
                return false;
            }

            var arr = Object.keys(vm.staff);
            if (arr.length==0){
                alert("参数不可为空");
                return false;
            }
            if (vm.staff.staffId===null || vm.staff.staffId==='' ||
                vm.staff.name===null || vm.staff.name=== '' ||
                vm.staff.sex===null || vm.staff.sex=== '' ||
                vm.staff.postStatus==null || vm.staff.postStatus=== '' ||
                vm.staff.deptId===null || vm.staff.deptId=== '' ||
                vm.staff.staffType===null || vm.staff.staffType=== ''){
                alert("必填参数不可为空");
                return false;
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
            if(vm.staff.staffType){
                if(vm.staff.dutyStatus!='0' && vm.staff.dutyStatus!='1' && vm.staff.dutyStatus!='2'){
                    alert("请选择服务状态!");
                    return;
                }
            }

            if(!/^[0-9]+$/.test(vm.staff.servedCount)){
                alert("请输入合法的服务人数!");
                return;
            }

            if(!/^[0-9]+$/.test(vm.staff.facoriteCount)){
                alert("请输入合法的被关注数!");
                return;
            }

            if (vm.staff.servedCount.length>10){
                alert("请输入合法的服务人数!");
                return;
            }

            if (vm.staff.facoriteCount.length>10){
                alert("请输入合法的被关注数!");
                return;
            }

            if (vm.staff.flag==true){
                vm.staff.flag=1;
            }else{
                vm.staff.flag=0;
            }
            vm.staff.headUrl = vm.detailHeadUrl;
            vm.staff.faceid = vm.faceid;
            var url = "DsCepStaff/update";
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
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name,'dutyStatus':vm.q.dutyStatus,'deptId':vm.q.deptId},
                page:page
            }).trigger("reloadGrid");
        },
        reload1: function (pageNo) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            if(pageNo){
                page = pageNo;
            }
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name,'dutyStatus':vm.q.dutyStatus,'deptId':vm.q.deptId},
                page:page
            }).trigger("reloadGrid");
        },
        getDept: function() {
            $.ajax({
                url:baseURL+"dsCepStore/list",
                type:'GET',
                dataType:'json',
                // cache: false,
                // async:true,
                success:function (res) {
                    vm.ops = res.list;
                    // $(obj).empty();
                    // var html = "";
                    // for (var i=0;i<list.length;i++){
                    //     html += "<option value='"+list[i].id+"'>"+list[i].storeName+"</option>";
                    // }
                    // $(obj).append(html);
                },
                error:function () {
                    alert('获取门店信息有误');
                }
            });
        }
    }
});