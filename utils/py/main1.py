with open("templates/template.html", "r") as f:
    template = f.readlines()
    f.close()


with open("templates/controller.java", "r") as f:
    controller = f.readlines()
    f.close()


for line in template:
    rslt = []
    rep = {"TITLE": "abc"}

    for j in rep:
        if f"<PY>{j}<PY>" in line:
            line = line.replace(f"<PY>{j}<PY>", f"<PY>{rep[j]}<PY>")

    rslt.append(line)

template = rep


for line in controller:
    rslt = []
    rep = {"APPNAME": "abc", "CONTROLLER_NAME": "abc", "URL": "abc"}

    for j in rep:
        if f"<PY>{j}<PY>" in line:
            line = line.replace(f"<PY>{j}<PY>", f"<PY>{rep[j]}<PY>")

    rslt.append(line)

controller = rep
