from random import randint

import requests
from requests.structures import CaseInsensitiveDict

response = requests.get("http://localhost:8080/api/vma/registry/current")

urlArtist = "http://localhost:8080/api/vma/voting/artist"
urlSong = "http://localhost:8080/api/vma/voting/song"
open_voting = "http://localhost:8080/api/vma/voting/open"

open_result = requests.get(open_voting).json()
print(open_result)
headers = CaseInsensitiveDict()
user_id = open_result['id']
headers["Cookie"] = f"votingId={user_id}"

for category in response.json():
    print(category["category"])
    print(category)
    if category["type"] == "ARTIST":
        elected = category["artists"][randint(0, len(category['artists'])-1)]
        resp = requests.post(
            urlArtist,
            headers=headers,
            json={
                "userId": user_id,
                "idC": category["id"],
                "idA": elected["id"]
            })
        print(resp.status_code)
    if category["type"] == "SONG" or category["type"] == "INSTRUMENTAL":
        elected = category["songs"][randint(0, len(category['songs'])-1)]
        payload = {
            "userId": user_id,
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
