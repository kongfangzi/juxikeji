$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'device/probe/list',
        datatype: "json",
        colModel: [
            { label: 'ID', name: 'id', index: "id", width: 45, hidden: true,key: true},
			{ label: '设备ID', name: 'deviceId', width: 45 },
			{ label: '所属门店ID', name: 'storeId', width: 75 },
            { label: '设备key', name: 'deviceKey', sortable: false, width: 75 },
			{ label: 'wifi名称', name: 'wifi', width: 90 },
			{ label: 'X轴', name: 'x', width: 100 },
            { label: 'Y轴', name: 'y', width: 100 },
			{ label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
                if(value === 0){return '<span class="label label-danger">未连接</span>'}
                else if(value === 1){return '<span class="label label-success">运行正常</span>'}
                else if(value===-1){return '<span class="label label-danger">运行失败</span>'}
			}},
			{ label: '创建时间', name: 'createTime', index: "createTime", width: 85}
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
            deviceId: null
        },
        showList: true,
        title:null,
        roleList:{},
        probe:{
            status:1,
            deptId:null,
            deptName:null
        }
    },
    methods: {
        query: function () {
            vm.reload1(1);
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.probe = {status:1,storeId:null,deptId:null,deptName:null};
            vm.getStore();
            vm.getDept();
        },
        exp1: function(){
            var url = "device/probe/export";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                success: function(r){
                    if(r.code === 0){
                        alert('数据导出成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        exp:function () {
            var url = encodeURI(baseURL+"device/probe/export")
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
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";
            vm.getProbe(id);
            vm.getStore();
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
                    url: baseURL + "device/probe/delete",
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
            var url = vm.probe.id == null ? "device/probe/save" : "device/probe/update";
            if (vm.probe.deviceId == null || vm.probe.deviceId == ''){
                alert("探针id不能为空");
                return;
            }
            if (vm.probe.storeId == null || vm.probe.storeId == ''){
                alert("门店不能为空");
                return;
            }
            if (vm.probe.wifi == null || vm.probe.wifi == ''){
                alert("wifi名称不能为空");
                return;
            }
            // if (!/^[0-9]+$/.test(vm.probe.x)){
            if (!/^(\-|\+)?\d+(\.\d+)?$/.test(vm.probe.x)){
                alert("x轴只能输入数字");
                return;
            }
            // if (!/^[0-9]+$/.test(vm.probe.y)){
            if (!/^(\-|\+)?\d+(\.\d+)?$/.test(vm.probe.y)){
                alert("y轴只能输入数字");
                return;
            }
            if (vm.probe.x.length > 16){
                alert("x轴数字长度不能大于16");
                return;
            }
            if (vm.probe.y.length > 16){
                alert("y轴数字长度不能大于16");
                return;
            }
            if (vm.probe.location == null || vm.probe.location == ''){
                alert("安装位置不能为空");
                return;
            }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.probe),
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
        getProbe: function(id){
            $.get(baseURL + "device/probe/info/"+id, function(r){
                vm.probe = r.probe;
            });
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree1"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.probe.deptId);
                if(node != null){
                    ztree.selectNode(node);
                    vm.probe.deptName = node.name;
                }
            })
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
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    vm.probe.storeId = $("#store").val();
                    layer.close(index);
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
                    vm.probe.deptId = node[0].deptId;
                    vm.probe.deptName = node[0].name;
                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'device_id': vm.q.deviceId},
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
                postData:{'device_id': vm.q.deviceId},
                page:page
            }).trigger("reloadGrid");
        }
    }
});