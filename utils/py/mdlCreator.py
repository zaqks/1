templates = {"controller": None, "template": None, "style": None}
for i in templates:
    with open(f"templates/{i}", "r") as f:
        templates[i] = f.readlines()
        f.close()


def replaceOn(fileLines, repDict):
    rslt = []

    for line in fileLines:
        for j in repDict:
            if f"<PY>{j}<PY>" in line:
                line = line.replace(f"<PY>{j}<PY>", f"{repDict[j]}")

        rslt.append(line)

    return rslt


def getStyleLines(src, srcs=[]):
    rslt = []

    for i in srcs:
        for j in replaceOn(templates["style"], {"URL": f"{src}/{i}.css"}):
            rslt.append(j)

    return "\t".join(rslt)


def getTemplateLines(title, style):
    return replaceOn(templates["template"], {"TITLE": title, "STYLE": style})


def getControllerLines(appName, ctrlName, url):
    return replaceOn(
        templates["controller"],
        {"APPNAME": appName, "CONTROLLER_NAME": ctrlName, "URL": url},
    )
