$(function(){
     //发布列表设置没有数据时候的高度end
    var Leng=$('.organ-lists .organ_li').length;
    var Lscreen=Leng * 600;
    if(Leng>2){
        $('.organ-choose').css('overflow-x','scroll');
        $('.organ-lists').css('width',Lscreen+'px');
    };//组织大于两个出滚动条
    $('.organ-choose').on('click','.organ_li .group_con .media-left',function(e){
        $(this).parents('.organ_li').addClass('act').siblings('.organ_li').removeClass('act');
        e.stopPropagation();
        var invite_id=$(this).find(".invite_ids").attr("data-invite");
        var index=$(this).parents('.organ_li').index();
        if($(this).attr("data-my")=="1"){
            $.post("/account/get_user_all",function (data) {
                $("#tab_cons").html(data);
            },"html")

        }else{
            var load=layer.load();
            var formData={invite_id:invite_id};
            $.post("/account/get_other_info",formData,function (data) {
                layer.close(load);
                $('.tab_cons').html(data);
                $('.tab_cons').show().siblings('.tab_list').hide();
            })

        }
    })//组织选择选项卡
    $('.social-lists').each(function(){
        var list_leng=$(this).find('li').length;
        if(list_leng>8){
            $(this).find('li:gt(9)').hide();
            $(this).next('.slide_more').show();
        }
    });//大于8个的部分隐藏。查看更多出现
    $('.slide_more').on('click',function(){
        if($(this).hasClass('act')){
            $(this).removeClass('act');
            $(this).prev('.social-lists').find('li:gt(9)').hide();
        }else{
            $(this).addClass('act');
            $(this).prev('.social-lists').find('li').show();
        }
    })//点击按钮查看全部
    $(document).on('click','.per-info_xiu',function(){
    	    $('.user-info1').hide();
        $('.user-info2').show();
    })
    $(document).on('click','.gai_cancel',function(){
      	$('.user-info1').show();
        $('.user-info2').hide();
    })
    //修改个人信息出现关闭
    $(document).off('click','.T-select').on('click','.T-select',function(){
        if($(this).parent('.Select_container').find('.select_lists').is(":visible")){
            $(this).parent('.Select_container').find('.select_lists').hide();
            $(this).find('.Select-btn').removeClass('act');
        }else{
            $(this).parents('.Select_container').find('.select_lists').show();
            $(this).find('.Select-btn').addClass('act');
        }
    })//模拟下拉框的展开和收起
    var arr = [],arr2 = [];
    $('.T-creat_lits').off('click','input[type="checkbox"]').on('click','input[type="checkbox"]',function(){
        var text = $(this).siblings('label').text();
        var name = $(this).attr('name');
        var id = $(this).val();
        var data_save = $("#invite_audit .add_shenhe").attr("data-save");
        if(data_save==1){
            arr = [];arr2 = [];
        }
        if($(this).prop('checked') == true)
        {
            $(this).siblings('label').addClass('act');
            if (name == 'invite_name') {
                $(".two_shenhe li input[type='checkbox']").each(function () {

                        if (id == $(this).val()) {
                            if(!$(this).parent().attr("data-admin")) {
                            $(this).parent().hide();
                        } else {
                            $(this).parent().show();
                        }
                    }
                })
                arr.push(text);
            }else{
                $(".one_shenhe li input[type='checkbox']").each(function () {

                        if(id==$(this).val()){
                            if(!$(this).parent().attr("data-admin")) {
                            $(this).parent().hide();
                        }else{

                            $(this).parent().show();
                        }
                    }
                })
                arr2.push(text);
            }
        }else{

            $(this).siblings('label').removeClass('act');
            if (name == 'invite_name'){
                $(".two_shenhe li input[type='checkbox']").each(function () {

                        if (id == $(this).val()) {
                            $(this).parent().show();
                        } else {
                            if(!$(this).parent().attr("data-admin")) {
                            $(this).parent().hide();
                        }
                    }
                })
                for(i in arr)
                {
                    if(arr[i] == text)
                    {
                        arr.splice(i,i == 0? 1: i);
                    }
                }
            }else{
                $(".one_shenhe li input[type='checkbox']").each(function () {

                        if (id == $(this).val()) {
                            $(this).parent().show();
                        } else {
                            if(!$(this).parent().attr("data-admin")) {
                            $(this).parent().hide();
                        }
                    }
                })
                for(i in arr2)
                {
                    if(arr2[i] == text)
                    {
                        arr2.splice(i,i == 0? 1: i);
                    }
                }
            }
        }
        var tempArr = name == 'invite_name' ? arr : arr2;
        var str = '';
        // var shen_cons=$('.shen_checked').html();
        // if()
        // tempArr.push(shen_cons);
        for(var i= 0;i<tempArr.length;i++){
            if(i==0){
                str += '<span>'+tempArr[0]+'</span>';
            }else{
                str += '<span>、'+tempArr[i]+'</span>';
            }

        }
        var shen_cons=$('.shen_checked').html();
        if(shen_cons){
        // var html_con=$(this).parents('.Select_container').find('.select-area').html();
            if(tempArr.length>=1){
                var shen_txt="<span class='shen_checked'>"+shen_cons+"</span>、"+str;
            }else{
                var shen_txt="<span class='shen_checked'>"+shen_cons+"</span>"
            }
        }else{
            var shen_txt=str;
        }
        $(this).parents('.Select_container').find('.select-area').empty().append(shen_txt);
    })//模拟下拉多选择
    $('.modal').each(function(){
            $(this).find('[data-dismiss="modal"]').click(function(){
                arr = [];arr2 = [];
                $(this).parents('.modal').find('.select-area').each(function(){
                    var txt=$(this).attr('data-tit');
                    $(this).html(txt);
                });
                $(this).parents('.modal').find('.select_lists').css('display','none');
                $(this).parents('.modal').find('input').val();
                $(this).parents('.modal').find('.T-creat_lits>li>input[type="checkbox"]').attr('checked',false);
                $(this).parents('.modal').find('.T-creat_lits>li>label').removeClass('act');
        })
    })//关闭弹窗后里面内容取消
    $('.Social_choose').on('click','input[type="radio"]',function(){
        if($(this).prop('checked') == true){
            if($(this).attr("data-acc")=="no"){
                $(".radiono_2").find("input").each(function () {
                    $(this).val("");
                })
                $(".radiono_1").find("input").each(function () {
                    $(this).val("");
                })
                $(".add_noaccredit").attr("disabled",true);
                if($(this).attr("id")=='radio1'){

                    $(".radiono_2").hide();
                    $(".radiono_1").show();
                }else{

                    $(".radiono_2").show();
                    $(".radiono_1").hide();
                }
            }
            var text = $(this).siblings('label').html();
            $(this).parents('.Select_container').find('.select-area').empty().append(text);
            $(this).parents('.select_lists').hide();
            $(this).parents('.Select_container').find('.Select-btn').removeClass('act');
        }
    });//授权账号选择
    $('.auther-lists').on('click','li',function(){
        var index=$(this).index();
        if(index==0){
            $('.auther_btn').show();
            $('.no-auther_btn').hide();
        }else{
            $('.auther_btn').hide();
            $('.no-auther_btn').show();
        }
    })//授权非授权按钮切换
    $('.account_departs').each(function(){
        var Leng=$(this).find('span').length;
        var nums=Leng-2;
        if(Leng>2){
            $(this).find('span:gt(1)').hide();
            $(this).append('<i class="label label-outline label-primary">+'+nums+'</i>')
        }
    })//部门名称只显示两个多余显示个数
    $('.modal').on('click','.btn-pure,.close',function(){
        $(this).parents('.modal').find('input[type="text"],textarea').val('');
        var txt=$(this).parents('.modal').find('.select-area').attr('title');
        $(this).parents('.modal').find('.select-area').text(txt);
        $('.wx-tishi').hide();
    })//取消弹窗后填写清空
    $('.auther-lists').on('click','li',function(){
        var index=$(this).index();
        $('.account-selects').hide();
        if(index==0){
            $('.account-accredit_select').show();
        }else{
            $('.account-noaccredit_select').show();
        }
    })//非授权批量添加出现
    $('.gai_tean-contro').on('click','button',function(){
        $(this).parent('.gai_tean-contro').hide();
        $(this).parent('.gai_tean-contro').next('.gai_team-name').show();
    });
    $('.gai_team-name').on('click','button',function(){
        $(this).parent('.gai_team-name').hide();
        $(this).parent('.gai_team-name').prev('.gai_tean-contro').show();
    })//团队详情名称修改
    $('.add_ul-platform').off('click',"li").on('click','li',function(e){
        $(this).addClass('act').siblings('li').removeClass('act');
        var index=$(this).index();
        $('.add_select-td ul').eq(index).show().siblings('ul').hide();
        switch (index){
            case 0:
                var obj=$('.add_select-td ul').eq(index).find("li").eq(0)
                if(obj.attr("data-ctype")<=2){
                    // obj.trigger("click");
                }else{
                    $(".add_ul-account").empty();
                }
                break;
            case 1:
                var obj1=$('.add_select-td ul').eq(index).find("li").eq(0)
                if(obj1.attr("data-ctype")<=2){
                    obj1.trigger("click");
                }else{
                    $(".add_ul-account").empty();
                }
                break;
            default:
                $(".add_ul-account").empty().html("");
                break;
        }
    })//我的主页平台选择
    $('.add_ul-module').off("click","li").on('click','li',function(){
        $('.add_ul-module li').removeClass('act');
        $(this).addClass('act').siblings('li').removeClass('act');
        var formData={};
        formData.filter_type=$(this).attr("data-filter");
        formData.group_type=$(this).attr("data-group");
        formData.look_type=$(this).attr("data-look");
        if(formData.filter_type&&formData.group_type){
            $(".add_ul-account").empty().html('<div id="progressBar" class="build-progressBar" style="width: 100% !important;">加载中...</div>')
            $.post("/index/get_account",formData,function (data) {
                $(".add_ul-account").empty().html(data);
                if(formData.look_type==3&&formData.group_type==1&&formData.filter_type==1){
                    $(".add_ul-account").addClass("max_10");
                    $(".max_10 input[type='checkbox']").on("click",function () {
                        $(".max_10 input[type='checkbox']").attr("disabled",false);
                        var len=$(".max_10 input[type='checkbox']:checked").length
                        if(len>=10){
                            $(".max_10 input[type='checkbox']").attr("disabled",true);
                            $(".max_10 input[type='checkbox']:checked").attr("disabled",false);
                        }
                    })
                }else{
                    $(".add_ul-account").removeClass("max_10");

                }
            },"html")
        }else{
            $(".add_info-btn").trigger("click")
        }
    })//我的主页模块选择
    $('.um-card_img_box').each(function(){
        var Len=$(this).find('li').length;
        $(this).find('ul').removeClass('blocks-1 blocks-2 blocks-3')
        $(this).find('ul').attr('dataimg',' ');
        if(Len==1){
            $(this).find('ul').addClass('blocks-1') ;
            $(this).find('ul').attr('dataimg','1');
        }else if(Len==2||Len==4){
            $(this).find('ul').addClass('blocks-2') ;
            $(this).find('ul').attr('dataimg','2');
        }else if(Len>=3&&Len!=4){
            $(this).find('ul').addClass('blocks-3') ;
            $(this).find('ul').attr('dataimg','3');
        }
    });//图片不同个数不同预览效果
    // $('.tm-m-photos-thumb').on('click','li',function(){
    //     var datanum=$(this).parent('.tm-m-photos-thumb').attr('dataimg');
    //     $(this).parent('.tm-m-photos-thumb').addClass('act').removeClass('blocks-'+datanum);//去掉页面里的blocks值
    // })//预览的时候整体列表图变小
    // $(document).on('click','.tm-m-photos-thumb li',function(){
    //     var datanum=$(this).parent('.tm-m-photos-thumb').attr('dataimg');
    //     $(this).parent('.tm-m-photos-thumb').addClass('act').removeClass('blocks-'+datanum);//去掉页面里的blocks值
    // })//预览的时候整体列表图变小2 ajax 加载之后的
    $(document).on('click','.tm-m-photo-viewer',function(){
        var datanum=$(this).parents('.tm-m-photos').find('.tm-m-photos-thumb').attr('dataimg');
        $(this).hide();
        $(this).parents('.tm-m-photos').find('.tm-m-photos-thumb').addClass('blocks-'+datanum);
        $(this).parents('.tm-m-photos').find('.tm-m-photos-thumb').removeClass('act');
    })//图片预览的时候 点击正在看的大图 ，恢复到之前
    $(document).on("click",'.video_link', function() {
        $('#videoModal').modal('show');
        var $this = $(this);
        var title = $this.attr('title');
        var myPlayer = videojs('my-video');
        var videoUrl = $(this).attr("videohref");
        videojs("my-video", {}, function() {
            window.myPlayer = this;
            $(".videoModal .modal-body #my-video source").attr("src", videoUrl);
            $(".videoModal .modal-title").html(title);
            myPlayer.src(videoUrl);
            myPlayer.load(videoUrl);
            myPlayer.play();
        });
        $(".click-modal").click();
        video_pause();
    });
    // 模态窗消失时，关闭视频
    $('.videoModal').on('hidden.bs.modal', function() {
        myPlayer.pause();
    });//设置视频播放
    function video_pause(){
        $('.videoModal').on('hidden.bs.modal', function() {
            myPlayer.pause();
        });//设置视频播放
    }

    $('.wb_account_list').each(function(){
        var Lang=$(this).find('ul li').length,
            nums=Lang-14;
        if(Lang>14){
            $(this).find('ul li:gt(13)').hide();
            $(this).find('ul .last_num span').show();
            $(this).find('ul .last_num span').html('+'+nums);
        }else{
            $(this).find('ul .last_num span').hide();
        }
    });//发布列表默认显示14个
    // $(document).on('click','.collection_ico',function(){
    //     if($(this).find('.ico').hasClass('icon-shoucang1')){
    //         $(this).find('.ico').removeClass('icon-shoucang1').addClass('icon-shoucang');
    //     }else{
    //         $(this).find('.ico').addClass('icon-shoucang1').removeClass('icon-shoucang');
    //     }
    // })//navbar的收藏
});
//微博板块展开更多
function more_btn(me){
    if($(me).parents(".panel-body").hasClass('show_more')){
        $(me).parents(".panel-body").removeClass('show_more');
        $(me).parents(".panel-body").find(".wb_account_list").removeClass('list-inline');
        $(me).parents(".panel-body").find(".wb_account_list li").show();
    }else{
        $(me).parents(".panel-body").addClass('show_more');
        $(me).parents(".panel-body").find(".wb_account_list").addClass('list-inline');
        $(me).parents(".panel-body").find(".wb_account_list li:gt(13)").hide();
    }
}//发布列表大于14个 微博头像不显示，展开显示全部
function show_net_length(){
    $('.wb_account_list').each(function(){
        var Lang=$(this).find('ul li').length,
            nums=Lang-14;
        if(Lang>14){
            $(this).find('ul li:gt(13)').hide();
            $(this).find('ul .last_num span').show();
            $(this).find('ul .last_num span').html('+'+nums);
        }else{
            $(this).find('ul .last_num span').hide();
        }
    });//发布列表默认显示14个
}
//ajax 加载调用
function img_nums_show(){
    $('.um-card_img_box').each(function(){
        var Len=$(this).find('li').length;
        $(this).find('ul').removeClass('blocks-1 blocks-2 blocks-3')
        $(this).find('ul').attr('dataimg',' ');
        if(Len==1){
            $(this).find('ul').addClass('blocks-1') ;
            $(this).find('ul').attr('dataimg','1');
        }else if(Len==2||Len==4){
            $(this).find('ul').addClass('blocks-2') ;
            $(this).find('ul').attr('dataimg','2');
        }else if(Len>=3&&Len!=4){
            $(this).find('ul').addClass('blocks-3') ;
            $(this).find('ul').attr('dataimg','3');
        }
    });//图片不同个数不同预览效果
}
//没有数据时候的动态高度
function nodata(){
    var Height=$(window).height();
    $('.publish_nodata').height(Height-220);
    $('.publish_nodata2').height(Height-262);
    $('.publish_nodata3').height(Height-150);
};
var HtmlUtil = {
    /*1.用正则表达式实现html转码*/
    htmlEncodeByRegExp:function (str){
        var s = "";
        if(str!=null) {
            if (str.length == 0) return "";
            s = str.replace(/&/g, "&amp;");
            s = s.replace(/</g, "&lt;");
            s = s.replace(/>/g, "&gt;");
            // s = s.replace(/ /g,"&nbsp;");
            s = s.replace(/\'/g, "&#39;");
            s = s.replace(/\"/g, "&quot;");
            return s;
        }else{
            return '';
        }
    },
    /*2.用正则表达式实现html解码*/
    htmlDecodeByRegExp:function (str){
        var s = "";
        if(str!=null) {
            if (str.length == 0) return "";
            s = str.replace(/&amp;/g, "&");
            s = s.replace(/&lt;/g, "<");
            s = s.replace(/&gt;/g, ">");
            // s = s.replace(/&nbsp;/g," ");
            s = s.replace(/&#39;/g, "\'");
            s = s.replace(/&quot;/g, "\"");
            return s;
        }else{
            return '';
        }
    }
};
//微博板块展开更多
function more_btn(me){
    if($(me).parents(".panel-body").hasClass('show_more')){
        $(me).parents(".panel-body").removeClass('show_more');
        $(me).parents(".panel-body").find(".wb_account_list").removeClass('list-inline');
        $(me).parents(".panel-body").find(".wb_account_list li").show();
    }else{
        $(me).parents(".panel-body").addClass('show_more');
        $(me).parents(".panel-body").find(".wb_account_list").addClass('list-inline');
        $(me).parents(".panel-body").find(".wb_account_list li:gt(13)").hide();
    }
}//发布列表大于14个 微博头像不显示，展开显示全部
function leadCon(text,i){
    var leadhtml='<div class="lead-con clearfix">' +
        '<img src="/static/images/jiantou.png" class="jiantou"/>' +
        '<div class="lead-text clearfix"><p class="width-350 pull-right">'+text+'</p></div>' +
        '<div class="btn-text text-right margin-top-15"><span class="font-size-14 padding-right-50 step-text"><i>'+i+'</i>/12</span> <a href="javascript:;" class="inline-block next-btn" data-id="1"><img src="/static/images/next-img.png"/> </a><span class="skip-btn" onclick="leadcancel_p()">跳过</span></div>' +
        '</div>'
    var black='<div class="modal-backdrop fade in"></div>';
    $('body').append(black);
    $('body').append(leadhtml);
};//灰色背景和引导模板
function leadCon2(text,i,e){
    var leadhtml='<div class="lead-con2 clearfix">' +
        '<img src="/static/images/jiantou.png" class="jiantou"/>' +
        '<div class="lead-text clearfix"><p class="width-350 pull-right">'+text+'</p></div>' +
        '<div class="btn-text text-right margin-top-15"><span class="font-size-14 padding-right-50 step-text"><i>'+i+'</i>/12</span> <a href="javascript:;" class="inline-block next-btn" data-id="5"><img src="/static/images/next-img.png"/> </a><span class="skip-btn" onclick="leadcancel_p()">跳过</span></div>' +
        '</div>';
    var navhtml='<div class="aside-nav">' +
        '<div class="list-group">' +
        '<a class="list-group-item padding-vertical-25 active" href="javascript:void(0)">'+e+'</a>' +
        '</div>' +
        '</div>';
    var black='<div class="modal-backdrop fade in"></div>';

    $('body').append(black);
    $('body').append(leadhtml);
    $('body').append(navhtml);
};//灰色背景和引导模板
function leadCon3(text,i){
    if(i==12){
        var leadhtml='<div class="lead-con3 clearfix"><div class="pull-left">' +
            '<div class="lead-text clearfix"><p class="width-350 pull-right">'+text+'</p></div>' +
            '<div class="btn-text text-right margin-top-15"><span class="font-size-14 padding-right-50 step-text"><i>'+i+'</i>/12</span> <a href="javascript:;" class="inline-block next-btn last-btn"><img src="/static/images/end.png"/> </a><span class="skip-btn" onclick="leadcancel_p()">跳过</span></div></div>' +
            '<img src="/static/images/jiantou.png" class="jiantou pull-right"/>' +
            '</div>';
    }else{
        var leadhtml='<div class="lead-con3 clearfix"><div class="pull-left">' +
            '<div class="lead-text clearfix"><p class="width-350 pull-right">'+text+'</p></div>' +
            '<div class="btn-text text-right margin-top-15"><span class="font-size-14 padding-right-50 step-text"><i>'+i+'</i>/12</span> <a href="javascript:;" class="inline-block next-btn last-btn"><img src="/static/images/next-img.png"/> </a><span class="skip-btn" onclick="leadcancel_p()">跳过</span></div></div>' +
            '<img src="/static/images/jiantou.png" class="jiantou pull-right"/>' +
            '</div>';
    }

    $('body').append(leadhtml);
};//灰色背景和引导模板
function set(e){
    var x=e.position().top;
    var y=e.offset().left;
    var thish=e.height();
    $('.aside-nav').css({'top':x+70,'left':y});
    $('.lead-con2').css({'top':x,'left':y+200});
}//二级导航引导
function leadcancel(){
    $('.add-author,.add-noauthor,.help-navs').removeClass('act');
    $('.next-btn').attr('data-id','1');
    $('.modal-backdrop,.lead-con,.lead-con2,.lead-con3').remove()
}//退出引导images/next-img.png
function leadcancel_e() {
    $.post("/leadweb/save_s","",function (data) {
        if(data.code==200){
            location.href="/account/index"
        }
    },"json")
    leadcancel();
}
function leadcancel_p() {
    $("#skip").modal("show")
}
$(document).off('click','#selet_member').on('click','#selet_member',function(){
    if($(this).prop("checked")){
        $(this).parents('.modal').find('.auth_ulcon li>.cards-item').addClass('act');
    }else{
        $(this).parents('.modal').find('.auth_ulcon li>.cards-item').removeClass('act');
    }
})
//动态获取屏幕高度并赋值给二级导航菜单