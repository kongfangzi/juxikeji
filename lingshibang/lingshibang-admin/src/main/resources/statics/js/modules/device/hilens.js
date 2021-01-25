$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'device/hilens/list',
        datatype: "json",
        colModel: [			
			{ label: 'HilensID', name: 'id', index: "id", width: 45, key: true },
			{ label: '所属门店ID', name: 'storeId', width: 75 },
            { label: '云端配置名称', name: 'cloudName', sortable: false, width: 75 },
			{ label: '登录用户', name: 'loginUser', width: 90 },
			// { label: '登录密码', name: 'loginPassword', width: 100 },
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
            cloudName: null
        },
        showList: true,
        title:null,
        roleList:{},
        hilens:{
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
            vm.hilens = {status:1,storeId:null,deptId:null,deptName:null};
            vm.getStore();
            //获取角色信息
            //this.getRoleList();
            vm.getDept();
        },
        exp1: function(){
            var url = "device/hilens/export";
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
            var url = encodeURI(baseURL+"device/hilens/export")
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
                var node = ztree.getNodeByParam("deptId", vm.hilens.deptId);
                if(node != null){
                    ztree.selectNode(node);
                    vm.hilens.deptName = node.name;
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
            vm.getHilens(id);
            vm.getStore();
            //获取角色信息
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
                    url: baseURL + "device/hilens/delete",
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
            var url = vm.hilens.id == null ? "device/hilens/save" : "device/hilens/update";
            if (vm.hilens.ip == null || vm.hilens.ip == ''){
                alert("IP地址不能为空");
                return;
            }
            if (vm.hilens.storeId == null || vm.hilens.storeId == ''){
                alert("门店不能为空");
                return;
            }
            if (vm.hilens.cloudName == null || vm.hilens.cloudName == ''){
                alert("云端配置不能为空");
                return;
            }
            if (vm.hilens.version == null || vm.hilens.version == ''){
                alert("版本不能为空");
                return;
            }
            if (vm.hilens.loginUser == null || vm.hilens.loginUser == ''){
                alert("登录用户不能为空");
                return;
            }
            // if (vm.hilens.loginPassword == null || vm.hilens.loginPassword == ''){
            //     alert("登录密码不能为空");
            //     return;
            // }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.hilens),
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
        getHilens: function(id){
            $.get(baseURL + "device/hilens/info/"+id, function(r){
                vm.hilens = r.hilens;
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
                    vm.hilens.storeId = $("#store").val();
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
                    vm.hilens.deptId = node[0].deptId;
                    vm.hilens.deptName = node[0].name;
                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'cloud_name': vm.q.cloudName},
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
                postData:{'cloud_name': vm.q.cloudName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});