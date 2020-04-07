import json

d = dict()

race_names = {1: 'Race1', 2: 'Race2', 3: 'Race3', 4: 'Race4', 5: 'Race5',
              6: 'Race1', 7: 'Race2', 8: 'Race3', 9: 'Race4', 10: 'Race5',
              11: 'Race1', 12: 'Race2', 13: 'Race3', 14: 'Race4', 15: 'Race5',
              16: 'Race1', 17: 'Race2', 18: 'Race3', 19: 'Race4', 20: 'Race5'}

race_name = race_names[1]

d['blockaded'] = False
d['productValue'] = 4

with open('baseUnits/' + 'SpaceDock' + race_name + '.json', "w") as file:
    json.dump(d, file, indent=4)
