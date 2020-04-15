import pandas as pd

# Load spreadsheet
xl = pd.ExcelFile('info.xlsx')

# Load a sheet into a DataFrame by name: df1
df1 = xl.parse('Technology')
print(df1.columns)
