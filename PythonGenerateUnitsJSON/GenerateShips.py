import json

d = dict()

race_names = {1: 'Arborec', 2: 'Naalu_Collective',
              3: 'Barony_of_Letnev', 4: 'Nekro_Virus',
              5: 'Clan_of_Saar', 6: "Sardakk_N'orr",
              7: 'Embers_of_Muaat', 8: 'Universities_of_Jol-Nar',
              9: 'Emirates_of_Hacan', 10: 'Winnu',
              11: 'Federation_of_Sol', 12: 'Xxcha_Kingdom',
              13: 'Ghosts_of_Creuss', 14: 'Yin_Brotherhood',
              15: 'L1Z1X_Mindnet', 16: 'Yssaril_Tribes',
              17: 'Mentak_Coalition'}
ship_names = {1: 'Carrier', 2: 'Cruiser', 3: 'Destroyer', 4: 'Dreadnought',
              5: 'Fighter', 6: 'Flagship', 7: 'WarSun'}

race_name = race_names[2]
ship_name = ship_names[5]

d['moveValue'] = 2
d['capacityValue'] = 0
d['combatValue'] = 8
d['cost'] = 1

d['canSustainDamaged'] = False
d['damaged'] = False

d['spaceCannonDiceValue'] = 10
d['spaceCannonNumDices'] = 0
d['bombardmentDiceValue'] = 10
d['bombardmentNumDices'] = 0
d['antiFighterBarrageDiceValue'] = 10
d['antiFighterBarrageNumDices'] = 0

for race_name in race_names.values():
    with open('baseUnits/' + ship_name + "II" + race_name + '.json', "w") as file:
        json.dump(d, file, indent=4)
