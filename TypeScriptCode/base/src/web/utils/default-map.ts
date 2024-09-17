export class DefaultMap<K, V> extends Map<K, V> {
  private defaultConstructor: () => V;

  constructor(defaultConstructor: () => V) {
    super();
    this.defaultConstructor = defaultConstructor;
  }

  override get(key: K): V {
    if (!super.has(key)) {
      super.set(key, this.defaultConstructor());
    }
    return super.get(key)!;
  }
}
