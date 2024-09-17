export type PickNumber<T extends object> = {
  [key in keyof T as T[key] extends number ? key : never]: T[key];
};

export type PartialNull<T extends object> = {
  [key in keyof T]: T[key] | null;
};

export type ArrayItemType<T extends any[]> = T extends (infer U)[] ? U : never;
