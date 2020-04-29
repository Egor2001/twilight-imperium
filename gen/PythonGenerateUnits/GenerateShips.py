import json

d = dict()

race_names = {1: 'Arborec', 2: 'Naalu_Collective',
              3: 'Barony_of_Letnev', 4: 'Nekro_Virus',
              5: 'Clan_of_Saar', 6: "Sardakk_Norr",
              7: 'Embers_of_Muaat', 8: 'Universities_of_Jol_Nar',
              9: 'Emirates_of_Hacan', 10: 'Winnu',
              11: 'Federation_of_Sol', 12: 'Xxcha_Kingdom',
              13: 'Ghosts_of_Creuss', 14: 'Yin_Brotherhood',
              15: 'L1Z1X_Mindnet', 16: 'Yssaril_Tribes',
              17: 'Mentak_Coalition'}
ship_names = {1: 'Carrier', 2: 'Cruiser', 3: 'Destroyer', 4: 'Dreadnought',
              5: 'Fighter', 6: 'Flagship', 7: 'WarSun'}

race_name = race_names[17]
ship_name = ship_names[6]

d['cost'] =            8
d['costNumUnits'] =   1
d['combatValue'] =     7
d['combatNumDices'] = 2
d['moveValue'] =       1
d['capacityValue'] =   3

d['canSustainDamaged'] = True
d['damaged'] = False

d['spaceCannonDiceValue'] = 11
d['spaceCannonNumDices'] = 0
d['bombardmentDiceValue'] = 11
d['bombardmentNumDices'] = 0
d['antiFighterBarrageDiceValue'] = 11
d['antiFighterBarrageNumDices'] = 0

#for race_name in race_names.values():
with open('baseUnits/' + ship_name + "" + race_name + '.json', "w") as file:
    json.dump(d, file, indent=4)
