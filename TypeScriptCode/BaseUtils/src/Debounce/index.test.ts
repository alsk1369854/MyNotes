import { debounce } from ".";

describe("Debounce", () => {
  test("debounce", () => {
    const func1 = () => {
      console.log("func1");
    };
    const newFunc = debounce(func1, 1000);
    expect(newFunc()).toBe(undefined);
    expect(newFunc()).toBe(undefined);
    expect(newFunc()).toBe(undefined);
  });
});
