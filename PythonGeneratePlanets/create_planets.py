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


make_file('Arinam', 'Industrial', 1, 2, '-')
