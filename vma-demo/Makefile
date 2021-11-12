post-category:
	curl -X POST -H "Content-Type: application/json" --data "@category.json" http://localhost:8080/api/vma/registry
post-songs:
	curl -X POST -H "Content-Type: application/json" --data "@songs.json" http://localhost:8080/api/vma/registry/random
get-songs:
	curl -i  http://localhost:8080/api/vma/registry/current
create-vmas: post-category post-songs get-songs
send-votes:
	python3 send_votes_first_candidates.py
send-song-votes:
	python3 send_votes_first_candidates_song_only.py
