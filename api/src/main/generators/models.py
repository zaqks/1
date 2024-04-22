MODELS_FOLDER = "/home/zak/Desktop/myDesk/javaProjects/el_meyloud_RE/src/main/java/com/zaqksdev/el_meyloud_RE/models/"


MODELS = {
    "Client": {
        "name": ["String", "@Column(length = 32)\n@Size(min = 3, max = 32)"],
        "surname": ["String", "@Column(length = 32)\n@Size(min = 3, max = 32)"],
        "nin": ["String", "@Column(length = 11)\n@Size(min = 11, max = 11)"],
        "phonenum": ["String", "@Column(length = 13)\n@Size(min = 10, max = 13)"],
        "email": ["String", "@Column(length = 32)\n@Size(min = 16, max = 32)"],
        "ccp": ["String", "@Column(length = 10)\n@Size(min = 10, max = 10)"],
        "key": ["String", "@Column(length = 2)\n@Size(min = 2, max = 2)"],
        "rip": ["String", "@Column(length = 19)\n@Size(min = 19, max = 19)"],
        "x": ["double"],
        "y": ["double"],
        "sells": ["Bool"],
    },
    "Agent": {
        "name": ["String", "@Column(length = 32)\n@Size(min = 3, max = 32)"],
        "surname": ["String", "@Column(length = 32)\n@Size(min = 3, max = 32)"],
        "nin": ["String", "@Column(length = 11)\n@Size(min = 11, max = 11)"],
        "phonenum": ["String", "@Column(length = 13)\n@Size(min = 10, max = 13)"],
        "email": ["String", "@Column(length = 32)\n@Size(min = 16, max = 32)"],
        "ccp": ["String", "@Column(length = 10)\n@Size(min = 10, max = 10)"],
        "key": ["String", "@Column(length = 2)\n@Size(min = 2, max = 2)"],
        "rip": ["String", "@Column(length = 19)\n@Size(min = 19, max = 19)"],
        "x": ["double"],
        "y": ["double"],
    },
    "Property": {
        "addr": ["String", "@Column(length = 64)\n@Size(min = 3, max = 64)"],
        "x": ["double"],
        "y": ["double"],
        "surf": ["int"],
        "rooms": ["int"],
        "pools": ["int"],
        "grgs": ["int"],
        "floors": ["int"],
        "owner": ["Client"],
    },
    "Offer": {},
    "Visit": {},
    "Contract": {},
}


for model in MODELS:
    clss = model
    content = []

    #imports
    content.append("package com.zaqksdev.el_meyloud_RE.models; import jakarta.persistence.*;")

    #class start
    content.append (f"@Entity\n @Table(name = \"{clss.lower()}s\")\n public class Product" + "{")
    

    #primary key
    content.append("@Id\n @GeneratedValue(strategy = GenerationType.IDENTITY)\n private int id;")

    

    model = MODELS[model]
    
    
    #attrs
    if model.keys().__len__():
        for attr in model:

            typ = model[attr][0]
            decs = ""
            if model[attr].__len__() - 1:
                decs = model[attr][1]

            content.append(f"\n{decs}")
            content.append(f"private {typ} {attr};")


        content.append("}")
            
            


        with open(f"{MODELS_FOLDER}{clss}.java", "w") as f:
            f.writelines(content)
            f.close()
