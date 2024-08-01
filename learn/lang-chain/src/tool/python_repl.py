from langchain_core.tools import Tool
from langchain_experimental.utilities import PythonREPL

def python_repl():
    python_repl = PythonREPL()
    # print(python_repl.run("print(1 + 1)"))

    # You can create the tool to pass to an agent
    repl_tool = Tool(
        name="python_repl",
        description="A Python shell. Use this to execute python commands. Input should be a valid python command. If you want to see the output of a value, you should print it out with `print(...)`.",
        func=python_repl.run,
    )
    print(repl_tool.invoke("print(1 + 1)"))
