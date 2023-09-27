class Utile {
  static deepCopy(value) {
    if (typeof value !== "object" || value === null) {
      return value;
    }
    const result = Array.isArray(value) ? [] : {};
    Object.setPrototypeOf(result, Object.getPrototypeOf(value));
    for (const key in value) {
      if (value.hasOwnProperty(key)) {
        result[key] = this.deepCopy(value[key]);
      }
    }
    return result;
  }
}

let a = {
  obj1: {
    obj1v: 1,
  },
  b: 2,
};
let b = Utile.deepCopy(a);
b.obj1.obj1v = 5;
console.log(a);
console.log(b);
