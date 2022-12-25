(function(){function r(e,n,t){function o(i,f){if(!n[i]){if(!e[i]){var c="function"==typeof require&&require;if(!f&&c)return c(i,!0);if(u)return u(i,!0);var a=new Error("Cannot find module '"+i+"'");throw a.code="MODULE_NOT_FOUND",a}var p=n[i]={exports:{}};e[i][0].call(p.exports,function(r){var n=e[i][1][r];return o(n||r)},p,p.exports,r,e,n,t)}return n[i].exports}for(var u="function"==typeof require&&require,i=0;i<t.length;i++)o(t[i]);return o}return r})()({1:[function(require,module,exports){
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;
var _default = function _default() {
  return 'I\'m Test Module';
};
exports.default = _default;

},{}],2:[function(require,module,exports){
"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.x = exports.a = void 0;
var _TestModule = _interopRequireDefault(require("./TestModule"));
function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
var x = 32;

//@ts-ignore
// ignoreFun();
exports.x = x;
console.log(document.getElementById('a'));
console.log((0, _TestModule.default)());
console.log(x);
console.log(1231);
var a = function () {
  return _TestModule.default;
}();
exports.a = a;

},{"./TestModule":1}]},{},[2]);
