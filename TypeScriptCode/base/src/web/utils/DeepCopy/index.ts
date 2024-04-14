/**
 * 深度拷貝
 * @param value 要拷貝的目標值
 * @returns 新值
 */
export function deepCopy<T>(value: T): T {
  if (typeof value !== "object" || value === null) {
    return value;
  }
  const result = Array.isArray(value) ? [] : {};
  Object.setPrototypeOf(result, Object.getPrototypeOf(value));
  for (const key in value) {
    if (value.hasOwnProperty(key)) {
      //@ts-ignore
      result[key] = deepCopy(value[key]);
    }
  }
  return result as T;
}
