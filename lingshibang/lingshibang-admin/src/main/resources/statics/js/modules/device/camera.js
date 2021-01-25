$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'device/camera/list',
        datatype: "json",
        colModel: [
            /*{ label: '摄像头ID', name: 'id', width: 45, key: true },*/
            { label: '所属门店ID', name: 'storeId', width: 75 },
            { label: '位置', name: 'location', width: 75 },
            { label: 'IP地址', name: 'ip', sortable: false, width: 75 },
            { label: '云端配置名称', name: 'cameraCloudName', width: 90 },
            { label: '品牌', name: 'brand', width: 100 },
            { label: '型号', name: 'model', width: 100 },
            { label: '技能', name: 'skill', width: 100, formatter: function(value, options, row){
                    if(value === '1'){return '<span class="label label-danger">人脸识别</span>'}
                    else if(value === '2'){return '<span class="label label-success">车牌识别</span>'}
                    else if (value === '1,2'){return '<span class="label label-danger">人脸识别 车牌识别</span>'}
                    else {return '<span class="label label-danger"></span>'}
                }},
            { label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
                    if(value === 0){return '<span class="label label-danger">未连接</span>'}
                    else if(value === 1){return '<span class="label label-success">运行正常</span>'}
                    else if (value === -1){return '<span class="label label-danger">运行失败</span>'}
                }},
            { label: '创建时间', name: 'createTime', index: "create_time", width: 85}
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
});
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            cameraCloudName: null,

        },
        showList: true,
        title:null,
        roleList:{},
        camera:{
            status:1,
            id:null,
            deptId:null,
            deptName:null,
            skill:null,
            skill1:null,
            skill2:null,
            ichnographyUrl:null
        }
    },
    methods: {
        query: function () {
            vm.reload1(1);
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.camera = {status:1,id:null,storeId:null,deptId:null,deptName:null};
            vm.getStore();
            vm.getDept();
        },
        exp1: function(){
            var url = "device/camera/export";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                success: function(r){
                    if(r.code === 0){
                        alert('数据导出成功,文件名称为'+r.msg, function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        exp:function () {
            var url = encodeURI(baseURL+"device/camera/export")
            window.location.href=url;
        },
        getStore:function (){
            //加载经销商信息
            $.get(baseURL + "dealer/store/getList", function(r){
                var dom = ''
                for(var i =0;i<r.length;i++){
                    dom+='<option value ="'+r[i].id+'">'+r[i].storeName+'</option>'
                }
                $("#store").html(dom)
            })
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree1"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.camera.deptId);
                if(node != null){
                    ztree.selectNode(node);
                    vm.camera.deptName = node.name;
                }
            })
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getCamera(id);
            vm.getStore();
            vm.getDept();
            //this.getRoleList();
        },
        details: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "查看";
            vm.getCamera(id);
            vm.getStore();
            vm.getDept();
            //this.getRoleList();
        },
        permissions: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }

            window.location.href=baseURL+"sys/permissions/index/"+userId;
        },
        del: function () {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "device/camera/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
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
        saveOrUpdate: function () {
            var url = vm.camera.id == null ? "device/camera/save" : "device/camera/update";
            if (vm.camera.ip == null || vm.camera.ip == ''){
                alert("IP地址不能为空");
                return;
            }
            if (vm.camera.storeId == null || vm.camera.storeId == ''){
                alert("门店不能为空");
                return;
            }
            if (vm.camera.cameraCloudName == null || vm.camera.cameraCloudName == ''){
                alert("云端配置不能为空");
                return;
            }
            if (vm.camera.loginUser == null || vm.camera.loginUser == ''){
                alert("登录用户不能为空");
                return;
            }
            // if (vm.camera.loginPassword == null || vm.camera.loginPassword == ''){
            //     alert("登录密码不能为空");
            //     return;
            // }
            var regu = /^(\-|\+)?\d+(\.\d+)?$/;
             if (!/^(\-|\+)?\d+(\.\d+)?$/.test(vm.camera.longitude)){
             // if (!/^[0-9]+$/.test(vm.camera.longitude)){
            // if (!/^(\d+|\d+\.\d{1,2})+$/.test(vm.camera.longitude)){
                alert("x轴只能输入数字");
                return;
            }
            if (!/^(\-|\+)?\d+(\.\d+)?$/.test(vm.camera.latitude)){
                alert("y轴只能输入数字");
                return;
            }
            if (vm.camera.longitude.length > 10){
                alert("x轴数字长度不能大于10");
                return;
            }
            if (vm.camera.latitude.length > 10){
                alert("y轴数字长度不能大于10");
                return;
            }
            if (vm.camera.location == null || vm.camera.location == ''){
                alert("安装位置不能为空");
                return;
            }
            if (!vm.camera.skill1 && !vm.camera.skill2){
                alert("技能不能为空");
                return;
            }
            if(vm.camera.skill1){
                vm.camera.skill = "1";
                if (vm.camera.skill2){
                    vm.camera.skill = "1,2";
                }
            }
            if (vm.camera.skill2){           	
                vm.camera.skill = "2";
                if (vm.camera.skill1){
                    vm.camera.skill = "1,2";
                }
            }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.camera),
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
        getCamera: function(id){
            $.get(baseURL + "device/camera/info/"+id, function(r){
                vm.camera = r.camera;
                if (r.camera.skill === '1,2'){
                    vm.camera.skill1 = true;
                    vm.camera.skill2 = true;
                }else if (r.camera.skill === '1'){
                    vm.camera.skill1 = true;
                }else if (r.camera.skill === '2'){
                    vm.camera.skill2 = true;
                }
                // var pic = document.getElementById("ichnographyUrl");
                // pic.src = vm.camera.ichnographyUrl;
            });
        },
        getRoleList: function(){
            $.get(baseURL + "sys/role/select", function(r){
                vm.roleList = r.list;
            });
        },
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择门店",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    vm.camera.storeId = $("#store").val();
                    layer.close(index);
                    // $.get(baseURL + "dealer/store/info/"+vm.camera.storeId, function(r){
                    //     vm.store = r.store;
                    //     $("#ichnographyUrl").attr("src",vm.store.ichnographyUrl);
                    //     var pic = document.getElementById("ichnographyUrl");
                    //     pic.src = vm.store.ichnographyUrl;
                    // });
                }
            });
        },
        deptTree1: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer1"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.camera.deptId = node[0].deptId;
                    vm.camera.deptName = node[0].name;
                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'camera_cloud_name': vm.q.cameraCloudName},
                page:page
            }).trigger("reloadGrid");
            // var pic = document.getElementById("ichnographyUrl");
            // pic.src = '';
        },
        reload1: function (pageNo) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            if(pageNo){
                page = pageNo;
            }
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'camera_cloud_name': vm.q.cameraCloudName},
                page:page
            }).trigger("reloadGrid");
            // var pic = document.getElementById("ichnographyUrl");
            // pic.src = '';
        }
    }
});