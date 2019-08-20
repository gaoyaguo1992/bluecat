(function() {
    function getScript(src) {
        document.write('<' + 'script src="' + src + '"' +
                ' type="text/javascript"><' + '/script>');
    }
    try{
        var c=window.localStorage
        getScript("http://api.map.baidu.com/api?v=2.0&ak=DEe280d8ef1428a5c510106bdcf1c833");
    }catch(e){
        alert("请关闭保护模式");
    }
})();
define(baseUrl+"/js/baidumap/bd.map", ["jquery-debug"],function(require, exports, module) {
    var $ = require("jquery-debug");
    (function(){
        function ComplexCustomOverlay(point, text,width,height){
          this._point = point;
          this.text = text;
          this.width=width;
          this.height = height;
        }
        BMap.pjoin=ComplexCustomOverlay;
        ComplexCustomOverlay.prototype = new BMap.Overlay();
        ComplexCustomOverlay.prototype.initialize = function(map){
          this._map = map;
          var div = this._div = document.createElement("div");
          div.style.position = "absolute";
          div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
          div.innerHTML=this.text;
          div.style.background=this.color?this.color:"#ff4400";
          div.style.width="55px";
          div.style.fontSize="12px";
          div.style.padding="0 5px 0 5px";
          div.style.lineHeight="25px";
          div.style.textAlign="center";
          div.style.height=this.height+"px";
          div.style.cursor="pointer";
          var self=this;
          div.onclick=function(){
              if(self.click)
                  self.click(self._map,self._point);
              return false;
          };
          this._map.getPanes().labelPane.appendChild(div);
          return div;
        };
        ComplexCustomOverlay.prototype.setPos=function(x,y){
            this._point.lng=x;
            this._point.lat=y;
        };
        ComplexCustomOverlay.prototype.setColor=function(color){
            this.color=color;
            if(this._div)this._div.style.background=color;
        };
        ComplexCustomOverlay.prototype.setClick=function(click){
            this.click=click;
        };
        ComplexCustomOverlay.prototype.draw = function(){
          var map = this._map;
          var pixel = map.pointToOverlayPixel(this._point);
          this._div.style.left = pixel.x-(this.width/2) +"px";
          this._div.style.top  = pixel.y-(this.height/2) + "px";
        };
    })();
    function getMap(target){
        var state = $.data(target, 'bmap');
        if(state.map){
            return state.map;
        }else{
            var map = new BMap.Map(target);
            state.map=map;
            return state.map;
        }
    }
    function init(target){
        var map=getMap(target);
        var opt=$.data(target, 'bmap').options;
        var point = new BMap.Point(opt.lng, opt.lat);
        map.centerAndZoom(point, opt.zoom);
        opt.dragging?map.enableDragging():map.disableDragging();
        opt.scrollWheelZoom?map.enableScrollWheelZoom():map.disableScrollWheelZoom();
        opt.doubleClickZoom?map.enableDoubleClickZoom():map.disableDoubleClickZoom();
        opt.keyboard?map.enableKeyboard():map.disableKeyboard();
        opt.inertialDragging?map.enableInertialDragging():map.disableInertialDragging();
        opt.continuousZoom?map.enableContinuousZoom():map.disableContinuousZoom();
        opt.pinchToZoom?map.enablePinchToZoom():map.disablePinchToZoom();
        opt.autoResize?map.enableAutoResize():map.disableAutoResize();
        opt.maxZoom?map.setMaxZoom(opt.maxZoom):0;
        opt.minZoom?map.setMinZoom(opt.minZoom):0;
        if(opt.dblclick)map.addEventListener("dblclick", function(param){opt.dblclick(param,map);});
        if(opt.rightdblclick)map.addEventListener("rightdblclick", function(param){opt.dblclick(param,map);});
        if(opt.click)map.addEventListener("click", function(param){opt.click(param,map);});
        if(opt.resize)map.addEventListener("resize", function(param){opt.resize(param,map);});
        if(opt.zoomend)map.addEventListener("zoomend",function(param){opt.zoomend(param,map);});
        var top_left_control = new BMap.NavigationControl();
        map.addControl(top_left_control);
    };
    $.bmapInfo=function(parent){
        var div=document.createElement("div");
        var left=(parent.offsetWidth-400)/2;
        var top=(parent.offsetHeight-240)/2;
        div.style.cssText="position:absolute;z-index:1000;top:"+top+"px;left:"+left+"px;width:400px;border:1px solid #ccc;color:#000";
        parent.appendChild(div);
        div.innerHTML+='<div style="height:40px;line-height:40px;background:#e6e6e6;">\
            <div class="cbtn" style="margin-left:20px;float:right;margin-right:20px;cursor:pointer">关闭</div>\
            <div style="margin-left:20px;float:left" class="portName"></div>\
        </div>\n\
        <div style="height:160px;background:#fff;padding:10px;">\n\
            <div class="pic_list" style="background:#ccc;height:80px;">\n\
            </div>\
            <div>\n\
                <table style="margin-left:2px;margin-top:10px;">\n\
                    <tr style="height:20px;line-height:20px;">\n\
                    <td style="color:#999;">地&nbsp;&nbsp;&nbsp;&nbsp;址：</td><td class="addr"></td></tr>\n\
                </table>\n\
            </div>\n\
            <div style="text-align:center;padding-top:10px;">\n\
                <div class="jump" style="background:#efefef;border:1px solid #ccc;text-align:center;width:120px;height:30px;line-height:30px;float:left;cursor:pointer;">视频监控</div>\n\
                <div class="jump" style="background:#efefef;border:1px solid #ccc;text-align:center;width:120px;height:30px;line-height:30px;float:left;margin-left:4px;cursor:pointer;">申请入库</div>\n\
                <div class="jump" style="background:#efefef;border:1px solid #ccc;text-align:center;width:120px;height:30px;line-height:30px;float:left;margin-left:4px;cursor:pointer;">物流申请</div>\n\
            </div>\n\
            <div class="pic_change" style="position:absolute;top:40px;right:2px;background-image:url('+baseUrl+'/js/baidumap/select.png);width:400px;height:80px;">\n\
            <div class="pic_prev" style="float:right;height:80px;width:40px;cursor:pointer;margin-right:10px;"></div>\n\
            <div class="pic_next" style="float:left;height:80px;width:40px;cursor:pointer;margin-left:10px;"></div></div>\n\
        </div>';
        $(div).find(".cbtn").click(function(){
            $(div).hide();
        });
        
        $(div).find(".pic_prev").click(function(){
            var piclist=$(div).find(".pic_list")[0];
            for(var i=0,l=piclist.childNodes.length;i<l;i++){
                if(piclist.childNodes[i].style.display!=="none"){
                    if(i===0){
                        $(piclist.childNodes[i]).hide();
                        $(piclist.childNodes[l-1]).show();return;
                    }else{
                        $(piclist.childNodes[i]).hide();
                        $(piclist.childNodes[i-1]).show();
                        return;
                    }
                }
            }
        });
        $(div).find(".pic_next").click(function(){
            var piclist=$(div).find(".pic_list")[0];
            for(var i=0,l=piclist.childNodes.length;i<l;i++){
                if(piclist.childNodes[i].style.display!=="none"){
                    if(i===l-1){
                        $(piclist.childNodes[i]).hide();
                        $(piclist.childNodes[0]).show();return;
                    }else{
                        $(piclist.childNodes[i]).hide();
                        $(piclist.childNodes[i+1]).show();
                        return;
                    }
                }
            }
        });
        return {
            show:function(){
                $(div).show();
            },
            bindFunc:function(func){
            	$(div).find(".jump").unbind().click(function(){
            		func($(this).html());
                });
            },
            setValue:function(obj){
                for(var i in obj){
                    $(div).find("."+i).html(obj[i]);
                }
                if(obj.picList){
                    var lsts=obj.pic_list.split(",");
                    var piclist=$(div).find(".pic_list")[0];
                    if(lsts.length>1){
                        $(div).find(".pic_change").show();
                    }else{
                        $(div).find(".pic_change").hide();
                    }
                    piclist.innerHTML="";
                    for(var i=0,l=lsts.length;i<l;i++){
                        var img = document.createElement("img");
                        img.src = picUrl + "/bmap/" + lsts[i];
                        img.style.cssText = "height:100%;width:100%;";
                        piclist.appendChild(img);
                        if(i!==0){
                            $(img).hide();
                        }
                    }
                }else{
                    $(div).find(".pic_change").hide();
                }
                $(div).show();
            }
        };
    };
    $.fn.bmap = function(options, param,param2) {
        if (typeof options === 'string') {
            return $.fn.bmap.methods[options](this, param,param2);
        }
        options = options || {};
        return this.each(function() {
            var state = $.data(this, 'bmap');
            if (state) {
                $.extend(state.options, options);
            } else {
                state = $.data(this, 'bmap', {
                    options: $.extend({}, $.fn.bmap.defaults, options),
                    markers:[],
                    boundarys:[],
                    lines:[],
                    micons:[],
                    pjions:[],
                    gc : new BMap.Geocoder() 
                });               
            }
            init(this);
        });
    };
    $.fn.bmap.methods = {
            options: function(jq) {
                return $.data(jq[0], 'bmap').options;
            },
            getBoundary:function(jq,data,opt){
                var bdary = new BMap.Boundary();
                console.info(data);
                opt=opt||{};
                var eventEnum=["click","dblclick","mousedown","mouseup","mouseout","mouseover","remove","lineupdate"];
                var map = $.data(jq[0], 'bmap').map;
                var boundarys= $.data(jq[0], 'bmap').boundarys;
                bdary.get(data.split("-")[0], function (rs) {
                	console.info(data);
                    var bounds;
                    var maxNum = -1, maxPly;
                    var count = rs.boundaries.length;
                    console.info(count);
                    for (var i = 0; i < count; i++) {
                        var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 1, strokeOpacity: 0.5, fillColor: data.split("-")[1], strokeColor: "#000000"});
                        map.addOverlay(ply);
                        boundarys.push(ply);
                        for(var j=0;j<eventEnum.length;j++){
                            if(opt[eventEnum[j]]){
                                ply.addEventListener(eventEnum[j],opt[eventEnum[j]]);
                            }
                        }
                        var arrPts = ply.getPoints();
                        if (arrPts.length > maxNum) {
                            maxNum = arrPts.length;
                            maxPly = ply;
                        }
                    }
                    if (maxPly) {
                        map.setViewport(maxPly.getPoints());
                    }
                });
            },
            getCenter:function(jq){
               return  $.data(jq[0], 'bmap').map.getCenter();
            },
            panTo:function(jq,param){
                var point = new BMap.Point(param.lng, param.lat);
                return $.data(jq[0], 'bmap').map.panTo(point);
            },
            setCenter:function(jq,param){
                var point = new BMap.Point(param.lng, param.lat);
                return $.data(jq[0], 'bmap').map.setCenter(point);
            },
            getAddress: function(jq, param) {
                var point = new BMap.Point(param.lng, param.lat);
                return $.data(jq[0], 'bmap').gc.getLocation(point, function(rs) {
                    var addComp = rs.addressComponents;
                    if(param.callbc)
                        param.callbc(addComp);
//                    alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
                });
            },
            addPJoin:function(jq,param,index){
                index=index||0;
                var point = new BMap.Point(param.lng, param.lat);
                var marker1 = new BMap.pjoin(point,param.text,param.width,param.height);
                if(param.click)marker1.setClick(param.click);
                if(param.color)marker1.setColor(param.color);
                $.data(jq[0], 'bmap').map.addOverlay(marker1);
                if($.data(jq[0], 'bmap').pjions[index]===undefined||$.data(jq[0], 'bmap').pjions[index]===null){
                    $.data(jq[0], 'bmap').pjions[index]=[];
                }
                $.data(jq[0], 'bmap').pjions[index].push(marker1);
                return marker1;
            },
            showPJoin:function(jq,index){
                index=index||0;
                for(var i=0;i<$.data(jq[0], 'bmap').pjions[index].length;i++){
                    $.data(jq[0], 'bmap').map.addOverlay($.data(jq[0], 'bmap').pjions[index][i]);
                }
            },
            clearPJoin:function(jq,index){
                index=index||0;
                for(var i=0;i<$.data(jq[0], 'bmap').pjions[index].length;i++){
                    $.data(jq[0], 'bmap').map.removeOverlay($.data(jq[0], 'bmap').pjions[index][i]);
                }
            },
            addMIcon:function(jq,param){
                var point = new BMap.Point(param.lng, param.lat);
                var marker1 = new BMap.micon(point,param.icon,param.width,param.height);
                if(param.click)marker1.setClick(param.click);
                $.data(jq[0], 'bmap').map.addOverlay(marker1);
                $.data(jq[0], 'bmap').micons.push(marker1);
                return marker1;
            },
            clearMIcon:function(jq){
                for(var i=0;i<$.data(jq[0], 'bmap').micons.length;i++){
                    $.data(jq[0], 'bmap').map.removeOverlay($.data(jq[0], 'bmap').micons[i]);
                }
            },
            showInfo:function(jq,param,func){
                if(!$.data(jq[0], 'bmap').bmapInfo){
                    var p=jq[0].parentNode;
                    p.style.position="relative";
                    $.data(jq[0], 'bmap').bmapInfo=$.bmapInfo(p);
                }
                $.data(jq[0], 'bmap').bmapInfo.setValue(param);
                $.data(jq[0],'bmap').bmapInfo.bindFunc(func)
            },
            addChild:function(jq){
                    var a=document.createElement("a");
                    a.className="btn";
                    a.innerHTML="物流服务申请";
                    a.href="../../page/logistics/logisticsApply.html";
                    jq[0].appendChild(a);  
            },
            addMarker:function(jq,param){
            	var point = new BMap.Point(param.lng, param.lat);
                var marker1 = new BMap.Marker(point);
                if(param.icon){
                    var myIcon = new BMap.Icon(param.icon,new BMap.Size(25,34),{anchor:new BMap.Size(10,34)});
                    marker1.setIcon(myIcon);
                }
                if(param.html){
                    var infoWindow1 = new BMap.InfoWindow(param.html);
                    marker1.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});
                }
                if(param.click){
                    marker1.addEventListener("click",function(){ param.click(param.clickparam);});
                }
                $.data(jq[0], 'bmap').map.addOverlay(marker1);
                $.data(jq[0], 'bmap').markers.push(marker1);
                return marker1;
            },
            showMarkers:function(jq){
                var map=$.data(jq[0], 'bmap').map;
                for(var i=0;i<$.data(jq[0], 'bmap').markers.length;i++){
                    map.addOverlay($.data(jq[0], 'bmap').markers[i]);
                }
            },
            hideMarkers:function(jq){
                var map=$.data(jq[0], 'bmap').map;
                for(var i=0;i<$.data(jq[0], 'bmap').markers.length;i++){
                    map.removeOverlay($.data(jq[0], 'bmap').markers[i]);
                }
            },
            showBoundarys:function(jq){
                var map=$.data(jq[0], 'bmap').map;
                for(var i=0;i<$.data(jq[0], 'bmap').boundarys.length;i++){
                    map.addOverlay($.data(jq[0], 'bmap').boundarys[i]);
                }
            },
            hideBoundarys:function(jq){
                var map=$.data(jq[0], 'bmap').map;
                for(var i=0;i<$.data(jq[0], 'bmap').boundarys.length;i++){
                    map.removeOverlay($.data(jq[0], 'bmap').boundarys[i]);
                }
            },
            removeMarker:function(jq,param){
                var markers=$.data(jq[0], 'bmap').markers;
                for(var i=0,l=markers.length;i<l;i++){
                    if(markers[i]===param){
                        $.data(jq[0], 'bmap').map.removeOverlay(param);
                        markers.splice(i,1);
                        return;
                    }
                }
            },
            addLine:function(jq,param,color,weight,opacity){
                var pts=[];
                color=color||"blue";
                weight=weight||2;
                opacity=opacity||0.5;
                for(var i=0;i<param.length;i+=2){
                    pts.push(new BMap.Point(param[i],param[i+1]));
                }
                var polyline=new BMap.Polyline(pts, {strokeColor:color, strokeWeight:weight, strokeOpacity:opacity});
                $.data(jq[0], 'bmap').map.addOverlay(polyline);
                $.data(jq[0], 'bmap').lines.push(polyline);
                return polyline;
            },
            clearLine:function(jq){
                for(var i=0;i<$.data(jq[0], 'bmap').lines.length;i++){
                    $.data(jq[0], 'bmap').map.removeOverlay($.data(jq[0], 'bmap').lines[i]);
                }
            },
            setZoom:function(jq,zoom){
                $.data(jq[0], 'bmap').map.setZoom(zoom);
            },
            getZoom:function(jq){
                return $.data(jq[0], 'bmap').map.getZoom();
            }
        };
        $.fn.bmap.defaults = {
            lng:116.404,
            lat:39.915,
            zoom:15,
            dragging:true,
            scrollWheelZoom:true,
            doubleClickZoom:false,
            keyboard:false,
            inertialDragging:false,
            continuousZoom:false,
            pinchToZoom:true,
            autoResize:true,
            dblclick:null,
            click:null
        };
});
//(function($){
//    function getMap(target){
//        var state = $.data(target, 'bmap');
//        if(state.map){
//            return state.map;
//        }else{
//            var map = new BMap.Map(target);
//            state.map=map;
//            return state.map;
//        }
//    }
//    function init(target){
//        var map=getMap(target);
//        var opt=$.data(target, 'bmap').options;
//        var point = new BMap.Point(opt.lng, opt.lat);
//        map.centerAndZoom(point, opt.zoom);
//        opt.dragging?map.enableDragging():map.disableDragging();
//        opt.scrollWheelZoom?map.enableScrollWheelZoom():map.disableScrollWheelZoom();
//        opt.doubleClickZoom?map.enableDoubleClickZoom():map.disableDoubleClickZoom();
//        opt.keyboard?map.enableKeyboard():map.disableKeyboard();
//        opt.inertialDragging?map.enableInertialDragging():map.disableInertialDragging();
//        opt.continuousZoom?map.enableContinuousZoom():map.disableContinuousZoom();
//        opt.pinchToZoom?map.enablePinchToZoom():map.disablePinchToZoom();
//        opt.autoResize?map.enableAutoResize():map.disableAutoResize();
//        opt.maxZoom?map.setMaxZoom(opt.maxZoom):0;
//        opt.minZoom?map.setMinZoom(opt.minZoom):0;
//        map.addEventListener("dblclick", opt.dblclick);
//    };
//    $.fn.bmap = function(options, param) {
//        if (typeof options === 'string') {
//            return $.fn.bmap.methods[options](this, param);
//        }
//        options = options || {};
//        return this.each(function() {
//            var state = $.data(this, 'bmap');
//            if (state) {
//                $.extend(state.options, options);
//            } else {
//                state = $.data(this, 'bmap', {
//                    options: $.extend({}, $.fn.bmap.defaults, options),
//                    markers:[]
//                });
//            }
//            init(this);
//        });
//    };
//    $.fn.bmap.methods = {
//        options: function(jq) {
//            return $.data(jq[0], 'bmap').options;
//        },
//        getCenter:function(jq){
//           return  $.data(jq[0], 'bmap').map.getCenter();
//        },
//        panTo:function(jq,param){
//            var point = new BMap.Point(param.lng, param.lat);
//            return $.data(jq[0], 'bmap').map.panTo(point);
//        },
//        addMarker:function(jq,param){
//            var point = new BMap.Point(param.lng, param.lat);
//            var marker1 = new BMap.Marker(point);
//            if(param.icon){
//                var myIcon = new BMap.Icon(param.icon,new BMap.Size(57,34),{anchor:new BMap.Size(10,34)});
//                marker1.setIcon(myIcon);
//            }
//            if(param.html){
//                var infoWindow1 = new BMap.InfoWindow(param.html);
//                marker1.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});
//            }
//            $.data(jq[0], 'bmap').map.addOverlay(marker1);
//            $.data(jq[0], 'bmap').markers.push(marker1);
//            return marker1;
//        },
//        removeMarker:function(jq,param){
//            var markers=$.data(jq[0], 'bmap').markers;
//            for(var i=0,l=markers.length;i<l;i++){
//                if(markers[i]===param){
//                    $.data(jq[0], 'bmap').map.removeOverlay(param);
//                    markers.splice(i,1);
//                    return;
//                }
//            }
//        }
//    };
//    $.fn.bmap.defaults = {
//        lng:116.404,
//        lat:39.915,
//        zoom:15,
//        dragging:true,
//        scrollWheelZoom:true,
//        doubleClickZoom:false,
//        keyboard:false,
//        inertialDragging:false,
//        continuousZoom:false,
//        pinchToZoom:true,
//        autoResize:true,
//        dblclick:function(){
//            
//        }
//    };
//})(jQuery);


