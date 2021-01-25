$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'list/white/list',
        datatype: "json",
        colModel: [			
			{ label: '摄像头ID', name: 'id', index: "user_id", width: 45, key: true },
			{ label: '所属门店ID', name: 'storeid', width: 75 },
            { label: '位置', name: 'location', width: 75 },
            { label: 'IP地址', name: 'ip', sortable: false, width: 75 },
			{ label: '云端配置名称', name: 'cameracloudname', width: 90 },
			{ label: '品牌', name: 'brand', width: 100 },
            { label: '型号', name: 'model', width: 100 },
			{ label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'createtime', index: "create_time", width: 85}
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
            cameracloudname: null
        },
        showList: true,
        title:null,
        roleList:{},
        camera:{
            status:1
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.camera = {status:1};

            //获取角色信息
            //this.getRoleList();

            //vm.getDept();
        },
        exp: function(){
            var url = "device/camera/export";
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
            //加载门店信息
            $.get(baseURL + "sys/store/list", function(r){
                var dom = ''
                for(var i =0;i<r.length;i++){
                    dom+='<option value ="'+r[i].id+'">'+r[i].storename+'</option>'
                }
                $("#store").html(dom)
                console.log(r)
            })
        },
        update: function () {
            var id = getSelectedRow();
            console.log("================"+id+"=================")
            if(id == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getCamera(id);
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
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'cameracloudname': vm.q.cameracloudname},
                page:page
            }).trigger("reloadGrid");
        }
    }
});