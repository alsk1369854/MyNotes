import { debounce } from "./Debounce";

const func1 = () => {
  console.log("func1");
};
const newFunc = debounce(func1, 1000);

newFunc();
newFunc();
newFunc();
