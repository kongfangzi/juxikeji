$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'dealer/dealer/list',
        datatype: "json",
        colModel: [
            { label: '编号', name: 'id', index: "id", width: 60, key: true,hidden:true},
			{ label: '经销商编码', name: 'dealerCode', index: "dealer_code", width: 50},
			{ label: '经销商名称', name: 'dealerName', width: 75 },
            { label: '所属区域', name: 'areaCode', width: 75 },
            { label: '所属部门', name: 'deptId', width: 75 },
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
            dealerName: null
        },
        showList: true,
        title:null,
        roleList:{},
        dealer:{
            status:1,
            dealerCode:null,
            dealerName:null,
            areaCode:null,
            deptId:null

        }
    },
    methods: {
        query: function () {
            vm.reload(1);
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.dealer = {status:1,dealerCode:null,dealerName:null,areaCode:null,deptId:null,deptName:null,};
            vm.getArea();
            vm.getDept();
        },
        exp: function(){
            var url = "device/dealer/export";
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
        getArea: function(){
            //加载门店信息
            $.get(baseURL + "dealer/area/getList", function(r){
                var dom = ''
                for(var i =0;i<r.length;i++){
                    dom+='<option value ="'+r[i].areaCode+'">'+r[i].areaName+'</option>'
                }
                $("#area").html(dom)
            })
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.dealer.deptId);
                if(node != null){
                    ztree.selectNode(node);
                    vm.dealer.deptId = node.deptId;
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
            vm.getDealer(id);
            vm.getArea();
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
                    url: baseURL + "dealer/dealer/delete",
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
            var url = vm.dealer.id == null ? "dealer/dealer/save" : "dealer/dealer/update";
            if (vm.dealer.dealerCode == null || vm.dealer.dealerCode == ''){
                alert("经销商编号不能为空");
                return;
            }
            if (vm.dealer.dealerName == null || vm.dealer.dealerName == ''){
                alert("经销商名称不能为空");
                return;
            }
            if (vm.dealer.areaCode == null || vm.dealer.areaCode == ''){
                alert("大区名称不能为空");
                return;
            }
            if (vm.dealer.deptId == null || vm.dealer.deptId == ''){
                alert("部门名称不能为空");
                return;
            }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.dealer),
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
        getDealer: function(id){
            $.get(baseURL + "dealer/dealer/info/"+id, function(r){
                vm.dealer = r.dealer;
            });
        },
        getRoleList: function(){
            $.get(baseURL + "sys/role/select", function(r){
                vm.roleList = r.list;
            });
        },
        areaTree(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择大区",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#areaLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    vm.$nextTick(() => {
                        vm.dealer.areaCode=$("#area").val();
                        layer.close(index);
                    })
                }
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
                    vm.dealer.deptId = node[0].deptId;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page =1;
            // var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'dealerName': vm.q.dealerName},
                page:page
            }).trigger("reloadGrid");
        }
    }
});