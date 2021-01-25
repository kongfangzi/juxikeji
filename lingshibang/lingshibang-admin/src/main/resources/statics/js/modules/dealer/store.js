$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'dealer/store/list',
        datatype: "json",
        colModel: [
            { label: '门店ID', name: 'id', index: "id", width: 45, key: true,hidden:true},
            { label: '所属经销商编码', name: 'dealerCode', width: 75 },
            { label: '门店名称', name: 'storeName', sortable: false, width: 75 },
            { label: '创建时间', name: 'createTime', width: 90 },
            { label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
                    if(value === 0){return '<span class="label label-danger">未安装</span>'}
                    else if(value === 1){return '<span class="label label-success">已安装</span>'}
                    else if(value===-1){return '<span class="label label-danger">已下线</span>'}
                }}
            // { label: '操作', name: 'id', width: 40, formatter: function(value, options, row){
            //         return '<batton class="label label-danger" on-click="getStore(value)">查看</batton>'
            //     }}
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

var setting_area = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pid",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var atree;

/**
 * 上传图片
 */
function uploadImg() {
    if ($("#img").val()==null||$("#img").val()==""){
        alert("请检查选择图片");
        return false;
    }

    var files = $('#img').prop('files');
    var formData = new FormData();
    formData.append('file', files[0]);

    // console.log("file"+formData);
    vm.failData = formData;
    $.ajax({
        url:baseURL+"sys/oss/upload",
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
            vm.store.ichnographyUrl = res.url;
            // console.log(res);
            var src = res.url;
            $("#ichnographyUrl").attr("src",src);
            var pic = document.getElementById("ichnographyUrl");
            pic.src = src;
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
            storeName: null,
            startTime:null
        },
        showList: true,
        title:null,
        roleList:{},
        store:{
            status:1,
            dealerCode:null,
            storeName:null,
            deptId:null,
            deptName:null,
            storeTypeId:null,
            storeType:null,
            principalName:null,
            telephone:null,
            ichnographyUrl:null,

            areaName:null,
            areaCode:null,
            sysAreaName:null,
            sysAreaCode:null,
            provinces:null,
            city:null,
            county:null
        },

        failData:null
    },
    methods: {
        query: function () {
            vm.reload1(1);
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.dealerList = {};
            vm.store = {dealerCode:null, storeName:null, status:1,deptId:null,deptName:null,storeTypeId:null,storeType:null,areaCode:null,areaName:null,sysAreaCode:null,sysAreaName:null};
            vm.getArea();
            vm.getDealer();
            vm.getDept();
            vm.getStoreType();
            vm.getSysArea();
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree1"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.store.deptId);
                if(node != null){
                    ztree.selectNode(node);
                    vm.store.deptName = node.name;
                }
            })
        },
        getDealer: function(){
            //加载经销商信息
            $.get(baseURL + "dealer/dealer/getList", function(r){
                var dom = ''
                for(var i =0;i<r.length;i++){
                    dom+='<option value ="'+r[i].dealerCode+'">'+r[i].dealerName+'</option>'
                }
                $("#storeDealer").html(dom)
            })
        },
        getDealer1: function(code){
            //加载经销商信息
            $.get(baseURL + "dealer/dealer/getList1?code="+code, function(r){
                var dom = ''
                for(var i =0;i<r.length;i++){
                    dom+='<option value ="'+r[i].dealerCode+'">'+r[i].dealerName+'</option>'
                }
                $("#storeDealer").html(dom)
            })
        },
        getStoreType: function(){
            //加载门店类型
            $.get(baseURL + "dealer/store/getTypeList", function(r){
                var dom = ''
                for(var i =0;i<r.length;i++){
                    dom+='<option value ="'+r[i].id+'">'+r[i].storeType+'</option>'
                }
                $("#storeType").html(dom)
            })
        },
        getSysArea: function(){
            //加载省市区信息
            $.get(baseURL + "sys/area/list", function(r){
                atree = $.fn.zTree.init($("#sysAreaTree"), setting_area, r);
                var node = atree.getNodeByParam("code", vm.store.sysAreaCode);
                if(node != null){
                    atree.selectNode(node);
                    vm.store.sysAreaName = node.gparentName + "-" + node.parentName + "-" + node.name;
                }
            })
        },
        getArea: function(){
            //加载大区信息
            $.get(baseURL + "dealer/area/getList", function(r){
                var dom1 = ''
                for(var i =0;i<r.length;i++){
                    dom1+='<option value ="'+r[i].areaCode+'">'+r[i].areaName+'</option>'
                }
                $("#area").html(dom1)
            })
        },
        update: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";
            vm.getStore(id);
            vm.getDealer();
            vm.getDept();
            vm.getStoreType();
            vm.getSysArea();
            vm.getArea();
        },
        exp1: function(){
            var url = "dealer/store/export";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
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
        exp:function () {
            var url = encodeURI(baseURL+"dealer/store/export")
            window.location.href=url;
        },
        details: function () {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "详情";
            vm.getStore(id);
            vm.getDealer();
            vm.getDept();
            vm.getStoreType();
            vm.getSysArea();
            vm.getArea();
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
                    url: baseURL + "dealer/store/delete",
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
            var url = vm.store.id == null ? "dealer/store/save" : "dealer/store/update";
            if (vm.store.ichnographyUrl == null || vm.store.ichnographyUrl == ''){
                alert("请先上传图片");
                return;
            }
            if (vm.store.storeName == null || vm.store.storeName == ''){
                alert("门店名称不能为空");
                return;
            }
            if (vm.store.dealerCode == null || vm.store.dealerCode == ''){
                alert("经销商编码不能为空");
                return;
            }
            if (vm.store.principalName == null || vm.store.principalName == ''){
                alert("负责人不能为空");
                return;
            }
            if (vm.store.telephone == null || vm.store.telephone == ''){
                alert("联系电话不能为空");
                return;
            }
            if(!/^[0-9]+$/.test(vm.store.telephone) || vm.store.telephone.length>11){
                alert("请输入合法的联系电话!");
                return;
            }
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.store),
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
        getStore: function(id){
            $.get(baseURL + "dealer/store/info/"+id, function(r){
                vm.store = r.store;
                $("#ichnographyUrl").attr("src",vm.store.ichnographyUrl);
                var pic = document.getElementById("ichnographyUrl");
                pic.src = vm.store.ichnographyUrl;
            });
        },
        getRoleList: function(){
            $.get(baseURL + "sys/role/select", function(r){
                vm.roleList = r.list;
            });
        },
        deptTree(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择经销商",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    vm.store.dealerCode=$("#storeDealer").val();
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
                    vm.store.deptId = node[0].deptId;
                    vm.store.deptName = node[0].name;
                    layer.close(index);
                }
            });
        },
        storeTypeTree(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择门店类型",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#storeTypeLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    vm.$nextTick(() => {
                        vm.store.storeTypeId=$("#storeType").val();
                        layer.close(index);
                    })
                }
            });
        },
        sysAreaTree(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择所在地",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#sysAreaLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = atree.getSelectedNodes();
                    vm.store.sysAreaCode = node[0].code;
                    vm.store.sysAreaName = node[0].name;
                    vm.store.sysAreaName = node[0].gparentName + "-" + node[0].parentName + "-" + node[0].name;
                    vm.store.provinces = node[0].gparentName;
                    vm.store.city = node[0].parentName;
                    vm.store.county = node[0].name;
                    layer.close(index);
                }
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
                        vm.getDealer1($("#area").val());
                        vm.store.areaCode=$("#area").val();
                        vm.store.dealerCode='';
                        layer.close(index);
                    })
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'store_name': vm.q.storeName},
                page:page
            }).trigger("reloadGrid");
            var pic = document.getElementById("ichnographyUrl");
            pic.src = baseURL+'/statics/picture/ichnography.png';
            var pic1 = document.getElementById("img");
            pic1.value='';
        },
        reload1: function (pageNo) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            if(pageNo){
                page = pageNo;
            }
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'store_name': vm.q.storeName},
                page:page
            }).trigger("reloadGrid");
            var pic = document.getElementById("ichnographyUrl");
            pic.src = baseURL+'/statics/picture/ichnography.png';
            var pic1 = document.getElementById("img");
            pic1.value='';
        }
    }
});