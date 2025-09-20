import zope.event
from locust import HttpUser, task

print("zope.event imported successfully ✅")

class DummyUser(HttpUser):
    @task
    def do_nothing(self):
        pass