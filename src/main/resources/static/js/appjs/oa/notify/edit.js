$().ready(function() {
    $('.summernote').summernote({
        height : '500px',
        lang : 'zh-CN',
        callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
    });
    var content = $("#content").val();
    $('#content_sn').summernote('code', content);
	//loadType();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
    var content_sn = $("#content_sn").summernote('code');
    $("#content").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/oa/notify/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}

function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/oa_notify_type',
		success : function(data) {
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			$(".chosen-select").val($("#Ttype").val());
			$(".chosen-select").trigger("chosen:updated");
			// 点击事件
			$('.chosen-select').on('change', function(e, params) {
				
			});
		}
	});
}

var openUser = function(){
    var selectUsers = layer.open({
        type:2,
        title:"选择人员",
        area : [ '300px', '450px' ],
        content:"/sys/user/treeView"
    });
    layer.full(selectUsers);
}

function loadUser(userIds,userNames){
    $("#userIds").val(userIds);
    $("#userNames").val(userNames);
}
