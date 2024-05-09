from mdlCreator import getControllerLines, getTemplateLines, getStyleLines
from os import mkdir, system

APPNAME = "com.zaqksdev.el_meyloud_RE"
ENTITIES = ["visit"]
TEMPLATES = [ "show", "showAll", "update"]

RSRCSPATH = "/home/zak/Desktop/myDesk/javaProjects/el_meyloud_RE/api/src/main/resources/"
#RSRCSPATH = "out/"

#reset everything
#system("sudo rm out/static/* -r; sudo rm out/templates/* -r ")

 
for i in ENTITIES:
    #folder
    rcrcPath = f"{RSRCSPATH}templates/{i}"
    mkdir(rcrcPath)
    #
    for j in TEMPLATES:
        tmpPath = rcrcPath + f"/{j}.html"

        css = []
        for k in TEMPLATES:
            css.append(f"{k}/{k}")

        with open(tmpPath, "w") as f:
            f.writelines(getTemplateLines(f"{j}{i[0].capitalize() + i[1:]}", getStyleLines(f"/{i}", css)) )
            f.close()

    #css
    
    staticPath = f"{RSRCSPATH}static/{i}"
    mkdir(staticPath)
    
    for j in TEMPLATES:
        staticDir = f"{staticPath}/{j}"
        mkdir(staticDir)
        
        with open(f"{staticDir}/{j}.css", "w") as f:
                #f.writelines(getTemplateLines(j))
                f.write("")
                f.close()

    
