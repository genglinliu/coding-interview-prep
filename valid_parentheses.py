# -*- coding: utf-8 -*-
"""valid_parentheses.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1C-_qpzfuBzt4PbDPDkCqLaqb-BIrlUxl
"""

str = "{[]}{({})()[()]}"

def isValid(str):
    stack = []
    parenMap = {')': '(', ']':'[', '}':'{'}
    rightParen = [')', ']', '}']

    for c in str:
        if c not in rightParen:
            stack.append(c)
        else: # see a right paren and pop stack
            if len(stack) == 0:
                return False
            p = stack.pop()
            if ((p != parenMap[c])):
                return False

    return len(stack) == 0

isValid(str)