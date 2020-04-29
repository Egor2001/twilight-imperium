import json

import pandas as pd

race_names = {1: 'Arborec', 2: 'Naalu_Collective',
              3: 'Barony_of_Letnev', 4: 'Nekro_Virus',
              5: 'Clan_of_Saar', 6: "Sardakk_Norr",
              7: 'Embers_of_Muaat', 8: 'Universities_of_Jol_Nar',
              9: 'Emirates_of_Hacan', 10: 'Winnu',
              11: 'Federation_of_Sol', 12: 'Xxcha_Kingdom',
              13: 'Ghosts_of_Creuss', 14: 'Yin_Brotherhood',
              15: 'L1Z1X_Mindnet', 16: 'Yssaril_Tribes',
              17: 'Mentak_Coalition'}
unit_names = {1: 'Carrier', 2: 'Cruiser', 3: 'Destroyer', 4: 'Dreadnought', 5: 'Fighter',
              6: 'Flagship', 7: 'WarSun', 8: 'Infantry', 9: 'PDS', 10: 'SpaceDock'}

# Load spreadsheet
xl = pd.ExcelFile('info.xlsx')

# Load a sheet into a DataFrame by name: df1
df1 = xl.parse('Factions')
print(df1.columns)


def main(name_column, num_raws, mod):
    for i in range(num_raws):
        d = dict()
        d['cost'] = 8
        d['costNumUnits'] = 1
        d['combatValue'] = 7
        d['combatNumDices'] = 1
        d['moveValue'] = 1
        d['capacityValue'] = 3

        d['canSustainDamaged'] = False
        d['damaged'] = False

        d['spaceCannonDiceValue'] = 11
        d['spaceCannonNumDices'] = 0
        d['bombardmentDiceValue'] = 11
        d['bombardmentNumDices'] = 0
        d['antiFighterBarrageDiceValue'] = 11
        d['antiFighterBarrageNumDices'] = 0

        lines = str(df1[name_column][10 + 11 * i]).replace(":", "").splitlines()

        for line in lines:
            line = line.split(" ")

            if line[0] == "Cost":
                d['cost'] = int(line[1])

            elif line[0] == "Combat":
                d['combatValue'] = int(line[1])
                if len(line) > 2:
                    if line[2][1] == "x":
                        if line[2][2] != "?":
                            d['combatNumDices'] = int(line[2][2])
                        else:
                            d['combatNumDices'] = 2
                    else:
                        print('biba')

            elif line[0] == "Move":
                d['moveValue'] = int(line[1])
            elif line[0] == "Capacity":
                d['capacityValue'] = int(line[1])
            elif line[0] == "Sustain":
                d['canSustainDamaged'] = True

            elif line[0] == "Bombard":
                d['bombardmentDiceValue'] = int(line[1])
                if len(line) > 2:
                    if line[2][1] == "x":
                        d['bombardmentNumDices'] = int(line[2][2])
                    else:
                        print('biba')

            elif line[0] == "Space":
                d['spaceCannonDiceValue'] = int(line[2])
                if len(line) > 2:
                    if line[3][1] == "x":
                        d['spaceCannonNumDices'] = int(line[3][2])
                    else:
                        print('biba')

            elif line[0] == "Anti-Fighter":
                d['antiFighterBarrageDiceValue'] = int(line[2])
                if len(line) > 2:
                    if line[3][1] == "x":
                        d['antiFighterBarrageNumDices'] = int(line[3][2])
                    else:
                        print('biba')

        with open('baseUnits/' + 'Flagship' + race_names[mod + 2 * i] + '.json', "w") as file:
            json.dump(d, file, indent=4)


main('Unnamed: 1', 9, 1)
main('Unnamed: 4', 8, 2)
