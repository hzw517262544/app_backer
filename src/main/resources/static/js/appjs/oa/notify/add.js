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
	loadType();
	validateRule();
	//选择文件触发事件
	$("#files").change(function () {
        upLoadFile();
    });
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
    var content_sn = $("#content_sn").summernote('code');
    $("#content").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/oa/notify/save",
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
				required : icon + "请输入姓名"
			}
		}
	})
}
function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/oa_notify_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			//点击事件
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}

var openUser = function(){
	layer.open({
		type:2,
		title:"选择人员",
		area : [ '300px', '450px' ],
		content:"/sys/user/treeView"
	});
	// layer.full(selectUsers);
}

function loadUser(userIds,userNames){
	$("#userIds").val(userIds);
	$("#userNames").val(userNames);
}

function upLoadFile() {
	var file = document.getElementById('files').files[0];
    var size = file.size;
    if((size / 1024 / 1024) > 10) {
        alert("文件大小不能超过10M...");
        return false;
    }
    console.log("size="+size);
    var formData = new FormData();
    formData.append("file", file);
    $.ajax({
        data : formData,
        type : "POST",
        url : "/common/sysFile/upload",    // 图片上传出来的url，返回的是图片上传后的路径，http格式
        cache : false,
        contentType : false,
        processData : false,
        dataType : "json",
        success: function(data) {//data是返回的hash,key之类的值，key是定义的文件名
            layer.msg("上传成功！");
            $("#fileIds").val(data.fileId);
        },
        error:function(){
            alert("上传失败！");
        }
    });
}