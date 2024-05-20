<img src="https://github.com/zaqks/el_meyloud_RE/blob/f3a5c8c45025986354d71c5431c476e529365bd6/api/src/main/resources/static/media/images/logo/text.svg">
<div align=center>
<img width="48" height="48" src="https://img.icons8.com/color/48/java-coffee-cup-logo--v1.png" alt="java-coffee-cup-logo--v1"/>
<img width="48" height="48" src="https://img.icons8.com/office/48/spring-logo.png" alt="spring-logo"/>
<img width="48" height="48" src="https://img.icons8.com/color/48/thymeleaf.png" alt="thymeleaf"/>
<img width="48" height="48"
        src="https://img.icons8.com/external-soft-fill-juicy-fish/48/external-sql-coding-and-development-soft-fill-soft-fill-juicy-fish.png" />
<img width="48" height="48" src="https://img.icons8.com/color/48/html-5--v1.png" />
<img width="48" height="48" src="https://img.icons8.com/color/48/css3.png" />
<img width="48" height="48" src="https://img.icons8.com/color/48/javascript--v1.png" />
<img width="48" height="48" src="https://img.icons8.com/color/48/python--v1.png" />
<img width="48" height="48" src="https://img.icons8.com/color/48/git.png" />

<img width="120" height="60" src="https://1000logos.net/wp-content/uploads/2020/08/Apache-Logo-500x313.png" />
</div>

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

# how are we able to calculate the distance between 2 points on the earth
basically we're calculatiing an arch length of a circle
so obviously, we'll use the haversine formula

```
public float getDistance() {
        return (float) (Math.acos(
                Math.sin(p1.x) * Math.sin(p2.x) +
                        Math.cos(p1.x) * Math.cos(p2.x) *
                                Math.cos(p2.y - p1.y))
        * 6371);
    }
```


