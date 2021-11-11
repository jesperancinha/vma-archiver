import requests
response = requests.get("http://localhost:8080/api/vma/registry/current")
for category in response.json():
    print(category["category"])
    print(category)



