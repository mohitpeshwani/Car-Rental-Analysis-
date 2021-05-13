from pyclustering.cluster.clarans import clarans
from pyclustering.utils import timedcall
import pandas as pd
import mysql.connector
from sklearn.preprocessing import MinMaxScaler
from matplotlib import pyplot as plt
%matplotlib inline
mydb = mysql.connector.connect(
host="localhost",
user="root",
password="",
database="sale_analysis"
)
mycursor = mydb.cursor()

mycursor.execute("SELECT year_of_manuf,no_of_seats FROM car_details")
x = mycursor.fetchall()
data = x
clarans_instance = clarans(data, 4, 6, 4)
(ticks, result) = timedcall(clarans_instance.process)
print("Execution time : ", ticks, "\n")

clusters = clarans_instance.get_clusters()
medoids = clarans_instance.get_medoids()
print("Index of the points that are in a cluster : ",clusters)
print("The index of medoids that algorithm found to be best : ",medoids)
