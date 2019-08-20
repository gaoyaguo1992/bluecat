var Nsts = Nsts || {};
Nsts.Header = function() {
    var g = function(v) {
        return typeof(v) != "string" ? v : document.getElementById(v)
    }, s = navigator.userAgent.toLowerCase(), o = /msie/.test(s) && !/opera/.test(s), l = o && !/msie 7/.test(s) && !/msie 8/.test(s);
    var i = {getCookie: function(w) {
            var v = document.cookie.match("(?:^|;)\\s*" + w + "=([^;]*)");
            return(v && v[1]) ? decodeURIComponent(v[1]) : ""
        }, parseQueryParams: function(B) {
            var y = {};
            var w = B.split("&");
            for (var z = 0, A = w.length; z < A; ++z) {
                var x = w[z], C = x.search("=");
                var D = x.substring(0, C);
                var v = x.substring(C + 1, x.length);
                y[decodeURIComponent(D)] = decodeURIComponent(v)
            }
            return y
        }, trim: function(v) {
            return v.replace(/^\s+|\s+$/g, "")
        }, hasClass: function(w, v) {
            w = g(w);
            if (!w || !v || !w.className) {
                return false
            }
            return(" " + w.className + " ").indexOf(" " + v + " ") > -1
        }, addClass: function(w, v) {
            w = g(w);
            if (!w || !v) {
                return
            }
            if (this.hasClass(w, v)) {
                return
            }
            w.className += " " + v
        }, removeClass: function(w, v) {
            w = g(w);
            if (!this.hasClass(w, v)) {
                return
            }
            w.className = w.className.replace(new RegExp(v, "g"), "");
            if (!this.trim(w.className)) {
                w.removeAttribute(o ? "className" : "class")
            }
        }, addEvent: function(x, w, v) {
            x = g(x);
            if (!x || !w || typeof(v) != "function") {
                return
            }
            if (x.addEventListener) {
                x.addEventListener(w, v, false)
            } else {
                if (x.attachEvent) {
                    x.attachEvent("on" + w, v)
                }
            }
        }, stopEvent: function(v) {
            if (v.stopPropagation) {
                v.stopPropagation()
            } else {
                v.cancelBubble = true
            }
            if (v.preventDefault) {
                v.preventDefault()
            } else {
                v.returnValue = false
            }
        }, getElementsByClassName: function(w, B, v, A) {
            if (!g(v)) {
                return
            }
            var x = [], z = g(v).getElementsByTagName(B), y = 0;
            for (; y < z.length; y++) {
                if (i.hasClass(z[y], w)) {
                    x[x.length] = z[y];
                    arguments[3] && arguments[3].call(z[y])
                }
            }
            return x
        }, escapeHTML: function(w) {
            var x = document.createElement("div");
            var v = document.createTextNode(w);
            x.appendChild(v);
            return x.innerHTML
        }};
    var e = i.getCookie("tracknick"), t = i.getCookie("_nk_") || e, j = i.getCookie("uc1"), d = i.parseQueryParams(j), q = i.getCookie("_l_g_") && t || i.getCookie("ck1") && e, p = parseInt(d._msg_) || 0, k = new Date().getTime(), r = (document.location.href.indexOf("https://") === 0);
    function a(x) {
        if (!x) {
            return
        }
        var w = i.getElementsByClassName("menu-bd", "div", x)[0];
        if (!w) {
            return
        }
        if (!r) {
            var v = document.createElement("iframe");
            v.src = "about: blank";
            v.className = "menu-bd";
            x.insertBefore(v, w);
            x.iframe = v
        }
        x.menulist = w;
        x.onmouseenter = function() {
            i.addClass(this.parentNode, "hover");
            if (r) {
                return
            }
            this.iframe.style.height = parseInt(this.menulist.offsetHeight) + 25 + "px";
            this.iframe.style.width = parseInt(this.menulist.offsetWidth) + 1 + "px"
        };
        x.onmouseleave = function() {
            i.removeClass(this.parentNode, "hover")
        }
    }
}();
