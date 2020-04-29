import pandas as pd
import json

bounds = []

for i in range(7):
    for j in range(7):
        if abs(i - j) <= 3:
            bounds.append((i, j))

print(len(bounds))

bounds.sort()

d = dict()

for i in range(len(bounds)):
    d.update({bounds[i]: i})

print(d)

bonds = []


def dist(a, b):
    if abs(a[0] - b[0]) <= 1 and abs(a[1] - b[1]) <= 1 and abs(a[0] - b[0]) + abs(a[1] - b[1]) >= 1:
        if abs(a[0] - b[0]) + abs(a[1] - b[1]) < 2:
            return 1
        if abs(a[0] - b[0] + a[1] - b[1]) == 2:
            return 1
    return 0


def get_neighbours(index):
    for tile in bounds:
        if dist(bounds[index], tile) == 1:
            bonds[index].append(d[tile])


for k in range(len(bounds)):
    bonds.append([])
    get_neighbours(k)

print(bonds)


def PutTile(index):
    vertex = dict()

    vertex['index'] = index
    vertex['neighbours_num'] = len(bonds[index])
    for num in range(len(bonds[index])):
        vertex['neighbour' + str(num)] = bonds[index][num]

    with open('../Board/' + str(index) + '.json', "w") as file:
         json.dump(vertex, file, indent=4)


for k in range(len(bounds)):
    PutTile(k)
