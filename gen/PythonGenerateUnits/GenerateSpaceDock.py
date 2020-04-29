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

race_name = race_names[1]

d['blockaded'] = False
d['productValue'] = 0
d['canFightSpace'] = False

for race_name in race_names.values():
    with open('baseUnits/' + 'SpaceDock' + race_name + '.json', "w") as file:
        json.dump(d, file, indent=4)
