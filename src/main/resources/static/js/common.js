$().ready(function() {
    /*加载select*/
    $("select").each(function (index,element) {
        var selectType = element.getAttribute("data-select-type");
        var id = element.id;
        if(selectType != undefined&&selectType != ''){
            console.log("初始化下拉框");
            getSelectType(id,selectType,null);
        }
        $("#"+id).on("change",function(a){
            if($("#"+id+"Name") != undefined){
                var options=$("#"+id+" option:selected");
                $("#"+id+"Name").val(options.text());
            }

        });
    });
});

function getSelectType(elementId,selectType,parentSelectTypeId) {
    $.ajax({
        url : '/common/dict/list',
        data:{
            limit: 10000,
            offset:0,
            'type':selectType,
            'parentId':parentSelectTypeId
        },
        success : function(data) {
            //加载数据
            var html = "<option value=''>请选择</option>";
            var rows = data.rows;
            for (var i = 0; i < rows.length; i++) {
                html += '<option value="' + rows[i].value + '">' + rows[i].name + '</option>'
            }
            $("#"+elementId).append(html);
            $("#"+elementId).chosen({
                maxHeight : 200
            });
        }
    });
}