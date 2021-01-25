$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'dealer/area/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', index: "id", width: 45,key:true,hidden:true },
			{ label: '大区编号', name: 'areaCode', width: 45 },
			{ label: '大区名称', name: 'areaName', width: 75},
			{ label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
                if(value === 0){return '<span class="label label-danger">已下线</span>'}
                else if(value === 1){return '<span class="label label-success">正常</span>'}
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
            areaName: null
        },
        showList: true,
        title:null,
        roleList:{},
        area:{
            id:null,
            status:1,
            deptId:null,
            deptName:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.area = {id:null,status:1,deptId:null,deptName:null};
            vm.getDept();
        },
        exp: function(){
            var url = "dealer/area/export";
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
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.area.deptId);
                if(node != null){
                    ztree.selectNode(node);
                    vm.area.deptName = node.name;
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
            vm.getArea(id);
            vm.dept = vm.area.deptName;
            vm.getDept();
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
                    url: baseURL + "dealer/area/delete",
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
            var url = vm.area.id == null ? "dealer/area/save" : "dealer/area/update";
            if (vm.area.areaCode == null || vm.area.areaCode == ''){
                alert("大区编号不能为空");
                return;
            }
            if (vm.area.areaName == null || vm.area.areaName == ''){
                alert("大区名称不能为空");
                return;
            }
            if (vm.area.deptId == null || vm.area.deptId == ''){
                alert("所属部门不能为空");
                return;
            }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.area),
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
        getArea: function(id){
            $.get(baseURL + "dealer/area/info/"+id, function(r){
                vm.area = r.area;
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
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.area.deptId = node[0].deptId;
                    vm.area.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page =1;
            // var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'area_name': vm.q.areaName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});