templates = {
    "controller":None,
    "template":None
}
for i in templates:
    with open(f"templates/{i}", "r") as f:
        templates[i]   = f.readlines()
        f.close()
 

def replaceOn(fileLines, repDict):
    rslt = []

    for line in fileLines:
        for j in repDict:
            if f"<PY>{j}<PY>" in line:
                line = line.replace(f"<PY>{j}<PY>", f"<PY>{repDict[j]}<PY>")

        rslt.append(line)

    return rslt


def getTemplateLines(title):
    return replaceOn(templates["template"], {"TITLE": title})


def getControllerLines(appName, ctrlName, url):
    return replaceOn(
        templates["controller"], {"APPNAME": appName, "CONTROLLER_NAME": ctrlName, "URL": url}
    )




