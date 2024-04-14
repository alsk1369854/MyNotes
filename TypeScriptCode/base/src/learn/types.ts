export function runTypes() {
  // 枚舉方法一
  const myTypes = ["small", "middle", "large"] as const;
  type MyType = (typeof myTypes)[number];
  const myType: MyType = "large";
  myTypes.forEach((type) => console.log(`loop myTypes value:${type}`));
  function switchMyType(type: MyType) {
    switch (type) {
      case "small":
      case "middle":
      case "large":
        break;
      default:
        const _checkExpand: never = type;
        return _checkExpand;
    }
  }

  // 枚舉方法二
  enum MyEnum {
    small = "small-value",
    middle = "middle-value",
    large = "large-value",
  }
  const myMyEnum: MyEnum = MyEnum.large;
  Object.keys(MyEnum).forEach((key) => {
    const value = MyEnum[key as keyof typeof MyEnum];
    console.log(`loop MyEnum key:${key} value:${value}`);
  });
  Object.entries(MyEnum).forEach(([key, value]) => {
    console.log(`loop MyEnum key:${key} value:${value}`);
  });
  function switchMyEnum(type: MyEnum) {
    switch (type) {
      case MyEnum.small:
      case MyEnum.middle:
      case MyEnum.large:
        break;
      default:
        const _checkExpand: never = type;
        return _checkExpand;
    }
  }
}
