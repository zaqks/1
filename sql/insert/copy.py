from os import system

with open("imgs.sql", "r") as f:
    imgs =  f.readlines()



for i in imgs:
    i = i.replace("\n", "")
    i = i.replace("$", "")

    system(f"cp /home/zak/Downloads/house.jpg '/home/zak/Desktop/myDesk/javaProjects/el_meyloud_RE/api/public/{i}'")