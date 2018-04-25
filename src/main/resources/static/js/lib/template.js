/*! art-template@4.0.0-beta | https://github.com/aui/art-template */
!function (e, t) {
    "object" == typeof exports && "object" == typeof module ? module.exports = t() : "function" == typeof define && define.amd ? define([], t) : "object" == typeof exports ? exports.template = t() : e.template = t()
}(this, function () {
    return function (e) {
        function t(r) {
            if (n[r])return n[r].exports;
            var o = n[r] = {i: r, l: !1, exports: {}};
            return e[r].call(o.exports, o, o.exports, t), o.l = !0, o.exports
        }

        var n = {};
        return t.m = e, t.c = n, t.i = function (e) {
            return e
        }, t.d = function (e, n, r) {
            t.o(e, n) || Object.defineProperty(e, n, {configurable: !1, enumerable: !0, get: r})
        }, t.n = function (e) {
            var n = e && e.__esModule ? function () {
                return e.default
            } : function () {
                return e
            };
            return t.d(n, "a", n), n
        }, t.o = function (e, t) {
            return Object.prototype.hasOwnProperty.call(e, t)
        }, t.p = "", t(t.s = 7)
    }([function (e, t, n) {
        "use strict";
        var r = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (e) {
            return typeof e
        } : function (e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        }, o = n(20), i = n(2), u = function e(t) {
            var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {};
            "object" === (void 0 === t ? "undefined" : r(t)) ? n = t : n.source = t, n = i.$extend(n), t = n.source;
            var u = n.debug, s = n.filename, a = n.cache;
            if (a && s) {
                var c = a.get(s);
                if (c)return c
            }
            if (!t)try {
                var l = n.resolveFilename(s, n.root);
                t = n.loader(l), n.filename = l, n.source = t
            } catch (e) {
                var f = {path: s, name: "CompileError", message: "template not found: " + e.message, stack: e.stack};
                if (n.bail)throw u(f)(), f;
                return u(f)
            }
            var p = new o(n), y = function t(r) {
                try {
                    return t.source(r)
                } catch (t) {
                    if (!n.compileDebug)return n.cache = null, n.compileDebug = !0, e(n)(r);
                    if (n.bail)throw u(t)(), t;
                    return u(t)()
                }
            };
            try {
                y.source = p.build(), a && s && a.set(s, y)
            } catch (e) {
                if (n.bail)throw u(e)(), e;
                return u(e)
            }
            return y.toString = function () {
                return y.source.toString()
            }, y
        };
        e.exports = u
    }, function (e, t, n) {
        "use strict";
        (function (t) {
            e.exports = !1;
            try {
                e.exports = "[object process]" === Object.prototype.toString.call(t.process)
            } catch (e) {
            }
        }).call(t, n(11))
    }, function (e, t, n) {
        "use strict";
        var r = n(13), o = n(12), i = n(15), u = n(17), s = n(16), a = n(6), c = n(14), l = n(19), f = n(18), p = {
            source: null,
            filename: null,
            rules: [l, f],
            escape: i,
            include: s,
            resolveFilename: a,
            cache: o,
            compress: null,
            imports: {$each: c, $escape: i, $include: s},
            debug: r,
            loader: u,
            compileDebug: !1,
            bail: !1,
            root: "/"
        };
        p.$extend = function (e) {
            var t = Object.create(this);
            for (var n in e)t[n] = e[n];
            return t
        }, e.exports = p
    }, function (e, t, n) {
        "use strict"
    }, function (e, t, n) {
        "use strict";
        Object.defineProperty(t, "__esModule", {value: !0}), t.default = /((['"])(?:(?!\2|\\).|\\(?:\r\n|[\s\S]))*(\2)?|`(?:[^`\\$]|\\[\s\S]|\$(?!\{)|\$\{(?:[^{}]|\{[^}]*\}?)*\}?)*(`)?)|(\/\/.*)|(\/\*(?:[^*]|\*(?!\/))*(\*\/)?)|(\/(?!\*)(?:\[(?:(?![\]\\]).|\\.)*\]|(?![\/\]\\]).|\\.)+\/(?:(?!\s*(?:\b|[\u0080-\uFFFF$\\'"~({]|[+\-!](?!=)|\.?\d))|[gmiyu]{1,5}\b(?![\u0080-\uFFFF$\\]|\s*(?:[+\-*%&|^<>!=?({]|\/(?![\/*])))))|(0[xX][\da-fA-F]+|0[oO][0-7]+|0[bB][01]+|(?:\d*\.\d+|\d+\.?)(?:[eE][+-]?\d+)?)|((?!\d)(?:(?!\s)[$\w\u0080-\uFFFF]|\\u[\da-fA-F]{4}|\\u\{[\da-fA-F]+\})+)|(--|\+\+|&&|\|\||=>|\.{3}|(?:[+\-\/%&|^]|\*{1,2}|<{1,2}|>{1,3}|!=?|={1,2})=?|[?~.,:;[\](){}])|(\s+)|(^$|[\s\S])/g, t.matchToToken = function (e) {
            var t = {type: "invalid", value: e[0]};
            return e[1] ? (t.type = "string", t.closed = !(!e[3] && !e[4])) : e[5] ? t.type = "comment" : e[6] ? (t.type = "comment", t.closed = !!e[7]) : e[8] ? t.type = "regex" : e[9] ? t.type = "number" : e[10] ? t.type = "name" : e[11] ? t.type = "punctuator" : e[12] && (t.type = "whitespace"), t
        }
    }, function (e, t, n) {
        "use strict";
        var r = {
            abstract: !0,
            await: !0,
            boolean: !0,
            break: !0,
            byte: !0,
            case: !0,
            catch: !0,
            char: !0,
            class: !0,
            const: !0,
            continue: !0,
            debugger: !0,
            default: !0,
            delete: !0,
            do: !0,
            double: !0,
            else: !0,
            enum: !0,
            export: !0,
            extends: !0,
            false: !0,
            final: !0,
            finally: !0,
            float: !0,
            for: !0,
            function: !0,
            goto: !0,
            if: !0,
            implements: !0,
            import: !0,
            in: !0,
            instanceof: !0,
            int: !0,
            interface: !0,
            let: !0,
            long: !0,
            native: !0,
            new: !0,
            null: !0,
            package: !0,
            private: !0,
            protected: !0,
            public: !0,
            return: !0,
            short: !0,
            static: !0,
            super: !0,
            switch: !0,
            synchronized: !0,
            this: !0,
            throw: !0,
            transient: !0,
            true: !0,
            try: !0,
            typeof: !0,
            var: !0,
            void: !0,
            volatile: !0,
            while: !0,
            with: !0,
            yield: !0
        };
        e.exports = function (e) {
            return r.hasOwnProperty(e)
        }
    }, function (e, t, n) {
        "use strict";
        var r = n(3), o = n(1), i = function (e, t, n) {
            if (o) {
                var i = n ? r.dirname(n) : "";
                return r.resolve(t, i, e)
            }
            return e
        };
        e.exports = i
    }, function (e, t, n) {
        "use strict";
        var r = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (e) {
            return typeof e
        } : function (e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        }, o = n(10), i = n(0), u = n(2), s = n(9), a = function (e, t) {
            return "object" === (void 0 === t ? "undefined" : r(t)) ? o({filename: e}, t) : i({filename: e, source: t})
        };
        a.render = o, a.compile = i, a.defaults = u, a.bindExtname = s, a.imports = u.imports, e.exports = a
    }, function (e, t) {
        function n(e) {
            throw new Error("Cannot find module '" + e + "'.")
        }

        n.keys = function () {
            return []
        }, n.resolve = n, e.exports = n, n.id = 8
    }, function (e, t, n) {
        "use strict";
        var r = n(1), o = function (e) {
            (arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : !function () {
                var e = new Error('Cannot find module "."');
                throw e.code = "MODULE_NOT_FOUND", e
            }()).extensions[e] = function (e, t) {
                var n = t || e.filename, r = "var template=require(" + JSON.stringify(7) + ")";
                e._compile(r + ";module.exports = template.compile({filename:" + JSON.stringify(n) + "});", n)
            }
        };
        r && o(".art", !function () {
            var e = new Error('Cannot find module "."');
            throw e.code = "MODULE_NOT_FOUND", e
        }()), e.exports = o
    }, function (e, t, n) {
        "use strict";
        var r = n(0), o = function (e, t, n) {
            return r(e, n)(t)
        };
        e.exports = o
    }, function (e, t, n) {
        "use strict";
        var r, o = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (e) {
            return typeof e
        } : function (e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        };
        r = function () {
            return this
        }();
        try {
            r = r || Function("return this")() || (0, eval)("this")
        } catch (e) {
            "object" === ("undefined" == typeof window ? "undefined" : o(window)) && (r = window)
        }
        e.exports = r
    }, function (e, t, n) {
        "use strict";
        var r = {
            __data: Object.create(null), set: function (e, t) {
                this.__data[e] = t
            }, get: function (e) {
                return this.__data[e]
            }, reset: function () {
                this.__data = {}
            }
        };
        e.exports = r
    }, function (e, t, n) {
        "use strict";
        var r = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (e) {
            return typeof e
        } : function (e) {
            return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
        }, o = function (e) {
            if ("object" === ("undefined" == typeof console ? "undefined" : r(console))) {
                var t = e.stack;
                delete e.stack, e = JSON.stringify(e, null, 4), console.error("Template Error: " + e + "\n\n" + t)
            }
            return function () {
                return "{Template Error}"
            }
        };
        e.exports = o
    }, function (e, t, n) {
        "use strict";
        var r = function (e, t) {
            if (Array.isArray(e))for (var n = 0, r = e.length; n < r; n++)t(e[n], n, e); else for (var o in e)t(e[o], o)
        };
        e.exports = r
    }, function (e, t, n) {
        "use strict";
        var r = function (e) {
            var t = {"<": "&#60;", ">": "&#62;", '"': "&#34;", "'": "&#39;", "&": "&#38;"};
            return function e(t) {
                return "string" != typeof t && (t = "function" == typeof t ? e(t.call(t)) : null === t ? "" : JSON.stringify(t) || ""), t
            }(e).replace(/&(?![\w#]+;)|[<>"']/g, function (e) {
                return t[e]
            })
        };
        e.exports = r
    }, function (e, t, n) {
        "use strict";
        var r = function (e, t, r, o) {
            var i = n(0);
            return e = n(6)(e, o, r), i({filename: e})(t)
        };
        e.exports = r
    }, function (e, t, n) {
        "use strict";
        var r = n(1), o = function (e) {
            if (r) {
                return n(3).readFileSync(e, "utf8")
            }
            var t = document.getElementById(e);
            return t.value || t.innerHTML
        };
        e.exports = o
    }, function (e, t, n) {
        "use strict";
        var r = {
            test: /{{([@#]?)(\/?)([\w\W]*?)}}/, use: function (e, t, n, r) {
                var o = this, i = o.options, u = o.getEsTokens(r), s = {}, a = u.map(function (e) {
                    return e.value
                }).filter(function (e) {
                    return !/^\s+$/.test(e)
                }), c = !!t && "raw", l = n + a.shift(), f = function (e, t) {
                    console.warn("Template upgrade example:", "{{" + e + "}}", ">>>", "{{" + t + "}}", "\n", i.filename || "")
                };
                switch ("#" === t && f("#value", "@value"), l) {
                    case"set":
                        r = "var " + a.join("") + ";";
                        break;
                    case"if":
                        r = "if(" + a.join("") + "){";
                        break;
                    case"else":
                        "if" === a[0] ? (a.shift(), r = "}else if(" + a.join("") + "){") : r = "}else{";
                        break;
                    case"/if":
                        r = "}";
                        break;
                    case"each":
                        "as" === a[1] && (f("each object as value index", "each object value index"), a.splice(1, 1));
                        var p = a[0] || "$data", y = a[1] || "$value", m = a[2] || "$index";
                        r = "$each(" + p + ",function(" + y + "," + m + "){", s.variables = ["$each", p];
                        break;
                    case"/each":
                        r = "});";
                        break;
                    case"echo":
                        l = "print", u[0].value = l, f("echo value", "print value");
                    case"print":
                    case"include":
                        r = l + "(" + a.join(",") + ");";
                        break;
                    default:
                        if (-1 !== a.indexOf("|")) {
                            for (var v = l, d = []; "|" !== a[0];)v += a.shift();
                            a.filter(function (e) {
                                return ":" !== e
                            }).forEach(function (e) {
                                "|" === e ? d.push([]) : d[d.length - 1].push(e)
                            }), d.reduce(function (e, t) {
                                var n = t.shift();
                                return t.unshift(e), r = n + "(" + t.join(",") + ")"
                            }, v)
                        } else i.imports[l] ? (f("filterName value", "value | filterName"), r = l + "(" + a.join(",") + ")", c = "raw") : r = "" + l + a.join("");
                        c || (c = "escape")
                }
                return s.code = r, s.output = c, s.variables || (s.variables = o.getVariables(u)), s
            }
        };
        e.exports = r
    }, function (e, t, n) {
        "use strict";
        var r = {
            test: /<%(#?)((?:==|=#|[=-])?)([\w\W]*?)(-?)%>/, use: function (e, t, n, r) {
                var o = {"-": "raw", "=": "escape", "": !1, "==": "raw", "=#": "raw"};
                return t && (r = "//" + r), {code: r, output: o[n]}
            }
        };
        e.exports = r
    }, function (e, t, n) {
        "use strict";
        function r(e) {
            if (Array.isArray(e)) {
                for (var t = 0, n = Array(e.length); t < e.length; t++)n[t] = e[t];
                return n
            }
            return Array.from(e)
        }

        function o(e, t) {
            if (!(e instanceof t))throw new TypeError("Cannot call a class as a function")
        }

        var i = function () {
            function e(e, t) {
                for (var n = 0; n < t.length; n++) {
                    var r = t[n];
                    r.enumerable = r.enumerable || !1, r.configurable = !0, "value" in r && (r.writable = !0), Object.defineProperty(e, r.key, r)
                }
            }

            return function (t, n, r) {
                return n && e(t.prototype, n), r && e(t, r), t
            }
        }(), u = n(21), s = n(22), a = n(5), c = "$data", l = function (e, t) {
            return e.hasOwnProperty(t)
        }, f = JSON.stringify, p = function () {
            function e(t) {
                var n = this;
                o(this, e);
                var r = t.filename, i = t.root, u = t.source;
                this.options = t, this.scripts = [], this.context = {}, this.internal = {
                    $out: '""',
                    $line: '[0,0,0,""]',
                    print: "function(){var text=''.concat.apply('',arguments);return $out+=text}",
                    include: "function(src,data){return $out+=$imports.$include(src,data||" + c + "," + f(r) + "," + f(i) + ")}"
                }, this.importContext("$out"), t.compileDebug && this.importContext("$line"), this.getTplTokens(u, t.rules, this).forEach(function (e) {
                    e.type === s.TYPE_STRING ? n.parseString(e) : n.parseExpression(e)
                })
            }

            return i(e, [{
                key: "getTplTokens", value: function () {
                    return s.apply(void 0, arguments)
                }
            }, {
                key: "getEsTokens", value: function (e) {
                    return u(e)
                }
            }, {
                key: "getVariables", value: function (e) {
                    var t = !1;
                    return e.filter(function (e) {
                        return "whitespace" !== e.type && "comment" !== e.type
                    }).filter(function (e) {
                        return "name" === e.type && !t || (t = "punctuator" === e.type && "." === e.value, !1)
                    }).map(function (e) {
                        return e.value
                    })
                }
            }, {
                key: "importContext", value: function (e) {
                    var t = "", n = this.internal, r = this.context, o = this.options, i = o.imports;
                    l(r, e) || e === c || "$imports" === e || a(e) || (t = l(n, e) ? n[e] : l(i, e) ? "$imports." + e : c + "." + e, r[e] = t)
                }
            }, {
                key: "parseString", value: function (e) {
                    var t = e.value, n = this.options, r = n.compress;
                    r && (t = r(t));
                    var o = "$out+=" + f(t);
                    this.scripts.push({source: t, tplToken: e, code: o})
                }
            }, {
                key: "parseExpression", value: function (e) {
                    var t = this, n = e.value, o = e.line, i = e.start, u = this.options, a = u.compileDebug, c = e.script, l = c.output, p = c.variables || [], y = c.code.trim();
                    if (!c.variables) {
                        var m = this.getEsTokens(y);
                        p.push.apply(p, r(this.getVariables(m)))
                    }
                    if (l && (!1 === escape || l === s.TYPE_RAW ? y = "$out+=" + c.code : (y = "$out+=$escape(" + c.code + ")", p.push("$escape"))), a) {
                        var v = [o, i, f(n)].join(",");
                        this.scripts.push({source: n, tplToken: e, code: "$line=[" + v + "]"})
                    }
                    p.forEach(function (e) {
                        return t.importContext(e)
                    }), this.scripts.push({source: n, tplToken: e, code: y})
                }
            }, {
                key: "checkExpression", value: function (e) {
                    for (var t = [[/^\s*}.*?{?\s*$/, ""], [/(^.*?\(\s*function\s*\(.*?\)\s*{\s*$)/, "$1})"], [/(^.*?\(\s*.*=>\s*{\s*$)/, "$1})"], [/(^.*?\(.*?\)\s*{\s*$)/, "$1}"]], n = 0; n < t.length;) {
                        if (t[n][0].test(e)) {
                            var o;
                            e = (o = e).replace.apply(o, r(t[n]));
                            break
                        }
                        n++
                    }
                    try {
                        return new Function(e), !0
                    } catch (e) {
                        return !1
                    }
                }
            }, {
                key: "build", value: function () {
                    var e = this.options, t = this.context, n = this.scripts, r = e.source, o = e.filename, i = e.imports, u = "var " + Object.keys(t).map(function (e) {
                            return e + "=" + t[e]
                        }).join(","), s = n.map(function (e) {
                        return e.code
                    }).join(";\n"), a = ['"use strict"', u, s, "return $out"].join(";\n");
                    if (e.compileDebug) {
                        var l = "{" + ["path:" + f(o), 'name:"RuntimeError"', "message:e.message", "line:$line[0]+1", "start:$line[1]+1", "source:$line[2]", "stack:e.stack"].join(",") + "}";
                        a = "try{" + a + "}catch(e){throw " + l + "}"
                    }
                    a = "function(" + c + "){\n" + a + "\n}";
                    try {
                        return new Function("$imports", "return " + a)(i)
                    } catch (e) {
                        for (var p = 0, y = 0, m = 0, v = r; p < n.length;) {
                            var d = n[p];
                            if (!this.checkExpression(d.code)) {
                                v = d.source, y = d.tplToken.line, m = d.tplToken.start;
                                break
                            }
                            p++
                        }
                        throw{
                            path: o,
                            name: "CompileError",
                            message: e.message,
                            line: y,
                            start: m,
                            source: v,
                            script: a,
                            stack: e.stack
                        }
                    }
                }
            }]), e
        }();
        e.exports = p
    }, function (e, t, n) {
        "use strict";
        var r = n(4).default, o = n(4).matchToToken, i = n(5), u = function (e) {
            return e.match(r).map(function (e) {
                return r.lastIndex = 0, o(r.exec(e))
            }).map(function (e) {
                return "name" === e.type && i(e.value) && (e.type = "keyword"), e
            })
        };
        e.exports = u
    }, function (e, t, n) {
        "use strict";
        var r = function (e, t, n) {
            for (var r = [{
                type: "string",
                value: e,
                line: 0,
                start: 0,
                end: e.length
            }], o = 0; o < t.length; o++)!function (e) {
                for (var t = e.test.ignoreCase ? "ig" : "g", o = e.test.source + "|^$|[\\w\\W]", i = new RegExp(o, t), u = 0; u < r.length; u++)if ("string" === r[u].type) {
                    for (var s = r[u].line, a = r[u].start, c = r[u].end, l = r[u].value.match(i), f = [], p = 0; p < l.length; p++) {
                        var y = l[p];
                        e.test.lastIndex = 0;
                        var m = e.test.exec(y), v = m ? "expression" : "string", d = f[f.length - 1], h = d || r[u], b = h.value;
                        a = h.line === s ? d ? d.end : a : b.length - b.lastIndexOf("\n") - 1, c = a + y.length;
                        var g = {type: v, value: y, line: s, start: a, end: c};
                        if ("string" === v)d && "string" === d.type ? (d.value += y, d.end += y.length) : f.push(g); else {
                            var x = e.use.apply(n, m);
                            g.script = x, f.push(g)
                        }
                        s += y.split(/\n/).length - 1
                    }
                    r.splice.apply(r, [u, 1].concat(f)), u += f.length - 1
                }
            }(t[o]);
            return r
        };
        r.TYPE_STRING = "string", r.TYPE_EXPRESSION = "expression", r.TYPE_RAW = "raw", r.TYPE_ESCAPE = "escape", e.exports = r
    }])
});