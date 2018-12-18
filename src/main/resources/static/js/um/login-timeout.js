$(function(){
	$.ajaxSetup({
		statusCode: {
	        999: function (data) {
	        	layer.confirm('登录超时，请重新登录！', {
	        		  btn: ['确定'] //按钮
        		}, function(){
        			window.open ('/web/index','_top');
        		})
	        }
	    }
	})
})