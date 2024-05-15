from requests import post

URL = "http://127.0.0.01:8000/client/offer/add/1"
DATA = {
    "price":90000,
    "rent":True,
    "description":"bon hada c juste un test frero"
}
COOKIES  ={
    "email":"abcefght%40yahoo.fr",
    "password": "y%26%21SKB9E7UM%26%26_%23"
}

response = post(url=URL, data=DATA, cookies=COOKIES)



print(f"Sent: {'password' not in str(response.content)}")

