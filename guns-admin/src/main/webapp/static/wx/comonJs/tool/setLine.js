define([ "jquery-debug",baseUrl+"/js/tool/highChars" ], function(require, exports,module) {
	var $ = require("jquery-debug");
	var Highcharts = require(baseUrl+"/js/tool/highChars");
	exports.setLine = function(variety, cateArr, series,unit) {
            var chart;
            Highcharts.setOptions({
                colors: ['#E00E36']
            });
            chart = new Highcharts.Chart({
                chart: {
                    renderTo: variety + "Chars",
//                    type: 'line',
                    marginRight: 10,
                    marginBottom: 25

                },
                title: {
                    text: '',
                    x: -20
                            //center
                },
                labels:{items:[{
            		html:cateArr[0],
            		style: {
            			left:0,
            			top: 220
            		}
            	},{
            		html:cateArr[1],
            		style: {
            			left:190,
            			top: 220
            		}
            	}]
                },
                xAxis: {
                	labels:{
                		enabled:false
                	}
//                	startOnTick:false,
//                	categories: cateArr
                	
                },
                yAxis: {
                	gridLineDashStyle: 'longdash',//长破折号线
                    title: {
                        text: unit,
                        style: {
                            color: '#333333',
                            fontSize: '12px'
                        }

                    }
//                    endOnTick: FALSE,
//                    MAXPADDING: 0.25
                },
                plotOptions: {
//                    area: {
//                        fillColor: {
//                            linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
//                            stops: [  // 红色渲染 #4572A7  rgba(69,114,167,0)   蓝色渲染 #7cb5ec rgba(124,181,236,0)
//                                [0, '#7cb5ec'], //Highcharts.getOptions().colors[0]
//                                [1, 'rgba(124,181,236,0)'] //Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')
//                            ]
//                        },
//                        lineWidth: 1,
//                        marker: {
//                            enabled: false
//                        },
//                        shadow: false,
//                        states: {
//                            hover: {
//                                lineWidth: 1
//                            }
//                        },
//                        threshold: null
//                    },
                	spline:{
                		marker: {
                           enabled: false
                        },
                        shadow: false,
                        states: {
                          hover: {
                        	  lineWidth: 0
                          }
                        },
                        threshold: null
                	}
                },
                tooltip: {
                    headerFormat: '<b>{series.name}</b><br />',
                    pointFormat: '金额：{point.y}'+unit
                },
                series: series
            });
            $(".highcharts-legend").remove();
    }
		});
		
