const eventNames = ['BPMN:DOWNLOAD_DIAGRAM'] as const;
type EventName = (typeof eventNames)[number];

class EventEmitter {
  private listeners: Record<EventName, Set<Function>> = eventNames.reduce(
    (listeners, eventName) => {
      listeners[eventName] = new Set();
      return listeners;
    },
    {} as Record<EventName, Set<Function>>
  );

  public on(eventName: EventName, listener: Function): void {
    this.listeners[eventName].add(listener);
  }

  public off(eventName: EventName, listener: Function): void {
    this.listeners[eventName].delete(listener);
  }

  public emit(eventName: EventName, ...args: any[]): void {
    this.listeners[eventName].forEach((listener) => listener(...args));
  }
}

export const eventEmitter = new EventEmitter();
