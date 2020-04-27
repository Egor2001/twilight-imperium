import json

field = dict()

for i in range(37):
    field.update({"tile" + str(i): i + 1})

with open('../BoardStructure/6players.json', "w") as file:
    json.dump(field, file, indent=4)
