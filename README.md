<img src="https://github.com/zaqks/el_meyloud_RE/blob/f3a5c8c45025986354d71c5431c476e529365bd6/api/src/main/resources/static/media/images/logo/text.svg">

# El Meyloud RealEstate

<img src="https://github.com/zaqks/1/blob/e709115f32887e1697013f53c91dc7b40338e6bd/docs/Screenshot_20240520_005654.png">

<img src="https://github.com/zaqks/1/blob/e709115f32887e1697013f53c91dc7b40338e6bd/docs/Screenshot_20240520_005820.png">


# client actions:
-filter the availble offers 
-book an appointment to visit l 7ala (then buy/rent, the contracts will be created by an agent)
-see his contracts and profile

# agent actions:
-activate properties for sale after checking them
-add properties
-making contracts after selling/renting properties
-see his vistis schedule

# agency actions:
-manage the visits schedule
-manage the agents
-manage the contracts
-manage the clients
-dashboard



# how does the auto scheduling system work
for each agent we define the hours he starts his service and the hour he ends 0-23
we also define the active day 0-6
the visit is 1hour long

we choose the less working agent
we choose the closest agent relative to the property

if someone misses the visits 2 times, he gets banned from the platform

