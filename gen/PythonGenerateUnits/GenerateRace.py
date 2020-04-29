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


def make_starting_fleet(lines):
    starting_fleet = dict()

    for name in unit_names.values():
        starting_fleet[name] = 0

    for string in lines:
        num_units = int(string[0])
        name = string[2:].replace(' ', '')
        if name[-1] == 's':
            name = name[:-1]

        if name not in unit_names.values():
            print('biba boba')

        starting_fleet[name] = num_units

    return starting_fleet


def make_tile_info(lines):
    planet_list = list()

    for line in lines:
        name = line.split(":")[0]
        name = name.replace(" ", "_")
        name = name.replace("'", "")

        planet_list.append(name)

    return planet_list


def make_starting_technology(lines):
    technology_list = dict()

    for line in lines:
        names = line.split(" ")
        param = names[-1].split("(")[-1].split(")")[0]
        names.pop(-1)
        name = "".join(names)

        technology_list[name] = param

    return technology_list


def write_file(num_lines, name_column, num_column):
    for i in range(0, num_lines + 1):
        line_with_starting_fleet = str(df1[name_column][6 + 11 * i]).splitlines()
        line_with_tile = str(df1[name_column][2 + 11 * i]).splitlines()
        line_with_starting_technology = str(df1[name_column][7 + 11 * i]).splitlines()

        big_dict = dict()
        big_dict['StartingFleet'] = make_starting_fleet(line_with_starting_fleet)
        big_dict['TileInfo'] = make_tile_info(line_with_tile)
        big_dict['StartingTechnology'] = make_starting_technology(line_with_starting_technology)
        with open('baseRaces/' + race_names[num_column + 2 * i] + '.json', "w") as file:
            json.dump(big_dict, file, indent=4)


# Load spreadsheet
xl = pd.ExcelFile('info.xlsx')

# Load a sheet into a DataFrame by name: df1
df1 = xl.parse('Factions')
print(df1.columns)

write_file(8, 'Unnamed: 1', 1)
write_file(7, 'Unnamed: 4', 2)

