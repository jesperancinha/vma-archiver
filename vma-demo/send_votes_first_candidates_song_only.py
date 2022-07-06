import uuid

import requests
from requests.structures import CaseInsensitiveDict


def generateUuuid():
    return str(uuid.uuid1())


response = requests.get("http://localhost:8080/api/vma/registry/current")

urlSong = "http://localhost:8080/api/vma/voting/song"
headers = CaseInsensitiveDict()
headers["Cookie"] = f"votingId={generateUuuid()}"

for category in response.json():
    print(category["category"])
    print(category)
    if category["type"] == "SONG" or category["type"] == "INSTRUMENTAL":
        elected = category["songs"][0]
        payload = {
            "userId": generateUuuid(),
            "idC": category["id"],
            "idS": elected["id"]
        }
        resp = requests.post(
            urlSong,
            headers=headers,
            json=payload)
        print("=======")
        print(payload)
        print(resp.status_code)