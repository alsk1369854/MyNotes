// const eventNames = ["BPMN:DOWNLOAD_DIAGRAM"] as const;
// type EventName = (typeof eventNames)[number];

type DefaultEventMap = {
  [_key in string]: any;
};

type Listener<
  EventMap extends DefaultEventMap,
  EventName extends keyof EventMap
> = (event: EventMap[EventName]) => void;

export class EventEmitter<EventMap extends DefaultEventMap = DefaultEventMap> {
  private listenerCollections: Map<keyof EventMap, Set<Function>> = new Map();

  public on<EventName extends keyof EventMap>(
    eventName: EventName,
    listener: Listener<EventMap, EventName>
  ): void {
    this.getListenerCollection(eventName).add(listener);
  }

  public off<EventName extends keyof EventMap>(
    eventName: EventName,
    listener: Listener<EventMap, EventName>
  ): void {
    this.getListenerCollection(eventName).delete(listener);
  }

  public emit<EventName extends keyof EventMap>(
    eventName: EventName,
    event: EventMap[EventName]
  ): void {
    this.getListenerCollection(eventName).forEach((listener) =>
      listener(event)
    );
  }

  private getListenerCollection(eventName: keyof EventMap): Set<Function> {
    return (
      this.listenerCollections.get(eventName) ??
      this.listenerCollections.set(eventName, new Set()).get(eventName)!
    );
  }
}
