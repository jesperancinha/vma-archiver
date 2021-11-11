import uuid

import requests
from requests.structures import CaseInsensitiveDict


def generateUuuid():
    return str(uuid.uuid1())


response = requests.get("http://localhost:8080/api/vma/registry/current")
for category in response.json():
    print(category["category"])
    print(category)

url = "http://localhost:8080/api/vma/voting/artist"

headers = CaseInsensitiveDict()
headers["Cookie"] = f"votingId={generateUuuid()}"

resp = requests.post(url,
                     headers=headers,
                     json={
                         "userID": generateUuuid(),
                         "idC": generateUuuid(),
                         "idA": generateUuuid()
                     })
print(resp.status_code)
