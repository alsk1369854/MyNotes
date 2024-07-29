import human_eval

def main() -> None:
    problems = human_eval.getHumanEvalProblems()
    first = list(problems.items())[0]
    print(first[1])

if __name__ == "__main__":
    main()
