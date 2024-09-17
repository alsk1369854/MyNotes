import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnDestroy,
  OnInit,
  Output,
  SimpleChanges,
  TemplateRef,
} from '@angular/core';
import { Subject, debounce, interval } from 'rxjs';

export interface TreeMenuNode<T = any> {
  key: any;
  label: string;
  children?: TreeMenuNode[];
  parent?: TreeMenuNode;
  expanded?: boolean;
  data?: T;
}

export type TreeMenuFilterCallback<N extends TreeMenuNode = TreeMenuNode> = (
  node: N
) => boolean;
export type TreeMenuExpandCallback<N extends TreeMenuNode = TreeMenuNode> = (
  node: N
) => boolean;

export interface TreeMenuOnDropEvent<T extends TreeMenuNode = TreeMenuNode> {
  start: T;
  enter: T;
}

@Component({
  selector: 'app-tree-menu',
  templateUrl: './tree-menu.component.html',
  styleUrl: './tree-menu.component.scss',
})
export class TreeMenuComponent implements OnChanges, OnInit, OnDestroy {
  @Input({ required: true }) nodes: TreeMenuNode[] = [];

  @Input() filter: Subject<TreeMenuFilterCallback> = new Subject();
  @Input() expandControl: Subject<TreeMenuExpandCallback> = new Subject();
  @Input() nodeTemplateRef?: TemplateRef<any>;

  @Input() focusedNode?: TreeMenuNode;
  @Output() focusedNodeChange: EventEmitter<TreeMenuNode | undefined> =
    new EventEmitter();
  private set _focusedNode(newValue: TreeMenuNode | undefined) {
    this.focusedNode = newValue;
    this.focusedNodeChange.emit(this.focusedNode);
  }

  public visibleNodes: Set<TreeMenuNode> = new Set();

  // draggable
  @Input() draggable: boolean = false;
  @Output() onDrop: EventEmitter<TreeMenuOnDropEvent> = new EventEmitter();
  public dragStartNode?: TreeMenuNode;
  public dragEnterNode?: TreeMenuNode;

  private onWindowClick = (event: MouseEvent) => {
    // this._focusedNodeKey = undefined;
  };

  constructor() {}

  ngOnInit(): void {
    window.addEventListener('click', this.onWindowClick);
    this.bindFilter(this.filter);
    this.bindExpandControl(this.expandControl);
    // 顯示所有節點
    this.filter.next(() => true);
  }

  ngOnChanges(changes: SimpleChanges): void {
    const nodesChange = changes['nodes'] !== undefined;
    const filterChange = changes['filter'] !== undefined;
    const expandControlChange = changes['expandControl'] !== undefined;
    if (nodesChange) {
      this.injectParentNode();
    }
    if (filterChange) {
      this.bindFilter(this.filter);
    }
    if (nodesChange || filterChange) {
      this.filter.next(() => true);
    }
    if (expandControlChange) {
      this.bindExpandControl(this.expandControl);
    }
  }

  ngOnDestroy(): void {
    window.removeEventListener('click', this.onWindowClick);
    this.visibleNodes.clear();
  }

  /**
   * 節點拖曳結束事件
   */
  public onNodeDragEnd(): void {
    this.dragStartNode = undefined;
    this.dragEnterNode = undefined;
  }

  /**
   * 節點拖曳開始事件
   * @param node 觸發事件節點
   */
  public onNodeDragStart(node: TreeMenuNode) {
    this._focusedNode = undefined;
    this.dragStartNode = node;
  }

  /**
   * 節點放下事件
   */
  public onNodeDrop(): void {
    this.onDrop.emit({
      start: this.dragStartNode!,
      enter: this.dragEnterNode!,
    });
  }

  /**
   * 節點展開icon點擊事件處理函式
   * @param event 點擊事件
   * @param node 觸發事件節點
   */
  public onNodeExpandClick(event: MouseEvent, node: TreeMenuNode): void {
    event.stopPropagation();
    this._focusedNode = undefined;

    if (!this.hasChildren(node)) return;
    node.expanded = !node.expanded;
  }

  /**
   * 節點點擊事件處理函式
   * @param event 點擊事件
   * @param node 觸發事件節點
   */
  public onNodeClick(event: MouseEvent, node: TreeMenuNode): void {
    event.stopPropagation();
    this._focusedNode = node;
  }

  /**
   * 轉型為 TreeMenuNode
   * @param node 節點數據
   * @returns TreeMenuNode
   */
  public castToTreeNode(node: any): TreeMenuNode {
    return node;
  }

  /**
   * 判斷當前節點是否具有子節點
   * @param node 當前節點
   * @returns 是否具有子節點
   */
  public hasChildren(node: TreeMenuNode): boolean {
    return node.children && node.children.length > 0 ? true : false;
  }

  /**
   * 注入每個節點的父節點
   */
  private injectParentNode(): void {
    const bfs = (
      node: TreeMenuNode,
      parent: TreeMenuNode | undefined
    ): void => {
      node.parent = parent;
      if (this.hasChildren(node)) {
        node.children?.forEach((subNode) => bfs(subNode, node));
      }
    };
    this.nodes.forEach((node) => bfs(node, undefined));
  }

  /**
   * 綁定過濾器事件
   * @param subject 訂閱事件
   */
  private bindFilter(subject: Subject<TreeMenuFilterCallback>): void {
    subject.pipe(debounce(() => interval(200))).subscribe((visibleCallback) => {
      // 清空可視節點
      this.visibleNodes.clear();
      const bfs = (node: TreeMenuNode): boolean => {
        // 此節點可視
        let visible = visibleCallback(node);
        if (this.hasChildren(node)) {
          node.children!.forEach((subNode) => {
            // 子節點可視
            const subNodeVisible = bfs(subNode);
            if (subNodeVisible) visible = true;
          });
        }
        if (visible) this.visibleNodes.add(node);
        return visible;
      };
      this.nodes.forEach(bfs);
    });
  }

  /**
   * 綁定展開控制器
   * @param subject 訂閱事件
   */
  private bindExpandControl(subject: Subject<TreeMenuExpandCallback>): void {
    subject.pipe(debounce(() => interval(200))).subscribe((expandCallback) => {
      const bfs = (node: TreeMenuNode): boolean => {
        let isExpand = expandCallback(node);
        if (this.hasChildren(node)) {
          node.children!.forEach((subNode) => {
            const isSubNodeExpand = bfs(subNode);
            if (isSubNodeExpand) isExpand = true;
          });
        }
        node.expanded = isExpand;
        return isExpand;
      };
      this.nodes.forEach(bfs);
    });
  }
}
