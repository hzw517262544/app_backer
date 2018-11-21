$().ready(function() {
    /*加载select*/
    $("select").each(function (index,element) {
        var selectType = element.getAttribute("data-select-type");
        var selectTypeName = element.getAttribute("data-select-type-name");
        var id = element.id;
        if(selectType != undefined&&selectType != ''){
            console.log("初始化下拉框");
            getSelectType(id,selectType,null);
        }

        $("#"+id).on("change",function(a){
            if($("#"+selectTypeName) != undefined&&$("#"+selectTypeName) != ''&&$("#"+selectTypeName) != null){
                var options=$("#"+id+" option:selected");
                $("#"+selectTypeName).val(options.text());
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
            // var html = "";
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