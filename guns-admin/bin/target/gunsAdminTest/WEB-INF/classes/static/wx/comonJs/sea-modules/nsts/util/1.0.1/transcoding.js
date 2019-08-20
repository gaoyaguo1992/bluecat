define("nsts/util/1.0.1/transcoding", [ "jquery-debug" ], function(require,
		exports, module) {
	var $ = require("jquery-debug");
	var mapList = new List();
	var ts = {};
	$(function() {
		initData();
	});
	function initData() {
		var filepath = baseUrl + "/conf/transcoding.json";
		if (mapList.size() == 0) {
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : filepath,
				success : function(data) {
					for ( var m in data) {
						var map = new Map();
						for ( var i = 0; i < data[m].length; i++) {
							map.put(data[m][i].id, data[m][i].name);
						}
						mapList.add(m, map);
						mapList.size();
					}

				}
			})

		}
		//return mapList;
	}
	module.exports = ts;
	ts.transCoding = function(jName, key) {
		var name = key;
		if ((key != "未知") && (key != "") && (key != null)) {
			name = mapList.get($.trim(jName)).get($.trim(key));

		} else {
			name = "未知";
		}
		return name;
	}

	function Map() {
		this.keys = new Array();
		this.data = new Object();
		this.put = function(key, value) {
			if (this.data[key] == null) {
				this.keys.push(key);
			}
			this.data[key] = value;
		};
		this.get = function(key) {
			return this.data[key];
		};
		this.isEmpty = function() {
			return this.keys.length == 0;
		};
		this.size = function() {
			return this.keys.length;
		};
	}

	function List() {
		this.keys = new Array();
		this.map = new Map();
		this.add = function(key, value) {
			if (this.map[key] == null) {
				this.keys.push(key);
			}
			this.map[key] = value;
		};

		this.get = function(key) {
			return this.map[key];
		};

		this.isEmpty = function() {
			return this.keys.length == 0;
		};

		this.size = function() {
			return this.keys.length;
		};

	}
});
