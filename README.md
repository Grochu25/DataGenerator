# Data Generator
This is a desktop program used to generate content for databases. 
It can output generated data into a text file in SQL or CSV-like format, or insert the data directly into a MySQL database.

## App description
- Data is divided into four basic sections and one custom section, where the user can add and remove their own datasets from text files.
- The basic sections allow the creation of numbers, names, personal data, addresses, and dates.
- The user can choose from different separators for the data and decide whether to use quotation marks for strings and brackets for rows.
- Some of fields are generated using other data. For example email address is generated based on the name and surname specified by the user when adding the email field or by clicking it later. If user doesn't specify the required fields, the necessary data for email generation will be generated randomly.
- There is an option to save the generated data into a file (either in a formt ready to be inserted into a database or as a CSV) or inserte it directly into MySql database server. Output format and database connection details can be changed from the top bar
- Currently, dates are provided in Spinners

![Screenshot of app](https://github.com/Grochu25/DataGenerator/blob/master/AppScreenShot.png?raw=true)
