class MyContext:
    def __init__(self):
        # example file or database connection
        print("__init__", self)
        self.big_resource: str = "Some big resource"

    def __enter__(self):
        print("__enter__", self)
        return self

    def __exit__(self, exc_type, exc_value, traceback):
        print("__exit__", self, exc_type, exc_value, traceback)
        if not exc_type:
            print("離開房間")
        else:
            print("錯誤追蹤結果:", traceback)

    def do_something(self, data):
        # simulate doing something with big_resource
        print("Doing something with data: ", self, data)


with MyContext() as f:
    data = "Hello"
    f.do_something(data)
