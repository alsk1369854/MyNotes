import { deepCopy } from ".";

describe("DeepCopy", () => {
  test("copy", () => {
    let a = {
      obj1: {
        obj1v: 1,
      },
      b: 2,
    };
    let b = deepCopy(a);
    expect(a === b).toBe(false);

    b.obj1.obj1v = 5;
    expect(a.obj1.obj1v).toBe(1);
    expect(b.obj1.obj1v).toBe(5);
  });
});
