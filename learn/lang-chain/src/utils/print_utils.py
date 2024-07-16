from typing import Dict, Any, Self, Optional, List

class PrintUtils:
    _instance: Optional[Self] = None 
    def __new__(cls, *args, **kwargs): 
        if cls._instance is None: 
            cls._instance = super().__new__(cls) 
        return cls._instance 
    def __init__(self) -> None:
        pass
    
    def printDict(self, target: Dict[str, Any]) -> None:
        for key, val in target.items():
            print(f"{key} : {val}")

    def printList(self, target: List[Any]) -> None:
        for item in target:
            print(f"{item}")