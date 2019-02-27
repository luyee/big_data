#!/usr/bin/python
# -*- coding: UTF-8 -*-

class Parent:
    empCount = 0

    def __init__(self, name, age):
        self.name = name
        self.age = age
        Parent.empCount += 1

    def displayCount(self):
        print("Total Employee %d" % Parent.empCount)

    def displayParent(self):
        print("Name : ", self.name, ", Age: ", self.age)


class Children(Parent):

    def __init__(self, score, name, age):
        super().__init__(name, age)
        self.score = score

    def __del__(self):
        class_name = self.__class__.__name__
        print(class_name+"销毁")

    def toString(self):
        print("Name : ", self.name, ", Age: ", self.age, "Score: ", self.score)


p1 = Parent("Zara", 20)


c1 = Children("cai", 20, 100)

p1.displayParent()
c1.toString()

