from mdlCreator import getControllerLines, getTemplateLines, getStyleLines
from os import mkdir, system

APPNAME = "com.zaqksdev.el_meyloud_RE"
ENTITIES = ["test1", "test2"]
TEMPLATES = ["show", "add", "showAll"]

#RSRCSPATH = "/home/zak/Desktop/myDesk/javaProjects/el_meyloud_RE/api/src/main/resources/"
RSRCSPATH = "out/"

#reset everything
system("sudo rm out/static/* -r; sudo rm out/templates/* -r ")

#create templates
#static

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
            f.writelines(getTemplateLines(f"{j}{i}", getStyleLines("", css)) )
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

    