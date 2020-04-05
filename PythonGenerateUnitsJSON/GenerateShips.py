import json

d = dict()

race_names = {1: 'Race1', 2: 'Race2', 3: 'Race3', 4: 'Race4', 5: 'Race5',
              6: 'Race1', 7: 'Race2', 8: 'Race3', 9: 'Race4', 10: 'Race5',
              11: 'Race1', 12: 'Race2', 13: 'Race3', 14: 'Race4', 15: 'Race5',
              16: 'Race1', 17: 'Race2', 18: 'Race3', 19: 'Race4', 20: 'Race5'}
ship_names = {1: 'Carrier', 2: 'Cruiser', 3: 'Destroyer', 4: 'Dreadnought',
              5: 'Fighter', 6: 'Flagship', 7: 'WarSun'}

race_name = race_names[2]
ship_name = ship_names[6]

d['moveValue'] = 0
d['capacityValue'] = 0
d['combatValue'] = 9
d['cost'] = 8

d['canSustainDamaged'] = False
d['damaged'] = False

d['spaceCannonDiceValue'] = 10
d['spaceCannonNumDices'] = 0
d['bombardmentDiceValue'] = 10
d['bombardmentNumDices'] = 0
d['antiFighterBarrageDiceValue'] = 10
d['antiFighterBarrageNumDices'] = 0

with open('baseUnits/' + ship_name + race_name + '.json', "w") as file:
    json.dump(d, file, indent=4)
