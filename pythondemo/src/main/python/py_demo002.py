#!/usr/bin/python
# -*- coding: UTF-8 -*-

import py_demo001

# py_demo001.test(12)

file = open("C:\\Users\\caiwe\\Desktop\\test.txt", "r+", encoding='UTF-8')

# print(file.read(500))

# files = file.read(500).split("\n")
# for i in files:
#     if i.__contains__("zhang"):
#         pass
#     print(i.split(","))

str1 = "123"
a = int(str1) + 3
print(a)

str2 = "hello world!"
if "h" in str2:
    print("true")
print(str2 * 3)
print("my name is %s , %d years old" % ("caiwei", 12))

hi = '''hi
dasad
sdasda
'''

# str3 = input("请输入：")
# print("你输入的内容是：",str3)

print([x * 5 for x in range(2, 10, 2)])
print(file.name)

# str4 = "\nwang,wu,40"

# file.writelines(str4)

n = file.tell()
print(n)



