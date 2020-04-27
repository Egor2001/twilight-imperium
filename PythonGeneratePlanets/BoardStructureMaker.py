import json


def put_tile(index, bonds):
    vertex = dict()

    vertex['index'] = index
    vertex['neighbours_num'] = len(bonds)
    for num in range(len(bonds)):
        vertex['neighbour' + str(num)] = bonds[num]

    with open('../TestBoards/Structure/Circle/' + str(index) + '.json', "w") as file:
        json.dump(vertex, file, indent=4)


def func(index):
    return [(index - 1) % func.mod, (index + 1) % func.mod]


def main():
    sz = 10
    func.mod = sz
    for i in range(sz):
        put_tile(i, func(i))

main()
