import mod1
from mod1.mod2 import say
from optparse import OptionParser
import inspect

say("main")


class SuperClass:
    super_static_string = "super_static_string"

    def __init__(self):
        self.super_string = "super_string"


def my_dec(func):
    def new_func(*args, **keyargs):
        print("my_dec")
        return func(*args, **keyargs)

    return new_func


def mid(func):
    def new_func(*args, **keyargs):
        self_ = args[0]
        methods = inspect.getmembers(self_, predicate=inspect.ismethod)
        for method_name, method in methods:
            if not method_name.startswith("_"):
                setattr(self_, method_name, my_dec(method))
        return func(*args, **keyargs)

    return new_func


class MyClass(SuperClass):
    static_value = "str"

    @mid
    def __init__(self):
        # print(method_name, _)
        # method = getattr(self, method_name)
        # method()
        # methoed = my_dec(methoed)

        self.string = "string"
        self.integer = 10
        self.float = 3.14
        self.boolean = True
        self.none = None

    def aaa(self):
        print("aaa")
        pass


my_class = MyClass()
# my_class.aaa()
# my_class.static_value = "tesdt"
# print(my_class.__dict__)

# print(type(my_class))
# print(type(MyClass))
# print(isinstance("aa", object))

# methods = inspect.getmembers(my_class, predicate=inspect.ismethod)
# print(methods)

my_class.aaa()
