# Import pandas
import pandas as pd
import json

file = '../../info.xlsx'

xl = pd.ExcelFile(file)
print(xl.sheet_names)
df1 = xl.parse('Planets')


def make_file(tile_num, planets_name):
    planet_dict = dict()
    planets = planets_name.split('+')

    planets = list(map(lambda el: el.strip(), planets))

    planet_dict['tile_num'] = tile_num
    planet_dict['planets_num'] = len(planets)

    for j in range(len(planets)):
        planet_dict['planet' + str(j)] = planets[j]

    print(planet_dict)

    with open('../baseTiles/tile' + str(tile_num) + '.json', "w") as file:
         json.dump(planet_dict, file, indent=4)


for i in range(1, 52):
    (make_file(df1['Systems'][i], df1['Unnamed: 13'][i]))
