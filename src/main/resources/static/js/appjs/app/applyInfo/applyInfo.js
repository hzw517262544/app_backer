
var prefix = "/app/applyInfo"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									checkbox : true
								},
                            {
                                field : 'Number',
                                title : '序号',
                                align: 'center',
                                width: 20,
                                formatter : function(value, row, index) {
                                    //return index + 1;
                                    var pageSize=$('#exampleTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                                    var pageNumber=$('#exampleTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                                    return pageSize * (pageNumber - 1) + index + 1;//返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                                }
                            },
																{
									field : 'username', 
									title : '用户账号' ,
									visible: false
								},
																{
									field : 'name', 
									title : '用户姓名'  ,
									visible: false
								},{
                                field : 'applyNo',
                                title : '编号'
							},{
                                field : 'applyTitle',
                                title : '标题'
                            },
																{
									field : 'applyType', 
									title : '申请类型' ,
									visible: false
								},
																{
									field : 'applyTypeName', 
									title : '申请类型'
								},
																{
									field : 'applySecodType', 
									title : '申请子类型'  ,
									visible: false
								},
																{
									field : 'applySecodTypeName', 
									title : '申请子类型',
									visible: false
								},
								{
									field : 'sendPlatform',
									title : '发送平台'  ,
									visible: false
								},
								{
									field : 'sendPlatformName',
									title : '发送平台'
								},
								{
									field : 'sendGrade',
									title : '发送等级'  ,
									visible: false
								},
								{
									field : 'sendGradeName',
									title : '发送等级'
								},
																{
									field : 'applyStartTime', 
									title : '申请开始时间' ,
									visible: false

								},
																{
									field : 'applyEndTime', 
									title : '申请结束时间' ,
									visible: false
								},
																{
									field : 'applyContent', 
									title : '申请内容' 
								},
																{
									field : 'applyStatus', 
									title : '申请状态' ,
									visible: false
								},
								{
                                field : 'applyStatusName',
                                title : '状态',
								align :'center',
								formatter : function(value, row, index){
                                        if(row.applyStatus=='1'||row.applyStatus=='2'){
                                            return '<span class="label label-warning">'+value+'</span>';
                                        }else if(row.applyStatus=='4'||row.applyStatus=='0'){
                                            return '<span class="label label-primary">'+value+'</span>';
                                        }else if(row.applyStatus=='3'||row.applyStatus=='5'){
                                            return '<span class="label label-danger">'+value+'</span>';
                                        }else{
                                        	return '<span class="label label-primary">'+value+'</span>';
										}
                                    }
                            	},
																{
									field : 'createTime', 
									title : '创建时间' 
								},
																{
									field : 'createUser', 
									title : '创建人' 
								},
																{
									field : 'updateTime', 
									title : '更新时间'  ,
									visible: false
								},
																{
									field : 'updateUser', 
									title : '更新人'  ,
									visible: false
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id+'\',\''+row.applyStatus
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id+'\',\''+row.applyStatus
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm " href="#" title="添加题材"  mce_href="#" onclick="addTopic(\''
												+ row.id+'\',\''+row.applyStatus
												+ '\')"><i class="fa fa-plus-square"></i></a> ';
                                        var g = '<a class="btn btn-success btn-sm " href="#" title="提交"  mce_href="#" onclick="commit(\''
                                            + row.id+'\',\''+row.applyStatus
                                            + '\')"><i class="fa fa-check-square"></i></a> ';
										return f + g + e + d ;
									}
								}]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	var addPage = layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
    layer.full(addPage);
}
function edit(id,applyStatus) {
	if(applyStatus != '1'&&applyStatus != '3'){
		layer.msg("只能编辑未提交、退回状态的申请!");
		return false;
	}
	var editPage = layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
    layer.full(editPage);
}
function remove(id,applyStatus) {
    if(applyStatus != '1'){
        layer.msg("只能删除未提交状态的申请!");
        return false;
    }
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

/**
 * 添加选题
 * @param id
 */
function addTopic(id,applyStatus) {
    var topicPage = layer.open({
        type : 2,
        title : '选题',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : '/app/topic/fromApply/'+id // iframe的url
    });
    layer.full(topicPage);
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}

function commit(id,applyStatus) {
    if(applyStatus != '1'&&applyStatus != '3'){
        layer.msg("只能提交未提交、退回状态的申请!");
        return false;
    }
    $.ajax({
        type : 'POST',
        data : {
            "id" : id
        },
        url : prefix + '/commit',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
}