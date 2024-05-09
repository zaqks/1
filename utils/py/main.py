from mdlCreator import getControllerLines, getTemplateLines
from os import mkdir

APPNAME = "com.zaqksdev.el_meyloud_RE"
ENTITIES = ["test1", "test2"]
TEMPLATES = ["show", "add", "showAll"]

#RSRCSPATH = "/home/zak/Desktop/myDesk/javaProjects/el_meyloud_RE/api/src/main/resources/"
RSRCSPATH = "out/"


#create templates
#static

for i in ENTITIES:
    #folder
    rcrcPath = f"{RSRCSPATH}templates/{i}"
    mkdir(rcrcPath)
    #
    for j in TEMPLATES:
        tmpPath = rcrcPath + f"/{j}.html"
        
        with open(tmpPath, "w") as f:
            f.writelines(getTemplateLines(f"{j}{i}"))
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

    