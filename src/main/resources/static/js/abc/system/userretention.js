require(["../../config"], function () {
    require(["jquery.full","../abc/util/date"], function ($) {
		layui.use('laydate', function() {
			var laydate = layui.laydate;
			laydate.render({
				elem: '#test4'
				,type: 'month'
				,theme: '#14b9d5'
			});
			laydate.render({
				elem: '#test5'
				,type: 'month'
				,theme: '#14b9d5'
			});

		})
      $(function () {
         //查询
    	 $("#consumer_query").click(function(){
			 var startTime = $("input[name='startTime']").val();
			 var endTime = $("input[name='endTime']").val();
			 if((startTime !=null && startTime!="")&&(endTime !=null && endTime!="")){
				 if(CompareDate(startTime,endTime)){
					 abc.layerAlert(false, "结束时间不能小于开始时间");
					 return;
				 }

				 if(monthDiff(startTime,endTime)>12){
					 abc.layerAlert(false, "查询时间起止间隔不超过12个月");
					 return;
				 }
			 }
			 abc.block();
			 $('#consumerListForm').submit();
    	 });

		  function CompareDate(d1,d2)
		  {
			  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
		  }

		  function monthDiff(d1, d2) {
			  var strSeparator = "-"; //日期分隔符
			  var oDate1;
			  var oDate2;
			  oDate1= d1.split(strSeparator);
			  oDate2= d2.split(strSeparator);
			  var strDateS = new Date(oDate1[0], oDate1[1]-1, 1);
			  var strDateE = new Date(oDate2[0], oDate2[1]-1, 1);
			  var months;
			  months = (strDateE.getFullYear() - strDateS.getFullYear()) * 12;
			  months -= strDateS.getMonth() + 1;
			  months += strDateE.getMonth()+2;
			  return months <= 0 ? 0 : months;
		  }

	  });
    })
});
