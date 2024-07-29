from dataclasses import dataclass
import json
from typing import Dict, List

@dataclass
class HumanEvalProblem:
    task_id: str # id
    prompt: str # 問題
    entry_point: str # 函數名稱
    canonical_solution: str # 範例解決方案
    test: str # 測試程式

    def __repr__(self) -> str:
        stringBuilder: List[str] = [] 
        for key, val in self.__dict__.items():
            stringBuilder.append(f'===== {key} =====\n{val}')
        return "\n".join(stringBuilder)

def getHumanEvalProblems() -> Dict[str, HumanEvalProblem]:
    problems: Dict[str, HumanEvalProblem] = dict()
    with open('./data/human-eval.json', 'r') as file:
        dataset: Dict[str, str] = json.loads(file.read())
        for key, val in dataset.items():
            problems[key] = HumanEvalProblem(**val)
    return problems
