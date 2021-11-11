from locust import HttpUser, task

class VmaVoting(HttpUser):
    @task
    def voting(self):
        print("Voting - Coming soon!")

