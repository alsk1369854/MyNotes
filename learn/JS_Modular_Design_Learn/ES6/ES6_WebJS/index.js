(function(){function r(e,n,t){function o(i,f){if(!n[i]){if(!e[i]){var c="function"==typeof require&&require;if(!f&&c)return c(i,!0);if(u)return u(i,!0);var a=new Error("Cannot find module '"+i+"'");throw a.code="MODULE_NOT_FOUND",a}var p=n[i]={exports:{}};e[i][0].call(p.exports,function(r){var n=e[i][1][r];return o(n||r)},p,p.exports,r,e,n,t)}return n[i].exports}for(var u="function"==typeof require&&require,i=0;i<t.length;i++)o(t[i]);return o}return r})()({1:[function(require,module,exports){
'use strict';

var _modules = require('./modules/modules1');

var _modules2 = _interopRequireDefault(_modules);

var _modules3 = require('./modules/modules2');

var _modules4 = _interopRequireDefault(_modules3);

var _modules5 = require('./modules/modules3');

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

console.log(_modules2.default);
console.log(_modules4.default);
console.log(_modules5.func, _modules5.value, _modules5.obj);
},{"./modules/modules1":2,"./modules/modules2":3,"./modules/modules3":4}],2:[function(require,module,exports){
'use strict';

Object.defineProperty(exports, "__esModule", {
    value: true
});
exports.default = {
    name: 'Main',
    age: 18
};
},{}],3:[function(require,module,exports){
'use strict';

Object.defineProperty(exports, "__esModule", {
    value: true
});

exports.default = function () {
    return 'ES6: export default func';
};
},{}],4:[function(require,module,exports){
'use strict';

Object.defineProperty(exports, "__esModule", {
    value: true
});
var func = function func() {
    return 'func';
};

var value = 123;

var obj = exports.obj = {
    name: 'han',
    age: 66
};

exports.func = func;
exports.value = value;
},{}]},{},[1]);
