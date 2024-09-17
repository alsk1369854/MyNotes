class SwitchUtils {
  public assertToNever(type: never): never {
    throw new Error(`${type} 類型在 switch 中未捕獲`);
  }
}

export const switchUtils = new SwitchUtils();
