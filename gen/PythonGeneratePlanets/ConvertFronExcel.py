# Import pandas
import pandas as pd
import json


def make_file(planet_name, type, resource, influence, tech):
    planet_dict = dict()

    planet_dict['name'] = planet_name
    planet_dict['type'] = type
    planet_dict['resource'] = resource
    planet_dict['influence'] = influence
    planet_dict['tech'] = tech

    with open('basePlanets/' + planet_name + '.json', "w") as file:
        json.dump(planet_dict, file, indent=4)


file = 'info.xlsx'

xl = pd.ExcelFile(file)
print(xl.sheet_names)
df1 = xl.parse('Planets')

for i in range(1, 34):
    make_file(df1['Planets'][i], df1['Unnamed: 1'][i], df1['Unnamed: 2'][i], df1['Unnamed: 3'][i], df1['Unnamed: 4'][i])

for i in range(1, 34):
    make_file(df1['Unnamed: 6'][i], df1['Unnamed: 7'][i], df1['Unnamed: 8'][i], df1['Unnamed: 9'][i], df1['Unnamed: 10'][i])

