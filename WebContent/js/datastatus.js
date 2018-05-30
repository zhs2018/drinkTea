var nameList = [];
var scoreList = [];
var jsonList=[];
function getChartData(sTime, url, main) {
	$.ajax({
		url : url,
		type : "post",
		data : {
			"sTime" : sTime
		},
		dataType : "json",
		success : function(data) {
			if (data.result == "ok") {
				if (null == nameList || nameList.length == 0
						|| null == scoreList || scoreList == 0) {
					var list = data.data;
					for (var i = 0; i < list.length; i++) {
						nameList.push(list[i].applyTime);
						scoreList.push(list[i].totalPrice);
					}
					md(nameList, scoreList, main);
				} else {
					nameList = [];
					scoreList = [];
					var list = data.data;
					for (var i = 0; i < list.length; i++) {
						nameList.push(list[i].applyTime);
						scoreList.push(list[i].totalPrice);
					}
					md(nameList, scoreList, main);
				}
			} else if (data.result == "no") {
				nameList = [];
				scoreList = [];
				md(nameList, scoreList, main);
				/*layer.confirm("您确定删除该角色吗？",{
					title:"删除角色",
					btn: ['留着吧']
				},
				function(){
					layer.close();
				}
				);*/
              alert("本月暂无数据!");
			}
		}
	});
	function md(nameList, scoreList, main) {
		// 路径配置
		require.config({
			paths : {
				echarts : '/drinkTea/js/build/dist'
			}
		});
		// 使用
		require([ 'echarts',
		          'echarts/chart/line',
		          'echarts/chart/bar',
				  'echarts/chart/pie',
				  'echarts/chart/map'// 使用不同图形加载line模块，按需加载
		], function(ec) {
			// 基于准备好的dom，初始化echarts图表
			eCharts = ec;
			myChart = eCharts.init(main,'dark');
			options = {
				title : {
					text : '月收入金额变化图',
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					padding : 10,
					itemGap : 20,
					data : [ '最高收入']
				},
				grid : {
					y2 : 75
				},
				toolbox : {
					show : true,
					feature : {
						dataZoom : {
							yAxisIndex : 'none'
						},
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar','stack','tiled']
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					position : 'bottom',
					boundaryGap : false,
					axisLine : { // 轴线
						show : true,
						lineStyle : {
							color : 'green',
							type : 'solid',
							width : 2
						}
					},
					axisTick : { // 轴标记
						show : true,
						length : 10,
						lineStyle : {
							color : 'red',
							type : 'solid',
							width : 2
						}
					},
					axisLabel : {
						show : true,
						interval : 'auto', // {number}
						rotate : 45,
						margin : 8,
						formatter : '{value}天',
						textStyle : {
							color : 'blue',
							fontFamily : 'sans-serif',
							fontSize : 15,
							fontStyle : 'italic',
							fontWeight : 'bold'
						}
					},
					splitLine : {
						show : true,
						lineStyle : {
							color : '#483d8b',
							type : 'dashed',
							width : 1
						}
					},
					splitArea : {
						show : true,
						areaStyle : {
							color : [ 'rgba(144,238,144,0.3)',
									'rgba(135,200,250,0.3)' ]
						}
					},
					data : nameList
				} ],
				yAxis : [ {
					type : 'value',
					splitNumber : 10,
					axisLabel : {
						formatter : '{value} 元'
					},
					splitArea : {
						show : true
					}
				} ],
				series : [
						{
							name : '最高收入',
							type : 'line',
							itemStyle : {
								normal : {
									lineStyle : {
										shadowColor : 'rgba(0,0,0,0.4)'
									}
								}
							},
							data : scoreList,
						 /*markPoint : {
						 data : [
						     {name : '天最高', value :3 , xAxis: '', yAxis: 6}
						 ]
						} "获取最大值，最小值，暂时没用，后台取数据直接传过来"*/
						}
						]
			};
			myChart.setOption(options); //先把可选项注入myChart中
			myChart.hideLoading();
		});
	}
}